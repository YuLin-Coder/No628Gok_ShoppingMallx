<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html>
<html>
<head>
<base href="<%= basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="home/CSS/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="home/CSS/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="home/CSS/main.css">
<title>Insert title here</title>
</head>
<body>

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
                    
                   <%--  <div class="captcha input-wrapper" data-type="en">
                        <input id="captcha-1" name="captcha" placeholder="验证码" required="" data-rule-required="true"
                               data-msg-required="请填写验证码">
                        <img class="captcha-img" data-toggle="tooltip" data-placement="top" title="看不清楚？换一张"
                             alt="验证码" src="<%=basePath %>/checkImg" id="cimg" onclick="change()">
                        <span id="checkcode_message"></span>
                    </div> --%>

                </div>
                <div class="sign-btn">
                    <button class="sign-button submit" onclick="register()"">注册</button>
                </div>
            </div>


<script type="text/javascript" src="home/JS/jquery.min.js"></script>
<script type="text/javascript" src="home/JS/jquery-ui.js"></script>
<script type="text/javascript" src="home/JS/bootstrap.min.js"></script>
<script type="text/javascript" src="home/JS/bg-canvas.js"></script>
<script type="text/javascript" src="home/JS/main.js"></script>
</body>
</html>