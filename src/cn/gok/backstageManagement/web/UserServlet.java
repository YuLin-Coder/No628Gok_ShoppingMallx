package cn.gok.backstageManagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.gok.backstageManagement.entity.User;
import cn.gok.backstageManagement.service.UserService;
import cn.gok.util.JSONResult;
import cn.gok.util.JsonUtil;

@WebServlet("/backstage_userServlet")
public class UserServlet extends HttpServlet{
	UserService service = new UserService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		switch(action) {
			case "login":login(req, resp);break;
			case "modifyPassword":modifyPassword(req, resp);break;
			case "checkOldPassword":checkOldPassword(req, resp);break;
			case "afterModifyPassword":afterModifyPassword(req, resp);break;
			case "logout":logout(req, resp);break;
		}
	}
	
	/**
	 * 用户登录
	 * @throws IOException 
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置请求编码格式:
				req.setCharacterEncoding("utf-8");
				//设置响应编码格式			
				resp.setContentType("text/html;charset=utf-8");
		//获取前端页面传来的用户名
		String userName = req.getParameter("userName");
		//获取前端页面传来的密码
		String password = req.getParameter("password");
		// 1、先验证该用户的角色是否为管理员
		String userRole = service.getUserRoleByName(userName);
		try {
			if(!"admin".equals(userRole)) {
				JSONResult errorMsg = JSONResult.errorMsg("暂无登录权限");
				resp.getWriter().println(JsonUtil.javaObjectToJson(errorMsg));
			}else {
				//2、获取用户数据 进行用户名和密码验证
				User user = service.getUserByNameAndPassword(userName, password);
				if(user!=null) {
					//将用户数据保存到session 作用域中，方便在后续的操作中使用用户数据
					req.getSession().setAttribute("user", user);
					JSONResult ok = JSONResult.ok();
					resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
				}else {
					// 将错误信息封装在结果集中
					JSONResult result = JSONResult.errorMsg("用户名或密码错误，请重试");
					//以json的形式返回给前端
					resp.getWriter().println(JsonUtil.javaObjectToJson(result));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 检查旧密码是否正确 ，在修改密码操作时使用
	 * @throws IOException 
	 */
	public void checkOldPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置请求编码格式:
				req.setCharacterEncoding("utf-8");
				//设置响应编码格式			
				resp.setContentType("text/html;charset=utf-8");
		User user =(User) req.getSession().getAttribute("user");
		String oldPassword = service.getPasswordById(user.getId()); 
		String password = req.getParameter("password");
		try {
			if(!oldPassword.equals(password)) {
				JSONResult errorMsg = JSONResult.errorMsg("原始密码错误，请重新输入");
				resp.getWriter().println(JsonUtil.javaObjectToJson(errorMsg));
			}else {
				JSONResult ok = JSONResult.ok();
				resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 *  修改密码
	 * @throws IOException 
	 */
	public void modifyPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置请求编码格式:
				req.setCharacterEncoding("utf-8");
				//设置响应编码格式			
				resp.setContentType("text/html;charset=utf-8");
		
		String password = req.getParameter("newPassword");
		User user =(User) req.getSession().getAttribute("user");
		int id = user.getId();
		
		int d = service.updatePasswordById(id, password);
		
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
	 * 成功修改密码后，清除session中的数据，并且重新跳转到登录页
	 * @param req
	 * @param resp
	 */
    public void afterModifyPassword(HttpServletRequest req, HttpServletResponse resp) {
    	req.getSession().removeAttribute("user");
		try {
			//使用iframe时，要避免这种直接跳转的方式
			// 此时只会让内嵌的iframe跳转到登录页，并非整个页面
//			resp.sendRedirect("backstage/login.jsp");
			
			PrintWriter writer = resp.getWriter();
			writer.print("<html>");
			writer.print("<script>");
			writer.print("window.open('"+ req.getContextPath()+"/backstage/login.jsp','_top')");
			writer.print("</script>");
			writer.print("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	

	/**
	 * 退出登录
	 */
    public void logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("user");
		try {
			resp.sendRedirect("backstage/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
