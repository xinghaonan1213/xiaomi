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
    	//先获取func
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
		//先获取前台传过来的数据 总价格和商品的件数
		String sumPrice = request.getParameter("sumPrice");//总价
		String goodsCount = request.getParameter("goodsCount");//商品的数量
		String orders_number = UUID.randomUUID().toString();//使用随机生成的数据产生订单号
		//获取用户的uid
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		//订单名称
		String ordersName=user.getUname()+"的订单详情";
		//日期
		Date create_time=new Date();
		//进行传值放到orders对象中
		Orders orders=new Orders(orders_number, Integer.valueOf(user.getUid()), ordersName, Integer.valueOf(goodsCount), create_time, Double.valueOf(sumPrice));
		//将数据传到service进行业务逻辑判断
		boolean isSuccess=ordersService.createOrders(orders);
		if (isSuccess) {
			System.out.println("添加订单信息成功！！");
		    //添加订单成功后，购物车的商品要使orders_number变化为对应的值，而其不为null就不会保存在购物车里边
			boolean isTrue=trolleyService.updateTrolley(orders_number,user.getUid());
			if (isTrue) {
				//System.out.println("购物车修改成功！！！");
				//存储到域对象中
				request.setAttribute("orders", orders);
				//将数据传到对应的数据页面
				request.getRequestDispatcher("pay/index.jsp").forward(request, response);
			}
		}
	}
}
