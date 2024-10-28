package cn.gok.backstageManagement.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;


import cn.gok.backstageManagement.entity.Orders;
import cn.gok.backstageManagement.service.GoodsService;
import cn.gok.backstageManagement.service.OrdersService;
import cn.gok.frontstageManagement.entity.User;
import cn.gok.util.FileUploadUtil;
import cn.gok.util.JSONResult;
import cn.gok.util.JsonUtil;

@WebServlet("/backstage_ordersServlet")
public class OrdersServlet extends HttpServlet{
	
	OrdersService service = new OrdersService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println(action);
		switch(action) {
			case "getOrdersList":getOrdersList(req,resp);break;
			case "getOrdersListByName":getOrdersListByName(req,resp);break;
			case "deleteOrders":deleteOrders(req,resp);break;
			case "deleteAllOrders":deleteAllOrders(req,resp);break;
			case "toOrdersUpdatePage":toOrdersUpdatePage(req,resp);break;
			case "updateOrders":updateOrders(req,resp);break;
			case "fastbuy":sendOrder(req,resp);break;
		}
	}

	/* 
	 * 删除全部订单 
	 */
	private void deleteAllOrders(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置请求编码格式:
				req.setCharacterEncoding("utf-8");
				//设置响应编码格式			
				resp.setContentType("text/html;charset=utf-8");
		// TODO 自动生成的方法存根
		int d = service.deleteAllOrders();
		try {
			if(d>0) {
				JSONResult ok = JSONResult.ok();
				resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
			}else {
				JSONResult errorMsg = JSONResult.errorMsg("删除失败，请重试");
				resp.getWriter().println(JsonUtil.javaObjectToJson(errorMsg));
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提交订单
	 * @throws IOException 
	 */
	public void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置请求编码格式:
				req.setCharacterEncoding("utf-8");
				//设置响应编码格式			
				resp.setContentType("text/html;charset=utf-8");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			User user =(User) req.getSession().getAttribute("user");
			Orders order = new Orders();
			order.setNumber(order.getRandomString(10));
			order.setTime(df.format(new Date()));
			order.setName(req.getParameter("recipients"));
			order.setAddress(req.getParameter("address"));
			order.setPhone(req.getParameter("phone"));
			order.setAddress_label(req.getParameter("addressLabel"));
			order.setSex(req.getParameter("sex"));
			order.setUser(user.getUserName());
			order.setGoods_id(Integer.parseInt(req.getParameter("id")));
			order.setGoods_num(Integer.parseInt(req.getParameter("num")));
			order.setGoods_status(1);
			int d = service.addOrder(order);
				try {
					if(d>0) {
						JSONResult ok = JSONResult.ok();
						resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
					}else {
						JSONResult error = JSONResult.errorMsg("订单提交失败!");
						resp.getWriter().println(JsonUtil.javaObjectToJson(error));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}	
		
	}

	
	/**
	 * 向前端页面返回订单数据列表
	 */
	public void getOrdersList(HttpServletRequest req,HttpServletResponse resp) {
		List<Orders> allOrders = service.getAllOrders();
		req.setAttribute("ordersList",allOrders);
		try {
			req.getRequestDispatcher("/backstage/tgls/ordersManage/orders_list.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * 根据订单名查询商品(注意！类别没改)
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	public void getOrdersListByName(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		//设置请求编码格式:
				req.setCharacterEncoding("utf-8");
				//设置响应编码格式			
				resp.setContentType("text/html;charset=utf-8");
		String name = req.getParameter("OrdersUser");
		//String type = req.getParameter("type");
		
		Map<String,String> parmas = new HashMap<>();
		parmas.put("name", name);
		//parmas.put("type", type);
		
		List<Orders> list = service.getOrdersByName(parmas);
	
		try {
			if(list != null) {
				JSONResult ok = JSONResult.ok(list);
				resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
			}else {
				JSONResult errorMsg = JSONResult.errorMsg("未获取到任何数据，请重试");
				resp.getWriter().println(JsonUtil.javaObjectToJson(errorMsg));
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改订单时，获取此订单的全部数据，并返回至修改页面
	 */
	public void toOrdersUpdatePage(HttpServletRequest req,HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Orders orders = service.getOrdersInfoById(id);
		req.setAttribute("orders", orders);
		try {
			req.getRequestDispatcher("/backstage/tgls/ordersManage/orders_update.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	/**
	 *  修改订单
	 * @throws IOException 
	 */
	
	public void updateOrders(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		//设置请求编码格式:
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式			
		resp.setContentType("text/html;charset=utf-8");
		Orders orders = new Orders();
		orders.setId(Integer.parseInt(req.getParameter("id")));
		orders.setNumber(req.getParameter("number"));
		orders.setUser(req.getParameter("user"));
		orders.setTime(req.getParameter("time"));
		orders.setName(req.getParameter("name"));
		orders.setSex(req.getParameter("sex"));
		orders.setAddress(req.getParameter("address"));
		orders.setPhone(req.getParameter("phone"));
		orders.setAddress_label(req.getParameter("address_label"));
		orders.setGoods_id(Integer.parseInt(req.getParameter("goods_id")));
		orders.setGoods_num(Integer.parseInt(req.getParameter("goods_num")));
		orders.setGoods_status(Integer.parseInt(req.getParameter("goods_status")));
		
		System.out.println(orders);
		int d = service.updateOrdersById(orders);
		System.out.println(d);
		try {
			if(d>0) {
				
				JSONResult ok = JSONResult.ok();
				resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
			}else {
				JSONResult errorMsg = JSONResult.errorMsg("修改失败，请重试");
				resp.getWriter().println(JsonUtil.javaObjectToJson(errorMsg));
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * 删除订单
	 * @throws UnsupportedEncodingException 
	 */
	public void deleteOrders(HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException {
		//设置请求编码格式:
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式			
		resp.setContentType("text/html;charset=utf-8");
		int id = Integer.parseInt(req.getParameter("id"));
		
		int d = service.deleteOrdersById(id);
		try {
			if(d>0) {
				JSONResult ok = JSONResult.ok();
				resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
			}else {
				JSONResult errorMsg = JSONResult.errorMsg("删除失败，请重试");
				resp.getWriter().println(JsonUtil.javaObjectToJson(errorMsg));
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
