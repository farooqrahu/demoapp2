package com.jamil.shop.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Farooq Rahu on 2/17/2017.
 */
public class Utility {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static <T> List<T> nullSafeList(List<T> genericList) {
        return genericList == null ? Collections.EMPTY_LIST : genericList;
    }

    public static <T> Set<T> nullSafeList(Set<T> genericList) {
        return genericList == null ? Collections.EMPTY_SET : genericList;
    }

    public static Long dateToLong(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date desiredDate = cal.getTime();

        return desiredDate.getTime();
    }

    public static Date stringToDate(String Date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(Date);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return date;
    }

    public static Date longToDate(Long timeInSeconds) {
        return new Date(timeInSeconds * 1000);
    }


    public static <T> List<T> objectToList(T object) {
        List<T> objectToList = new ArrayList<T>();
        objectToList.add(object);
        return objectToList;
    }

    public static Boolean uploadFile(MultipartFile fileData, String dirPath, String fileName) {

        try {
            String readPath = String.format("%s/branch_logos/", System.getProperty("user.dir").replace("'\\'", "'//'"));
            String formatted = readPath.replace("\\", "/");
            File file = new File(formatted);

            if (!file.exists()) {
                if (file.mkdir()) {
                    LOGGER.info("Directory is created!");
                } else {
                    LOGGER.info("Failed to create directory!");
                }
            }
            if (fileData.getSize() > 0) {
                byte[] btDataFile = fileData.getBytes();
                Files.write(Paths.get(dirPath + fileName), btDataFile);
                return true;
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return false;
    }


    public static double valueOf(Double d1, double d2) {
        if (d1 == null) {
            return d2;
        } else {
            return d1.doubleValue();
        }
    }

    //******************* Date Formatter for MIR Files ***********//

    public static Long convertDateFormat(Long date, int day, String month) {
        //Date date = new Date();
        Calendar cal = Calendar.getInstance();
        //calendar.setTime(date);
        cal.setTimeInMillis(date);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH, day);
        if (month.equals("JAN")) {
            cal.set(Calendar.MONTH, Calendar.JANUARY);
        } else if (month.equals("FEB")) {
            cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        } else if (month.equals("MAR")) {
            cal.set(Calendar.MONTH, Calendar.MARCH);
        } else if (month.equals("APR")) {
            cal.set(Calendar.MONTH, Calendar.APRIL);
        } else if (month.equals("MAY")) {
            cal.set(Calendar.MONTH, Calendar.MAY);
        } else if (month.equals("JUN")) {
            cal.set(Calendar.MONTH, Calendar.JUNE);
        } else if (month.equals("JUL")) {
            cal.set(Calendar.MONTH, Calendar.JULY);
        } else if (month.equals("AUG")) {
            cal.set(Calendar.MONTH, Calendar.AUGUST);
        } else if (month.equals("SEP")) {
            cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        } else if (month.equals("OCT")) {
            cal.set(Calendar.MONTH, Calendar.OCTOBER);
        } else if (month.equals("NOV")) {
            cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        } else if (month.equals("DEC")) {
            cal.set(Calendar.MONTH, Calendar.DECEMBER);
        }
        cal.get(Calendar.YEAR);
        return cal.getTimeInMillis();
    }

    public static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    public static boolean isMac() {

        return (OS.indexOf("mac") >= 0);

    }

    public static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

    }

    public static boolean isSolaris() {

        return (OS.indexOf("sunos") >= 0);

    }


    //******************* End Date Formatter for MIR Files ***********//




}
