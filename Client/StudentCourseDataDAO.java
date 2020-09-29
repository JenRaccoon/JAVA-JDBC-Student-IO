package java0709;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDataDAO implements IDAO<StudentCourseData>{
	IOMethod amd = new IOMethod();
	Student student = new Student();
	Connection conn = new MySQL().getConnection();
	ArrayList<StudentCourseData> scdArr = new ArrayList<>();
	PreparedStatement pstmt = null;
	public ArrayList<StudentCourseData> getAllStudentCourseData() throws IOException {
			String sql4 = "INSERT INTO StudentCourseData values(?,?)";
			String[] lines4 = amd.FileInput("fileTx/StudentCourseData.txt");// ##修課資料: 學生代號,科系代號

			for (String line : lines4) {
				StudentCourseData scd = new StudentCourseData();
				scd.setIdStudent(line.split(",")[0]);
				scd.setIdDept(line.split(",")[1]);
				scdArr.add(scd);

			}
			return scdArr;

	}
	@Override
	public boolean insert(StudentCourseData t) {
		try {
			String sql4 = "INSERT INTO StudentCourseData values(?,?)";
			pstmt = conn.prepareStatement(sql4);
			pstmt.setString(1,t.getIdStudent());
			pstmt.setString(2,t.getIdDept() );
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	@Override
	public boolean update(StudentCourseData t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(StudentCourseData t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public StudentCourseData get(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StudentCourseData get(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<StudentCourseData> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}