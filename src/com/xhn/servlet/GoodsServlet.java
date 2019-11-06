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
    	//先获取对应的func方法
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
		//获取id值
		String ids = request.getParameter("ids");
		boolean isSuccess=goodsService.deleteById(ids);
		if (isSuccess) {
			response.sendRedirect("goods?func=findAllGoods");
		}
		
	}
	private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//通过id进行修改
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
		
		//考虑的问题，如果管理员不选择上传图片，使用默认图片，需要直接传回list界面，如果选择上传图片，则替换掉原来的就图片
		//先给pic为空
		String pic=null;
		//获取绝对路径
		Part part = request.getPart("pic");
		if (part.getSize()==0) {
		    //如果未选中使用旧图（默认选中）
			pic=request.getParameter("oldpic");
		}else {
			//使用工具类
			pic=UploadUtils.upload("goods?func=findGoodsById", part, request, response);
			if ("".equals(pic)) {
				return;
			}
		}
		//放到对应的对象中
		Goods goods=new Goods(Integer.valueOf(gid),Integer.valueOf(cid),gname,color,size,Double.valueOf(price),description,
				full_description,pic,Integer.valueOf(state),version,DateTool.stringToDate(product_date));
		boolean isSuccess=goodsService.updateGoods(goods);
		if (isSuccess) {
			//如果成功跳转到主界面
			//response.sendRedirect("goods?func=findAllGoods");
			findAllGoods(request,response);
		}else {
			//找老弟
			response.sendRedirect("admin/error.jsp");
		}
	}
	private void findGoodsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取主键id值
		String gid = request.getParameter("gid");
		Goods goods=goodsService.findGoodsById(gid);
		//数据回填时还有商品分类的数据回填是一个数据
		List<Category> cates = categoryService.findAllCate();
		request.setAttribute("goods", goods);
		request.setAttribute("cates", cates);
		request.getRequestDispatcher("admin/goods_update.jsp").forward(request, response);
	}
	private void findAllGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先获取当前界面的码值
		String currentPage = request.getParameter("currentPage");
		//查询获取当前的总页码数量
		int totalCount=goodsService.selectGoodsCount();
		//System.out.println(totalCount);
		//创建分页对象
		PageTool pageTool=new PageTool(totalCount, currentPage);
		//查找所有的数据显示到list界面 ,首先需要分页
		//System.out.println(pageTool);
		List<Goods> cGoods=goodsService.findAllGoods(pageTool);
		request.setAttribute("goods", cGoods);
		request.setAttribute("pageTool", pageTool);
		request.getRequestDispatcher("admin/goods_list.jsp").forward(request, response);
	}
	private void insertGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//先获取前台界面的数据
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
		
		//图片的名称
		String pic="";
		//得到part对象
		Part part = request.getPart("pic");
		//判断文件是否上传
		if (part.getSize()==0) {
			//返回错误信息
			request.setAttribute("msg", "文件没有上传");
			//重新跳转到添加界面
		    findAllCate(request, response);
		}else {
			pic=UploadUtils.upload("goods?func=findAllCate", part, request, response);
			if ("".equals(pic)) {
				return;
			}
		}
		//创建对象放置数据
		Goods goods=new Goods(Integer.valueOf(cid),gname,color,size,Double.valueOf(price),description,
				full_description,pic,Integer.valueOf(state),version,DateTool.stringToDate(product_date));
	boolean isSuccess=goodsService.insertGoods(goods);
	if (isSuccess) {
		//如果成功跳转到主界面
        //重新查询所有数据到主界面
		findAllGoods(request,response);
	}else {
		//找老弟
		response.sendRedirect("admin/error.jsp");
	}
}
	private void findAllCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取分类中的分类名
		List<Category> categories=categoryService.findAllCate();
		//System.out.println(categories);
		request.setAttribute("cates", categories);
		request.getRequestDispatcher("admin/goods_add.jsp").forward(request, response);
		
	}
}
