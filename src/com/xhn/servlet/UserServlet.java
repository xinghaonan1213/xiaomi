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
    	
    	//�Ȼ�ȡfunc��Ӧ�ķ���
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
		//�Ȼ�ȡidֵ
		String uid = request.getParameter("uid");
		//��ȡ����Ա������ͨ�û�
		String manager = request.getParameter("manager");
		//�����ݷŵ�������
		User user=new User(Integer.valueOf(uid), Integer.valueOf(manager));
		//�����ݴ�����̨�����ж�
		boolean isSuccess=userService.updateManager(user);
		if (isSuccess) {
			response.sendRedirect("user?func=findAllUser");
		}
		
	}
	private void findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������������
		String currentPage = request.getParameter("currentPage");
		//��ȡ���ݵ�����
		int totalCount=userService.selectUserCount();
		/*System.out.println(totalCount);*/
		PageTool pageTool=new PageTool(totalCount, currentPage);
		/*������������ʾ���� */
         List<User> users=userService.findAllUser(pageTool);
         request.setAttribute("users", users);
         request.setAttribute("pageTool", pageTool);
         request.getRequestDispatcher("admin/user_list.jsp").forward(request, response);
		
	}
	private void adminLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�˳������е�sessionɾ��������ת����½����
		HttpSession session = request.getSession();
		session.invalidate();
		//��ת����½����
		response.sendRedirect("admin/login.jsp");
	}
	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//��ȡǰ̨������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//�����ݴ���user�����У�������service����ҵ���߼�����
		User user=new User(username, password);
		//����½��״̬������̨������Ự�о��ܼ�ס���״̬��
		HttpSession session = request.getSession();
		boolean isSuccess=userService.adminLogin(user,session);
		if (isSuccess) {
			response.sendRedirect("admin/main.jsp");
		}else {
			request.setAttribute("msg","��¼ʧ�ܣ������ǹ���Ա" );
			request.getRequestDispatcher("admin/login.jsp").forward(request, response);
		}
	}
	private void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�Ȼ�ȡ����ֵ
		String phone = request.getParameter("phone");
		String code = request.getParameter("code");
		//��ȡsession�Ķ���
		HttpSession session = request.getSession();
		//����ҵ������ҵ����
		boolean isSuccess=userService.validateCode(phone,code,session);
		//д�ص�jsp����
		response.getWriter().print(isSuccess);
	}
	private void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�Ȼ�ȡ�ֻ��ţ�servlet�����ڽ����ж��Ƿ��ͳɹ���״̬��service��
		String phone = request.getParameter("phone");
		//�ȴ���session��������service����ҵ����
		HttpSession session = request.getSession();
		//�����ݺͶ��󴫵�service����ҵ���߼�����
		boolean isSuccess=userService.createCode(phone,session);
		//��״̬д�ص�jspҳ��
		response.getWriter().print(isSuccess);
	}
	private void checkPhoneIsRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		checkRegiste(request, response);
		
	}
	private void registeUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//��ȡjsp�е�����
		String uname = request.getParameter("uname");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String area = request.getParameter("area");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String photo="";
		//��ȡpart����
		Part part = request.getPart("photo");

		//�ж�part���Ƿ�����ϴ������ݣ����ڣ�ִ���ϴ��������ڣ���ʾ��ʾͷ��
		if (part.getSize()==0) {
			request.setAttribute("msg", "��ѡ��ͷ��");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else {
			//���������ж��Ƿ�ΪͼƬ������ͼƬ��������  ���ù�����uploadutils
			photo = UploadUtils.upload("register.jsp",part, request, response);
			if ("".equals(photo)) {
				return;
			}
		}
		//�����ݷŵ�user������ �洢�ϴ�����������
		
		User user=new User(uname,Integer.valueOf(gender),phone,area,username,
				password,photo,new Date());
		//��user���󴫵�ҵ���߼���������ݽ���
		boolean isSuccess=userService.registeUser(user);
		if (isSuccess) {
			response.sendRedirect("login.jsp");
		}else {
			request.setAttribute("msg", "ע��ʧ�ܣ�����������");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�Ȼ�ȡ�˺�
		String username = request.getParameter("username");
		//����ҵ���߼����ж��Ƿ���ע��
		boolean isSuccess=userService.checkUsername(username);
		//������д��jspҳ��
		response.getWriter().print(isSuccess);
		
	}
	private void checkRegiste(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�Ȼ�ȡ�ֻ���
		String phone = request.getParameter("phone");
		//�����ݴ��䵽serviceҵ��������У��
		boolean isRegiste=userService.checkRegiste(phone);
		//������д�ص�jsp����
		response.getWriter().print(isRegiste);
	}
}
