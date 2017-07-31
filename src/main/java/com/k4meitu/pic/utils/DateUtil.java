package com.k4meitu.pic.utils;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Date Utility Class. And please use Apache common DateUtils to add, judge
 * equality, round, truncate date.
 * 
 * @author sherlockq
 * @see com.k4meitu.pic.utils.DateUtil.time.DateUtils
 */
public class DateUtil
{

	protected static final Log log = LogFactory.getLog(DateUtil.class);

	/**
	 * FastDateFormat cache map
	 */
	protected static Map<String, FastDateFormat> dateFormatMap = new HashMap<String, FastDateFormat>();

	/**
	 * Date format pattern
	 */
	public final static String FORMAT_DATE_DEFAULT = "yyyy-MM-dd";
	public final static String FORMAT_DATE_YYYYMMDD = "yyyyMMdd";
	public final static String FORMAT_DATE_YYYY_MM_DD = "yyyy-MM-dd";
	public final static String FORMAT_DATE_SLASH_YYYY_MM_DD = "yyyy/MM/dd";
	public final static String FORMAT_DATE_SLASH_YYYY_M_DD = "yyyy/M/dd";
	public final static String FORMAT_DATE_SLASH_YYYY_MM_D = "yyyy/MM/d";
	public final static String FORMAT_DATE_SLASH_YYYY_M_D = "yyyy/M/d";
	public final static String FORMAT_DATE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public final static String FORMAT_DATE_YYMMDD_HHMMSS = "yyMMdd_HHmmssSSS";
	public final static String FORMAT_DATE_YYMMDDHHMMSS = "yyMMddHHmmss";

	/**
	 * DateTime format pattern
	 */
	public final static String FORMAT_DATETIME_DEFAULT = "yyyy-MM-dd HH:mm";
	public final static String FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public final static String FORMAT_DATETIME_YYYY_MM_DD_HHMM = "yyyy-MM-dd HHmm";
	public final static String FORMAT_DATETIME_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public final static String FORMAT_DATETIME_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HHmmss";
	public final static String FORMAT_DATETIME_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public final static String FORMAT_DATETIME_EXIF = "yyyy:MM:dd HH:mm:ss";
	public final static String FORMAT_DATETIME_YYYY_MM_DDHH_MM = "yyyy-MM-ddHH:mm";
	public final static String FORMAT_DATETIME_US = "EEE MMM dd HH:mm:ss 'CST' yyyy";
	
	/**
	 * Time format pattern
	 */
	public final static String FORMAT_TIME_DEFAULT = "HH:mm";
	public final static String FORMAT_TIME_HH_MM = "HH:mm";
	public final static String FORMAT_TIME_HHMM = "HHmm";
	public final static String FORMAT_TIME_HH_MM_SS = "HH:mm:ss";
	public final static String FORMAT_TIME_HHMMSS = "HHmmss";

	/**
	 * Returns FastDateFormat according to given pattern which is cached.<br/>
	 * This method is not public due to that the FastDateFormat instance may be
	 * altered by outside.
	 * 
	 * @param formatPattern date format pattern string
	 * @return Cached FastDateFormat instance which should not be altered in any
	 * way.
	 */
	protected static Format getCachedDateFormat(String formatPattern)
	{
		// Due to simpledateformat's non thread-local, return a new pattern
		// every time.
		FastDateFormat dateFormat = dateFormatMap.get(formatPattern);

		if (dateFormat == null)
		{

			dateFormat = FastDateFormat.getInstance(formatPattern);
			dateFormatMap.put(formatPattern, dateFormat);

		}

		return dateFormat;

	}

	/**
	 * 日期转换成时间
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToStr(Date date, String formatStr)
	{
		String str = "";
		SimpleDateFormat format = null;
		if (formatStr == null || formatStr.equals(""))
		{
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		else
		{
			format = new SimpleDateFormat(formatStr);
		}
		try
		{
			str = format.format(date);
		}
		catch (Exception e)
		{
			System.out.print(e.toString());
		}
		return str;
	}

	/**
	 * Format the date according to the pattern.
	 * 
	 * @param date Date to format. If it's null, the result will be null.
	 * @param formatPattern Date format pattern string. You can find common ones
	 * in DateUtils class.
	 * @return Formatted date in String
	 * @see DateUtil
	 */
	public static String formatDate(Date date, String formatPattern)
	{
		if (date == null)
		{
			return null;
		}
		return getCachedDateFormat(formatPattern).format(date);
	}

	/**
	 * Format the date in default pattern: yyyy-MM-dd.
	 * 
	 * @param date Date to format. If it's null, the result will be null.
	 * @return Formatted date in String
	 * @see DateUtil#FORMAT_DATE_DEFAULT
	 * @see DateUtil#formatDate(Date, String)
	 */
	public static String formatDate(Date date)
	{
		return formatDate(date, FORMAT_DATE_DEFAULT);
	}

	/**
	 * Format the date in default date-time pattern: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date Date to format. If it's null, the result will be null.
	 * @return Formatted date-time in String
	 * @see DateUtil#FORMAT_DATETIME_DEFAULT
	 * @see DateUtil#formatDate(Date, String)
	 */
	public static String formatDateTime(Date date)
	{
		return formatDate(date, FORMAT_DATETIME_DEFAULT);
	}

	/**
	 * Format the date in default time pattern: HH:mm:ss
	 * 
	 * @param date Date to format. If it's null, the result will be null.
	 * @return Formatted time in String
	 * @see DateUtil#FORMAT_TIME_DEFAULT
	 * @see DateUtil#formatDate(Date, String)
	 */
	public static String formatTime(Date date)
	{
		return formatDate(date, FORMAT_TIME_DEFAULT);
	}

	/**
	 * Same as javascript library's Wkz.date.formatTimeRange logic.
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static String formatTimeRange(Date start, Date end)
	{
		if (start == null) return null;
		if (end == null) return formatDateTime(start);
		if (DateUtils.isSameDay(start, end))
		{
			// same day
			return formatDateTime(start) + '~' + formatTime(end);
		}
		else
		{
			// not same day
			return formatDateTime(start) + '~' + formatDateTime(end);
		}
	}

	/**
	 * Returns current system date.
	 * 
	 * @return current system date.
	 * @see Date#Date()
	 */
	public static Date getCurrentDate()
	{
		return new Date();
	}
	/**
	 * 获取当前时间
	 * @param formatPattern
	 * @return
	 */
	public static String getCurrentDate(String formatPattern)
	{
		return formatDate(getCurrentDate(), formatPattern);
	}

	/**
	 * Parse string value to Date by given format pattern. If parse failed, null
	 * would be returned.
	 * 
	 * @param stringValue date value as string.
	 * @param formatPattern format pattern.
	 * @return Date represents stringValue, null while parse exception occurred.
	 */
	public static Date parse(String stringValue, String formatPattern)
	{

		try
		{
			return new SimpleDateFormat(formatPattern).parse(stringValue);
		}
		catch (ParseException e)
		{
			log.warn("parse error:" + e.toString());
			return null;
		}

	}
	/**
	 * 转换时间对象  匹配格式 yyyy-MM-dd; yyyy-MM-dd HH:mm; yyyy-MM-dd HH:mm:ss; EEE MMM dd HH:mm:ss 'CST' yyyy;
	 * @param stringValue
	 * @return
	 */
	public static Date parse(String stringValue)
	{
		if (stringValue == null || stringValue.length() == 0) return null;
		
		Date date = null;
		SimpleDateFormat format = null;
		
		if (stringValue.length() == FORMAT_DATE_YYYY_MM_DD.length())
		{
			format = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);
		}
		else if (stringValue.length() == FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS.length())
		{
			format = new SimpleDateFormat(FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);
		}
		else if (stringValue.length() == FORMAT_DATETIME_YYYY_MM_DD_HH_MM.length())
		{
			format = new SimpleDateFormat(FORMAT_DATETIME_YYYY_MM_DD_HH_MM);
		}
		else if (stringValue.length() == FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS_SSS.length())
		{
			format = new SimpleDateFormat(FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS_SSS);
		}
		else if (stringValue.length() == FORMAT_DATETIME_US.length())
		{
			format = new SimpleDateFormat(FORMAT_DATETIME_US, Locale.US);
		}
		else
		{
			format = new SimpleDateFormat(FORMAT_DATE_DEFAULT);
		}
			
		try
		{
			date = format.parse(stringValue);
		}
		catch (ParseException e)
		{
		}

		return date;
	}
	
	/**
	 * 转换时间对象  匹配格式 yyyyMMdd;yyyyMMddHHmmss
	 * @param stringValue
	 * @return
	 */
	public static Date parseNoSeparator(String stringValue)
	{
		if (stringValue == null || stringValue.length() == 0) return null;
		
		Date date = null;
		SimpleDateFormat format = null;
		
		if (stringValue.length() == FORMAT_DATE_YYYYMMDD.length())
		{
			format = new SimpleDateFormat(FORMAT_DATE_YYYYMMDD);
		}
		else if (stringValue.length() == FORMAT_DATE_YYYYMMDDHHMMSS.length())
		{
			format = new SimpleDateFormat(FORMAT_DATE_YYYYMMDDHHMMSS);
		}
		else
		{
			format = new SimpleDateFormat(FORMAT_DATE_YYYYMMDD);
		}
			
		try
		{
			date = format.parse(stringValue);
		}
		catch (ParseException e)
		{
		}

		return date;
	}

	/**
	 * Parse string value to Date by given format pattern. If string value is
	 * null or parse failed, null would be returned.
	 */
	public static Date parseAvoidNull(String stringValue, String formatPattern) throws ParseException
	{
		if (stringValue == null || stringValue.trim().equals(""))
		{
			return null;
		}

		int index = stringValue.indexOf("-");
		if (index > 0)
		{
			String str = stringValue.substring(0, index);
			if (str.length() == 2)
			{
				stringValue = "20" + stringValue;
			}
		}

		return new SimpleDateFormat(formatPattern).parse(stringValue);
	}

	/**
	 * Parses given Date to a Timestamp instance with same date in milliseconds
	 * precision.
	 * 
	 * @param date Date to parse.
	 * @return Timestamp in milliseconds precision
	 */
	public static Timestamp parseTimestamp(Date date)
	{
		return new Timestamp(date.getTime());
	}

	/**
	 * Compares two dates based on the specified calendar field, and ignores
	 * those fields more trivial. Neither date could be null.<br/>
	 * For example, if calendarField is Calendar.Month, then result will be 0 if
	 * 2008-8-2 and 2008-8-10 is compared. But the result will be -1 if
	 * 
	 * @param date1
	 * @param date2
	 * @param calenderField
	 * @return date1 < date2 : <0<br/>
	 * date1 = date2 : 0<br/>
	 * date1 > date2 : >0
	 * @throws IllegalArgumentException If either date is null.
	 * @see Calendar
	 */
	public static int compareDateToField(final Date date1, final Date date2, int calendarField)
	{
		if (date1 == null || date2 == null)
		{
			throw new IllegalArgumentException("Neither date could be null");
		}
		Date truncatedDate1 = DateUtils.truncate(date1, calendarField);
		Date truncatedDate2 = DateUtils.truncate(date2, calendarField);
		return truncatedDate1.compareTo(truncatedDate2);

	}
	
	/**
	 * 获取与系统时间相差的秒数
	 * 
	 * @param date
	 * @return 日期为空 返回-1
	 */
	public static int compareDateToSeconds(final Date date)
	{
		if (date == null) return -1;
		long begin = date.getTime();
		long end = System.currentTimeMillis();
		int days = (int) ((end - begin) / 1000);
		return days;
	}
	/**
	 * 获取与系统时间相差的秒数
	 * 
	 * @param date
	 * @return 日期为空 返回-1
	 */
	public static int compareDateToSeconds(final String date)
	{
		return compareDateToSeconds(parse(date));
	}
	/**
	 * 获取与系统时间相差的秒数
	 * 
	 * @param date
	 * @return 日期为空 返回-1
	 */
	public static int compareDateToSeconds(final String date,final String format)
	{
		return compareDateToSeconds(parse(date, format));
	}

	/**
	 * 获取两日期相差天数
	 * 
	 * @param date1
	 * @param date2
	 * @return 日期为空 返回0 否则返回正负整数
	 */
	public static int compareDaysBetween(final Date date1, final Date date2)
	{
		if (date1 == null || date2 == null) return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long begin = calendar.getTimeInMillis();
		calendar.setTime(date2);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long end = calendar.getTimeInMillis();
		int days = (int) ((begin - end) / 1000 / 3600 / 24);
		return days;
	}

	/**
	 * 系统常用java日期格式转化为extjs对应的日期格式
	 * 
	 * @param java2FastDateFormat
	 * @return
	 */
	public static String extjsFastDateFormat(String java2FastDateFormat)
	{
		Map<String, String> extFastDateFormatMap = new HashMap<String, String>();
		extFastDateFormatMap.put(DateUtil.FORMAT_DATE_DEFAULT, "Y-m-d");
		extFastDateFormatMap.put(DateUtil.FORMAT_DATE_YYYYMMDD, "Ymd");
		extFastDateFormatMap.put(DateUtil.FORMAT_DATE_SLASH_YYYY_M_D, "Y/m/d");
		extFastDateFormatMap.put(DateUtil.FORMAT_DATETIME_DEFAULT, "Y-m-d H:i:s");
		extFastDateFormatMap.put(DateUtil.FORMAT_DATETIME_YYYYMMDDHHMMSS, "YmdHis");
		return extFastDateFormatMap.get(java2FastDateFormat);
	}

	public static Date append(Date start, Integer appendDays)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		calendar.add(Calendar.DAY_OF_YEAR, appendDays);

		return calendar.getTime();
	}
	
	public static String getTime()
	{
		SimpleDateFormat fmt = new SimpleDateFormat(FORMAT_TIME_HH_MM_SS);
		return fmt.format(new Date());
	}
	
	public static String getDate()
	{
		return getDate(new Date());
	}
	public static String getDate(Date date)
	{
		SimpleDateFormat fmt = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);
		return fmt.format(date);
	}
	public static String getDateTime()
	{
		return getDateTime(new Date());
	}
	public static String getDateTime(Date date)
	{
		SimpleDateFormat fmt = new SimpleDateFormat(FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);
		return fmt.format(date);
	}
	public static String getDateByFileName()
	{
		SimpleDateFormat fmt = new SimpleDateFormat(FORMAT_DATE_YYYYMMDD);
		return fmt.format(new Date());
	}
	public static Timestamp getNowTimeStamp(){
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date());//将时间格式转换成符合Timestamp要求的格式.
		Timestamp timestamp =Timestamp.valueOf(nowTime);//把时间转换
		return timestamp;
	}
}
