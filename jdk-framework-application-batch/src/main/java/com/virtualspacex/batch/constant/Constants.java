/*
 * @Author: your name
 * @Date: 2020-10-10 13:29:14
 * @LastEditTime: 2020-10-11 13:17:10
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/constant/Constants.java
 */
package com.virtualspacex.batch.constant;

/**
 * 定数
 *
 * @version 0.0.1
 * @since JDK8
 */
public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * プロジェクトのバージョン
     */
    public static final String VERSION = "0.2.2";
    public static final String CONTAINER_VERSION = "the batch container version is ";
    public static final String BATCH_VERSION = "the batch version is ";
    public static final String BATCH_NAME = "the batch name is ";
    public static final String BATCH_ID = "the batch id is ";

    public static final String EMPTY_STRING = "";
    public static final String POINT_STRING = ".";

    public static final String ENCODING_UTF8 = "UTF-8";
    public static final String ENCODING_SHIFTJIS = "SHIFT-JIS";

    public static final String RESULT_ERROR = "-1";

    public static final String PROPERTIES = "properties";
    public static final String XML = "xml";

    public static final String CONFIG_FILE_PATH = "config";

    public static final String TASK_ID = "taskId";
    public static final String TASK_NAME = "taskName";
    
    public static final String HIBERNATE_CONFIG_FILE = "hibernate.cfg.xml";

}
