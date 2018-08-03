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

	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*" />
	</jsp:useBean>
	
<%
	MemberDAO mdao = new MemberDAO();
	
	// 테이블에서 가져온 패스워드
	String pass = mdao.getPass(mbean.getId());
	
	if (mbean.getPass1() == null) {
		mbean.setPass1("");
	}
	
	if (mbean.getPass1().equals(pass)) {
		
		mdao.deleteMember(mbean.getId());
		
		response.sendRedirect("A04MemberList.jsp");
	} else {
%>	
		<script type="text/javascript">
			alert("비밀번호가 일치하지 않습니다!");
			history.go(-1);
		</script>
<%
	}
%>
</body>
</html>














