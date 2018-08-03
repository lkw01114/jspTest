<%@page import="model.MemberDAO"%>

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
	request.setCharacterEncoding("utf-8");
%>

	<!-- 1. useBean을 이용 : 사용자가 넘긴 데이터를 받아옴 -->
	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*" />
	</jsp:useBean>
	
	<!-- 2. 취미(hobby) 처리 -->
<%
	String [] hobby = request.getParameterValues("hobby");
	String texthobby = "";
	
	for (int i = 0; i < hobby.length; i++) {
		texthobby += hobby[i] + " "; 
	}
	
	/* 
		useBean을 이용하여 사용자 입력 데이터를 mbean에 한번에 저장했으므로..
		기존의 "mbean.hobby" 맴버 변수는 사용자가 처음 체크한 입력값 하나만 저장되어 있음
		따라서 그 내용을 하나의 스트링으로 저장한 변수를 다시 셋팅 
	*/
	mbean.setHobby(texthobby);
%>

	<!-- 3. 사용자가 수정한 내역을 테이블에 Update -->
<%
	MemberDAO mdao = new MemberDAO();

	// 패스워드만 별도로 가져오는 메서드를 이용하여 사용자의 비밀번호를 가져옴
	String pass = mdao.getPass(mbean.getId());
	
	// 사용자가 패스워드를 입력하지 않아 null로 값이 넘어오는 경우 ""로 다시 저장
	if (mbean.getPass1() == null) {
		mbean.setPass1("");
	}
	
	// 사용자가 입력한 패스워드와 테이블에서 가져온 패스워드 비교
	if (mbean.getPass1().equals(pass)) {
		
		// Update 수행
		mdao.updateMember(mbean);
		
		// Update 수행 후 회원정보 리스트로 이동
		response.sendRedirect("A04MemberList.jsp");
	} else {
%>	
	<script type="text/javascript">
		alert("비밀번호가 일치하지 않습니다.");
		history.go(-1);
	</script>
<%
	}
%>
</body>
</html>



















