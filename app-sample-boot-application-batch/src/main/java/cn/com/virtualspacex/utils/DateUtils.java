package cn.com.virtualspacex.utils;



import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * 
 * 类描述：时间操作定义类
 */
public class DateUtils extends PropertyEditorSupport {


	public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


	/**
	 *
	 * @return 以当地时区表示的系统当前日历
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 默认方式表示的系统当前日期，具体格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 默认日期按“yyyy-MM-dd HH:mm:ss“格式显示
	 */
	public static String formatDateTime() {
		return datetimeFormat.format(getCalendar().getTime());
	}



}