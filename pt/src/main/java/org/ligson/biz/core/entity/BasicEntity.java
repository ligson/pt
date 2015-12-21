package org.ligson.biz.core.entity;

import java.io.Serializable;

import org.ligson.biz.core.service.BaseService;


/**
 * 基本实体类
 *
 */
@SuppressWarnings("serial")
public class BasicEntity extends BasePageDTO implements Serializable {
    private char _action_ = 'N';//N:no-operation,U:update,I:insert

    public String primaryKey() {
        throw new IllegalArgumentException("Invalid operation - getPrimaryKey()!");
    }

    public String className() {
        throw new IllegalArgumentException("Invalid operation - getClassName()!");
    }

    public void markUpdate() {
        if(_action_ != 'N')
            return;
        _action_ = 'U';
    }

    public void markInsert() {
        if(_action_ != 'N')
            return;
        _action_ = 'I';
    }

    public void markDelete() {
        if(_action_ != 'N')
            return;
        _action_ = 'D';
    }

    public void forceUpdate() {
        _action_ = 'U';
    }

    public void forceInsert() {
        _action_ = 'I';
    }

    public void foreDelete() {
        _action_ = 'D';
    }

    public boolean canUpdate() {
        return _action_ == 'U';
    }

    public boolean canInsert() {
        return _action_ == 'I';
    }

    public boolean canDelete() {
        return _action_ == 'D';
    }

    public BaseService service() {
        throw new IllegalArgumentException("Invalid operation - getService()!");
    }
    
    /**
     * 排序信息 该字段会自动封装到查询sql中
     * 使用举例："USER_ID DESC,CREATED_TIME ASC"
     */
    public String columnSort;
	/**
     * 获取排序信息 该字段会自动拼装到查询sql中
     * 使用举例："USER_ID DESC,CREATED_TIME ASC"
     */
	public String getColumnSort() {
		return columnSort;
	}

	 /**
     * 设置排序信息 该字段会自动拼装到查询sql中
     * 使用举例："USER_ID DESC,CREATED_TIME ASC"   #columnSort 防止SQL注入
     */
	public void setColumnSort(String columnSort) {
		this.columnSort = columnSort;
	}
	
}
