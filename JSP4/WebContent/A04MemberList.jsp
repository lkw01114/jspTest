<%@page import="model.MemberBean"%>
<%@page import="java.util.Vector"%>
<%@page import="model.MemberDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body {
		font-family: "돋움", "굴림";
	}
	
	h1 {
		text-align: center;
	}
	
	table {
		width: 800px;
		margin: 0 auto;
	}
	
	table, tr, th, td {
		border: 1px solid black;
	}
	
	tr {
		height: 40px;
	}
	
	.id {
		width: 150px;
		text-align: center;
	}
	
	.email {
		width: 250px;
	}
	
	.tel {
		width: 200px;
		text-align: center;
	}
	
	.hobby {
		width: 200px;
	}
</style>
</head>
<body>
<!-- 1. member 테이블에서 모든 회원정보를 조회 -->
<%
	MemberDAO mdao = new MemberDAO();

	Vector<MemberBean> vec = mdao.SelectAllMember();
%>
<!-- 2. table 태그를 이용하여 모든 회원 정보를 화면에 출력(아이디,이메일,전화,취미만 출력) -->
	<h1>회원 정보</h1>
	
	<table>
		<thead>
			<tr>
				<th class="id">아이디</th>
				<th class="email">이메일</th>
				<th class="tel">전화번호</th>
				<th class="hobby">취미</th>
			</tr>
		</thead>
		<tbody>
<%
		for (int i = 0; i < vec.size(); i++) {
			MemberBean bean = vec.get(i);
%>		
			<tr>
				<td class="id">
					<a href="A05MemberInfo.jsp?id=<%=bean.getId() %>"> <%=bean.getId() %> </a> 
				</td>
				<td class="email"> <%=bean.getEmail() %> </td>
				<td class="tel"> <%=bean.getTel() %> </td>
				<td class="hobby"> <%=bean.getHobby() %> </td>
			</tr>
<%
		}
%>	
		</tbody>
	</table>
</body>
</html>











