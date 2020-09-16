package java0709;

import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;

public class StudentDataDAO implements IDAO<StudentData> {
	IOMethod amd = new IOMethod();
	Student student = new Student();
	Connection conn = new MySQL().getConnection();
	PreparedStatement pstmt = null;
	ArrayList<StudentData> sddArr = new ArrayList<>();

	public ArrayList getAllStudentData() throws SQLException, IOException {

		String[] lines1 = amd.FileInput("fileTx/StudentData.txt");// 讀學生資料

		for (String line : lines1) {
			StudentData sd = new StudentData();
			sd.setId(line.split(",")[0]);
			sd.setName(line.split(",")[1]);
			sd.setBirth(line.split(",")[2]);
			sddArr.add(sd);

		}
		return sddArr;

	}

	@Override
	public boolean insert(StudentData t) {
		try {
			String sql1 = "INSERT INTO StudentData(student,name,birth) values(?,?,?)";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, t.getId());
			pstmt.setString(2, t.getName());
			pstmt.setString(3, t.getBirth());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(StudentData t) {
		try {
			PreparedStatement pstm = conn.prepareStatement("update StudentData set Student = ?,name = ?,birth =? where Student =?");
			pstm.setString(1, t.getId());
			pstm.setString(2, t.getName());
			pstm.setString(3, t.getBirth());
			pstm.setString(4, t.getId());
			pstm.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean delete(StudentData t) {
		try {
			PreparedStatement pstm = conn.prepareStatement("delete from StudentData where id =?");
			pstm.setString(1, t.getId());
			pstm.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public StudentData get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentData get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentData> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
