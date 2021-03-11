package dbtest;

import java.sql.*;

import util.DBUtil;

public class ConnectionSample3 {

	public static void main(String[] args) {
		//모든 부서 조회
		Connection conn = DBUtil.getConnection();
		Statement st= null;
		ResultSet rs= null;
		String sql="select * from departments";
		
		try {
			st= conn.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()) {
				int id= rs.getInt(1);
				String name= rs.getString("department_name");
				System.out.println(id+"\t"+name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
	}

}
