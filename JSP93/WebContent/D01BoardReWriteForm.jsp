<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>D01BoardReWriteForm</title>
<style>
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
		width: 150px;
		padding: 5px;
		text-align: right;
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

<form action="BoardReWriteProc.do" method="post">
<table>
	<thead>
	<tr>
		<th colspan="2" class="mainName">답글쓰기</th>
	</tr>
	</thead>
	<tbody>
		<tr>
			<th class="subName">작성자</th>
			<td>
				<input type="text" name="writer" class="control">
			</td>
		</tr>
		<tr>
			<th class="subName">제목</th>
			<td>
				<input type="text" name="subject" class="control" value="[Re] ">
			</td>
		</tr>
		<tr>
			<th class="subName">이메일</th>
			<td>
				<input type="email" name="email" class="control">
			</td>
		</tr>
		<tr>
			<th class="subName">비밀번호</th>
			<td>
				<input type="password" name="password" class="control">
			</td>
		</tr>
		<tr>
			<th class="subName">글내용</th>
			<td>
				<textarea rows="10" cols="50" name="content" class="control"></textarea>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2" class="end">
				<input type="hidden" name="ref" value="${ ref }">
				<input type="hidden" name="re_step" value="${ re_step }">
				<input type="hidden" name="re_level" value="${ re_level }">
				<input type="submit" value="답글저장"> &nbsp;&nbsp;
				<input type="reset" value="다시쓰기"> &nbsp;&nbsp;
				<button type="button" onclick="location.href='BoardList.do'">전체글보기</button>
			</td>
		</tr>
	</tfoot>
</table>
</form>

</body>
</html>