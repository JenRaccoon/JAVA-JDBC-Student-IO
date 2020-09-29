package java0709;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class Client {

	public static void main(String[] args) throws SQLException, IOException {
		IOMethod amd = new IOMethod(); 							//創建讀取txt檔類別的對象
		StudentDAO settable = new StudentDAO();					//創建StudentDAO()類別對象
		//把所有資料匯入資料庫 分別使用各個表格DAO中的insert方法
/*		CourseDataDAO cdd = new CourseDataDAO(); //創建CourseDataDAO()對象 並使用其insert方法把讀出的資料傳入mysql CourseData 表格
		ArrayList<CourseData> cddArr = cdd.getAllCourseData();
		for (CourseData cr:cddArr) {
			cdd.insert(cr);
		}
		
		DeptDataDAO ddd = new DeptDataDAO(); //創建DeptDataDAO()對象 並使用其insert方法把讀出的資料傳入mysql DeptData 表格
		ArrayList<DeptData> dddArr = ddd.getAllDeptData();
		for (DeptData dd:dddArr) {
			ddd.insert(dd);
		}
		StudentCourseDataDAO scdd = new StudentCourseDataDAO(); //創建StudentCourseDataDAO()對象 並使用其insert方法把讀出的資料傳入mysql StudentCourseData 表格
		ArrayList<StudentCourseData> scddArr= scdd.getAllStudentCourseData();
		for (StudentCourseData scd:scddArr) {
			scdd.insert(scd);
		}
		StudentDataDAO  sdd = new StudentDataDAO(); //創建StudentDataDAO()對象 並使用其insert方法把讀出的資料傳入mysql StudentData 表格
		ArrayList<StudentData> sddArr= sdd.getAllStudentData();
		for (StudentData sd:sddArr) {
			sdd.insert(sd);
		}

		settable.allStudent();									// 建立整理好的資料到mysql Student表格 

*/
		// 使用getAll查詢結果student表格的資料
		for (Student stu : settable.getAll()) {
			System.out.println(stu.toString());
		}
		
		//使用Student的update 把相同id的學生資料更新
		Student s = settable.getAll().get(0);
/*		s.setName("王曉明");
		settable.update(s);
*/
		//使用Student的delete 把相同id的學生刪除
//		settable.delete(s);
		
		
	}

}
