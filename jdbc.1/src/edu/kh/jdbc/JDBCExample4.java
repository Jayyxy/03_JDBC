package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {
	public static void main(String[] args) {
		
		// 부서명을 입력 받아
		// 해당 부서에 근무하는 사원의 
		// 사번, 이름, 부서명, 직급명을 
		// 직급코드 오름차순으로 조회
		
		//	[실행화면]
		// 부서명 입력 : 총무부
		
		// 200 / 선동일 / 총무부 / 대표
		// 202 / 노옹철 / 총무부 / 부사장
		// 201 / 송중기 / 총무부 / 부사장 
		
		
		// - 총무부가 아닌 부서 입력시 - 
		
		// 부서명 입력 : 개발팀
		// 일치하는 부서가 없습니다! 
		
		// hint : SQL 에서 문자열은 양쪽 '' (홑따옴표) 필요
		// ex) 총무부 입력 -> '총무부'
		
		// 1. JDBC 객체 참조용 변수 선언
				Connection conn = null; // DB 연결정보 저장 객체
				Statement stmt = null; // SQL 수행, 결과 반환용 객체
				ResultSet rs = null; // -> SQL_SELECT (조회)
				
				Scanner sc = null;  // 키보드 입력용 객체
				
				try {
					
					// 2. DriverManager 객체를 이용해서 Connection 객체 생성
					// 2-1) Oracle JDBC Driver 객체 메모리 로드
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					// 2-2) DB 연결 정보 작성
					String url = "jdbc:oracle:thin:@localhost:1521:XE"; // 드라이버의 종류
					
					String userName = "kh"; // 계정
					String password = "kh1234";// 계정 비밀번호
					
					// 2-3) DB 연결 정보와 DriverManager 를 이용해서 Connection 생성 
					conn = DriverManager.getConnection(url,userName, password);
					
				
					
				// 3. SQL 작성 
					
				sc = new Scanner(System.in); 
				
				System.out.println("부서명 입력 :");
				String deptname = sc.nextLine();
				
				
				
				String slq = """
						
						SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
							FROM EMPLOYEE E
							JOIN DEPARTEMT ON (DEPT_CODE = DEPT_ID)
							JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE)
							WHERE DEPT_TITLE =  '""" + deptname + "'ORDER BY JOB_CODE";
				
				// sql에서 사용할때 문자열은 홑따옴표 사용해야함 (' ')
				
				// JOIN 을 사용할때 ON을 사용해도 되지만 다중조인같은경우 혼동 가능성 높음
				// ON 사용 ON(테이블.컬럼 = 테이블.컬럼)
				
				// 4. Statement 객체 생성
				
				stmt = conn.createStatement();
				
				// 5. SQL 수행 후 결과 반호나 받기
				rs = stmt.executeQuery(slq);
				
				
				
				// 다른 부서를 입력할때 처리하는 방법
				/*//방법 1) flag 이용법	
				
				boolean flag = true;
				// 조회결과가 있다면 false, 없다면 true
				
				// 6. 1행씩 접근해서 컬럼 값 얻어오기
				while(rs.next()) {
					
					String empId = rs.getString("EMP_ID");
					String empName = rs.getString("EMP_NAME");
					String deptTitle = rs.getString("DEPT_TITLE");
					String jobCode = rs.getString("JOB_CODE");
				
					System.out.printf("%s / %s / %s / %s \n",
										empId, empName, deptTitle, jobCode);
				if(flag){
				System.out.println("일치하는 부서가 없습니다.");
				
				}
		*/
				
				// 방법 2)  return 방법 
				
				if(!rs.next()) { // 조회결과가 없다면
					System.out.println("일치하는 부서가 없습니다!");
					return;
				}
				
				
				// while 문이 아닌 do ~ while문 사용
				// while문을 사용할시 커서가 이미 한번 사용 
				do{

					String empId = rs.getString("EMP_ID");
					String empName = rs.getString("EMP_NAME");
					String deptTitle = rs.getString("DEPT_TITLE");
					String jobCode = rs.getString("JOB_CODE");
				
					System.out.printf("%s / %s / %s / %s \n",
										empId, empName, deptTitle, jobCode);
				
					
				}while(rs.next()); {
					
					String empId = rs.getString("EMP_ID");
					String empName = rs.getString("EMP_NAME");
					String deptTitle = rs.getString("DEPT_TITLE");
					String jobCode = rs.getString("JOB_CODE");
				
					System.out.printf("%s / %s / %s / %s \n",
										empId, empName, deptTitle, jobCode);
				
				
				}	
			}catch (Exception e) {
				
				e.printStackTrace();
				
			} finally{
				// 7. 사용 완료된 JDBC 객체 자원 반환 (close)
				
				try {
					if(rs != null) rs.close();
					if(stmt !=null) stmt.close();
					if(conn != null) conn.close();
					if(sc != null) sc.close();
					
				}catch(Exception e) {
					e.printStackTrace();
					
				}
			}
		}
	}
