package dbtest;

import java.sql.*;
import java.util.Scanner;

import util.DBUtil;

public class ConnectionSample4 {

	public static void main(String[] args) {
		//모든 부서 조회
		Connection conn = DBUtil.getConnection();
		Statement st= null;
		ResultSet rs= null;
		//급여를 가변으로 입력받아서 입력값 이상의 급여를 받는 직원들 조회
		//직원이름, 급여 , 입사일, 부서번호 출력한다.
		Scanner sc= new Scanner(System.in);
		System.out.print("급여를 입력하세요: ");
		int sal= sc.nextInt();
		String sql="select first_name, salary, hire_date, department_id"
				+" from employees"
				+" where salary>="+sal;
		
		try {
			st= conn.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()) {
				String fname= rs.getString("first_name");
				int salary= rs.getInt("salary");
				Date d= rs.getDate("hire_date");
				int did= rs.getInt("department_id");
				
				System.out.println(fname+"\t"+salary+"\t"+d+"\t"+did);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
	}

}
