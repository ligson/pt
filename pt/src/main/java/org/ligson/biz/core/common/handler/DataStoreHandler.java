package org.ligson.biz.core.common.handler;

import java.util.List;

import org.ligson.biz.core.base.DataStore;
import org.ligson.biz.core.entity.BasicEntity;
import org.ligson.biz.core.utils.FastJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * 数据处理工具类
 *
 */
@Component
public class DataStoreHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(DataStoreHandler.class);

	/**
	 * 存储数据
	 * @param dataStore
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class }) 
	public Boolean save(DataStore dataStore) throws Exception {
		int result = 0;
		if(dataStore==null){
            throw new RuntimeException("data store is null.");
		}else{
			List<BasicEntity> basicEntityList = dataStore.getUpdateOrInsertOrDeleteList();
			if(basicEntityList==null || basicEntityList.size()==0){
				return false;
			}else{
				logger.info("basicEntityList="+basicEntityList.size());
				for(BasicEntity basicEntiy : basicEntityList){
					/*try{*/
					if(basicEntiy.canInsert()){
						try {
                            result = basicEntiy.service().insert(basicEntiy);
                        } catch (Exception e) {
                            logger.error("DataStoreHandler Save error - INSERT:"+FastJsonUtils.toJSONString(basicEntiy));
                            throw e;
                        }
					}else if(basicEntiy.canUpdate()){
						result = basicEntiy.service().updateBak(basicEntiy);
                        if(result==0){
                            logger.error("DataStoreHandler Save - UPDATE:"+FastJsonUtils.toJSONString(basicEntiy));
                            throw new RuntimeException("DataStoreHandler Save - UPDATE:"+FastJsonUtils.toJSONString(basicEntiy));
                        }
					} else {
                        result = basicEntiy.service().delete(basicEntiy);
                        if(result==0){
                            logger.error("DataStoreHandler Save - DELETE:"+FastJsonUtils.toJSONString(basicEntiy));
                            throw new RuntimeException("DataStoreHandler Save - DELETE:"+FastJsonUtils.toJSONString(basicEntiy));
                        }
                    }
				}
			}
		}
		return true;
	}
	
    @Transactional(rollbackFor = { Exception.class })
    public Boolean saveInBatch(List<? extends DataStore> dataStoreList) throws Exception {
        int result = 0;
        if(dataStoreList==null || dataStoreList.size()==0){
            return false;
        }else{
            for(DataStore dataStore : dataStoreList) {
                List<BasicEntity> basicEntityList = dataStore.getUpdateOrInsertOrDeleteList();
                if(basicEntityList==null || basicEntityList.size()==0){
                    return false;
                }else{
                    logger.info("basicEntityList="+basicEntityList.size());
                    for(BasicEntity basicEntiy : basicEntityList){
                        if(basicEntiy.canInsert()){
                            try {
                                result = basicEntiy.service().insert(basicEntiy);
                            } catch (Exception e) {
                                logger.error("DataStoreHandler saveInBatch error - INSERT:"+FastJsonUtils.toJSONString(basicEntiy));
                                throw e;
                            }
                        }else if(basicEntiy.canUpdate()){
                            result = basicEntiy.service().updateBak(basicEntiy);
                            if(result==0){
                                logger.error("saveInBatch saveInBatch - UPDATE:"+FastJsonUtils.toJSONString(basicEntiy));
                                throw new RuntimeException("saveInBatch saveInBatch - UPDATE:"+FastJsonUtils.toJSONString(basicEntiy));
                            }
                        } else {
                            result = basicEntiy.service().delete(basicEntiy);
                            if(result==0){
                                logger.error("saveInBatch saveInBatch - DELETE:"+FastJsonUtils.toJSONString(basicEntiy));
                                throw new RuntimeException("saveInBatch saveInBatch - DELETE:"+FastJsonUtils.toJSONString(basicEntiy));
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Data Store Handler for Data Migration
     * @param dataStore
     * @return
     */
    @Transactional(rollbackFor = { Exception.class })
    public Boolean saveForDataMigration(DataStore dataStore) throws Exception {
        int result = 0;
        if(dataStore==null){
            return false;
        }else{
            List<BasicEntity> basicEntityList = dataStore.getUpdateOrInsertOrDeleteList();
            if(basicEntityList==null || basicEntityList.size()==0){
                return false;
            }else{
                logger.info("basicEntityList="+basicEntityList.size());
                for(BasicEntity basicEntiy : basicEntityList){
					/*try{*/
                    if(basicEntiy.canInsert()){
                        try{
                            result =  basicEntiy.service().insertBak(basicEntiy);
                        } catch (Exception e) {
                            logger.error("DataStoreHandler saveForDataMigration error - INSERT:"+FastJsonUtils.toJSONString(basicEntiy));
                            throw e;
                        }
                    }else if(basicEntiy.canUpdate()){
                        result = basicEntiy.service().updateBak(basicEntiy);
                        if(result==0){
                            logger.error("saveInBatch saveForDataMigration - UPDATE:"+FastJsonUtils.toJSONString(basicEntiy));
                            throw new RuntimeException("saveForDataMigration saveInBatch - UPDATE:"+FastJsonUtils.toJSONString(basicEntiy));
                        }
                    } else {
                        result = basicEntiy.service().delete(basicEntiy);
                        if(result==0){
                            logger.error("saveInBatch saveForDataMigration - DELETE:"+FastJsonUtils.toJSONString(basicEntiy));
                            throw new RuntimeException("saveForDataMigration saveInBatch - DELETE:"+FastJsonUtils.toJSONString(basicEntiy));
                        }
                    }
                }
            }
        }
        return true;
    }
}
