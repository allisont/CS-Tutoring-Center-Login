package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Predicate;

public class StudentHistory implements Serializable{
	
	private static final long serialVersionUID = -6555492599464272085L;
	private LinkedList<StudentLoggedIn> studentHistoryList;
	
	public StudentHistory() {
		studentHistoryList = new LinkedList<>();
	}

	public LinkedList<StudentLoggedIn> getStudentHistoryList() {
		return studentHistoryList;
	}

	public void setStudentHistoryList(LinkedList<StudentLoggedIn> studentHistoryList) {
		this.studentHistoryList = studentHistoryList;
	}
	
	public boolean add(StudentLoggedIn student) {
		studentHistoryList.add(student);
		return true;
	}
	
	public boolean addAll(Collection<StudentLoggedIn> c) {
		studentHistoryList.addAll(c);
		return true;
	}
	
	public LinkedList<StudentLoggedIn> generateStudentStats(Predicate<StudentLoggedIn> predicate) {
		LinkedList<StudentLoggedIn> studentsAttended = new LinkedList<StudentLoggedIn>();
		for (int i = 0; i<studentHistoryList.size(); i++) {
			if (predicate.test(studentHistoryList.get(i))) { 
				studentsAttended.add(studentHistoryList.get(i));
			}
		}
		return studentsAttended;
	}
	
	public String generateTimeStats(Predicate<StudentLoggedIn> predicate) {
		Long totalTime = (long) 0;
		LinkedList<StudentLoggedIn> temp = generateStudentStats(predicate);
		for (int i = 0; i<temp.size(); i++) {
			totalTime+=temp.get(i).getTime();
		}
		return totalTime/60+":"+totalTime%60;
	}
	
	public Integer[] generateTrafficStats(Predicate<StudentLoggedIn> predicate) {
		LinkedList<StudentLoggedIn> temp = generateStudentStats(predicate);
		int n10 = 0;
		int n11 = 0;
		int n12 = 0;
		int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		int n4 = 0;
		int n5 = 0;
		for (int i = 0; i<temp.size(); i++) {
			if (temp.get(i).getTimeBegin().getHour() == 10) {
				n10++;
			} else if (temp.get(i).getTimeBegin().getHour() == 11) {
				n11++;
			} else if(temp.get(i).getTimeBegin().getHour() == 12) {
				n12++;
			} else if(temp.get(i).getTimeBegin().getHour() == 1) {
				n1++;
			} else if(temp.get(i).getTimeBegin().getHour() == 2) {
				n2++;
			} else if(temp.get(i).getTimeBegin().getHour() == 3) {
				n3++;
			} else if(temp.get(i).getTimeBegin().getHour() == 4) {
				n4++;
			} else {
				n5++;
			}
		}
		Integer[] countArr = {n10, n11, n12, n1, n2, n3, n4, n5};
		return countArr;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentHistoryList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentHistory other = (StudentHistory) obj;
		return Objects.equals(studentHistoryList, other.studentHistoryList);
	}

	@Override
	public String toString() {
		return "StudentHistory [studentHistoryList=" + studentHistoryList + "]";
	}
	
	

}
