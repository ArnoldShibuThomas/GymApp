package main.java.memoranda.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.io.*;
import java.nio.charset.StandardCharsets;
import main.java.memoranda.gymAssets.Avaliability;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AvaliabilityTool {

    //private instance variables
    private ArrayList<Avaliability> peopleAvaliability;
    int whatView;
    String who;

    String[] days = new String[]
            {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    /**
     * This is the constructor.
     */
    public AvaliabilityTool(int whoIsUsingSystem, String whoAreyou) {
        this.whatView = whoIsUsingSystem;
        this.who = whoAreyou;
        peopleAvaliability = readAvaliability();
    }

    /**
     * This will remove the avalaiability given.
     * @param a the avalaibility that needs to be removed
     * @return boolean if it was sucessful
     */
    public boolean removeAvaliability(Avaliability a) {

        if (a == null) {
            System.out.println("[Debug]: We have a null!");
            return false;
        }

        if (peopleAvaliability.contains(a)) {
            peopleAvaliability.remove(a);
            writeAvaliability();
            return true;
        }

        return false;
    }

    /**
     * This will add back avalaibaility.
     */
    public void addBack(Avaliability a) {
        peopleAvaliability.add(a);
        writeAvaliability();
    }

    /**
     * This will get the people that have avalaibility for that day.
     * @param day the day we want to check
     * @return ArrayList all people on that day
     */
    public ArrayList<Avaliability> getPeopleForDay(String day) {

        //create a new arraylist
        ArrayList<Avaliability> peopleOnThisDay = new ArrayList<>();

        //check if the view is not the student
        if (whatView == 2) {
            return peopleOnThisDay;
        }

        //go through all the people omn the schedule
        for (int i = 0; i < peopleAvaliability.size(); i++) {
            //get the people here
            Avaliability here = peopleAvaliability.get(i);

            //if they are the admin go ahead and show all the people on this day
            if (here.hasDay(day)) {
                if (whatView == 0) {
                    peopleOnThisDay.add(here);
                } else {

                    //if the person is the trainer then
                    //check to see it only pulls up with thier avaliability
                    if (who.equals(here.getTrainer())) {
                        peopleOnThisDay.add(here);
                    }
                }
            }
        }

        return peopleOnThisDay;
    }

    /**
     * This is will return the day.
     * @param day number day
     * @return String the day word
     */
    public String getTheDayOfTheWeek(int day) {

        if (day <= 0) {
            return days[0];
        }

        if (day >= 7) {
            return days[6];
        }

        return days[day - 1];
    }

    /**
     * This will add new Avalability.
     * @param trainer the trainer
     * @param start the start time
     * @param end the end time
     * @param days the days
     * @param reponse the label to update
     * @return boolean if it was sucessful
     */
    public boolean addNewAvalaibility(String trainer, String start,
                                      String end,ArrayList<String> days, JLabel reponse) {

        if (trainer == null || start == null || end == null) {
            return false;
        }

        if (days.size() == 0) {
            reponse.setText("Please choose day(s) to associate with this avaliability!");
            return false;
        }

        Avaliability wantsToAdd = new Avaliability();
        wantsToAdd.setTrainer(trainer);
        wantsToAdd.setStartTime(start);
        wantsToAdd.setEndTime(end);
        wantsToAdd.setDays(days);

        //the person is trying to set an avaliability with start time and end times reversed
        if (wantsToAdd.getTimeAsInt(1) <= wantsToAdd.getTimeAsInt(0)) {

            if (wantsToAdd.getTimeAsInt(1) == wantsToAdd.getTimeAsInt(0)) {
                reponse.setText("Please give a start and end time "
                        + "that are different!");
            } else {
                reponse.setText("Please give a start time that "
                        + "is behind the end time(No time traveling)!");
            }
            return false;
        }

        for (int i = 0; i < peopleAvaliability.size(); i++) {
            Avaliability here = peopleAvaliability.get(i);

            //check to see if the trainers match
            if (trainer.equals(here.getTrainer())) {
                //get the list of days this person already does
                ArrayList<String> daysPersonAlreadyDoes = here.getDays();
                //checl to see if there are any over laps
                for (int j = 0; j < daysPersonAlreadyDoes.size(); j++) {
                    for (int e = 0; e < days.size(); e++) {

                        if ((days.get(e)).equals(daysPersonAlreadyDoes.get(j))) {

                            if (wantsToAdd.getTimeAsInt(1) == here.getTimeAsInt(1)) {
                                reponse.setText("On " + days.get(e) + " you already end at "
                                        + end + "! Please try again!");
                                return false;
                            }

                            if (wantsToAdd.getTimeAsInt(0) == here.getTimeAsInt(0)) {
                                reponse.setText("On " + days.get(e) + " you already start at "
                                        + start + "! Please try again!");
                                return false;
                            }

                            if (wantsToAdd.getTimeAsInt(0) > here.getTimeAsInt(0)
                                    && wantsToAdd.getTimeAsInt(0) < here.getTimeAsInt(1)) {
                                reponse.setText("Try Again: avaliability overlaps with start "
                                        + "avaliability somewhere else!");
                                return false;
                            }

                            if (wantsToAdd.getTimeAsInt(1) > here.getTimeAsInt(0)
                                    && wantsToAdd.getTimeAsInt(1) < here.getTimeAsInt(1)) {
                                reponse.setText("Try Again: avaliability overlaps with "
                                        + "end avaliability somewhere else!");
                                return false;
                            }

                            if (wantsToAdd.getTimeAsInt(0) < here.getTimeAsInt(0)
                                    && wantsToAdd.getTimeAsInt(1) > here.getTimeAsInt(1)) {
                                reponse.setText("Try Again: avaliability "
                                        + "overlaps with exisitng avaliability!");
                                return false;
                            }
                        }
                    }
                }
            }
        }

        reponse.setText("It Looks like we have " + trainer + " on the schedule!");
        peopleAvaliability.add(wantsToAdd);
        writeAvaliability();
        return true;
    }

    /**
     * This is a public method the gets all the avaliability.
     */
    public ArrayList<Avaliability> getAvaliability() {
        return peopleAvaliability;
    }

    /**
     * This is a public method the updates all the avaliability.
     */
    public void setAvaliability(ArrayList<Avaliability> updated) {
        peopleAvaliability = updated;
    }

    /**
     * This is a public method the writes the avaliability.
     */
    public void writeAvaliability() {

        //create a JSON array
        JSONArray times = new JSONArray();

        //iterate all the times and to write them to the file
        for (int i = 0; i < peopleAvaliability.size(); i++) {
            //Creating a JSONObject object
            JSONObject timeObject = new JSONObject();
            //get the time here
            Avaliability timeHere = peopleAvaliability.get(i);
            //put parts into JSON object
            timeObject.put("trainer", timeHere.getTrainer());
            timeObject.put("start", timeHere.getStartTime());
            timeObject.put("end", timeHere.getEndTime());
            timeObject.put("days", timeHere.getDaysAsString());
            times.add(timeObject);
        }

        //create a times JSON object that contains array this is considered the main part of JSON
        JSONObject main = new JSONObject();
        //put this into JSONObject
        main.put("times", times);

        OutputStream os = null;
        Writer wr = null;

        //write to a file now
        try {
            //create a file writer
            os = new FileOutputStream("src/main/resources/gymfiles/times.json");
            wr = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            //write JSON object to file
            wr.write(main.toJSONString());
            //close the file reader
            wr.close();
            os.close();
            //print out the file was created
            System.out.println("[Debug]: JSON file created, "
                    + "found in src/main/resources/gymfiles/times.json");
        } catch (IOException e) {

            try {
                if(wr != null){
                    wr.close();
                }
                if(os != null){
                    os.close();
                }
            } catch(Exception f) {
                wr = null;
                os = null;
            }

            // print error since file was not able to be read
            System.out.println("[Error]: JSON file was unable to be created try again!");

        }
    }


    /**
     * This is a private method the reads the avaliability.
     */
    private ArrayList<Avaliability> readAvaliability() {
        //create a JSON parser to read JSON
        JSONParser parser = new JSONParser();

        //an arrayList of all peopleAvaliability
        ArrayList<Avaliability> avaliability = new ArrayList<Avaliability>();

        //create a file reader
        BufferedReader reader = null;
        InputStream in = null;
        Reader read = null;

        //try to parse times
        try {

            //use file reader to read in JSON at this location
            in = new FileInputStream("src/main/resources/gymfiles/times.json");
            read = new InputStreamReader(in, StandardCharsets.UTF_8);
            reader = new BufferedReader(read);
            System.out.println("[Debug]: times.json was parsed!");

            //put the parsed JSON Object into an Object type variable
            Object obj = parser.parse(reader);
            //cast this object type to a JSONObject
            JSONObject timetInJSON = (JSONObject) obj;
            // go ahead and get the times Array from the JSON
            JSONArray timesArray = (JSONArray) timetInJSON.get("times");

            for (int i = 0; i < timesArray.size(); i++) {

                //get the object at this index of JSON Array
                JSONObject timeHere = (JSONObject) timesArray.get(i);

                //create a new avaliability object
                Avaliability a = new Avaliability();
                a.setTrainer((String) timeHere.get("trainer"));
                a.setStartTime((String) timeHere.get("start"));
                a.setEndTime((String) timeHere.get("end"));
                a.setDaysFromString((String) timeHere.get("days"));

                //add to list of avalaibaility
                avaliability.add(a);

            }

            System.out.println("[Debug]: Times.json was read in and we have all the availabiltiy!");

            reader.close();
            read.close();
            in.close();
            return avaliability;

        } catch (Exception e) {
            System.out.println("[Debug]: Times.json was unable to be read in"
                    + " so you need to add some avaliabilities!");
            System.out.println(e);
            try {
                if(reader != null){
                    reader.close();
                }
                if(read != null){
                    read.close();
                }
                if(in != null){
                    in.close();
                }
            } catch (Exception f) {
                reader = null;
                read = null;
                in = null;
            }

            return avaliability;
        }
    }
}
    