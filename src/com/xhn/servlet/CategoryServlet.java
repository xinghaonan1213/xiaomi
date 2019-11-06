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
       //获取func的值
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
		//创建对象
		Category category=new Category(Integer.valueOf(cid),cname,Integer.valueOf(state),
				Integer.valueOf(order_number),description,DateTool.stringToDate(create_time));
		System.out.println(category);
		//将数据传到后台进行交互
		boolean isSuccess=cService.updateCate(category);
		if (isSuccess) {
			response.sendRedirect("category?func=findAllCategory");
		}else {
			response.sendRedirect("admin/error.jsp");
		}
	}
	private void findCateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过主键id先查询出来数据
		String cid = request.getParameter("cid");
		Category category=cService.findCateById(cid);
/*		System.out.println(category);*/
		//将数据保存到域对象中，装发到修改页面
		request.setAttribute("cate", category);
		request.getRequestDispatcher("admin/category_update.jsp").forward(request, response);
		
	}
	private void insertCate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取数据
		String cname = request.getParameter("cname");
		String state = request.getParameter("state");
		String order_number = request.getParameter("order_number");
		String description = request.getParameter("description");
		String create_time = request.getParameter("create_time");
		//创建对象
		Category category=new Category(cname,Integer.valueOf(state),
				Integer.valueOf(order_number),description,DateTool.stringToDate(create_time));
		//将数据传到后台
		System.out.println(category);
		boolean isSuccess=cService.insertCate(category);
		if (isSuccess) {
			response.sendRedirect("category?func=findAllCategory");
		}else {
			response.sendRedirect("admin/error.jsp");
		}
		
	}
	private void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//需要使用分页效果，先获取当前页码值,再获取数据库中记录总数
		String currentPage = request.getParameter("currentPage");
		//获取总数
		int totalCount=cService.selectCateCount();
		//放到分页中进行操作
		PageTool pageTool=new  PageTool(totalCount, currentPage);
		//通过分页操作，查询所有数据
		List<Category> categories=cService.findAllCategory(pageTool);
		//将获取到的数据保存到域对象中，并提交到对应的jsp页面
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("cates", categories);
		//请求转发
		request.getRequestDispatcher("admin/category_list.jsp").forward(request, response);
	}
}
