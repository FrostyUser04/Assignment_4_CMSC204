import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Yesho Vir
 */

public class CourseDBStructure implements CourseDBStructureInterface {

    private int tableSize;
	LinkedList<CourseDBElement>[] hashTable;

    @SuppressWarnings("unchecked")
	public CourseDBStructure(int numOfCourses) {
		tableSize = numOfCourses;
		hashTable = new LinkedList[tableSize];
	}

	/**
	 * 
	 * @param testing
	 * @param numOfCourses
	 */

    public CourseDBStructure(String testing, int numOfCourses) {
		this(numOfCourses);
	}

    @Override
	public void add(CourseDBElement element) {
		boolean placeIntoTable = true;
		int initialPlacement = (int) Math.ceil(element.hashCode() % hashTable.length);
		
		if (hashTable[initialPlacement] == null)
			hashTable[initialPlacement] = new LinkedList<>();
		
		for (CourseDBElement listElement : hashTable[initialPlacement])
			if (listElement.compareTo(element) == 0)
				placeIntoTable = false;
		
		if (placeIntoTable)
			hashTable[initialPlacement].add(element);
	}

    @Override
	public CourseDBElement get(int crn) throws IOException {
		CourseDBElement toReturn = null;
		
		for (LinkedList<CourseDBElement> list : hashTable)
			if (list != null)
				for (CourseDBElement listElement : list)
					if (listElement.compareTo(new CourseDBElement("", crn, 0, "", "")) == 0)
						toReturn = listElement;
		
		if (toReturn == null)
			throw new IOException("Couldn't find " + crn);
		
		return toReturn;
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> courses=new ArrayList<String>();
		for(int i=0; i < tableSize; i++) {
			while(hashTable[i]!=null) {
				for(int j=0;j<hashTable[i].size();j++) {
					CourseDBElement element= (CourseDBElement) hashTable[i].get(j);
					courses.add("\n"+element.toString());
				}
				break;
			}
		}
		return courses;
	}

	@Override
	public int getTableSize() {
		return tableSize;
	}

    
}
