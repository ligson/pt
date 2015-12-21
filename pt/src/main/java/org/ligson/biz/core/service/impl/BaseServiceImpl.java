package org.ligson.biz.core.service.impl;


import java.util.List;

import org.ligson.biz.core.dao.ISuperDAO;
import org.ligson.biz.core.entity.BasePageDTO;
import org.ligson.biz.core.entity.BasicEntity;
import org.ligson.biz.core.entity.Pagination;
import org.ligson.biz.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 描述: 通用数据库操作service类，其他数据库操作service类均需继承该类
 *
 * @version V1.0
 */
public class BaseServiceImpl<T extends BasicEntity> implements BaseService<T> {

    @Autowired
    public ISuperDAO superDAO;

    /**
     * 获取superDAO
     *
     * @return superDAO superDAO
     */
    public ISuperDAO getSuperDAO() {
        return superDAO;
    }


    public Long getNextIdVal(String tableName) {
        return superDAO.getNextIdVal(tableName);
    }


    public BasicEntity get(BasicEntity entity) {
        return superDAO.get(entity);
    }


    @Override
    public List<T> getList(T entity) {
        return (List<T>) superDAO.getList(entity);
    }

    @Override
    public Integer insert(T entity) {
        return this.superDAO.insert(entity);
    }


    public Integer insertBak(BasicEntity entity) {
        return this.superDAO.insert(entity);
    }


    public Integer update(BasicEntity entity) {
        return this.superDAO.update(entity);
    }


    public Integer delete(BasicEntity entity) {
        return this.superDAO.delete(entity);
    }


    public Integer updateBak(BasicEntity entity) {
        return this.superDAO.updateBak(entity);
    }

    @SuppressWarnings("rawtypes")

    public Pagination getPagenationList(String statementName, BasePageDTO baseParamDTO) {
        return this.superDAO.getPagenationList(statementName, baseParamDTO);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})

    public List getList(String statementName, Object parameterObject) {
        return superDAO.getList(statementName, parameterObject);
    }


    public int batchInsert(List<? extends BasicEntity> basicEntityList) {
        return superDAO.batchInsert(basicEntityList);
    }


    public int batchUpdate(List<? extends BasicEntity> basicEntityList) {
        return superDAO.batchUpdate(basicEntityList);
    }
    
    @Override
	public Integer getCount(String statementName,BasicEntity entity) {
		return superDAO.getCount(statementName,entity);
	}
	@Override
	public Integer deleteFamily(String statementName,BasicEntity entity) {
		return superDAO.deleteFamily(statementName,entity);
	}

	public int queryByName(String statementName, BasicEntity entity) {
		return superDAO.queryByName(statementName,entity);
	}

	public String getFamilyIdByName(String statementName, BasicEntity entity) {
		return superDAO.getFamilyIdByName(statementName,entity);
	}

}
