<%@page import="model.MemberBean"%>
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
	
	table {
		width: 500px;
		margin: 0 auto;
	}
	
	table, tr, th, td {
		border: 1px solid black;
	}
	
	.mainName {
		padding: 10px;
	}
	
	.subName {
		width: 150px;
		padding: 5px;
		text-align: center;
	}
	
	.control {
		width: 350px;
	}
	
	.end {
		padding: 5px;
		text-align: center;
	}
</style>
</head>
<body>
	<!-- 1. 테이블에서 사용자가 선택한 회원 한명의 정보를 가져옴 -->
<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	
	MemberDAO mdao = new MemberDAO();
	MemberBean mbean = mdao.SelectOneMember(id);
%>	

	<!-- 2. table 태그를 이용하여 화면에 사용자가 선택한 회원 한명의 정보를 출력 -->
	<table>
		<thead>
			<tr>
				<th colspan="2" class="mainName">회원정보 상세</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th class="subName">아이디</th>
				<td class="control"><%=mbean.getId() %></td>
			</tr>
			<tr>
				<th class="subName">이메일</th>
				<td class="control"><%=mbean.getEmail() %></td>
			</tr>
			<tr>
				<th class="subName">전화번호</th>
				<td class="control"><%=mbean.getTel() %></td>
			</tr>
			<tr>
				<th class="subName">취미</th>
				<td class="control"><%=mbean.getHobby() %></td>
			</tr>
			<tr>
				<th class="subName">직업</th>
				<td class="control"><%=mbean.getJob() %></td>
			</tr>
			<tr>
				<th class="subName">연령</th>
				<td class="control"><%=mbean.getAge() %></td>
			</tr>
			<tr>
				<th class="subName">소개</th>
				<td class="control"><%=mbean.getInfo() %></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2" class="end">
					<button onclick="location.href='A06MemberUpdateForm.jsp?id=<%=mbean.getId() %>'">회원정보 수정</button>
					<button onclick="location.href='A08MemberDeleteForm.jsp?id=<%=mbean.getId() %>'">회원정보 삭제</button>
					<button onclick="location.href='A04MemberList.jsp'">회원전체 보기</button>
					<button onclick="location.href='A01MemberJoin.jsp'">회원정보 입력</button>
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>




















