/*
  File: UnitTestStudent.java
  Author: Arnold Shibu-Thomas   
  Date: 11/7/2022
  
  Description: This class is a junit test for all the methods that are in student.java
*/
import org.junit.Assert;
import org.junit.Test;
import  main.java.memoranda.gymAssets.Student;

public class UnitTestStudent{
  private Student student;

  @org.junit.Before
  public void setUp() throws Exception {
    student = new Student();
  }

  //since this class has no branches (if else statements the test will be both white and black box)
  @Test
  public void UnitTestingCreatingStudent(){

    //default constructor
    String name = student.getName();
    String password = student.getPassword();
    String belt = student.getBeltLevel();
    Assert.assertEquals(name,"John Doe");
    Assert.assertEquals(password,"password");
    Assert.assertEquals(belt,"white");

    //with overridden constructor
    student = new Student("Test", "pass");
    name = student.getName();
    password = student.getPassword();
    belt = student.getBeltLevel();
    Assert.assertEquals(name,"Test");
    Assert.assertEquals(password,"pass");
    Assert.assertEquals(belt,"white");
  }

  @Test
  public void UnitTestingChangingName(){

    //default constructor
    student.setName("hello");
    String name = student.getName();
    String password = student.getPassword();
    String belt = student.getBeltLevel();
    Assert.assertEquals(name,"hello");
    Assert.assertEquals(password,"password");
    Assert.assertEquals(belt,"white");

    //with overridden constructor
    student = new Student("Test", "pass");
    student.setName("hi");
    name = student.getName();
    password = student.getPassword();
    belt = student.getBeltLevel();
    Assert.assertEquals(name,"hi");
    Assert.assertEquals(password,"pass");
    Assert.assertEquals(belt,"white");
  }

  @Test
  public void UnitTestingChangingPassword(){

    //default constructor
    student.setPassword("hello");
    String name = student.getName();
    String password = student.getPassword();
    String belt = student.getBeltLevel();
    Assert.assertEquals(name,"John Doe");
    Assert.assertEquals(password,"hello");
    Assert.assertEquals(belt,"white");

    //with overridden constructor
    student = new Student("Test", "pass");
    student.setPassword("hi");
    name = student.getName();
    password = student.getPassword();
    belt = student.getBeltLevel();
    Assert.assertEquals(name,"Test");
    Assert.assertEquals(password,"hi");
    Assert.assertEquals(belt,"white");
  }

  @Test
  public void UnitTestingChangingBeltToImproperColor(){

    //default constructor
    student.setBeltLevel("hello");
    String name = student.getName();
    String password = student.getPassword();
    String belt = student.getBeltLevel();
    Assert.assertEquals(name,"John Doe");
    Assert.assertEquals(password,"password");
    Assert.assertEquals(belt,"white");

    //with overridden constructor
    student = new Student("Test", "pass");
    student.setBeltLevel("");
    name = student.getName();
    password = student.getPassword();
    belt = student.getBeltLevel();
    Assert.assertEquals(name,"Test");
    Assert.assertEquals(password,"pass");
    Assert.assertEquals(belt,"white");

    //with overridden constructor
    student = new Student("Test", "pass");
    student.setBeltLevel(" ");
    name = student.getName();
    password = student.getPassword();
    belt = student.getBeltLevel();
    Assert.assertEquals(name,"Test");
    Assert.assertEquals(password,"pass");
    Assert.assertEquals(belt,"white");
  }

  @Test
  public void UnitTestingChangingBeltToProperColor(){

    //default constructor
    student.setBeltLevel("yellow");
    String name = student.getName();
    String password = student.getPassword();
    String belt = student.getBeltLevel();
    Assert.assertEquals(name,"John Doe");
    Assert.assertEquals(password,"password");
    Assert.assertEquals(belt,"yellow");

    //with overridden constructor
    student = new Student("Test", "pass");
    student.setBeltLevel("y");
    name = student.getName();
    password = student.getPassword();
    belt = student.getBeltLevel();
    Assert.assertEquals(name,"Test");
    Assert.assertEquals(password,"pass");
    Assert.assertEquals(belt,"yellow");

    //with overridden constructor
    student = new Student("Test", "pass");
    student.setBeltLevel("Y");
    name = student.getName();
    password = student.getPassword();
    belt = student.getBeltLevel();
    Assert.assertEquals(name,"Test");
    Assert.assertEquals(password,"pass");
    Assert.assertEquals(belt,"yellow");

    //with overridden constructor
    student = new Student("Test", "pass");
    student.setBeltLevel("Yellow");
    name = student.getName();
    password = student.getPassword();
    belt = student.getBeltLevel();
    Assert.assertEquals(name,"Test");
    Assert.assertEquals(password,"pass");
    Assert.assertEquals(belt,"yellow");
  }

}