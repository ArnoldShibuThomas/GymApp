/**
 * @File: UnitTestRoom.java
 * @Author: William Mastronardi
 * @Date: 11/10/2022
 * @Version: 1.0
 * @Description: This class is a JUnit test for the Room.java class. Due to the simplistic
 *                  nature of the Room.java file, some reliance on the other backend assets
 *                  will be necessary. For the purpose of testing, only ensuring proper
 *                  functionality is important, especially because no nodes or edges occur
 *                  within the Room file itself.
 * @Coverage: This covers 98% of the lines of code within the Room.java file, and 83% of the
 *              branches
 *
 */

import main.java.memoranda.gymAssets.Instructor;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;
import  main.java.memoranda.gymAssets.Room;
import main.java.memoranda.gymAssets.GymClass;

import static org.junit.Assert.assertEquals;

public class UnitTestRoom {
    private Room room;

    /**
     * This constructor does not require a different id number for the Room class, as that has
     * no alteration on the class functionality.
     *
     * @throws Exception
     */
    @org.junit.Before
    public void setUp() throws Exception {
        room = new Room(0);
    }

    /**
     * Tests if duplicate classes can be added
     */
    @Test
    public void testAddClassDuplicate() {
        GymClass gymClass = new GymClass(); //Default GymClass creation
        room.addClass(gymClass);
        assertEquals(room.getClassCount(), 1);
        assertEquals(room.getAllClasses().size(), 1); //Ensures the getClassCount stores the
                                                            // correct value
        room.addClass(gymClass);
        assertEquals(room.getClassCount(), 1);
        assertEquals(room.getAllClasses().size(), 1); //Ensures the getClassCount stores the
                                                            // correct value
        assertEquals(gymClass, room.getClass(0)); //Ensures the class added was the correct class
    }

    /**
     * Adds a list of 4 classes to ensure that a room can contain multiple classes,
     * but checks if they occur at the same time to avoid double scheduling.
     */
    @Test
    public void testMultipleClasses() {
        GymClass[] classes = new GymClass[4];
        main.java.memoranda.gymAssets.Instructor temp = new Instructor(); //Default instructor
        LocalDate date = LocalDate.now(); 
        for (int i = 0; i < 4; i++) {
            classes[i] = new GymClass(true, temp, true, date);
            room.addClass(classes[i]);
        }
        assertEquals(room.getClassCount(), 1);
    }

}
