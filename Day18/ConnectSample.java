package dbtest;

import java.sql.*;

public class ConnectSample {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1. Oracle Driver 준비 ... jar
		String driverName= "oracle.jdbc.driver.OracleDriver";
		Class.forName(driverName);
		System.out.println("1.driver load 성공");
		
		//2. DB연결: Connection
		Connection conn = null;
		String url= "jdbc:oracle:thin:@localhost:1521:xe";
		String userid="hr", password="hr";
		conn = DriverManager.getConnection(url, userid, password);
		System.out.println("2.connection성공");
		
		//3. SQL문 실행
		String sql = " select first_name, last_name, salary"
				+ " from employees"
				+ " where employee_id = 100";
		Statement st= conn.createStatement();
		ResultSet rs= st.executeQuery(sql);
		while(rs.next()) {
			String fname = rs.getString(1);
			String lname = rs.getString(2);
			int sal = rs.getInt(3);
			System.out.println(fname+"\t"+lname+"\t"+sal);
		}
		
		//4. 자원 반납 .. db연결해제
		rs.close();
		st.close();
		conn.close();
		
	}

}
