<%@page import="model.MemberDAO"%>
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

<!-- 0. 한글 깨짐 방지 -->
<%
	request.setCharacterEncoding("utf-8");
%>

<!-- 1. 사용자가 입력한 회원정보를 MemberBean에 셋팅 -->
	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*" />
	</jsp:useBean>

<!-- 2. 취미(hobby) 처리	 -->
<%
	String [] hobby = request.getParameterValues("hobby");
	String texthobby = "";
	
	for(int i = 0; i < hobby.length; i++){
		texthobby += hobby[i] + " ";
	}
	
	mbean.setHobby(texthobby);
%>

<!-- 3. 패스워드와 패스워드 확인 비교 -->
<%
	if(!mbean.getPass1().equals(mbean.getPass2())) {
%>
	<script type="text/javascript">
		alert("비밀번호와 비밀번호 확인이 일치하지 않습니다!");
		history.go(-1);
	</script>
<%		
	} else {
//	4. 오라클에 데이터 insert
		MemberDAO mdao = new MemberDAO();
		
		// MemberDAO의 insertMember를 이용하여 insert 처리
		mdao.insertMember(mbean);
		
		// insert 이후 회원 리스트를 보여주는 페이지로 이동
		response.sendRedirect("A04MemberList.jsp");
	}
%>
</body>
</html>
