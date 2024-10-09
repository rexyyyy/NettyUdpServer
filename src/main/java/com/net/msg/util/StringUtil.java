package com.net.msg.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class StringUtil {

    public static boolean isEmpty(String str)
    {
        return  str==null || "".equals(str.trim());
    }
    public static boolean isEmptyLong(Long str)
    {
        return  str==null ;
    }
    /**
     * 将传入类型转换为String
     *
     * @param value
     * @return
     */
    public static String toString(Object value) {

        return value == null ? "" : value.toString();
    }

    public static String toStringTrim(Object value) {

        return value == null ? "" : value.toString().trim();
    }

    static String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    public static String getWeekOfDate(Date date) {


        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;

        if (w < 0)

            w = 0;

        return weekDays[w];

    }

    /**
     * 将传入类型转换为String
     *
     * @param value
     * @return
     */
    public static String toHtmlStringEsNull(Object value) {
        return (value == null || "null".equals(value) || "".equals(value)) ? "&nbsp;" : value.toString();
    }

    /**
     * 将传入类型转换为String
     *
     * @param value
     * @return
     */
    public static String toStringEsNull(Object value) {
        return (value == null || "null".equals(value) || "".equals(value)) ? "" : value.toString();
    }

    /**
     *
     * @param value
     * @return
     */
    public static int toInt(Object value) {
        if(value instanceof Number){
            Number mm =(Number)value;
            return mm.intValue();
        }else {
            try {
                String str = value == null ? "" : value.toString();
                int idx = str.indexOf(".");
                if(idx>=0)
                {
                    str=str.substring(0,idx);
                }
                return Integer.parseInt(str);
            } catch (Exception ex) {
            }
        }
        return -1;
    }

    /**
     *
     * @param value
     * @return
     */
    public static long toLong(Object value) {
        if(value instanceof Number){
            Number mm =(Number)value;
            return mm.longValue();
        }else {
            try {
                String str = value == null ? "" : value.toString();
                int idx = str.indexOf(".");
                if (idx >= 0) {
                    str = str.substring(0, idx);
                }
                return Long.parseLong(str);
            } catch (Exception ex) {
            }
        }
        return -1L;

    }

    public static double toDouble(Object value) {
        if(value instanceof Number){
            Number mm =(Number)value;
            return mm.doubleValue();
        }else {
            String str = value == null ? "" : value.toString();
            double d = -1;
            try {
                d = Double.parseDouble(str);
            } catch (Exception ex) {
            }
            return d;
        }

    }

    public static int toInt0(Object value) {
        try {
            String str = value == null ? "" : value.toString();
            return Integer.parseInt(str);
        } catch (Exception ex) {
        }
        return 0;
    }

    /**
     *
     * @param value
     * @return
     */
    public static long toLong0(Object value) {
        try {
            String str = value == null ? "" : value.toString();

            BigDecimal bd1 = new BigDecimal(str);

            return bd1.longValue();
            //return Long.parseLong(str);
        } catch (Exception ex) {
        }
        return 0L;

    }

    public static double toDouble0(Object value) {
        String str = value == null ? "" : value.toString();
        double d = 0;
        try {
            d = Double.parseDouble(str);
        } catch (Exception ex) {
        }
        return d;
    }
    public static String toDouble2(Double value) {
        String str = value == null ? "" : value.toString();
        double d = 0;
        if(value==null)
            return "0.00";

            //d = Double.parseDouble(str);
            DecimalFormat formatter = new DecimalFormat("0.00");
            return formatter.format(value);

    }
    public static String doubleToStr(Double d) {
        if(d==null)
            return "0.0";
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return nf.format(d);
    }

    public static String convert(double value) {
        DecimalFormat formatter = new DecimalFormat("###,###.00");
        return formatter.format(value);
    }
    public static String numPrise(Number num, int bits) {
        String sr = "";
        if (null != num) {
            DecimalFormat df = new DecimalFormat("#.00000000");
            String s = df.format(num);
            ////System.out.println(s);
            //s="12345";
            int pos = s.indexOf(".");
            if (pos > 0) {
                String s1 = s.substring(0, pos);
                String s2 = s.substring(pos + 1);
                ////System.out.println(s2);
                if (s2.length() < bits) {
                    while (s2.length() < bits) {
                        s2 = s2 + "0";
                    }
                } else {
                    s2 = s2.substring(0, bits);
                }
                sr = s1 + "." + s2;
            } else {

                String s2 = "";
                while (s2.length() < bits) {
                    s2 = s2 + "0";
                }
                sr = s + "." + s2;
            }

            return sr;
        } else {
            return "";
        }
    }
    public static String getTimeStr(Date date, String strFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
        String strDate = formatter.format( date );
        return strDate;
    }
    public static String getTimeStr(String strFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
        String strDate = formatter.format(new Date());
        return strDate;
    }

    static SimpleDateFormat formatterDateMin = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static String getTimeStrDefaultMin(Date date) {

        String strDate = formatterDateMin.format(date);
        return strDate;
    }

    static SimpleDateFormat formatterDateDefault = new SimpleDateFormat("yyyy-MM-dd");
    public static String getDateDefault(Date date) {

        String strDate = formatterDateDefault.format(date);
        return strDate;
    }

    static SimpleDateFormat formatterDateNowChDefault = new SimpleDateFormat("yyyy年MM月dd日");
    public static String getDateNowCh(Date date) {

        String strDate = formatterDateNowChDefault.format(date);
        return strDate;
    }

    public static String getDateDefault() {

        String strDate = formatterDateDefault.format(new Date());
        return strDate;
    }
    static  SimpleDateFormat formatterDefault = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getTimeStrDefault(Date date) {

        String strDate = formatterDefault.format(date);
        return strDate;
    }


    public static String getTimeStrDateDefault(String dateStr) {

        String newStr="";
        try {
            int strLen=dateStr.length();
            if(strLen==16) {
                Date strDate = formatterDefaultHuor.parse(dateStr);

                newStr = formatterDateDefault.format(strDate);
            }else if(strLen>16){
                Date strDate = formatterDefault.parse(dateStr);

                newStr = formatterDateDefault.format(strDate);
            }else if(strLen>1 && strLen<11){
                return dateStr;
            }
        }catch (Exception ex){
            return "";
        }
        return newStr;
    }

    static  SimpleDateFormat formatterDefaultHuor = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static String getTimeStrDefaultHour(Date date) {

        String strDate = formatterDefaultHuor.format(date);
        return strDate;
    }

    public static Date toDateMin(String str1){
        Date date=new Date();
        try {
            date = formatterDefaultHuor.parse(str1);
        }catch (Exception e){

        }
        return date ;
    }

    static  SimpleDateFormat formatterYYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
    public static Date getTimeYYYYMMDDHHMMSS(String YYYYMMDDHHMMSS) {
        Date date1=null;
        try {
            date1 = formatterYYYYMMDDHHMMSS.parse(YYYYMMDDHHMMSS);
        }catch (java.text.ParseException pex){
            date1=null;
        }
        return date1;
    }

    public static String getDateStrNum() {

        String strDate = formatterYYYYMMDDHHMMSS.format(new Date());
        return strDate;
    }

    public static String getTimeStampStr() {
        return getTimeStr("yyyy-MM-dd HH:mm:ss");
    }

    public static String lpad(int num, int length, char ch) {

        String str = Integer.toString(num);
        int len = str.length();
        for (; len < length;) {
            str = ch + str;
            len = str.length();
        }
        return str;
    }

    /**
     *
     * @param _str
     *            String
     * @param length
     *            int
     * @param ch
     *            char
     * @return String
     */
    public static String lpad(String _str, int length, char ch) {

        String str = _str;
        int len = str.length();
        for (; len < length;) {
            str = ch + str;
            len = str.length();
        }
        return str;
    }
    public static String rpad(String _str, int length, char ch) {

        String str = _str;
        int len = str.length();
        for (; len < length;) {
            str = str+ch;
            len = str.length();
        }
        return str;
    }

 public static String join(Iterator<?> iterator, String separator) {
 

    if (iterator == null) {
      return null;
    }


    if (!iterator.hasNext()) {
      return "";
    }


    Object first = iterator.next();

    if (!iterator.hasNext()) {
      return toString(first);
    }
 

    StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small

    if (first != null) {
      buf.append(first);
    }

 
    while (iterator.hasNext()) {
      if (separator != null) {

        buf.append(separator);
      }

      Object obj = iterator.next();
      if (obj != null) {

        buf.append(obj);
      }
    }
    return buf.toString();
  }
}
