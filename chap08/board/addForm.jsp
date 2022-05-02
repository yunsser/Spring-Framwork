<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글 쓰기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<style>
	form { width: fit-content; padding: 10px; margin: 0 auto; border: 1px solid black;}
	form > div { margin: 10px 10px;}
	form > div > label { font-weight: bolder; margin-right: 2em; display: inline-block; width: 5em; }
	h3 { width: fit-content; margin: 0 auto; margin-bottom: 30px; }
	#bt { display: block; width: fit-content; margin: 0 auto;}
	#brr { background-color: white; border: none; width: fit-content; }
	#btn { background-color: white; border: 1px dotted black; }
</style>
</head>
<body>
<h3>게시판 글 쓰기</h3>
<form action="/bbs/add" method="post" enctype="multipart/form-data">
	<div><label for="author">작성자</label><input type="text" name="author" placeholder="제목 입력..."></div>
	<div><label for="title">제 목</label><input type="text" name="title" placeholder="글 입력..."></div>
	<div><label for="contents">내 용</label><textarea name="contents" cols="40" rows="5"></textarea></div>
	<div><label>첨부파일</label><input type="file" name="files" multiple></div>
	<div>
		<button type="submit">저장</button>
		<button type="reset">취소</button>
	</div>
</form>
</body>
</html>