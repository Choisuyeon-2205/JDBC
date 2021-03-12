package dbtest2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.oracore.Util;
import util.DBUtil;

public class JDBC_LAB2 {
	
	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	static String sql;
	
	// controller(메인)
	public static void main(String[] args) throws SQLException {
		
		
//		Integer[] depts = {30, 50, 80};
//		int minSal= 5000;
//		int maxSal= 17000;
//		method1(depts, minSal, maxSal);
		List<EmpData> emplist= method2("r");
		myPrint(emplist);
	}
	
	//VIEW
	private static void myPrint(List<EmpData> emplist) {
		
		for(EmpData emp: emplist) {
			System.out.println(emp);
		}
		
	}
	
	//MODEL- DAO
	private static List<EmpData> method2(String ch) throws SQLException {
		
		List<EmpData> emplist= new ArrayList<EmpData>();
		
		sql = 
				" select first_name, last_name, salary, hire_date"+
				" from employees"+
				" where first_name like '__'||?||'%'";
		
		conn = DBUtil.getConnection();
		st= conn.prepareStatement(sql);
		st.setString(1, ch);
		rs= st.executeQuery();

		while(rs.next()) {
			emplist.add(new EmpData(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4)));
		}
		
		DBUtil.dbClose(rs, st, conn);
		return emplist;
		
	}

	private static void method1(Integer[] depts, int minSal, int maxSal) throws SQLException {
		String dept_where= " and department_id in (";
		for(Integer i :depts) {
			dept_where += "?,";
		}
		dept_where= dept_where.substring(0,dept_where.length()-1)+")";
	
		sql = 
				"select *"+
				" from employees"+
				" where commission_pct is not null"+
				" and (to_char(hire_date,'yyyy') between '2000' and '2009')"+
				dept_where+
				" and salary between ? and ?"+
				" order by hire_date , salary desc";

		conn = DBUtil.getConnection();
		st= conn.prepareStatement(sql);
		int cnt=1;
		for(Integer i:depts) {
		st.setInt(cnt++, i);
		}
		st.setInt(cnt++, minSal);
		st.setInt(cnt++, maxSal);
		rs= st.executeQuery();
		
		//4. select결과를 사용하기
				while(rs.next()) {
					int empl_id= rs.getInt(1);
					String fname= rs.getString(2);
					Date dt= rs.getDate("hire_date");
					int sal= rs.getInt("salary");
					int did= rs.getInt("department_id");
					double comm= rs.getDouble("commission_pct");
				
					System.out.println("사원번호: "+empl_id);
					System.out.println("이름: "+fname);
					System.out.println("급여: "+sal);
					System.out.println("입사일: "+dt);
					System.out.println("부서번호: "+did);
					System.out.println("커미션비: "+comm);
					System.out.println("-----------------");
				}
				
				DBUtil.dbClose(rs, st, conn);
	}

}
