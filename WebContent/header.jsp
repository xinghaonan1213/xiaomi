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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script src="<%=basePath %>js/jquery-3.3.1.js"></script>
</head>
<body>

<div class="box">
      <div class="inner whiteGL">
          <div class="left">
              <a class="mix" href="">仿小米商城-学习专用</a>
          </div>
          <div class="right">
              <c:if test="${user == null }">
                  <a class="mix" href="login.jsp">登录</a>
                  <a href="register.jsp">注册</a>
              </c:if>
              
               <c:if test="${user != null }">
                   <span style="color:white;" class="mix">欢迎用户${user.uname }来到商城</span>
               </c:if>
              <a class="max"  href="">消息通知</a>
              <a href="javascript:void(0)" onclick="findAllTrolley()" >
                                        购物车(<span style="color:white;" id="trolleyNumber">0</span>)
              </a>
          </div>
      </div>
  </div>
  <div class="logo">
      <div class="logo_left">
          <div>
              <a href="javascript:void(0);" title="小米官网"><img class="xiaomi" src="img/logo.jpg"></a>
          </div>
      </div>
      <ul class="logo_center orangeGL">
          <a href="">小米手机</a>
          <a href="">红米</a>
          <a href="">笔记本</a>
          <a href="">电视</a>
          <a href="">盒子</a>
          <a href="">新品</a>
          <a href="">路由器</a>
          <a href="">智能硬件</a>
          <a href="">服务</a>
          <a href="">社区</a>
      </ul>
      <form class="logo_right">
         <div class="logo_input"><input type="text" id="search">
         </div>
          <a class="logo_right_a">
          <img src="img/find.jpg"></a>
      </form>
  </div>
</body>

<!-- 给按钮设置点击事件 -->
<script type="text/javascript">
   $(".logo_right_a").click(function (){
	   //获取输入框的数据
	   var search=$("#search").val();
	   //异步刷新，将数据传输后台
	   $.ajax({
		   url:"index",
		   type:"post",
		   data:{"func":"searchInfo","search":search},
		   dataType:"json",
		   success:function(obj){
			   alert("添加cookie成功");
		   }
	   })
   })
   //一被加载的瞬间，强行执行js脚本，发送ajax请求，
   //来得到当前用户的购物车数量，显示在页面中！
   var user="${sessionScope.user}";
   if(user !=null && user != ""){
	   $.ajax({
		   url:"trolley",
		   type:"post",
		   data:{"func":"selectTrolleyCount"},
		   dataType:"text",
		   success:function(count){
			   $("#trolleyNumber").text(count);
		   }
	   })
   }
   
   //给购物车添加点击事件，先查询出购物车的数据，再跳转到购物车界面
   function findAllTrolley(){
	   //不需要返回成功之后的信息，故不需要使用ajax，直接查询出来跳转界面
	   //先校验是否登陆
	   var user="${sessionScope.user}";
	   if(user != null && user != ""){
		   //跳转界面
		   window.location="trolley?func=findAllTrolley";
	   }else{
		   alert("小伙子请先登陆吧");
		   window.location="login.jsp";
	   }
   }
</script>
</html>





