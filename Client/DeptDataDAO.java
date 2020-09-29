package java0709;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDataDAO implements IDAO<DeptData> {
	IOMethod amd = new IOMethod();
	Student student = new Student();
	Connection conn = new MySQL().getConnection();
	PreparedStatement pstmt = null;
	ArrayList<DeptData> deptArr = new ArrayList<>();

	public ArrayList<DeptData> getAllDeptData() throws IOException {

		String[] lines2 = amd.FileInput("fileTx/DeptData.txt");// 科系資料: 代號,名稱
		for (String line : lines2) {
			DeptData dd = new DeptData();
			dd.setIdDept(line.split(",")[0]);
			dd.setDeptName(line.split(",")[1]);
			deptArr.add(dd);

		}
		return deptArr;
	}

	@Override
	public boolean insert(DeptData t) {

		String sql2 = "INSERT INTO DeptData values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, t.getIdDept());
			pstmt.setString(2, t.getDeptName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean update(DeptData t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(DeptData t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DeptData get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeptData get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeptData> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
