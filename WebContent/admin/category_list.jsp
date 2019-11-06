<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品分类</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
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
	    	<li><a href="#">分类管理</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <div class="tools">
	    	<ul class="toolbar">
		        <li style="cursor: pointer;" onclick="add_category()"><span><img src="<%=basePath %>admin/images/t01.png"  /></span>添加类别</li>
		        <li style="cursor: pointer;" onclick="batchDelete()"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
	        </ul>
	    </div>
	    <table class="tablelist">
	    	<thead>
		    	<tr>
			        <th><input name="" type="checkbox" value="" checked="checked"/></th>
			        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
			        <th>类别名称</th>
			        <th>启用状态</th>
			        <th>排序序号</th>
			        <th>创建时间</th>
			        <th>描述</th>
			        <th>操作</th>
		        </tr>
	        </thead>
			<tbody>
			     <!--遍历对象中的元素  -->
			     <c:forEach items="${ cates}" var="cate" varStatus="i">
			     	<tr style="text-align:center;">
			        <td><input name="" class="one" type="checkbox" value="${cate.cid }"/></td>
			        <td>${i.count }</td>
			        <td>${cate.cname }</td>
			        <td>
			          <c:if test="${cate.state==1 }"><font style="color:green">启用</font></c:if>
			          <c:if test="${cate.state==0 }"><font style="color:red">未启用</font></c:if>
			        </td>
			        <td>${cate.order_number }</td>
			        <td>${cate.description }</td>
			        <td>
			           <fmt:formatDate value="${cate.create_time }" pattern="yy-MM-dd HH:mm:ss"/>
			        </td>
			        <td>
			         <a href="category?func=findCateById&cid=${cate.cid }">修改</a>
			        </td>
		        </tr>
			     </c:forEach>
			</tbody>
		</table>
		<div class="pagin">
			<div class="message">
				共<i class="blue">${pageTool.totalCount }</i>条记录， 当前显示第&nbsp;<i
					class="blue">${pageTool.currentPage }&nbsp;</i>页 总共&nbsp;<i
					class="blue">${pageTool.pageSize}&nbsp;</i>页
			</div>
			<ul class="paginList">
				<li class="paginItem"><a
					href="<%=basePath%>category?func=findAllCategory&currentPage=1">首页</a></li>
				<li class="paginItem"><a
					href="<%=basePath%>category?func=findAllCategory&currentPage=${pageTool.lastPage }">上一页</a></li>
				<li class="paginItem"><a
					href="<%=basePath%>category?func=findAllCategory&currentPage=${pageTool.nextPage }">下一页</a></li>
				<li class="paginItem"><a
					href="<%=basePath%>category?func=findAllCategory&currentPage=${pageTool.pageSize }">尾页</a></li>
			</ul>
		</div>
  </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<script type="text/javascript">
	 function add_category(){
		 window.location="admin/category_add.jsp";
	 }
	 function batchDelete(){
		//先设置一个String的字符串用来拼接字符串
			var ids="";
			//获取每个单选框的id值
			$(".one:checked").each(function(){
				//拼接id
				ids=","+$(this).val();
			})
			if(ids==""){
				alert("请先选择好再删除吧");
				return;
			}else{
				ids=ids.substring(1);
				ids=ids.trim();
			}
			//将ids传到后台servlet
			if(confirm("您确定要删除吗？？")){
				window.location="category?func=deleteById&ids="+ids;
			}
	 } 
	</script>
</body>
</html>
