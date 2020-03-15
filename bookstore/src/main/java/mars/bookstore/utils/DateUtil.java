package mars.bookstore.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "HH:mm yyyy-MM-dd";
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYYY_MM = "yyyy-MM";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_YYYYMM = "yyyyMM";
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final int DATETYPE_MONTH = 0;   //日期类型：月
    public static final int DATETYPE_DAY = 1;     //日期类型：日
    
    public static final int UPDATE_CYCLE_DAY = 3;//日周期
    public static final int UPDATE_CYCLE_MONTH = 2;//月周期
    
    public static final int LAST_MONTH = -30; //往前推一个月
    public static final int LAST_YEAR = -12;  //往前推一年

    public static String string3String(String date) {
        
        
        String s = "";
        if (date.indexOf(".") > 0) {
            int pos = date.indexOf(".");
            s = date.substring(0, pos);

        }
        return s;
    }
    public static String string2String2(String date) {
        
        String s = date;
        if (s == null || s.equals("")) {
            return null;
        }
        String result1 = null;
        try {
            DateFormat format = null;
            if (s.length() > 15) {
                format = new SimpleDateFormat(DATETIME_FORMAT);
            } else if (s.length() > 8) {
                format = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
            } else if (s.length() > 4) {
                format = new SimpleDateFormat(FORMAT_YYYY_MM);
            } else {
                format = new SimpleDateFormat("yyyy");
            }
            result1 = date2String(format.parse(s), FORMAT_YYYY_MM_DD);
        } catch (Exception e) {
            logger.error("日期转换异常",e);
        }
        return result1;
    }
    
    /**
     * 格式化当前日期为需要的格式
     * @param dateStr
     * @param dateStrFormat
     * @param afterFormat
     * @return
     */
    public static String string2StringFormat(String dateStr, String dateStrFormat, String afterFormat) {
        if(dateStr == null || "".equals(dateStr)) {
            return null;
        }
        String result = null;
        try {
            DateFormat format = new SimpleDateFormat(dateStrFormat);
            
            result = date2String(format.parse(dateStr), afterFormat);
        } catch (ParseException e) {
            logger.error("日期转换异常",e);
        }
        return result;
    }
    
    public static String string2String(String date) {
        String s = "";
        
        if (date == null || date.equals("")) {
            return null;
        }
        
        if (date.indexOf(".") > 0) {
            int pos = date.indexOf(".");
            s = date.substring(0, pos);

        }

        if (s == null || s.equals("")) {
            return null;
        }
        String result = null;
        try {
            DateFormat format = null;
            if (s.length() > 15) {
                format = new SimpleDateFormat(DATETIME_FORMAT);
            } else if (s.length() > 8) {
                format = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
            } else if (s.length() > 4) {
                format = new SimpleDateFormat(FORMAT_YYYY_MM);
            } else {
                format = new SimpleDateFormat("yyyy");
            }
            result = date2String(format.parse(s), DATE_FORMAT);
        } catch (Exception e) {
            logger.error("日期转换异常",e);
        }
        return result;
    }

    public static String date2String(Date date) {
        if (date == null) {
            return "";
        }
        String result = "";
        DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
        result = dateFormat.format(date);
        return result;
    }

    /**
     * 日期转换为字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String date2String(Date date, String formatPra) {
        String format = formatPra;
        if (date == null) {
            return "";
        }
        if (format == null || format.equals("")) {
            format = DATETIME_FORMAT;
        }
        String result = "";
        DateFormat dateFormat = new SimpleDateFormat(format);
        result = dateFormat.format(date);
        return result;
    }

    public static String timeStamp2String(java.sql.Timestamp date) {
        if (date == null) {
            return "";
        }
        String result = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        result = dateFormat.format(date);
        return result;
    }
    public static String timeStamp2String(java.sql.Timestamp date,String format) {
        if (date == null) {
            return "";
        }
        String result = "";
        DateFormat dateFormat = new SimpleDateFormat(format);
        result = dateFormat.format(date);
        return result;
    }
    
    public static Date string2Date(String dateStr, String formatStr) {
        if (dateStr == null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            logger.error("日期转换异常",e);
        }
        return date;
    }
    
    public static Timestamp  string2Timestamp(String sdate) {
        if (sdate == null) {
            return null;
        }
        Timestamp ts=null;
        try {
            ts = Timestamp.valueOf(sdate);
            
        }catch (Exception e){
            
        }
        return ts;
    }
  
    
    /**
     * 获取当前日期往前推一定日期的日期字符串
     * @author YZ
     * TODO
     * @param monthNum
     * @param dataDate 当前日期  格式为yyyymmdd
     * @return
     *
     */
    public static String getFrontDay(int dayNum, String dataDate) {
        String dateStr = "";
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYYMMDD);
        try {
            Date date = format.parse(dataDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if(!StringUtil.isEmpty(dataDate)) {
                calendar.add(Calendar.DATE,-dayNum);
                dateStr = format.format(calendar.getTime());
            }
        } catch (ParseException e) {
            logger.error("日期转换异常",e);
        }
        return dateStr;
    }
    
    /**
     * 
     * @author YZ
     * TODO 获取当前日期往前推一定日期的日期字符串
     * @param dayNum
     * @param dataDate
     * @param dateFormat
     * @return
     *
     */
    public static String getFrontDay(int dayNum, String dataDate,String dateFormat) {
        String dateStr = "";
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            Date date = format.parse(dataDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if(!StringUtil.isEmpty(dataDate)) {
                calendar.add(Calendar.DATE,-dayNum);
                dateStr = format.format(calendar.getTime());
            }
        } catch (ParseException e) {
            logger.error("日期转换异常",e);
        }
        return dateStr;
    }
    
    /**
     * 
     * @author YZ
     * TODO 获取当月最后一天
     * @param date
     * @return
     *
     */
    public static Date lastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }
    
    /**
     * 获取指定月份的最后一天的字符串
     * @param dataDate yyyymm
     * @return yyyymmdd
     */
    public static String lastDayOfMonth(String dataDate) {
        String dayStr = "";
        SimpleDateFormat month_format = new SimpleDateFormat(FORMAT_YYYYMM);
        SimpleDateFormat day_format = new SimpleDateFormat(FORMAT_YYYYMMDD);
        try {
            Date monthDate = month_format.parse(dataDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(monthDate);
            int value = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, value);
            dayStr = day_format.format(calendar.getTime());
        } catch (ParseException e) {
            logger.error("日期转换异常",e);
        }
        return dayStr;
    }
    
    /**
     * 
     * @author DG
     * TODO 获取当月第一天
     * @param date
     * @return
     *
     */
    public static Date firstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }
    
    /**
     * 获得指定日期的前几天的日期字符串
     * @param currentDay 当前日期
     * @param num 当前日期前几天
     * @return yyyyMMdd型的日期
     * @throws Exception
     */
    public static String getLastDate(String currentDay, int num) {
        DateFormat df = new SimpleDateFormat(FORMAT_YYYYMMDD);
        Date date = null;
        String dateStr = null;
        try {
            date = df.parse(currentDay);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int day = calendar.get(Calendar.DATE);
            calendar.set(Calendar.DATE, day - num); // 得到前几天
            Date lastDate = calendar.getTime();
            dateStr = df.format(lastDate);
        } catch (ParseException e) {
            logger.error("日期转换异常",e);
        }
        return dateStr;
    }
    
    /**
     * 获得当前日期的前一天的日期字符串
     * @return yyyyMMdd型的日期
     * @throws Exception
     */
    public static String getLastDate() {
        DateFormat df = new SimpleDateFormat(FORMAT_YYYYMMDD);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1); // 得到前一天
        Date lastDate = calendar.getTime();
        String dateStr = df.format(lastDate);
        return dateStr;
    }

    /**
     * 获取偏移日期
     * @param offset 偏移量
     * @param dateType  日期类型
     * @return
     */
//    public static String getOffsetDate(int offset,int dateType){
//        String offsetDate = "";
//        Calendar c = Calendar.getInstance();
//        switch (dateType){
//            case DATETYPE_MONTH:
//                c.add(Calendar.MONTH, offset);//前一个月
//                DateFormat yyyyMMDateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMAT_YYYYMM);
//                offsetDate = yyyyMMDateFormat.format(c.getTime());
//                break;
//            case DATETYPE_DAY:
//                c.add(Calendar.DATE, offset);//前一天，建议从配置文件中取
//                DateFormat yyyyMMdateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMAT_YYYYMMDD);
//                offsetDate = yyyyMMdateFormat.format(c.getTime());
//                break;
//            default:
//        }
//        return offsetDate;
//    }
    
    /**
     * 获取指定日期偏移日期
     * @param date yyyyMM或者yyyyMMdd形式字符串，根据dateType决定
     * @param offset 偏移量
     * @param dateType  日期类型
     * @return
     */
    public static String getOffsetDateByDate(String date, int offset, int dateType) {
        if(date == null || "".equals(date)) {
            return null;
        }
        String offsetDate = "";
        Calendar c = Calendar.getInstance();
        try {
            switch (dateType) {
            case DATETYPE_MONTH:
                DateFormat yyyyMMDateFormat = new SimpleDateFormat(FORMAT_YYYYMM);
                Date m_date = yyyyMMDateFormat.parse(date);
                c.setTime(m_date);
                c.add(Calendar.MONTH, offset);
                offsetDate = yyyyMMDateFormat.format(c.getTime());
                break;
            case DATETYPE_DAY:
                DateFormat yyyyMMddDateFormat = new SimpleDateFormat(FORMAT_YYYYMMDD);
                Date d_date = yyyyMMddDateFormat.parse(date);
                c.setTime(d_date);
                c.add(Calendar.DATE, offset);
                offsetDate = yyyyMMddDateFormat.format(c.getTime());
                break;
            default:
            }
        } catch (ParseException e) {
            logger.error("日期转换异常",e);
        }
        return offsetDate;
    }
    /**
     * 获取指定日期偏移日期
     * @param date yyyyMM或者yyyyMMdd形式字符串，根据dateType决定
     * @param offset 偏移量
     * @param dateType  日期类型
     * @return
     */
    public static String getOffsetDateByDate(String date, int offset, int dateType, String fmt) {
        if(date == null || "".equals(date)) {
            return null;
        }
        String offsetDate = "";
        Calendar c = Calendar.getInstance();
        try {
            switch (dateType) {
            case UPDATE_CYCLE_MONTH:
                DateFormat yyyyMMDateFormat = new SimpleDateFormat(fmt);
                Date m_date = yyyyMMDateFormat.parse(date);
                c.setTime(m_date);
                c.add(Calendar.MONTH, offset);
                offsetDate = yyyyMMDateFormat.format(c.getTime());
                break;
            case UPDATE_CYCLE_DAY:
                DateFormat yyyyMMddDateFormat = new SimpleDateFormat(fmt);
                Date d_date = yyyyMMddDateFormat.parse(date);
                c.setTime(d_date);
                c.add(Calendar.DATE, offset);
                offsetDate = yyyyMMddDateFormat.format(c.getTime());
                break;
            default:
            }
        } catch (ParseException e) {
            logger.error("日期转换异常",e);
        }
        return offsetDate;
    }
    public static String getCurrentDay(){
        String date = date2String(new Date(), FORMAT_YYYY_MM_DD)+" 00:00:00";
        return date;
    }
    
    /**
     * 获取当前时间
     * 日期管理中添加，用于作为日期选择组件的截止日期
     * @return
     * @author caihq 68434
     */
    public static String getCurrentDayYYYYMMDD(){
        String date = date2String(new Date(), FORMAT_YYYYMMDD);
        return date;
    }
    
    /**
     * 将yyyymm和yyyymmdd格式的日期转换成yyyy-mm和yyyy-mm-dd
     * @param dataDate
     * @return
     */
    public static String dateFormat(String dataDate) {
        String formatStr = "";
        String resultFormatStr = "";
        String dateStr = "";
        if(null != dataDate && !"".equals(dataDate)) {
            if(dataDate.length() == 6) {
                formatStr = FORMAT_YYYYMM;
                resultFormatStr = FORMAT_YYYY_MM;
            }
            if(dataDate.length() == 8) {
                formatStr = FORMAT_YYYYMMDD;
                resultFormatStr = FORMAT_YYYY_MM_DD;
            }
        } else {
            return null;
        }
        
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        SimpleDateFormat resultFormat = new SimpleDateFormat(resultFormatStr);
        try {
            Date theDate = format.parse(dataDate);
            dateStr = resultFormat.format(theDate);
        } catch (ParseException e) {
            logger.error("日期转换异常",e);
        }
        return dateStr;
    }
    
    /**
     * 获取date往前推latestDays天的日期
     * @version ZJ
     * @param date
     * @param latestDays
     * @return
     */
    public static Date  getStartDate(Date date,int latestDays){
        Calendar cd = Calendar.getInstance();   
        cd.setTime(date);
        cd.add(Calendar.DATE, -latestDays);
        return cd.getTime();
    }
    
  
     //-----------------------------------------------------------------------
    /**
     * Adds a number of years to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of months to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of weeks to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addWeeks(Date date, int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of days to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of hours to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of minutes to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of seconds to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addSeconds(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of milliseconds to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMilliseconds(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param calendarField  the calendar field to add to
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     * @deprecated Will become privately scoped in 3.0
     */
    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
    
    public static String getEarlierDate(String dateAStr,String dateBStr,String format){
        Date dateA = DateUtil.string2Date(dateAStr, format);
        Date dateB = DateUtil.string2Date(dateBStr, format);
        if(null!=dateA && null!=dateB && dateA.getTime()>dateB.getTime()){
            return dateBStr;
        }else{
            return dateAStr;
        }
    }
    
}