<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자 정보 리스트</title>
<style>
	h3 { width:fit-content; margin:10px auto;}
	table { border:1px solid black; border-spacing:0; width:500px; margin:0 auto; }
	
	th { border:1px solid black; background-color:#dddddd;}
	th, td { border-right: 1px solid black; } /*상하 좌우*/
	th { border-bottom: 3px double black; }
	td { border-bottom:1px dashed black; padding: 3px 10px; }
	td:nth-child(4){ text-align: center; }
	td:last-child { border-right: none; } /* 옆길이, 오른쪽 테투리를 없애겠다, 점선 없애는 */
	tr:nth-child(odd) { background-color: #eeeeee; } /*even 짝수, odd 홀수*/
	tr:last-child >td { border-bottom: none; } /* 테투리의 바닥을 점선으로 하지말아라 */
	div { display: block; width: fit-content; margin: 0 auto; }
	a { background-image: url(""); }
</style>
<!-- <script>
function sessionCheck(uid){
	var suid = '${suid}';
	if(suid==uid){
		var url='/jdbc/detail?useruid='+uid;
		location.href = url;
		//alert(url)
	}else{
		alert('상세정보확인');
	}
}

</script> -->
</head>
<body>
<h3>사용자 정보 리스트</h3>
<table>

<tr><th>ID</th><th>Name</th><th>Phone</th></tr>
<c:forEach var="u" items="${list}" >

	<tr>  <!-- 돌아갈 프로그램지목 ? 뒤에있는 프로그램을 받아와서 쓰기  -->
	<td><a href="/jdbc/detail?useruid=${u.uid}" >${u.uid}</a></td>
	<%-- <td><a href="javascript:sessionCheck('${u.uid}')" >${u.uid}</a></td> --%>
	<td>${u.name}</td>
	<td>${u.phone}</td>
	</tr>
</c:forEach>
</table>
<p>
	<div>
	<span><a href="/jdbc/add" target="_blank"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAZlBMVEUAAAD////Nzc3c3Nzg4OD8/PypqamQkJATExPv7++4uLhJSUkZGRmCgoL19fWvr69gYGB0dHShoaFYWFg9PT0vLy8mJiYJCQm+vr5tbW1CQkLExMRnZ2fk5OTW1tYhISGUlJQWFhZus4QwAAAHGUlEQVR4nOWd6XqqMBCGw2GTRQRFwVqrvf+bPKBWQUlIQiYTwve/hfeRZNZMiAMtP3PrYBsWu3J/SFcVqVbpYV/uinAb1G7mgz+fAP7vyKvD41dFWKq+jmHtRYBvAUXoxkWSMtm6SpMidoHeBILwHOQrbriXVnlwBngb1YTZac3/0w38mOtTpviNlBJG8fcEuj99x0qXpULCOmdvKvyq8lrda6ki9NaK6P609hS9mRLCbJMo5muVbJQsSQWEUThlb2EpDRWsyMmE5wII765isgGZSOjmoHyt8okLchKhdwTna3WcxDiBEPj77KqYsB6lCf2rNr5WV+kgRJYw/tUKSMhvrJXQhbB/Y0rkog8pwhCBr1WoifDnggRIyOVHA6GP9QPeFQrvOKKEXokKSEgpahwFCQNkvlYBIGEG76PxKBeKOUQI3QM220MHEbshQBhjg3UkYP75CVUH8dO0Vk7o77CZ3rTjNRuchBG2kfhUyRlv8BG6eG4MXRe+/YaL8B82DEX/VBGesEmoOqkhNMlKvIvDaowTbrApmNpMJzQbkANxjNDkT/SusQ91hNDcTealke2GTWiqmeiLbTSYhC72u3OKafpZhJGJnsyQLiwHjkHom+eL0lQy3HAGoWnRBEs7GUKz4sEx0eNFKqH5hrAvqlmkEc5lG32JtqFSCDNTkk78OlAycBRCM9KGYspFCE1I/IprOFU8SOhhv6ukBhP+Q4QzMvV9DRr+IULc6tIUDRUYBwh/sN9zggbqiwOEc/G3h3ThIZzvN9rq8zv9IJyfM9PXh2vzQYjRZaFSyRjh3BzuT7274G+EPkwj0MZzP+XBJCp/fSbhFeShlFwRUJ7ryiI8wzxTLyHpt6T2CaG6DfUSFnRCMI9bL2HfA+8RgjXEaiY80gjhjL1mwp7Z7xLCBfa6CbvhfocQaiMl+gm722mHELBtWzthZzt9EUZwz9NPSF6VjBchZNSkn/AVRT0JM6ijPa30E6bZByFovV4/4au+/yQEjQsRCJN3QtgUKQLh03X7I4StpWEQrt8IQR+GQkj6hDXsw1AI6x4hcK0JhTDvEkaqTmFThEJYRR1C6AwbCuEj63YnVDEogCUcwu8XYQb8KCRCkj0JwfvzkAhPT0Lw1hkkwvWTEDKsuAmJMP0jBExfPIREeEtmtITwnRdYhMGDEL55BoswfxDKjDwSExbh6k6ooeqLRdimhomWoigaYXwj1DDeAo2wuBFqqNyjESYtYQRu7xEJ06gh1NGmh0ZIvIYQOIFxEx5h3RDq6IHCIwwbQh2TkPAIjw3hl4bn4BF+OcQHTkLdhEdY+QQ8g9EKj7Dh09KLiEjoEh3GApOwJrLh7+afgIYPe2Qi/0K2vhmQreRfqhq+yStZ12tLZA0+1GBjmmT3i5DIxk5zISyI7DnKuRDuiOz5mLkQlmRvOeGeyB40nAvhgchG+HMhTIlssnQuhCsiG1rMhVA+dJoLoTzjXAirBaxD+/dS++2h/T6N/X6p/bGF/fGh/TG+/Xka+3Nt9udL7c9521+3sL/2ZH/9cAE1YPvr+Pb3YtjfT2N/T5T9fW0L6E20v7/U/h5h+/u8F9Crb/95C/vPzNh/7mkBZ9fsP39o/xlS+88BL+Ast/3n8e2fqbCAuRj2zzZZwHwa+2cM2T8nagGzvuyf12b/zL0FzE20f/blAuaX2j+DFtDq6ybsNvtYOQu6N9B7YfO87Z/JDmYT9RL2b0Nc2t0IC7jfwv47ShZwz8wC7gqy/76nBdzZtYB712b8nXLenbeA+w/tv8NyAfeQLuAu2QXcBzxD10bwTuf5ueDC93Iv4G51x5E9e4mhHR2DQTgjwz9o6scJnWguPvglYlCwCGezoTLPszIJ9Zwum6zhPBcfIXxPnwKd2AgjhDMwi1RDyEkIW99XoM0YwCih4YijgByERn+oY58oH6HB283IJsNNaKzRYJsJEULHNdG7ufANruAjdCLzfNSS5aqJEzq+aZHGjuFsSxGaFi/S40F5QqOsBoeVkCB0XFPSUweR4TgihE5mRpIxp2TVFBCakSoeTvyqInQ8bLNRig43EiV0fNzKVMhrJOQJHecHz8G5DNQHAQjxCoxD5UEYQsfF6NhI5AaoyRE25h+mtYiuXwEjr4TQ8a9aAa/CO8xkQsc5axio8VDBGUcoJmyMo44JTA3fpPl+kwibLQfej8snzi+cSAj+rRbn8VcAJmzi/xDqQFEaTlh/CgmbmGMDYR+TjVAMQZMSwkae6hTAWtX4UFWEjepc1YnwKq/VvZZCwmZFxipGF3zHClbfS0oJG2Wn9ZR9J12flCy+jlQTtjoHucxgplUeTDYNA4IgbOXGRcL/Y6ZJEUONXoYibBV5dXj8Ym8/1dcxrD2lC+9NkIR3+ZlbB9uw2JX7Q7qqSLVKD/tyV4TboHYz6ZCBW/8B05RlojUf5k0AAAAASUVORK5CYII=" width="30px" height="30px"  alt="사용자정보추가"></a></span>
	<span><a href="/jdbc/logout"><img src="data:image/png;base64,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" width="30px" height="30px"  alt="로그아웃"></a></span>
	</div>

</body>
</html>