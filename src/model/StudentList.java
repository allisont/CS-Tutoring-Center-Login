package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Predicate;

public class StudentList implements Serializable{
	
	private static final long serialVersionUID = 4769446982169761035L;
	private LinkedList<Student> studentList;
	
	public StudentList() {
		studentList = new LinkedList<Student>();
	}

	public LinkedList<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(LinkedList<Student> studentList) {
		this.studentList = studentList;
	}
	
	public boolean add(Student student) {
		if (studentList.contains(student)) {
			return false;
		}
		studentList.add(student);
		return true;
	}
	
	public boolean remove(Student student) {
		if (studentList.contains(student)) {
			studentList.remove(student);
			return true;
		}
		return false;
	}
	
	public Student find(Predicate<Student> predicate) {
		for (int i = 0; i<studentList.size(); i++) {
			if (predicate.test(studentList.get(i))) {
				return studentList.get(i);
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "StudentList [studentList=" + studentList + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentList other = (StudentList) obj;
		return Objects.equals(studentList, other.studentList);
	}
	
	
	
}
