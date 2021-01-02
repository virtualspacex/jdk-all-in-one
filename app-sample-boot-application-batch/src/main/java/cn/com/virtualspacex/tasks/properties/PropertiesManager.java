package cn.com.virtualspacex.tasks.properties;

import org.hibernate.Session;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.dao.SystemBatchSetparamDao;
import cn.com.virtualspacex.model.SystemBatchSetparam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.virtualspacex.component.hibernate.HibernateUtils;

/**
 * @author zeng-zhuanghu
 * @date 2020/10/19
 */
public class PropertiesManager {
	
    /**
     * 配置文件的FLAGFILE_DIR的值
     */
    public static String FLAGFILE_DIR="./";

    /**
     * 配置文件的FLAGFILE_NAME的值
     */
    public static String FLAGFILE_NAME="proc9111.csv";

    /**
     * 配置文件的RETRY_TIME的值
     */
    public static String RETRY_TIME="3";

    /**
     * 配置文件的RETRY_SECONDS的值
     */
    public static String RETRY_SECONDS="60";

    /**
     * 配置文件的LOGFILE_DIR的值
     */
    public static String LOGFILE_DIR="./";
    /**
     * 配置文件的ONLINESALEINFOS_TABLENAME的值
     */
    public static String ONLINESALEINFOS_TABLENAME="onlinesaleinfos";

    /**
     * 配置文件的SALE_SERIAL_MISSINGNUMBERS_TABLENAME的值
     */
    public static String SALE_SERIAL_MISSED_TABLENAME="sale_serial_missed";

    /**
     * 配置文件的SALE_SERIAL_END_TABLENAME的值
     */
    public static String SALE_SERIAL_END_TABLENAME="sale_serial_end";

    /**
     * 配置文件的 login的值
     */
    public static String LOGIN_URL;

    /**
     * 配置文件的logout的值
     */
    public static String LOGOUT_URL;

    /**
     * 配置文件的fesalesinforeq_url的值
     */
    public static String FESALESINFOREQ_URL;


    public static String LOGIN_ID;

    public static String LOGIN_PASSWORD;
    
    public static int MAX_THREADS = 2000;

    private static ArrayList<String> msgList = new ArrayList<>();

    /**
     * 读取配置信息
     */
    public boolean loadProperties() {
        Session session;
		try {
			session = (Session) HibernateUtils.openSession();
		} catch (Exception e1) {
			return false;
		}
		
        boolean result = true;
        try {
            SystemBatchSetparamDao systemBatchSetparamDao = new SystemBatchSetparamDao();
            List<SystemBatchSetparam> systemBatchSetparamBOS = systemBatchSetparamDao.get(session);
            Map<String, String> batchParamsMap = new HashMap<String, String>();

            for (SystemBatchSetparam s : systemBatchSetparamBOS) {
            	batchParamsMap.put(s.getParamId(), s.getParamValue());
            }
            
            if (checkValue(batchParamsMap.get(Constant.MAX_THREADS))) {
            	try {
            		MAX_THREADS = Integer.valueOf(batchParamsMap.get(Constant.MAX_THREADS));
            	} catch(Exception e) {
            		//do nothing
            	}
            }

            if (checkValue(batchParamsMap.get(Constant.FLAGFILE_DIR))) {
                FLAGFILE_DIR = batchParamsMap.get(Constant.FLAGFILE_DIR);
            }

            if (checkValue(batchParamsMap.get(Constant.FLAGFILE_NAME))) {
                FLAGFILE_NAME = batchParamsMap.get(Constant.FLAGFILE_NAME);
            }

            if (checkValue(batchParamsMap.get(Constant.RETRY_TIME))) {
                RETRY_TIME = batchParamsMap.get(Constant.RETRY_TIME);
            }

            if (checkValue(batchParamsMap.get(Constant.RETRY_SECONDS))) {
                RETRY_SECONDS = batchParamsMap.get(Constant.RETRY_SECONDS);
            }

            if (checkValue(batchParamsMap.get(Constant.LOGFILE_DIR))) {
                LOGFILE_DIR = batchParamsMap.get(Constant.LOGFILE_DIR);
            }
            if (checkValue(batchParamsMap.get(Constant.ONLINESALEINFOS_TABLENAME))) {
                ONLINESALEINFOS_TABLENAME = batchParamsMap.get(Constant.ONLINESALEINFOS_TABLENAME);
            }
            if (checkValue(batchParamsMap.get(Constant.SALE_SERIAL_MISSED_TABLENAME))) {
                SALE_SERIAL_MISSED_TABLENAME = batchParamsMap.get(Constant.SALE_SERIAL_MISSED_TABLENAME);
            }
            if (checkValue(batchParamsMap.get(Constant.SALE_SERIAL_END_TABLENAME))) {
                SALE_SERIAL_END_TABLENAME = batchParamsMap.get(Constant.SALE_SERIAL_END_TABLENAME);
            }
            if (checkValue(batchParamsMap.get(Constant.LOGIN_URL))) {
                LOGIN_URL = batchParamsMap.get(Constant.LOGIN_URL);
            }else {
                msgList.add(Constant.LOGIN_URL_IS_NULL);
                result=false;
            }

            if (checkValue(batchParamsMap.get(Constant.LOGOUT_URL))) {
                LOGOUT_URL = batchParamsMap.get(Constant.LOGOUT_URL);
            }else {
                msgList.add(Constant.LOGOUT_URL_IS_NULL);
                result=false;
            }

            if (checkValue(batchParamsMap.get(Constant.FESALESINFOREQ_URL))) {
                FESALESINFOREQ_URL = batchParamsMap.get(Constant.FESALESINFOREQ_URL);
            } else {
                msgList.add(Constant.FESALESINFOREQ_URL_IS_NULL);
                result=false;
            }

            if (checkValue(batchParamsMap.get(Constant.LOGIN_ID))) {
                LOGIN_ID = batchParamsMap.get(Constant.LOGIN_ID);
            }else {
                msgList.add(Constant.LOGIN_ID_IS_NULL);
                result=false;
            }

            if (checkValue(batchParamsMap.get(Constant.LOGIN_PASSWORD))) {
                LOGIN_PASSWORD = batchParamsMap.get(Constant.LOGIN_PASSWORD);
            }else {
                msgList.add(Constant.LOGIN_PASSWORD_IS_NULL);
                result=false;
            }

            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
        	try {
				HibernateUtils.closeSession(session);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        return result;
    }

    private boolean checkValue(String value) {
        if (null == value || value.length() == 0) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getMsgList() {
        return msgList;
    }
}
