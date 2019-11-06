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
	     //获取gid的值
		String gid = request.getParameter("gid");
		//通过goodsservice来通过方法查询对应的数据
		Goods goods = goodsService.findGoodsById(gid);
		request.setAttribute("goods", goods);
		//转发到商品详情页面
		request.getRequestDispatcher("detail.jsp").forward(request, response);
		
	}
	private void searchInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//将前台的数据获取到
		String search = request.getParameter("search");
		//将数据存储在cookie中，存取放在工具类中
		
		// 将获取到的数据响应给客户端浏览器
          CookieUtils.addCookie(search, request, response);
		//将数据写回到jsp页面
		response.getWriter().print(true);
	}
	private void indexInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//显示分类信息
		List<Category> cates=categoryService.findAllCate(10);
		//显示小米明星单品 4代表小分类中的明星单品 5代表显示的数据量
		List<Goods> starGoodes=goodsService.findGoodsByState(4,5);
		//显示家电 3代表分类中的家电类 8代表显示的数据量
		List<Goods> homeGoodes =goodsService.finGoodsByCate(3,8);
		//显示智能 5代表分类中的智能家居类 4代表显示的数量
		List<Goods> smartGoodes = goodsService.finGoodsByCate(5,5);
		//热门产品  1代表小分类的热门产品，显示4条数据 后添加的优先推荐热门，按照主键降序排列
		List<Goods> hotGoodes = goodsService.findGoodsByState(1, 4);
		//获取到为你推荐的商品
		String info = CookieUtils.getCookieInfo(request);
		// 判断，如果info没有数据，就根据小分类显示
		List<Goods> tjGoodes = null;
		if ("".equals(info)) {
			tjGoodes = goodsService.findGoodsByState(2, 5);
			
		} else {
			tjGoodes = goodsService.finGoodsBySearch(info, 5);
		}
    	//放置到request域对象中
    	request.setAttribute("cates", cates);
    	request.setAttribute("starGoodes", starGoodes);
    	request.setAttribute("homeGoodes", homeGoodes);
    	request.setAttribute("smartGoodes", smartGoodes);
    	request.setAttribute("hotGoodes", hotGoodes);
    	request.setAttribute("tjGoodes", tjGoodes);
    	//转发到主界面中
    	request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
}
