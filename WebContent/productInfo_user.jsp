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
<title>单个商品详细信息</title>
<link rel="stylesheet" type="text/css" href="home/CSS/main.css">
<script type="text/javascript" src="home/JS/jquery.min.js"></script>
<script type="text/javascript" src="home/JS/jquery-ui.js"></script>
<script type="text/javascript" src="home/JS/bootstrap.min.js"></script>
<script type="text/javascript" src="home/JS/main.js"></script>
	<!-- <script type="text/javascript" src="home/JS/bg-canvas.js"></script> -->
</head>
<body>

<section class="w">
<div class="product-img">
    <div class="view">
        <img src="home/img/${pro.imgurl}">
    </div>
</div>
<div class="product-details">
    <h1>${pro.description}</h1>
    <p class="re"><span>产品编号：</span><span>RE201612101843132</span></p>
    <p class="price" data-price="3649">
        <span>价格</span>
        <span class="price">${pro.price }</span>
        <span id="goods_id" style="display:none">${pro.id }</span>
    </p>
    
    <ul class="details">
        <li><span>颜色</span><a class="u-check n-check">${pro.color }</a></li>
     
        <c:if test="${not empty pro.memory}">
         	<li><span>内存</span><a class="u-check n-check">${pro.memory }</a></li>
        </c:if>
        
        <li><span>销售地区</span><a class="u-check n-check">中国</a></li>
        <li><span>购买数量：</span>
            <div class="count-box">
                    <input class="min" name="" type="button" value="-"/>
                    <input class="text-box" name="num" type="text" value="1" id="num"/>
                    <input class="add" name="" type="button" value="+"/>
                </div>
        </li>
    </ul>
    <div class="action">
        <a class="buy"	onclick="send()" >立即购买</a>
       	<a class="addCar" onclick="addProductToCart('${pro.id}')"><i></i>加入购物车</a>
    </div>
</div>
</section>

<script type="text/javascript">
	function addProductToCart(id){
		alert("添加成功");
		location.href="${pageContext.request.contextPath}/frontstage_goodsServlet?action=addCar&id="+id;
 	}
	
	function send(){
		var json = {id:"",num:""};
		json.id = $('#goods_id').html();
		json.num = $('#num').val(); 		
		window.location.href="http://localhost:8080/Gok_ShoppingMallx//backstage/fastbuy.jsp?id="+ json.id+"&num="+ json.num; 
	}
	
</script>
</body>
</html>
