package com.tahara.amarshimi.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amarshimi on 2/22/2015.
 */

public class CalculateNidaDayes {

    public CalculateNidaDayes() {
        super();
        // TODO Auto-generated constructor stub
    }

    // v
    public boolean markingTheDayOfNextMonth(int hebrewManth, int hebrewDay,
                                            int manth, int day) {
        if (hebrewManth == manth && hebrewDay == day)
            return true;
        return false;

    }

    // מגלה לי מה החודש הבא
    public int markingtTheOnaBenonitManth(int manth) {
        return manth + 1;

    }

    public int[] markingtTheSevenDayes(int manth, int day) {

        int sevenArray[] = new int[7];
        if (manth == 29) {
            for (int i = 0; i < sevenArray.length; i++) {
                if (day == 30)
                    day = 1;
                sevenArray[i] = day;
                day++;
            }
            return sevenArray;
        }
        if (manth == 30) {
            for (int i = 0; i < sevenArray.length; i++) {
                if (day == 31)
                    day = 1;
                sevenArray[i] = day;
                day++;
            }
            return sevenArray;

        }
        return sevenArray;
    }

    public List<Integer> markingtTheFourDayes(int manth, int day) {
        List<Integer> integers = new ArrayList<Integer>();

        if (manth == 29 && day <= 25) {
            for (int i = 0; i <= 3; i++) {
                integers.add(day);
                day++;
            }
            return integers;
        }
        if (manth == 30 && day <= 26) {
            for (int i = 0; i <= 3; i++) {
                integers.add(day);
                day++;
            }
            return integers;
        }

        if (manth == 29) {
            for (int i = 0; i <= 3; i++) {
                if (day == 30)
                    day = 1;
                integers.add(day);
                day++;
            }
            return integers;
        }
        if (manth == 30) {
            for (int i = 0; i <= 3; i++) {
                if (day == 31)
                    day = 1;
                integers.add(day);
                day++;
            }
            return integers;
        }
        return integers;

    }

    public int markingtTheOnaBenonit(int manth, int day) {

        if (manth == 30)
            return day;
        else
            return day + 1;
    }

    public int vesetKavua(String day) {
        int x = Integer.parseInt(day);
        if (x == 1) {
            return 1;
        } else {
            return 0;
        }

    }

    public int addOneDayTofourDayes(int manth, int day, int array[]) {
        int lastDay = 0;

        lastDay = array[array.length - 1];

        if (manth == 30 && lastDay == 30)
            return 1;
        else if (manth == 29 && lastDay == 29)
            return 1;
        return lastDay + 1;

    }

    public List<Integer> addOneDayTofourDayesList(int manth, int day,
                                                  List<Integer> integers) {

        int stam = 0;
        List<Integer> name = new ArrayList<Integer>();
        for (int i = 0; i < integers.size(); i++) {
            name.add(integers.get(i));
            stam = integers.get(i);

        }

        if (manth == 30 && day <= 30) {

            name.add(1);
            return name;
        }

        else if (manth == 29 && day <= 29)

        {
            name.add(1);
            return name;
        } else
            name.add(stam + 1);
        return name;

    }

    public int[] arrayForAfterShkia(int[] array) {

        for (int i = 0; i < array.length; i++) {

            array[i] = array[i] + 1;
        }

        return array;

    }

}

