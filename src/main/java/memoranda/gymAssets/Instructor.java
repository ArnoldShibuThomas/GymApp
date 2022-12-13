package main.java.memoranda.gymAssets;

import java.time.LocalDate;
import java.util.ArrayList; // import the ArrayList class
import java.util.List;

public class Instructor extends Student {

	//instance variable 
	private Belt trainingRank;
	private boolean isOwner; 
	

	/**
    * Default constructor for Instructor Class.
    * Default values of used
    * Name = "John Doe"
    * Password = "password"
    * beltLevel = "white"
    * owner = false
    */
	public Instructor() {
		trainingRank = new Belt();
		setName("John Doe");
		setPassword("password");
		setBeltLevel("White");
		isOwner = false; 
	}

	/**
    * Parameterized constructor for Instructor Class.
    * Default values of used
    * Name = "John Doe"
    * Password = "password"
    * beltLevel = "white"
    * owner = false
    *
    * @param owner is true if the user is the owner and false otherwise
    */
	public Instructor(boolean owner) {
		trainingRank = new Belt();
		setName("John Doe");
		setPassword("password");
		setBeltLevel("White");
		isOwner = owner; 
	}

	/**
     * Parameterized constructor for Instructor Class.
     *
     * @param name gets the name for the instructor 
     * @param password get the password for the instructor
     * @param beltLevel gets the prefered belt level of this trainer
     * @param training get the training level the train is 
     * @param owner is true if the user is the owner and false otherwise
     */
	public Instructor(String name, String password, String beltLevel, String training, boolean owner) {
		trainingRank = new Belt();
		setTrainingRank(training);
		setName(name);
		setPassword(password);
		setBeltLevel(beltLevel);
		isOwner = owner; 
	}

	/**
     * This method returns true if the trainer is the owner
     */
	public boolean owner(){
		return isOwner;
	}

	/**
     * This method gets the training rank of the trainer
     */
	public String getTrainingRank() {
		return trainingRank.getBelt(); //get the training rank of trainer here
	}

	/**
     * This method sets the training rank.
     * 
     * @param training this is the training rank we want to set the trainer to
     */
	public void setTrainingRank(String training) {
		trainingRank.setBelt(training); //set the trainer's belt level
	
	}

	/**
     * Methods that allows owner to add a class
     *
     * @param teacher specifies instrcutor for class it can be the owner
     * @param advanced specieifes if the class is advanced or not
     * @param date the date when this class is
     * @param classes the arraylist of classes
     */
	public void schedulePublicClass(Instructor teacher, boolean advanced,LocalDate date,ArrayList<GymClass> classes){
		//if you are the owner then you can schedule yourself as well here
		if(isOwner == true){

			//check to see if this is booked
			boolean booked = false;
			//check to see if the trainer is booked at that time
			for(int i = 0; i < classes.size(); i++){
				GymClass current = classes.get(i);
				//if the teacher is the same
				if(current.getTeacher().getName().equals(teacher.getName())){
					//if the time is the same as the time on this class then it is booked
					if(current.getDate().equals(date)){
						if(current.isPublic() == true){
							booked = true;
						}
						if(current.isPublic() == false){
							List<Student> person = current.getStudents();
							if(person.size() >= 1){
								booked = true;
							}
						}
					}
				}
			}

			if(booked == false){
				GymClass newClass = new GymClass(true,teacher,advanced,date);
				classes.add(newClass);
			}
		}
		else{
			boolean booked = false;
			//check to see if the trainer is booked at that time
			for(int i = 0; i < classes.size(); i++){
				GymClass current = classes.get(i);
				//if the teacher is the same
				if(current.getTeacher().getName().equals(teacher.getName())){
					//if the time is the same as the time on this class then it is booked
					if(current.getDate().equals(date)){
						booked = true;
					}
				}
			}

			if(booked == false){
				GymClass newClass = new GymClass(false,teacher,advanced,date);
				classes.add(newClass);
			}

		}
	}


}
