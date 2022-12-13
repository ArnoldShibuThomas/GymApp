/**
 * @File: UnitTestGymClass.java
 * @Author: William Mastronardi
 * @Date: 11/10/2022
 * @Version: 1.0
 * @Description: This class is a JUnit test for the GymClass.java class. Due to the simplistic
 *                  nature of the GymClass.java file with primary methods being getters and setters,
 *                  some reliance on the other backend assets will be necessary. For the purpose
 *                  of testing, only ensuring proper functionality is important,
 *                  especially because no nodes or edges occur
 *                  within the Room file itself.
 * @Coverage: This covers 98% of the lines of code within the Room.java file, and 83% of the
 *              branches
 *
 */
import org.junit.Before;
import main.java.memoranda.gymAssets.Instructor;
import main.java.memoranda.gymAssets.GymClass;
import main.java.memoranda.gymAssets.Student;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;


public class UnitTestGymClass {
    private GymClass gymClassDefault;
    private GymClass gymClassArguments;
    private GymClass gymClassPrivate;

    @Before
    public void setUp() {
        gymClassDefault = new GymClass(); //Default GymClass
        gymClassArguments = new GymClass(true, new Instructor(), false, LocalDate.now());
        gymClassPrivate = new GymClass(false, new Instructor(), false, LocalDate.now());
    }

    /**
     * Tests the capability of changing the teacher in the GymClass
     */
    @Test
    public void testChangeTeacher() {
        Instructor temp = new Instructor("Tom", "p1", "black3", "black3", false);
        gymClassArguments.setTeacher(temp);
        gymClassPrivate.setTeacher(temp);
        gymClassDefault.setTeacher(temp);

        assertEquals(gymClassArguments.getTeacher(), temp);
        assertEquals(gymClassArguments.getTeacher(), temp);
        assertEquals(gymClassArguments.getTeacher(), temp);
    }

    /**
     * Tests adding the same student into enrolled students in a GymClass instance with a Gym
     * Class instance set to the default constructor
     */
    @Test
    public void testAddDefaultStudentDefaultConstructor() {
        gymClassDefault = new GymClass(); //Default GymClass
        for(int i = 0; i < 22; i++) {
            gymClassDefault.addStudent(new Student());
        }
        assertEquals(gymClassDefault.getStudents().size(), 20);
    }

    /**
     * Tests adding the same student into enrolled students in a GymClass instance with a Gym
     * Class instance set to the Private constructor
     */
    @Test
    public void testAddDefaultStudentPrivateConstructor() {
        gymClassPrivate = new GymClass(false, new Instructor(), false, LocalDate.now());
        for(int i = 0; i < 22; i++) {
            gymClassPrivate.addStudent(new Student());
        }
        System.out.println(gymClassPrivate.getStudents().size());
        assertEquals(gymClassPrivate.getStudents().size(), 1);
    }

    /**
     * Tests adding the same student into enrolled students in a GymClass instance with a Gym
     * Class instance set to the custom constructor
     */
    @Test
    public void testAddDefaultStudentCustomConstructor() {
        gymClassArguments = new GymClass(true, new Instructor(), false, LocalDate.now());
        for(int i = 0; i < 22; i++) {
            gymClassArguments.addStudent(new Student());
        }
        assertEquals(gymClassArguments.getStudents().size(), 20);
    }

    /**
     * Tests adding students into enrolled students in a GymClass instance with a class set
     * to the default constructor
     */
    @Test
    public void testAddStudentsDefault() {
        gymClassDefault = new GymClass(); //Default GymClass
        for (int i = 0; i < 22; i++) {
            Student s1 = new Student("" + i, "p");
            gymClassDefault.addStudent(s1);
        }
        assertEquals(gymClassDefault.getStudents().size(), 20);
    }

    /**
     * Tests adding students into enrolled students in a GymClass instance with a class set
     * to private
     */
    @Test
    public void testAddStudentsPrivate() {
        gymClassPrivate = new GymClass(false, new Instructor(), false, LocalDate.now());
        for (int i = 0; i < 22; i++) {
            Student s1 = new Student("" + i, "p");
            gymClassPrivate.addStudent(s1);
        }
        assertEquals(gymClassPrivate.getStudents().size(), 1);
    }

    /**
     * Tests adding students into enrolled students in a GymClass instance with a class set
     * to a custom constructor
     */
    @Test
    public void testAddStudentsArguments() {
        gymClassArguments = new GymClass(true, new Instructor(), false, LocalDate.now());
        for (int i = 0; i < 22; i++) {
            Student s1 = new Student("" + i, "p");
            gymClassArguments.addStudent(s1);
        }
        assertEquals(gymClassArguments.getStudents().size(), 20);
    }
}
