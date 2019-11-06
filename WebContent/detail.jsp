<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <meta charset="UTF-8">
    <title>${goods.gname}详情页面</title>
    <link rel="stylesheet" href="<%=basePath %>css/index.css">
    <script src="<%=basePath %>js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	

</script>
</head>
<body>
    
    <jsp:include page="header.jsp"></jsp:include>
    
    <div class="plus5_no2 ">
        <div class="plus5_no2_in">
            <c:if test="${user == null }">
				<a class="plus5_no2_in_a"> 为方便您购买，请提前登录 </a>
				<a class="plus5_no2_in_a orange" style="color: pink;"
					href="login.jsp">立即登录</a>
				<a id="plus5_no2_in" class="plus5_no2_in_a" href="javascript:;">x</a>
			</c:if>
        </div>
    </div>
    <div class="plus5_no3">
        <div class="plus5_no3_img">
            <img width="560px" height="560px" src="<%=imgPath %>${goods.pic}" style="display: block" alt="">
        </div>
        <div class="plus5_no3_right">
            <h3>${good.gname }</h3>
            <p class="plus5_no3_right_p2">
            	<span class="plus5_no3_right_span2">${goods.description }</span>
            	<div style="font-size: 14px;">${goods.full_description }</div>
            	
            </p>
            <p class="plus5_no3_right_p3">${goods.price } 元<span class="plus5_no3_right_span3">${goods.price + 30 } 元</span></p>
            <div class="plus5_no3_right_div4">
                <div class="plus5_no3_right_div4_in">
                    <p class="plus5_no3_right_p4">赠品</p><span>小米大礼包</span>
                </div>
                <div class="plus5_no3_right_div4_in">
                    <p class="plus5_no3_right_p4">赠品</p><span>小米VR机体验</span>
                </div>


            </div>
            <div class="plus5_no3_right_div5">
                    <p >北京 北京市 东城区 永定门外街道 <span class="plus5_no3_right_div5_span">
                    <a class="plus5_no3_right_div5_span" href="">修改</a> &nbsp;&nbsp;  有现货</span></p>
            </div>
            <div class="plus5_no3_right_div6">当前版本号</div>
            <div class="plus5_no3_right_div7">
                <ul>
                   <li class="plus5_no3_right_div7_in plus5_no3_right_div7_in_hover">
                    <span class="plus5_no3_right_div7_span0 ">${goods.version }</span>
                    <span class="plus5_no3_right_div7_span " >${goods.price }元</span>
               	   </li>
                </ul>
            </div>
            <div class="plus5_no3_right_div6">选择颜色</div>
            <div class="plus5_no3_right_div9">
              <ul>
                 <li class="plus5_no3_right_div7_in plus5_no3_right_div7_in_hover ">
            		<img src="img/plus5_phone_01.png" alt=""><span>${goods.color }</span>
            	 </li>
              </ul>
            </div>
            <div class="plus5_no3_right_div10">
                <p class="plus5_no3_right_div10_p1">${goods.gname } <span class="plus5_no3_right_div10_span0">${goods.version }</span> <span class="plus5_no3_right_div10_span1"></span><span class="plus5_no3_right_div10_span2">${goods.color }</span></p><span class="plus5_no3_right_span10">${goods.price }元</span>
                <p class="plus5_no3_right_div10_p2" style="font-size: 18px;font-weight: bold;">总计 ：${goods.price } 元</p>

            </div>
            <a href="javascript:void(0)" >
              <div class="plus5_no3_right_div11" style="cursor: pointer;" onclick="addTrolley(${goods.gid})" >加入购物车
              </div>
            </a>
            <div class="plus5_no3_right_div12">
                <div class="plus5_no3_right_div12_y">√</div>
                <div class="plus5_no3_right_div12_z">七天无理由退货</div>
                <div class="plus5_no3_right_div12_y">√</div>
                <div class="plus5_no3_right_div12_z">15天质量问题换货</div>
                <div class="plus5_no3_right_div12_y">√</div>
                <div class="plus5_no3_right_div12_z">360天保障</div>
            </div>
            <a href="javascript:void (0);"></a>

        </div>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
    <script>
        var plus5_no2_in = document.getElementById("plus5_no2_in");
        var plus5_no2 = document.getElementsByClassName("plus5_no2")[0];
        plus5_no2.onclick = function () {
            plus5_no2.className="plus5_no2 plus5_no2_close";
//            plus5_no2.style.display="none";
        }
        $(".plus5_no3_right_div9 .plus5_no3_right_div7_in").click(function () {
            console.log($(this).children().eq(1).html());
            $(".plus5_no3_right_div10_span2").html($(this).children().eq(1).html());

            $(".plus5_no3_img img").css("display","none").eq($(this).index()).css("display", "block");
            $(this).siblings().removeClass("plus5_no3_right_div7_in_hover").end().addClass("plus5_no3_right_div7_in_hover");

        })
        $(".plus5_no3_right_div7 .plus5_no3_right_div7_in").click(function () {
//            var value = $(".plus5_no3_right_div7_span0 ").html();
            console.log($(this).children().eq(1).html());
            $(".plus5_no3_right_div10_span0").html($(this).children().html());
            $(".plus5_no3_right_span10").html($(this).children().eq(1).html());
            var value = "总计:"+$(this).children().eq(1).html()
            $(".plus5_no3_right_div10_p2").html(value);

            $(this).siblings().removeClass("plus5_no3_right_div7_in_hover").end().addClass("plus5_no3_right_div7_in_hover");
        })
    </script>
</body>
<script type="text/javascript">
   //为按钮设置点击事件
   function addTrolley(gid){
	   //先获取用户是否登陆
	   var user="$(sessionScope.user)";
	   if(user == null || user == ""){
		   alert("小伙子先登陆吧");
		   window.location="login.jsp";
	   }else{
		   //实现异步刷新，将商品添加到购物车
		   $.ajax({
			   url:"trolley",
			   type:"post",
			   data:{"gid":gid,"func":"addTrolley"},
			   dataType:"json",
			   success:function(isRepeat){
				   //动态修改当前用户购物车显示的购物车数量
				   if(!isRepeat){
					   //先获取显示的数量
					  var number= $("#trolleyNumber").text();
					  //将数据的量加一
					  $("#trolleyNumber").text(Number(number)+1);
				   }
			   }
		   })
	   }
   }

</script>
</html>