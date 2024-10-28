package cn.gok.frontstageManagement.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gok.frontstageManagement.entity.Goods;
import cn.gok.frontstageManagement.entity.PageBean;
import cn.gok.frontstageManagement.entity.User;
import cn.gok.frontstageManagement.service.GoodsService;
import cn.gok.util.JsonUtil;

@WebServlet("/frontstage_goodsServlet")
public class GoodsServlet extends HttpServlet{
	GoodsService service = new GoodsService();
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		switch(action) {
			case "findAllGoods":findAllGoods(req,resp);break;
			case "findGoodsById":findGoodsById(req,resp);break;
			case "findGoodsByType":findGoodsByType(req,resp);break;
			case "carChart":carChart(req,resp);break;
			case "carChartRe":carChartRe(req,resp);break;
			case "addCar":addCar(req,resp);break;
		}
	}
	
	/**
	 * 获取所有商品列表
	 * @param req
	 * @param resp
	 */
	public void carChartRe(HttpServletRequest req, HttpServletResponse resp) {
		User user = (User) req.getSession().getAttribute("user");
		int id = user.getId();
		String idd = req.getParameter("idd");
		service.deleteCartGoods(id+"", idd);
		Map<String, List<Goods>> allGoods = service.getCartGoods(id+"");
		try {
			req.setAttribute("cart", allGoods);
			req.getRequestDispatcher("/showcart.jsp").forward(req, resp);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取所有商品列表
	 * @param req
	 * @param resp
	 */
	public void carChart(HttpServletRequest req, HttpServletResponse resp) {
		User user = (User) req.getSession().getAttribute("user");
		int id =232323;
		if(user!=null) {
		 id = user.getId();
		}
		
		System.out.println(id+"dddddddddddddddd");
		Map<String, List<Goods>> allGoods = service.getCartGoods(id+"");
		try {
			req.setAttribute("cart", allGoods);
			req.getRequestDispatcher("/showcart.jsp").forward(req, resp);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取所有商品列表
	 * @param req
	 * @param resp
	 */
	public void findAllGoods(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, List<Goods>> allGoods = service.getAllGoods();
		
		try {
			req.setAttribute("allGoods", allGoods);
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *根据类型获得商品列表 
	 * @param req
	 * @param resp
	 */
	public void findGoodsByType(HttpServletRequest req, HttpServletResponse resp) {
		String type = req.getParameter("type");
		String currentPage = req.getParameter("currentPage");
		
		PageBean pageBean = null;
		
		// 如果当前第几页currentPage 值为null，说明第一次跳转到此页面或者是要跳转到首页，则设定该值currentPage默认为1
		if(currentPage == null) {
			pageBean = service.getGoodsByType(type, 4, 1);
		}else {
			
			pageBean = service.getGoodsByType(type, 4, Integer.parseInt(currentPage));
		}
		
		try {
			req.setAttribute("pageBean", pageBean);
			req.setAttribute("type", type);
			
			req.getRequestDispatcher("/product.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取商品的详细信息
	 * @param req
	 * @param resp
	 */
	public void findGoodsById(HttpServletRequest req, HttpServletResponse resp) {
		int id =Integer.parseInt(req.getParameter("id"));
		
		Goods goodsInfo = service.getGoodsInfoById(id);
		
		try {
			req.setAttribute("pro", goodsInfo);
			req.getRequestDispatcher("/productInfo_user.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取商品的详细信息
	 * @param reqaddCar
	 * @param resp
	 */
	public void addCar(HttpServletRequest req, HttpServletResponse resp) {
		int id =Integer.parseInt(req.getParameter("id"));
		
		User user = (User) req.getSession().getAttribute("user");
		int ids =232323;
		if(user!=null) {
			ids = user.getId();
		}
		Goods goodsInfo = service.getGoodsInfoById(id);
		
		try {
			service.addCar(goodsInfo,ids+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(ids+"dddddddddddddddd");
		Map<String, List<Goods>> allGoods = service.getCartGoods(ids+"");
		try {
			req.setAttribute("cart", allGoods);
			req.getRequestDispatcher("/showcart.jsp").forward(req, resp);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
