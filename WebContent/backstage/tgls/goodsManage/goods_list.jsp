<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<base href="<%= basePath%>">
				
		
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
		<script src="backstage/framework/jquery-ui-1.10.4.min.js"></script>
		<script src="backstage/framework/jquery.mousewheel.min.js"></script>
		<script src="backstage/framework/jquery.mCustomScrollbar.min.js"></script>
		<script src="backstage/framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
		<!-- 公共样式 结束 -->
		
		<style>
			.layui-table img {
			    max-width: none;
			}
		</style>

	</head>

	<body>
		<div class="cBody">
			<div class="console">
				<form class="layui-form" action="">
					<div class="layui-form-item">
						<div class="layui-input-inline">
							<input type="text" name="goodsName" required lay-verify="required" placeholder="输入商品名称" autocomplete="off" class="layui-input">
						</div>
						<button class="layui-btn" lay-submit lay-filter="searchGoods">检索</button>
					</div>
				</form>

				<script>
					layui.use('form', function() {
						var form = layui.form;
						//监听提交
						form.on('submit(searchGoods)', function(data) {
							//异步登录
							$.ajax({
								url:'<%=basePath %>/backstage_goodsServlet?action=getGoodsListByName',
								method:'post',
								data:data.field,
								dataType:'JSON',
								success:function(result){
									if(result.status==200){
										var str="";
										for(var i = 0; i<result.data.length; i++){
											str +="<tr>"
												+"<td>"+result.data[i].name+"</td>"
												+"<td><img src='home/img/"+result.data[i].imgurl +"' width='80' height='80'/></td>"
												+"<td>"+result.data[i].price+"</td>"
												+"<td>"+result.data[i].num+"</td>"
												+"<td>"
											    +  "<div class='layui-input-inline'>"
											    +    "<input type='text' class='layui-input' id='test1' placeholder='"+result.data[i].createDate +"' readonly='readonly'>"
											    +  "</div>"
												+"</td>"
												+"<td>"
												+	"<button class='layui-btn layui-btn-xs' value='"+ result.data[i].id+"'onclick='toUpatePage(this)' >修改</button>"
												+	"<button class='layui-btn layui-btn-xs' onclick='deleteGoods(this)' value='"+ result.data[i].id +"'>删除</button>"
												+"</td>"
											+"</tr>";
										}
									$("#goodLoist").html(str);
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
			
			
			
			<table class="layui-table">
				<thead>
					<tr>
						<th>商品名称</th>
						<th>商品图片</th>
						<th>参考价格</th>
						<th>库存数量</th>
						<th>上架时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id = "goodLoist">
					<c:forEach items="${goodsList}" var="goods">
						<tr>
							<td>${goods.name}</td>
							<td><img src="home/img/${goods.imgurl}" width="80" height="80"/></td>
							<td>${goods.price}</td>
							<td>${goods.num}</td>
							<td>
						      <div class="layui-input-inline">
						        <input type="text" class="layui-input" id="test1" placeholder="${goods.createDate}" readonly="readonly">
						      </div>
								
							</td>
							  
							<td>
								<button class="layui-btn layui-btn-xs" value="${goods.id}" onclick="toUpatePage(this)" >修改</button>
								<button class="layui-btn layui-btn-xs" onclick="deleteGoods(this)" value="${goods.id}">删除</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<!-- layUI 分页模块 -->
			<div id="pages"></div>
			
			<script>
				function toUpatePage(btn){
					var id = btn.value;
					window.location.href="backstage_goodsServlet?action=toGoodsUpdatePage&id="+id;
				}
			
				//删除商品
				function deleteGoods(btn){
					var id = btn.value;
					layer.confirm('确认删除本条记录吗?', { btn: ['是','否'],
						btn1: function(){
			                $.ajax({
	                          url: '<%=basePath %>/backstage_goodsServlet?action=deleteGoods',
	                          type: "POST",
	                          data:{'id':id},
	                          dataType:'JSON',
	                          success:function (result) {
	                        	if(result.status == 200){
	                        		alert("删除成功");                        		
	                        		window.location.href="<%=basePath %>backstage_goodsServlet?action=getGoodsList";
	                        	}else{
	                        		layer.msg(result.msg);
	                        	}
	                          }
			                })
		                },
		                btn2: function(){
		                	layer.close();
		                }
		            })	
				}
			</script>
		</div>
	</body>

</html>