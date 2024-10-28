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
		<link rel="stylesheet" type="text/css" href="../../css/base.css">
		<link rel="stylesheet" type="text/css" href="../../css/iconfont.css">
		<script type="text/javascript" src="../../framework/jquery-1.11.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="../../layui/css/layui.css">
		<script type="text/javascript" src="../../layui/layui.js"></script>
		<!-- 滚动条插件 -->
		<link rel="stylesheet" type="text/css" href="../../css/jquery.mCustomScrollbar.css">
		<script src="../../framework/jquery-ui-1.10.4.min.js"></script>
		<script src="../../framework/jquery.mousewheel.min.js"></script>
		<script src="../../framework/jquery.mCustomScrollbar.min.js"></script>
		<script src="../../framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
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
					<label class="layui-form-label">商品名称</label>
					<div class="layui-input-block">
						<input type="text" id="goodsName" name="goodsName" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">商品图片</label>
					<div class="layui-upload-drag" id="goodsPic">
						<div id="upIcon">
							<i class="layui-icon"></i>-
							<p>点击上传，或将文件拖拽到此处</p>
						</div>
					  <img style="max-width: 400px;max-height:400px;" id="preview">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">商品库存数量</label>
					<div class="layui-input-block">
						<input type="text" id="num" name="num" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">参考价格</label>
					<div class="layui-input-block">
						<input type="text"  id="price" name="price" required lay-verify="required|number" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">商品类别</label>
				    <div class="layui-input-inline" >
				        <select name="type" id="type" lay-filter="type">
				        	<option value="">--请选择商品类别--</option>
				            <option value="phone">手机</option>
							<option value="tablet_PC">平板电脑</option>
							<option value="laptop">笔记本电脑</option>
							<option value="fitting">配件</option>
				        </select>	
				    </div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">颜色</label>
					<div class="layui-input-block">
						<input type="text"  id="color" name="color" value="${goods.color}" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item" id="memory_block" style="display: none">
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
				    </div>
				</div>
				
				
				<div class="layui-form-item">
					<label class="layui-form-label">商品描述</label>
					<div class="layui-input-block">
						<textarea name="description" id="description" class="layui-textarea"></textarea>
					</div>
				</div>
				
			
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" id="commit" onclick="return false">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
			
			
			
			<script>
			
				
			
				layui.use(['upload','layer','form','jquery'], function() {
					var form = layui.form;
					var upload = layui.upload;
					var layer = layui.layer;
					var $ = layui.jquery;
					
					//根据用户选择的添加商品的类别，选择是否出现内存选择框
					form.on('select(type)', function(data){
						if(data.value != 'fitting'){
							 $('#memory_block').css("display","");
						}
					});
				
					
					
					//拖拽上传
					upload.render({
						elem: '#goodsPic',
						url: '<%=basePath %>/backstage_goodsServlet?action=addGoods',
						auto: false,//选择文件后不自动上传
			            bindAction: '#commit',//绑定上传的按钮
			            
			            
			            //上传前的回调，这里获取表单中的其他数据
			            before: function () {
			                this.data = {
			                	goodsName: $('#goodsName').val(),
			                    num: $('#num').val(),
			                    price: $('#price').val(),
			                    type: $('#type').val(),
			                    color: $('#color').val(),
			                    memory: $('#memory').val(),
			                    description: $('#description').val()
			                }
			            },
			            
			          //选择文件后的回调，这里是实现图片的预览效果
						choose: function (obj) {
						    obj.preview(function (index, file, result) {
						        $('#preview').attr('src', result);
								$('#upIcon').hide();
						    })
						},
			            
						//操作成功的回调，res封装着服务器返回的信息
						done: function(res) {
							layer.alert('添加成功！',function(){
								window.location.href="<%=basePath %>backstage_goodsServlet?action=getGoodsList";
							});
						},
						
						
						//上传错误回调
			            error: function (index, upload) {
			                layer.alert('添加失败！' + index);
			            }
					});
					
				});
			</script>

		</div>
	</body>

</html>