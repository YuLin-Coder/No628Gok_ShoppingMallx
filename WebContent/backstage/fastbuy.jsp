<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
		<!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
		<meta name="renderer" content="webkit">
		<!--国产浏览器高速模式-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="gok商城" />
		<!-- 作者 -->
		<meta name="revised" content="gok商城.v3, 2019/05/01" />
		<!-- 定义页面的最新版本 -->
		<meta name="description" content="网站简介" />
		<!-- 网站简介 -->
		<meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
		<title>gok商城</title>

		<!-- 公共样式 开始 -->
		<link rel="shortcut icon" href="images/favicon.ico"/>
		<link rel="bookmark" href="images/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/iconfont.css">
		<script type="text/javascript" src="framework/jquery-1.11.3.min.js" ></script>
		<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
	    <!--[if lt IE 9]>
	      	<script src="framework/html5shiv.min.js"></script>
	      	<script src="framework/respond.min.js"></script>
	    <![endif]-->
		<script type="text/javascript" src="layui/layui.js"></script>
		<!-- 滚动条插件 -->
		<link rel="stylesheet" type="text/css" href="css/jquery.mCustomScrollbar.css">
		<script src="framework/jquery-ui-1.10.4.min.js"></script>
		<script src="framework/jquery.mousewheel.min.js"></script>
		<script src="framework/jquery.mCustomScrollbar.min.js"></script>
		<script src="framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
		<!-- 公共样式 结束 -->

		<link rel="stylesheet" type="text/css" href="css/login.css">
		<script type="text/javascript" src="js/login.js"></script> 
	</head>

	<body>
		
		<!--主体 开始-->
		<div class="login_main">
			<!--轮播图 开始-->
			<div class="layui-carousel lbt" id="loginLbt">
				<div carousel-item>
					<div class="item" style="background: url(images/login_bg1.jpg) no-repeat; background-size: cover;"></div>
					<div class="item" style="background: url(images/login_bg2.jpg) no-repeat; background-size: cover;"></div>
				</div>
			</div>
			<!--轮播图 结束-->
			<div class="logo">
				<img src="images/login_logo.png" />
				<div>
					<h1>gok商城</h1>
					<p>版本号:19.3.01</p>
				</div>
			</div>
			<div class="form_tzgg">
				<div class="form">
					<form action="" method="post" class="layui-form">
						<div class="title" style="text-align:center; font-size:24px;">订单确认</div>
						<div class="con" onclick="getFocus(this)">
							<input type="text" name="recipients" lay-verify="recipients" placeholder="收件人" autocomplete="off" class="layui-input">
						</div>
						<div class="con" onclick="getFocus(this)">
							<input type="text" name="address" lay-verify="address" placeholder="收货地址" autocomplete="off" class="layui-input">
						</div>
						<div class="con" onclick="getFocus(this)">
							<input type="text" name="phone" lay-verify="phone" placeholder="电话号码" autocomplete="off" class="layui-input">
						</div>
						<div class="con" onclick="getFocus(this)">
							<input type="text" name="sex" lay-verify="sex" placeholder="性别" autocomplete="off" class="layui-input">
						</div>
						<div class="con" onclick="getFocus(this)">
							<input type="text" name="addressLabel" lay-verify="addressLabel" placeholder="地址标签" autocomplete="off" class="layui-input">
						</div>
						<input type="hidden" name="id" id="good_id">
						<input type="hidden" name="num" id="num">
						<div class="but">
							<button class="layui-btn" lay-submit lay-filter="confirm">确认</button>
						</div>
					</form>
				</div>
	<script>
		function GetQueryString(name){
		     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		     var r = window.location.search.substr(1).match(reg);
		     if(r!=null)return  unescape(r[2]); return null;
		}
		$('#num').val(GetQueryString("num"));
		$('#good_id').val(GetQueryString("id"));
		layui.use('form', function() {
			var form = layui.form;
			
			form.verify({
				recipients: function(value, item){ //value：表单的值、item：表单的DOM对象
	                if(value == null || value == ""){
	                    return '请输入收件人姓名！';
	                }
	            },
	            address: function(value, item){ 
	                if(value == null || value == ""){
	                    return '请输入收货地址！';
	                }
	            },
	            phone: function(value, item){ 
	                if(value == null || value == ""){
	                    return '请输入电话号码！';
	                }
	            }
	        });
				
				
			//监听提交
			form.on('submit(confirm)', function(data) {
				//异步登录
				$.ajax({
					url:'<%=basePath %>/backstage_ordersServlet?action=fastbuy',
					method:'post',
					data:data.field,
					dataType:'JSON',
					success:function(result){
						if(result.status==200){
							layer.alert("订单提交成功！");
							window.location.href="<%=basePath %>/frontstage_goodsServlet?action=findAllGoods";
						}else{
							layer.open({
								  title: '提示信息',
								  content: result.msg
								});
						}
					}
				});
				return false;
			});
		});
	</script>
			</div>
		</div>  
	</body>

</html>