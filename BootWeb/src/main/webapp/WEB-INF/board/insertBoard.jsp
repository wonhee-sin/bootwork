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
		<form action="/insertBoard" method="post">
		<table style="width:600; text-align:center; background:#faf5ef;" border="1">
			<tr height="30">
				<td align="center" width="150">제목</td>
				<td align="center" width="450"><input type="text" name ="title" size="50"></td>
			</tr>
			<tr height="30">
				<td align="center">작성자</td>
				<td align="center"><input type="text" name ="writer" size="50"></td>
			</tr>
			<tr height="30">
				<td align="center">내용</td>
				<td align="center"><textarea rows="10" cols="50" name="content"></textarea></td>
			</tr>
			<tr height="30">
				<td align="center" colspan="2">
					<button type="submit">새글 등록</button>&nbsp;&nbsp;
					<button type="button" onclick="location.href='/getBoardList02'">목록 보기</button>&nbsp;&nbsp;				
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>