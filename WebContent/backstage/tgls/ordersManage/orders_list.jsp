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
							<input type="text" name="OrdersUser" required lay-verify="required" placeholder="输入下单人名字" autocomplete="off" class="layui-input">
						</div>
						<button class="layui-btn" lay-submit lay-filter="searchOrders">检索</button>
					</div>
				</form>

				<script>
					layui.use('form', function() {
						var form = layui.form;
						//监听提交
						form.on('submit(searchOrders)', function(data) {
							//异步登录
							$.ajax({
								url:'<%=basePath %>/backstage_ordersServlet?action=getOrdersListByName',
								method:'post',
								data:data.field,
								dataType:'JSON',
								success:function(result){
									if(result.status==200){
										var str="";
										for(var i = 0; i<result.data.length; i++){
											str +="<tr>"
												+"<td>"+result.data[i].id+"</td>"
												+"<td>"+result.data[i].number+"</td>"
												+"<td>"+result.data[i].user+"</td>"
												+"<td>"+result.data[i].time+"</td>"
												+"<td>"+result.data[i].name+"</td>"
												+"<td>"+result.data[i].sex+"</td>"
												+"<td>"+result.data[i].address+"</td>"
												+"<td>"+result.data[i].phone+"</td>"
												+"<td>"+result.data[i].address_label+"</td>"
												+"<td>"+result.data[i].goods_id+"</td>"
												+"<td>"+result.data[i].goods_num+"</td>"
												+"<td>"+result.data[i].goods_status+"</td>"
												+"<td>"
												+	"<button class='layui-btn layui-btn-xs' value='"+ result.data[i].id+"'onclick='toUpatePage(this)' >修改</button>"
												+	"<button class='layui-btn layui-btn-xs' onclick='deleteOrders(this)' value='"+ result.data[i].id +"'>删除</button>"
												+"</td>"
											+"</tr>";
										}
									$("#orderLoist").html(str);
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
						<th>订单号</th>
						<th>订单编码</th>
						<th>下单用户</th>
						<th>下单时间</th>
						<th>收件人</th>
						<th>性别</th>
						<th>收货地址</th>
						<th>电话号码</th>
						<th>地址标签</th>
						<th>商品编号</th>
						<th>订单数量</th>
						<th>订单状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id = "orderLoist">
					<c:forEach items="${ordersList}" var="orders">
						<tr>
							<td>${orders.id}</td>
							<td>${orders.number}</td>
							<td>${orders.user}</td>
							<td>${orders.time}</td>
							<td>${orders.name}</td>
							<td>${orders.sex}</td>
							<td>${orders.address}</td>
							<td>${orders.phone}</td>
							<td>${orders.address_label}</td>
							<td>${orders.goods_id}</td>
							<td>${orders.goods_num}</td>
							<td>${orders.goods_status}</td>
							<td>
								<button class="layui-btn layui-btn-xs" value="${orders.id}" onclick="toUpatePage(this)" style="float:left; display:block">修改</button>
								<button class="layui-btn layui-btn-xs" onclick="deleteOrders(this)" value="${orders.id}" style="display:block;margin-left: 50px;">删除</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<!-- layUI 分页模块 -->
			<div id="pages">
				<button class="layui-btn layui-btn-md" onclick="deleteAllOrders()" style="display:block; margin:0 auto;">批量删除</button>
			</div>
			
			
			<script>
				function toUpatePage(btn){
					var id = btn.value;
					window.location.href="backstage_ordersServlet?action=toOrdersUpdatePage&id="+id;
				}
			
				//删除订单
				function deleteOrders(btn){
					var id = btn.value;
					layer.confirm('确认删除本条记录吗?', { btn: ['是','否'],
						btn1: function(){
			                $.ajax({
	                          url: '<%=basePath %>/backstage_ordersServlet?action=deleteOrders',
	                          type: "POST",
	                          data:{'id':id},
	                          dataType:'JSON',
	                          success:function (result) {
	                        	if(result.status == 200){
	                        		alert("删除成功");                        		
	                        		window.location.href="<%=basePath %>backstage_ordersServlet?action=getOrdersList";
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
				
				//删除全部订单
				function deleteAllOrders(){
					layer.confirm('确认删除全部记录吗?', { btn: ['是','否'],
						btn1: function(){
			                $.ajax({
	                          url: '<%=basePath %>/backstage_ordersServlet?action=deleteAllOrders',
	                          type: "POST",
	                          data:{'id':1},
	                          dataType:'JSON',
	                          success:function (result) {
	                        	if(result.status == 200){
	                        		alert("删除成功");                        		
	                        		window.location.href="<%=basePath %>backstage_ordersServlet?action=getOrdersList";
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