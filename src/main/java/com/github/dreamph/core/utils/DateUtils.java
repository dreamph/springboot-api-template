package com.github.dreamph.core.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateUtils {
    public static final Locale TH_LOCALE = new Locale("th", "TH");
    public static final Locale EN_LOCALE = new Locale("en", "US");

    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_PATTERN = "yyyy";
    public static final String YYYYMMDD_PATTERN = "yyyyMMdd";
    public static final String YYYY_MM_DD_PATTERN = "yyyy-MM-dd";
    public static final String HHMMSS_PATTERN = "HHmmss";
    public static final String SIMPLE_DATETIME_PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static final String SIMPLE_DATE_PATTERN_REPORT = " dd / MM / yyyy";
    public static final String SIMPLE_DATE_PATTERN = "dd/MM/yyyy";
    public static final String FULL_DATE_PATTERN = "dd MMMM yyyy";
    public static final long MILLISECS_PER_DAY = 86400000;
    public static final String DEFAULT_DATETIME_REPORT_PATTERN = "dd/MM/yyyy HH:mm";
    public static final String ORACLE_BAM_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss:SSSSZ";
    public static final String DEFAULT_DATETIME_NO_SEC_PATTERN = "yyyyMMddHHmm";
    public static final String DEFAULT_DATETIME_NO_SEC_PATTERN_2 = "yyMMddHHmm";
    public static final String DATE_DATETIME = "yyyy-MM-dd_HH-mm-ss";
    public static final String ALL_DATETIME_PATTERN = "yyyyMMddHHmmssSSS";
    public static final String ALL_DATETIME_PATTERN_2 = "yyMMddHHmmssSSS";

    public static Date getCurrentDate() {
        return new GregorianCalendar(EN_LOCALE).getTime();
    }

    public static Date getCurrentDateTh() throws Exception {
        return new GregorianCalendar(TH_LOCALE).getTime();
    }

    public static Date getCurrentDateByPattern() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
        String curDate = sdf.format(new Date());
        return sdf.parse(curDate);
    }

    public static Date convertDateByPattern(Date date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
        String curDate = sdf.format(date);
        return sdf.parse(curDate);
    }

    public static Locale getDefaultLocale() {
        return EN_LOCALE;
    }

    /*
     * String Thai Date Format with pattern dd/MM/yyyy
     */
    public static String toStringThaiDateSimpleFormat(Date date) throws Exception {
        SimpleDateFormat thFormat = new SimpleDateFormat(SIMPLE_DATE_PATTERN, TH_LOCALE);
        return thFormat.format(date);
    }

    /*
     * String Thai Date Format with pattern dd/MM/yyyy
     */
    public static String toStringThaiDateSimpleFormatReport(Date date) throws Exception {
        SimpleDateFormat thFormat = new SimpleDateFormat(SIMPLE_DATE_PATTERN_REPORT, TH_LOCALE);
        return thFormat.format(date);
    }

    /*
     * String Thai Date Format with pattern dd/MM/yyyy hh:mm:ss
     */
    public static String toStringThaiDateTimeSimpleFormat(Date date) throws Exception {
        SimpleDateFormat thFormat = new SimpleDateFormat(SIMPLE_DATETIME_PATTERN, TH_LOCALE);
        return thFormat.format(date);
    }

    /*
     * String Thai Date Format with pattern dd/MM/yyyy hh:mm:ss
     */
    public static String toStringThaiDateFullFormat(Date date) throws Exception {
        if (date != null) {
            SimpleDateFormat thFormat = new SimpleDateFormat(FULL_DATE_PATTERN, TH_LOCALE);
            return thFormat.format(date);
        }
        return "";
    }

    /*
     * String Eng Date Format with pattern dd/MM/yyyy
     */
    public static String toStringEngDateSimpleFormat(Date date) throws Exception {
        SimpleDateFormat engFormat = new SimpleDateFormat(SIMPLE_DATE_PATTERN, EN_LOCALE);
        if (date != null) {
            return engFormat.format(date);
        } else {
            return null;
        }
    }

    /*
     * String Eng Date Format with pattern dd/MM/yyyy hh:mm:ss
     */
    public static String toStringEngDateTimeSimpleFormat(Date date) {
        SimpleDateFormat engFormat = new SimpleDateFormat(SIMPLE_DATETIME_PATTERN, EN_LOCALE);
        return engFormat.format(date);
    }

    /*
     * String Eng Date Format with pattern yyMM
     */
    public static String toStringEngDateBySimpleFormat(Date date, String formatDate) {
        SimpleDateFormat engFormat = new SimpleDateFormat(formatDate, EN_LOCALE);
        return engFormat.format(date);
    }

    /*
     * Thai Date Format with custom pattern and Locale
     */
    public static String toStringCustomDateFormat(Date date, String pattern, Locale locale) {
        SimpleDateFormat strFormat = new SimpleDateFormat(pattern, locale);
        return strFormat.format(date);
    }

    /*
     * convert Date String to Date Object . Apply for patterns, dd/MM/yyyy,
     * dd/MM/yyyy HH:mm:ss, yyyy-MM-dd HH:mm:ss
     */
    public static Date convertStringToDate(String stringDate) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat();
        format.setCalendar(new GregorianCalendar(EN_LOCALE));
        Date rtnDate = null;
        try {
            format.applyPattern(SIMPLE_DATE_PATTERN);
            rtnDate = format.parse(stringDate);
        } catch (ParseException pe1) {
            try {
                format.applyPattern(SIMPLE_DATETIME_PATTERN);
                rtnDate = format.parse(stringDate);
            } catch (ParseException pe2) {
                format.applyPattern(DEFAULT_DATETIME_PATTERN);
                rtnDate = format.parse(stringDate);
            }
        }
        return rtnDate;
    }

    /*
     * convert Date String to Date Object . Apply for patterns, dd/MM/yyyy,
     * dd/MM/yyyy HH:mm:ss, yyyy-MM-dd HH:mm:ss
     */
    public static Date convertStringToDateByFormat(String stringDate, String pattern) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(pattern, EN_LOCALE);
        Date rtnDate = format.parse(stringDate);
        return rtnDate;
    }

    public static Date convertStringToDateByFormatTh(String stringDate, String pattern) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(pattern, TH_LOCALE);
        Date rtnDate = format.parse(stringDate);
        return rtnDate;
    }

    /*
     * convert Date String to Date Object . Apply for patterns, dd/MM/yyyy,
     * dd/MM/yyyy HH:mm:ss, yyyy-MM-dd HH:mm:ss
     */
    public static Date convertCalendarToDate(Calendar calendar) throws Exception {
        Date rtnDate = null;
        if (calendar != null) {
            long timeInMillis = calendar.getTimeInMillis();
            if (timeInMillis > 0) {
                rtnDate = new Date(timeInMillis);
            }
        }
        return rtnDate;
    }

    public static Timestamp convertCalendarToTimestamp(Calendar calendar) throws Exception {
        Timestamp timestamp = null;
        if (calendar != null) {
            long timeInMillis = calendar.getTimeInMillis();
            if (timeInMillis > 0) {
                timestamp = new Timestamp(timeInMillis);
            }
        }
        return timestamp;
    }

    /*
     * calculate Diff Date return number of differ days
     */
    public static long diffDayCalculate(Date startDate, Date endDate) throws Exception {
        long endL = endDate.getTime();
        long startL = startDate.getTime();
        return ((endL - startL) / MILLISECS_PER_DAY);
    }

    public static long diffSecCalculate(Date startDate, Date endDate) throws Exception {
        long endL = endDate.getTime();
        long startL = startDate.getTime();
        return ((endL - startL) / 1000);
    }

    /*
     * calculate Diff Date return number of differ days
     */
    public static long diffDayCalculateIgnoreTime(Date startDate, Date endDate) throws Exception {
        /*
         * Date startDateNoTime = setDateIgnoreTime(startDate); Date
         * endDateNoTime = setDateIgnoreTime(endDate); long endL =
         * endDateNoTime.getTime(); long startL = startDateNoTime.getTime();
         * return ((endL - startL) / MILLISECS_PER_DAY);
         */
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
        String startDtString = sdf.format(startDate);
        Date startDateNoTime = sdf.parse(startDtString);
        String endDtString = sdf.format(endDate);
        Date endDateNoTime = sdf.parse(endDtString);

        long endL = endDateNoTime.getTime();
        long startL = startDateNoTime.getTime();
        return ((endL - startL) / MILLISECS_PER_DAY);
    }

    /*
     * private static Date setDateIgnoreTime(Date date) throws Exception{
     * Calendar calendar = new GregorianCalendar(); calendar.setTime(date);
     * calendar.set(Calendar.HOUR, 0); calendar.set(Calendar.MINUTE, 0);
     * calendar.set(Calendar.SECOND, 0); calendar.set(Calendar.MILLISECOND, 0);
     * return calendar.getTime(); }
     */

    /*
     * calculate Diff Month return number of differ months
     */
    public static long diffMonthCalculate(Date startDate, Date endDate) throws Exception {
        long endL = endDate.getTime();
        long startL = startDate.getTime();
        return ((endL - startL) / (MILLISECS_PER_DAY * 30));
    }

    /*
     * calculate Diff Month return number of differ months
     */
    public static long diffMonthCalculateIgnoreTime(Date startDate, Date endDate) throws Exception {
        /*
         * Date startDateNoTime = setDateIgnoreTime(startDate); Date
         * endDateNoTime = setDateIgnoreTime(endDate); long endL =
         * endDateNoTime.getTime(); long startL = startDateNoTime.getTime();
         * return ((endL - startL) / MILLISECS_PER_DAY);
         */
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
        String startDtString = sdf.format(startDate);
        Date startDateNoTime = sdf.parse(startDtString);
        String endDtString = sdf.format(endDate);
        Date endDateNoTime = sdf.parse(endDtString);

        long endL = endDateNoTime.getTime();
        long startL = startDateNoTime.getTime();
        return ((endL - startL) / (MILLISECS_PER_DAY * 30));
    }

    public static long diffYearCalculate(Date startDate, Date endDate) throws Exception {
        long endL = endDate.getTime();
        long startL = startDate.getTime();
        return ((endL - startL) / (MILLISECS_PER_DAY * 365));
    }

    /*
     * calculate Diff Year return number of differ years
     */
    public static long diffYearCalculateIgnoreTime(Date startDate, Date endDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
        String startDtString = sdf.format(startDate);
        Date startDateNoTime = sdf.parse(startDtString);
        String endDtString = sdf.format(endDate);
        Date endDateNoTime = sdf.parse(endDtString);

        long endL = endDateNoTime.getTime();
        long startL = startDateNoTime.getTime();
        return ((endL - startL) / (MILLISECS_PER_DAY * 365));
    }

    /*
     * shift min up int mins = +,-
     */
    // Add by Chayatorn 12/05/2010
    public static Date shiftMinute(Date inputDate, int mins) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(inputDate);
        calendar.add(Calendar.MINUTE, mins);
        return calendar.getTime();
    }

    public static Date shiftMinute(Date inputDate, Long mins) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(inputDate);
        calendar.add(Calendar.MINUTE, mins.intValue());
        return calendar.getTime();
    }

    public static Date shiftSecond(Date inputDate, Long secs) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(inputDate);
        calendar.add(Calendar.SECOND, secs.intValue());
        return calendar.getTime();
    }

    /*
     * shift date up
     */
    public static Date shiftDateUp(Date inputDate, int days) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(inputDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /*
     * shift date down
     */
    public static Date shiftDateDown(Date inputDate, int days) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(inputDate);
        int dayDown = days * -1;
        calendar.add(Calendar.DAY_OF_MONTH, dayDown);
        return calendar.getTime();
    }

    /*
     * shift month up
     */
    public static Date shiftMonthUp(Date inputDate, int months) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(inputDate);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /*
     * shift month down
     */
    public static Date shiftMonthDown(Date inputDate, int months) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(inputDate);
        int monthDown = months * -1;
        calendar.add(Calendar.MONTH, monthDown);
        return calendar.getTime();
    }

    public static String getCurrentYear() {
        SimpleDateFormat engFormat = new SimpleDateFormat("yyyy", EN_LOCALE);
        return engFormat.format(new Date());
    }

    public static String getCurrentMonth() {
        SimpleDateFormat engFormat = new SimpleDateFormat("MM", EN_LOCALE);
        return engFormat.format(new Date());
    }

    public static String getCurrentDay() {
        SimpleDateFormat engFormat = new SimpleDateFormat("dd", EN_LOCALE);
        return engFormat.format(new Date());
    }

    public static String getCurrentMonthYear() {
        SimpleDateFormat engFormat = new SimpleDateFormat("MMyyyy", EN_LOCALE);
        return engFormat.format(new Date());
    }

    public static String getCurrentDayMonthYear() {
        SimpleDateFormat engFormat = new SimpleDateFormat("ddMMyy", EN_LOCALE);
        return engFormat.format(new Date());
    }

    public static String getCurrentHoMinSec() {
        SimpleDateFormat engFormat = new SimpleDateFormat("HHmmss", EN_LOCALE);
        return engFormat.format(new Date());
    }

    /*
     * String Eng Date Format with pattern yyMM
     */
    public static String toStringThaiDateBySimpleFormat(Date date, String formatDate) {
        SimpleDateFormat engFormat = new SimpleDateFormat(formatDate, TH_LOCALE);
        return engFormat.format(date);
    }

    public static boolean compareDateTime(String timeFrom, String timeTo) throws ParseException {
        boolean checked = false;

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date from = sdf.parse(timeFrom);
        Date to = sdf.parse(timeTo);

        if (from.after(to)) {
            checked = true;
        }
        return checked;
    }

    /*
     * get current date timestamp dd/MM/yyyy HH:mm:ss, yyyy-MM-dd HH:mm:ss
     */
    public static Timestamp getCurrentDateTimeStamp() {
        Date curDate = null;
        Timestamp currentTimestamp = null;
        try {
            curDate = getCurrentDate();
            currentTimestamp = new Timestamp(curDate.getTime());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentTimestamp;
    }

    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + day);
        return cal.getTime();
    }

    public static java.sql.Date getCurrentSqlDate() {
        return new java.sql.Date(new Date().getTime());
    }

    public static java.sql.Date getSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static Date getDateWithoutTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Integer compareToDayOnly(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        LocalDate localDate1 = LocalDate.of(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH) + 1, calendar1.get(Calendar.DAY_OF_MONTH));
        LocalDate localDate2 = LocalDate.of(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH) + 1, calendar2.get(Calendar.DAY_OF_MONTH));

        if (localDate1.isBefore(localDate2)) {
            return -1;
        } else if (localDate1.isEqual(localDate2)) {
            return 0;
        } else
            return 1;

    }

    public static Date getTomorrowDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static Timestamp toTimestamp(Date date) {
        return date == null ? null : new Timestamp(date.getTime());
    }

    public static Timestamp toTimestamp(Calendar date) {
        return date == null ? null : new Timestamp(date.getTime().getTime());
    }

    public static Date toDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return new Date(timestamp.getTime());
    }

    public static Date getEndOfDay(Date date) {
        return org.apache.commons.lang3.time.DateUtils.addMilliseconds(org.apache.commons.lang3.time.DateUtils.ceiling(date, Calendar.DATE), -1);
    }

    public static Date getStartOfDay(Date date) {
        return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.DATE);
    }

    public static Date getStartDayOfMonth(Date date) {
        return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.MONTH);
    }

    public static Date getEndDayOfMonth(Date date) {
        return org.apache.commons.lang3.time.DateUtils.addMilliseconds(org.apache.commons.lang3.time.DateUtils.ceiling(date, Calendar.MONTH), -1);
    }

    public static Date getStartDayOfYear(Date date) {
        return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.YEAR);
    }

    public static Date getEndDayOfYear(Date date) {
        return org.apache.commons.lang3.time.DateUtils.addMilliseconds(org.apache.commons.lang3.time.DateUtils.ceiling(date, Calendar.YEAR), -1);
    }

    public static Date getStartDayOfQuarterly(int quarterly, int year) throws ParseException {
        int month = startMonthQuarter(quarterly);
        final SimpleDateFormat formatLocalDate = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.of(year, month, 1);
        LocalDate firstDay = localDate.with(localDate.getMonth().firstMonthOfQuarter())
                .with(TemporalAdjusters.firstDayOfMonth());
        return getStartOfDay(formatLocalDate.parse(firstDay.toString()));
    }

    public static Date getEndDayOfQuarterly(int quarterly, int year) throws ParseException {
        int month = startMonthQuarter(quarterly);
        final SimpleDateFormat formatLocalDate = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.of(year, month, 1);
        LocalDate firstDay = localDate.with(localDate.getMonth().firstMonthOfQuarter())
                .with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = firstDay.plusMonths(2)
                .with(TemporalAdjusters.lastDayOfMonth());
        return getEndOfDay(formatLocalDate.parse(lastDay.toString()));
    }

    public static Date getStartDayOfMonth(int month, int year) throws ParseException {
        final SimpleDateFormat formatLocalDate = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.of(year, month, 1);
        LocalDate firstDay = localDate.with(localDate.getMonth())
                .with(TemporalAdjusters.firstDayOfMonth());
        return getStartOfDay(formatLocalDate.parse(firstDay.toString()));
    }

    public static Date getEndDayOfMonth(int month, int year) throws ParseException {
        final SimpleDateFormat formatLocalDate = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.of(year, month, 1);
        LocalDate firstDay = localDate.with(localDate.getMonth())
                .with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = firstDay.with(TemporalAdjusters.lastDayOfMonth());
        return getEndOfDay(formatLocalDate.parse(lastDay.toString()));
    }

    public static Date getDateTime(Date date, Integer timeHH, Integer timeMM) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, timeHH);
        cal.set(Calendar.MINUTE, timeMM);
        return cal.getTime();
    }

    private static int startMonthQuarter(int quarter) {
        if (quarter == 1) {
            return 1;
        } else if (quarter == 2) {
            return 4;
        } else if (quarter == 3) {
            return 7;
        } else if (quarter == 4) {
            return 10;
        } else {
            return 0;
        }
    }

    public static boolean compareDateTime(Date timeFrom, Date timeTo) throws ParseException {
        boolean checked = timeFrom.after(timeTo);

        return checked;
    }

    public static String getUniqueNumberByDate(int randomDigitAmount) {
        int bound = (int) Math.pow(10, randomDigitAmount - 1);
        int randomNumber = new Random().nextInt(bound * 9) + bound;
        String date = DateUtils.toStringEngDateBySimpleFormat(getCurrentDate(), DateUtils.ALL_DATETIME_PATTERN);
        return date + randomNumber;
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(getEndOfDay(new Date()));

//        Calendar start = Calendar.getInstance();
//        Calendar end = Calendar.getInstance();
//        end.set(Calendar.MONTH, 1);
//        System.out.println(getStartDayOfMonth(8,2020));

//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, Integer.parseInt("2020"));
//        cal.set(Calendar.MONTH, Integer.parseInt("12"));
//
//        Date currentTime = DateUtils.getCurrentDate();

        //Calculate Monthly
//        Date firstDayOfMonth = DateUtils.getStartDayOfMonth(Integer.parseInt(request.getMonth()), Integer.parseInt(request.getYear()));
//        Date lastDayOfMonth = DateUtils.getEndDayOfMonth(Integer.parseInt(request.getMonth()), Integer.parseInt(request.getYear()));
//        Date shiftMonthDown = DateUtils.shiftMonthDown(cal.getTime(),1);
//        Date lastDayOfLastMonth = DateUtils.getEndDayOfMonth(shiftMonthDown);
//        System.out.println("cal.getTime():"+cal.getTime());
//        System.out.println("shiftMonthDown:"+shiftMonthDown);
//        System.out.println("lastDayOfLastMonth:"+shiftMonthDown);


//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        Date firstDayOfYear = DateUtils.getStartDayOfYear(cal.getTime());

//        Date firstDayOfMonth = DateUtils.getStartDayOfMonth(cal.getTime());

//        Date firstDayOfMonth = cal.getTime();

//        Date endDayOfYear = DateUtils.getEndDayOfYear(cal.getTime());
//        System.out.println(firstDayOfYear+" , "+endDayOfYear);
//        System.out.println(DateUtils.toStringThaiDateBySimpleFormat(firstDayOfYear,FULL_DATE_PATTERN));

//        String dayThStr = DateUtils.toStringCustomDateFormat(firstDayOfMonth, "dd", TH_LOCALE);
//        System.out.println(dayThStr);
//
//        String monthThStr = DateUtils.toStringCustomDateFormat(firstDayOfMonth, "MMM", TH_LOCALE);
//        System.out.println(monthThStr);
//
//        String yyyyThStr = DateUtils.toStringCustomDateFormat(firstDayOfMonth, "YYYY", TH_LOCALE);
//        System.out.println(yyyyThStr);
//
//        String dateTh = dayThStr+ " " + monthThStr+ " " + yyyyThStr;
//        System.out.println(dateTh);

//        System.out.println(compareDateTime(getCurrentDate(), addDay(start.getTime(),-10)));
//        System.out.println(addDay(end.getTime(),10));
//        System.out.println(diffDayCalculateIgnoreTime(addDay(start.getTime(),10), addDay(end.getTime(),10)));
    }

    public static String dateThai(Date date) {
        return dateThai(toStringThaiDateBySimpleFormat(date, YYYY_MM_DD_PATTERN));
    }

    public static String dateThai(String strDate) {
        String[] months = {
                "ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.",
                "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.",
                "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค."};
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int year = 0, month = 0, day = 0;

        try {
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DATE);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return String.format("%s %s %s", day, months[month], year + 543);
    }

    public static Date shiftSecond(Date inputDate, int sec) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(inputDate);
        calendar.add(Calendar.SECOND, sec);
        return calendar.getTime();
    }

    public static void main_(String[] args) {
        try {
            System.out.println(getCurrentDateTh());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
