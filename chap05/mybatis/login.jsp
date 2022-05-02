<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인</title>
<style type="text/css">
   main { width:fit-content; padding:1em; margin:0 auto; text-align: center;}
   label {display:inline-block; width:50px; text-align:right; margin:0 10px; }
   input { width:100px; }
   form {border:1px solid black; padding:10px; border-radius:3px; }
   div:last-child { margin-top:1em; }
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script>
function login()
{
	var serData = $('#loginForm').serialize();
	// uid=ingoo&upw=1234
	$.ajax({
		url:'/mb/login',
		method:'post',
		cache:false,
		data:serData,
		dataType:'json',
		success:function(res){
			//console.log(res)
			//alert(res.ok ? '로그인 성공' : '로그인 실패' );
			if(res.ok) {
                alert('로그인 성공');
             } else {
                alert('로그인 실패');
                return;
             }
             /* if(res.ok) location.href = "/user/list"; */
             location.href = '/mb/list2';
		},
		error: function(xhr,status,err){
			alert('에러:'+err);
		}
		
	});
	return false;
}



</script>
</head>
<body>
<main>
   <h3>로그인</h3>
   <form id="loginForm" onsubmit="return login();">
  <!--  <Form id="loginForm"> -->
      <div><label>아이디</label>
         <input type="text" id="uid" name="uid" value="din">
      </div>
      <div><label>암 호</label>
         <input type="password" id="pwd" name="pwd" value="1111">
      </div>
      <div>
         <button type="submit" >로그인</button>
         <button type="reset" >취 소</button>
      </div>
   </form>
</main>
<!-- <script type="text/javascript">
const form2 = document.querySelector("#loginForm") // DOM 개념 QUERYSELECTOR = GETELEMENTBYID
form2.addEventListener('submit',(e)=>{ // event listener  arrow function
	e.preventDefault() // submit event 막기 

	
	const {uid,pwd} = e.target // E.TARGET.UID / E.TARGET.PWD 
	// { uid,pwd } // 비구조 할당문
	
	fetch('/user/login',{
		method:'POST', 
		body:`uid=${uid}&pwd=1111` // template 리터널
	})
	.then(data=>data) // PROMISE OBjECT
	.then(response=> {
		const {ok} = response.data
		if( ok === true ) {
			console.log('로그인 성공')
		} else {
			// CONSOLE.LOG('로그인 실패')
		}
	})
	
	
})
</script> -->
</body>
</html>