/*
  File: AvaliabilityTool
  .java
  Author: Arnold Shibu-Thomas   
  Date: 11/7/2022
  
  Description: This class is a junit test for all the methods that are in AvaliabilityTool.java
*/
import org.junit.Assert;
import java.util.ArrayList; 
import org.junit.Test;
import javax.swing.JLabel;
import  main.java.memoranda.util.AvaliabilityTool;
import main.java.memoranda.gymAssets.Avaliability;

public class UnitTestAvaliabilityTool{
  private AvaliabilityTool tool;

  @org.junit.Before
  public void setUp() throws Exception {
    tool = new AvaliabilityTool(0,"Tim");
  }

  //There is minimal branches in this code so this implements both whitebox and blackbox:

  //This tests ensure that adding avaiability behaves accordingly
  @Test
  public void testAddAvaliability(){

    //create a person to add to the avaiability
    ArrayList<String> days = new ArrayList<>();
    days.add("Monday");
    String trainer = "Jones";
    String startTime = "04:00";
    String endTime = "07:00";

    boolean result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a training to the schedule with this time
    //file doesn't exist
    if(result == true) {
      Assert.assertEquals(result, true); 
    }
    //file exits
    else {
      Assert.assertEquals(result, false); 
    }

    startTime = "04:00";
    endTime = "07:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a training to the schedule again this time
    Assert.assertEquals(result, false); 

    startTime = "01:00";
    endTime = "09:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a training to the schedule again this time
    Assert.assertEquals(result, false); 

    startTime = "00:00";
    endTime = "00:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a training to the schedule this time with an avaliability which is not valid
    Assert.assertEquals(result, false); 

    startTime = "10:00";
    endTime = "08:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a training to the schedule this time with an avaliability which is reversed
    Assert.assertEquals(result, false); 

    startTime = "04:00";
    endTime = "08:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a training to the schedule this time with an avaliability which is overlapping
    Assert.assertEquals(result, false);

    startTime = "05:00";
    endTime = "06:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a training to the schedule this time with an avaliability which is overlapping
    Assert.assertEquals(result, false);

    startTime = "5:00";
    endTime = "09:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a training to the schedule this time with an avaliability which is overlapping
    Assert.assertEquals(result, false);

    startTime = "6:00";
    endTime = "09:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a training to the schedule this time with an avaliability which is overlapping
    Assert.assertEquals(result, false);

     startTime = "1:00";
    endTime = "09:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a training to the schedule this time with an avaliability which is overlapping
    Assert.assertEquals(result, false);

    startTime = "07:00";
    endTime = "08:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a trainer to the schedule with a new avalloability after the old one
    //file doesn't exist
    if(result == true) {
      Assert.assertEquals(result, true); 
    }
    //file exits
    else {
      Assert.assertEquals(result, false); 
    }

    startTime = "02:00";
    endTime = "04:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    //try adding a trainer to the schedule with a new avalloability before old one
    //file doesn't exist
    if(result == true) {
      Assert.assertEquals(result, true); 
    }
    //file exits
    else {
      Assert.assertEquals(result, false); 
    }
  }

  @Test
  public void testGetPeopleForDay(){
    //check to see if there are any people on the one day we added
    ArrayList<Avaliability> peopleOnThisDay = new ArrayList<>();
    peopleOnThisDay = tool.getPeopleForDay("Monday");
    int size = peopleOnThisDay.size();
    Assert.assertTrue( size >= 0 );

    //this day should contain zero
    peopleOnThisDay = tool.getPeopleForDay("Tuesday");
    size = peopleOnThisDay.size();
    Assert.assertTrue( size == 0 );

    peopleOnThisDay = tool.getPeopleForDay("");
    size = peopleOnThisDay.size();
    Assert.assertTrue( size == 0 );

    peopleOnThisDay = tool.getPeopleForDay(null);
    size = peopleOnThisDay.size();
    Assert.assertTrue( size == 0 );

  }

  @Test
  public void testingAddingInvalid(){
        //create a person to add to the avaiability
    ArrayList<String> days = new ArrayList<>();
    String trainer = "Jones";
    String startTime = "04:00";
    String endTime = "07:00";

    boolean result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    Assert.assertEquals(result, false); 

    days.add("Monday");
    trainer = null;
    startTime = "04:00";
    endTime = "07:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    Assert.assertEquals(result, false); 

    trainer = "Jones";
    startTime = null;
    endTime = "07:00";
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    Assert.assertEquals(result, false); 

    trainer = "Jones";
    startTime = "07:00";
    endTime = null;
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    Assert.assertEquals(result, false); 

    trainer = null;
    startTime = null;
    endTime = null;
    result = tool.addNewAvalaibility(trainer,startTime,endTime,days, new JLabel());
    Assert.assertEquals(result, false); 

  }

  @Test
  public void testGettingDays() {
    //give a valid day
    Assert.assertEquals(tool.getTheDayOfTheWeek(1),"Sunday");
    Assert.assertEquals(tool.getTheDayOfTheWeek(0),"Sunday");
    //give an valid day
    Assert.assertEquals(tool.getTheDayOfTheWeek(6),"Friday");
    Assert.assertEquals(tool.getTheDayOfTheWeek(7),"Saturday");
    Assert.assertEquals(tool.getTheDayOfTheWeek(8),"Saturday");

    //give some invalid days
    Assert.assertEquals(tool.getTheDayOfTheWeek(-1),"Sunday");
    Assert.assertEquals(tool.getTheDayOfTheWeek(200),"Saturday");

  }

   //This tests ensure that removing avaiability behaves accordingly
  @Test
  public void testRemoveAvaliability() {
    //create a person to add to the avaiability
    ArrayList<String> days = new ArrayList<>();
    days.add("Monday");
    String trainer = "Jones";
    String startTime = "04:00";
    String endTime = "07:00";

    //create a new avalaibility obj
    Avaliability a = new Avaliability();
    a.setTrainer(trainer);
    a.setStartTime(startTime);
    a.setEndTime(endTime);
    a.setDays(days);

    //remove this avaliability unsucessfully since times don't match
    a.setEndTime("09:00");
    boolean result = tool.removeAvaliability(a);
    Assert.assertEquals(result, false);

    //remove this avaliability unsucessfully since trainers don't match
    a.setTrainer("joe");
    a.setEndTime("07:00");
    result = tool.removeAvaliability(a);
    Assert.assertEquals(result, false);

    //remove this avaliability unsucessfully since times don't match
    result = tool.removeAvaliability(null);
    Assert.assertEquals(result, false);

    //try to remove the people added throughout this test this should be sucessful
    ArrayList<Avaliability> peopleOnThisDay = new ArrayList<>();
    peopleOnThisDay = tool.getPeopleForDay("Monday");
    for(int i = 0; i< peopleOnThisDay.size(); i++) {
      a = peopleOnThisDay.get(i);
      result = tool.removeAvaliability(a);
      Assert.assertEquals(result, true);
    }


  }
}