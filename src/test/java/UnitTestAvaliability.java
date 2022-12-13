/*
  File: Avaliability
  .java
  Author: Arnold Shibu-Thomas   
  Date: 11/28/2022
  
  Description: This class is a junit test for all the methods that are in Avaliability.java
*/
import org.junit.Assert;
import java.util.ArrayList; 
import org.junit.Test;
import main.java.memoranda.gymAssets.Avaliability;

public class UnitTestAvaliability{
  private Avaliability avaliability;

  @org.junit.Before
  public void setUp() throws Exception {
    avaliability = new Avaliability();
  }

  //There is minimal branches in this code so this implements both whitebox and blackbox:

  //This tests ensure that we can setup the avalaiability as it supposed to be
  @Test
  public void testSettingStrings(){

    //add a valid avaliability
    avaliability.setTrainer("Jones");
    avaliability.setStartTime("09:00");
    avaliability.setEndTime("10:00");
    Assert.assertEquals("Jones", avaliability.getTrainer());
    Assert.assertEquals("09:00", avaliability.getStartTime());
    Assert.assertEquals("10:00", avaliability.getEndTime());

    //add a valid avaliability
    avaliability.setTrainer("");
    avaliability.setStartTime("");
    avaliability.setEndTime("");
    Assert.assertEquals("", avaliability.getTrainer());
    Assert.assertEquals("", avaliability.getStartTime());
    Assert.assertEquals("", avaliability.getEndTime());

    //add null
    avaliability.setTrainer(null);
    avaliability.setStartTime(null);
    avaliability.setEndTime(null);
    Assert.assertEquals("", avaliability.getTrainer());
    Assert.assertEquals("00:00", avaliability.getStartTime());
    Assert.assertEquals("00:00", avaliability.getEndTime());

  }

  //this will test to see if the days are read properly
  @Test
  public void testSettingdaysFromString() {
    //if we are able to do it with zero 
    avaliability.setDaysFromString("");
    Assert.assertEquals(avaliability.getDays().size(), 0);

    //if we give it days of null
    avaliability.setDaysFromString(null);
    Assert.assertEquals(avaliability.getDays().size(), 0);

    //if we have 1 or more than 1 day
    avaliability.setDaysFromString("Monday Tuesday");
    Assert.assertEquals(avaliability.getDays().size(), 2);
  }

  //this will test to see if we get the right string
  @Test
  public void testGettingDaysAsString() {
    //if we are able to do it with zero 
    avaliability.setDaysFromString("");
    Assert.assertEquals(avaliability.getDaysAsString(), "");

    //if we give it days of null
    avaliability.setDaysFromString(null);
    Assert.assertEquals(avaliability.getDaysAsString(), "");

    //if we have 1 or more than 1 day
    avaliability.setDaysFromString("Monday Tuesday");
    Assert.assertEquals(avaliability.getDaysAsString(), "Monday Tuesday");

    //if we have 1 or more than 1 day with something wrong
    avaliability.setDaysFromString("Monday Tuesday Wed");
    Assert.assertEquals(avaliability.getDaysAsString(), "Monday Tuesday ");

    //if we have 1 or more than 1 day with all days
    avaliability.setDaysFromString("Monday Tuesday Wednesday Thursday Friday Saturday Sunday");
    Assert.assertEquals(avaliability.getDaysAsString(), "Monday Tuesday Wednesday Thursday Friday Saturday Sunday ");
  }

  //this will test to see if we get days
  @Test
  public void testGettingDays() {
    //if we have 1 or more than 1 day with all days
    avaliability.setDaysFromString("Monday Tuesday Wednesday Thursday Friday Saturday Sunday");
    Assert.assertEquals(avaliability.hasDay("Monday"), true);
    Assert.assertEquals(avaliability.hasDay("monday"), false);
    Assert.assertEquals(avaliability.getDays().size(), 7);

    avaliability = new Avaliability();
    //if we have 1 or more than 1 day with something wrong
    avaliability.setDaysFromString("Monday Tuesday Wed");
    Assert.assertEquals(avaliability.hasDay("Monday"), true);
    Assert.assertEquals(avaliability.hasDay("monday"), false);
    Assert.assertEquals(avaliability.getDays().size(), 3);

    avaliability = new Avaliability();
    //if we are able to do it with zero 
    avaliability.setDaysFromString("");
    Assert.assertEquals(avaliability.hasDay("Monday"), false);
    Assert.assertEquals(avaliability.hasDay("monday"), false);
    Assert.assertEquals(avaliability.getDays().size(), 0);

    avaliability = new Avaliability();
    //if we give it days of null
    avaliability.setDaysFromString(null);
    Assert.assertEquals(avaliability.hasDay("Monday"), false);
    Assert.assertEquals(avaliability.hasDay("monday"), false);
    Assert.assertEquals(avaliability.getDays().size(), 0);
  }

  //this will test to see if we get the time as an int
  @Test
  public void testTimeAsInt() {

    //add a valid avaliability
    avaliability.setTrainer("Jones");
    avaliability.setStartTime("09:00");
    avaliability.setEndTime("10:00");

    //check if the conversion worked
    Assert.assertEquals(avaliability.getTimeAsInt(0),9);
    Assert.assertEquals(avaliability.getTimeAsInt(1),10);

    //change start and end times
    avaliability.setStartTime("00:00");
    avaliability.setEndTime("23:00");
    //check if the conversion worked
    Assert.assertEquals(avaliability.getTimeAsInt(0),0);
    Assert.assertEquals(avaliability.getTimeAsInt(1),23);

    //try some invalid options
    avaliability.setStartTime(null);
    avaliability.setEndTime(null);
    //check if the conversion worked
    Assert.assertEquals(avaliability.getTimeAsInt(0),0);
    Assert.assertEquals(avaliability.getTimeAsInt(1),0);
  }

}