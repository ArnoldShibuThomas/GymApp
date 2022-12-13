package main.java.memoranda.util;

//import arraylists
import java.util.ArrayList;

//import some Gym aspects
import main.java.memoranda.gymAssets.Instructor;
import main.java.memoranda.gymAssets.Student;

import main.java.memoranda.util.JSONTool;

public class LoginCredentials {

    JSONTool reader;


    /**
     * This is the default constructor.
     */
    public LoginCredentials() {
        //create a JSON Tool just to read from the file
        reader = new JSONTool();
        //read from the JSON
        reader.readFromJSON();
    }

    /**
     * This method check if the userName and password is the owner.
     * @param name this is the name provided
     * @param password this is the password provided
     * @return boolean true if they are owner 
     */
    public boolean checkOwner(String name, String password) {

        if (name == null || password == null) { 
            return false;
        }

        //check to see if they are the owner and login
        Instructor owner = (Instructor) reader.getOwner();

        if (name.equals(owner.getName())) {

            if (password.equals(owner.getPassword())) {
                return true;
            }
        }

        return false;
    }

    
    /**
     * This method check if the userName and password is trainer or student.
     * @param type this is the type of user they want to compare to 
     * @param name this is the name provided
     * @param pass this is the password provided
     * @return boolean true if they are who they are 
     */
    public boolean credentialsMatch(int type, String name, String pass) {
        //check both of the lists
        ArrayList<Student> trainers = reader.getAllTrainers();
        ArrayList<Student> students = reader.getAllStudents();

        if (name == null || pass == null) { 
            return false;
        }

        //if the list they want to search through is trainer then type == 1
        if (type == 1) {
            // looks through all the trainers
            for (int i = 0; i < trainers.size(); i++) {
                //store that person in the a Student object
                Student here = trainers.get(i); 

                //check if the username match
                if (here.getName().equals(name)) {

                    //check if the password matches
                    if (here.getPassword().equals(pass)) {
                        //if the username and password match return true
                        return true;
                    }
                }
            }
        } else {
            // looks through all the students
            for (int i = 0; i < students.size(); i++) {
                //store that person in the a Student object
                Student here = students.get(i); 

                //check if the username match
                if (here.getName().equals(name)) {

                    //check if the password matches
                    if (here.getPassword().equals(pass)) {
                        //if the username and password match return true
                        return true;
                    }
                }
            }
        }
        
        //return false if the person doesn't exist in the records
        return false;
    }
}