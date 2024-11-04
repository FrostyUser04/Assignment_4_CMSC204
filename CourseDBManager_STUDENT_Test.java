import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CourseDBManager_STUDENT_Test {

	//Instance of the CourseDBManager to be used in each test
	private CourseDBManager dbManager;

	//Setup method
    @Before
    public void setUp() {
        dbManager = new CourseDBManager();
    }

	//Test the add and get methods of CourseDBManager
    @Test
    public void testAddAndGet() {
        // Test adding a course and retrieving it by CRN
        dbManager.add("CMSC204", 20949, 4, "SW213", "David Kujit");
        CourseDBElement element = dbManager.get(20949);
        
        assertEquals("CMSC204", element.getCourseID());
        assertEquals(20949, element.getCRN());
        assertEquals(4, element.getNumberOfCredits());
        assertEquals("SW213", element.getRoomNum());
        assertEquals("David Kujit", element.getInstructor());
    }

	// Test attempting to get a course using a CRN that doesn't exist in the database
    @Test
    public void testGetNonexistentCRN() {
        // Test getting a non-existent course by CRN
        CourseDBElement element = dbManager.get(99999);
        
        // Expecting a blank CourseDBElement since CRN doesn't exist
        assertEquals("", element.getCourseID());
        assertEquals(0, element.getCRN());
        assertEquals(0, element.getNumberOfCredits());
        assertEquals("", element.getRoomNum());
        assertEquals("", element.getInstructor());
    }

	// Test to verify the showAll method, which should list all added courses
    @Test
    public void testShowAll() {
        // Add some elements and check the showAll method output
        dbManager.add("CMSC204", 20949, 4, "SW213", "David Kujit");
        dbManager.add("CMSC207", 20955, 4, "SW211", "Ahmed Tarek");

        ArrayList<String> allCourses = dbManager.showAll();
        
        assertEquals(2, allCourses.size());
        assertTrue(allCourses.get(0).contains("CMSC204") || allCourses.get(1).contains("CMSC204"));
        assertTrue(allCourses.get(0).contains("CMSC207") || allCourses.get(1).contains("CMSC207"));
    }

	// Test to verify the readFile method by reading data from a temporary file
    @Test
    public void testReadFile() throws IOException {
        // Create a temporary file with sample content
        File tempFile = File.createTempFile("sample_courses", ".txt");
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("CMSC204 20949 4 SW213 David Kujit");
            writer.println("CMSC207 20955 4 SW211 Ahmed Tarek");
        }

		// Reading from the file into the CourseDBManager
        dbManager.readFile(tempFile);
        
        // Validate that the courses have been added
        CourseDBElement element1 = dbManager.get(20949);
        CourseDBElement element2 = dbManager.get(20955);
        
        assertEquals("CMSC204", element1.getCourseID());
        assertEquals("CMSC207", element2.getCourseID());
        
        // Delete the temp file after the test
        tempFile.deleteOnExit();
    }

	// Test to handle the case where the file to be read does not exist
    @Test(expected = FileNotFoundException.class)
    public void testReadFileNotFound() throws FileNotFoundException {
        // Test for FileNotFoundException
        File nonExistentFile = new File("non_existent_file.txt");
        
		// Attempting to read from the non-existent file, expecting a FileNotFoundException
		dbManager.readFile(nonExistentFile);
    }
}