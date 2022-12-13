package main.java.memoranda.gymAssets;

public class Student {
	private String name;
	private String password;
	private Belt beltLevel;
	/**
     *default constructor
     */
	public Student() {
		name = "John Doe";
		password = "password";
		//create a new beltLevel Object
		beltLevel = new Belt();
	}
	/**
     * Parameterized constructor for Student Class.
     *
     * @param name String value of the student's name
     * @param password 
     */
	public Student(String name, String password) {
		this.name = name;
		this.password = password;
		beltLevel = new Belt();
	}
	/**
     * Parameterized constructor for Student Class.
     *
     * @return returns the String value of the name
     */
	public String getName() {
		return name;
	}
	/**
     * Sets the Student's name
     *
     * @param name String value of the student's name
     */
	public void setName(String name) {
		this.name = name;
	}
	/**
     * Gets the password
     *
     * @return returns the password for the student. 
     */
	public String getPassword() {
		return password;
	}
	/**
     * Updates the user's password
     *
     * @param password string value of the user's password. 
     */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
     * Gets the user's belt level
     *
     * @return beltLevel
     */
	public String getBeltLevel() {
		return beltLevel.getBelt(); //get the belt level of the student and trainer
	}
	/**
     * Sets the student's belt level
     *
     * @param belt sets the string value of the beltLevel
     */
	public void setBeltLevel(String belt) {
		beltLevel.setBelt(belt); //change belt level of student and trainer
	}	
}
