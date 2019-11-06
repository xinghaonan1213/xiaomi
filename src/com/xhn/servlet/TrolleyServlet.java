package com.xhn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xhn.entity.Trolley;
import com.xhn.entity.User;
import com.xhn.service.TrolleyService;

@WebServlet("/trolley")
public class TrolleyServlet extends HttpServlet{

private TrolleyService trolleyService=new TrolleyService();
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//获取对应的方法参数
    	String func = request.getParameter("func");
    	switch (func) {
		case "addTrolley":
			addTrolley(request,response);
			break;
        case "selectTrolleyCount":
        	selectTrolleyCount(request,response);
        	break;
        case "findAllTrolley":
        	findAllTrolley(request,response);
        	break;
        case "addOrDeleteNumber":
        	addOrDeleteNumber(request,response);
        	break;
        case "delGoods":
        	delGoods(request,response);
        	break;
		default:
			break;
		}
    }
	private void delGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取id值
		String tid = request.getParameter("tid");
		boolean isSuccess=trolleyService.delGoods(tid);
		if (isSuccess) {
			response.sendRedirect("trolley?func=findAllTrolley");
		  }
	}
	private void addOrDeleteNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//先获取tid的值
		String tid = request.getParameter("tid");
		//获取number的值
		String number = request.getParameter("number");
	boolean	isSuccess=trolleyService.addOrDeleteNumber(tid,number);
	if (isSuccess) {
		response.sendRedirect("trolley?func=findAllTrolley");
	  }
	}
	private void findAllTrolley(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//需要先判断是哪个登陆用户的购物车
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
	    //将数据传到后台进行查询
		List<Trolley> trolleys=trolleyService.findAllTrolley(user);
		//System.out.println(trolleys);
		//将数据存储到域对象，并传回购物车界面
		request.setAttribute("trolleys", trolleys);
		request.getRequestDispatcher("trolley.jsp").forward(request, response);
		
	}
	private void selectTrolleyCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//通过用户id来查询数据
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
        //通过uid进行查询商品种类
		int count=trolleyService.selectTrolleyCount(user.getUid());
		response.getWriter().write(count+"");
	}
	private void addTrolley(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//先获取商品的id
		String gid = request.getParameter("gid");
		//先获取session对象，再获取当前用户的uid值
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		//将uid和gid添加到trolley中
		Trolley trolley=new Trolley(user.getUid(), Integer.valueOf(gid));
        //将数据传到后台进行数据的添加
		boolean isRepeat=trolleyService.addTrolley(trolley);
		response.getWriter().print(isRepeat);
	}
}
