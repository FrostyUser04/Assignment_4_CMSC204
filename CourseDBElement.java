
/** This is the Data Element class that implements the Comparable Interface
 * @author Yesho Vir
 */

public class CourseDBElement implements Comparable {

	private String courseID;
	private int crnNumber;
	private int numberOfCredits;
	private String roomNumber;
	private String instructor;
	
	/** Default constructor that sets default values */
	public CourseDBElement() {
		this("", 0, 0, "", "");
	}

	/**
	 * @param courseID
	 * @param crn
	 * @param numberOfCredits
	 * @param roomNumber
	 * @param instructor
	 */

    public CourseDBElement(String courseID, int crn, int numberOfCredits, String roomNumber, String instructor) {
		this.courseID = courseID;
		this.crnNumber = crn;
		this.numberOfCredits = numberOfCredits;
		this.roomNumber = roomNumber;
		this.instructor = instructor;
	}

	//Getters and Setters for the data element class
	
	/**
	 * @return courseID
	 */

    public String getCourseID() {
		return courseID;
	}

	/**
	 * @param courseID
	 */

    public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	/**
	 * @return crnNumber
	 */

    public int getCRN() {
		return crnNumber;
	}

	/**
	 * @param crnNumber
	 */

    public void setCRN(int crnNumber) {
		this.crnNumber = crnNumber;
	}

	/**
	 * @return numberOfCredits
	 */

    public int getNumberOfCredits() {
		return numberOfCredits;
	}

	/**
	 * @param numberOfCredits
	 */

    public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	/**
	 * @return roomNumber
	 */

    public String getRoomNum() {
		return roomNumber;
	}

	/**
	 * @param roomNumber
	 */

    public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * @return instructor
	 */

    public String getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor
	 */

    public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

    public int hashCode() {
		return String.valueOf(crnNumber).hashCode();
	}

    @Override
	public int compareTo(CourseDBElement element) {
		return hashCode() - element.hashCode();
	}

    @Override
	public String toString() {
		return "Course:" + courseID + " CRN:" + crnNumber + " Credits:" + numberOfCredits + " Instructor:" + instructor + " Room:" + roomNumber;
	}

}

