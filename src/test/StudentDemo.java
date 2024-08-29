package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

import model.Student;
import model.StudentHistory;
import model.StudentList;
import model.StudentLoggedIn;
import model.StudentLoginQueue;

public class StudentDemo { //CLEAR

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//	//student
//		ArrayList<String> arr = new ArrayList<>();
//		arr.add("hey");
//		arr.add("bye");
//		
////		Student s1 = new Student("A", "A", "1", arr);
////		Student s2 = new Student("B", "B", "2", arr);
////		Student s3 = new Student("C", "C", "3", arr);
//		
//	//studentList
//		StudentList sl = new StudentList();
//		sl.add(s1); //add
//		sl.add(s2);
//		sl.add(s3);
//		sl.add(s3); // add 2?
//		
//		sl.remove(s3); //remove
//		
//		Predicate<Student> p1 = i -> i.getId().equals("2");
//		System.out.println("ID: 2?" + sl.find(p1)); //find
//		
//	//studentLoggedIn
//		ArrayList<String> topics = new ArrayList<>();
//		topics.add("yass class");
//		topics.add("naur class");
//		StudentLoggedIn sli1 = new StudentLoggedIn(s1, "li", "cse148", topics);
//		StudentLoggedIn sli2 = new StudentLoggedIn(s2, "li", "cse148", topics);
//		StudentLoggedIn sli3 = new StudentLoggedIn(s3, "li", "cse148", topics);
//		System.out.println(sli3.toString());
//		sli1.setTime(sli1.getTimeBegin(), sli1.getTimeBegin().plusMinutes(20));
//		sli2.setTime(sli1.getTimeBegin(), sli1.getTimeBegin().plusMinutes(20));
//		sli3.setTime(sli1.getTimeBegin(), sli1.getTimeBegin().plusMinutes(20));
//
//
//		
//	//studentLoginQueue
//		StudentLoginQueue slq = new StudentLoginQueue();
//		slq.add(sli1);
//		slq.add(sli2);
//		slq.add(sli3);
//		
//		Predicate<StudentLoggedIn> p2 = i -> i.getStudent().getId().equals("2");
//		slq.remove(p2);
//		
//	//studentHistory
//		StudentHistory sh = new StudentHistory();
//		sh.addAll(slq.removeAll());
//		
//		p2 = i -> i.getDate().equals(LocalDate.now());
//		sh.generateStudentStats(p2);
//		System.out.println(sh.generateTimeStats(p2));
//		System.out.println(80/60 + ":" + 80%60);
//		
//		
	}

}
