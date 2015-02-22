package com.tahara.amarshimi.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by amarshimi on 2/22/2015.
 */

public class NidaDAO {
    private MySQLiteOpenHelper mySQLiteHelper;
    private Context context;

    public NidaDAO(Context context) {
        mySQLiteHelper = new MySQLiteOpenHelper(context);
        this.context = context;
    }

    // -----------insertDaysColorsRed----------------------------------------------------------------------

    public void insertDaysColorsRed(long time, String tableClnColor) {

        List<Long> daysArr = new ArrayList<Long>();
        daysArr = addDaysFourRed(time);

        ContentValues cv = new ContentValues();
        for (int i = 0; i < daysArr.size(); i++) {
            cv.put(tableClnColor, daysArr.get(i));

            // insert all four days to sqlite table
            mySQLiteHelper.getWritableDatabase().insert(
                    MySQLiteOpenHelper.TBL_NOTIFICATION_COLOR, null, cv);
        }

    }

    private List<Long> addDaysFourRed(long firstRed) {

        List<Long> days = new ArrayList<Long>();
        days.add(firstRed);

        for (int i = 1; i < 4; i++) {
            firstRed += (1 * 24 * 60 * 60 * 1000);
            days.add(firstRed);

        }

        return days;

    }

    public void addOneDay(String tableClnColor, long lastDay) {

        lastDay += (1 * 24 * 60 * 60 * 1000);
        ContentValues cv = new ContentValues();
        cv.put(tableClnColor, lastDay);

        // insert all four days to sqlite table
        mySQLiteHelper.getWritableDatabase().insert(
                MySQLiteOpenHelper.TBL_NOTIFICATION_COLOR, null, cv);

    }

    // ------------------------------------------------------------------------------------

    // -----------insertDaysColorsYellow----------------------------------------------------------------------

    public void insertDaysColorsYellow(long time, String tableClnColor) {

        List<Long> daysArr = new ArrayList<Long>();
        daysArr = addDaysFourYellow(time);

        ContentValues cv = new ContentValues();
        for (int i = 0; i < daysArr.size(); i++) {
            cv.put(tableClnColor, daysArr.get(i));

            mySQLiteHelper.getWritableDatabase().insert(
                    MySQLiteOpenHelper.TBL_NOTIFICATION_COLOR, null, cv);

        }

    }

    public void updateDaysColorsYellow(long time, String tableClnColor) {

        List<Long> daysArr = new ArrayList<Long>();
        daysArr = addDaysFourYellow(time);

        ContentValues cv = new ContentValues();
        for (int i = 0; i < daysArr.size(); i++) {
            cv.put(tableClnColor, daysArr.get(i));

            mySQLiteHelper.getWritableDatabase().update(
                    MySQLiteOpenHelper.TBL_NOTIFICATION_COLOR, cv,
                    tableClnColor + " != 1", null);
            Log.d("daysArr", "" + daysArr.get(i));
        }

    }

    /**
     * update cln color yellow if yesh ketem beshiva nekym מעדכן את טבלת הצבעים
     * בעמודת צהוב אם ראתה כתם בשבעה נקיים
     *
     * @param time
     * @param tableClnColor
     */

    public int ifIsLastShivaNekym() {

        int lastId = 0;
        String selectQuery = "SELECT "
                + MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_ID + " FROM "
                + MySQLiteOpenHelper.TBL_NOTIFICATION_COLOR + " WHERE "
                + MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_YELLOW + " !=1 "
                + " ORDER BY " + MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_ID
                + " DESC LIMIT 1";
        Log.d("selectQuery", selectQuery);
        Cursor c = mySQLiteHelper.getReadableDatabase().rawQuery(selectQuery,
                null);
        if (c.moveToFirst()) {
            do {

                lastId = c
                        .getInt(c
                                .getColumnIndex(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_ID));

            } while (c.moveToNext());
        } else
            Log.d("selecTcolor", "cursor is null");
        return lastId;

    }

    public long selectNextMonthOnaBenonit(int month) {

        long day = 0;

        String selectQuery = "SELECT "
                + MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_ONA_BENONIT
                + " FROM " + MySQLiteOpenHelper.TBL_MARKING_NEXT_MONTH
                + " WHERE "
                + MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_ONA_BENONIT
                + " !=1 " + " AND "
                + MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_ID + " = " + month;

        Log.d("selectQuery", selectQuery);
        Cursor c = mySQLiteHelper.getReadableDatabase().rawQuery(selectQuery,
                null);
        if (c.moveToFirst()) {
            do {

                day = c.getLong(c
                        .getColumnIndex(MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_ONA_BENONIT));

            } while (c.moveToNext());
        } else
            Log.d("selecTcolor", "cursor is null");
        return day;

    }

    public long selectNextMonthYomHahodesh(int month) {

        long day = 0;

        String selectQuery = "SELECT "
                + MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_YOM_HAHODESH
                + " FROM " + MySQLiteOpenHelper.TBL_MARKING_NEXT_MONTH
                + " WHERE "
                + MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_YOM_HAHODESH
                + " !=1 " + " AND "
                + MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_ID + " = " + month;

        Log.d("selectQuery", selectQuery);
        Cursor c = mySQLiteHelper.getReadableDatabase().rawQuery(selectQuery,
                null);
        if (c.moveToFirst()) {
            do {

                day = c.getLong(c
                        .getColumnIndex(MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_YOM_HAHODESH));

            } while (c.moveToNext());
        } else
            Log.d("selecTcolor", "cursor is null");
        return day;

    }

    public void updateYellowIf(long time, String tableClnColor) {
        List<Long> daysArr = new ArrayList<Long>();
        int firstYellowDay = 0;
        daysArr = addDaysFourYellow(time);
        String selectQuery = "SELECT "
                + MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_ID + " FROM "
                + MySQLiteOpenHelper.TBL_NOTIFICATION_COLOR + " WHERE "
                + tableClnColor + " !=1 " + " ORDER BY "
                + MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_ID + " ASC LIMIT 1";
        Log.d("selectQuery", selectQuery);
        Cursor c = mySQLiteHelper.getReadableDatabase().rawQuery(selectQuery,
                null);
        if (c.moveToFirst()) {
            do {

                firstYellowDay = c
                        .getInt(c
                                .getColumnIndex(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_ID));

            } while (c.moveToNext());
        } else
            Log.d("selecTcolor", "cursor is null");

        ContentValues cv = new ContentValues();
        for (int i = 0; i < daysArr.size(); i++) {
            cv.put(tableClnColor, daysArr.get(i));
            mySQLiteHelper.getWritableDatabase().update(
                    MySQLiteOpenHelper.TBL_NOTIFICATION_COLOR,
                    cv,
                    MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_ID + " = "
                            + firstYellowDay, null);
            firstYellowDay++;
        }
    }

    private List<Long> addDaysFourYellow(long firstRed) {

        List<Long> days = new ArrayList<Long>();
        firstRed += (1 * 24 * 60 * 60 * 1000);
        days.add(firstRed);

        for (int i = 1; i < 7; i++) {
            firstRed += (1 * 24 * 60 * 60 * 1000);
            days.add(firstRed);

        }

        return days;

    }

    public ArrayList<Long> selectFromRedClnLong(String clnName) {

        String selectQuery = "SELECT " + clnName + " FROM "
                + MySQLiteOpenHelper.TBL_NOTIFICATION_COLOR;

        Log.d("deal get", selectQuery.toString());
        ArrayList<Long> temp = new ArrayList<Long>();

        Cursor c = mySQLiteHelper.getReadableDatabase().rawQuery(selectQuery,
                null);
        if (c.moveToFirst()) {
            do {

                temp.add(c.getLong(c.getColumnIndex(clnName)));

            } while (c.moveToNext());
        } else
            Log.d("select tbl color", "cursor is null");
        return temp;
    }

    // ------------------------------------------------------------------------------------

    // select from tbl color with parameter clnName
    public ArrayList<String> selectFromRedCln(String clnName) {

        String selectQuery = "SELECT " + clnName + " FROM "
                + MySQLiteOpenHelper.TBL_NOTIFICATION_COLOR;

        Log.d("deal get", selectQuery.toString());
        ArrayList<Long> temp = new ArrayList<Long>();

        Cursor c = mySQLiteHelper.getReadableDatabase().rawQuery(selectQuery,
                null);
        if (c.moveToFirst()) {
            do {

                temp.add(c.getLong(c.getColumnIndex(clnName)));

            } while (c.moveToNext());
        } else
            Log.d("select tbl color", "cursor is null");
        ArrayList<String> markin = new ArrayList<String>();

        for (int i = 0; i < temp.size(); i++) {
            Timestamp stamp = new Timestamp(temp.get(i));
            Date date = new Date(stamp.getTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy");
            markin.add(simpleDateFormat.format(date));
            Log.d("SimpleDateFormat", markin.get(i));

        }

        return markin;

    }

    public String convertLongDateToString(long timeStamp) {

        String dateStaring = "";
        Timestamp stamp = new Timestamp(timeStamp);
        Date date = new Date(stamp.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy");
        dateStaring = simpleDateFormat.format(date);
        return dateStaring;

    }

    public String convertMonth(String allDayGrid) {
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date d = null;
        try {
            d = sdf.parse(allDayGrid);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sdf.applyPattern("d-M-yyyy");
        date = sdf.format(d);
        Log.d("convertDate", date);
        return date;

    }

    // יצירת טבלאות כל חודש
    public void createTable(String tableCreateQuery, String oldTableName,
                            String newTableName) {
        String mikReplace = tableCreateQuery
                .replace(oldTableName, newTableName);
        mySQLiteHelper.getWritableDatabase().execSQL(mikReplace);
    }

    public void updateBooleans(String tableClnBool, String bool) {
        ContentValues cv = new ContentValues();
        cv.put(tableClnBool, bool);

        mySQLiteHelper.getWritableDatabase().update(
                MySQLiteOpenHelper.TBL_BOOLEAN, cv, null, null);

    }

    public void insertBooleans(String tableClnBool, String bool) {

        ContentValues cv = new ContentValues();
        cv.put(tableClnBool, bool);

        mySQLiteHelper.getWritableDatabase().insert(
                MySQLiteOpenHelper.TBL_BOOLEAN, null, cv);
        Log.d("in", "x");
    }

    public boolean selectBooleans(String clnBool) {

        ArrayList<String> retrn = new ArrayList<String>();
        boolean contentBool;
        String selectQuery = "SELECT " + clnBool + " FROM "
                + MySQLiteOpenHelper.TBL_BOOLEAN;

        Cursor c = mySQLiteHelper.getReadableDatabase().rawQuery(selectQuery,
                null);
        if (c.moveToFirst()) {
            do {

                retrn.add(c.getString(c.getColumnIndex(clnBool)));

            } while (c.moveToNext());
        } else
            Log.d("select tbl boolean", "cursor is null");

        contentBool = Boolean.valueOf(retrn.get(0));

        return contentBool;

    }

    /**
     * check if data base empty
     *
     * @param cln
     *            name boolean
     * @return value from database
     *
     */
    public ArrayList<String> selectBooleansString(String clnBool) {

        // String retrn;
        ArrayList<String> retrn = new ArrayList<String>();
        String selectQuery = "SELECT " + clnBool + " FROM "
                + MySQLiteOpenHelper.TBL_BOOLEAN;

        Cursor c = mySQLiteHelper.getReadableDatabase().rawQuery(selectQuery,
                null);
        if (c.moveToFirst()) {
            do {

                retrn.add(c.getString(c.getColumnIndex(clnBool)));

            } while (c.moveToNext());
        } else
            Log.d("select tbl boolean", "cursor is null");

        return retrn;

    }

    /**
     * @param day
     *
     *            + 30 days or 29
     * @return
     */

    public void addDayToOnaBenonit(int month, long day) {
        day += (1 * 24 * 60 * 60 * 1000l) * 30;
        ContentValues cv = new ContentValues();
        // insert the first day from four days to mark the next Day Of Month in
        // next
        // month
        cv.put(MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_ONA_BENONIT, day);
        cv.put(MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_ID, month);
        mySQLiteHelper.getWritableDatabase().insert(
                MySQLiteOpenHelper.TBL_MARKING_NEXT_MONTH, null, cv);

    }

    public void insertMimve(long day) {
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_BLUE, day);
        mySQLiteHelper.getWritableDatabase().insert(
                MySQLiteOpenHelper.TBL_NOTIFICATION_COLOR, null, cv);

    }

    public void markingtTheDayOfMonth(int month, int monthLength, long day) {
        Log.d("dayxx", "" + convertLongDateToString(day));
        if (monthLength == 30)
            day += (1 * 24 * 60 * 60 * 1000l) * 30;
        else
            day += (30 * 24 * 60 * 60 * 1000l) + (1 * 24 * 60 * 60 * 1000l);
        Log.d("dayxx", "" + convertLongDateToString(day));
        ContentValues cv = new ContentValues();
        // insert the first day from four days to mark the next Day Of Month in
        // next
        // month
        cv.put(MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_YOM_HAHODESH, day);
        cv.put(MySQLiteOpenHelper.CLN_MARKING_NEXT_MONTH_ID, month);
        mySQLiteHelper.getWritableDatabase().insert(
                MySQLiteOpenHelper.TBL_MARKING_NEXT_MONTH, null, cv);
    }

    @SuppressWarnings("deprecation")
    public int getIntegerMonth(long timestampFull) {
        String month = convertLongDateToString(timestampFull);
        Timestamp stamp = new Timestamp(timestampFull);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(stamp);
        calendar.get(Calendar.MONTH);
        Log.d("substring", "" + calendar.get(Calendar.MONTH));
        Log.d("substring", month);
        month = month.substring(1, 3);
        Log.d("substring", month);
        int x = 0;
        return x;

    }

    // insert to table vesatot from select algoritem
    public void insertToVsatot(long firstDay) {
        // firstDay += (1 * 24 * 60 * 60 * 1000l) * 30;
        Log.d("shimi", convertLongDateToString(firstDay));
        ContentValues cv = new ContentValues();
        // insert the first day from four days to vesatot table
        cv.put(MySQLiteOpenHelper.CLN_MARKING_VESATOT_FIRST, firstDay);
        mySQLiteHelper.getWritableDatabase().insert(
                MySQLiteOpenHelper.TBL_MARKING_VESATOT, null, cv);

    }

}
