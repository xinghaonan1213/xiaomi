package com.xhn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.xhn.entity.Category;
import com.xhn.entity.Goods;
import com.xhn.service.CategoryService;
import com.xhn.service.GoodsService;
import com.xhn.untils.DateTool;
import com.xhn.untils.PageTool;
import com.xhn.untils.UploadUtils;
@WebServlet("/goods")
@MultipartConfig
public class GoodsServlet extends HttpServlet{
    private CategoryService categoryService=new CategoryService();
    private GoodsService goodsService=new GoodsService();
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//�Ȼ�ȡ��Ӧ��func����
    	String func = request.getParameter("func");
    	switch (func) {
		case "findAllCate":
			findAllCate(request,response);
			break;
		case "insertGoods":
			insertGoods(request,response);
			break;
		case "findAllGoods":
			findAllGoods(request,response);
			break;
		case "findGoodsById":
			findGoodsById(request,response);
			break;
		case "updateGoods":
			updateGoods(request,response);
			break;
		case "deleteById":
			deleteById(request,response);
			break;
		default:
			break;
		}
    }
	private void deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡidֵ
		String ids = request.getParameter("ids");
		boolean isSuccess=goodsService.deleteById(ids);
		if (isSuccess) {
			response.sendRedirect("goods?func=findAllGoods");
		}
		
	}
	private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//ͨ��id�����޸�
		String gid = request.getParameter("gid");
		String cid = request.getParameter("cid");
		String gname = request.getParameter("gname");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String full_description = request.getParameter("full_description");
		String state = request.getParameter("state");
		String version = request.getParameter("version");
		String product_date = request.getParameter("product_date");
		
		//���ǵ����⣬�������Ա��ѡ���ϴ�ͼƬ��ʹ��Ĭ��ͼƬ����Ҫֱ�Ӵ���list���棬���ѡ���ϴ�ͼƬ�����滻��ԭ���ľ�ͼƬ
		//�ȸ�picΪ��
		String pic=null;
		//��ȡ����·��
		Part part = request.getPart("pic");
		if (part.getSize()==0) {
		    //���δѡ��ʹ�þ�ͼ��Ĭ��ѡ�У�
			pic=request.getParameter("oldpic");
		}else {
			//ʹ�ù�����
			pic=UploadUtils.upload("goods?func=findGoodsById", part, request, response);
			if ("".equals(pic)) {
				return;
			}
		}
		//�ŵ���Ӧ�Ķ�����
		Goods goods=new Goods(Integer.valueOf(gid),Integer.valueOf(cid),gname,color,size,Double.valueOf(price),description,
				full_description,pic,Integer.valueOf(state),version,DateTool.stringToDate(product_date));
		boolean isSuccess=goodsService.updateGoods(goods);
		if (isSuccess) {
			//����ɹ���ת��������
			//response.sendRedirect("goods?func=findAllGoods");
			findAllGoods(request,response);
		}else {
			//���ϵ�
			response.sendRedirect("admin/error.jsp");
		}
	}
	private void findGoodsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����idֵ
		String gid = request.getParameter("gid");
		Goods goods=goodsService.findGoodsById(gid);
		//���ݻ���ʱ������Ʒ��������ݻ�����һ������
		List<Category> cates = categoryService.findAllCate();
		request.setAttribute("goods", goods);
		request.setAttribute("cates", cates);
		request.getRequestDispatcher("admin/goods_update.jsp").forward(request, response);
	}
	private void findAllGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Ȼ�ȡ��ǰ�������ֵ
		String currentPage = request.getParameter("currentPage");
		//��ѯ��ȡ��ǰ����ҳ������
		int totalCount=goodsService.selectGoodsCount();
		//System.out.println(totalCount);
		//������ҳ����
		PageTool pageTool=new PageTool(totalCount, currentPage);
		//�������е�������ʾ��list���� ,������Ҫ��ҳ
		//System.out.println(pageTool);
		List<Goods> cGoods=goodsService.findAllGoods(pageTool);
		request.setAttribute("goods", cGoods);
		request.setAttribute("pageTool", pageTool);
		request.getRequestDispatcher("admin/goods_list.jsp").forward(request, response);
	}
	private void insertGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//�Ȼ�ȡǰ̨���������
		String cid = request.getParameter("cid");
		String gname = request.getParameter("gname");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String full_description = request.getParameter("full_description");
		String state = request.getParameter("state");
		String version = request.getParameter("version");
		String product_date = request.getParameter("product_date");
		
		//ͼƬ������
		String pic="";
		//�õ�part����
		Part part = request.getPart("pic");
		//�ж��ļ��Ƿ��ϴ�
		if (part.getSize()==0) {
			//���ش�����Ϣ
			request.setAttribute("msg", "�ļ�û���ϴ�");
			//������ת����ӽ���
		    findAllCate(request, response);
		}else {
			pic=UploadUtils.upload("goods?func=findAllCate", part, request, response);
			if ("".equals(pic)) {
				return;
			}
		}
		//���������������
		Goods goods=new Goods(Integer.valueOf(cid),gname,color,size,Double.valueOf(price),description,
				full_description,pic,Integer.valueOf(state),version,DateTool.stringToDate(product_date));
	boolean isSuccess=goodsService.insertGoods(goods);
	if (isSuccess) {
		//����ɹ���ת��������
        //���²�ѯ�������ݵ�������
		findAllGoods(request,response);
	}else {
		//���ϵ�
		response.sendRedirect("admin/error.jsp");
	}
}
	private void findAllCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����еķ�����
		List<Category> categories=categoryService.findAllCate();
		//System.out.println(categories);
		request.setAttribute("cates", categories);
		request.getRequestDispatcher("admin/goods_add.jsp").forward(request, response);
		
	}
}
