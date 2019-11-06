<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//获取tomcat 协议+地址+端口号
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/ximipic/";
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品信息</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.tablelist th{
   text-align:center;
}
</style>
<script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>

<script type="text/javascript">

// old write 
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});



</script>
</head>
<body>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="#">商品管理</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <div class="tools">
	    	<ul class="toolbar">
		        <li style="cursor: pointer;" onclick="add_goods()"><span><img src="<%=basePath %>admin/images/t01.png"  /></span>添加商品</li>
		        <li style="cursor: pointer;" onclick="batchDelete()"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
	        </ul>
	    </div>
	    <table class="tablelist">
	    	<thead>
		    	<tr style="text-align:center">
			        <th><input name="" type="checkbox" value="" checked="checked"/></th>
			        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
			        <th>商品类别</th>
			        <th>商品名称</th>
			        <th>商品颜色</th>
			        <th>商品价格</th>
			        <th width="10%">简介</th>
			        <th width="20%">详情</th>
			       	<th>商品展示图</th>
			       	<th>是否热推</th>
			       	<th>型号</th>
			       	<th>生产日期</th>
			       	<th>操作</th>
		        </tr>
	        </thead>
	        <tbody>
				<c:forEach items="${goods}" var="goods" varStatus="i">
					<tr style="text-align:center">
						<td><input class="one" name="" type="checkbox" value="${goods.cid }"/></td>
						<td>${i.count }</td>
						<td>${goods.category.cname}</td>
						<td>${goods.gname }</td>
						<td>${goods.color }</td>
						<td>${goods.price }</td>
						<td widtd="10%">${goods.description}</td>
						<td widtd="20%">${goods.full_description}</td>
						<td>
						  <img width="77" height="77" src="<%=imgPath %>${goods.pic}">
						</td>
						<td>
						   <c:if test="${goods.state==0 }">正常</c:if>
						   <c:if test="${goods.state==1 }">热门产品</c:if>
						   <c:if test="${goods.state==2 }">为你推荐</</c:if>
						   <c:if test="${goods.state==3 }">新品</c:if>
						   <c:if test="${goods.state==4 }">小米明星单品</c:if>
						</td>
						<td>${goods.version }</td>
						<td>
						  <fmt:formatDate value="${goods.product_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td><a style="color:red" href="<%=basePath%>goods?func=findGoodsById&gid=${goods.gid }">修改</a></td>
					</tr>
				</c:forEach>
			</tbody>
	    </table>
	    <div class="pagin">
	    	<div class="message">
	    	共<i class="blue">${pageTool.totalCount }</i>条记录，
	    	当前显示第&nbsp;<i class="blue">${pageTool.currentPage }&nbsp;</i>页
	    	总共&nbsp;<i class="blue">${pageTool.pageSize}&nbsp;</i>页
	    	</div>
	        <ul class="paginList">
		         <li class="paginItem"><a href="<%=basePath %>goods?func=findAllGoods&currentPage=1">首页</a></li>
		         <li class="paginItem"><a href="<%=basePath %>goods?func=findAllGoods&currentPage=${pageTool.lastPage}">上一页</a></li>
		         <li class="paginItem"><a href="<%=basePath %>goods?func=findAllGoods&currentPage=${pageTool.nextPage}">下一页</a></li>
		         <li class="paginItem"><a href="<%=basePath %>goods?func=findAllGoods&currentPage=${pageTool.pageSize}">尾页</a></li>
	        </ul>
	    </div>
    </div>
    <script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<script type="text/javascript">
	 function add_goods(){
		 //先得查询出商品的分类实现数据的填写
		 window.location="<%=basePath%>goods?func=findAllCate";
	 }
	 
	 function batchDelete(){
			//先设置一个String的字符串用来拼接字符串
				var ids="";
				//获取每个单选框的id值  遍历数组
				$(".one:checked").each(function(){
					//拼接id
					ids=","+$(this).val();
				})
				if(ids==""){
					alert("请先选择好再删除吧");
					return;
				}else{
					ids=ids.substring(1);
					//trim函数将字符串俩侧的东西清空
					ids=ids.trim();
				}
				//将ids传到后台servlet
				if(confirm("您确定要删除吗？？")){
					window.location="goods?func=deleteById&ids="+ids;
				}
		 } 
	</script>
</body>
</html>
