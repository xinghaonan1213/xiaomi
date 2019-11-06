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
    	//��ȡ��Ӧ�ķ�������
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
		//��ȡidֵ
		String tid = request.getParameter("tid");
		boolean isSuccess=trolleyService.delGoods(tid);
		if (isSuccess) {
			response.sendRedirect("trolley?func=findAllTrolley");
		  }
	}
	private void addOrDeleteNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�Ȼ�ȡtid��ֵ
		String tid = request.getParameter("tid");
		//��ȡnumber��ֵ
		String number = request.getParameter("number");
	boolean	isSuccess=trolleyService.addOrDeleteNumber(tid,number);
	if (isSuccess) {
		response.sendRedirect("trolley?func=findAllTrolley");
	  }
	}
	private void findAllTrolley(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��Ҫ���ж����ĸ���½�û��Ĺ��ﳵ
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
	    //�����ݴ�����̨���в�ѯ
		List<Trolley> trolleys=trolleyService.findAllTrolley(user);
		//System.out.println(trolleys);
		//�����ݴ洢������󣬲����ع��ﳵ����
		request.setAttribute("trolleys", trolleys);
		request.getRequestDispatcher("trolley.jsp").forward(request, response);
		
	}
	private void selectTrolleyCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//ͨ���û�id����ѯ����
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
        //ͨ��uid���в�ѯ��Ʒ����
		int count=trolleyService.selectTrolleyCount(user.getUid());
		response.getWriter().write(count+"");
	}
	private void addTrolley(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�Ȼ�ȡ��Ʒ��id
		String gid = request.getParameter("gid");
		//�Ȼ�ȡsession�����ٻ�ȡ��ǰ�û���uidֵ
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		//��uid��gid��ӵ�trolley��
		Trolley trolley=new Trolley(user.getUid(), Integer.valueOf(gid));
        //�����ݴ�����̨�������ݵ����
		boolean isRepeat=trolleyService.addTrolley(trolley);
		response.getWriter().print(isRepeat);
	}
}
