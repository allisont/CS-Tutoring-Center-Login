package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Student;
import model.StudentHistory;
import model.StudentList;
import model.StudentLoginQueue;

public class DataCenter {

	private static DataCenter instance = null;
	
	private StudentHistory studentHistory;
	private StudentList studentList;
	private Student studentLoggedIn;
	private StudentLoginQueue studentLoginQueue;
	private String tutor;
	
	private DataCenter() {
		try {
			File studentListBackup = new File("src/utilities/studentListBackup.dat");
			File studentHistoryBackup = new File("src/utilities/studentHistoryBackup.dat");
			if (studentListBackup.exists() && studentHistoryBackup.exists()) {
				load();
			}else {
				studentList = importStudentData("src/utilities/studentList.tsv");
				studentHistory = new StudentHistory();
				studentListBackup.createNewFile();
				studentHistoryBackup.createNewFile();
				backup();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		studentLoggedIn = null;
		studentLoginQueue = new StudentLoginQueue();
		tutor = null;
	}
	
	public static DataCenter getInstance() throws IOException, ClassNotFoundException {
		if (instance == null) {
			instance = new DataCenter();
		}
		return instance;
	}
	
	public void load(){ 
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/utilities/studentListBackup.dat"))) {
			studentList = (StudentList) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/utilities/studentHistoryBackup.dat"))) {
			studentHistory = (StudentHistory) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void backup(){
		try (ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream("src/utilities/studentListBackup.dat", false))) {
			oos.writeObject(studentList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		try (ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream("src/utilities/studentHistoryBackup.dat", false))) {
			oos.writeObject(studentHistory);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public StudentList getStudentList() {
		return studentList;
	}
	
	public StudentHistory getStudentHistory() {
		return studentHistory;
	}
	
	public Student getStudentLoggedIn() {
		return studentLoggedIn;
	}
	
	public void setStudentLoggedIn(Student studentLoggedIn) {
		this.studentLoggedIn = studentLoggedIn;
	}
	
	public StudentLoginQueue getStudentLoginQueue() {
		return studentLoginQueue;
	}
	
	public void setStudentLoginQueue(StudentLoginQueue studentLoginQueue) {
		this.studentLoginQueue = studentLoginQueue;
	}
	
	public String getTutor() {
		return tutor;
	}
	
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
	
	public StudentList importStudentData(String path) throws IOException {
		StudentList studentList = new StudentList();
		FileReader fr = new FileReader(path); 
		BufferedReader br = new BufferedReader(fr);
		try {			
			while (br.ready()) {
				String[] studentTempArr = br.readLine().split("\t");
				Student tempStudent = new Student(studentTempArr[0], studentTempArr[1], studentTempArr[2], studentTempArr[3].split(","));
				studentList.add(tempStudent);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			fr.close();
			br.close();
		}
		return studentList;
	}
	
//	public StudentHistory importStudentHistoryData(String path) throws IOException { // try with resources
//		StudentHistory studentHistoryList = new StudentHistory();
//		FileReader fr = new FileReader(path); 
//		BufferedReader br = new BufferedReader(fr);
//		try {			
//			while (br.ready()) { 
//				//student, LocalDate date, LocalTime timeBegin, LocalTime timeEnd, Long time, instructor, course, topics
//				String[] studentLoggedInTempArr = br.readLine().split("\t");
//				String[] studentTempArr = studentLoggedInTempArr[0].split(" "); //split by space comma
//				Student studentTemp = new Student(studentTempArr[0], studentTempArr[1], studentTempArr[2], studentTempArr[3].split(","));
//								StudentLoggedIn studentLoggedInTemp = new StudentLoggedIn(studentTemp, LocalDate.parse(studentLoggedInTempArr[1]),
//						LocalTime.parse(studentLoggedInTempArr[2]), LocalTime.parse(studentLoggedInTempArr[3]),
//					Long.parseLong(studentLoggedInTempArr[4]), studentLoggedInTempArr[5], studentLoggedInTempArr[6], studentLoggedInTempArr[7]);
//				studentHistoryList.add(studentLoggedInTemp);
//			}
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} finally {
//			fr.close();
//			br.close();
//		}
//		return studentHistoryList;
//	}	
	
}
