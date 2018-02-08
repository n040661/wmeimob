package com.wmeimob.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 常用的时间操作方法
 *
 * @author Administrator
 *
 */
public class DateUtil {
    public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**当前系统时间 */
	public static Date getCurrDate() {
		return new Date();
	}
	
	/**获取当天开始时间 */
	public static Date getCurrBeginDate() {
		Calendar currentDate = new GregorianCalendar();   
		currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		currentDate.set(Calendar.MINUTE, 0);  
		currentDate.set(Calendar.SECOND, 0);  
		return (Date)currentDate.getTime().clone();
	}
	
	/**获取当天结束时间 */
	public static Date getCurrEndDate() {
		Calendar currentDate = new GregorianCalendar();   
		currentDate.set(Calendar.HOUR_OF_DAY, 23);  
		currentDate.set(Calendar.MINUTE, 59);  
		currentDate.set(Calendar.SECOND, 59);  
		return (Date)currentDate.getTime().clone();
	}
	
	

	 /** 获取当前日期前一天 */
	public static Date getPreDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date date = calendar.getTime();
		return date;
	}

	 /** 获取当前日期前n天,n为负数 */
	public static Date getPreDay(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, n); // 得到前一天
		Date date = calendar.getTime();
		return date;
	}

    /**
     * 获取指定日期前n个月，n为负数
     * @param date
     * @param n
     * @return
     */
    public static Date getPreMonth(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, n);
        return c.getTime();
    }

    /**  获取当前日期后一天  */
	public static Date getNextDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1); // 得到后一天
		Date date = calendar.getTime();
		return date;
	}

	/**  日期转成字符串,指定日期格式  */
	public static String dateToStr(Date date, String format) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		} else {
			return null;
		}
	}

	/**  日期转成字符串,返回默认的yyyy-MM-dd HH:mm:ss格式  */
	public static String dateToStr(Date date) {
		if (date != null) {
			return dateToStr(date, DEFAULT_FORMAT);
		} else {
			return null;
		}
	}

	/**  根据指定格式返回当前日期  */
	public static Date getCurrDate(String formatStr) throws ParseException {
		Date now = new Date();
		DateFormat sdf = new SimpleDateFormat(formatStr);
		now = strToDate(sdf.format(now), formatStr);
		return now;
	}

	/** 字符串转换到时间格式*/
	public static Date strToDate(String dateStr, String formatStr) {
		if (null == dateStr || "".equals(dateStr))
			return null;
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
	}

	/**
	 * 返回当月第一天的日期
	 */
	public static Date firstDay(Date date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 返回当月最后一天的日期
	 */
	public static Date lastDay(Date date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}

	/**
	 * 获取日期d的days天后的一个Date
	 *
	 * @param d
	 * @param days
	 * @return
	 */
	public static Date getInternalDateByDay(Date d, int days) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.DATE, days);
		return now.getTime();
	}

	/**
	 * 获取日期d的months个月后的一个Date
	 *
	 * @param
	 * @return
	 */
	public static Date getInternalDateByMon(Date d, int months) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.MONTH, months);
		return now.getTime();
	}
    /**
     * 获取日期d的months个月后的一个集合
     *
     * @param
     * @return
     */
    public static List<String> getInternalDateByMonList(Date d, int months) {
        List<String> list=new ArrayList<String>();
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        now.add(Calendar.MONTH, months);
        //return now.getTime();
        Calendar c_begin = new GregorianCalendar();
        Calendar c_end = new GregorianCalendar();
        c_begin.setTime(d);
        c_end.setTime(d);
        c_end.add(Calendar.MONTH, months);
        while (c_begin.before(c_end)) {
            list.add(dateToStr(c_begin.getTime(), "yyyy-MM"));
            c_begin.add(Calendar.MONTH, 1);
        }
        return  list;
    }
    
    /**
     * 返回两个日期间的字符串
     * @author zJun
     * @date 2017年10月31日 下午3:10:22
     */
    public static List<String> getDateStrList(Date begin, Date end) {
    	int date = daysBetween(begin, end);
        List<String> list=new ArrayList<String>();
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(begin);
        now.add(Calendar.DATE, date);
        Calendar c_begin = new GregorianCalendar();
        Calendar c_end = new GregorianCalendar();
        c_begin.setTime(begin);
        c_end.setTime(begin);
        c_end.add(Calendar.DATE, date);
        while (c_begin.before(c_end)) {
            list.add(dateToStr(c_begin.getTime(), "yyyy-MM-dd"));
            c_begin.add(Calendar.DATE, 1);
        }
        return list;
    }
    
	/**
	 * 获取日期d的years个年后的一个Date
	 *
	 * @param d
	 * @return
	 */
	public static Date getInternalDateByYear(Date d, int years) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.YEAR, years);
		return now.getTime();
	}

	/**
	 * 获取日期d的sec个秒后的一个Date
	 *
	 * @param d
	 * @return
	 */
	public static Date getInternalDateBySec(Date d, int sec) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.SECOND, sec);
		return now.getTime();
	}

	/**
	 * 获取日期d的min个分钟后的一个Date
	 *
	 * @param d
	 * @return
	 */
	public static Date getInternalDateByMin(Date d, int min) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.MINUTE, min);
		return now.getTime();
	}

	/**
	 * 获取日期d的hours个小时后的一个Date
	 *
	 * @param d
	 * @param hours
	 * @return
	 */
	public static Date getInternalDateByHour(Date d, int hours) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.HOUR_OF_DAY, hours);
		return now.getTime();
	}
	/**
	 *
	* @Title: getCurrentMDays
	* @Description: 获取当前月天数
	* @param
	* @return int
	* @throws
	 */
	public static int getCurrentMDays(String years_month)   throws   ParseException{
		   String yMmonth = years_month;
		   SimpleDateFormat  sdf =  new SimpleDateFormat("yyyy-MM");
		   Calendar calendar   =  new  GregorianCalendar();
		   Date date = sdf.parse(yMmonth);
		   calendar.setTime(date);
		   int day =calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		   return day;

	}

	// 获取date对应的星期,从0为周日开始计
	public static int getWeekly(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return day;
	}

	public static final String getCurrentDateTimeStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
		return df.format(new Date());
	}

	public static final String getCurrentDateStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}

    /**
     * 获取年月
     * @param addMonth 距离的月份
     * @return
     */
	public static final String getCurrentYearAndMonth(int addMonth) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");// 设置日期格式
		String currentDate = df.format(new Date());
        Date result = null;
        try {
            Date date = df.parse(currentDate);
            Calendar g = Calendar.getInstance();
            g.setTime(date);
            g.add(Calendar.MONTH,addMonth);
            result = g.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(result);
	}



	/**
     * 通过日期获取年龄
     *
     * @param birth
     * @return
     */
    public static String getBirthday(String birth) {
        String year = "";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date mydate;
            mydate = myFormatter.parse(birth);
            // System.out.println(date.getTime()+" "+mydate.getTime());
            long day = ((date.getTime() - mydate.getTime()) / 1000) / (60 * 60 * 24);
            // System.out.println(day);
            year = String.valueOf(Math.ceil((day / 365f)));
            year = year.substring(0, year.lastIndexOf("."));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return year;
    }
    
    /**
     * 通过日期获取年龄
     *
     * @param birth
     * @return
     */
    public static String getBirthday(String birth, String format) {
        String year = "";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat(format);
            Date date = new Date();
            Date mydate;
            mydate = myFormatter.parse(birth);
            // System.out.println(date.getTime()+" "+mydate.getTime());
            long day = ((date.getTime() - mydate.getTime()) / 1000) / (60 * 60 * 24);
            // System.out.println(day);
            year = String.valueOf(Math.ceil((day / 365f)));
            year = year.substring(0, year.lastIndexOf("."));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return year;
    }
    
    /**
     * 在当前日期上加天数
     *
     * @param date
     * @return
     */
    public static Date addDate(int date) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, date);
        return c.getTime();
    }

    /**
     * 在指定日期上增加天数
     * @param date
     * @param day
     * @return
     */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }
    
    /**
     * 与当前时间作比较（注意：dateStr > new Date（） return -1）
     * @param dateStr
     * 时间字符串
     * @param format
     * 格式
     * @return -1 大于当前时间 0 等于 1小于
     */
    public static int matchDate(String dateStr, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        // 系统当前时间
        Calendar cal = Calendar.getInstance();
        // 比较时间
        Calendar cal1 = Calendar.getInstance();
        int result = 0;
        try {
            cal1.setTime(sf.parse(dateStr));
            result = sf.parse(sf.format(cal.getTime())).compareTo(cal1.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 比较
        return result;
    }
    /**
     * 判断当前日期是星期几<br> <br>
     *
     * @param pTime
     * 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static int dayForWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }
    /**
     * @author lee 2013-05-28 获得两个日期的 天数集合
     * @param start,格式"yyyy-MM-dd"
     * @param end,格式"yyyy-MM-dd"
     * @return
     */
    public static List<String> getBetween2Date(String start,String end){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<String>();
        try {
            Date date_start = sdf.parse(start);
            Date date_end = sdf.parse(end);
            Date date =date_start;
            Calendar cd = Calendar.getInstance();

            while (date.getTime()<date_end.getTime()){
                list.add(sdf.format(date));
                cd.setTime(date);
                cd.add(Calendar.DATE, 1);//增加一天
                date=cd.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     */  
    public static int daysBetween(Date smdate,Date bdate){    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
    
    /**
     * 当前时间加多少分钟
     * @param minute
     * @return
     */
    public static Date addMinute(Integer minute){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MINUTE, +minute);
    	return cal.getTime();
    }

    /**
     * 获取两个时间之间的日期集合
     * @param minute
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getDatesBetweenTwoDate(Date beginDate,Date endDate) {
    	List lDate = new ArrayList();
    	lDate.add(beginDate);//把开始时间加入集合
    	Calendar cal = Calendar.getInstance();
    	//使用给定的 Date 设置此 Calendar 的时间
    	cal.setTime(beginDate);
    	boolean bContinue = true;
    	while (bContinue) {
	    	//根据日历的规则，为给定的日历字段添加或减去指定的时间量
	    	cal.add(Calendar.DAY_OF_MONTH, 1);
	    	// 测试此日期是否在指定日期之后
	    	if (endDate.after(cal.getTime())) {
	    		lDate.add(cal.getTime());
	    	} else {
	    		break;
	    	}
    	}
    	lDate.add(endDate);//把结束时间加入集合
    	return lDate;
    }
    
    /**
     * 两个时间相差的月份
     * @param minute
     * @return
     */
    public static long getMonth(String startDate, String endDate) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        long monthday;
        try {
            Date startDate1 = f.parse(startDate);
            //开始时间与今天相比较
            Date endDate1 = new Date();

            Calendar starCal = Calendar.getInstance();
            starCal.setTime(startDate1);

            int sYear = starCal.get(Calendar.YEAR);
            int sMonth = starCal.get(Calendar.MONTH);
            int sDay = starCal.get(Calendar.DATE);

            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endDate1);
            int eYear = endCal.get(Calendar.YEAR);
            int eMonth = endCal.get(Calendar.MONTH);
            int eDay = endCal.get(Calendar.DATE);

            monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));

            if (sDay < eDay) {
                monthday = monthday + 1;
            }
            return monthday;
        } catch (ParseException e) {
            monthday = 0;
        }
        return monthday;
    }
    
    /**
     * 比较两个日期是否是同一天 是：true
     * @author zJun
     * @date 2016年6月6日 上午10:55:16
     */
    public static boolean isSameDay(Date date1, Date date2){
    	if(date1 == null || date2 == null) return false;
    	String str1 = dateToStr(date1, "yyyyMMdd");
    	String str2 = dateToStr(date2, "yyyyMMdd");
    	return str1.equals(str2);
    }
    
    /**
     * 判断当前时间是否在指定日期之间
     * @author zJun
     * @date 2016年6月17日 下午6:41:41
     */
    public static boolean ifBetweenDate(String beginDate, String endDate) {
    	if(DateUtil.matchDate(beginDate, "yyyyMMdd") != -1 && DateUtil.matchDate(endDate, "yyyyMMdd") != 1) return true;
    	return false;
    }

    public static void main(String[] args) throws ParseException {
    	
//    	System.out.println(DateUtil.dateToStr(new Date(1475308153l*1000), "YYYY-MM-dd HH:mm:ss"));
//    	System.out.println(DateUtil.dateToStr(new Date(1512114553l*1000), "YYYY-MM-dd HH:mm:ss"));
//    	System.out.println(DateUtil.dateToStr(new Date(1530431353l*1000), "YYYY-MM-dd HH:mm:ss"));
    	
    	
    	System.out.println(getDateStrList(DateUtil.strToDate("20170901", "yyyyMMdd"), DateUtil.strToDate("20170901", "yyyyMMdd")));
    	
    	
    	
    	
    	
    	
//    	System.out.println(DateUtil.ifBetweenDate("20160606", "20160618"));
//    	System.out.println(DateUtil.dateToStr(DateUtil.getInternalDateByDay(new Date(), 3), "yyyyMMdd HH:mm:ss"));
//    	System.out.println(DateUtil.dateToStr(DateUtil.getInternalDateByDay(new Date(), 0), "yyyyMMdd HH:mm:ss"));
//    	System.out.println(DateUtil.dateToStr(DateUtil.getInternalDateByDay(new Date(), -3), "yyyyMMdd HH:mm:ss"));
    	
//    	System.out.println(DateUtil.matchDate("20160527142900", "yyyyMMddHHmmss"));
    	
//    	String date1="2014-09-10";
//    	System.out.println(getMonth(date1,dateToStr(new Date())));
        // System.out.println(DateUtil.getBirthday("1985-06-05"));
/*      List<String> list =getBetween2Date("2013-04-29", "2013-04-30");
        System.out.println(list.size());
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        System.out.println(getInternalDateByMonList(strToDate("2013-05", "yyyy-MM"), 3));*/
	}
}
