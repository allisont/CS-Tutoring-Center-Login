package model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class StudentLoggedIn implements Serializable{

	private static final long serialVersionUID = 4564322607052242994L;
	private Student student;
	private String name;
	private String id;
	private LocalDate date;
	private LocalTime timeBegin;
	private LocalTime timeEnd;
	private Long time;
	private String instructor;
	private String course;
	private String topics;
	private static DateTimeFormatter formatter;
	
	public StudentLoggedIn(Student student, String instructor, String course, String topics) {
		this.student = student;
		this.name = student.getFirstName()+ " " + student.getLastName();
		this.id = student.getId();
		date = LocalDate.now();
		formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
		timeBegin = LocalTime.parse(formatter.format(LocalTime.now()));
		timeEnd = null;
		time = null;
		this.instructor = instructor;
		this.course = course;
		this.topics = topics;
	}
	
	public StudentLoggedIn(Student student, LocalDate date, LocalTime timeBegin, LocalTime timeEnd, Long time, String instructor, String course, String topics) {
		this.student = student;
		this.name = student.getFirstName() + " " + student.getLastName();
		this.id = student.getId();
		this.date = date;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
		this.time = time;
		this.instructor = instructor;
		this.course = course;
		this.topics = topics;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(LocalTime timeBegin) {
		this.timeBegin = timeBegin;
	}

	public LocalTime getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(LocalTime timeEnd){
		this.timeEnd = timeEnd;
		setTime();
	}
	
	public Long getTime() {
		return time;
	}
	
	public void setTime() {
		this.time = Duration.between(timeBegin, timeEnd).toMinutes();
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return date + "\t" + name + "\t" + id + "\t" + timeBegin + "\t" + timeEnd + "\t" + time + "\t" + course + "\t" + instructor + "\t" + topics;
	}

	@Override
	public int hashCode() {
		return Objects.hash(course, date, instructor, student, timeBegin, timeEnd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentLoggedIn other = (StudentLoggedIn) obj;
		return Objects.equals(date, other.date) && Objects.equals(student, other.student)
				&& Objects.equals(timeBegin, other.timeBegin) && Objects.equals(timeEnd, other.timeEnd);
	}
	
	
	
	
	
}
