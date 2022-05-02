<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자정보 추가</title>
<style>
	form { width: fit-content; padding: 30px; margin: 0 auto; border: 1px solid black;}
	form > div { margin: 10px;}
	form > div > label { font-weight: bolder; margin-right: 2em; display: inline-block; width: 2em; }
	h3 { width: fit-content; margin: 0 auto; margin-bottom: 30px; }
	#bt { display: block; width: fit-content; margin: 0 auto;}
	#brr { background-color: white; border: none; width: fit-content; }
	#btn { background-color: white; border: none; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script>
function goList()
{
	var url = "/mb/list";
	location.href=url;
}

function addUser()
{
	if(!idchecked){
		alert('아이디 중복검사를 확인해주세요');
		return;
	}
	var serData = $('#addForm').serialize();
	$.ajax({
		url:'/mb/add',
		method:'post',
		cache:false,
		data:serData,
		dataType:'json',
		success:function(res){
			alert(res.added ? '저장 성공' : '저장 실패' );
             location.href = '/mb/list2';
		},
		error: function(xhr,status,err){
			alert('에러:'+err);
		}
		
	});
	return false;
}

var idchecked = false;

function idcheck() {
	var uid = $('#uid').val();
	var obj = {};
	obj.uid = uid;
	
	//alert(uid);
	$.ajax({
		url:'/mb/idcheck', 
		method:'post',
		cache:false,
		data:obj,
		dataType:'json',
		success:function(res){
			alert(res.idchecked ? '사용 가능한 아이디입니다' : '중복된 아이디입니다' );
			idchecked = res.idchecked;
		},
		error: function(xhr,status,err){
			alert('에러:'+err);
		}
		
	});
	
	
}
</script>
</head>
<body>
<h3>사용자정보 추가</h3>
<form id="addForm" onsubmit="return addUser();">

	<div><label for="id">I&nbsp; D </label>
		<input type="text" id="uid" name="uid" value="baero">
		<button id="brr" type="button" onclick="idcheck();" ><img alt="중복확인" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcDzxoNWNBF_LpGHPHUQ1cu3O3Afo9eZjkNZSrsI590SCNXP-M9SktxvnMGJTmFqPvWt0&usqp=CAU" width="20px" height="20px" ></button>
	</div>
	
	<div><label for="id">P W </label>
		<input type="text" id="pwd" name="pwd" value="1111">
	</div>
	<div><label for="id">Name </label>
		<input type="text" id="name" name="name" value="baero">
	</div>
	<div><label for="id">Phone </label>
		<input type="text" id="phone" name="phone" value="010-1111-1111">
	</div>
	<div id="bt">
		<button id="btn" type="submit" ><img alt="저장" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcDzxoNWNBF_LpGHPHUQ1cu3O3Afo9eZjkNZSrsI590SCNXP-M9SktxvnMGJTmFqPvWt0&usqp=CAU" width="30px" height="30px" ></button>
		<button id= "btn" type="button" onclick="goList()"><img alt="목록보기" src="data:image/png;base64,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" width="30px" height="30px"></button>	
	</div>
</form>
</body>
</html>