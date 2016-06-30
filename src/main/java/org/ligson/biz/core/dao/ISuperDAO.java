package org.ligson.biz.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.ligson.biz.core.entity.BasePageDTO;
import org.ligson.biz.core.entity.BasicEntity;
import org.ligson.biz.core.entity.Pagination;



/**
 * 描述: 通用数据库操作DAO接口类，该类实现了常用的数据库操作
 *
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
public interface ISuperDAO {

    /**
     * 描述: 自定义更新实体信息
     *
     * @param statementName   自定义更新sql映射对应的ID
     * @param parameterObject 需要更新的实体信息
     * @return
     */
    public Integer update(String statementName, Object parameterObject);

    /**
     * 描述: 系统默认更新实体信息
     *
     * @param parameterObject 需要更新的实体信息
     * @return
     */
    public Integer update(BasicEntity parameterObject);

    /**
     * 描述: 自定义删除满足条件的实体信息
     *
     * @param statementName   自定义删除sql映射对应的ID
     * @param parameterObject 需要删除的实体条件信息
     * @return
     */
    public Integer delete(String statementName, Object parameterObject);

    /**
     * 描述: 系统默认删除满足条件的实体信息
     *
     * @param parameterObject 需要删除的实体条件信息
     * @return
     */
    public Integer delete(BasicEntity parameterObject);

    /**
     * 描述: 自定义插入实体信息
     *
     * @param statementName   自定义插入sql映射对应的ID
     * @param parameterObject 需要插入的实体信息
     * @return
     */
    public Integer insert(String statementName, Object parameterObject);

    /**
     * 描述: 系统默认插入实体信息
     *
     * @param parameterObject 需要插入的实体信息
     * @return
     */
    public Integer insert(BasicEntity parameterObject);

    /**
     * 描述: 系统默认根据主键查询唯一记录
     *
     * @param parameterObject 实体参数查询条件（需包含主键信息）
     * @return
     */
    public BasicEntity get(BasicEntity parameterObject);

    /**
     * 描述: 自定义根据实体参数查询满足条件的记录
     *
     * @param statementName   自定义查询ID
     * @param parameterObject 实体参数查询条件
     * @return
     */
    public List<Object> getList(String statementName, Object parameterObject);

    /**
     * 描述: 系统默认根据实体参数查询满足条件的记录
     *
     * @param parameterObject 实体参数查询条件
     * @return
     */
    public List<BasicEntity> getList(BasicEntity parameterObject);

    /**
     * 描述: 自定义分页查询通用方法
     *
     * @param statementName 自定义查询ID
     * @param baseParamDto  查询参数
     * @return
     */
    public Pagination getPagenationList(String statementName, BasePageDTO baseParamDto);

    /**
     * 描述: 系统默认分页查询方法
     *
     * @param baseParamDto 查询参数
     * @return
     */
    public Pagination getPagenationList(BasePageDTO baseParamDto);

    /**
     * 描述: 获取结果集为Map信息
     *
     * @param statementName   查询sql映射ID
     * @param parameterObject 参数信息
     * @param key             主键信息
     * @return
     */
    public <T, V> Map<T, V> getMap(String statementName, T parameterObject, String key);


    /**
     * 描述: 通用获取结果集首行记录信息
     *
     * @param statementName   sql映射ID
     * @param parameterObject 查询条件参数信息
     * @return
     */
    public <T> T getObject(String statementName, T parameterObject);

    /**
     * 描述: 自定义根据序列号名称获取对应的下一序列号值方法
     *
     * @param tableName 表名称
     * @return
     */
    public Long getNextIdVal(String tableName);

    /**
     * 描述: 系统默认更新实体信息(可以设置字段为空)
     *
     * @param parameterObject 需要更新的实体信息
     * @return
     */
    public Integer updateBak(BasicEntity parameterObject);

    /**
     * 描述: 批量插入实体信息
     *
     * @param basicEntityList 需要批量插入的实体信息
     * @return
     */
    public int batchInsert(List<? extends BasicEntity> basicEntityList);

    /**
     * 描述: 批量更新实体信息
     *
     * @param basicEntityList 需要批量更新的实体信息
     * @return
     */
    public int batchUpdate(List<? extends BasicEntity> basicEntityList);

    /**
     * 描述: 自定义查询（可以指定查询数量）
     *
     * @param statementName   自定义查询ID
     * @param parameterObject 自定义查询参数
     * @param rowBounds       查询数量设置
     * @return
     */
    public List<? extends BasicEntity> getList(String statementName, Object parameterObject, RowBounds rowBounds);

    /**
     * 描述: 获取实体数量
     *
     * @param entity 获取实体数量
     * @return
     */
    public Integer getCount(String statementName, BasicEntity entity);

    public Integer getCount(String statementName, Object parameterObject);

    /**
     * 描述: 删除实体信息（可以设置字段为空）
     *
     * @param statementName sql查询ID
     * @param entity        需要删除的实体信息
     * @return
     */
    public Integer deleteFamily(String statementName, BasicEntity entity);

    public int queryByName(String statementName, BasicEntity entity);

    public String getFamilyIdByName(String statementName, BasicEntity entity);

}
