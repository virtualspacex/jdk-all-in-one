package cn.com.virtualspacex.config;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.apache.log4j.PropertyConfigurator;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.tasks.logger.Logger;
import cn.com.virtualspacex.tasks.properties.PropertiesManager;

import com.virtualspacex.batch.checker.Checker;
import com.virtualspacex.batch.configuration.DefaultBatchConfiguration;
import com.virtualspacex.batch.configuration.Metadata;
import com.virtualspacex.batch.filter.AfterFilter;
import com.virtualspacex.batch.filter.BeforeFilter;
import com.virtualspacex.middleware.handler.EventHandlerInterface;
import com.virtualspacex.util.io.file.FileUtil;

public class BatchConfiguration extends DefaultBatchConfiguration{

	String flagFileDir = "";
	
	@Override 
	public Metadata getMeta() {
		return new Metadata("0.0.1", "batch name", "9999");
	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public DatabaseUtilInterface<?, ?> getDatabaseUtil() throws Exception{
//		
//    	String hibernateConfigFile = FileUtil.getClassPath()
//        		+ File.separator 
//        		+ Constants.CONFIG_FILE_PATH
//        		+ File.separator 
//        		+ Constants.HIBERNATE_CONFIG_FILE;
//    	
//		return new HibernateUtils(hibernateConfigFile);
//	}
	
	@Override
	public Checker getChecker() {
		return () -> {
	        boolean ret = false;

	        PropertiesManager propertiesManager = new PropertiesManager();
	        ret = propertiesManager.loadProperties();

	        // 设置log文件名的日期，重新加载log配置文件
	        String businessDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
	        System.setProperty(Constant.LOG_NAME, businessDate);
	        System.setProperty(Constant.LOGFILE_DIR, PropertiesManager.LOGFILE_DIR);
	        String logConfigFile = FileUtil.getClassPath() + File.separator + "config" + File.separator + "log4j.properties";
	        PropertyConfigurator.configure(logConfigFile);

	        // 取得处理中标志文件路径
	        flagFileDir = PropertiesManager.FLAGFILE_DIR + Constant.TASK_ID + Constant.UNDERLINE_STRING + PropertiesManager.FLAGFILE_NAME;
	        
	        if (!ret) {
	            if (PropertiesManager.getMsgList().size() > 0) {
	                ArrayList<String> msg = PropertiesManager.getMsgList();
	                for (String s : msg) {
	                    Logger.getLogger(Constant.LOGGER_TASK).msg(s);
	                }
	            }
	        }
	        return ret;
		};
	}
	
	@Override
	public EventHandlerInterface getStartupListener() {
		return ()-> {
	        // 系统启动日志输出
	        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00001, Collections.singletonList(Constant.TASK_NAME));
		};
	}
	
	@Override
	public EventHandlerInterface getShutdownListener() {
		return ()-> {
	        // 系统结束的日志输出
	        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00002, Collections.singletonList(Constant.TASK_NAME));
		};
	}
	
	@Override
	public BeforeFilter getBeforeFilter() {
		return ()-> {
			
	        boolean commonResult = false;

	        FileUtil FileUtil = new FileUtil();

	        // 判断标志文件是否存在
	        boolean isExist = FileUtil.isExist(flagFileDir);

	        // 标志文件是否存在的确认
	        if (isExist) {
	            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_E_00012, null);
	            return commonResult;
	        }

	        try {
	        	commonResult = FileUtil.create(flagFileDir);
	        } catch (IOException e) {
	            Logger.getLogger(Constant.LOGGER_CONTAINER).msg(Constant.MSG_PROC_FLAG_CREATED);
	        }

	        // 创建标志文件是否成功的确认
	        if (commonResult) {
	            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MSG_PROC_FLAG_CREATED);
	        } else {
	            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.STRING_FILE + flagFileDir + Constant.CREATE_ERROR);
	        }
	        
	        return commonResult;
		};
	}
	
	@Override
	public AfterFilter getAfterFilter() {
		return ()-> {
	        // 删除标志文件
	        boolean deleteRet = new FileUtil().delete(flagFileDir);

	        // 删除标志文件是否成功的日志输出
	        if (deleteRet) {
	            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MSG_PROC_FLAG_DELETED);
	        } else {
	            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.STRING_FILE + flagFileDir + Constant.DELETE_ERROR);
	        }
	        
	        return deleteRet;
		};
	}
	
}
