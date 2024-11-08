package io.geekidea.springboot.assembly.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Slf4j
public class TimeUtil {

	public static final String TIME_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String TIME_FORMAT_YYYY_MM_DD_HH = "yyyy-MM-dd HH:00:00";
	public static final String TIME_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String TIME_FORMAT_HH_MM_SS = "HH:mm:ss";

	public static final DateTimeFormatter SECOND_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);

	private static final ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD);
		}
	};

	private static final ThreadLocal<SimpleDateFormat> hourFormat = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH);
		}
	};

	private static final ThreadLocal<SimpleDateFormat> timeFormat = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
		}
	};

	private static final ThreadLocal<SimpleDateFormat> characterFormat = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(TIME_FORMAT_YYYYMMDDHHMMSS);
		}
	};

	public static String getCurrentDateCharacter() {
		return getStringTime(characterFormat.get());
	}

	/**
	 * 获取当前日期的String类型
	 * @return 例:2015-01-19
	 */
	public static String getCurrentDateString() {
		return getStringTime(dateFormat.get());
	}

	/**
	 * 获取指定时间的小时级格式化数据
	 * @return 例:yyyy-MM-dd HH:00:00
	 */
	public static String getDateHourString(Date date) {
		return hourFormat.get().format(date);
	}

	/**
	 * 获取当前时间的Timestamp类型，精确到小时
	 * @return 例:2015-01-19 10:00:00.0
	 */
	public static Timestamp getCurrentHourTimestamp() {
		return getTimestampTime(hourFormat.get());
	}

	/**
	 * 获取当前时间的String类型，精确到小时
	 * @return 例:2015-01-19 10:00:00
	 */
	public static String getCurrentHourString() {
		return getStringTime(hourFormat.get());
	}

	/**
	 * 获取当前时间的Timestamp类型
	 * @return 例:2015-01-19 10:00:00.0
	 */
	public static Timestamp getCurrentTimeTimestamp() {
		return getTimestampTime(timeFormat.get());
	}

	/**
	 * 获取当前时间的String类型
	 * @return 例:2015-01-19 10:00:00
	 */
	public static String getCurrentTimeString() {
		return getStringTime(timeFormat.get());
	}

	/**
	 * 判断当前时间是否在开始时间和结束时间之间（大于等于看是时间，小于结束时间）<br/>
	 * 如果开始时间为null或空，则只判断是否在结束时间之前（小于结束时间）<br/>
	 * 如果结束时间为null或空，则只判断是否在开始时间之后（大于等于开始时间）
	 * @param beginTime
	 * @param endTime
	 * @return true：是；false：否
	 */
	public static boolean isBetweenBeginTimeAndEndTime(String beginTime, String endTime) {
		if(StringUtils.isEmpty(beginTime)) {
			return isBeforeAppointedTime(endTime);
		}
		if(StringUtils.isEmpty(endTime)) {
			return !isBeforeAppointedTime(beginTime);
		}
		return !isBeforeAppointedTime(beginTime) && isBeforeAppointedTime(endTime);
	}

	/**
	 * 是否在指定的时间之前
	 * @param appointedTime 指定的时间，有格式限制，例如: 2015-01-29 19:52:00
	 * @return true：是 <br/>false：否<br/>例如如果当前时间为2015-01-29 19:00:00，在指定的时间2015-01-29 19:52:00之前，会返回ture
	 */
	public static boolean isBeforeAppointedTime(String appointedTime) {
		if(StringUtils.isEmpty(appointedTime)) {
			return false;
		}
		return getCurrentTimeTimestamp().before(Timestamp.valueOf(appointedTime));
	}


	/**
	 * 是否在指定的时间之后
	 * @param appointedTime 指定的时间，有格式限制，例如: 2015-01-29 19:52:00
	 * @return true：是 <br/>false：否<br/>例如如果当前时间为2015-01-29 19:00:00，在指定的时间2015-01-29 19:52:00之前，会返回ture
	 */
	public static boolean isAfterAppointedTime(String appointedTime) {
		if(StringUtils.isEmpty(appointedTime)) {
			return false;
		}
		return getCurrentTimeTimestamp().after(Timestamp.valueOf(appointedTime));
	}

	/**
	 * 是否是在指定时间的特定小时之前
	 * @param appointedTime 指定的时间
	 * @param specifiedHour 特定的小时
	 * @return true：是在指定时间的特定小时之前；fasle：不是在指定时间的特定小时之前
	 */
	public static boolean isBeforeAppointedBeforeSpecifiedHour(String appointedTime, int specifiedHour) {
		if(StringUtils.isEmpty(appointedTime)) {
			return false;
		}
		try {
			Date date = timeFormat.get().parse(appointedTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR, (specifiedHour > 0) ? -specifiedHour : specifiedHour);
			return isBeforeAppointedTime(timeFormat.get().format(calendar.getTime()));
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 是否是在指定时间的特定小时之后
	 * @param appointedTime 指定的时间
	 * @param specifiedHour 特定的小时
	 * @return  true：在指定时间的特定小时之后；false：不在指定时间的特定小时之后
	 */
	public static boolean isAfterAppointedAfterSpecifiedHour(String appointedTime, int specifiedHour) {
		if(StringUtils.isEmpty(appointedTime)) {
			return false;
		}
		try {
			Date date = timeFormat.get().parse(appointedTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR, (specifiedHour > 0) ? specifiedHour : -specifiedHour);
			return isAfterAppointedTime(timeFormat.get().format(calendar.getTime()));
		} catch (ParseException e) {
			return false;
		}
	}

	private static Timestamp getTimestampTime(SimpleDateFormat sdf) {
		return Timestamp.valueOf(getStringTime(sdf));
	}

	private static String getStringTime(SimpleDateFormat sdf) {
		Date date = new Date();
		return sdf.format(date);
	}

	/**
	 * 获取当天晚上12点的秒数，用于设置缓存过期时间
	 */
	public static long getEndTimeToday() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.HOUR_OF_DAY, 23);
		calendar.set(GregorianCalendar.MINUTE, 59);
		calendar.set(GregorianCalendar.SECOND, 59);
		long timestamp = calendar.getTime().getTime() /1000;
		return timestamp;
	}


	/**
	 * 取时间戳，默认为1S的时间戳。支持1S、10S、100S、1000S
	 * @param time
	 * @return
	 */
	public static String getPreTimeValue(long time) {
		int digit = 3;
		if(time == 10) {
			digit = 4;
		}
		if(time == 100) {
			digit = 5;
		}
		if(time == 1000) {
			digit = 6;
		}
		String tenStr = String.valueOf(System.currentTimeMillis());
		return tenStr.substring(0, tenStr.length() - digit); // 取改时间段内的时间戳
	}

	/**
	 * 删除标准时间格式的最后一个".0"
	 * @param stringTimeWithPointZero
	 * @return
	 */
	public static String removePointZeroFromTime(String stringTimeWithPointZero) {
		if (stringTimeWithPointZero == null) {
			return stringTimeWithPointZero;
		}
		if (stringTimeWithPointZero.indexOf(".0") == stringTimeWithPointZero.length() - 2) {
			return stringTimeWithPointZero.substring(0, stringTimeWithPointZero.length() - 2);
		}
		return stringTimeWithPointZero;
	}

	/**
	 * 是否是在指定时间的特定小时之后（新增的）
	 * @param appointedTime 指定的时间
	 * @param specifiedHour 特定的小时(正数，表示在指定时间之后，负数，表示在指定时间之前)
	 * @return  true：在指定时间的特定小时之后；false：不在指定时间的特定小时之后
	 */
	public static boolean isAfterAppointedAfterSpecifiedHourNew(String appointedTime, int specifiedHour) {
		if(StringUtils.isEmpty(appointedTime)) {
			return false;
		}
		try {
			Date date = timeFormat.get().parse(appointedTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR, (specifiedHour >= 0) ? specifiedHour : specifiedHour);
			return isAfterAppointedTime(timeFormat.get().format(calendar.getTime()));
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 *
	 * Description About isAfterAppointedAfterSpecifiedMinuteNew: <br>
	 * 是否是在指定时间的特定分钟之后（新增的）
	 * @param appointedTime 指定的时间
	 * @param specifiedMinute 特定的分钟(正数，表示在指定时间之后，负数，表示在指定时间之前)
	 * @return true：在指定时间的特定小时之后；false：不在指定时间的特定小时之后
	 */
	public static boolean isAfterAppointedAfterSpecifiedMinuteNew(String appointedTime, int specifiedMinute) {
		if(StringUtils.isEmpty(appointedTime)) {
			return false;
		}
		try {
			Date date = timeFormat.get().parse(appointedTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, (specifiedMinute >= 0) ? specifiedMinute : specifiedMinute);
			return isAfterAppointedTime(timeFormat.get().format(calendar.getTime()));
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 *
	 * Description About getStringTimeFromLongTime: <br>
	 * 将long类型的毫秒数时间，转换为String类型的格式，例如：2015-01-29 19:52:00
	 * @param time 从1970年开始的毫秒数
	 * @return 返回时间的String格式，例如：2015-01-29 19:52:00
	 */
	public static String getStringTimeFromLongTime(long time){
		String result = "";
		try {
			Date date = new Date(time);
			result = timeFormat.get().format(date);
		} catch (Exception e) {
			return "";
		}
		return result;
	}

	/**
	 * 将标准时间加上对应的秒数
	 * @param time
	 * @param second
	 * @return
	 */
	public static String addSecond(String time, int second) {
		if (StringUtils.isEmpty(time) || second == 0) {
			return time;
		}

		SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
		try {
			long timeLong = timeFormat.parse(time).getTime() + second * 1000;
			return timeFormat.format(new Date(timeLong));
		} catch (ParseException e) {
			return null;
		}
	}
	public static Date addHours(String time, int hours) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
			Date sDate = sdf.parse(time);

			Calendar c = Calendar.getInstance();
			c.setTime(sDate);
			c.add(Calendar.HOUR_OF_DAY, hours);
			Date date =  c.getTime();
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
	public static Date getNextDay09clock() {
		try {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.HOUR_OF_DAY, 3);
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
			Date sDate = sdf.parse(new SimpleDateFormat( "yyyy-MM-dd 21:00:00").format(c.getTime()));
			return sDate;
		} catch (ParseException e) {
			return null;
		}
	}
	public static Date getHourTime(Date sDate, int hours) {
		try {
			Calendar ca = Calendar.getInstance();
			ca.setTime(sDate);
			ca.set(Calendar.MINUTE, 0);
			ca.set(Calendar.SECOND, 0);
			ca.add(Calendar.HOUR_OF_DAY, hours);
			return ca.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * getHourTime精确到分秒
	 * @param sDate
	 * @param hours
	 * @return
	 */
	public static Date getExactHourTime(Date sDate, int hours) {
		try {
			Calendar ca = Calendar.getInstance();
			ca.setTime(sDate);
			ca.add(Calendar.HOUR_OF_DAY, hours);
			return ca.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getStringTimeFromDateTime(Date date){
		String result = "";
		try {
			result = timeFormat.get().format(date);
		} catch (Exception e) {
			return "";
		}
		return result;
	}

	/**
	 * 该时间距今多少天
	 * @param date
	 * @return
	 */
	public static int getDateToCurrentTime(Long date){
		Long time = (System.currentTimeMillis() - date) / (1000 * 60 * 60 * 24);
		return time.intValue();
	}

	/**
	 * 获取标准时间对应的毫秒
	 * @param time
	 * @return
	 */
	public static long getTimeMS(String time) {
		if (StringUtils.isEmpty(time)) {
			return 0;
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
		try {
			long timeLong = timeFormat.parse(time).getTime();
			return timeLong;
		} catch (ParseException e) {
			return 0;
		}
	}


	/**
	 * 判断时间顺序
	 * @param specifiedTime
	 * @param appointedTime
	 * @return
	 */
	public static boolean isSpecifiedTimeBeforeAppointedTime(String specifiedTime, String appointedTime) {
		if (StringUtils.isEmpty(specifiedTime) || StringUtils.isEmpty(appointedTime)) {
			return false;
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
		try {
			long startDateTime = timeFormat.parse(specifiedTime).getTime();
			long endDateTime = timeFormat.parse(appointedTime).getTime();
			return endDateTime - startDateTime > 0;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 返回起止时间间隔,格式：HH:mm:ss
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long calculateTimeIntervalFromEndTimeToStartTime(String startTime, String endTime) {
		if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
			return 0L;
		}
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			long startDateTime = simpleFormat.parse(startTime).getTime();
			long endDateTime = simpleFormat.parse(endTime).getTime();
			long interval = endDateTime - startDateTime;
			return interval;
		} catch (ParseException e) {
			return 0L;
		}
	}

	/**
	 * 获取标准时间对应的毫秒,格式：HH:mm:ss
	 * @param time
	 * @return
	 */
	public static long getHourTimeMS(String time) {
		if (StringUtils.isEmpty(time)) {
			return 0;
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT_HH_MM_SS);
		try {
			long timeLong = timeFormat.parse(time).getTime();
			return timeLong;
		} catch (ParseException e) {
			return 0;
		}
	}

	/**
	 * 返回起止时间间隔的天数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int calculateDayIntervalFromStartTimeToEndTime(String startTime, String endTime) {
		if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
			return 0;
		}
		SimpleDateFormat simpleFormat = new SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
		try {
			Date startDate = simpleFormat.parse(startTime);
			Date endDate = simpleFormat.parse(endTime);
			long startDateTime = startDate.getTime();
			long endDateTime = endDate.getTime();
			int days = (int) ((endDateTime - startDateTime) / (1000 * 60 * 60 * 24));
			return days;
		} catch (ParseException e) {
			return 0;
		}

	}

	/**
	 * 返回Date
	 * @param days
	 * @return
	 */
	public static Date getTimeBefore(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, days);

		Date date = calendar.getTime();
		return date;
	}

	public static Date addDay(Date now, int day){
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, day);
		return c.getTime();
	}

	public static String dtFormatConvertHour(String dateStr, String sourcePattern, String targetPattern) {
		try {
			SimpleDateFormat sdfSource = new SimpleDateFormat(sourcePattern);
			SimpleDateFormat sdfTarget = new SimpleDateFormat(targetPattern);
			Calendar c = Calendar.getInstance();
			c.setTime(sdfSource.parse(dateStr));
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			return sdfTarget.format(c.getTime());
		}catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取指定时间加一定分钟后的时间 例："2022-03-01 23:49:09" 偏移"30"分钟后的时间为 "2022-03-02 00:19:09"
	 * @param sDate 指定时间
	 * @param minutes 偏移的分钟数
	 */
	public static Date getTime (Date sDate, int minutes) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sDate);
			cal.add(Calendar.MINUTE, minutes);
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 比较时间
	 * @param s1 yyyy-MM-dd HH:mm:ss
	 * @param s2 yyyy-MM-dd HH:mm:ss
	 * @return 0(s1=s2)  负数(s2>s1) 正数(s2<s1)
	 */
	public static int compareLocalDateTime(String s1,String s2) {
		return LocalDateTime.parse(s1.substring(0,19), SECOND_FORMATTER).compareTo(LocalDateTime.parse(s2.substring(0,19), SECOND_FORMATTER));
	}
}
