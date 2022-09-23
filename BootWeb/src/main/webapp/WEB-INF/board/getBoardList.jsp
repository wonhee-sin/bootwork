<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2>게시글 목록</h2>
		<table style="width:600; text-align:center; background:#faf5ef;" border="1">
			<thead>
				<tr height="50">
					<th width="50">번호</th>
					<th width="200">제목</th>
					<th width="100">작성자</th>
					<th width="200">등록일</th>
					<th width="50">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ boardList }" var="board">
				<tr height="50">
					<td width="50"><c:out value="${ board.seq }" /></td>
					<td width="200"><a href="/getBoard?seq=<c:out value="${ board.seq}" />"><c:out value="${ board.title }" /></a></td>
					<td width="100"><c:out value="${ board.writer }" /></td>
					<td width="200"><fmt:formatDate value="${ board.createDate }" pattern="yyyy/MM/dd" /></td>
					<td width="50"><c:out value="${ board.cnt }" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>