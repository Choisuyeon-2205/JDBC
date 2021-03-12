package model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import util.DBUtil;

//DAO(Data Access Object)
public class EmpDAO {
	
	//1. 모든 직원 조회
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn= DBUtil.getConnection();
		Statement st= null;
		ResultSet rs= null;
		String sql="select * from employees";
		try {
			st= conn.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}	
	
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp= new EmpVO();
		emp.setCommission_pct(rs.getDouble("Commission_pct"));
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setEmail(rs.getString("Email"));
		emp.setEmployee_id(rs.getInt("Employee_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setHire_date(rs.getDate("Hire_date"));
		emp.setJob_id(rs.getString("Job_id"));
		emp.setLast_name(rs.getString("Last_name"));
		emp.setManager_id(rs.getInt("Manager_id"));
		emp.setPhone_number(rs.getString("Phone_number"));
		emp.setSalary(rs.getInt("Salary"));
	
		return emp;
	}
	
	//2. 유일키(Primary key).. null불가, 필수칼럼, 중복없음 => employee_id
	//직원 번호로 직원 한명의 정보 조회
	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		Connection conn= DBUtil.getConnection();
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql="select * from employees where employee_id= ?";
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1, empid);
			rs= st.executeQuery();
			while(rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emp;
	}	
	
	//3. 특정 부서에 근무하고 있는 직원들을 조회
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn= DBUtil.getConnection();
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql="select * from employees where department_id= ?";
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1, deptid);
			rs= st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}	
	
	//4. job_id가 같은 직원들을 조회
	public List<EmpVO> selectByJob(String jobid) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn= DBUtil.getConnection();
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql="select * from employees where job_id= ?";
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, jobid);
			rs= st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}	
	
	//5. 급여로 조회
	public List<EmpVO> selectBySalary(int min, int max) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn= DBUtil.getConnection();
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql="select * from employees where salary between ? and ?";
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1, min);
			st.setInt(2, max);
			rs= st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}	
	
	//6. 입사일로 조회
	public List<EmpVO> selectByHireDate(String sdate, String edate) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn= DBUtil.getConnection();
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql="select * from employees where hire_date between ? and ?";
		try {
			st= conn.prepareStatement(sql);
			st.setString(1, sdate);
			st.setString(2, edate);
			rs= st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}	
	
	//6. 입사일로 조회
		public List<EmpVO> selectByHireDate2(Date sdate, Date edate) {
			List<EmpVO> emplist = new ArrayList<>();
			Connection conn= DBUtil.getConnection();
			PreparedStatement st= null;
			ResultSet rs= null;
			String sql="select * from employees where hire_date between ? and ?";
			try {
				st= conn.prepareStatement(sql);
				st.setDate(1, sdate);
				st.setDate(2, edate);
				rs= st.executeQuery();
				while(rs.next()) {
					emplist.add(makeEmp(rs));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(rs, st, conn);
			}
			return emplist;
		}	
	
	//7. 이름에 특정문자가 들어간 사람 조회
	public List<EmpVO> selectByName(String str) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn= DBUtil.getConnection();
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql="select * from employees where first_name like ?";
		try {
			st= conn.prepareStatement(sql);
			st.setString(1,"%"+str+"%");
			rs= st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}	
	
	//8. 여러조건으로 조회(부서,job_id,salary,hire_date)
	public List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn= DBUtil.getConnection();
		PreparedStatement st= null;
		ResultSet rs= null;
		String sql="select * from employees where department_id= ? and job_id = ? and salary >= ? and hire_date between ? and add_months(?,24)";
		try {
			st= conn.prepareStatement(sql);
			st.setInt(1, deptid);
			st.setString(2, jobid);
			st.setInt(3, sal);
			st.setDate(4, hdate);
			st.setDate(5, hdate);
			rs= st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}	

}
