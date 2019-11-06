package com.xhn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhn.entity.Category;
import com.xhn.service.CategoryService;
import com.xhn.untils.DateTool;
import com.xhn.untils.PageTool;
@WebServlet("/category")
public class CategoryServlet extends HttpServlet{
	private CategoryService cService=new CategoryService();
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //��ȡfunc��ֵ
    	String func = request.getParameter("func");
    	switch (func) {
		case "findAllCategory":
			findAllCategory(request,response);
			break;
		case "insertCate":
			insertCate(request,response);
			break;
		case "findCateById":
			findCateById(request,response);
			break;
		case "updateCate":
			updateCate(request,response);
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
		boolean isSuccess=cService.deleteById(ids);
		if (isSuccess) {
			response.sendRedirect("category?func=findAllCategory");
		}
	}
	private void updateCate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		String state = request.getParameter("state");
		String order_number = request.getParameter("order_number");
		String description = request.getParameter("description");
		String create_time = request.getParameter("create_time");
		//��������
		Category category=new Category(Integer.valueOf(cid),cname,Integer.valueOf(state),
				Integer.valueOf(order_number),description,DateTool.stringToDate(create_time));
		System.out.println(category);
		//�����ݴ�����̨���н���
		boolean isSuccess=cService.updateCate(category);
		if (isSuccess) {
			response.sendRedirect("category?func=findAllCategory");
		}else {
			response.sendRedirect("admin/error.jsp");
		}
	}
	private void findCateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ͨ������id�Ȳ�ѯ��������
		String cid = request.getParameter("cid");
		Category category=cService.findCateById(cid);
/*		System.out.println(category);*/
		//�����ݱ��浽������У�װ�����޸�ҳ��
		request.setAttribute("cate", category);
		request.getRequestDispatcher("admin/category_update.jsp").forward(request, response);
		
	}
	private void insertCate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡ����
		String cname = request.getParameter("cname");
		String state = request.getParameter("state");
		String order_number = request.getParameter("order_number");
		String description = request.getParameter("description");
		String create_time = request.getParameter("create_time");
		//��������
		Category category=new Category(cname,Integer.valueOf(state),
				Integer.valueOf(order_number),description,DateTool.stringToDate(create_time));
		//�����ݴ�����̨
		System.out.println(category);
		boolean isSuccess=cService.insertCate(category);
		if (isSuccess) {
			response.sendRedirect("category?func=findAllCategory");
		}else {
			response.sendRedirect("admin/error.jsp");
		}
		
	}
	private void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��Ҫʹ�÷�ҳЧ�����Ȼ�ȡ��ǰҳ��ֵ,�ٻ�ȡ���ݿ��м�¼����
		String currentPage = request.getParameter("currentPage");
		//��ȡ����
		int totalCount=cService.selectCateCount();
		//�ŵ���ҳ�н��в���
		PageTool pageTool=new  PageTool(totalCount, currentPage);
		//ͨ����ҳ��������ѯ��������
		List<Category> categories=cService.findAllCategory(pageTool);
		//����ȡ�������ݱ��浽������У����ύ����Ӧ��jspҳ��
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("cates", categories);
		//����ת��
		request.getRequestDispatcher("admin/category_list.jsp").forward(request, response);
	}
}
