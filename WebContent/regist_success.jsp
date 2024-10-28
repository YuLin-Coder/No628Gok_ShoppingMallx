<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功3秒后跳转</title>
<!-- <meta http-equiv="refresh" content="3;url=http://localhost:8080/Estore"> -->
<meta content="3;url=<%=basePath %>/frontstage_goodsServlet?action=findAllGoods">
</head>
<body>
<h1>
		注册成功，<span id="s">3</span>秒后跳转到<a href="<%=basePath %>/frontstage_goodsServlet?action=findAllGoods">首页</a>
</h1>
<script type="text/javascript">
	var interval;
	window.onload = function() {
		interval = window.setInterval("fun()", 1000); //设置1秒调用一次fun函数
	};

	function fun() {
		var time = document.getElementById("s").innerHTML;

		//判断如果等于0了，不在进行调用fun函数，
		if (time == 0) {
			window.clearInterval(interval);
			window.location.href="<%=basePath %>/frontstage_goodsServlet?action=findAllGoods";
			return;
		}

		document.getElementById("s").innerHTML = (time - 1);
	}
</script>

</body>
</html>