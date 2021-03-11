package dbtest;

import java.sql.*;

public class ConnectionSample2 {

	public static void main(String[] args) {
		//JDBC(Java DataBase Connection)
		String driverName="oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String userid="hr", userpw="hr";
		String sql="select * from employees"; //세미콜론 넣으면 안됌
		Connection conn = null;
		Statement st = null;
		ResultSet rs= null;
		
		try {
			Class.forName(driverName);
			System.out.println("1. class load 성공");
			conn= DriverManager.getConnection(url,userid,userpw);
			System.out.println("2. Connection 성공");
			st= conn.createStatement();
			rs= st.executeQuery(sql);
			System.out.println("3. SQL문 실행 성공");
			while(rs.next()) {
				String fname= rs.getString(2);
				int salary= rs.getInt("salary");
				Date d= rs.getDate("hire_date");
				System.out.println(fname+"\t"+salary+"\t"+d);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(st!=null) st.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}

}
