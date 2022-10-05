<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div align="center">
		<h2>글쓰기</h2>
		<form action="/updateBoard" method="post">
		<input type="hidden" name="seq" value="${board.seq}">
		<table style="width:600px; text-align:center; background:#faf5ef;" border="1">
			<tr height="30">
				<td align="center" width="150px">제목</td>
				<td align="center" width="450px"><input type="text" name ="title" size="50" value="${board.title}"></td>
				<td align="center">작성자</td>
				<td align="center"><input type="text" name ="writer" size="50" value="${board.writer}" readonly="readonly"></td>
			</tr>
			<tr height="30">
				<td align="center">등록일</td>
				<td align="center"><fmt:formatDate value="${board.createDate }"/></td>
				<td align="center">조회수</td>
				<td align="center"><input type="text" name ="cnt" size="50" value="${board.cnt}" readonly="readonly"></td>
			</tr>
			<tr>
				<td align="center" colspan="2">내용</td>
				<td align="center" colspan="2"><textarea rows="10" cols="50" name="content"><c:out value="${board.content}" /></textarea></td>
			</tr>

			<tr height="30">
				<td align="center" colspan="4">
					<button type="submit">글 수정</button>&nbsp;&nbsp;
					<a href="/deleteBoard?seq=<c:out value='${board.seq}' />"><button type="button" onclick="return confirm('정말로 삭제하시겠습니까?')">글 삭제</button></a>&nbsp;&nbsp;
					<button type="button" onclick="location.href='/getBoardList02'">목록 보기</button>&nbsp;&nbsp;				
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>