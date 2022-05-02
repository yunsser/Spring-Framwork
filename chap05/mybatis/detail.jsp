<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>title</title>
<style>
	main { width: fit-content; padding: 15px; margin: 0 auto; border: 1px solid black;}
	main > div { margin: 10px; border-bottom: 1px dashed black;}
	main > div > label { font-weight: bolder; margin-right: 1em; display: inline-block; width: 4em; }
	h3 { width: fit-content; margin: 0 auto; margin-bottom: 30px; }
	#bt { display: block; width: fit-content; margin: 0 auto;}
	#btn { background-color: white; border: none; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script>
function goList()
{
	var url = "/mb/list2";
	location.href=url;
}

function goEdit()
{
	var url = "/mb/edit?useruid=${user.uid}";
	location.href=url;
}

function goDelete()
{
	//alert('hello');
	if(!confirm('현재 글을 삭제하시겠어요?')){
		alert('정상적으로 취소했습니다');
		return;
	}
	
	var obj = {};
	obj.uid = '${user.uid}';
	//alert('${user.uid}');

	$.ajax({
		url:'/mb/delete',
		method:'post',
		cache:false,
		data:obj,
		dataType:'json',
		success:function(res){
			alert(res.deleted ? '삭제 성공' : '삭제 실패');
			location.href='/mb/list';
		},
		error:function(xhr,status,err){
			alert('에러:'+err);
		}
	});
	
}
</script>
</head>
<body>
<h3>이용자 상세정보</h3>
<main>
<div><label>아이디</label>${user.uid}</div>
<%-- <div><label>암호</label>${user.pwd}</div> --%>
<div><label>이름</label>${user.name}</div>
<div><label>전화</label>${user.phone}</div>
</main>
<p>
<div id="bt">
	<button id="btn" type="button" onclick="goEdit()"><img alt="수정" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3fsadE7JHA6cs1k2pKC1WUFvQLFayqNUQ-pyqdU4pE6D0sq8m3L3Pd90-I2TrbJ1HoqE&usqp=CAU" width="30px" height="30px"></button>
	<button id="btn" type="button" onclick="goDelete()"><img alt="삭제" src="data:image/png;base64,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" width="30px" height="30px"></button>	
	<button id="btn" type="button" onclick="goList()"><img alt="리스트" src="data:image/png;base64,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" width="30px" height="30px"></button>
</div>
<%-- <c:if test="${a==b }">
	참일때만 띄워줄 버튼을 안에 넣어준다
</c:if> --%>
</body>
</html>