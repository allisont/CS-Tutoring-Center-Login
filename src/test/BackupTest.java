package test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.function.Predicate;

import model.StudentHistory;
import model.StudentList;
import model.StudentLoggedIn;
import utilities.DataCenter;

public class BackupTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		DataCenter dc = DataCenter.getInstance();
		
		StudentHistory history = dc.getStudentHistory();
		StudentList list = dc.getStudentList();
		
		
		LocalDate date = LocalDate.now();
		
		System.out.println(date);
		LocalDate d1 = LocalDate.parse("2023-10-10");
		LocalDate d2 = LocalDate.parse("2023-10-11");
		
		
		//use this for predicate 
		Predicate<StudentLoggedIn> predicate = i -> !(i.getDate().isBefore(d1)) && !(i.getDate().isAfter(d1));
		
		
		System.out.println(history.generateStudentStats(predicate));
		
		System.out.println(history.generateTimeStats(predicate));
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM");
		System.out.println(f.format(LocalDate.now()));
			
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d uuuu");
		String tempDate = "Oct 28 2023";
		LocalDate firstDate = LocalDate.now();
		System.out.println(firstDate);
		
		predicate = i -> !(i.getDate().isBefore(firstDate)) && !(i.getDate().isAfter(firstDate));

		System.out.println(history.generateStudentStats(predicate));
		
	}

}
