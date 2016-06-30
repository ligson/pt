package org.ligson.biz.core.service;


import java.util.List;

import org.ligson.biz.core.entity.BasePageDTO;
import org.ligson.biz.core.entity.BasicEntity;
import org.ligson.biz.core.entity.Pagination;


/**
 * 描述: 通用数据库操作service类，其他数据库操作service类均需继承该类
 * @version V1.0
 */
public interface BaseService<T extends BasicEntity> {
	
	/**
     * 描述: 自定义根据序列号名称获取对应的下一序列号值方法
     * @param tableName 表名称
     * @return
     */
	public Long getNextIdVal(String tableName);

	/**
	 * 
	 * 描述: 根据主键查询实体信息
	 * @param entity
	 * @return
	 */
    public BasicEntity get(BasicEntity entity);

    /**
     * 
     * 描述:根据条件查询实体信息
     * @param entity
     * @return
     */
    public List<T> getList(T entity);
    
    /**
     * 描述: 插入实体信息
     * @param entity 需要插入的实体信息
     * @return
     */
	public Integer insert(T entity);

    /**
     * 描述: 插入实体信息
     * @param entity 需要插入的实体信息
     * @return
     */
    public Integer insertBak(BasicEntity entity);
	
	/**
     * 描述: 更新实体信息
     * @param entity 需要更新的实体信息
     * @return
     */
	public Integer update(BasicEntity entity);
	
	/**
     * 描述: 删除实体信息
     * @param entity 需要删除的实体信息
     * @return
     */
	public Integer delete(BasicEntity entity);
	
	/**
     * 描述: 更新实体信息（可以设置字段为空）
     * @param entity 需要更新的实体信息
     * @return
     */
	public Integer updateBak(BasicEntity entity);
	
	/**
	 * 
	 * 描述:自定义分页查询通用方法
	 * @param statementName sql查询ID
	 * @param baseParamDTO  分页查询条件
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Pagination<T> getPagenationList(String statementName, BasePageDTO baseParamDTO);
	
	/**
	 * 
	 * 描述: 根据条件查询实体信息
	 * @param statementName sql查询ID
	 * @param parameterObject 查询条件
	 * @return
	 */
	public List<Object> getList(String statementName,Object parameterObject);
	
	/**
     * 
     * 描述: 批量插入实体信息
     * @param basicEntityList 需要批量插入的实体信息
     * @return
     */
    public int batchInsert(List<? extends BasicEntity> basicEntityList);
    
    /**
     * 
     * 描述: 批量更新实体信息
     * @param basicEntityList 需要批量更新的实体信息
     * @return
     */
    public int batchUpdate(List<? extends BasicEntity> basicEntityList);
    
    /**
     * 描述: 获取实体数量
     * @param statementName sql查询ID
     * @param entity  获取实体数量
     * @return
     */
	public Integer getCount(String statementName,BasicEntity entity);
	/**
     * 描述: 删除实体信息（可以设置字段为空）
     * @param statementName sql查询ID
     * @param entity 需要删除的实体信息
     * @return
     */
	Integer deleteFamily(String statementName, BasicEntity entity);
	
    
}
