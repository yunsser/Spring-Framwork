<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CSS Float Property</title>
<style>
	#container01 > div { width: 70px; border: 1px solid black; text-align: center; margin-right: 5px;}
	.left { float: left; }
	.right { float: right; } /* 속성: 모든 효과 무시하고 내가 제일 위에 뜨게함 , 겹치는 현상도 있음 */
	
	#dumb { clear: both; }
	
	#container02 { margin-top: 20px; /* width: 500px; */ border: 1px solid black; padding:  5px;} /* 양쪽의 float를 해제한다 */	
	#container02 > img { float: left; margin-right: 10px;} /* 양쪽의 float를 해제한다 */
	#container02::after { content: ""; clear: both; display: table; } /* 유사 셀럭터 유사 쿨래스 */
	

</style>
</head>
<body>

<div id="container01">
	<div class="left">A</div>
	<div class="left">B</div>
	<div class="left">C</div>
	<div class="right">D</div>
	<div class="right">E</div>
</div>

<div id="dumb"></div>

<div id="container02">
	<img src="/images/baeroo.jpg"> <!-- 인라인의 특징 -->
	배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 배로는 귀여워 
</div>

</body>
</html>