/*
  File: UnitTestInstructor.java
  Author: Arnold Shibu-Thomas   
  Date: 11/6/2022
  
  Description: This class is a junit test for all the methods that are in instructor.java
*/
import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList; 
import org.junit.Test;
import  main.java.memoranda.gymAssets.Instructor;
import  main.java.memoranda.gymAssets.GymClass;

public class UnitTestInstructor{

	private Instructor trainer;

	@org.junit.Before
    public void setUp() throws Exception {
        trainer = new Instructor();
    }

    //Black Box Testing starts here
    @Test
    public void blackBoxTestCreatingTrainers(){

    	//creating a regular trainer
    	String name = trainer.getName();
    	String password = trainer.getPassword();
    	String belt = trainer.getBeltLevel();
    	String rank = trainer.getTrainingRank();
    	boolean isOwner = trainer.owner();

    	Assert.assertEquals(name, "John Doe");
    	Assert.assertEquals(password, "password");
    	Assert.assertEquals(belt, "white");
    	Assert.assertEquals(rank, "white");
    	Assert.assertEquals(isOwner, false);

    	//creating a parameterized trainer
    	trainer = new Instructor(false);
    	
    	name = trainer.getName();
    	password = trainer.getPassword();
    	belt = trainer.getBeltLevel();
    	rank = trainer.getTrainingRank();
    	isOwner = trainer.owner();

    	Assert.assertEquals(name, "John Doe");
    	Assert.assertEquals(password, "password");
    	Assert.assertEquals(belt, "white");
    	Assert.assertEquals(rank, "white");
    	Assert.assertEquals(isOwner, false);

    
    	//creating a parameterized trainer 
    	trainer = new Instructor("Bob", "123", "yellow","white", false);

    	name = trainer.getName();
    	password = trainer.getPassword();
    	belt = trainer.getBeltLevel();
    	rank = trainer.getTrainingRank();
    	isOwner = trainer.owner();

    	Assert.assertEquals(name, "Bob");
    	Assert.assertEquals(password, "123");
    	Assert.assertEquals(belt, "yellow");
    	Assert.assertEquals(rank, "white");
    	Assert.assertEquals(isOwner, false);

    }

    @Test
    public void blackBoxTestCreatingOwners(){
    	//creating an owner trainer
    	trainer = new Instructor(true);

    	String name = trainer.getName();
    	String password = trainer.getPassword();
    	String belt = trainer.getBeltLevel();
    	String rank = trainer.getTrainingRank();
    	boolean isOwner = trainer.owner();

    	Assert.assertEquals(name, "John Doe");
    	Assert.assertEquals(password, "password");
    	Assert.assertEquals(belt, "white");
    	Assert.assertEquals(rank, "white");
    	Assert.assertEquals(isOwner, true);

    	//creating a parameterized owner 
    	trainer = new Instructor("Joe", "helloWorld", "black1","brown3", true);

    	name = trainer.getName();
    	password = trainer.getPassword();
    	belt = trainer.getBeltLevel();
    	rank = trainer.getTrainingRank();
    	isOwner = trainer.owner();

    	Assert.assertEquals(name, "Joe");
    	Assert.assertEquals(password, "helloWorld");
    	Assert.assertEquals(belt, "black1");
    	Assert.assertEquals(rank, "brown3");
    	Assert.assertEquals(isOwner, true);

    }

    @Test
    public void blackBoxCheckOwner(){
    	//creating a parameterized owner and checking
    	trainer = new Instructor("Joe", "helloWorld", "black1","brown3", true);
    	boolean isOwner = trainer.owner();
    	Assert.assertEquals(isOwner, true);

    	//creating a parameterized owner and checking
    	trainer = new Instructor(true);
    	isOwner = trainer.owner();
    	Assert.assertEquals(isOwner, true);

    	//creating a parameterized trainer who is not the owner and checking
    	trainer = new Instructor(false);
    	isOwner = trainer.owner();
    	Assert.assertEquals(isOwner, false);

    	//creating a default trainer who is not the owner and checking
    	trainer = new Instructor();
    	isOwner = trainer.owner();
    	Assert.assertEquals(isOwner, false);
    }

    @Test
    public void blackBoxCheckTrainingRankMethodsWithDefaultValue(){
    	//creating a default trainer and checking thier rank
    	trainer = new Instructor();
    	String rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "white");

    	//creating a default trainer and checking thier rank
    	trainer = new Instructor(false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "white");

    }

    @Test
    public void blackBoxCheckTrainingRankWithOwnerSpecifiedCorrectly(){

    	//creating a instructor of rank white
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","white", false);
    	String rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "white");

    	//creating a instructor of rank yellow
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","yellow", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "yellow");

    	//creating a instructor of rank orange
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","orange", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "orange");

    	//creating a instructor of rank purple
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","purple", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "purple");

    	//creating a instructor of rank blue
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","blue", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "blue");

    	//creating a instructor of rank blue stripe
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","blue stripe", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "blue stripe");

    	//creating a instructor of rank green
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","green", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "green");

    	//creating a instructor of rank green stripe
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","green stripe", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "green stripe");

    	//creating a instructor of rank brown1
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","brown1", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "brown1");

    	//creating a instructor of rank brown2
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","brown2", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "brown2");

    	//creating a instructor of rank brown 3
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","brown3", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "brown3");

    	//creating a instructor of rank black1
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","black1", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "black1");

    	//creating a instructor of rank black2
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","black2", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "black2");

    	//creating a instructor of rank black3
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","black3", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "black3");
    }

    @Test
    public void blackBoxCheckTrainingRankWithOwnerSpecifiedIncorrectly(){

    	//creating a instructor of rank gray which is a color that doesn't exist
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","gray", false);
    	String rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "white");

    	//creating a instructor of rank nothing that doesn't exist
    	trainer = new Instructor("Joe", "helloWorld", "blakc1","", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "white");

    	//creating a instructor of rank space that doesn't exist
    	trainer = new Instructor("Joe", "helloWorld", "blakc1"," ", false);
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "white");
    }

    @Test
    public void blackBoxTestSetTrainingRankMethods(){

    	//try setting the training rank
    	trainer = new Instructor();
    	trainer.setTrainingRank("blue");
    	String rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "blue");

    	//try to set rank to black1
    	trainer = new Instructor(true);
    	trainer.setTrainingRank("black1");
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "black1");

    	//try setting trainer rank to black2
    	trainer = new Instructor("Joe", "pass","white","yellow",false);
    	trainer.setTrainingRank("black2");
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "black2");

    	//try setting the training rank to a misspelled rank
    	trainer = new Instructor();
    	trainer.setTrainingRank("ble");
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "white");

    	//try to set rank to nothing
    	trainer = new Instructor(true);
    	trainer.setTrainingRank("");
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "white");

    	//try to set rank to a space
    	trainer = new Instructor("Joe", "pass","white","",false);
    	trainer.setTrainingRank(" ");
    	rank = trainer.getTrainingRank();
    	Assert.assertEquals(rank, "white");
    }

    @Test
    public void blackBoxSchedulePublicClass(){

      //if the owner wants to schedule public class
      ArrayList<GymClass> classes =  new ArrayList<GymClass>();
      trainer = new Instructor(true);
      trainer.schedulePublicClass(trainer,true,LocalDate.now(), classes);
      Assert.assertEquals(classes.size(),1);

      //owner makes a public class
      classes =  new ArrayList<GymClass>();
      Instructor t = new Instructor(false);
      trainer = new Instructor(true);
      LocalDate temp = LocalDate.now();
      trainer.schedulePublicClass(t,true,temp, classes);
      Assert.assertEquals(classes.size(),1);

      //add a duplicate class
      trainer.schedulePublicClass(t,true,temp, classes);
      Assert.assertEquals(classes.size(),1);

      //try to schedule a private class during a public class
      trainer = new Instructor(false);
      trainer.schedulePublicClass(t,true,temp, classes);
      Assert.assertEquals(classes.size(),1);


    }

    //WhiteBox Testing Here
    @Test
    public void whiteBoxTestCreatingTrainers(){

      //with default constructor
      trainer = new Instructor();
      Assert.assertEquals(trainer.getName(), "John Doe");

      //with overriden constructor
      trainer = new Instructor(false);
      Assert.assertEquals(trainer.getName(), "John Doe");
      trainer = new Instructor(true);
      Assert.assertEquals(trainer.getName(), "John Doe");

      //with other overriden constructor
      trainer = new Instructor("Joe", "pass","white","yellow",false);
      Assert.assertEquals(trainer.getName(), "Joe");
      trainer = new Instructor("Joe", "pass","white","yellow",true);
      Assert.assertEquals(trainer.getName(), "Joe");

      //creating a instructor of rank white
      trainer = new Instructor("Joe", "helloWorld", "blakc1","white", false);
      String rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "white");

      //creating a instructor of rank yellow
      trainer = new Instructor("Joe", "helloWorld", "blakc1","yellow", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "yellow");

      //creating a instructor of rank orange
      trainer = new Instructor("Joe", "helloWorld", "blakc1","orange", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "orange");

      //creating a instructor of rank purple
      trainer = new Instructor("Joe", "helloWorld", "blakc1","purple", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "purple");

      //creating a instructor of rank blue
      trainer = new Instructor("Joe", "helloWorld", "blakc1","blue", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "blue");

      //creating a instructor of rank blue stripe
      trainer = new Instructor("Joe", "helloWorld", "blakc1","blue stripe", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "blue stripe");

      //creating a instructor of rank green
      trainer = new Instructor("Joe", "helloWorld", "blakc1","green", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "green");

      //creating a instructor of rank green stripe
      trainer = new Instructor("Joe", "helloWorld", "blakc1","green stripe", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "green stripe");

      //creating a instructor of rank brown1
      trainer = new Instructor("Joe", "helloWorld", "blakc1","brown1", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "brown1");

      //creating a instructor of rank brown2
      trainer = new Instructor("Joe", "helloWorld", "blakc1","brown2", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "brown2");

      //creating a instructor of rank brown 3
      trainer = new Instructor("Joe", "helloWorld", "blakc1","brown3", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "brown3");

      //creating a instructor of rank black1
      trainer = new Instructor("Joe", "helloWorld", "blakc1","black1", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "black1");

      //creating a instructor of rank black2
      trainer = new Instructor("Joe", "helloWorld", "blakc1","black2", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "black2");

      //creating a instructor of rank black3
      trainer = new Instructor("Joe", "helloWorld", "blakc1","black3", false);
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "black3");

    }

    @Test
    public void whiteBoxTestCheckingOwner(){

      //check owner when not
      trainer = new Instructor("Joe", "pass","white","yellow",false);
      Assert.assertEquals(trainer.owner(), false);

      //check owner when you are the owner
      trainer = new Instructor("Joe", "pass","white","yellow",true);
      Assert.assertEquals(trainer.owner(), true);
    }

    @Test
    public void whiteBoxTestTrainingRank(){

      //check owner when not
      trainer = new Instructor("Joe", "pass","white","yellow",false);
      trainer.setTrainingRank("Orange");
      String rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "orange");

      //check owner when you are the owner
      trainer = new Instructor("Jane", "pass","white","yellow",true);
      trainer.setTrainingRank("black1");
      rank = trainer.getTrainingRank();
      Assert.assertEquals(rank, "black1");

    }

    @Test
    public void whiteBoxTestSchedulingClass(){

      //if the owner wants to schedule public class
      ArrayList<GymClass> classes =  new ArrayList<GymClass>();
      trainer = new Instructor(true);      
      trainer.schedulePublicClass(trainer,true,LocalDate.of(2022, 11, 20), classes);
      Assert.assertEquals(classes.size(),1);

      //owner makes a public class
      classes =  new ArrayList<GymClass>();
      Instructor t = new Instructor(false);
      trainer = new Instructor(true);
      trainer.schedulePublicClass(t,true,LocalDate.of(2022, 11, 20), classes);
      Assert.assertEquals(classes.size(),1);

      //add a duplicate public class
      trainer.schedulePublicClass(t,true,LocalDate.of(2022, 11, 20), classes);
      Assert.assertEquals(classes.size(),1);

      //try to schedule a private class during a public class
      trainer = new Instructor(false);
      trainer.schedulePublicClass(t,true,LocalDate.of(2022, 11, 21), classes);
      Assert.assertEquals(classes.size(),2);

      //try to schedule a private class that is duplicate
      trainer = new Instructor(false);
      trainer.schedulePublicClass(t,true,LocalDate.of(2022, 11, 21), classes);
      Assert.assertEquals(classes.size(),2);

      //another trainer wants to schedule a private class at the same time
      trainer = new Instructor("Joe", "pass","white","yellow",false);
      trainer.schedulePublicClass(t,true,LocalDate.of(2022, 11, 21), classes);
      Assert.assertEquals(classes.size(),2);

      //owner overrides a private class with no students
      trainer = new Instructor("House", "pass","white","yellow",true);
      trainer.schedulePublicClass(t,true,LocalDate.of(2022, 11, 21), classes);
      Assert.assertEquals(classes.size(),3);

    }
}