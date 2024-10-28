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
		<link rel="stylesheet" type="text/css" href="backstage/css/base.css">
		<link rel="stylesheet" type="text/css" href="backstage/css/iconfont.css">
		<script type="text/javascript" src="backstage/framework/jquery-1.11.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="backstage/layui/css/layui.css">
		<script type="text/javascript" src="backstage/layui/layui.js"></script>
		<!-- 滚动条插件 -->
		 <link rel="stylesheet" type="text/css" href="backstage/css/jquery.mCustomScrollbar.css">
		<!--  <script src="backstage/framework/jquery.mousewheel.min.js"></script> -->
		<!-- <script src="backstage/framework/jquery.mCustomScrollbar.min.js"></script> --> 
		<!-- <script src="backstage/framework/cframe.js"></script>  -->
		<!-- 公共样式 结束 -->

	</head>

	<body>
		<div class="cBody">
			<form id="addForm" class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label">原始密码</label>
					<div class="layui-input-inline shortInput">
						<input type="password" id="oldpassword" name="oldpassword" onblur="checkOldPassword()" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">新密码</label>
					<div class="layui-input-inline shortInput">
						<input type="password" id="password1" name="password" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">确认新密码</label>
					<div class="layui-input-inline shortInput">
						<input type="password" id="password2" name="password2" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
			</form>
			
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" onclick="updatePassword()">确认修改</button>
				</div>
			</div>
			
			<script>
				var flag = false;
				function checkOldPassword() {
					var password = $("#oldpassword").val();
					console.log("旧密码"+password);
					$.ajax({
						url:'<%=basePath %>/frontstage_userServlet?action=checkOldPassword',
						method:'post',
						data:{'password':password},
						dataType:'JSON',
						success:function(result){
							if(result.status==200){
								flag = true;
							}else{
								/* layer.open({
									  title: '提示信息',
									  content: result.msg
									}); */
								alert(result.msg);
							}
						}
					});
				}
			
				function updatePassword() {
					if(!flag){
						/* layer.open({
							  title: '提示信息',
							  content: '原始密码错误，请重新输入'
							}); */
						alert("原始密码错误，请重新输入");
					}else{
						var newPassword1 = $("#password1").val();
						var newPassword2 = $("#password2").val();
						console.log("新密码："+newPassword1);
						console.log("新密码验证："+newPassword2);
						if(newPassword1 != newPassword2){
							/* layer.open({
								  title: '提示信息',
								  content: '两次密码不一致，请重新输入'
								}); */
								alert("两次密码不一致，请重新输入");
						}else{
							$.ajax({
								url:'<%=basePath %>/frontstage_userServlet?action=modifyPassword',
								method:'post',
								data:{'newPassword':newPassword2},
								dataType:'JSON',
								success:function(result){
									if(result.status==200){
										alert("修改成功");
										window.location.href="<%=basePath %>frontstage_userServlet?action=logout"; 
									}else{
										/* layer.open({
											  title: '提示信息',
											  content: result.msg
											}); */
										alert(result.msg);
									}
								}
							});
						}
					}
				}
			</script>

		</div>
	</body>
</html>