package java0709;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;

public class StudentDAO implements IDAO<Student> {
	Connection conn = new MySQL().getConnection();

	// 把Student資料整理後並丟到mysql的Student的表格
	public void allStudent() throws SQLException {
		IOMethod amd = new IOMethod();
		Student student = new Student();
		StudentDAO dao = new StudentDAO();
		String sql3 = "SELECT sd.student , sd.name , dd.DeptName ,  cd.CourseName "
				+ "FROM CourseData cd , DeptData dd , StudentCourseData scd , StudentData sd "
				+ "WHERE substring(sd.student, 1, 2) = dd.idDept && sd.student=scd.idS && scd.idD = cd.idCourse "
				+ "group by sd.student " + "order by sd.student";
		String sql4 = "SELECT a.CourseName,b.idS " + "from CourseData a Left Join StudentCourseData b "
				+ "on a.idCourse = b.idD " + "order by b.idS";
		
		PreparedStatement pstmt3 = conn.prepareStatement(sql3);
		PreparedStatement pstmt4 = conn.prepareStatement(sql4);
		ResultSet rs2 = pstmt3.executeQuery();
		ResultSet rs3 = pstmt4.executeQuery();
		//使用sql3設定學生學號 姓名 科系
		while (rs2.next()) {
			Student stuCreate = new Student(rs2.getObject("student").toString(), rs2.getObject("name").toString(),
					rs2.getObject("dd.DeptName").toString());
			student.stu.add(stuCreate);
		}
		//使用sql4語法比對學生學號和有修的課程名稱依序放入學生修課的arrayList
		for (int i = 0; i < student.stu.size(); i++) {
			ArrayList<String> arr = new ArrayList<>();
			while (rs3.next()) {
				if (student.stu.get(i).getId().contentEquals(rs3.getObject("b.idS").toString())) {
					arr.add(rs3.getObject("a.CourseName").toString());
				} else {
					rs3.previous();
					break;
				}
			}
			student.stu.get(i).setCourse(arr);
		}
		// 使用insert(Student t)方法把stu匯入mysql
		for (Student s : student.stu) {
			dao.insert(s);
		}

	}

	@Override
	public boolean insert(Student t) {
		
		try {
			PreparedStatement pstm = conn.prepareStatement("insert into Student values(?,?,?,?)");
			pstm.setString(1, t.getId());
			pstm.setString(2, t.getName());
			pstm.setString(3, t.getMaster());
			Clob clob = new SerialClob(t.listCourse().toCharArray());
			pstm.setClob(4, clob);
			pstm.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean update(Student t) {
		try {
			PreparedStatement pstm = conn.prepareStatement("update Student set id = ?,name = ?,master = ?,course = ? where id =?");
			pstm.setString(1, t.getId());
			pstm.setString(2, t.getName());
			pstm.setString(3, t.getMaster());
			Clob clob = new SerialClob(t.listCourse().toCharArray());
			pstm.setClob(4, clob);
			pstm.setString(5, t.getId());
			pstm.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean delete(Student t) {

		try {
			PreparedStatement pstm = conn.prepareStatement("delete from Student where id =?");
			pstm.setString(1, t.getId());
			pstm.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public Student get(String id) {

		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement("Select * from Student");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				if (rs.getNString("id").equals(id)) {
					Student s = new Student();
					s.setId(rs.getString("id"));
					s.setName(rs.getString("name"));
					s.setMaster(rs.getString("master"));
					Clob is = rs.getClob(4);
					String[] x = is.getSubString(1, (int) is.length()).split(",");
					ArrayList al = new ArrayList();
					for (int i = 0; i < x.length; i++) {
						if (x.length == 1) {
							al.add(x[i].substring(1, x[i].length() - 1));
						} else if (x[i].charAt(x[i].length() - 1) == '}') {
							al.add(x[i].substring(0, x[i].length() - 1));
						} else if (x[i].charAt(0) == '{') {
							al.add(x[i].substring(1));
						} else
							al.add(x[i]);
					}
					s.setCourse(al);
					System.out.println(s.toString());
					return s;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Student> getAll() {
		PreparedStatement pstm = null;
		ArrayList<Student> allStu = new ArrayList<>();
		try {
			pstm = conn.prepareStatement("Select * from Student");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getString("id"));
				s.setName(rs.getString("name"));
				s.setMaster(rs.getString("master"));
				Clob is = rs.getClob(4);
				String[] x = is.getSubString(1, (int) is.length()).split(",");
				ArrayList al = new ArrayList();
				// 清除載入科目資料的{和}
				for (int i = 0; i < x.length; i++) {
					if (x.length == 1) {
						al.add(x[i].substring(1, x[i].length() - 1));
					} else if (x[i].charAt(x[i].length() - 1) == '}') {
						al.add(x[i].substring(0, x[i].length() - 1));
					} else if (x[i].charAt(0) == '{') {
						al.add(x[i].substring(1));
					} else
						al.add(x[i]);
				}
				s.setCourse(al);
				allStu.add(s);
			}

		} catch (Exception e) {

		}
		return allStu;
	}

	@Override
	public Student get(int id) {
		return null;
	}
}
