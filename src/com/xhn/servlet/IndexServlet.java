package com.xhn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhn.entity.Category;
import com.xhn.entity.Goods;
import com.xhn.service.CategoryService;
import com.xhn.service.GoodsService;
import com.xhn.untils.CookieUtils;
@WebServlet("/index")
public class IndexServlet extends HttpServlet{

private CategoryService categoryService=new CategoryService();
private GoodsService goodsService=new GoodsService();
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String func = request.getParameter("func");
    	switch (func) {
		case "indexInfo":
			indexInfo(request,response);
			break;
		case "searchInfo":
			searchInfo(request,response);
			break;
		case "findGoodsById":
			findGoodsById(request,response);
			break;
		default:
			break;
		}
    	
    }
	private void findGoodsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     //��ȡgid��ֵ
		String gid = request.getParameter("gid");
		//ͨ��goodsservice��ͨ��������ѯ��Ӧ������
		Goods goods = goodsService.findGoodsById(gid);
		request.setAttribute("goods", goods);
		//ת������Ʒ����ҳ��
		request.getRequestDispatcher("detail.jsp").forward(request, response);
		
	}
	private void searchInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ǰ̨�����ݻ�ȡ��
		String search = request.getParameter("search");
		//�����ݴ洢��cookie�У���ȡ���ڹ�������
		
		// ����ȡ����������Ӧ���ͻ��������
          CookieUtils.addCookie(search, request, response);
		//������д�ص�jspҳ��
		response.getWriter().print(true);
	}
	private void indexInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ʾ������Ϣ
		List<Category> cates=categoryService.findAllCate(10);
		//��ʾС�����ǵ�Ʒ 4����С�����е����ǵ�Ʒ 5������ʾ��������
		List<Goods> starGoodes=goodsService.findGoodsByState(4,5);
		//��ʾ�ҵ� 3��������еļҵ��� 8������ʾ��������
		List<Goods> homeGoodes =goodsService.finGoodsByCate(3,8);
		//��ʾ���� 5��������е����ܼҾ��� 4������ʾ������
		List<Goods> smartGoodes = goodsService.finGoodsByCate(5,5);
		//���Ų�Ʒ  1����С��������Ų�Ʒ����ʾ4������ ����ӵ������Ƽ����ţ�����������������
		List<Goods> hotGoodes = goodsService.findGoodsByState(1, 4);
		//��ȡ��Ϊ���Ƽ�����Ʒ
		String info = CookieUtils.getCookieInfo(request);
		// �жϣ����infoû�����ݣ��͸���С������ʾ
		List<Goods> tjGoodes = null;
		if ("".equals(info)) {
			tjGoodes = goodsService.findGoodsByState(2, 5);
			
		} else {
			tjGoodes = goodsService.finGoodsBySearch(info, 5);
		}
    	//���õ�request�������
    	request.setAttribute("cates", cates);
    	request.setAttribute("starGoodes", starGoodes);
    	request.setAttribute("homeGoodes", homeGoodes);
    	request.setAttribute("smartGoodes", smartGoodes);
    	request.setAttribute("hotGoodes", hotGoodes);
    	request.setAttribute("tjGoodes", tjGoodes);
    	//ת������������
    	request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
}
