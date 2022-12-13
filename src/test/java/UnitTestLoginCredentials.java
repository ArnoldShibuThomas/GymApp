/*
  File: LoginCredentials.java
  Author: Arnold Shibu-Thomas   
  Date: 11/7/2022
  
  Description: This class is a junit test for all the methods that are in LoginCredentials.java since this is the backend
*/
import org.junit.Assert;
import java.util.ArrayList; 
import org.junit.Test;
import  main.java.memoranda.util.LoginCredentials;
import  main.java.memoranda.gymAssets.Student;

public class UnitTestLoginCredentials{
  private LoginCredentials login;

  @org.junit.Before
  public void setUp() throws Exception {
    login = new LoginCredentials();
  }

  @Test 
  public void testCredentialsMatchCorrectOwner(){
     Assert.assertEquals(login.checkOwner("Tim","password"), true);
  }

  @Test
  public void testCredentialsMatchCorrectTrainers() {
    Assert.assertEquals(login.credentialsMatch(1,"Tim","password"), true);
    Assert.assertEquals(login.credentialsMatch(1,"Jones","password"), true);
  }

  @Test
  public void testCredentialsMatchStudents() {
    Assert.assertEquals(login.credentialsMatch(2,"John","password"), true);
    Assert.assertEquals(login.credentialsMatch(2,"Bob","password"), true);
  }

  @Test
  public void testCredentialsMatchTrainersNotInSystem() {

    Assert.assertEquals(login.credentialsMatch(1,"",""), false);
    Assert.assertEquals(login.credentialsMatch(1," "," "), false);
    Assert.assertEquals(login.credentialsMatch(1,"John","pssword"), false);
    Assert.assertEquals(login.credentialsMatch(1,"Bob","pssword"), false);
    Assert.assertEquals(login.credentialsMatch(1,"jess","password"), false);
    Assert.assertEquals(login.credentialsMatch(1,"alan","password"), false);
  }

  @Test
  public void testCredentialsMatchStudentsNotInSystem() {
    ArrayList<Student> people = new ArrayList<Student>();

    //test two correct people
    people.add(new Student("arnold", "password"));
    people.add(new Student("joe", "password"));

    Assert.assertEquals(login.credentialsMatch(2,"",""), false);
    Assert.assertEquals(login.credentialsMatch(2," "," "), false);
    Assert.assertEquals(login.credentialsMatch(2,"John","pssword"), false);
    Assert.assertEquals(login.credentialsMatch(2,"Bob","pssword"), false);
    Assert.assertEquals(login.credentialsMatch(2,"jess","password"), false);
    Assert.assertEquals(login.credentialsMatch(2,"alan","password"), false);
  }

  @Test 
  public void testCredentialsMatchOwnerNotInSystem(){
     Assert.assertEquals(login.checkOwner("Tom","password"), false);
     Assert.assertEquals(login.checkOwner("Tim","pssword"), false);
  }

  @Test 
  public void testInvalidCredentials() {
    Assert.assertEquals(login.credentialsMatch(2,null,null), false);

    Assert.assertEquals(login.credentialsMatch(1,null,null), false);

    Assert.assertEquals(login.checkOwner(null,null), false);
  }


}