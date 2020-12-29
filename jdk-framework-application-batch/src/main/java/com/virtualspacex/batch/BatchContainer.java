package com.virtualspacex.batch;

import com.virtualspacex.batch.configuration.*;
import com.virtualspacex.batch.constant.Constants;
import com.virtualspacex.batch.event.BAT;
import com.virtualspacex.batch.job.AbstractJob;
import com.virtualspacex.middleware.listener.EventCenter;
import com.virtualspacex.middleware.loader.AdvancedInstanceCreator;
import com.virtualspacex.util.common.CommonUtil;
// import com.virtualspacex.util.database.DatabaseUtilFactory;
import com.virtualspacex.util.logger.LoggerFactory;

/**
 * プログラムエントリークラス
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @date 2020/08/26
 * @since JDK8
 */
public class BatchContainer {

	private static Class<? extends BatchConfigurationInterface> batchConfigClass = null;
    
	private BatchConfigurationInterface batchConfig = new DefaultBatchConfiguration();
	
	private String containerVersion = Constants.VERSION;
    
    /**
     * コンストラクター、ログ構成を初期化、キャッチされない例外処理クラスを宣言
     *
     * @since 0.0.1
     */
    public BatchContainer() {

    }
    
    public static void config (Class<? extends BatchConfigurationInterface> clazz) {
    	batchConfigClass = clazz;
    }

    /**
     * システム実行中
     *
     * @param clazz 実行するクラス
     * @since 0.0.1
     */
    public void run(Class<? extends AbstractJob> clazz) {
    	
    	//加载应用程序及配置文件
    	
    	AbstractJob task = null;
    	
    	try {
    		task = (AbstractJob) AdvancedInstanceCreator.from(clazz);
    		
    		if (null == task) 
    			throw new Exception();
    		
    		if(null != batchConfigClass) 
    			batchConfig = batchConfigClass.newInstance();
    		
    	} catch (Exception e) {
    		LoggerFactory.getLogger().error(CommonUtil.eToStackString(e));
			LoggerFactory.getLogger().error("Failed to load " + clazz.getSimpleName());
			return;
    	}
    	
    	//打印版本信息
    	printBasicInfo();
    	
    	//配置程序运行环境
    	try {
	    	doConfig();
    	} catch (Exception e) {
    		LoggerFactory.getLogger().error(CommonUtil.eToStackString(e));
			LoggerFactory.getLogger().error("Failed to config " + clazz.getSimpleName());
			return;
    	}
    	
    	//自检
    	if(!doCheck()) {
    		LoggerFactory.getLogger().error("Failed to Check " + clazz.getSimpleName());
    		return;
    	} 
    	
    	try {
    		
    		//发送启动事件
	    	EventCenter.emit(BAT.STARTUP);
	    	
	    	
        	if(doBeforeFilter()) {
        		try {
        			task.execute();
        		} finally {
        			doAfterFilter();
        		}
        	}
		} catch (Throwable e) {
			LoggerFactory.getLogger().error(CommonUtil.eToStackString(e));
			return;
		} finally {
			//发送结束事件
			EventCenter.emit(BAT.SHUTDOWN);
		}
    }
    
    private boolean doCheck() {
    	return batchConfig.getChecker().check();
    }
    
    private void doConfig() throws Exception {
    	EventCenter.register(BAT.STARTUP, batchConfig.getStartupListener());
    	EventCenter.register(BAT.SHUTDOWN, batchConfig.getShutdownListener());
    	Thread.setDefaultUncaughtExceptionHandler(batchConfig.getUncaughtExceptionHandler());
    	LoggerFactory.register(batchConfig.getLogger());
    	// DatabaseUtilFactory.register(batchConfig.getDatabaseUtil());
    }
    
    private boolean doBeforeFilter() {
    	return batchConfig.getBeforeFilter().doFilter();
    }
    
    private boolean doAfterFilter() {
    	return batchConfig.getAfterFilter().doFilter();
    }
    
    private void printBasicInfo() {
    	Metadata matadata = batchConfig.getMeta();
    	LoggerFactory.getLogger().info(Constants.CONTAINER_VERSION + containerVersion);
    	LoggerFactory.getLogger().info(Constants.BATCH_VERSION + matadata.version());
    	LoggerFactory.getLogger().info(Constants.BATCH_NAME + matadata.name());
    	LoggerFactory.getLogger().info(Constants.BATCH_ID + matadata.id());
    }
}


