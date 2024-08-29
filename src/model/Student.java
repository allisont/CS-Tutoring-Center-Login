package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Student implements Serializable {

	private static final long serialVersionUID = 884555980377093840L;
	private String firstName;
	private String lastName;
	private String id;
	private String[] courses;
	
	public Student(String firstName, String lastName, String id, String[] courses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.courses = courses;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getCourses() {
		return courses;
	}

	public void setCourses(String[] courses) {
		this.courses = courses;
	}



	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", courses="
				+ Arrays.toString(courses) + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courses, firstName, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName);
	}
	
	
}
