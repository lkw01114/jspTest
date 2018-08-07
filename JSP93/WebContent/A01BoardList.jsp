<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>B01BoardList</title>
<style type="text/css">
	body {
		font-family: "돋움";
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		width: 700px;
		margin: 0 auto;
	}
	
	table, tr, th, td {
		border: 1px solid black;
		border-collapse: collapse;
	}
	
	#num {
		padding: 10px;
		width: 50px;
	}
	#subject {
		padding: 10px;
		width: 350px;
	}
	#writer {
		padding: 10px;
		width: 100px;
	}
	#reg_date {
		padding: 10px;
		width: 140px;
	}
	#readcount {
		padding: 10px;
		width: 60px;
	}
	
	.st1 {
		padding: 5px;
		text-align: center;
	}
	.st2 {
		padding: 5px;
		text-align: left;
	}
	
	.end {
		padding: 5px;
		text-align: center;
	}
	
	a {
		text-decoration: none;
	}
	
	#pagecount {
		text-align: center;
	}
</style>
</head>
<body>

<!-- 수정완료시 메시지 처리 -->
<c:if test="${ msg == 'UPDATE_OK' }">
	<script type="text/javascript">
		alert("수정이 완료되었습니다.");
	</script>
</c:if>

<!-- 삭제완료시 메시지 처리 -->
<c:if test="${ msg == 'DELETE_OK' }">
	<script type="text/javascript">
		alert("삭제가 완료되었습니다.");
	</script>
</c:if>

<h2>[전체글 보기]</h2>
<table>
	<thead>
		<tr>
			<th id="num">번호</th>
			<th id="subject">제목</th>
			<th id="writer">작성자</th>
			<th id="reg_date">작성일</th>
			<th id="readcount">조회수</th>
		</tr>
	</thead>
	<tbody>
	<!-- 카운터링 - Start -->
		<!-- 서블릿에서 넘겨받은 첫번재 글 번호 -->
		<c:set var="num" value="${ number }" />
	<!-- 카운터링 - End -->
		<c:forEach var="bean" items="${ vec }">
			<tr>
				<!-- 1. 글번호 -->
				<td class="st1">
					${ num }
				</td>
				<!-- 2. 글제목 -->
				<td class="st2">
					<c:if test="${ bean.re_step > 1 }">
						<c:forEach var="j" begin="1" end="${ bean.re_step - 1 }">
							&nbsp;
						</c:forEach>
					</c:if>
					<c:choose>
						<c:when test="${ bean.del_flag == 'Y' }">
							삭제된 글입니다.
						</c:when>
						<c:otherwise>
							<a href="BoardInfo.do?num=${ bean.num }">
								${ bean.subject }
							</a>
						</c:otherwise>
					</c:choose>
				</td>
				<!-- 3. 작성자 -->
				<td class="st1">
					${ bean.writer }
				</td>
				<!-- 4. 작성일 -->
				<td class="st1">
					${ bean.reg_date }
				</td>
				<!-- 5. 조회수 -->
				<td class="st1">
					${ bean.readcount }
				</td>
			</tr>
			<!-- 반복문이 끝나기 전에 화면에 출력할 글번호를 하나씩 감소시킴 -->
			<c:set var="num" value="${ number = number - 1 }" />
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5" class="end">
				<button onclick="location.href='B01BoardWriteForm.jsp'">글쓰기</button>
			</td>
		</tr>
	</tfoot>
</table>

<!-- 카운터링 - Start -->
<p id="pagecount">
	<c:if test="${ count > 0 }">
		<!-- 화면에 출력될 카운터링 개수 -->
		<c:set var="pageBlock" value="${ 3 }" />
		
		<!-- 카운터링의 전체 개수 -->
		<c:set var="pageCount" 
			value="${ count / pageSize + (count % pageSize == 0 ? 0 : 1) }" />
		
		<!-- 화면에 출력될 시작 카운터링 숫자 설정 -->	
		<c:set var="startPage" value="${ 1 }" />
		<c:choose>
			<c:when test="${ currentPage % pageBlock != 0 }">
				<fmt:parseNumber var="result" value="${ currentPage / pageBlock }" integerOnly="true" />
				<c:set var="startPage" value="${ result * pageBlock + 1 }" />
			</c:when>
			<c:otherwise>
				<fmt:parseNumber var="result" value="${ currentPage / pageBlock }" integerOnly="true" />
				<c:set var="startPage" value="${ (result - 1) * pageBlock + 1 }" />
			</c:otherwise>
		</c:choose>
			
		<!-- 화면에 출력될 마지막 카운터링 숫자 설정 -->	
		<c:set var="endPage" value="${ startPage + pageBlock - 1 }" />
		<c:if test="${ endPage > pageCount }">
			<c:set var="endPage" value="${ pageCount }" />
		</c:if>
			
		<!-- "이전"이라는 링크를 만들어야 할지 파악 및 출력 -->
		<c:if test="${ startPage > pageBlock }">
			<a href="BoardList.do?pageNum=${ startPage-pageBlock }">[이전]</a>
		</c:if>
		
		<!-- 카운터링 출력 -->
		<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
			<a href="BoardList.do?pageNum=${ i }">[${ i }]</a>
		</c:forEach>
		
		<!-- "다음"이라는 링크를 만들어야 할지 파악 및 출력 -->
		<c:if test="${ endPage < pageCount }">
			<a href="BoardList.do?pageNum=${ startPage + pageBlock }">[다음]</a>
		</c:if>
	</c:if>
</p>
<!-- 카운터링 - End -->

</body>
</html>












