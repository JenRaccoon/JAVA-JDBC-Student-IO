package javahw;

import java.io.IOException;
import java.util.ArrayList;

public class DemoMain {

	public static void main(String[] args) throws IOException {
		Student student = new Student();
		IOMethod amd = new IOMethod();
		String[] lines = amd.FileInput("data/Result.txt");
		String[] lines1 = amd.FileInput("data/StudentData.txt"); // 讀學生資料
		String[] lines2 = amd.FileInput("data/DeptData.txt");// 科系資料: 代號,名稱
		String[] lines3 = amd.FileInput("data/CourseData.txt");// 課程資料: 代號,名稱
		String[] lines4 = amd.FileInput("data/StudentCourseData.txt");// ##修課資料: 學生代號,科系代號
		//先設定student的ID和姓名
		for (String line : lines) {
			getStudent gtstu = new getStudent(line.split(",")[0], line.split(",")[1]);
			student.stu.add(gtstu);
		}
		//比對ID和科系代號取得科系名稱
		for (String line : lines2) {
			for (int i = 0; i < student.stu.size(); i++)
				if (line.split(",")[0].contentEquals(student.stu.get(i).getId().substring(0, 2))) {
					student.stu.get(i).setMaster(line.split(",")[1]);

				}
		}
		//找出學生有修課代號 並找出代號的中文名稱
		for (int i = 0; i < student.stu.size(); i++) {
			ArrayList<String> tmp = new ArrayList<>();
			for (String line : lines4) {
				if (line.split(",")[0].contentEquals(student.stu.get(i).getId())) {
					for (String li : lines3) {
						if (line.split(",")[1].contentEquals(li.split(",")[0])) {
							tmp.add(li.split(",")[1]);
						}
					}
					student.stu.get(i).setCourse(tmp);
				}

			}
		}
		//顯示所有要查詢的學生資料
		for (Student s : student.stu) {
			System.out.println(s.toString());
		}
/*		
		//創建題目二物件並呼叫
		Q2Fibonacci fib = new Q2Fibonacci();
		System.out.println(fib.Fibonacci(10));
*/
	}
}
