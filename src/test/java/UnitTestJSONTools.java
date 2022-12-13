package test.java;/*
  File: UnitTestJSONTools
  .java
  Author: Arnold Shibu-Thomas   
  Date: 11/7/2022
  
  Description: This class is a junit test for all the methods that are in belt.java
*/
import org.junit.Assert;
import java.util.ArrayList; 
import org.junit.Test;
import  main.java.memoranda.util.JSONTool;
import  main.java.memoranda.gymAssets.Student;
import  main.java.memoranda.gymAssets.Instructor;

public class UnitTestJSONTools{
  private JSONTool tool;

  @org.junit.Before
  public void setUp() throws Exception {
    tool = new JSONTool();
  }

  //There is minimal branches in this code so this implements both whitebox and blackbox:

  //test with getting the tool lists a constructor
  @Test
  public void testCreatingJSONTool(){

    ArrayList<Student> trainers = tool.getAllTrainers();
    Assert.assertEquals(trainers.size(), 4); 

    ArrayList<Student> students = tool.getAllStudents();
    Assert.assertEquals(students.size(), 6); 
  }

  //test reading from a file
  @Test
  public void testReading(){

    //read from the JSON
    tool.readFromJSON();
    
    //check to see if all the trainers are in the system from file
    ArrayList<Student> trainers = tool.getAllTrainers();
    Assert.assertEquals(trainers.size(), 4); 

    //check if all the students are in the system from the file
    ArrayList<Student> students = tool.getAllStudents();
    Assert.assertEquals(students.size(), 6); 

    //check that it read the trainers properly
    Instructor trainer = (Instructor) trainers.get(0);
    Assert.assertEquals(trainer.getName(),"Jones");
    Assert.assertEquals(trainer.getTrainingRank(),"green");
    Assert.assertEquals(trainer.owner(),false);

    //check that it read the trainers properly
    Student student = students.get(0);
    Assert.assertEquals(student.getName(),"John");
    Assert.assertEquals(student.getBeltLevel(),"white");

    //check owner
    Student owner = tool.getOwner();
    Assert.assertEquals(owner.getName(),"Tim");

  }


  @Test
  public void testUpdatingListsOfStudents(){
    //create new tool
    tool = new JSONTool();

    //add a trainer
    Instructor t = new Instructor();
    //ad a student
    Student s = new Student();

    //add a trainer
    ArrayList<Student> trainers = tool.getAllTrainers();
    trainers.add(t);  
    tool.updateTrainersInJSON(trainers);

    //add student
    ArrayList<Student> students = tool.getAllStudents();
    students.add(s);
    tool.updateStudentsToJSON(students);

    //check if all the students are in the system from the file
    ArrayList<Student> str = tool.getAllStudents();
    Assert.assertEquals(str.size(), 8); 
  }

  @Test
  public void testUpdatingListsOfTrainers(){
    //create new tool
    tool = new JSONTool();

    //add a trainer
    Instructor t = new Instructor();

    //add a trainer
    ArrayList<Student> trainers = tool.getAllTrainers();
    trainers.add(t);  
    tool.updateTrainersInJSON(trainers);

    //check to see if all the trainers are in the system from file
    trainers = tool.getAllTrainers();
    Assert.assertEquals(trainers.size(), 5); 
  }

  @Test
  public void testWriting(){
    //create new tool
    tool = new JSONTool();

    //read
    tool.readFromJSON();

    //check to see if all the trainers are in the system from file
    ArrayList<Student> trs = tool.getAllTrainers();
    Assert.assertEquals(trs.size(), 4); 

    //check if all the students are in the system from the file
    ArrayList<Student> sts = tool.getAllStudents();
    Assert.assertEquals(sts.size(), 6); 

    //write to JSON
    tool.writeToJSON();

    //read
    tool.readFromJSON();

    //check to see if all the trainers are in the system from file
    ArrayList<Student> trainers = tool.getAllTrainers();
    Assert.assertEquals(trainers.size(), 4); 

    //check if all the students are in the system from the file
    ArrayList<Student> students = tool.getAllStudents();
    Assert.assertEquals(students.size(), 6); 

  }

  @Test
  public void testUpdatingStudentsAndTrainers(){
    tool.readFromJSON();

    //try to give an invalid list
    tool.updateTrainersInJSON(null);
    tool.updateStudentsToJSON(null);

    //check to see if all the trainers are in the system from file
    ArrayList<Student> trainers = tool.getAllTrainers();
    Assert.assertEquals(trainers.size(), 4); 

    //check if all the students are in the system from the file
    ArrayList<Student> students = tool.getAllStudents();
    Assert.assertEquals(students.size(), 6); 

    //try to give an empty list
    tool.updateTrainersInJSON(new ArrayList<Student>());
    tool.updateStudentsToJSON(new ArrayList<Student>());

    //check to see if all the trainers are in the system from file
    trainers = tool.getAllTrainers();
    Assert.assertEquals(trainers.size(), 4); 

    //check if all the students are in the system from the file
    students = tool.getAllStudents();
    Assert.assertEquals(students.size(), 6); 

    //try to add a students regularly
    students = tool.getAllStudents();
    students.add(new Student());

    trainers = tool.getAllTrainers();
    trainers.add(new Instructor());

    trainers.add(new Student("arnold","password"));
    
    //add student 
    tool.updateStudentsToJSON(students);
    tool.updateTrainersInJSON((ArrayList<Student>) trainers);

    //check to see if all the trainers are in the system from file
    trainers = tool.getAllTrainers();
    Assert.assertEquals(trainers.size(), 5); 

    //check if all the students are in the system from the file
    students = tool.getAllStudents();
    Assert.assertEquals(students.size(), 7); 

  }

  @Test
  public void testWhatAreYou(){
    tool.readFromJSON();
    int num = 0;
    //test reading in a null name
    num = tool.whatAreYou(null);
    Assert.assertEquals(num,-1);

    num = tool.whatAreYou("Tim");
    Assert.assertEquals(num,0);

    num = tool.whatAreYou("Bob");
    Assert.assertEquals(num,1);

    num = tool.whatAreYou("Joe");
    Assert.assertEquals(num,-1);

    num = tool.whatAreYou(" ");
    Assert.assertEquals(num,-1);
  }
}