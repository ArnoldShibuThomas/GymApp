/*
  File: UnitTestBelt
  .java
  Author: Arnold Shibu-Thomas   
  Date: 11/7/2022
  
  Description: This class is a junit test for all the methods that are in belt.java
*/
import org.junit.Assert;
import java.util.ArrayList; 
import org.junit.Test;
import  main.java.memoranda.gymAssets.Belt;

public class UnitTestBelt{
  private Belt belt;

  @org.junit.Before
  public void setUp() throws Exception {
    belt = new Belt();
  }

  //BlackBoxTesting Starts here:

  @Test
  public void blackBoxTestCreatingBeltDefaultConstructor(){
    belt = new Belt();
    String color = belt.getBelt();
    Assert.assertEquals(color, "white"); 
  }

  @Test
  public void blackBoxTestCreatingBeltOverriddenConstructor(){
    belt = new Belt("");
    String color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    belt = new Belt("0");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    belt = new Belt("w");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

     belt = new Belt("W");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    belt = new Belt("white");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    belt = new Belt("White");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

  }

  @Test
  public void blackBoxTestGraduateBelt(){

    //test the graduation at the begining
    belt = new Belt();
    belt.graduateBelt();
    String color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 

    //test the graduation of a belt in the middle
    belt = new Belt("blue");
    belt.graduateBelt();
    color = belt.getBelt();
    Assert.assertEquals(color, "blue stripe");

    //close to the end
    belt = new Belt("black2");
    belt.graduateBelt();
    color = belt.getBelt();
    Assert.assertEquals(color, "black3");

    //test the graduation after the belt has reached max value
    belt = new Belt("black3");
    belt.graduateBelt();
    color = belt.getBelt();
    Assert.assertEquals(color, "black3");

  }

  @Test
  public void blackBoxTestSetBeltNormal(){

    //full name
    belt = new Belt();
    belt.setBelt("yellow");
    String color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 

    //full name capitilized
    belt = new Belt();
    belt.setBelt("Yellow");
    color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 

    //first intial lowercase
    //full name capitilized
    belt = new Belt();
    belt.setBelt("y");
    color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 

    //first intial capitilized
    //full name capitilized
    belt = new Belt();
    belt.setBelt("Y");
    color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 
  }

  @Test
  public void blackBoxTestSetBeltImproper(){

    //with index
    belt = new Belt();
    belt.setBelt("1");
    String color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 

    //with an invalid index
    belt = new Belt();
    belt.setBelt("20");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    //with nothing
    belt = new Belt();
    belt.setBelt("");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    //with something that doesn't exist
    belt = new Belt();
    belt.setBelt("biege");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    //with space
    belt = new Belt();
    belt.setBelt(" ");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

  }

  // WhiteBoxTesting Starts here:

  // Since the constructor wa tests with full coverge it wasn't tested again
  // Same with get belt, so agreed with team that the only thing missing was
  // the coverage for setBelt. That is why there isn't 5 tests here

  @Test
  public void whiteBoxTestSetBelt(){

    //full name lower case
    belt = new Belt();
    belt.setBelt("white");
    String color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("White");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("W");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("w");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    //index
    belt = new Belt();
    belt.setBelt("0");
    color = belt.getBelt();
    Assert.assertEquals(color, "white"); 

    //full name lower case
    belt = new Belt();
    belt.setBelt("yellow");
    color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Yellow");
    color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("Y");
    color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("y");
    color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 

    //index
    belt = new Belt();
    belt.setBelt("1");
    color = belt.getBelt();
    Assert.assertEquals(color, "yellow"); 

    //full name lower case
    belt = new Belt();
    belt.setBelt("orange");
    color = belt.getBelt();
    Assert.assertEquals(color, "orange"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Orange");
    color = belt.getBelt();
    Assert.assertEquals(color, "orange"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("O");
    color = belt.getBelt();
    Assert.assertEquals(color, "orange"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("o");
    color = belt.getBelt();
    Assert.assertEquals(color, "orange"); 

    //index
    belt = new Belt();
    belt.setBelt("2");
    color = belt.getBelt();
    Assert.assertEquals(color, "orange"); 

    //full name lower case
    belt = new Belt();
    belt.setBelt("purple");
    color = belt.getBelt();
    Assert.assertEquals(color, "purple"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Purple");
    color = belt.getBelt();
    Assert.assertEquals(color, "purple"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("P");
    color = belt.getBelt();
    Assert.assertEquals(color, "purple"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("p");
    color = belt.getBelt();
    Assert.assertEquals(color, "purple"); 

    //index
    belt = new Belt();
    belt.setBelt("3");
    color = belt.getBelt();
    Assert.assertEquals(color, "purple"); 

    //full name lower case
    belt = new Belt();
    belt.setBelt("blue");
    color = belt.getBelt();
    Assert.assertEquals(color, "blue"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Blue");
    color = belt.getBelt();
    Assert.assertEquals(color, "blue"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("BL");
    color = belt.getBelt();
    Assert.assertEquals(color, "blue"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("bl");
    color = belt.getBelt();
    Assert.assertEquals(color, "blue"); 

    //index
    belt = new Belt();
    belt.setBelt("4");
    color = belt.getBelt();
    Assert.assertEquals(color, "blue"); 

    //full name lower case
    belt = new Belt();
    belt.setBelt("blue stripe");
    color = belt.getBelt();
    Assert.assertEquals(color, "blue stripe"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Blue Stripe");
    color = belt.getBelt();
    Assert.assertEquals(color, "blue stripe"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("BLS");
    color = belt.getBelt();
    Assert.assertEquals(color, "blue stripe"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("bls");
    color = belt.getBelt();
    Assert.assertEquals(color, "blue stripe"); 

    //index
    belt = new Belt();
    belt.setBelt("5");
    color = belt.getBelt();
    Assert.assertEquals(color, "blue stripe"); 

    //full name lower case
    belt = new Belt();
    belt.setBelt("green");
    color = belt.getBelt();
    Assert.assertEquals(color, "green"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Green");
    color = belt.getBelt();
    Assert.assertEquals(color, "green"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("G");
    color = belt.getBelt();
    Assert.assertEquals(color, "green"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("g");
    color = belt.getBelt();
    Assert.assertEquals(color, "green"); 

    //index
    belt = new Belt();
    belt.setBelt("6");
    color = belt.getBelt();
    Assert.assertEquals(color, "green"); 

    //full name lower case
    belt = new Belt();
    belt.setBelt("green stripe");
    color = belt.getBelt();
    Assert.assertEquals(color, "green stripe"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Green Stripe");
    color = belt.getBelt();
    Assert.assertEquals(color, "green stripe"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("GS");
    color = belt.getBelt();
    Assert.assertEquals(color, "green stripe"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("gs");
    color = belt.getBelt();
    Assert.assertEquals(color, "green stripe"); 

    //index
    belt = new Belt();
    belt.setBelt("7");
    color = belt.getBelt();
    Assert.assertEquals(color, "green stripe"); 

    //full name lower case
    belt = new Belt();
    belt.setBelt("brown1");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown1"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Brown1");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown1"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("BR1");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown1"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("br1");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown1"); 

    //index
    belt = new Belt();
    belt.setBelt("8");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown1"); 


        //full name lower case
    belt = new Belt();
    belt.setBelt("brown2");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown2"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Brown2");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown2"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("BR2");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown2"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("br2");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown2"); 

    //index
    belt = new Belt();
    belt.setBelt("9");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown2"); 

        //full name lower case
    belt = new Belt();
    belt.setBelt("brown3");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown3"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Brown3");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown3"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("BR3");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown3"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("br3");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown3"); 

    //index
    belt = new Belt();
    belt.setBelt("10");
    color = belt.getBelt();
    Assert.assertEquals(color, "brown3"); 

    //full name lower case
    belt = new Belt();
    belt.setBelt("black1");
    color = belt.getBelt();
    Assert.assertEquals(color, "black1"); 


    //full name upper case
    belt = new Belt();
    belt.setBelt("Black1");
    color = belt.getBelt();
    Assert.assertEquals(color, "black1"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("B1");
    color = belt.getBelt();
    Assert.assertEquals(color, "black1"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("b1");
    color = belt.getBelt();
    Assert.assertEquals(color, "black1"); 

    //index
    belt = new Belt();
    belt.setBelt("11");
    color = belt.getBelt();
    Assert.assertEquals(color, "black1"); 


    //full name lower case
    belt = new Belt();
    belt.setBelt("black2");
    color = belt.getBelt();
    Assert.assertEquals(color, "black2"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Black2");
    color = belt.getBelt();
    Assert.assertEquals(color, "black2"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("B2");
    color = belt.getBelt();
    Assert.assertEquals(color, "black2"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("b2");
    color = belt.getBelt();
    Assert.assertEquals(color, "black2"); 

    //index
    belt = new Belt();
    belt.setBelt("12");
    color = belt.getBelt();
    Assert.assertEquals(color, "black2"); 

        //full name lower case
    belt = new Belt();
    belt.setBelt("black3");
    color = belt.getBelt();
    Assert.assertEquals(color, "black3"); 

    //full name upper case
    belt = new Belt();
    belt.setBelt("Black3");
    color = belt.getBelt();
    Assert.assertEquals(color, "black3"); 

    //intials
    //Upper case
    belt = new Belt();
    belt.setBelt("B3");
    color = belt.getBelt();
    Assert.assertEquals(color, "black3"); 

    //Lower case
    belt = new Belt();
    belt.setBelt("b3");
    color = belt.getBelt();
    Assert.assertEquals(color, "black3"); 

    //index
    belt = new Belt();
    belt.setBelt("13");
    color = belt.getBelt();
    Assert.assertEquals(color, "black3"); 
  }
}