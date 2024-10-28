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
					<input id="goodsId" value="${goods.id}" style="display: none">
					<label class="layui-form-label">商品名称</label>
					<div class="layui-input-block">
						<input type="text" id="goodsName" name="goodsName" value="${goods.name}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">商品图片</label>
					<div class="layui-upload" id="goodsPic">
						<div class="layui-upload-list">
						  <img src="home/img/${goods.imgurl}" style="max-width: 250px;max-height:250px;" id="preview">
						</div>
						<input value ="false" id="isUpFile" style="display: none">
						<button type="button" style="margin-left: 150px" class="layui-btn">修改图片</button>
					</div>  
				</div>
				

				
				<div class="layui-form-item">
					<label class="layui-form-label">商品库存数量</label>
					<div class="layui-input-block">
						<input type="text" id="num" name="num" value="${goods.num}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">参考价格</label>
					<div class="layui-input-block">
						<input type="text"  id="price" name="price" value="${goods.price}" required lay-verify="required|number" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">商品类别</label>
				    <div class="layui-input-inline">
				        <select  id="type" name="type" lay-filter="provid">
				            <option value="phone">手机</option>
							<option value="tablet_PC">平板电脑</option>
							<option value="laptop">笔记本电脑</option>
							<option value="fitting">配件</option>
				        </select>
				        <input id="typeValue" style="display:none" value="${goods.type}" >
				    </div>
				</div>
				
				
				<div class="layui-form-item">
					<label class="layui-form-label">颜色</label>
					<div class="layui-input-block">
						<input type="text"  id="color" name="color" value="${goods.color}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
					
				<c:if test="${!(empty goods.memory)}">
					<div class="layui-form-item" id="memory_block">
						<label class="layui-form-label">内存大小</label>
					    <div class="layui-input-inline">
					        <select name="memory" id="memory" lay-filter="provid">
					        	<option value="">--请选择内存大小--</option>
					            <option value="16G">16G</option>
								<option value="32G">32G</option>
								<option value="64G">64G</option>
								<option value="128G">128G</option>
								<option value="256G">128G</option>
								<option value="512G">128G</option>
								<option value="1T">1T</option>
					        </select>
					        <input id="memoryValue" style="display:none" value="${goods.memory}" >
					    </div>
					</div>
				</c:if>
				
			
				
				
				<div class="layui-form-item">
					<label class="layui-form-label">商品描述</label>
					<div class="layui-input-block">
						<textarea name="description"  id="description" class="layui-textarea"> ${goods.description}</textarea>
					</div>
				</div>
			</form>
			
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn"  onclick="updateGoods()">修改</button>
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
				
			
				layui.use(['upload','layer','form'], function() {
					var form = layui.form;
					var upload = layui.upload;
					var layer = layui.layer;
					var id = $("#goodsId").val();
					
					upload.render({
						elem: '#goodsPic',
						url: '<%=basePath %>/backstage_goodsServlet?action=upGoodsImage&id='+id,
								
						//操作成功的回调
						done: function(res, index, upload) {
							$('#preview').attr('src', 'home/img/'+res.data);
							layer.alert('图片修改成功！');
						},
						
						//上传错误回调
			            error: function (index, upload) {
			                layer.alert('修改失败！请重试');
			            }
					});
				});
				
				
				function updateGoods() {
					var data = {
		                	id:$("#goodsId").val(),
		                	goodsName: $('#goodsName').val(),
		                    num: $('#num').val(),
		                    price: $('#price').val(),			       
		                    type: $('#type').val(),
		                    color: $('#color').val(),
		                    memory: $('#memory').val(),
		                    description: $('#description').val()
		                };
					
					$.ajax({
						url:'<%=basePath %>/backstage_goodsServlet?action=updateGoods',
						method:'post',
						data:data,
						dataType:'JSON',
						success:function(result){
							if(result.status==200){
								alert("修改成功");
								window.location.href="<%=basePath %>backstage_goodsServlet?action=getGoodsList";
							
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