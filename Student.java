package java0709;

import java.util.ArrayList;

public class Student {
	ArrayList<Student> stu = new ArrayList<>();

	public Student() {
	}

	public Student(String name, String id, String master, ArrayList<String> course) {
		this.name = name;
		this.id = id;
		this.master = master;
		this.course = course;
	}

	public Student(String id, String name) {
		this.name = name;
		this.id = id;
	}

	public Student(String id, String name, String master) {
		this.name = name;
		this.id = id;
		this.master = master;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < course.size(); i++) {
			if (i == course.size() - 1) {
				str = str + course.get(i);
			} else {
				str = str + course.get(i) + ",";
			}

		}
		return id + "," + name + "," + master + ",{" + str + "}";
	}
	public String listCourse() {
		String str = "";
		for (int i = 0; i < course.size(); i++) {
			if (i == course.size() - 1) {
				str = str + course.get(i);
			} else {
				str = str + course.get(i) + ",";
			}

		}
		return "{" + str + "}";
	}
	private String name;
	private String id;
	private String master;
	private ArrayList<String> course;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public ArrayList<String> getCourse() {
		return course;
	}

	public void setCourse(ArrayList<String> course) {
		this.course = course;
	}

}
