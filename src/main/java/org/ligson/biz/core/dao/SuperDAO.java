package org.ligson.biz.core.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.ligson.biz.core.entity.BasePageDTO;
import org.ligson.biz.core.entity.BasicEntity;
import org.ligson.biz.core.entity.Pagination;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;



@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class SuperDAO implements ISuperDAO {

    public static final Logger log = LoggerFactory.getLogger(SuperDAO.class);

    private static final String pagenationStatementSuffix = "Mapper.getPagenationList";
    private static final String pagenationCountStatementSuffix = "-count";
    private static final String getListStatementSuffix = "Mapper.getList";
    private static final String selectStatementSuffix = "Mapper.findByPriKey";
    private static final String insertStatementSuffix = "Mapper.insert";
    private static final String updateStatementSuffix = "Mapper.update";
    private static final String deleteStatementSuffix = "Mapper.deleteByPriKey";
    private static final String getNextIdValStatement = "CommonEntity.getNextIdVal";
    private static final String batchUpdateStatementSuffix = "Mapper.batchUpdate";
    private static final String batchInsertStatementSuffix = "Mapper.batchInsert";

    /**
     * 批量sql单次执行的数量
     */
    private static final int batchExecuteOnceCount = 300;
    /**
     * 运行环境的SessionTemplate
     */
    @Autowired
    private SqlSessionTemplate userSqlSessionTemplate;


    /**
     * 运行环境的SessionTemplate
     *//*
    private SqlSessionTemplate sessionTemplate;

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
    
	public SqlSession getSqlSession(){
		return teslaSqlSessionTemplate.getSqlSessionFactory().openSession();
	}*/

    /*
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#update(java.lang.String, java.lang.Object)
     */
    @Override
    public Integer update(String statementName, Object parameterObject) {
        return userSqlSessionTemplate.update(statementName, parameterObject);
    }

    /*
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#update(java.lang.Object)
     */
    @Override
    public Integer update(BasicEntity parameterObject) {
        String statementName = parameterObject.getClass().getSimpleName() + updateStatementSuffix;
        return this.update(statementName, parameterObject);
    }

    /*
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#delete(java.lang.String, java.lang.Object)
     */
    @Override
    public Integer delete(String statementName, Object parameterObject) {
        return userSqlSessionTemplate.delete(statementName, parameterObject);
    }

    /*
     * 
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#delete(java.lang.Object)
     */
    @Override
    public Integer delete(BasicEntity parameterObject) {
        String statementName = parameterObject.getClass().getSimpleName() + deleteStatementSuffix;
        return this.delete(statementName, parameterObject);
    }

    /*
     * 
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#insert(java.lang.String, java.lang.Object)
     */
    @Override
    public Integer insert(String statementName, Object parameterObject) {
        return userSqlSessionTemplate.insert(statementName, parameterObject);
    }

    /*
     * 
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#insert(com.wangyin.wallet.common.BasicEntity)
     */
    @Override
    public Integer insert(BasicEntity parameterObject) {
        String statementName = parameterObject.getClass().getSimpleName() + insertStatementSuffix;
        return userSqlSessionTemplate.insert(statementName, parameterObject);
    }

    /*
     * 
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#get(java.lang.Object)
     */
    @Override
    public BasicEntity get(BasicEntity parameterObject) {
        String statementName = parameterObject.getClass().getSimpleName() + selectStatementSuffix;
        return userSqlSessionTemplate.selectOne(statementName, parameterObject);
    }

    /*
     * 
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#getList(java.lang.String, java.lang.Object)
     */
    @Override
    public List<Object> getList(String statementName, Object parameterObject) {
        if (parameterObject instanceof BasicEntity) {
            statementName = parameterObject.getClass().getSimpleName() + "Mapper." + statementName;
        }
        return userSqlSessionTemplate.selectList(statementName, parameterObject);
    }

    /*
     *
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#getList(java.lang.Object)
     */
    @Override
    public List<BasicEntity> getList(BasicEntity parameterObject) throws DataAccessException {
        String statementName = parameterObject.getClass().getSimpleName() + getListStatementSuffix;
        return userSqlSessionTemplate.selectList(statementName, parameterObject);
    }

    /*
     * 
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#getPagenationList(java.lang.String, com.wangyin.wallet.common.dto.BasePageDTO)
     */
    @Override
    public Pagination getPagenationList(String statementName, BasePageDTO baseParamDTO) {
        if (baseParamDTO == null || !baseParamDTO.isPage()) {
            return null;
        }
        /**
         * 判断pageNum和pageSize
         */
        if (baseParamDTO.getPageNum() == null || baseParamDTO.getPageNum().intValue() < 1) {
            baseParamDTO.setPageNum(Pagination.DEFAULT_PAGE_NUM);
        }
        if (baseParamDTO.getPageSize() == null || baseParamDTO.getPageSize().intValue() < 1) {
            baseParamDTO.setPageSize(Pagination.DEFAULT_PAGE_SIZE);
        }

        // 计算记录起始值和结束值
        baseParamDTO.setEndIdx(baseParamDTO.getPageSize() * baseParamDTO.getPageNum());
        baseParamDTO.setStartIdx(baseParamDTO.getPageSize() * (baseParamDTO.getPageNum() - 1));//mysql 索引下标从0开始

        Integer totalCount = (Integer) userSqlSessionTemplate.selectOne(statementName + pagenationCountStatementSuffix, baseParamDTO);

        List resultList = userSqlSessionTemplate.selectList(statementName, baseParamDTO);

        return new Pagination(baseParamDTO.getPageSize(), baseParamDTO.getPageNum(), totalCount, resultList);
    }

    /*
     * 
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#getPagenationList(com.wangyin.wallet.common.dto.BasePageDTO)
     */
    @Override
    public Pagination getPagenationList(BasePageDTO baseParamDTO) {
        String statementName = baseParamDTO.getClass().getSimpleName() + pagenationStatementSuffix;
        return this.getPagenationList(statementName, baseParamDTO);
    }

    /*
     * 
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#getMap(java.lang.String, java.lang.Object, java.lang.String)
     */
    @Override
    public <T, V> Map<T, V> getMap(String statementName, T parameterObject, String key) {
        return userSqlSessionTemplate.selectMap(statementName, parameterObject, key);
    }

    /*
     * 
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#getObject(java.lang.String, java.lang.Object)
     */
    @Override
    public <T> T getObject(String statementName, T parameterObject) {
        return (T) userSqlSessionTemplate.selectOne(statementName, parameterObject);
    }


    @Override
    public Long getNextIdVal(String tableName) {
        return userSqlSessionTemplate.selectOne(getNextIdValStatement, tableName);
    }

    /*
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.dao.ISuperDAO#update(java.lang.Object)
     */
    @Override
    public Integer updateBak(BasicEntity parameterObject) {
        String statementName = parameterObject.getClass().getSimpleName() + updateStatementSuffix + "Bak";
        return this.update(statementName, parameterObject);
    }

    /* 
     * desc:
	 * (non-Javadoc)
	 * @see com.wangyin.payment.tesla.common.dao.ISuperDAO#batchInsert(java.util.List)
	 */
    @Override
    public int batchInsert(List<? extends BasicEntity> basicEntityList) {
        //每次I/O提交数量
        int singleNum = batchExecuteOnceCount;
        //sql执行影响数据行数
        int affectedRows = 0;
        try {
            if (basicEntityList != null && basicEntityList.size() > 0) {
                String statementName = basicEntityList.get(0).getClass().getSimpleName();
                statementName = statementName + batchInsertStatementSuffix;
                int rowSize = basicEntityList.size();//数据总量
                int fromIndex = 0;//起始序号
                int endIndex = 0;//结束序号
                log.debug("批量执行插入【" + statementName + "】开始：" + System.currentTimeMillis());
                for (int i = 0; i == 0 || i < Math.ceil(Double.valueOf(Integer.valueOf(rowSize / singleNum))); i++) {
                    endIndex = (i + 1) * singleNum;//默认结束序号滚动一页
                    if (endIndex >= rowSize) {//如结束序号大于等于数据总量时，把数据总量赋值给结束序列
                        endIndex = rowSize;
                    }
                    log.debug("批量执行插入【" + statementName + "】第【" + (i + 1) + "】次共【" + (endIndex - fromIndex) + "】条记录开始：" + System.currentTimeMillis());
                    affectedRows += userSqlSessionTemplate.insert(statementName, basicEntityList.subList(fromIndex, endIndex));
                    log.debug("批量执行插入【" + statementName + "】第【" + (i + 1) + "】次共【" + (endIndex - fromIndex) + "】条记录结束：" + System.currentTimeMillis());
                    fromIndex = endIndex;
                }
                log.debug("批量执行插入【" + statementName + "】结束：" + System.currentTimeMillis());
            }
        } catch (Exception e) {
            log.error("系统批量插入数据出错：", e);
            throw new RuntimeException(e);
        }
        return affectedRows;
    }

    /*
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.common.dao.ISuperDAO#batchUpdate(java.util.List)
     */
    @Override
    public int batchUpdate(List<? extends BasicEntity> basicEntityList) {
        //每次I/O提交数量
        int singleNum = batchExecuteOnceCount;
        //sql执行影响数据行数
        int affectedRows = 0;
        try {
            if (basicEntityList != null && basicEntityList.size() > 0) {
                String statementName = basicEntityList.get(0).getClass().getSimpleName();
                statementName = statementName + batchUpdateStatementSuffix;
                int rowSize = basicEntityList.size();//数据总量
                int fromIndex = 0;//起始序号
                int endIndex = 0;//结束序号
                log.debug("批量执行更新【" + statementName + "】开始：" + System.currentTimeMillis());
                for (int i = 0; i == 0 || i < Math.ceil(Double.valueOf(Integer.valueOf(rowSize / singleNum))); i++) {
                    endIndex = (i + 1) * singleNum;//默认结束序号滚动一页
                    if (endIndex >= rowSize) {//如结束序号大于等于数据总量时，把数据总量赋值给结束序列
                        endIndex = rowSize;
                    }
                    log.debug("批量执行更新【" + statementName + "】第【" + (i + 1) + "】次共【" + (endIndex - fromIndex) + "】条记录开始：" + System.currentTimeMillis());
                    affectedRows += userSqlSessionTemplate.update(statementName, basicEntityList.subList(fromIndex, endIndex));
                    log.debug("批量执行更新【" + statementName + "】第【" + (i + 1) + "】次共【" + (endIndex - fromIndex) + "】条记录结束：" + System.currentTimeMillis());
                    fromIndex = endIndex;
                }
                log.debug("批量执行更新【" + statementName + "】结束：" + System.currentTimeMillis());
            }
        } catch (Exception e) {
            log.error("系统批量更新数据出错：", e);
            throw new RuntimeException(e);
        }
        return affectedRows;
    }

    /*
     * desc:
     * (non-Javadoc)
     * @see com.wangyin.payment.tesla.common.dao.ISuperDAO#getList(java.lang.String, java.lang.Object, org.apache.ibatis.session.RowBounds)
     */
    @Override
    public List<? extends BasicEntity> getList(String statementName, Object parameterObject, RowBounds rowBounds) {
        return userSqlSessionTemplate.selectList(statementName, parameterObject, rowBounds);
    }

    @Override
    public Integer getCount(String statementName, BasicEntity entity) {
        statementName = entity.getClass().getSimpleName() + "Mapper." + statementName;
        return (Integer) userSqlSessionTemplate.selectOne(statementName, entity);
    }

    @Override
    public Integer getCount(String statementName, Object parameterObject) {
        if (parameterObject instanceof BasicEntity) {
            return getCount(statementName, (BasicEntity) parameterObject);
        } else {
            return (Integer) userSqlSessionTemplate.selectOne(statementName, parameterObject);
        }
    }

    @Override
    public Integer deleteFamily(String statementName, BasicEntity entity) {
        statementName = entity.getClass().getSimpleName() + "Mapper." + statementName;
        return (Integer) userSqlSessionTemplate.update(statementName, entity);
    }

    @Override
    public int queryByName(String statementName, BasicEntity entity) {
        statementName = entity.getClass().getSimpleName() + "Mapper." + statementName;
        return (Integer) userSqlSessionTemplate.selectOne(statementName, entity);
    }

    @Override
    public String getFamilyIdByName(String statementName, BasicEntity entity) {
        statementName = entity.getClass().getSimpleName() + "Mapper." + statementName;
        return (String) userSqlSessionTemplate.selectOne(statementName, entity);
    }

}
