package com.tahara.amarshimi.logic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by amarshimi on 2/22/2015.
 */
public class MySQLiteOpenHelper  extends SQLiteOpenHelper {

    public final static String DB_NAME = "nida.db";
    private final static int DB_VERSION = 3;

    // table notifications time ------------------------------

    public final static String TBL_NOTIFICATION = "notifications";
    public final static String CLN_NOTIFICATION_ID = "id";
    public final static String CLN_NOTIFICATION_DATE = "date";
    public final static String CLN_NOTIFICATION_HOUR = "hour";

    // ----------------------------------------------------

    // table notifications color of dayes ------------------------------

    public final static String TBL_NOTIFICATION_COLOR = "notifications_color";
    public final static String CLN_NOTIFICATION_COLOR_ID = "id";
    public final static String CLN_NOTIFICATION_COLOR_RED = "red";
    public final static String CLN_NOTIFICATION_COLOR_YELLOW = "yellow";
    public final static String CLN_NOTIFICATION_COLOR_BLUE = "blue";

    // ----------------------------------------------------

    // table marking the next month ------------------------------

    public final static String TBL_MARKING_NEXT_MONTH = "next_month";
    public final static String CLN_MARKING_NEXT_MONTH_ID = "id";
    public final static String CLN_MARKING_NEXT_MONTH_ONA_BENONIT = "ona_benonit";
    public final static String CLN_MARKING_NEXT_MONTH_YOM_HAHODESH = "yom_hahodesh";
    public final static String CLN_MARKING_NEXT_MONTH_HAFLAGA = "haflaga";
    public final static String CLN_MARKING_NEXT_MONTH_VESET_KAVUA = "veset_kavua";

    // ----------------------------------------------------

    // table marking the vesatot ------------------------------

    public final static String TBL_MARKING_VESATOT = "vesatot";
    public final static String CLN_MARKING_VESATOT_FIRST_ID = "id";
    public final static String CLN_MARKING_VESATOT_FIRST = "first";

    // ----------------------------------------------------

    // table mikvaot ------------------------------

    public final static String TBL_MIKVAOT = "mikvaot";
    public final static String CLN_MIKVAOT_ID = "id";
    public final static String CLN_MIKVAOT_NAME = "name";
    public final static String CLN_MIKVAOT_CITY = "city";
    public final static String CLN_MIKVAOT_ADDRESS = "address";
    public final static String CLN_MIKVAOT_LONGTI = "longti";
    public final static String CLN_MIKVAOT_LATI = "lati";

    // ----------------------------------------------------

    // table booleans ------------------------------

    public final static String TBL_BOOLEAN = "booleans";
    public final static String CLN_BOOLEAN_ID = "id";
    public final static String CLN_BOOLEAN_IS_LAST_RED = "islastred";
    public final static String CLN_BOOLEAN_IS_LAST_SHIVA_NEKYM = "islastshivanekym";
    public final static String CLN_BOOLEAN_IS_SHIVA_NEKYM = "isshivanekym";
    public final static String CLN_BOOLEAN_IS_MIKVE = "ismikve";
    public final static String CLN_IS_RED = "isred";
    public final static String CLN_IS_STAM = "isstam";

    // ----------------------------------------------------
    // create table booleans ------------------------------

    private static final String TBL_BOOLEAN_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TBL_BOOLEAN
            + "("
            + CLN_BOOLEAN_IS_LAST_RED
            + " TEXT DEFAULT 'false' , "
            + CLN_BOOLEAN_IS_LAST_SHIVA_NEKYM
            + " TEXT DEFAULT 'false' , "
            + CLN_BOOLEAN_IS_MIKVE
            + " TEXT DEFAULT 'false' , "
            + CLN_IS_RED
            + " TEXT DEFAULT 'false' , "
            + CLN_IS_STAM
            + " TEXT , "
            + CLN_BOOLEAN_IS_SHIVA_NEKYM
            + " TEXT DEFAULT 'false' )";
    private final String TBL_BOOLEAN_DROP = "DROP TABLE IF EXISTS "
            + TBL_NOTIFICATION;

    // --------------------------------------------------------------------------------------

    // create table mikvaot ------------------------------

    private static final String TBL_MIKVAOT_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TBL_MIKVAOT
            + "("
            + CLN_MIKVAOT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CLN_MIKVAOT_NAME
            + " TEXT DEFAULT 'false' , "
            + CLN_MIKVAOT_CITY
            + " TEXT , "
            + CLN_MIKVAOT_ADDRESS
            + " TEXT , "
            + CLN_MIKVAOT_LONGTI
            + " INTEGER , "
            + CLN_MIKVAOT_LATI + " INTEGER )";
    private final String TBL_MIKVAOT_DROP = "DROP TABLE IF EXISTS "
            + TBL_NOTIFICATION;

    // --------------------------------------------------------------------------------------

    // create table notification time ------------------------------

    private final String TBL_NOTIFICATION_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TBL_NOTIFICATION
            + "("
            + CLN_NOTIFICATION_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CLN_NOTIFICATION_DATE
            + " INTEGER , " + CLN_NOTIFICATION_HOUR + " INTEGER )";
    private final String TBL_NOTIFICATION_DROP = "DROP TABLE IF EXISTS "
            + TBL_NOTIFICATION;

    // --------------------------------------------------------------------------------------

    // create table notification color-----------------------------------

    public final String TBL_NOTIFICATION_COLOR_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TBL_NOTIFICATION_COLOR
            + "("
            + CLN_NOTIFICATION_COLOR_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CLN_NOTIFICATION_COLOR_RED
            + " TEXT  , "
            + CLN_NOTIFICATION_COLOR_YELLOW
            + " TEXT  , "
            + CLN_NOTIFICATION_COLOR_BLUE + " TEXT )";
    private final String TBL_NOTIFICATION_COLOR_DROP = "DROP TABLE IF EXISTS "
            + TBL_NOTIFICATION_COLOR;

    // --------------------------------------------------------------------------------------

    // create table marking the next month----------------------

    public final String TBL_MARKING_NEXT_MONTH_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TBL_MARKING_NEXT_MONTH
            + "("
            + CLN_MARKING_NEXT_MONTH_ID
            + " TEXT ,"
            + CLN_MARKING_NEXT_MONTH_ONA_BENONIT
            + " TEXT , "
            + CLN_MARKING_NEXT_MONTH_YOM_HAHODESH
            + " TEXT , "
            + CLN_MARKING_NEXT_MONTH_HAFLAGA
            + " TEXT , "
            + CLN_MARKING_NEXT_MONTH_VESET_KAVUA + " TEXT )";
    private final String TBL_MARKING_NEXT_MONTH_DROP = "DROP TABLE IF EXISTS "
            + TBL_NOTIFICATION_COLOR;

    // --------------------------------------------------------------------------------------

    // create table marking the vesatot----------------------

    public final String TBL_MARKING_VESATOT_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TBL_MARKING_VESATOT
            + "("
            + CLN_MARKING_VESATOT_FIRST_ID
            + " TEXT ,"
            + CLN_MARKING_VESATOT_FIRST
            + " TEXT )";
    private final String TBL_MARKING_VESATOT_DROP = "DROP TABLE IF EXISTS "
            + TBL_NOTIFICATION_COLOR;

    // --------------------------------------------------------------------------------------

    // array create
    // tables-------------------------------------------------------------------
    private String[] TABLES_CREATE = { TBL_NOTIFICATION_CREATE,
            TBL_NOTIFICATION_COLOR_CREATE, TBL_MARKING_NEXT_MONTH_CREATE,
            TBL_MARKING_VESATOT_CREATE, TBL_MIKVAOT_CREATE,TBL_BOOLEAN_CREATE };
    private String[] TABLES_DROP = { TBL_NOTIFICATION_DROP,
            TBL_NOTIFICATION_COLOR_DROP, TBL_MARKING_NEXT_MONTH_DROP,
            TBL_MARKING_VESATOT_DROP, TBL_MIKVAOT_DROP,TBL_BOOLEAN_DROP };

    // ------------------------------------------------------------------------------------------------

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // onCreate(getReadableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        for (String create : TABLES_CREATE) {
            Log.d("onUpgrade", create);
            db.execSQL(create);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        for (String drop : TABLES_DROP) {
            Log.d("onUpgrade", drop);
            db.execSQL(drop);
        }
        // create new tables
        onCreate(db);
    }

}
