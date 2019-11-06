package com.xhn.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.xhn.entity.User;
import com.xhn.service.UserService;
import com.xhn.untils.PageTool;
import com.xhn.untils.UploadUtils;
@WebServlet("/user")
@MultipartConfig
public class UserServlet extends HttpServlet{
    private UserService userService=new UserService();
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//先获取func对应的方法
    	String func = request.getParameter("func");
    	switch (func) {
		case "checkRegiste":
			checkRegiste(request,response);
			break;
		case "checkUsername":
			checkUsername(request,response);
		break;
		case "registeUser":
			registeUser(request,response);
			break;
		case "checkPhoneIsRegist":
			checkPhoneIsRegist(request,response);
			break;
		case "createCode":
			createCode(request,response);
			break;
	    case "validateCode":
	    	validateCode(request,response);
	    	break;
	    case "adminLogin":
	    	adminLogin(request,response);
	    	break;
	    case "adminLogout":
	    	adminLogout(request,response);
	    	break;
	    case "findAllUser":
	    	findAllUser(request,response);
	    	break;
	    case "updateManager":
	    	updateManager(request,response);
	    	break;
	    case "deleteById":
	    	deleteById(request,response);
	    	break;
		default:
			break;
		}
    }
	private void deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids=request.getParameter("ids");
		boolean isSuccess=userService.deleteById(ids);
		if (isSuccess) {
			response.sendRedirect("user?func=findAllUser");
		}
		
	}
	private void updateManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//先获取id值
		String uid = request.getParameter("uid");
		//获取管理员或者普通用户
		String manager = request.getParameter("manager");
		//将数据放到对象中
		User user=new User(Integer.valueOf(uid), Integer.valueOf(manager));
		//将数据传到后台进行判断
		boolean isSuccess=userService.updateManager(user);
		if (isSuccess) {
			response.sendRedirect("user?func=findAllUser");
		}
		
	}
	private void findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//构建工具类对象
		String currentPage = request.getParameter("currentPage");
		//获取数据的总数
		int totalCount=userService.selectUserCount();
		/*System.out.println(totalCount);*/
		PageTool pageTool=new PageTool(totalCount, currentPage);
		/*将所有数据显示出来 */
         List<User> users=userService.findAllUser(pageTool);
         request.setAttribute("users", users);
         request.setAttribute("pageTool", pageTool);
         request.getRequestDispatcher("admin/user_list.jsp").forward(request, response);
		
	}
	private void adminLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//退出将所有的session删除，并跳转到登陆界面
		HttpSession session = request.getSession();
		session.invalidate();
		//跳转到登陆界面
		response.sendRedirect("admin/login.jsp");
	}
	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//获取前台的数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//将数据传到user对象中，并进入service进行业务逻辑处理
		User user=new User(username, password);
		//将登陆的状态传到后台，往后会话中就能记住这个状态了
		HttpSession session = request.getSession();
		boolean isSuccess=userService.adminLogin(user,session);
		if (isSuccess) {
			response.sendRedirect("admin/main.jsp");
		}else {
			request.setAttribute("msg","登录失败，您不是管理员" );
			request.getRequestDispatcher("admin/login.jsp").forward(request, response);
		}
	}
	private void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//先获取俩个值
		String phone = request.getParameter("phone");
		String code = request.getParameter("code");
		//获取session的对象
		HttpSession session = request.getSession();
		//传到业务层进行业务处理
		boolean isSuccess=userService.validateCode(phone,code,session);
		//写回到jsp界面
		response.getWriter().print(isSuccess);
	}
	private void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//先获取手机号（servlet），在进行判断是否发送成功的状态（service）
		String phone = request.getParameter("phone");
		//先创建session对象，再在service进行业务处理
		HttpSession session = request.getSession();
		//将数据和对象传到service进行业务逻辑处理
		boolean isSuccess=userService.createCode(phone,session);
		//将状态写回到jsp页面
		response.getWriter().print(isSuccess);
	}
	private void checkPhoneIsRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		checkRegiste(request, response);
		
	}
	private void registeUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//获取jsp中的数据
		String uname = request.getParameter("uname");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String area = request.getParameter("area");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String photo="";
		//获取part对象
		Part part = request.getPart("photo");

		//判断part中是否存在上传的内容，存在，执行上传；不存在，提示显示头像
		if (part.getSize()==0) {
			request.setAttribute("msg", "请选择头像！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else {
			//存在内容判断是否为图片，并且图片不能重名  调用工具类uploadutils
			photo = UploadUtils.upload("register.jsp",part, request, response);
			if ("".equals(photo)) {
				return;
			}
		}
		//将数据放到user对象中 存储上传的所有内容
		
		User user=new User(uname,Integer.valueOf(gender),phone,area,username,
				password,photo,new Date());
		//将user对象传到业务逻辑层进行数据交互
		boolean isSuccess=userService.registeUser(user);
		if (isSuccess) {
			response.sendRedirect("login.jsp");
		}else {
			request.setAttribute("msg", "注册失败，请重新输入");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//先获取账号
		String username = request.getParameter("username");
		//传入业务逻辑层判断是否已注册
		boolean isSuccess=userService.checkUsername(username);
		//将数据写回jsp页面
		response.getWriter().print(isSuccess);
		
	}
	private void checkRegiste(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//先获取手机号
		String phone = request.getParameter("phone");
		//将数据传输到service业务处理层进行校验
		boolean isRegiste=userService.checkRegiste(phone);
		//将数据写回到jsp界面
		response.getWriter().print(isRegiste);
	}
}
