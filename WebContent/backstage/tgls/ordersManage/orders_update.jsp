<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			.layui-form-label{
				width: 100px;
			}
			.layui-input-block{
				margin-left: 130px;
			}
			.layui-form{
				margin-right: 30%;
			}
		</style>

	</head>


	<body>
		<div class="cBody">
			<form id="addForm" class="layui-form" action="">
				<div class="layui-form-item">
					<input id="ordersId" value="${orders.id}" style="display: none">
					<label class="layui-form-label">订单编号</label>
					<div class="layui-input-block">
						<input type="text" id="number" name="number" value="${orders.number}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">下单用户</label>
					<div class="layui-input-block">
						<input type="text" id="user" name="user" value="${orders.user}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">下单时间</label>
					<div class="layui-input-block">
						<input type="text"  id="time" name="time" value="${orders.time}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">收件人</label>
					<div class="layui-input-block">
						<input type="text" id="name" name="name" value="${orders.name}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">性别</label>
				    <div class="layui-input-inline">
				        <select  id="type" name="type" lay-filter="provid">
							<option value="tablet_PC">男</option>
							<option value="laptop">女</option>
				        </select>
				        <input id="sex" style="display:none" value="${orders.sex}" >
				    </div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">收货地址</label>
					<div class="layui-input-block">
						<input type="text" id="address" name="address" value="${orders.address}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">电话号码</label>
					<div class="layui-input-block">
						<input type="text" id="phone" name="phone" value="${orders.phone}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">地址标签</label>
				    <div class="layui-input-block">
						<input type="text" id="address_label" name="address_label" value="${orders.address_label}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">商品编号</label>
					<div class="layui-input-block">
						<input type="text" id="goods_id" name="goods_id" value="${orders.goods_id}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">订单数量</label>
					<div class="layui-input-block">
						<input type="text" id="goods_num" name="goods_num" value="${orders.goods_num}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">订单状态</label>
				    <div class="layui-input-inline">
				        <select  id="type" name="type" lay-filter="provid">
				            <option value="phone">订单下单</option>
							<option value="tablet_PC">订单删除</option>
				        </select>
				        <input id="goods_status" style="display:none" value="${orders.goods_status}" >
				    </div>
				</div>
			</form>	
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn"  onclick="updateOrders()">修改</button>
				</div>
			</div>
			
			<script>
			
				//自动选择下拉选框的值
				$(function() {
					var value1 = $("#typeValue").val();
					$(" select option[value='"+value1+"']").attr("selected","selected");  
				
					var value2 = $("#memoryValue").val();
					$(" select option[value='"+value2+"']").attr("selected","selected"); 
					
				});
				
			
				<%-- layui.use(['upload','layer','form'], function() {
					var form = layui.form;
					var upload = layui.upload;
					var layer = layui.layer;
					var id = $("#ordersId").val();
					
					upload.render({
						elem: '#ordersPic',
						url: '<%=basePath %>/backstage_ordersServlet?action=upOrdersImage&id='+id,
								
						//操作成功的回调
						//done: function(res, index, upload) {
							//$('#preview').attr('src', '/goodsImg/'+res.data);
							//layer.alert('图片修改成功！');
						//},
						
						//上传错误回调
			            error: function (index, upload) {
			                layer.alert('修改失败！请重试');
			            }
					});
				}); --%>
				
				
				function updateOrders() {
					var data = {
		                	id:$("#ordersId").val(),
		                	number:$('#number').val(),
		                	user: $('#user').val(),
		                	time: $('#time').val(),
		                	name: $('#name').val(),			       
		                    sex: $('#sex').val(),
		                    address: $('#address').val(),
		                    phone: $('#phone').val(),
		                    address_label: $('#address_label').val(),
		                    goods_id: $('#goods_id').val(),
		                    goods_num: $('#goods_num').val(),
		                    goods_status: $('#goods_status').val()
		                };
					//console.log(data);
					$.ajax({
						url:'<%=basePath %>/backstage_ordersServlet?action=updateOrders',
						method:'post',
						data:data,
						dataType:'JSON',
						success:function(result){
							if(result.status==200){
								alert("修改成功");
								window.location.href='<%=basePath %>backstage_ordersServlet?action=getOrdersList';
							
							}else{
								layer.open({
									  title: '提示信息',
									  content: result.msg
									});
							}
						}
					});
					return false;
				}
			</script>

		</div>
	</body>
</html>