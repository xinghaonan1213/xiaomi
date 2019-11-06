<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//给图片添加路径
	String imgPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/ximipic/";
%>

<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
  .tablelist th{
    text-align:center;
  }
</style>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>

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
				<li style="cursor: pointer;" onclick="batchDelete()"><span>
						<img src="<%=basePath%>admin/images/t03.png" />
				</span>批量删除</li>
			</ul>

		</div>
    <table class="tablelist">
    	<thead>
	    	<tr>
	        <th><input name="" type="checkbox" value="" /></th>
	        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
	        <th>姓名</th>
	        <th>性别</th>
	        <th>电话号码</th>
	        <th>所在地区</th>
	        <th>权限</th>
	        <th>账号</th>
	       	<th>头像</th>
	       	<th>注册时间</th>
	       	<th>操作</th>
	        </tr>
	        </thead>
        <tbody>
       <%--  ${users} --%>
        <c:forEach items="${users}" var="user" varStatus="i">
            <tr style="text-align:center">
            <!-- 将用户id值绑定到复选框上 -->
            <td><input name="" class="one" type="checkbox" value="${user.uid}" /></td>
	        <td>${i.count}</td>
	        <td>${user.uname}</td>
	        <td>
	          <c:if test="${user.gender == 1 }">男</c:if>
	           <c:if test="${user.gender == 0 }">女</c:if>
	        </td>
	        <td>${user.phone}</td>
	        <td>${user.area }</td>
	        <td>
	           <c:if test="${user.manager == 1 }">普通用户</c:if>
	           <c:if test="${user.manager == 0 }"><span style="color:red;">管理员</span></c:if>
	        </td>
	        <td>${user.username }</td>
	       	<td>
	       	    <img  src="<%=imgPath %>${user.photo }" width="40" height="40"/>
	       	</td>
	       	<td>
	       	 <fmt:formatDate value="${user.create_time }" pattern="yy-MM-dd HH:mm:ss"/>
	       	</td>
	       	<td>
	       	   <c:if test="${user.manager==1 }">
	       	   <a style="color:green" href="<%=basePath %>user?func=updateManager&uid=${user.uid}&manager=0">设置为管理员</a>
	       	   </c:if>
	       	   <c:if test="${user.manager==0 }">
	       	    <a style="color:red;" href="<%=basePath %>user?func=updateManager&uid=${user.uid}&manager=1">撤销管理员</a>
	       	   </c:if>
	       	</td>
	        </tr>
        	</c:forEach>
        </tbody>
    </table>
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${pageTool.totalCount }</i>条记录，
    	当前显示第&nbsp;<i class="blue">${pageTool.currentPage }&nbsp;</i>页
    	总共&nbsp;<i class="blue">${pageTool.pageSize}&nbsp;</i>页
    	</div>
        <ul class="paginList">
	        
	         <li class="paginItem"><a href="<%=basePath%>user?func=findAllUser&currentPage=1">首页</a></li>
	         <li class="paginItem"><a href="<%=basePath%>user?func=findAllUser&currentPage=${pageTool.lastPage }">上一页</a></li>
	         <li class="paginItem"><a href="<%=basePath%>user?func=findAllUser&currentPage=${pageTool.nextPage }">下一页</a></li>
	         <li class="paginItem"><a href="<%=basePath%>user?func=findAllUser&currentPage=${pageTool.pageSize }">尾页</a></li>
	         
	        
        </ul>
    </div>
  </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<script type="text/javascript">
	  
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
			window.location="user?func=deleteById&ids="+ids;
		}
	}
	</script>
</body>
</html>
