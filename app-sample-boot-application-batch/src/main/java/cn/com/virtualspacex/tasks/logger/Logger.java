package cn.com.virtualspacex.tasks.logger;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.spi.LoggerFactory;

import cn.com.virtualspacex.constants.Constant;

import com.virtualspacex.util.io.file.FileUtil;
import com.virtualspacex.util.property.XmlPropertiesReader;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

/**
 * Logger类
 *
 * @author yao-chaochao
 * @version 1.0
 * @date 2020/09/15
 */
public class Logger {
    /**
     * log4j的log对象
     */
    private org.apache.log4j.Logger logger;

    /**
     * properties读取类对象
     */
    private static XmlPropertiesReader propertiesReader;

    /**
     * 自身
     */
    private static Logger self;

    /**
     * Retrieve the appropriate {@link org.apache.log4j.Logger} instance.
     */
    public static Logger getLogger(String name) {
        if (null == self) {
            self = new Logger();
        }
        self.logger = LogManager.getLogger(name);
        return self;
    }

    /**
     * Retrieve the appropriate {@link org.apache.log4j.Logger} instance.
     */
    public static Logger getLogger(Class<?> clazz) {
        if (null == self) {
            self = new Logger();
        }
        self.logger = LogManager.getLogger(clazz.getName());
        return self;
    }

    /**
     * Retrieve the appropriate root logger.
     */
    public static Logger getRootLogger() {
        if (null == self) {
            self = new Logger();
        }
        self.logger = LogManager.getRootLogger();
        return self;
    }

    /**
     * Retrieve the appropriate {@link org.apache.log4j.Logger} instance.
     */
    public static Logger getLogger(String name, LoggerFactory factory) {
        if (null == self) {
            self = new Logger();
        }
        self.logger = LogManager.getLogger(name, factory);
        return self;
    }

    /**
     * 打印业务log
     *
     * @param messageId     业务log的消息id
     * @param parameterList 业务log的参数集合
     */
    public void msg(String messageId, List<String> parameterList) {
        if (propertiesReader == null ) {
            propertiesReader = new XmlPropertiesReader();
            String classPath = FileUtil.getClassPath();
            try {
                String filePath = classPath + File.separator + "config" + File.separator + Constant.MESSAGE_FILE_PATH;
                boolean isLoaded = propertiesReader.loadPropertyFile(filePath);
                if (!isLoaded) {
                    msg(filePath + Constant.NOT_FIND_FILE);
                }
            } catch (Throwable e) {
                msg(e.toString());
            }
        }
        try {
            String messageTemplate = propertiesReader.getProperty(messageId);
            String messageFullContent = messageTemplate;
            if (parameterList != null) {
                String[] params = new String[parameterList.size()];
                messageFullContent = MessageFormat.format(messageTemplate, parameterList.toArray(params));
            }
            info(messageId + "," + messageFullContent);
        } catch (Throwable e) {
            msg(e.toString());
        }
    }

    /**
     * 打印业务log
     *
     * @param message 业务log
     */
    public void msg(String message) {
        debug("," + message);
    }

    /**
     * info
     *
     * @param message the message object to log
     * @see org.apache.log4j.Logger#info(Object)
     */
    private void info(Object message) {
        logger.info("," + Constant.FUNCTION_SYMBOL + "," + Constant.TASK_ID + "," + message);
    }

    /**
     * Log a message object with the {@link Level#DEBUG DEBUG} level.
     *
     * <p>This method first checks if this category is <code>DEBUG</code>
     * enabled by comparing the level of this category with the {@link
     * Level#DEBUG DEBUG} level. If this category is
     * <code>DEBUG</code> enabled, then it converts the message object
     * (passed as parameter) to a string by invoking the appropriate
     * {@link org.apache.log4j.or.ObjectRenderer}. It then proceeds to call all the
     * registered appenders in this category and also higher in the
     * hierarchy depending on the value of the additivity flag.
     *
     * <p><b>WARNING</b> Note that passing a {@link Throwable} to this
     * method will print the name of the <code>Throwable</code> but no
     * stack trace. To print a stack trace use the {@link #debug(Object,
     * Throwable)} form instead.
     *
     * @param message the message object to log.
     */
    private void debug(Object message) {
        logger.debug("," + Constant.FUNCTION_SYMBOL + "," + Constant.TASK_ID + "," + message);
    }
}
