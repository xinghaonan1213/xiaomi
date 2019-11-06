package com.xhn.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xhn.entity.Orders;
import com.xhn.entity.User;
import com.xhn.service.OrdersService;
import com.xhn.service.TrolleyService;
@WebServlet("/orders")
public class OrdersServlet extends HttpServlet{

   private OrdersService ordersService=new OrdersService();
   private TrolleyService trolleyService=new TrolleyService();
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//�Ȼ�ȡfunc
    	String func = request.getParameter("func");
    	switch (func) {
		case "createOrders":
			createOrders(request,response);
			break;
		case "updateOrders":
			updateOrders(request,response);
			break;
		default:
			break;
		}
    }
	private void updateOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String orders_number = request.getParameter("orders_number");
		boolean isSuccess=ordersService.updateOrders(orders_number);
		if (isSuccess) {
			response.sendRedirect("index?func=indexInfo");
		}
	}
	private void createOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Ȼ�ȡǰ̨������������ �ܼ۸����Ʒ�ļ���
		String sumPrice = request.getParameter("sumPrice");//�ܼ�
		String goodsCount = request.getParameter("goodsCount");//��Ʒ������
		String orders_number = UUID.randomUUID().toString();//ʹ��������ɵ����ݲ���������
		//��ȡ�û���uid
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		//��������
		String ordersName=user.getUname()+"�Ķ�������";
		//����
		Date create_time=new Date();
		//���д�ֵ�ŵ�orders������
		Orders orders=new Orders(orders_number, Integer.valueOf(user.getUid()), ordersName, Integer.valueOf(goodsCount), create_time, Double.valueOf(sumPrice));
		//�����ݴ���service����ҵ���߼��ж�
		boolean isSuccess=ordersService.createOrders(orders);
		if (isSuccess) {
			System.out.println("��Ӷ�����Ϣ�ɹ�����");
		    //��Ӷ����ɹ��󣬹��ﳵ����ƷҪʹorders_number�仯Ϊ��Ӧ��ֵ�����䲻Ϊnull�Ͳ��ᱣ���ڹ��ﳵ���
			boolean isTrue=trolleyService.updateTrolley(orders_number,user.getUid());
			if (isTrue) {
				//System.out.println("���ﳵ�޸ĳɹ�������");
				//�洢���������
				request.setAttribute("orders", orders);
				//�����ݴ�����Ӧ������ҳ��
				request.getRequestDispatcher("pay/index.jsp").forward(request, response);
			}
		}
	}
}
