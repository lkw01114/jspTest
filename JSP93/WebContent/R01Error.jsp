<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>에러 페이지</h1>
	
	<c:if test="${ msg == 'UPDATE_PASS_ERR' }">
		<script type="text/javascript">
			alert("수정시 비밀번호가 일치하지 않습니다.");
			history.go(-1);
		</script>
	</c:if>
	
	<c:if test="${ msg == 'DELETE_PASS_ERR' }">
		<script type="text/javascript">
			alert("삭제시 비밀번호가 일치하지 않습니다.");
			history.go(-1);
		</script>
	</c:if>	
</body>
</html>