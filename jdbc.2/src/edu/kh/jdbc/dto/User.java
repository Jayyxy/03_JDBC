package edu.kh.jdbc.dto;

public class User {
	
// DTO (Data Transfer Object - 데이터 전송 객체)
// : 값을 묶어서 전달하는 용도의 객체
// -> DB에 데이터를 묶어서 전달하거나
// DB에서 조회한 결과를 가져올 때 사용 (데이터 교한을 위한 객체)
// == DB 특정 테이블의 한 행의 데이터를
// 저장할 수 있는 형태로 class 작성

// lombok : 자바 코드에서 반복적으로 작성해야하는 코드 (보일럴 플 뭐시기)
// 자동으로 완성해주는 라이브러리

@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor 
@ToString 
public class user{
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String enrollDate;
	// -> enrollDate를 java.sql.Date 이 아닌 String 으로 한 이유
	// -> DB 조회 시 날짜 데이터를 원하는 형태의 문자열로
	// 변환하여 조회할 예정 -> TO_CHAR() 이용
	
	
	
	// getter/setter
	// 생성자들
	//
	
	
	
	
	
	
	
}
	
	
}
