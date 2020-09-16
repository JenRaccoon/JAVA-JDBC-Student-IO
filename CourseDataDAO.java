package java0709;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDataDAO implements IDAO<CourseData> {
	IOMethod amd = new IOMethod();
	Student student = new Student();

	Connection conn = new MySQL().getConnection();
	PreparedStatement pstmt = null;
	ArrayList<CourseData> cdArr = new ArrayList<>();

	public ArrayList getAllCourseData() throws IOException {
		String[] lines3 = amd.FileInput("fileTx/CourseData.txt");// 課程資料: 代號,名稱
		for (String line : lines3) {
			CourseData cd = new CourseData();
			cd.setIdCourse(line.split(",")[0]);
			cd.setCourseName(line.split(",")[1]);
			cdArr.add(cd);

		}
		return cdArr;
	}

	@Override
	public boolean insert(CourseData t) {
		try {
			String sql3 = "INSERT INTO CourseData values(?,?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, t.getIdCourse());
			pstmt.setString(2, t.getCourseName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(CourseData t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CourseData t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CourseData get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseData get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
