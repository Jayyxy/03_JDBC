package edu.kh.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dto.User;

// (Model 중 하나)DAO (Data Access Object)
// 데이터가 저장된 곳에 접근하는 용도의 객체
// -> DB 에 접근하여 Java에서 원하는 결과를 얻기위해
//	SQL 을 수행하고 결과를 반환 받는 역할

public class UserDAO {

	
	// 필드
	// - DB 접근 관련한 JDBC 객체 참조 변수 미리 선언
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	public User selectId(Connection conn, String input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// 메서드
	/* 전달받은 Connection을 이용해서 DB에 접근하여
	 * 전달받은 아이디(input)와 일치하는 User 정보를 DB 조회하기
	 * @param conn : Service 에서 생성한 Connection 객체
	 * @param input : View 에 입력받은 아이디
	 * @return 아이디가 일치하는 회원의 User 또는 null
	 * 
	 * */
	public User selectId(Connection conn, String input) {
		
		// 1. 결과 저장용 변수 선언
		User user = null;
		try {
			
			// SQL 작성
			
			String sql = "SELECT * FROM TB_USER WHERE USER_ID = ? "
			
			// 3. PreparedStatement 객체 생성
			pstmt =conn.prepareStatement(sql);
			
			// 4. ? (위치홀더) 에 알맞은 값 세팅
			pstmt.setString(1,input);
			
			// 5. SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과가 있을 경우
			// 중복되는 아이디가 없다고 가정
			// -> 1행만 조회되기 때문에 while문 보다는 if를 사용하는게 효과적
			if(rs.next()) {
				// 첫 행에 데이터가 존재한다면
				
				// 각 컬럼의 값 얻어오기
				int userNo = rs.getInt("USER_ID");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				// java.sql.Date
				Date enrollDate = rs.getDate("ENROLL_DATE");
				
				// 조회된 컬럼값 
				
			}
			
		}catch(Exception e){
		
			e.printStackTrace()
		
		}finally {
			
			// 사용자 JDBC 객체 자원  반환 close
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		
		return user; // 결과 반환 (생성된 User 객체 또는 null)
		
	}
}
