package com.eling.elcms.privilege.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Id;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.eling.elcms.core.AppContext;
import com.eling.elcms.core.dao.hibernate.CriteriaUtils;
import com.eling.elcms.core.dao.hibernate.ICriteriaEnhancer;
import com.eling.elcms.core.dao.hibernate.IQueryCondition;
import com.eling.elcms.core.exception.AppException;
import com.eling.elcms.core.model.BaseModel;
import com.eling.elcms.core.service.IGenericModelService;
import com.eling.elcms.core.service.ServiceLocator;
import com.eling.elcms.core.util.PropertyUtils;
import com.eling.elcms.privilege.model.User;
import com.eling.elcms.privilege.service.IRbacService;

/**
 * 自动附加数据权限语句的查询统一增强器，参数格式为“code：code实体对应本实体的完整路径”，比如：活动模型的参数配置为“creater.organization”
 * 
 * @author NieCR 
 *
 */
public class CommonPrivilegeCriteriaEnhancer implements ICriteriaEnhancer {
	
	private Map<String, String> paths;
	
	private IRbacService rbacService = ServiceLocator.getService(IRbacService.class);
	
	private IGenericModelService gms = ServiceLocator.getService(IGenericModelService.class);
	
	@Override	
	public void init(String parameter) {
		paths = new HashMap<>();
		for (String cell : parameter.split(";")) {
			paths.put(cell.split(":")[0], cell.split(":")[1]);
		}
	}
	
	private boolean filterPkOrg(Long pkOrg){
		try{
			Object cond = Class.forName("com.eling.elcms.system.model.OrgParameter").newInstance();
			PropertyUtils.setProperty(cond, "organization.pkOrganization", pkOrg);
			PropertyUtils.setProperty(cond, "sysParamValue.sysParameter.code", "data.filted");
			PropertyUtils.setProperty(cond, "sysParamValue.value", "true");
			Object[] args = new Object[]{cond,Class.forName("com.eling.elcms.system.model.OrgParameter")};
			@SuppressWarnings("unchecked")
			List< ? extends BaseModel> memPoilist =(List<? extends BaseModel>) MethodUtils.invokeMethod(gms, "query", args);
			return memPoilist.size()>0;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Criteria enhance(Criteria criteria, Object cond) {
		// 后台任务执行时，使用匿名用户，不附加权限
		if (AppContext.anonymous == AppContext.curUser()) {
			return criteria;
		}
		if (cond instanceof IQueryCondition) {
			// 如果不进行权限校验，则不附加权限
			IQueryCondition qc = (IQueryCondition) cond;
			if (Boolean.FALSE.equals(qc.getValidatePrivilege())) {
				return criteria;
			}
		}
		List<String> codeList  = new ArrayList<>(paths.keySet());
		if(codeList.isEmpty()){
			// 如果没有配置权限，则返回加强器
			return criteria;
		}
		List<String> bussinessScenses = new ArrayList<>();
		if(PropertyUtils.getProperty(cond, "bussinessScenses") != null){
			 Arrays.asList(PropertyUtils.getProperty(cond, "bussinessScenses"));
		}
		for (String code : codeList) {
			List<Long> pks = new ArrayList<>();
			String pkName ="";
			//如果走机构增强器，默认加入壹零后平台
			if(code.equals("organization")){
				pks.add(1L);
			}
			if(code.equals("member")){
				
				//首先查询当前用户的服务点 
				List<Long> serpointPks = rbacService.getUserAssignment(AppContext.curUser().getPkUser(),"servicepoint");
				//查询对应会员pk
				try {
					if(serpointPks.isEmpty()){
						pkName ="organization.pkOrganization";
						pks = rbacService.getUserAssignment(AppContext.curUser().getPkUser(),"organization",bussinessScenses);
						List<Long> filters = new ArrayList<>();
						for(Long pkOrg:pks){
							if(filterPkOrg(pkOrg)){
								filters.add(pkOrg);
							}
						}
						pks.removeAll(filters);
					}else{
						pkName ="pkMember";
						Object memPoicond =Class.forName("com.eling.elcms.member.model.MemberPoint").newInstance();
						PropertyUtils.setProperty(memPoicond, "pkServicePointIn", serpointPks);
						Object[] args = new Object[]{memPoicond,Class.forName("com.eling.elcms.member.model.MemberPoint")};
						@SuppressWarnings("unchecked")
						List< ? extends BaseModel> memPoilist =(List<? extends BaseModel>) MethodUtils.invokeMethod(gms, "query", args);
						User user = AppContext.curUser();
						for(BaseModel memPoint:memPoilist){
							Long pkMember = PropertyUtils.getProperty(memPoint, "pkMember");
							Object memCond =Class.forName("com.eling.elcms.member.model.Member").newInstance();
							PropertyUtils.setProperty(memCond, "creater.pkUser", user.getPkUser());
							PropertyUtils.setProperty(memCond, "pkMember", pkMember);
							Object[] args2 = new Object[]{memCond,Class.forName("com.eling.elcms.member.model.Member")};
							@SuppressWarnings("unchecked")
							List< ? extends BaseModel> memlist =(List<? extends BaseModel>)  MethodUtils.invokeMethod(gms, "query", args2);
							if(!CollectionUtils.isEmpty(memlist)){
								pks.addAll(memPoilist.stream().map(a->(Long)PropertyUtils.getProperty(a, "pkMember")).collect(Collectors.toList()));
							}
						}
						//pks.addAll(memPoilist.stream().map(a->(Long)PropertyUtils.getProperty(a, "pkMember")).collect(Collectors.toList()));
					}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
					throw new AppException("数据异常");
				}
				
			}else{
				 pkName = getModelPkName(rbacService.getPermissionTypeModelClassByCode(code));
				 pks.addAll(rbacService.getUserAssignment(AppContext.curUser().getPkUser(),code,bussinessScenses));
			}
			//pks为空就是没有数据权限，加入一个没有权限的pk
			if(pks.isEmpty()){
				pks.add(-99L);
			}
			CriteriaUtils.getOrCreateCriteria(criteria, paths.get(code)).add(Restrictions.in(pkName,pks));
		}
		return criteria;
	}
	
	private String getModelPkName(Class<?> baseModelClass) {
		for (Field f : PropertyUtils.getAllDeclaredFields(baseModelClass)) {
			if (null != f.getAnnotation(Id.class)) {
				return  f.getName();
			}
		}
		return "";
	}
}
