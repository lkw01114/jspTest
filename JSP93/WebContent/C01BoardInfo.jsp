<%@page import="model.BoardBean"%>
<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C01BoardInfo</title>
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
		width: 100px;
		padding: 5px;
	}
	.st1 {
		width: 200px;
		padding: 5px;
		text-align: center;
	}
	.end {
		padding: 5px;
		text-align: center;
	}
</style>
</head>
<body>

<table>
	<thead>
		<tr>
			<th colspan="4" class="mainName">글상세</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th class="subName">글번호</th>
			<td class="st1">${ bean.num }</td>
			<th class="subName">조회수</th>
			<td class="st1">${ bean.readcount }</td>
		</tr>
		<tr>
			<th class="subName">작성자</th>
			<td class="st1">${ bean.writer }</td>
			<th class="subName">작성일</th>
			<td class="st1">${ bean.reg_date }</td>
		</tr>
		<tr>
			<th class="subName">이메일</th>
			<td colspan="3">${ bean.email }</td>
		</tr>
		<tr>
			<th class="subName">제목</th>
			<td colspan="3">${ bean.subject }</td>
		</tr>
		<tr>
			<th class="subName">글내용</th>
			<td colspan="3">${ bean.content }</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4" class="end">
				<button onclick="location.href='BoardReWrite.do?num=${ bean.num }&ref=${ bean.ref }&re_step=${ bean.re_step }&re_level=${ bean.re_level }'">답글쓰기</button>
				<button onclick="location.href='BoardUpdate.do?num=${ bean.num }'">수정하기</button>
				<button onclick="location.href='BoardDelete.do?num=${ bean.num }'">삭제하기</button>
				<button onclick="location.href='BoardList.do'">목록보기</button>
			</td>
		</tr>
	</tfoot>
</table>

</body>
</html>