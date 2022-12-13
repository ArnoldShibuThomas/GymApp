/*
  File:	Belt.Java
  Author: Arnold Shibu-Thomas
  Date:	10/27

  Description: This file contains the class for the belt progession
*/


package main.java.memoranda.gymAssets;

/**
 Class:GymClass

 Description:This class is in charge of handling belts
 */
public class Belt {

	//private instance variables
	private int currentBelt;
	//a variable that contains the belts in the system
  private final String[] beltColors = { "white", "yellow", "orange", "purple", "blue", "blue stripe", "green", "green stripe", "brown1" , "brown2" , "brown3", "black1", "black2", "black3" };
	

	/**
     * Default constructor for Gym Class.
     */
	public Belt(){
		currentBelt = 0;
	}

	/**
     * Parameterized constructor for Gym Class.
     *
     * @param belt tells if class is public
     */
	public Belt(String belt){
		currentBelt = 0;
		setBelt(belt);
	}

	/**
     * This method will increase the belt to the next one.
     */
	public void graduateBelt(){
		//if the belt is less than the total belts we have in out system graduate the belt
		if(currentBelt < beltColors.length-1){
			currentBelt++;
		}
	}

	/**
     * This method sets the belt.
     * 
     * @param belt
     */
	public void setBelt(String belt){
		//check which belt the person means
		if(belt.equals("white") || belt.equals("White") || belt.equals("w") || belt.equals("W") || belt.equals("0") ){
			currentBelt = 0;
		}
		else if(belt.equals("yellow") || belt.equals("Yellow") || belt.equals("y") || belt.equals("Y")|| belt.equals("1") ){
			currentBelt = 1;
		}
		else if(belt.equals("orange") || belt.equals("Orange") || belt.equals("o") || belt.equals("O") || belt.equals("2") ){
			currentBelt = 2;
		}
		else if(belt.equals("purple") || belt.equals("Purple") || belt.equals("p") || belt.equals("P") || belt.equals("3") ){
			currentBelt = 3;
		}
		else if(belt.equals("blue") || belt.equals("Blue") || belt.equals("bl") || belt.equals("BL") || belt.equals("4") ){
			currentBelt = 4;
		}
		else if(belt.equals("blue stripe") || belt.equals("Blue Stripe") || belt.equals("bluestripe") || belt.equals("blueStripe") || belt.equals("BlueStripe") ||belt.equals("bls") || belt.equals("BLS") || belt.equals("5") ){
			currentBelt = 5;
		}
		else if(belt.equals("green") || belt.equals("Green") || belt.equals("g") || belt.equals("G") || belt.equals("6") ){
			currentBelt = 6;
		}
		else if(belt.equals("green stripe") || belt.equals("Green Stripe") || belt.equals("GreenStripe") || belt.equals("greenStripe") || belt.equals("greenstripe") || belt.equals("gs") || belt.equals("GS") || belt.equals("7") ){
			currentBelt = 7;
		}
		else if(belt.equals("brown1") || belt.equals("Brown1") || belt.equals("br1") || belt.equals("BR1") || belt.equals("8") ){
			currentBelt = 8;
		}
		else if(belt.equals("brown2") || belt.equals("Brown2") || belt.equals("br2") || belt.equals("BR2") || belt.equals("9") ){
			currentBelt = 9;
		}
		else if(belt.equals("brown3") || belt.equals("Brown3") || belt.equals("br3") || belt.equals("BR3") || belt.equals("10") ){
			currentBelt = 10;
		}
		else if(belt.equals("black1") || belt.equals("Black1") || belt.equals("b1") || belt.equals("B1") || belt.equals("11") ){
			currentBelt = 11;
		}
		else if(belt.equals("black2") || belt.equals("Black2") || belt.equals("b2") || belt.equals("B2") || belt.equals("12") ){
			currentBelt = 12;
		}
		else if(belt.equals("black3") || belt.equals("Black3") || belt.equals("b3") || belt.equals("B3") || belt.equals("13") ){
			currentBelt = 13;
		}
		//since no valid belt was given we set the user's belt to white
		else{
			currentBelt= 0;
		}
	}

	/**
     * This method gets the belt.
      * @return String that is the name of the belt
     */
	public String getBelt(){
		//go ahead and return the belt color
		return beltColors[currentBelt];
	}
	
}