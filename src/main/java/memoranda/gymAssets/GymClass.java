/*
  File:	GymClass.Java
  Author: Arnold Shibu-Thomas
  Date:	10/27

  Description: This file contains the class for gym classes for private and public classes
*/

/**
 Class:GymClass

 Description:This class contains interact with the gymclass whether public or private
 */
package main.java.memoranda.gymAssets;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class GymClass {

    //private instance variables
    private boolean isPublic;
    private List<Student> students;
    private int classSize;
    private Instructor teacher;
    private boolean advanced;
    LocalDate date;
    

    /**
     * Default constructor for Gym Class.
     */
    public GymClass(){
        students =  new ArrayList<Student>(); //student list for the class
        isPublic = true; //by default class will public
        classSize = 20; //by default class max will be 20 students.
        teacher = new Instructor(); //creates a blank instructor
        setAdvanced(false); //By default classes are beginner level.
        LocalDate testDate = LocalDate.now();
        setDate(testDate);//sets date as empty         
    }

    /**
     * Parameterized constructor for Gym Class.
     *
     * @param isPublic tells if class is public
     * @param teacher is the instructor for the class
     * @param advanced determines if this is an advanced class or not
     * @param date is a temp variable until we can better set dates for classes
     */
    public GymClass(boolean isPublic, Instructor teacher, boolean advanced,LocalDate date){
        if(isPublic == true){
            this.isPublic = isPublic;         
            classSize = 20; //public classes can have up to 20 students
        }
        else{
            this.isPublic = isPublic;
            classSize = 1; //private classes can have only 1 student
        }        
        this.setAdvanced(advanced); //sets if class is advanced or not
        this.setDate(date); //sets current date for class
        this.teacher = teacher; //Sets teacher for class
        students =  new ArrayList<Student>(); //creates student list for the class
        
    }	
    
    /**
     * Change teacher.
     *
     * @param teacher this is an Instructor class object that will be the new instructor.
    
     */    
    public void setTeacher(Instructor teacher) {
    	this.teacher = teacher;
    }
    /**
     * returns the Instructor class object of the current teachers of the class
     * @return Instructor
     */
    public Instructor getTeacher() {
    	return teacher;
    }
    /**
     * returns a boolean of if the class is private or not.
     * @return boolean
     */
	public boolean isPublic() {
		return isPublic;
	}
	/**
     * Returns the entire list of students
     * @return List<Student> the list of current students.
     */
	public List<Student> getStudents() {
		return students;
	}
	/**
     * this method will add a student to the list if the class max hasn't been reached. 
     *
     * @param newStudent single student class object
     * @return boolean if was successful or not
     */
	public boolean addStudent(Student newStudent) {
		boolean error = true;
        System.out.println(students.size() + " " + classSize);
        if(students.contains(newStudent)) { //Added in validation to avoid student enrolling twice
            System.out.println("contains");
            return true;
        }
		if(classSize > students.size()) { //Changed operator to reflect correct comparison
            System.out.println("adding");
			students.add(newStudent);
			error = false;
		}
		return error;
	}
	/**
     * Returns the boolean value of if the class is advanced or not
     *
     * @return boolean
     */
	public boolean isAdvanced() {
		return advanced;
	}
	/**
     * Allows the class to be flipped to an advanced class after creation
     *
     * 
     * @param advanced this is a boolean value of if the class is advanced or not.
     */
	public void setAdvanced(boolean advanced) {
		this.advanced = advanced;
	}
	/**
     *gets the current date value for the class.
     *
     *@return String returns a String value of the date for the class
     */
	public LocalDate getDate() {
		return date;
	}
	/**
     * Allows the class date to be changed.
     *
     * @param date the String value of the date
     */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
}
