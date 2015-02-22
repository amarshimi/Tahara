package com.tahara.amarshimi.tahara;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/*import net.sourceforge.zmanim.ZmanimCalendar;
import net.sourceforge.zmanim.util.GeoLocation;
import android.R.integer;*/
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tahara.amarshimi.date.HebrewDate;
import com.tahara.amarshimi.date.RegularHebrewDate;
import com.tahara.amarshimi.logic.CalculateNidaDayes;
import com.tahara.amarshimi.logic.MySQLiteOpenHelper;
import com.tahara.amarshimi.logic.NidaDAO;
import com.tahata.amarshimi.tahara.R;


/**
 * Created by amarshimi on 2/22/2015.
 */

public class JewishCalendar extends Fragment implements View.OnClickListener {

    private static final String tag = "SimpleCalendarViewActivity";

    private ImageView calendarToJournalButton;
    private Button selectedDayMonthYearButton;
    private Button currentMonth;
    private ImageView prevMonth;
    private ImageView nextMonth;
    private GridView calendarView;
    private GridCellAdapter adapter;
    TextView event , yomHahodesh,onaBenonit , haflaga;
    private Calendar _calendar;
    private int month, year;
    private final DateFormat dateFormatter = new DateFormat();
    private static final String dateTemplate = "MMMM yyyy";
    public static final String PREFS_NAME = "toldotru";

    public String timeFormat;
    static final int DATE_DIALOG_ID = 3;
    int x;
    int y;
    int sevenDay;
    List<Integer> xx = new ArrayList<Integer>();
    List<String> redDays = new ArrayList<String>();
    List<String> yellowDays = new ArrayList<String>();
    List<String> realYellowDay = new ArrayList<String>();
    List<String> blueDays = new ArrayList<String>();
    int[] yy = new int[7];
    Button btn;
    Button btn2;
    Button btnzmanim;
    int tempPosition = 0;
    // boolean bollPosition = false;
    boolean isRed;
    boolean isLastRed;
    boolean isSivaNekym;
    boolean isLastDayOfShivaNekym;
    boolean ssss = false;
    List<Long> fullDateGeorgi = new ArrayList<Long>();
    List<Long> redDaysLong = new ArrayList<Long>();
    List<Long> yellowDaysLong = new ArrayList<Long>();
    CalculateNidaDayes nidaDayes;
    HebrewDate hebrewDate;
    int tempToXx = 0;
    int anotherDay = 0;
    NidaDAO nidaDAO;
    int checkGrey = 0;
    ArrayList<String> AllDaysForGrid = new ArrayList<String>();
    View rootView;
    //ZmanimCalendar zc;
    RegularHebrewDate regularHebrewDate;
    Calendar cal;
    int Comonth ;

    /** Called when the activity is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        rootView = inflater.inflate(R.layout.main, container, false);
        regularHebrewDate = new RegularHebrewDate();

        new MySQLiteOpenHelper(getActivity());
        nidaDAO = new NidaDAO(getActivity());
        if (nidaDAO.selectBooleansString(MySQLiteOpenHelper.CLN_IS_RED).size() <= 0) {
            nidaDAO.insertBooleans(MySQLiteOpenHelper.CLN_IS_STAM, "false");
        }
        // nidaDAO.insertBooleans(MySQLiteOpenHelper.CLN_IS_STAM, "false");
		/*
		 * nidaDAO.insertBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_RED,
		 * "false");
		 * nidaDAO.insertBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_SHIVA_NEKYM,
		 * "false");
		 * nidaDAO.insertBooleans(MySQLiteOpenHelper.CLN_IS_LAST_SHIVA_NEKYM,
		 * "false");
		 */

        //isRed = false;
        //isLastRed = false;
        //isSivaNekym = false;
        //isLastDayOfShivaNekym = false;
		/*isRed = nidaDAO.selectBooleans(MySQLiteOpenHelper.CLN_IS_RED);
		isLastRed = nidaDAO
				.selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_RED);
		isSivaNekym = nidaDAO
				.selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_SHIVA_NEKYM);
		isLastDayOfShivaNekym = nidaDAO
				.selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_SHIVA_NEKYM);*/
        cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        Comonth = cal.get(Calendar.MONTH)+1;
        x = 2;
        y = 10;
        _calendar = Calendar.getInstance(Locale.getDefault());
        month = _calendar.get(Calendar.MONTH) + 1;
        year = _calendar.get(Calendar.YEAR);
        Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: "
                + year);

		/*redDays = nidaDAO
				.selectFromRedCln(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_RED);

		for (int i = 0; i < redDays.size(); i++) {
			Log.d("redDays", redDays.get(i));

		}
		yellowDays = nidaDAO
				.selectFromRedCln(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_YELLOW);

		for (int i = 0; i < yellowDays.size(); i++) {
			Log.d("yellowDays", yellowDays.get(i));

		}*/
        // selectedDayMonthYearButton = (Button)
        // this.findViewById(R.id.selectedDayMonthYear);
        // selectedDayMonthYearButton.setText("Selected: ");
        hebrewDate = new HebrewDate();
        String hebrewdate = hebrewDate.getHebrewDateAsString();
        prevMonth = (ImageView) rootView.findViewById(R.id.prevMonth);
        prevMonth.setOnClickListener(this);

        yomHahodesh = (TextView)rootView.findViewById(R.id.txthodesh);
        onaBenonit = (TextView)rootView.findViewById(R.id.txtonabenonit);
        haflaga = (TextView)rootView.findViewById(R.id.txthaflaga);

        onaBenonit.setText(nidaDAO.convertLongDateToString(nidaDAO.selectNextMonthOnaBenonit(Comonth)));
        yomHahodesh.setText(nidaDAO.convertLongDateToString(nidaDAO.selectNextMonthYomHahodesh(Comonth)));

        currentMonth = (Button) rootView.findViewById(R.id.currentMonth);
        currentMonth.setText(hebrewdate);
        currentMonth.setOnClickListener(this);

        nextMonth = (ImageView) rootView.findViewById(R.id.nextMonth);
        nextMonth.setOnClickListener(this);

        calendarView = (GridView) rootView.findViewById(R.id.calendar);
        // final CalculateNidaDayes calculateNidaDayes =new
        // CalculateNidaDayes();

        nidaDayes = new CalculateNidaDayes();
        x = nidaDayes.markingtTheOnaBenonit(11, 10);

        SharedPreferences settings = getActivity().getSharedPreferences(
                PREFS_NAME, 0);

        String lat = settings.getString("lat", "40.714352800000000000"); // 59.43695
        String lng = settings.getString("lng", "-74.005973100000000000"); // 24.75352
        // String location = settings.getString("location", "Tallinn, Estonia");
        timeFormat = settings.getString("timeFormat", "24h");

        // date today
        // int cyear = c.get(Calendar.YEAR);
        // int cmonth = c.get(Calendar.MONTH);
        // int cday = c.get(Calendar.DAY_OF_MONTH);

        // zmanim
        // String locationName = location;
        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(lng);
        double elevation = 0; // optional elevation
        TimeZone timeZone = TimeZone.getDefault();

      /*  GeoLocation geoLocation = new GeoLocation("shimi", latitude, longitude,
                elevation, timeZone);

        zc = new ZmanimCalendar(geoLocation);*/

        // createNotificationForShivaNekym();

        new RetrieveCalendarView().execute();
		/*btnzmanim = (Button) rootView.findViewById(R.id.btnzmanim);
		btnzmanim.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});*/
        return rootView;
    }

    private class RetrieveCalendarView extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {

            isRed = nidaDAO.selectBooleans(MySQLiteOpenHelper.CLN_IS_RED);
            isLastRed = nidaDAO
                    .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_RED);
            isSivaNekym = nidaDAO
                    .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_SHIVA_NEKYM);
            isLastDayOfShivaNekym = nidaDAO
                    .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_SHIVA_NEKYM);
            yellowDays = nidaDAO
                    .selectFromRedCln(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_YELLOW);
            redDays = nidaDAO
                    .selectFromRedCln(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_RED);
            blueDays = nidaDAO
                    .selectFromRedCln(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_BLUE);
            adapter = new GridCellAdapter(getActivity(),
                    R.id.calendar_day_gridcell, month, year);
            adapter.notifyDataSetChanged();

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(getActivity(), null,
                    "...טוען נתונים אנא המתן");

        }

        @Override
        protected void onPostExecute(Void result) {
            // Initialised
            super.onPostExecute(result);
            calendarView.setAdapter(adapter);
            progress.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    /**
     * // שליחת נוטיפיקיישן לבדיקת שבעה נקיים public void
     * createNotificationForShivaNekym() { // Prepare intent which is triggered
     * if the // notification is selected Intent intent = new Intent(this,
     * JewishCalendar.class); PendingIntent pIntent =
     * PendingIntent.getActivity(this, 0, intent, 0);
     *
     * // Build notification // Actions are just fake Notification noti = new
     * Notification.Builder(this) .setContentTitle("בדיקת שבעה נקיים :)")
     * .setContentText("אפליקציית טהרת הבית שלחה לך תזכורת לבדוק ")
     * .setSmallIcon(R.drawable.ic_launcher) .setContentIntent(pIntent)
     *
     * .addAction(R.drawable.ic_launcher, "היום הוא יום 1 לבדיקת שבעה נקיים",
     * pIntent).build(); NotificationManager notificationManager =
     * (NotificationManager) getSystemService(NOTIFICATION_SERVICE); // hide the
     * notification after its selected noti.flags =
     * Notification.FLAG_AUTO_CANCEL;
     *
     * notificationManager.notify(0, noti);
     *
     * }
     *
     * // שליחת נוטיפיקיישן לטבילה במקווה public void
     * createNotificationForMikve() { // Prepare intent which is triggered if
     * the // notification is selected Intent intent = new Intent(this,
     * SimpleJewishCalendarActivity.class); PendingIntent pIntent =
     * PendingIntent.getActivity(this, 0, intent, 0);
     *
     * // Build notification // Actions are just fake Notification noti = new
     * Notification.Builder(this) .setContentTitle("טבילה במקווה :)")
     * .setContentText("למציאת המקווה הקרוב כנסי לטאב מקוואות ")
     * .setSmallIcon(R.drawable.ic_launcher) .setContentIntent(pIntent)
     *
     * .addAction(R.drawable.ic_launcher, "היום הוא יום 1 לבדיקת שבעה נקיים",
     * pIntent).build(); NotificationManager notificationManager =
     * (NotificationManager) getSystemService(NOTIFICATION_SERVICE); // hide the
     * notification after its selected noti.flags =
     * Notification.FLAG_AUTO_CANCEL;
     *
     * notificationManager.notify(0, noti);
     *
     * }
     *
     * // שליחת נוטיפיקיישן להפסק טהרה public void
     * createNotificationForHefsekTahara() { // Prepare intent which is
     * triggered if the // notification is selected Intent intent = new
     * Intent(this, SimpleJewishCalendarActivity.class); PendingIntent pIntent =
     * PendingIntent.getActivity(this, 0, intent, 0);
     *
     * // Build notification // Actions are just fake Notification noti = new
     * Notification.Builder(this) .setContentTitle("הפסק טהרה :)")
     * .setContentText("את יכולה לנסות היום הפסק טהרה ")
     * .setSmallIcon(R.drawable.ic_launcher).setContentIntent(pIntent)
     *
     * .addAction(R.drawable.ic_launcher, "לונג טקסט", pIntent) .build();
     * NotificationManager notificationManager = (NotificationManager)
     * getSystemService(NOTIFICATION_SERVICE); // hide the notification after
     * its selected noti.flags = Notification.FLAG_AUTO_CANCEL;
     *
     * notificationManager.notify(0, noti);
     *
     * }
     */
    /**
     *
     * @param month
     * @param year
     */
    private void setGridCellAdapterToDate(int month, int year) {

        adapter = new GridCellAdapter(getActivity(),
                R.id.calendar_day_gridcell, month, year);
        _calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
        currentMonth.setText(DateFormat.format(dateTemplate,
                _calendar.getTime()));
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);

        new RetrieveCalendarView().execute();
    }

    @Override
    public void onClick(View v) {
        if (v == prevMonth) {
            fullDateGeorgi.clear();
            if (month <= 1) {
                month = 12;
                year--;

            } else {
                month--;
            }
            Log.d(tag, "Setting Prev Month in GridCellAdapter: " + "Month: "
                    + month + " Year: " + year);
            setGridCellAdapterToDate(month, year);

        }
        if (v == nextMonth) {
            fullDateGeorgi.clear();
            if (month > 11) {
                month = 1;
                year++;
            } else {
                month++;
            }
            Log.d(tag, "Setting Next Month in GridCellAdapter: " + "Month: "
                    + month + " Year: " + year);
            setGridCellAdapterToDate(month, year);
        }
        if (v == currentMonth) {
            getActivity().showDialog(DATE_DIALOG_ID);

        }

    }

	/*
	 * // Creating dialog
	 *
	 * @Override protected Dialog onCreateDialog(int id) { Calendar c =
	 * Calendar.getInstance(); switch (id) { case DATE_DIALOG_ID: // return new
	 * DatePickerDialog(this, mDateSetListener, cyear, // cmonth, cday); //
	 * return new MonthYearDateSlider(this, mDateSetListener, // cyear, cmonth,
	 * cday // c);
	 *
	 * } return null; }
	 */

    // define the listener which is called once a user selected the date.
	/*
	 * private DateSlider.OnDateSetListener mDateSetListener = new
	 * DateSlider.OnDateSetListener() {
	 *
	 * public void onDateSet(DateSlider view, Calendar selectedDate) { //
	 * Toast.makeText(CalendarViewActivity.this, // selectedDate.toString(),
	 * Toast.LENGTH_SHORT).show(); month = selectedDate.get(Calendar.MONTH) + 1;
	 * year = selectedDate.get(Calendar.YEAR);
	 * setGridCellAdapterToDate(selectedDate.get(Calendar.MONTH) + 1,
	 * selectedDate.get(Calendar.YEAR));
	 *
	 * } };
	 */

    @Override
    public void onDestroy() {
        Log.d(tag, "Destroying View ...");
        super.onDestroy();
    }

    // ///////////////////////////////////////////////////////////////////////////////////////
    // Inner Class
    public class GridCellAdapter extends BaseAdapter implements View.OnClickListener {
        private static final String tag = "GridCellAdapter";
        private final Context _context;

        private final List<String> list;
        private static final int DAY_OFFSET = 1;
        private final String[] weekdays = new String[] { "Sun", "Mon", "Tue",
                "Wed", "Thu", "Fri", "Sat" };
        private final String[] months = { "January", "February", "March",
                "April", "May", "June", "July", "August", "September",
                "October", "November", "12" };
        private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
                31, 30, 31 };
        private final int month, year;
        private int daysInMonth, prevMonthDays;
        private int currentDayOfMonth;
        private int currentWeekDay;
        private Button gridcell;
        private TextView num_events_per_day;
        private final HashMap eventsPerMonthMap;
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat(
                "dd-MMM-yyyy");

        // Days in Current Month
        public GridCellAdapter(Context context, int textViewResourceId,
                               int month, int year) {
            super();
            this._context = context;
            this.list = new ArrayList<String>();
            this.month = month;
            this.year = year;

            Log.d(tag, "==> Passed in Date FOR Month: " + month + " "
                    + "Year: " + year);
            Calendar calendar = Calendar.getInstance();

            setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
            setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
            Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
            Log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
            Log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());

            // Print Month
            printMonth(month, year);

            // Find Number of Events
            eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
        }

        private String getMonthAsString(int i) {
            return months[i];
        }

        private String getWeekDayAsString(int i) {
            return weekdays[i];
        }

        private int getNumberOfDaysOfMonth(int i) {
            return daysOfMonth[i];
        }

        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        public void yeshShinuy() {
            final Dialog settingsDialog = new Dialog(getActivity());
            settingsDialog.setContentView(R.layout.image_layout);
            settingsDialog.setTitle("yesh shinuy ok!!!");
            btn = (Button) settingsDialog.findViewById(R.id.button1);
            btn2 = (Button) settingsDialog.findViewById(R.id.button2);
            btn2.setVisibility(View.GONE);
            btn.setText("close");
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    settingsDialog.dismiss();
                }
            });
            settingsDialog.show();

        }

        /**
         * Prints Month
         *
         * @param mm
         * @param yy
         */
        private void printMonth(int mm, int yy) {
            Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
            // The number of days to leave blank at
            // the start of this month.
            int trailingSpaces = 0;
            int leadSpaces = 0;
            int daysInPrevMonth = 0;
            int prevMonth = 0;
            int prevYear = 0;
            int nextMonth = 0;
            int nextYear = 0;

            int currentMonth = mm - 1;
            String currentMonthName = getMonthAsString(currentMonth);
            daysInMonth = getNumberOfDaysOfMonth(currentMonth);

            Log.d(tag, "Current Month: " + " " + currentMonthName + " having "
                    + daysInMonth + " days.");

            // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
            GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
            Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

            if (currentMonth == 11) {
                prevMonth = currentMonth - 1;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 0;
                prevYear = yy;
                nextYear = yy + 1;
                Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:"
                        + prevMonth + " NextMonth: " + nextMonth
                        + " NextYear: " + nextYear);
            } else if (currentMonth == 0) {
                prevMonth = 11;
                prevYear = yy - 1;
                nextYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 1;
                Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:"
                        + prevMonth + " NextMonth: " + nextMonth
                        + " NextYear: " + nextYear);
            } else {
                prevMonth = currentMonth - 1;
                nextMonth = currentMonth + 1;
                nextYear = yy;
                prevYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                Log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:"
                        + prevMonth + " NextMonth: " + nextMonth
                        + " NextYear: " + nextYear);
            }

            // Compute how much to leave before before the first day of the
            // month.
            // getDay() returns 0 for Sunday.
            int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
            trailingSpaces = currentWeekDay;

            Log.d(tag, "Week Day:" + currentWeekDay + " is "
                    + getWeekDayAsString(currentWeekDay));
            Log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
            Log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);

            if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 2) {
                ++daysInMonth;
            }
            if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 3) {
                ++daysInPrevMonth;
            }
            // Trailing Month days
            for (int i = 0; i < trailingSpaces; i++) {
                Log.d(tag,
                        "PREV MONTH:= "
                                + prevMonth
                                + " => "
                                + getMonthAsString(prevMonth)
                                + " "
                                + String.valueOf((daysInPrevMonth
                                - trailingSpaces + DAY_OFFSET)
                                + i));
                list.add(String
                        .valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET)
                                + i)
                        + "-GREY"
                        + "-"
                        + getMonthAsString(prevMonth)
                        + "-"
                        + prevYear);
            }

            // Current Month Days
            String color = "";
            for (int i = 1; i <= daysInMonth; i++) {
                Log.d(currentMonthName, String.valueOf(i) + " "
                        + getMonthAsString(currentMonth) + " " + yy);
                Log.d("amar", "" + (i - 1) + "-" + mm + "-" + yy);

                color = "WHITE";
                if (i == getCurrentDayOfMonth()) {
                    color = "BLUE";
                }

                for (int j = 0; j < redDays.size(); j++) {
                    if (redDays.get(j).equals((i) + "-" + mm + "-" + yy)) {
                        color = "SHIMI";
                        // Log.d("xxxcc", redDays.get(i));

                    }
                }
                for (int j = 0; j < yellowDays.size(); j++) {
                    Log.d("yellowDayscolor", yellowDays.get(j) + "--->>"
                            + (i - 1) + "-" + mm + "-" + yy);
                    // Log.d("yellowDayscolor", "---->>"+redDays.get(i));
                    if (yellowDays.get(j).equals((i) + "-" + mm + "-" + yy)) {
                        color = "YELLOW";
                        // Log.d("xxxcc", redDays.get(i));

                    }
                }

				/*
				 * if (isJewishHolliday(i + "-" + mm + "-" + yy)) { color =
				 * "RED"; }
				 */
                list.add(String.valueOf(i) + "-" + color + "-"
                        + getMonthAsString(currentMonth) + "-" + yy);
            }

            // Leading Month days
            for (int i = 0; i < list.size() % 7; i++) {
                Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
                list.add(String.valueOf(i + 1) + "-GREY" + "-"
                        + getMonthAsString(nextMonth) + "-" + nextYear);
            }
        }

        /**
         * NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
         * ALL entries from a SQLite database for that month. Iterate over the
         * List of All entries, and get the dateCreated, which is converted into
         * day.
         *
         * @param year
         * @param month
         * @return
         */
        private HashMap findNumberOfEventsPerMonth(int year, int month) {
            HashMap map = new HashMap<String, Integer>();
            // DateFormat dateFormatter2 = new DateFormat();
            //
            // String day = dateFormatter2.format("dd", dateCreated).toString();
            //
            // if (map.containsKey(day))
            // {
            // Integer val = (Integer) map.get(day) + 1;
            // map.put(day, val);
            // }
            // else
            // {
            // map.put(day, 1);
            // }
            return map;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) _context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.calendar_day_gridcell, parent,
                        false);
            }
           /* Log.d("zmanim", String.valueOf(zc.getSunrise()));
            Log.d("zmanim", String.valueOf(zc.getShaahZmanisGra()));
            Log.d("zmanim", String.valueOf(zc.getMinchaGedola()));
            String s = String.valueOf(zc.getSunrise());
            s = s.substring(11, s.length() - 9);
            Log.d("zmanim", "shhhhh" + s);
            Log.d("zmanim", String.valueOf(zc.getAlosHashachar()));
            Log.d("zmanim", String.valueOf(zc.getAlos72()));*/
            // Get a reference to the Day gridcell
            gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);

            // gridcell.setOnClickListener(this);
            gridcell.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Toast.makeText(getActivity().getApplicationContext(),
                    // String.valueOf(zc.getSunset()), 1);
                    // Log.d("shkia", );

                    Log.d("corent", ""+Comonth);
                    Log.d("corent", ""+month);
                    Log.d("corent", ""+JewishCalendar.this.month);
                    if(Comonth==month){
                        if (!isRed) {
						/* isRed = */nidaDAO.updateBooleans(
                                    MySQLiteOpenHelper.CLN_IS_RED, "true");
                            isRed = nidaDAO
                                    .selectBooleans(MySQLiteOpenHelper.CLN_IS_RED);
                            final Dialog settingsDialog = new Dialog(getActivity());
                            settingsDialog.setContentView(R.layout.image_layout);
                            settingsDialog.setTitle("האם את רוצה לסמן ראייה?");
                            settingsDialog.setCancelable(false);
                            btn = (Button) settingsDialog
                                    .findViewById(R.id.button1);
                            // btb2 = כן
                            btn2 = (Button) settingsDialog
                                    .findViewById(R.id.button2);

                            btn.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    // TODO Auto-generated method stub

                                    // new RetrieveCalendarView().execute();
                                    settingsDialog.dismiss();
                                    nidaDAO.updateBooleans(
                                            MySQLiteOpenHelper.CLN_IS_RED, "false");
								/* isRed = false; */
                                    isRed = nidaDAO
                                            .selectBooleans(MySQLiteOpenHelper.CLN_IS_RED);

                                }
                            });
                            settingsDialog.show();

                            btn2.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    // TODO Auto-generated method stub
                                    HebrewDate hebrewDate = new HebrewDate();
                                    // CalculateNidaDayes calculateNidaDayes = new
                                    // CalculateNidaDayes();;
                                    nidaDAO.updateBooleans(
                                            MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_RED,
                                            "true");
								/* isLastRed = true; */
                                    isLastRed = nidaDAO
                                            .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_RED);
                                    redDays = nidaDAO
                                            .selectFromRedCln(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_RED);
                                    xx = nidaDayes.markingtTheFourDayes(
                                            hebrewDate.getLastDayOfHebrewMonth(),
                                            checkGrey);
                                    nidaDAO.insertDaysColorsRed(
                                            fullDateGeorgi.get(position + 2),

                                            MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_RED);

                                    Toast.makeText(
                                            getActivity(),
                                            ""
                                                    + hebrewDate.getHebrewMonth()
                                                    + hebrewDate
                                                    .getLastDayOfHebrewMonth(),
                                            Toast.LENGTH_SHORT).show();

                                    nidaDAO.insertToVsatot(fullDateGeorgi
                                            .get(position + 2));
                                    nidaDAO.markingtTheDayOfMonth(Comonth,
                                            hebrewDate.getLastDayOfHebrewMonth(month),
                                            fullDateGeorgi.get(position + 2));
                                    Log.d("month4", ""+month);
                                    nidaDAO.addDayToOnaBenonit(Comonth,fullDateGeorgi
                                            .get(position + 2));
                                    nidaDAO.getIntegerMonth(fullDateGeorgi
                                            .get(position + 2));

                                    // gridcell.setTag();

                                    new RetrieveCalendarView().execute();
                                    settingsDialog.dismiss();
                                }
                            });
                            // TODO Auto-generated method stub
						/*
						 *
						 * HebrewDate hDate = new HebrewDate();
						 * gridcell.setBackground
						 * (getResources().getDrawable(R.drawable.red));
						 */
                            // gridcell.setText(hDate.getHebrewDayAsString()/*+"\n"+hDate.getHebrewMonthAsString()*/);
                            // adapter.notifyDataSetChanged();

                            // Log.d("zmanim", String.valueOf(zc.getSunrise()));
                            // //
                            // Toast.makeText(getActivity().getApplicationContext(),
                            // AllDaysForGrid.get(position), Toast.LENGTH_LONG)
                            // .show();
                            // אם זה לא היום האחרון של הארבעה ימים אדומים
                        } /*
					 * else if (position - 7 != xx.get(xx.size() - 1)) {
					 * yeshShinuy(); // אם זה יום מתוך שבעה נקיים }
					 */

                        if (isSivaNekym) {
                            // אם זה יום מתוך השבעה נקיים
                            // בונה ליסט עם שבעת הימים נטו לא כולל האפסים
                            for (int i = 0; i < yellowDays.size(); i++) {
                                if (!yellowDays.get(i).equals("1-1-1970")) {
                                    realYellowDay.add(yellowDays.get(i));
                                    Log.d("equals", "i");
                                }
                            }
                            if (nidaDAO.convertMonth(AllDaysForGrid.get(position))
                                    .equals(realYellowDay.get(5))
                                    || nidaDAO.convertMonth(
                                    AllDaysForGrid.get(position)).equals(
                                    realYellowDay.get(4))
                                    || nidaDAO.convertMonth(
                                    AllDaysForGrid.get(position)).equals(
                                    realYellowDay.get(3))
                                    || nidaDAO.convertMonth(
                                    AllDaysForGrid.get(position)).equals(
                                    realYellowDay.get(2))
                                    || nidaDAO.convertMonth(
                                    AllDaysForGrid.get(position)).equals(
                                    realYellowDay.get(1))
                                    || nidaDAO.convertMonth(
                                    AllDaysForGrid.get(position)).equals(
                                    realYellowDay.get(0))) {

                                final Dialog settingsDialog = new Dialog(
                                        getActivity());
                                settingsDialog
                                        .setContentView(R.layout.image_layout);
                                settingsDialog.setTitle("האם ראית כתם היום888?"
                                        + "\n" + " האם הכתם טמא?");
                                btn = (Button) settingsDialog
                                        .findViewById(R.id.button1);
                                btn2 = (Button) settingsDialog
                                        .findViewById(R.id.button2);
                                btn.setText("yes");
                                btn2.setText("no");
                                // מוסיף יום על הימים הצהובים
                                btn.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        // TODO Auto-generated method stub

                                        Toast.makeText(
                                                getActivity(),
                                                ""
                                                        + nidaDAO
                                                        .convertLongDateToString(fullDateGeorgi
                                                                .get(position + 2)),
                                                Toast.LENGTH_SHORT).show();
									/*
									 * yy = nidaDayes.markingtTheSevenDayes(
									 * hebrewDate .getLastDayOfHebrewMonth(),
									 * position - 7 + 1);
									 */
                                        yellowDaysLong = nidaDAO
                                                .selectFromRedClnLong(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_YELLOW);
                                        nidaDAO.updateYellowIf(
                                                fullDateGeorgi.get(position + 2),
                                                MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_YELLOW);

                                        // set siman for ketem
                                        new RetrieveCalendarView().execute();
                                        settingsDialog.dismiss();
                                    }
                                });
                                settingsDialog.show();
                                btn2.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        // TODO Auto-generated method stub

                                        // new RetrieveCalendarView().execute();
                                        // nidaDAO.updateBooleans(
                                        // MySQLiteOpenHelper.CLN_BOOLEAN_IS_SHIVA_NEKYM,
                                        // "false");
									/* isSivaNekym = false; */
                                        isSivaNekym = nidaDAO
                                                .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_SHIVA_NEKYM);
                                        // isLastRed = false;
                                        isLastRed = nidaDAO
                                                .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_RED);
                                        Toast.makeText(getActivity(), "7",
                                                Toast.LENGTH_SHORT).show();

                                        settingsDialog.dismiss();
                                    }
                                });

                            }

                            // בלחיצה על היום האדום האחרון
                        }
                        if (isLastDayOfShivaNekym) {
                            if (nidaDAO
                                    .convertMonth(AllDaysForGrid.get(position))
                                    .equals(realYellowDay.get(realYellowDay.size() - 1))) {

                                final Dialog settingsDialog = new Dialog(
                                        getActivity());
                                settingsDialog
                                        .setContentView(R.layout.image_layout);
                                settingsDialog.setTitle("הפסק טהרה??");
                                btn = (Button) settingsDialog
                                        .findViewById(R.id.button1);
                                btn2 = (Button) settingsDialog
                                        .findViewById(R.id.button2);
                                // btn2.setVisibility(View.GONE);
                                btn.setText("yes");
                                btn2.setText("no");
                                btn.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        // TODO Auto-generated method stub
                                        if (yellowDaysLong.size() < 2) {
                                            yellowDaysLong = nidaDAO
                                                    .selectFromRedClnLong(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_YELLOW);
                                        }
                                        nidaDAO.insertMimve(fullDateGeorgi
                                                .get(position + 2));
                                        blueDays = nidaDAO
                                                .selectFromRedCln(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_BLUE);
                                        nidaDAO.updateBooleans(
                                                MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_SHIVA_NEKYM, "false");
                                        isRed = nidaDAO
                                                .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_SHIVA_NEKYM);
                                        nidaDAO.updateBooleans(
                                                MySQLiteOpenHelper.CLN_BOOLEAN_IS_SHIVA_NEKYM, "false");
                                        isRed = nidaDAO
                                                .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_SHIVA_NEKYM);

                                        new RetrieveCalendarView().execute();
                                        settingsDialog.dismiss();
                                    }
                                });
                                settingsDialog.show();

                                btn2.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        // TODO Auto-generated method stub

                                        if (yellowDaysLong.size() < 2) {
                                            yellowDaysLong = nidaDAO
                                                    .selectFromRedClnLong(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_YELLOW);
                                        }
                                        nidaDAO.addOneDay(
                                                MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_YELLOW,
                                                yellowDaysLong.get(yellowDaysLong
                                                        .size() - 1));
                                        new RetrieveCalendarView().execute();
                                        settingsDialog.dismiss();
                                    }
                                });

                                Log.d("real", "shimi");

                            }

                        }
                        for (int i = 0; i < realYellowDay.size(); i++) {

                            Log.d("real",
                                    "-----"
                                            + realYellowDay.get(6)
                                            + "----------->"
                                            + nidaDAO.convertMonth(AllDaysForGrid
                                            .get(position)) + "-----");

                        }
                        if (isLastRed) {
                            // אם זה היום האחרון של ארבעת הימים האדומים וגם יש לנו
                            // ימים אדומים
                            for (int i = 0; i < redDays.size(); i++) {
                                Log.d("redDaysget", redDays.get(i));
                            }
                            Log.d("redDaysget", redDays.get(redDays.size() - 1));
                            if (nidaDAO.convertMonth(AllDaysForGrid.get(position))
                                    .equals(redDays.get(redDays.size() - 1))) {

                                final Dialog settingsDialog = new Dialog(
                                        getActivity());
                                settingsDialog
                                        .setContentView(R.layout.image_layout);
                                settingsDialog.setTitle("אנא סמני את הלילה!");
                                btn = (Button) settingsDialog
                                        .findViewById(R.id.button1);
                                btn2 = (Button) settingsDialog
                                        .findViewById(R.id.button2);
                                // btn2.setVisibility(View.GONE);
                                btn.setText("yes");
                                btn2.setText("no");
                                btn.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        // TODO Auto-generated method stub
                                        yy = nidaDayes.markingtTheSevenDayes(
                                                hebrewDate
                                                        .getLastDayOfHebrewMonth(),
                                                position - 7 + 1);

                                        redDaysLong = nidaDAO
                                                .selectFromRedClnLong(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_RED);

                                        nidaDAO.insertDaysColorsYellow(
                                                redDaysLong.get(redDaysLong.size() - 1),
                                                MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_YELLOW);

                                        yellowDays = nidaDAO
                                                .selectFromRedCln(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_YELLOW);

                                        settingsDialog.dismiss();
									/* isLastDayOfShivaNekym = true */
                                        nidaDAO.updateBooleans(
                                                MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_SHIVA_NEKYM,
                                                "true");
                                        isLastDayOfShivaNekym = nidaDAO
                                                .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_SHIVA_NEKYM);
                                        nidaDAO.updateBooleans(
                                                MySQLiteOpenHelper.CLN_IS_RED,
                                                "true");
									/* isRed = true; */
                                        isRed = nidaDAO
                                                .selectBooleans(MySQLiteOpenHelper.CLN_IS_RED);
                                        nidaDAO.updateBooleans(
                                                MySQLiteOpenHelper.CLN_BOOLEAN_IS_SHIVA_NEKYM,
                                                "true");
									/* isSivaNekym = true; */
                                        isSivaNekym = nidaDAO
                                                .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_SHIVA_NEKYM);
                                        nidaDAO.updateBooleans(
                                                MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_RED,
                                                "false");
									/* isLastRed = false; */
                                        isLastRed = nidaDAO
                                                .selectBooleans(MySQLiteOpenHelper.CLN_BOOLEAN_IS_LAST_RED);
                                        new RetrieveCalendarView().execute();

                                    }
                                });
                                settingsDialog.show();
                                // מוסיף יום על הימים האדומים
                                btn2.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        // TODO Auto-generated method stub
                                        xx = nidaDayes.addOneDayTofourDayesList(
                                                hebrewDate.getHebrewMonth(),
                                                hebrewDate.getHebrewDayAsInt(), xx);
                                        redDaysLong = nidaDAO
                                                .selectFromRedClnLong(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_RED);

                                        nidaDAO.addOneDay(
                                                MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_RED,
                                                redDaysLong.get(redDaysLong.size() - 1));

                                        new RetrieveCalendarView().execute();
                                        settingsDialog.dismiss();
                                    }
                                });

                            }

                        } else {
                            //yeshShinuy();
                        }
                    }else{
                        Toast.makeText(getActivity(), "לא ניתן לסמן יום ", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // ACCOUNT FOR SPACING
            redDays = nidaDAO
                    .selectFromRedCln(MySQLiteOpenHelper.CLN_NOTIFICATION_COLOR_RED);
            Log.d(tag, "Current Day: " + getCurrentDayOfMonth());
            String[] day_color = list.get(position).split("-");
            for (int i = 0; i < list.size(); i++) {
                Log.d("list", list.get(i));
            }

            String theday = day_color[0];
            String themonth = day_color[2];
            String theyear = day_color[3];
            String curent = theday + "-" + themonth + "-" + theyear;
            Log.d("themonth", theday + "-" + themonth + "-" + theyear);
            AllDaysForGrid.add(position, curent);
            Log.d("position", "" + position);
            Log.d("num", "" + AllDaysForGrid.size());
            Log.d("AllDaysForGrid", AllDaysForGrid.get(position));

            if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null)) {
                if (eventsPerMonthMap.containsKey(theday)) {
                    num_events_per_day = (TextView) row
                            .findViewById(R.id.num_events_per_day);
                    Integer numEvents = (Integer) eventsPerMonthMap.get(theday);
                    num_events_per_day.setText(numEvents.toString());
                }
            }
            int monthInt = 0;
            for (int i = 0; i < months.length; i++) {
                if (months[i].contains(themonth)) {
                    monthInt = i + 1;
                    break;
                }
            }

            if (isJewishHolliday(theday + "-" + monthInt + "-" + theyear)) {
                event = (TextView) row.findViewById(R.id.event);
                event.setText(JewishHollidayName(theday + "-" + monthInt + "-"
                        + theyear));
                event.setTextColor(Color.MAGENTA);
            }
			/*for (int i = 0; i < redDays.size(); i++) {
				Log.d("dateSplit7777", redDays.get(i));
				Log.d("dateSplit1", theday + "-" + monthInt + "-" + theyear);
			}*/

            if (isSaturday(theday + "-" + monthInt + "-" + theyear)) {
            }
            TextView normal_day = (TextView) row.findViewById(R.id.normal_day);
            normal_day.setText(theday);
            normal_day.setTextColor(Color.BLACK);
            // Set the Day GridCell
            HebrewDate hDate = new HebrewDate();
            hDate.setDate(monthInt, Integer.parseInt(theday),
                    Integer.parseInt(theyear));
            // ---object calculateNidaDayes--//shimi

            // nidaDayes.markingTheDayOfNextMonth(hDate.getHebrewMonth(),
            // hDate.getHebrewDayAsInt(), 11, 1);

            // ---------markingTheDayOfNextMonth----------------//shimi
            if (position - 2 == anotherDay && anotherDay != 0) {
                gridcell.setBackground(getResources().getDrawable(
                        R.drawable.red));
                gridcell.setText(hDate.getHebrewDayAsString());

            }
            if (nidaDayes.markingTheDayOfNextMonth(hDate.getHebrewMonth(),
                    hDate.getHebrewDayAsInt(), 11, 1))

            {
                gridcell.setBackground(getResources().getDrawable(
                        R.drawable.red));
                gridcell.setText(hDate.getHebrewDayAsString()/*
															 * +"\n"+hDate.
															 * getHebrewMonthAsString
															 * ()
															 */);
            } else {
                gridcell.setText(hDate.getHebrewDayAsString());
                String string_date = hDate.getDateAsFull();

                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                Date d = null;
                try {
                    d = f.parse(string_date);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                long milliseconds = d.getTime();
                fullDateGeorgi.add(milliseconds);
                // Log.d("gridcellsetText1", hDate.getDateAsFull());
                // Log.d("gridcellsetText", theday + "-" + themonth + "-" +
                // theyear);
                gridcell.setTag(theday + "-" + themonth + "-" + theyear);

            }
            // Log.d("gettag", gridcell.getTag().toString());
			/*
			 * if(hDate.getHebrewDayAsInt()==1){
			 * gridcell.setBackground(getResources
			 * ().getDrawable(R.drawable.red));
			 * gridcell.setText(hDate.getHebrewDayAsString
			 * ()+"\n"+hDate.getHebrewMonthAsString());
			 *
			 * }
			 */
            // order the choose markin day
			/*if (hDate.getHebrewDayAsInt() == nidaDayes.markingtTheOnaBenonit(
					hDate.getHebrewMonth(), 2)
					&& hDate.getHebrewMonth() == nidaDayes
							.markingtTheOnaBenonitManth(10)) {
				gridcell.setBackground(getResources().getDrawable(
						R.drawable.red));
				gridcell.setText(hDate.getHebrewDayAsString());
			}
*/
			/*
			 * for (int i = 0; i < yy.length; i++) { if
			 * (hDate.getHebrewDayAsInt() == yy[i]) {
			 * gridcell.setBackground(getResources().getDrawable(
			 * R.drawable.orange));
			 * gridcell.setText(hDate.getHebrewDayAsString() +"\n"+hDate.
			 * getHebrewMonthAsString () ); } }
			 */
			/*
			 * for (int i = 0; i < xx.size(); i++) { if
			 * (hDate.getHebrewDayAsInt() == xx.get(i)) {
			 * gridcell.setBackground(getResources().getDrawable(
			 * R.drawable.red)); gridcell.setText(hDate.getHebrewDayAsString()
			 * +"\n"+hDate. getHebrewMonthAsString () ); } }
			 */
			/*
			 * if (hDate.getHebrewDayAsInt() == sevenDay) {
			 * gridcell.setBackground(getResources().getDrawable(
			 * R.drawable.black));
			 * gridcell.setText(hDate.getHebrewDayAsString());
			 *
			 * }
			 */

			/*
			 * gridcell.setOnClickListener(new OnClickListener() {
			 *
			 * @Override public void onClick(View v) { // TODO Auto-generated
			 * method stub
			 *
			 * String date_month_year = (String) v.getTag();
			 * selectedDayMonthYearButton.setText("Selected: " +
			 * date_month_year); Date parsedDate = null; try { parsedDate =
			 * dateFormatter.parse(date_month_year); } catch
			 * (java.text.ParseException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } //Log.d(tag, "Parsed Date: " +
			 * parsedDate.toString());
			 *
			 * String[] date = date_month_year.split("-"); int monthInt = 0; for
			 * (int i = 0; i < months.length; i++) { if
			 * (months[i].contains(date[1])) { monthInt = i + 1; break; } }
			 *
			 * int cyear = Integer.parseInt(date[2]); int cmonth = monthInt;
			 * Integer.parseInt(date[1]); int cday = Integer.parseInt(date[0]);
			 * DisplayData dd = new DisplayData().cday(cday) .cmonth(cmonth -
			 * 1).cyear(cyear); String ddOutput = dd
			 * .getOuput(SimpleJewishCalendarActivity.this); String body =
			 * ddOutput + "\n";
			 *
			 * Calendar gCal = new GregorianCalendar(2013, 12, 22);
			 * zc.setCalendar(selectFromRedClngCal); SimpleDateFormat format_time = null; if
			 * (timeFormat.equalsIgnoreCase("12h")) format_time = new
			 * SimpleDateFormat("h:mm"); else format_time = new
			 * SimpleDateFormat("k:mm");
			 *
			 * Calendar Cal = new GregorianCalendar(cyear, cmonth - 1, cday);
			 * RegularHebrewDate jDate = new RegularHebrewDate();
			 * jDate.setDate(Cal); body = body + "\n" + jDate.getHoliday() +
			 * "\n"; String alot = ""; if (zc.getAlosHashachar() != null) alot =
			 * format_time.format(zc.getAlosHashachar()); String tallit = ""; if
			 * (zc.getSunriseOffsetByDegrees(101) != null) tallit =
			 * format_time.format(zc .getSunriseOffsetByDegrees(101)); String
			 * shmaGra = ""; if (zc.getSofZmanShmaGRA() != null) shmaGra =
			 * format_time.format(zc.getSofZmanShmaGRA()); String shkiah = "";
			 * if (zc.getSunset() != null) shkiah =
			 * format_time.format(zc.getSunset()); body = body +
			 * "Alos HaShachar:" + alot + "\n" + "Earliest Tallis:" + tallit +
			 * "\n" + "Netz:" + format_time.format(zc.getSunrise()) + "\n" +
			 * "Latest Sh'ma:" + shmaGra + "\n" + "Chatzos:" +
			 * format_time.format(zc.getChatzos()) + "\n" + "Mincha Gedola:" +
			 * format_time.format(zc.getMinchaGedola()) + "\n" + "Mincha Ktana:"
			 * + format_time.format(zc.getMinchaKetana()) + "\n" +
			 * "Plag Hamincha:" + format_time.format(zc.getPlagHamincha()) +
			 * "\n" + "Shkiah:" + shkiah;
			 *
			 * Dialog adb = new Dialog(SimpleJewishCalendarActivity.this);
			 * adb.setContentView(R.layout.list_item);
			 * adb.setTitle("׀—׀¼׀°׀½׀¸׀¼");
			 * adb.setCanceledOnTouchOutside(true); TextView text = (TextView)
			 * adb.findViewById(R.id.item_text); text.setText(body); adb.show();
			 *
			 * } });
			 */
            // Log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-" +
            // theyear);
            Log.d("getHebrewDay", hDate.getHebrewDayAsString());
            if (day_color[1].equals("GREY")) {
                gridcell.setText("");
                gridcell.setTextColor(Color.GRAY);
                gridcell.setBackground(getResources().getDrawable(
                        R.drawable.black));
                gridcell.setClickable(false);

            }
            //String x = nidaDAO.convertLongDateToString(Long.valueOf(blueDays.get(0)));

            if (day_color[1].equals("WHITE")) {
                gridcell.setTextColor(Color.BLACK);

            }
            if (day_color[1].equals("BLUE")) {
                gridcell.setTextColor(getResources().getColor(
                        R.color.static_text_color));

            }
            if (day_color[1].equals("RED")) {
                gridcell.setTextColor(Color.RED);
            }
            if (day_color[1].equals("SHIMI")) {
                gridcell.setBackground(getResources().getDrawable(
                        R.drawable.red));
                gridcell.setText(hDate.getHebrewDayAsString());
            }
            if (day_color[1].equals("YELLOW")) {
                gridcell.setBackground(getResources().getDrawable(
                        R.drawable.orange));
                gridcell.setText(hDate.getHebrewDayAsString());
            }
            //Log.d("amarshimi", blueDays.get(blueDays.size()-1)+"-->"+theday + "-" + monthInt + "-" + theyear);
            if(blueDays.size()>=1&&blueDays.get(blueDays.size()-1).equals(theday + "-" + monthInt + "-" + theyear)){
                Log.d("amarshimi", blueDays.get(blueDays.size()-1)+"-->"+theday + "-" + monthInt + "-" + theyear);
                //Log.d("amarshimi", blueDays.get(0)+"-->"+theday + "-" + monthInt + "-" + theyear);
                gridcell.setBackground(getResources().getDrawable(
                        R.drawable.black));
                gridcell.setText(hDate.getHebrewDayAsString());
            }
            return row;
        }

        // /-----onclick to zmanim jar-----------

		/*
		 * @Override public void onClick(View view) {
		 *
		 * String date_month_year = (String) view.getTag();
		 * selectedDayMonthYearButton.setText("Selected: " + date_month_year);
		 * Date parsedDate = null; try { parsedDate =
		 * dateFormatter.parse(date_month_year); } catch
		 * (java.text.ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } Log.d(tag, "Parsed Date: " +
		 * parsedDate.toString());
		 *
		 * String[] date = date_month_year.split("-"); int monthInt = 0; for
		 * (int i = 0; i < months.length; i++) { if
		 * (months[i].contains(date[1])) { monthInt = i + 1; break; } }
		 *
		 * int cyear = Integer.parseInt(date[2]); int cmonth = monthInt;
		 * Integer.parseInt(date[1]); int cday = Integer.parseInt(date[0]);
		 * DisplayData dd = new DisplayData().cday(cday).cmonth(cmonth - 1)
		 * .cyear(cyear); String ddOutput =
		 * dd.getOuput(SimpleJewishCalendarActivity.this); String body =
		 * ddOutput + "\n";
		 *
		 * Calendar gCal = new GregorianCalendar(cyear, cmonth, cday);
		 * //zc.setCalendar(gCal); SimpleDateFormat format_time = null; if
		 * (timeFormat.equalsIgnoreCase("12h")) format_time = new
		 * SimpleDateFormat("h:mm a"); else format_time = new
		 * SimpleDateFormat("k:mm");
		 *
		 * Calendar Cal = new GregorianCalendar(cyear, cmonth - 1, cday);
		 * RegularHebrewDate jDate = new RegularHebrewDate();
		 * jDate.setDate(Cal); body = body + "\n" + jDate.getHoliday() + "\n";
		 * String alot = ""; if (zc.getAlosHashachar() != null) alot =
		 * format_time.format(zc.getAlosHashachar()); String tallit = ""; if
		 * (zc.getSunriseOffsetByDegrees(101) != null) tallit =
		 * format_time.format(zc.getSunriseOffsetByDegrees(101)); String shmaGra
		 * = ""; if (zc.getSofZmanShmaGRA() != null) shmaGra =
		 * format_time.format(zc.getSofZmanShmaGRA()); String shkiah = ""; if
		 * (zc.getSunset() != null) shkiah = format_time.format(zc.getSunset());
		 * body = body + "Alos HaShachar:" + alot + "\n" + "Earliest Tallis:" +
		 * tallit + "\n" + "Netz:" + format_time.format(zc.getSunrise()) + "\n"
		 * + "Latest Sh'ma:" + shmaGra + "\n" + "Chatzos:" +
		 * format_time.format(zc.getChatzos()) + "\n" + "Mincha Gedola:" +
		 * format_time.format(zc.getMinchaGedola()) + "\n" + "Mincha Ktana:" +
		 * format_time.format(zc.getMinchaKetana()) + "\n" + "Plag Hamincha:" +
		 * format_time.format(zc.getPlagHamincha()) + "\n" + "Shkiah:" + shkiah;
		 *
		 * Dialog adb = new Dialog(SimpleJewishCalendarActivity.this);
		 * adb.setContentView(R.layout.list_item); adb.setTitle("׀—׀¼׀°׀½׀¸׀¼");
		 * adb.setCanceledOnTouchOutside(true); TextView text = (TextView)
		 * adb.findViewById(R.id.item_text); text.setText(body); adb.show(); }
		 */

        public int getCurrentDayOfMonth() {
            return currentDayOfMonth;
        }

        public Boolean ifDayIsRed(String dateString) {

            if (redDays.get(0).equals(dateString)) {
                Log.d("ssdd", dateString);

                return true;
            }
            return false;

        }

        public Boolean isSaturday(String dateString) {
            String[] dateAr = dateString.split("-");
            int year = Integer.parseInt(dateAr[2]);
            int month = Integer.parseInt(dateAr[1]);
            int day = Integer.parseInt(dateAr[0]);
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(year, month - 1, day);
            if ((cal.get(Calendar.DAY_OF_WEEK)) == 7)
                return true;
            return false;
        }

        public Boolean isJewishHolliday(String dateString) {
            String[] dateAr = dateString.split("-");
            int year = Integer.parseInt(dateAr[2]);
            int month = Integer.parseInt(dateAr[1]);
            int day = Integer.parseInt(dateAr[0]);
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(year, month - 1, day);
            RegularHebrewDate jDate = new RegularHebrewDate();
            jDate.setDate(cal);
            if (!jDate.getHoliday().contentEquals(""))
                return true;
            return false;

        }

        public String JewishHollidayName(String dateString) {
            String[] dateAr = dateString.split("-");
            int year = Integer.parseInt(dateAr[2]);
            int month = Integer.parseInt(dateAr[1]);
            int day = Integer.parseInt(dateAr[0]);
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(year, month - 1, day);
            RegularHebrewDate jDate = new RegularHebrewDate();
            jDate.setDate(cal);
            return jDate.getHoliday();
        }

        private void setCurrentDayOfMonth(int currentDayOfMonth) {
            this.currentDayOfMonth = currentDayOfMonth;
        }

        public void setCurrentWeekDay(int currentWeekDay) {
            this.currentWeekDay = currentWeekDay;
        }

        public int getCurrentWeekDay() {
            return currentWeekDay;
        }

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

        }

    }

}
