package main.java.memoranda.util;

//imports

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;

import main.java.memoranda.gymAssets.Belt;
import main.java.memoranda.gymAssets.Instructor;
import main.java.memoranda.gymAssets.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONTool {

    //instance variables
    private ArrayList<Student> allStudentsInGym;
    private ArrayList<Student> allTrainersInGym;
    private Student owner;
    private String whoIsUsing;
    private int view;

    /**
    * This is the default constructor.
    */
    public JSONTool() {
        //instantiate the lists
        allStudentsInGym = new ArrayList<Student>();
        allTrainersInGym = new ArrayList<Student>();
        owner = new Student();
        //set the default view to a student
        whoIsUsing = "Bob";
        view = 2;
        //read from the JSON
        readFromJSON();
        System.out.println("[Debug]: JSON tool was created and is ready to be used!");
    }

    /**
    * This will set who is using the system.
    */
    public void setUser(String name,int view) {
        //if the view and user of the system changes do this 
        System.out.println("[Debug]: Hello " + name + " you will use view: " + view);
        whoIsUsing = name;
        this.view = view;
    }

    /**
    * This will get the view.
    */
    public int getView() {
        return view;
    }

    /**
    * This will get the user.
    */
    public String getUser() {
        return whoIsUsing;
    }

    /**
    * This will return the owner of the system.
    */
    public Student getOwner() {
        return owner;
    }

    /**
    * This method provides all the students in our system.
    * @return  the arraylist of all the Trainers in our system
    */
    public ArrayList<Student> getAllTrainers() {
        return allTrainersInGym;
    }

    /**
    * This method provides all the students in the system.
    * @return the arraylist of all the students in our system
    */
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> everyoneHere = new ArrayList<Student>();
        everyoneHere.addAll(allStudentsInGym);
        everyoneHere.addAll(allTrainersInGym);
        return everyoneHere;
    }

    /**
    * This method updates the arraylist that contains all the trainers.
    * @param  updatedTrainers  the arraylist containing the new trainer info
    */
    public void updateTrainersInJSON(ArrayList<Student> updatedTrainers) {

        if (updatedTrainers != null) {

            if (updatedTrainers.size() > 0) {
                ArrayList<Student> trainersFound = new ArrayList<Student>();

                //check if we can update trainers list
                for (int i = 0; i < updatedTrainers.size(); i++) {
                    Student here = updatedTrainers.get(i);
                    String name = here.getName();
                    int who = whatAreYou(name);

                    //they are a student
                    if (who == 1) {
                        System.out.println("It looks like you are trying to add" 
                            + " a student which won't work here!");
                    } else if (who == 0) {
                        //they are a trainer
                        trainersFound.add(here);
                    } else {
                        System.out.println("We cannot update the list to include: " 
                            + here.getName() 
                            + " since they are a new person consider adding them!");
                    }
                }

                if (trainersFound.size() > 0) {
                    //update what the lists are
                    allTrainersInGym = trainersFound;
                }

            }
        }
    }

    /**
    * This method updates the arraylist that contains all the students.
    * @param  updatedStudents  the arraylist containing the new trainer info
    */
    public void updateStudentsToJSON(ArrayList<Student> updatedStudents) {

        if (updatedStudents != null) {
            if (updatedStudents.size() > 0) {
                //create new trainer and student list
                ArrayList<Student> trainersFound = new ArrayList<Student>();
                ArrayList<Student> studentsFound = new ArrayList<Student>();

                //loop through all the students
                for (int i = 0; i < updatedStudents.size(); i++) {
                    Student here = updatedStudents.get(i);
                    String name = here.getName();
                    int who = whatAreYou(name);

                    //they are a student
                    if (who == 1) {
                        studentsFound.add(here);
                    } else if (who == 0) {
                        //they are a trainer
                        trainersFound.add(here);
                    } else {
                        System.out.println("We cannot update the list to include: " 
                            + here.getName() 
                            + " since they are a new person consider adding them!");
                    }
                }

                if (trainersFound.size() > 0) {
                    //update what the lists are
                    allTrainersInGym = trainersFound;
                }

                allStudentsInGym = studentsFound;
            }
        }

    }

    /**
    * This method reads the JSON file.
    */
    public void writeToJSON() {
        
        //create a JSON array
        JSONArray people = new JSONArray();

        //iterate over students and to write them to the file
        for (int i = 0; i < allStudentsInGym.size(); i++) {
            //Creating a JSONObject object
            JSONObject studentObject = new JSONObject();
            //get the student here
            Student studentHere = allStudentsInGym.get(i);
            //put parts into JSON object
            studentObject.put("name", studentHere.getName());
            studentObject.put("password", studentHere.getPassword());
            studentObject.put("beltLevel", studentHere.getBeltLevel());
            studentObject.put("trainer", false);
            people.add(studentObject);
        }

        //iterate over all the trainer to write them to a file
        for (int i = 0; i < allTrainersInGym.size(); i++) {
            //Creating a JSONObject object
            JSONObject trainerObject = new JSONObject();
            //get the student here
            Instructor trainerHere = (Instructor) allTrainersInGym.get(i);
            //put parts into JSON object
            trainerObject.put("name", trainerHere.getName());
            trainerObject.put("password", trainerHere.getPassword());
            trainerObject.put("beltLevel", trainerHere.getBeltLevel());
            trainerObject.put("trainer", true);
            trainerObject.put("rank", trainerHere.getTrainingRank());
            trainerObject.put("owner",trainerHere.owner());

            //ad this tainer to JSON
            people.add(trainerObject);
        }

        //create a students JSON object that contains array this is considered the main part of JSON
        JSONObject main = new JSONObject();
        //put this into JSONObject
        main.put("students", people);

        //ensure file isn't modify if list is destroyed
        if (allTrainersInGym.size() > 0) {
            OutputStream os = null;
            Writer wr = null;

            //write to a file now
            try {
                os = new FileOutputStream("src/main/resources/gymfiles/students.json");
                wr = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                wr.write(main.toJSONString());
                //print out the file was created
                System.out.println("[Debug]: JSON file created, found "
                    + "in src/main/resources/gymfiles/students.json");
            } catch (IOException e) {
                // print error since file was not able to be read
                System.out.println("[Error]: JSON file was unable to be created try again!");
            } finally {

                if (wr != null) {
                    try {
                        //close the file reader
                        wr.close();
                        os.close();
                    } catch (IOException e) {
                        System.out.println("Unable to close resource");
                    }
                }
            }
        }
    }

    /**
    * This method is used to read the JSON file.
    */
    public void readFromJSON() {

        //create a JSON parser to read JSON
        JSONParser parser = new JSONParser();
        BufferedReader reader = null;
        InputStream in = null;
        Reader read = null;

        //try to parse students
        try {

            //use file reader to read in JSON at this location
            in = new FileInputStream("src/main/resources/gymfiles/students.json");
            read = new InputStreamReader(in, StandardCharsets.UTF_8);
            reader = new BufferedReader(read);
            System.out.println("[Debug]: students.json was parsed!");

            //put the parsed JSON Object into an Object type variable
            Object obj = parser.parse(reader);
            //cast this object type to a JSONObject
            JSONObject studentInJson = (JSONObject) obj;
            // go ahead and get the Students Array from the JSON
            JSONArray studentArray = (JSONArray) studentInJson.get("students");

            //make sure the arraylists are reset
            allTrainersInGym = new ArrayList<Student>();
            allStudentsInGym = new ArrayList<Student>();
            //iterate through the whole array
            for (int i = 0; i < studentArray.size(); i++) {
                //get the object at this index of JSON Array
                JSONObject studentHere = (JSONObject) studentArray.get(i);

                //use the get methods to get specific fields of the student
                String name = (String) studentHere.get("name");
                String password = (String) studentHere.get("password");
                String belt = (String) studentHere.get("beltLevel");

                boolean isTrainer = (Boolean) studentHere.get("trainer");
                if (isTrainer == true) {
                    // get the training rank of student
                    String trainingRankFromJson = (String) studentHere.get("rank");
                    boolean isOwner = (Boolean) studentHere.get("owner");
                    //create instructor
                    Instructor t = new Instructor(name,password,belt,trainingRankFromJson, isOwner);
                    //set belt level
                    t.setBeltLevel(belt);
                    //add to all trainers in gym
                    allTrainersInGym.add(t);
                    if (isOwner == true) {
                        owner = t;
                    }
                } else {
                    //create a student object
                    Student s = new Student(name,password);
                    //set it's belt level to that of what the file has provided
                    s.setBeltLevel(belt);
                    //add student to all students in gym
                    allStudentsInGym.add(s);
                }
            }

            //print sucess message
            System.out.println("[Debug]: Students from JSON have been added sucessfully!");
            
        } catch (Exception e) {
            //if there are any error go ahead and catch them and display error
            System.out.println("[Error]: students.json not found in" 
                + "directory and/or parsing failed check format!");
            System.out.println(e);
            return;
        } finally {

            if (reader != null) {
                try {
                    //clean up reader
                    reader.close();
                    read.close();
                    in.close();
                } catch (Exception e) {
                    System.out.println("Unable to close resource");
                }
            }
        }
    }

    /**
    * This method is to help determine if the person is an instructor or student.
    * @param  name  the name of the person
    * @return int 1 for student and 0 for trainer
    */
    public int whatAreYou(String name) {
        //loop over the list of students in the gym
        for (int i = 0; i < allStudentsInGym.size(); i++) {
            Student here = allStudentsInGym.get(i);

            //if the the name correponds with a studen then do this and return 1
            if (here.getName().equals(name)) {
                System.out.println("[Debug]: We are writing a Student: " + name + "!");
                return 1;
            }
        }

        //loop over the list of trainers in the gym
        for (int i = 0; i < allTrainersInGym.size(); i++) {
            Student here = allTrainersInGym.get(i);

            //if the the name correponds with a trainer then do this and return 0
            if (here.getName().equals(name)) {
                System.out.println("[Debug]: We are writing a Trainer: " + name + "!");
                return 0;
            }
        }

        //in all other cases go ahead and return -1;
        return -1;
    }


}