<%@page import="model.BoardBean"%>
<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E01BoardUpdateForm</title>
<style type="text/css">
	body {
		font-family: "돋움";
	}
	table {
		width: 600px;
		margin: 0 auto;
	}
	table, tr, th, td {
		border: 1px solid black;
	}
	.mainName {
		padding: 10px;
	}
	.subName {
		width: 110px;
		padding: 5px;
	}
	.st1 {
		width: 150px;
		padding: 5px;
		text-align: center;
	}
	.control {
		width: 450px;
	}
	.end {
		padding: 5px;
		text-align: center;
	}
</style>
</head>
<body>

<form action="BoardUpdateProc.do" method="post">
<table>
	<thead>
		<tr>
			<th class="mainName" colspan="4">글수정</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th class="subName">작성자</th>
			<td class="st1">${ bean.writer }</td>
			<th class="subName">작성일</th>
			<td class="st1">${ bean.reg_date }</td>
		</tr>
		<tr>
			<th class="subName">제목</th>
			<td colspan="3">
				<input type="text" name="subject" value="${ bean.subject }" class="control">
			</td>
		</tr>
		<tr>
			<th class="subName">비밀번호</th>
			<td colspan="3">
				<input type="password" name="password" class="control">
			</td>
		</tr>
		<tr>
			<th class="subName">글내용</th>
			<td colspan="3">
				<textarea rows="10" cols="50" name="content" class="control">${ bean.content }</textarea>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td class="end" colspan="4">
				<input type="hidden" name="num" value="${ bean.num }">
				<input type="submit" value="글수정">&nbsp;&nbsp;
				<input type="reset" value="다시작성">&nbsp;&nbsp;
				<button type="button" onclick="location.href='B01BoardList.jsp'">글목록보기</button>
			</td>
		</tr>
	</tfoot>
</table>
</form>

</body>
</html>

















