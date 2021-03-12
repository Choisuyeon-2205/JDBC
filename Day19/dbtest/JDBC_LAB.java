package dbtest2;

import java.sql.*;
import java.util.Scanner;

import util.DBUtil;

public class JDBC_LAB {
	
	static Connection conn;
	static Statement st;
	static ResultSet rs;
	

	public static void main(String[] args) throws SQLException {
//		Scanner sc= new Scanner(System.in);
//		String name= sc.next();
//		method1(name);
//		method2(80);
		method3("steven");

	}
	
	private static void method3(String fname) throws SQLException {
		String sql= 
				" select salary, hire_date,first_name||last_name"+
				" from employees"+
				" where first_name = initCap(?)";
		
		//1. DB연결
		conn= DBUtil.getConnection();
		//2. DB와 대화할 수 있는 통로 만들기
		PreparedStatement pst;
		pst= conn.prepareStatement(sql);
		pst.setString(1,fname);
		//3. SQL문 실행
		rs= pst.executeQuery();
		//읽기위해 접근: rs.next()
		//4. select결과를 사용하기
		while(rs.next()) {
			int sal= rs.getInt(1);
			Date dt= rs.getDate("hire_date");
			String first_name= rs.getString(3);
			System.out.println("급여: "+sal);
			System.out.println("입사일: "+dt);
			System.out.println("이름: "+first_name);
			System.out.println("-----------------");
		}
		
		DBUtil.dbClose(rs, pst, conn);
		
	}

	private static void method2(int did) throws SQLException {
		String sql= 
				" select salary, hire_date,first_name||last_name, department_id"+
				" from employees"+
				" where department_id = ?"; //?: 바인딩 변수
		
		//1. DB연결
		conn= DBUtil.getConnection();
		//2. DB와 대화할 수 있는 통로 만들기: Statement <---- PreparedStatement
		PreparedStatement pst;
		pst= conn.prepareStatement(sql);
		pst.setInt(1, did);
		//3. SQL문 실행
		rs= pst.executeQuery();
		//읽기위해 접근: rs.next()
		//4. select결과를 사용하기
		while(rs.next()) {
			int sal= rs.getInt(1);
			Date dt= rs.getDate("hire_date");
			String first_name= rs.getString(3);
			int dept_id= rs.getInt(4);
			System.out.println("급여: "+sal);
			System.out.println("입사일: "+dt);
			System.out.println("이름: "+first_name);
			System.out.println("부서번호: "+dept_id);
			System.out.println("-----------------");
		}
		
		DBUtil.dbClose(rs, pst, conn);
		
	}


	private static void method1(String fname) throws SQLException {
		String sql= 
				" select salary, hire_date,first_name||last_name"+
				" from employees"+
				" where first_name = initCap('"+fname+"')";
		
		//1. DB연결
		conn= DBUtil.getConnection();
		//2. DB와 대화할 수 있는 통로 만들기
		st= conn.createStatement();
		//3. SQL문 실행
		rs= st.executeQuery(sql);
		//읽기위해 접근: rs.next()
		//4. select결과를 사용하기
		while(rs.next()) {
			int sal= rs.getInt(1);
			Date dt= rs.getDate("hire_date");
			String first_name= rs.getString(3);
			System.out.println("급여: "+sal);
			System.out.println("입사일: "+dt);
			System.out.println("이름: "+first_name);
			System.out.println("-----------------");
		}
		
		DBUtil.dbClose(rs, st, conn);
		
	}

}
