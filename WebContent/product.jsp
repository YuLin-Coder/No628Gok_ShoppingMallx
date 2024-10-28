<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>product</title>
<link rel="stylesheet" type="text/css" href="home/CSS/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="home/CSS/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="home/CSS/main.css">
<script type="text/javascript">

function phone(obj) {
	location.href="<%= basePath%>/frontstage_goodsServlet?action=findGoodsByType&type="+encodeURIComponent(encodeURIComponent(obj));
}
function change() {
	document.getElementById("cimg").src = "${pageContext.request.contextPath}/checkImg?time="
			+ new Date().getTime();
}
function findProductById(id){
	location.href="<%= basePath%>/frontstage_goodsServlet?action=findGoodsById&id="+id;
}

function tocart(m) {
	location.href="<%= basePath%>/frontstage_goodsServlet?action=carChart&name="+m;

}
</script>
</head>
<body>
<div id="preloader" >
    <div id="status" ></div>
</div>
<header>
    <nav class="navbar navbar-inverse" role="navigation">
     <div class="logo"></div>
        <a href="frontstage_goodsServlet?action=findAllGoods">首页</a>
        <a href="javascript:void(0)" onclick="phone('phone')">手机</a>
        <a href="javascript:void(0)" onclick="phone('tablet_PC')">平板</a>
        <a href="javascript:void(0)" onclick="phone('laptop')">1笔记本</a>
        <a href="javascript:void(0)" onclick="phone('fitting')">配件</a>
        <span class="slider-bar"></span>
       	<c:if test="${not empty user }">
        	<i class="carts" onclick="tocart(${user.userName})"></i>
		</c:if>
        <span>
        	<c:if test="${not empty user }">
        			 <h4 class="user">${user.userName}</h4> |
					 <a  href="<%= basePath%>/frontstage_userServlet?action=logout">注销</a>
			</c:if>
			<c:if test="${ empty user }">
        		<h4 class="signin" data-toggle="modal" data-target="#log-wrapper">登录</h4>
            	<h4 class="signup" data-toggle="modal" data-target="#log-wrapper">注册</h4>
            </c:if>
		</span>
    </nav>
</header>

<!--登录注册-->
<div id="log-wrapper" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-content modal-dialog" id="log-move">
        <canvas id="myCanvas"></canvas>
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
        <div id="log">
            <div id="navs-slider">
                <a id="#signin" class="active">登录</a>
                <a id="#signup">注册</a>
                <span class="navs-slider-bar"></span>
            </div>
            
            <!-- 登录 -->
            <div id="sign-form-1">
                <div class="group-inputs">
                    <div class="username input-wrapper" >
                        <input aria-label="用户名" placeholder="用户名" required="required" type="text" value=""
                               name="userName" id="signup-email_adress" >
                    </div>
                    <div class="input-wrapper password">
                        <input required="required" type="password" id="password-1" name="password" aria-label="密码"
                               placeholder="密码">
                        <span id="password_message-1"></span>
                    </div>
                    <div class="captcha input-wrapper" data-type="en">
                        <input id="captcha" name="captcha" placeholder="验证码" required="required" data-rule-required="true"
                               data-msg-required="请填写验证码">
                        <img class="captcha-img" data-toggle="tooltip" data-placement="top" title="看不清楚？换一张"
                             alt="验证码" src="<%=basePath %>/checkImg" id="cimg" onclick="change()">
                    </div>
                </div>
                 <div class="sign-btn">
                    <button class="sign-button submit" onclick="login()"">登录</button>
                </div>
            </div>
            
            
            <!-- 注册 -->
            <div id="sign-form-2">
                <div class="group-inputs">
                    <div class="username input-wrapper">
                        <input aria-label="用户名" id="r_userName" placeholder="用户名（由4~16位字母数字下划线组成）" required="required" type="text" value=""
                               name="username"  onblur="checkUserName()">
                        <span id="username_message"></span>
                    </div>
                    <div class="input-wrapper password">
                        <input required="required" type="password" id="r_password" name="password" aria-label="密码"
                               placeholder="密码（必须是包含大小写字母和数字，不能使用特殊字符，长度在4-10之间）">
                        <span id="password_message"></span>
                    </div>
                    <div class="input-wrapper password">
                        <input required="required" type="password" id="r_repassword" name="repassword" aria-label="重复密码"
                               placeholder="重复密码">
                        <span id="repassword_message"></span>
                    </div>
                    <div class="email input-wrapper">
                        <input aria-label="邮箱"  placeholder="邮箱" required="" type="text" value=""
                               name="email" id="r_email_adress">
                        <span id="email_message"></span>
                    </div>
                    
                    <div class="phone input-wrapper" style="border-top: 1px solid #d5d5d5">
                        <input aria-label="手机号" placeholder="手机号" required="" type="text" value=""
                               name="phone" id="r_phoneNum">
                        <span id="phoneNum_message"></span>
                    </div>
                </div>
                <div class="sign-btn">
                    <button class="sign-button submit" onclick="register()">注册</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--登录注册END-->

<section class="page">
    <aside id="aside" class="panel-group aside-menu">
        <h3 class="type"> 
        	<c:if test="${type==phone}">手机</c:if>
        	<c:if test="${type==tablet_PC}">平板电脑</c:if>
        	<c:if test="${type==laptop}">笔记本</c:if>
        	<c:if test="${type==fitting}">配件</c:if>
        </h3>
    </aside>

    <div class="content">
        <c:forEach items="${pageBean.goods}" var="p" varStatus="vs">
				<div class="product">
					<img src="home/img/${p.imgurl}" onclick="findProductById('${p.id}')">
        			<span class="brand">${p.name}</span>
        			<span class="title">${p.description}</span>
        			<span class="price">${p.price}</span>
        			<a href="<%= basePath%>/frontstage_goodsServlet?action=findGoodsById&id=${p.id}"><em class="fast-buy"></em></a>
				</div>
		</c:forEach>
    </div>
    
    <ul class="pagination">
        <li><a href="<%= basePath%>/frontstage_goodsServlet?action=findGoodsByType&currentPage=1&type=${type}">首页</a></li>
		<li>
			<c:if test="${pageBean.currentPage==1}"><a>上一页</a></c:if>
		</li>
		<li>
			<c:if test="${pageBean.currentPage!=1}"><a href="<%= basePath%>/frontstage_goodsServlet?action=findGoodsByType&currentPage=${pageBean.currentPage-1}&type=${type}">上一页</a></c:if>
		</li>
			<c:if test="${pageBean.currentPage==pageBean.totalPage}"><li><a>下一页</a></li><li><a>尾页</a></li></c:if>
		<li>
			<c:if test="${ppageBean.currentPage!=pageBean.totalPage}">
				<a href="<%= basePath%>/frontstage_goodsServlet?action=findGoodsByType&currentPage=${pageBean.currentPage+1 }&type=${type}">下一页</a>
				<a href="<%= basePath%>/frontstage_goodsServlet?action=findGoodsByType&currentPage=${pageBean.totalPage}&type=${type}">尾页</a>
			</c:if>
		</li>
    </ul> 
    
    
</section>





<aside class="aside-tool">
    <ul>
        <li class="customer">
            <a href="http://wpa.qq.com/msgrd?v=3&uin=476759153&site=qq&menu=yes" target=_blank
               clickid=guanwang_navigation_customer>联系客服</a>
        </li>
        <li class="top"></li>
    </ul>
</aside>
<script type="text/javascript" src="home/JS/jquery.min.js"></script>
<script type="text/javascript" src="home/JS/jquery-ui.js"></script>
<script type="text/javascript" src="home/JS/bootstrap.min.js"></script>
<script type="text/javascript" src="home/JS/bg-canvas.js"></script>
<script type="text/javascript" src="home/JS/main.js"></script>
<script type="text/javascript" src="CategoryJS/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="CategoryJS/onloada.js"></script>
<script type="text/javascript">
			$(function () { $("[data-toggle='tooltip']").tooltip(); });
	
			//登录
			function login() {	
				var data={"userName":$("#signup-email_adress").val(),"password":$("#password-1").val(),"captcha":$("#captcha").val()};
				
				$.ajax({
					url:'<%=basePath %>/frontstage_userServlet?action=login',
					method:'post',
					data:data,
					dataType:'JSON',
					success:function(result){
					
						if(result.status==200){
							alert("登录成功");
							window.location.href="<%=basePath %>/frontstage_goodsServlet?action=findAllGoods";
						}else{
							alert(result.msg);
						}
					}
				});
			}
			
			
			
			var isRepeatUserName = true;
			
			
			//注册时检查重名
			function checkUserName(){
				$.ajax({
					url:'<%=basePath %>/frontstage_userServlet?action=checkUserNameRepeat',
					method:'post',
					data:{'userName':$("#r_userName").val()},
					dataType:'JSON',
					success:function(result){
						if(result.status==200){
							isRepeatUserName = false;
						}else{
							alert(result.msg);
						}
					}
				});
			}
			
			
			//注册
			function register() {
				var userName = $("#r_userName").val();
				var u_flag = false; //判断此用户名是否能用的标记位
				
				var password = $("#r_password").val();
				var p_flag = false;
				
				var repassword = $("#r_repassword").val();
				
				var email = $("#r_email_adress").val();
				var phoneNum = $("#r_phoneNum").val();
			
				if(userName == null){
					alert("用户名不能为空");
				}else{
					var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;
					
					if(uPattern.test(userName)){
						u_flag = true;
					}else{
						alert("用户名格式不正确，请重新输入");
					}
				}
				
				
				if(u_flag){
					if(password == null){
						alert("密码不能为空");
					}else{
						var p_pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,10}$/;
						
						if(p_pattern.test(password)){
							if(password == repassword){
								p_flag = true;
							}else{
								alert("两次密码不一致，请重新输入");
							}	
						}else{
							alert("密码格式不正确，请重新输入");
						}
						
					}
				}
				
				
				
				var data={'userName':userName,'password':password,'email':email,'phoneNum':phoneNum};
				$.ajax({
					url:'<%=basePath %>/frontstage_userServlet?action=register',
					method:'post',
					data:data,
					dataType:'JSON',
					success:function(result){
						if(result.status==200){
							window.location.href="<%=basePath %>/regist_success.jsp";
						}else{
							alert(result.msg);
						}
					}
				});				
			}
</script>

</body>
</html>