package controller;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import model.EmpDAO;
import model.EmpVO;
import view.EmpView;

public class EmpController {

	public static void main(String[] args) {
		//1.모든직원조회
		//method1();
		
		//2.직원1건조회(id로)
//		Scanner sc= new Scanner(System.in);
//		System.out.print("보고싶은 직원의 id를 입력하세요:");
//		method2(sc.nextInt());
		
		//3.부서로 조회
//		Scanner sc= new Scanner(System.in);
//		System.out.print("부서 id를 입력하세요:");
//		method3(sc.nextInt());

		//4.job_id로 조회
//		Scanner sc= new Scanner(System.in);
//		System.out.print("Job id를 입력하세요:");
//		method3(sc.next());

		//5. salary로 조회
//		Scanner sc= new Scanner(System.in);
//		System.out.print("급여를 입력하세요(최소,최대):");
//		method4(sc.nextInt(),sc.nextInt());
		
		//6. 입사일로 조회
//		Scanner sc= new Scanner(System.in);
//		System.out.print("입사일을 입력하세요(ex.2004-01-01, 2004-12-31):");
//		method5(sc.next(),sc.next());
		
//		method7("2004-01-01","2004-12-31");
		
		//7. 이름(특정문자열)으로 조회
//		Scanner sc= new Scanner(System.in);
//		System.out.print("특정 문자열을 입력하세요:");
//		method6(sc.next());
		
		//8. 여러가지 조건
		method8(60,"IT_PROG",1000,"2005-01-01");

	}
	

	private static void method8(int dept, String job, int sal, String dt) {
		EmpDAO dao= new EmpDAO();
		List<EmpVO> emplist = dao.selectByCondition(dept,job,sal,Date.valueOf(dt));	
		EmpView.display(emplist);	
	}


	//6-2
	private static void method7(String sdate, String edate) {
		EmpDAO dao= new EmpDAO();
		Date d1= Date.valueOf(sdate); //String -> java.sql.date
		Date d2= Date.valueOf(edate);

		List<EmpVO> emplist = dao.selectByHireDate2(d1,d2);	
		EmpView.display(emplist);
	}

	//7
	private static void method6(String str) {
		EmpDAO dao= new EmpDAO();
		List<EmpVO> emplist = dao.selectByName(str);	
		EmpView.display(emplist);
		
	}

	//6-1
	private static void method5(String sdate, String edate) {
		EmpDAO dao= new EmpDAO();
		List<EmpVO> emplist = dao.selectByHireDate(sdate,edate);	
		EmpView.display(emplist);
		
	}

	private static void method4(int min, int max) {
		EmpDAO dao= new EmpDAO();
		List<EmpVO> emplist = dao.selectBySalary(min,max);	
		EmpView.display(emplist);
	}

	private static void method3(String jobid) {
		EmpDAO dao= new EmpDAO();
		List<EmpVO> emplist = dao.selectByJob(jobid);	
		EmpView.display(emplist);
	}

	private static void method2(int empid) {
		EmpDAO dao= new EmpDAO();
		EmpVO emp = dao.selectById(empid);
		EmpView.display(emp);
	}

	private static void method1() {
		EmpDAO dao= new EmpDAO();
		List<EmpVO> emplist = dao.selectAll();		
		EmpView.display(emplist);
	}

}
