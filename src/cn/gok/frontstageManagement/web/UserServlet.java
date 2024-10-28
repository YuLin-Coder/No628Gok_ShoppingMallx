package cn.gok.frontstageManagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.gok.frontstageManagement.entity.User;
import cn.gok.frontstageManagement.service.UserService;
import cn.gok.util.JSONResult;
import cn.gok.util.JsonUtil;

@WebServlet("/frontstage_userServlet")
public class UserServlet extends HttpServlet {
	UserService service = new UserService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		switch (action) {
		case "login":
			login(req, resp);
			break;
		case "checkUserNameRepeat":
			checkUserNameRepeat(req, resp);
			break;
		case "register":
			register(req, resp);
			break;
		case "logout":
			logout(req, resp);
			break;
		case "checkOldPassword":
			checkOldPassword(req, resp);
			break;
		case "modifyPassword":
			modifyPassword(req, resp);
			break;
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置请求编码格式:
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式			
		resp.setContentType("text/html;charset=utf-8");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String captcha = req.getParameter("captcha");
		if (captcha != null) {
			captcha = captcha.toUpperCase();
		}

		// 先进行验证码验证
		String checkcode = (String) req.getSession().getAttribute("checkcode_session");

		try {
			// 将用户输入的验证码和 系统验证对比
			if (checkcode.equals(captcha)) {
				User user = service.getUserByNameAndPassword(userName, password);
				if (user != null) {
					HttpSession session = req.getSession();
					session.setAttribute("user", user);
					JSONResult ok = JSONResult.ok();
					resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
				} else {
					// 将错误信息封装在结果集中
					JSONResult result = JSONResult.errorMsg("用户名或密码错误，请重试");
					// 以json的形式返回给前端
					resp.getWriter().println(JsonUtil.javaObjectToJson(result));
				}
			} else {
				// 将错误信息封装在结果集中
				JSONResult result = JSONResult.errorMsg("验证码输入错误，请重试");
				// 以json的形式返回给前端
				resp.getWriter().println(JsonUtil.javaObjectToJson(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户名重名检测
	 * 
	 * @param req
	 * @param resp
	 */
	public void checkUserNameRepeat(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("userName");

		if (userName != null) {
			int d = service.checkUserNameRepeat(userName);
			try {
				if (d > 0) {
					JSONResult error = JSONResult.errorMsg("");
					resp.getWriter().println(JsonUtil.javaObjectToJson(error));
				} else {
					JSONResult ok = JSONResult.ok();
					resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 用户注册
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置请求编码格式:
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式			
		resp.setContentType("text/html;charset=utf-8");
		User user = new User();
		user.setUserName(req.getParameter("userName"));
		user.setPassword(req.getParameter("password"));
		user.setEmail(req.getParameter("email"));
		user.setPhone(req.getParameter("phoneNum"));
		user.setRole("ordinaryUser");
		int d = service.addUser(user);
		try {
			if (d > 0) {
				JSONResult ok = JSONResult.ok();
				resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
			} else {
				JSONResult error = JSONResult.errorMsg("注册失败");
				resp.getWriter().println(JsonUtil.javaObjectToJson(error));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 退出登录
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("user");
		try {
			resp.sendRedirect("index.jsp");
		} catch (IOException e) {
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
		User user = (User) req.getSession().getAttribute("user");		
		String oldPassword = service.getPasswordById(user.getId());
		System.out.println("旧密码："+oldPassword);
		String password = req.getParameter("password");
		System.out.println("旧密码验证："+password);
		try {
			if (!oldPassword.equals(password)) {
				JSONResult errorMsg = JSONResult.errorMsg("原始密码错误，请重新输入");
				resp.getWriter().println(JsonUtil.javaObjectToJson(errorMsg));
			} else {
				JSONResult ok = JSONResult.ok();
				resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改密码
	 * @throws IOException 
	 */
	public void modifyPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置请求编码格式:
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式			
		resp.setContentType("text/html;charset=utf-8");

		String password = req.getParameter("newPassword");
		System.out.println("新密码："+password);
		User user = (User) req.getSession().getAttribute("user");
		int id = user.getId();
		System.out.println("旧密码id"+id);
		int d = service.updatePasswordById(id, password);
		try {
			if (d > 0) {
				JSONResult ok = JSONResult.ok();
				resp.getWriter().println(JsonUtil.javaObjectToJson(ok));
			} else {
			  JSONResult errorMsg = JSONResult.errorMsg("修改失败，请重试");
			  resp.getWriter().println(JsonUtil.javaObjectToJson(errorMsg));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
