package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Predicate;

public class StudentLoginQueue {
	
	private LinkedList<StudentLoggedIn> studentLoggedInList;
	private int nStudentsLoggedIn;
	
	public StudentLoginQueue() {
		nStudentsLoggedIn = 0;
		studentLoggedInList = new LinkedList<StudentLoggedIn>();
	}

	public LinkedList<StudentLoggedIn> getStudentLoggedInList() {
		return studentLoggedInList;
	}

	public void setStudentLoggedInList(LinkedList<StudentLoggedIn> studentLoggedInList) {
		this.studentLoggedInList = studentLoggedInList;
	}
	
	public int getNStudentsLoggedIn() {
		return nStudentsLoggedIn;
	}
	
	public void setNStudentsLoggedIn(int nStudentsLoggedIn) {
		this.nStudentsLoggedIn = nStudentsLoggedIn;
	}
	
	public boolean add(StudentLoggedIn studentLoggedIn) {
		for (int i = 0; i<studentLoggedInList.size(); i++) {
			if (studentLoggedInList.get(i).getStudent().equals(studentLoggedIn.getStudent())) {
				return false;
			}
		}
		studentLoggedInList.add(studentLoggedIn);
		nStudentsLoggedIn++;
		return true;
	}
	
	public StudentLoggedIn remove(Predicate<StudentLoggedIn> predicate) { //use predicate
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
		for (int i = 0; i<studentLoggedInList.size(); i++) {
			if (predicate.test(studentLoggedInList.get(i))) {
				nStudentsLoggedIn--;
				studentLoggedInList.get(i).setTimeEnd(LocalTime.parse(formatter.format(LocalTime.now())));
				return studentLoggedInList.remove(i);
			}
		}
		return null;
	}
	
	public StudentLoggedIn find(Predicate<StudentLoggedIn> predicate) {
		for (int i = 0; i<studentLoggedInList.size(); i++) {
			if (predicate.test(studentLoggedInList.get(i))) {
				return studentLoggedInList.get(i);
			}
		}
		return null;
	}
	
	public LinkedList<StudentLoggedIn> removeAll() {
		LinkedList<StudentLoggedIn> temp = new LinkedList<>();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
		for (int i = 0; i<studentLoggedInList.size(); i++) {
			studentLoggedInList.get(i).setTimeEnd(LocalTime.parse(formatter.format(LocalTime.now())));
			temp.add(studentLoggedInList.get(i));
		}
		studentLoggedInList.clear();
		return temp;
	}

	@Override
	public String toString() {
		return "StudentLoginQueue [studentLoggedInList=" + studentLoggedInList + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentLoggedInList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentLoginQueue other = (StudentLoginQueue) obj;
		return Objects.equals(studentLoggedInList, other.studentLoggedInList);
	}
	

}
