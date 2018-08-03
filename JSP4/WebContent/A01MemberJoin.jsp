<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
		font-family: "돋움";
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
		text-align: right;
	}
	
	.end {
		padding: 5px;
		text-align: center;
	}
	
	.control {
		width: 350px;
	}
	
	#selectcontrol {
		width: 150px;
	}
</style>
</head>
<body>
<form action="A03MemberJoinProc.jsp" method="post">
	<table>
		<thead>
			<tr>
				<th colspan="2" class="mainName">회원정보입력</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th class="subName">아이디</th>
				<td>
					<input type="text" name="id" class="control">
				</td>
			</tr>
			<tr>
				<th class="subName">패스워드</th>
				<td>
					<input type="password" name="pass1" class="control">
				</td>
			</tr>
			<tr>
				<th class="subName">패스워드 확인</th>
				<td>
					<input type="password" name="pass2" class="control">
				</td>
			</tr>
			<tr>
				<th class="subName">이메일</th>
				<td>
					<input type="email" name="email" placeholder="abc@abc.com" class="control">
				</td>
			</tr>
			<tr>
				<th class="subName">전화번호</th>
				<td>
					<input type="text" name="tel" class="control">
				</td>
			</tr>
			<tr>
				<th class="subName">취미</th>
				<td>
					<input type="checkbox" name="hobby" value="낚시" id="chk1"><label for="chk1">낚시</label>&nbsp;
					<input type="checkbox" name="hobby" value="등산" id="chk2"><label for="chk2">등산</label>&nbsp;
					<input type="checkbox" name="hobby" value="영화" id="chk3"><label for="chk3">영화</label>&nbsp;
					<input type="checkbox" name="hobby" value="음악" id="chk4"><label for="chk4">음악</label>&nbsp;
				</td>
			</tr>
			<tr>
				<th class="subName">직업</th>
				<td>
					<select name="job" id="selectcontrol">
						<option value="프로그래머">프로그래머</option>
						<option value="디자이너">디자이너</option>
						<option value="모델러">모델러</option>
						<option value="관리자">관리자</option>
					</select>
				</td>
			</tr>
			<tr>
				<th class="subName">연령</th>
				<td>
					<input type="radio" name="age" value="10" id="rad1"><label for="rad1">10대</label>&nbsp;
					<input type="radio" name="age" value="20" id="rad2"><label for="rad2">20대</label>&nbsp;
					<input type="radio" name="age" value="30" id="rad3"><label for="rad3">30대</label>&nbsp;
					<input type="radio" name="age" value="40" id="rad4"><label for="rad4">40대</label>&nbsp;
				</td>
			</tr>
			<tr>
				<th class="subName">소개</th>
				<td>
					<textarea rows="5" cols="40" name="info">본인소개를 간략히 해주세요.</textarea>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2" class="end">
					<input type="submit" value="등록">
					<input type="reset" value="취소">
					<button type="button" onclick="location.href='A04MemberList.jsp'">회원전체 보기</button>
				</td>
			</tr>
		</tfoot>
	</table>
</form>
</body>
</html>