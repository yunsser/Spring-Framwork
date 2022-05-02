<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CSS Position Property Test</title>
<style>
	div { display: inline-block; } 
	div { width: 100px; height: 100px; border: 1px solid black;}
	#div1 { background-color: red; position: relative; } /*relative(상대적인위치, 부모한테 포지션 속성이 있는 태그 div1 기준)*/
	#div2 { background-color: blue; top: 50px; left: 50px; position: relative; } /* 애를 기점으로 떨어진다 */
	#div3 { background-color: green; top: 50px; left: 50px; position: relative; }
	
	#container{position: relative;}
	#div4 { background-color: yellow; position: relative; left: 50px; top: 100px;}
	#div5 { background-color: black; position: relative; left: 50px; top: 100px;}
	
	#div6 { background-color: pink; position: absolute; top: 100px; left: 200px; }
	#div7 { background-color: purple; position: absolute; top: 100px; left: 100px; }
</style>
</head>
<body>
<h3>CSS Position Test</h3>
<!-- 
	main, heapder, footer, article, sectiom, aside, nav ...
 -->
 <div id="div1">DIV1
 	<div id="div2">DIV2
 		<div id="div3">DIV3</div>
 	</div>
 </div>
 
 <div id="container">
 	<div id="div4"></div>
 	<div id="div5"></div>
 </div>
 
<div id="div6"></div>
<div id="div7"></div>
 
 
</body>
</html>