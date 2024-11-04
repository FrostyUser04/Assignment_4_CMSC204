import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {
    
	// Instance of CourseDBStructure to store course data
    private CourseDBStructure structure;

    // Constructor to initialize the CourseDBStructure
    public CourseDBManager() {
        structure = new CourseDBStructure(500);
    }

    // Adds a new course to the database using the provided parameters
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        structure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
    }

    // Retrieves a course from the database using its CRN
    @Override
    public CourseDBElement get(int crn) {
        CourseDBElement toReturn = new CourseDBElement("", 0, 0, "", "");
        try {
            toReturn = structure.get(crn);
        } catch (IOException e) {
            System.err.println(crn + " Not found");
        }
        return toReturn;
    }

    // Reads a list of courses from a file and adds each course to the database
    @Override
    public void readFile(File input) throws FileNotFoundException {
        if (!input.exists()) {
            throw new FileNotFoundException("File not found: " + input.getPath());
        }

        try (Scanner fileScanner = new Scanner(input)) {
            while (fileScanner.hasNextLine()) {
                String next = fileScanner.nextLine().trim();
                
                if (!next.isEmpty()) {
                    String[] dataArr = next.split(" ");

                    if (dataArr.length < 5) {
                        System.err.println("Invalid line format: " + next);
                        continue;
                    }

                    String courseID = dataArr[0];
                    int crnNumber;
                    int numOfCredits;
                    
                    try {
                        crnNumber = Integer.parseInt(dataArr[1]);
                        numOfCredits = Integer.parseInt(dataArr[2]);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format in line: " + next);
                        continue;
                    }
                    
                    String roomNumber = dataArr[3];
                    String instructor = String.join(" ", Arrays.copyOfRange(dataArr, 4, dataArr.length));
                    add(courseID, crnNumber, numOfCredits, roomNumber, instructor);
                }
            }
        }
    }

    // Returns a list of all courses in the database as formatted strings
    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> data = new ArrayList<>();
        
        for (LinkedList<CourseDBElement> list : structure.hashTable) {
            if (list != null) {
                for (CourseDBElement listElement : list) {
                    data.add("\n" + listElement.toString());
                }
            }
        }
        
        return data;
    }
}
