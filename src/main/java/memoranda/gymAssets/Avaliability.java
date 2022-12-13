/*
*File:Avaliability.Java
*Author:Arnold Shibu-Thomas
*Date:11/22
*Description:This file contains the class for the avaliability.
*/

package main.java.memoranda.gymAssets;

import java.util.ArrayList;

/**
 Class:AvaliabilityClass

 Description:This class is in charge of handling Avaliability.
 */
public class Avaliability {

    //instance variables
    private String trainer;
    private String start;
    private String end;
    private ArrayList<String> days;

    /**
     * This is the default constructor.
     */
    public Avaliability() {
        trainer = "";
        start = "";
        end = "";
        days = new ArrayList<String>();
    } 

    /**
     * This method will set the trainer.
     * @parm trainer this is the trainer to set
     */
    public void setTrainer(String trainer) {

        if (trainer == null) {
            this.trainer = "";
            return;
        }

        this.trainer = trainer;
    }

    /**
     * This method will set the start time.
     * @parm start this is the startTime to set
     */
    public void setStartTime(String start) {

        if (start == null) {
            this.start = "00:00";
            return;
        }

        this.start = start;
    }

    /**
     * This method will set the end time.
     * @parm end this is the endTime to set
     */
    public void setEndTime(String end) {

        if (end == null) {
            this.end = "00:00";
            return;
        }

        this.end = end;
    }

    /**
     * This method will set the day.
     * @parm days this is the day to set
     */
    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

    /**
     * This method will set the days given a string.
     * @parm days this is the days to set
     */
    public void setDaysFromString(String days) {

        if (days == null) {
            return;
        }

        if (days.length() == 0) {
            return;
        }

        String[] here = days.split(" "); 
        for (int i = 0; i < here.length; i++) {
            this.days.add(here[i]);
        } 

    }

    /**
     * This method will get the days as a string.
     * @return String this will return the days
     */
    public String getDaysAsString() {

        String daysToString = "";
        int size = days.size();

        if (days.contains("Monday")) {
            daysToString = daysToString + "Monday";
            size--;

            if (size > 0) {
                daysToString = daysToString + " ";
            }
        }

        if (days.contains("Tuesday")) {
            daysToString = daysToString + "Tuesday";
            size--;

            if (size > 0) {
                daysToString = daysToString + " ";
            }
        }

        if (days.contains("Wednesday")) {
            daysToString = daysToString + "Wednesday";
            size--;

            if (size > 0) {
                daysToString = daysToString + " ";
            }
        }  

        if (days.contains("Thursday")) {
            daysToString = daysToString + "Thursday";
            size--;

            if (size > 0) {
                daysToString = daysToString + " ";
            }
        }

        if (days.contains("Friday")) {
            daysToString = daysToString + "Friday";
            size--;

            if (size > 0) {
                daysToString = daysToString + " ";
            }
        }

        if (days.contains("Saturday")) {
            daysToString = daysToString + "Saturday";
            size--;

            if (size > 0) {
                daysToString = daysToString + " ";
            }
        }

        if (days.contains("Sunday")) {
            daysToString = daysToString + "Sunday";
            size--;

            if (size > 0) {
                daysToString = daysToString + " ";
            }
        }

        System.out.println("[Debug]:  " + daysToString);
        return daysToString;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getStartTime() {
        return start;
    }

    public String getEndTime() {
        return end;
    }

    public ArrayList<String> getDays() {
        return days;
    }

    /**
     * This method will set the trainer.
     * @parm whichOne this is a flag to specifiy end or start
     * @return int the time as int
     */
    public int getTimeAsInt(int whichOne) {

        if (whichOne == 0) {
            String[] hour = start.split(":"); 
            String number = hour[0];
            return Integer.parseInt(number);
        } else {
            String[] hour = end.split(":"); 
            String number = hour[0];
            return Integer.parseInt(number);
        }
    }

    public boolean hasDay(String day) {
        return days.contains(day);
    }


}