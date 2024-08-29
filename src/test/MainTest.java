package test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import model.Student;
import model.StudentHistory;
import model.StudentList;
import model.StudentLoggedIn;
import utilities.DataCenter;

public class MainTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(LocalTime.now().toString());
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
		System.out.println(f.format(dt));
		
		f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		System.out.println(f.format(dt));
		
		String lt = f.format(dt);
		LocalDate yas = LocalDate.parse(lt);
		System.out.println(yas);

		
		
		//testing files
		DataCenter dc = DataCenter.getInstance();
		StudentList sl =  dc.getStudentList();
		StudentHistory sh = dc.getStudentHistory();
			
		
		//testing load with functions
		String[] t = {"cse18", "cse10"};
		Student add = new Student("Yass", "Naur", "12345678", t);
		sl.add(add);
		Predicate<Student> p = i -> i.getFirstName().equals("Yass");
		System.out.println(sl.find(p));
		p = i -> i.getFirstName().equals("Allison");
		System.out.println(sl.find(p));
		System.out.println(sl.find(i -> i.getId().equals("0")));
		
//		sl.find(p).ifPresentOrElse(System.out::print, () -> System.out.println("ugh"));
//		System.out.println(sl.find(p).map(Student::getFirstName).get());

		
	}

}
