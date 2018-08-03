<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// 0. 한글 깨짐 방지
	request.setCharacterEncoding("utf-8");
%>

	<!-- 1. 사용자가 입력한 회원정보를 MemberBean에 셋팅 -->
	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*" />
	</jsp:useBean>
	
<%
	/* 2. 취미(hobby) 처리 */
	String [] hobby = request.getParameterValues("hobby");
	String texthobby = "";
	
	for(int i = 0; i < hobby.length; i++){
		texthobby += hobby[i] + " ";
	}
	
	mbean.setHobby(texthobby);
	
	/* 3. 오라클에 접속 및 데이터 처리 */
	String id = "scott";		// DB 접속 아이디
	String pass = "tiger";		// DB 접속 패스워드
	String url = "jdbc:oracle:thin:@localhost:1521:XE";	// 오라클 접속 경로
	
	try{
		// 3-1. 오라클 데이터 베이스 사용 선언(오라클 드라이버 로드)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 3-2. 오라클에 접속
		Connection con = DriverManager.getConnection(url, id, pass);
		
		// 3-3. 쿼리 준비 (PreparedStatment 객체 사용)
		String sql = "insert into member values(?,?,?,?,?,?,?,?)";
		
		// 3-4. PreparedStatment 객체를 이용하여 JSP에서 쿼리를 사용하도록 설정
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		// 쿼리에서 "?"의 순서에 맞게 데이터 매핑
		pstmt.setString(1, mbean.getId());
		pstmt.setString(2, mbean.getPass1());
		pstmt.setString(3, mbean.getEmail());
		pstmt.setString(4, mbean.getTel());
		pstmt.setString(5, mbean.getHobby());
		pstmt.setString(6, mbean.getJob());
		pstmt.setString(7, mbean.getAge());
		pstmt.setString(8, mbean.getInfo());
		
		// 3-5. 쿼리 실행
		pstmt.executeUpdate();	// insert, update, delete 시 사용하는 메서드
		
		// 3-6. 자원 반납
		pstmt.close();
		con.close();
	}catch(Exception e){
		e.printStackTrace();
	}
%>
	
</body>
</html>



























































