<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>
<body>
	<div class="sign_background">
		<div class="sign_background_in">
			<div class="sign_background_no1">
				<a href="index.html"><img src="img/logo.jpg" alt=""></a>
			</div>
			<div class="sign_background_no2">注册小米帐号</div>
			<div class="sign_background_no3">

				<div class="sign_background_no5">
                         <span id="msg">${msg}</span>
					<form action="user?func=registeUser" method="post" enctype="multipart/form-data">
						<table style="width: 500px;" border="0" cellspacing="0">
							<tr>
								<td width="25%" class="_left">姓名：</td>
								<td><input type="text" id="uname" name="uname"/> <span
									id="unameInfo"></span></td>
							</tr>
							<tr>
								<td width="25%" class="_left" >性别：</td>
								<td>男<input name="gender" type="radio" value="1"/> 女
								<input name="gender" type="radio"  value="0"/>
								</td>
							</tr>
							<tr>
								<td width="25%" class="_left">电话号码：</td>
								<td><input type="text" id="phone" name="phone"/> <span
									id="phoneInfo"></span></td>
							</tr>
							<tr>
								<td width="25%" class="_left">所在地区：</td>
								<td><input type="text" id="area" name="area"/><span
									id="areaInfo"></span></td>
							</tr>
							<tr>
								<td width="25%" class="_left">账号：</td>
								<td><input type="text" id="username" name="username"/><span
									id="usernameInfo"></span></td>
							</tr>
							<tr>
								<td width="25%" class="_left">密码：</td>
								<td><input type="password" style="width:220px;height:30px" id="pwd" name="password"/><span
									id="pwdInfo"></span></td>
							</tr>
							<tr>
								<td width="25%" class="_left">上传头像：</td>
								<td><input type="file" id="photo" name="photo"/><span
									id="errorInfo"></span></td>
							</tr>
						</table>
						<div class="sign_background_no6" id="btn" onclick="sub()">立即注册</div>
					</form>

				</div>
			</div>
			<div class="sign_background_no7">
				注册帐号即表示您同意并愿意遵守小米 <span>用户协议</span>和<span>隐私政策</span>
			</div>
		</div>
		<div class="sign_background_no8">
			<img src="img/sign01.jpg" alt="">
		</div>

	</div>
</body>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
//设置姓名，地址和密码的非空校验
$("#uname").blur(function(){
    //先获取值
    var uname=$(this).val();
	//如果为空，则提示姓名不能为空的错误信息提示
	if(uname == null || uname==""){
		$("#unameInfo").text("姓名不能为空！！").css("color","red");
	}else{
		$("#unameInfo").text("姓名可以使用！！").css("color","green");
	}
})
$("#area").blur(function(){
	//先获取地址的值
	var area=$(this).val();
	if(area==null || area==""){
		$("#areaInfo").text("地址不能为空").css("color","red");
	}else{
		$("#areaInfo").text("地址可以使用！！").css("color","green");
	}
})
$("#pwd").blur(function(){
	//先获取地址的值
	var pwd=$(this).val();
	if(pwd==null || pwd==""){
		$("#pwdInfo").text("密码不能为空").css("color","red");
	}else{
		$("#pwdInfo").text("密码可以使用！！！").css("color","green");
	}
})

//设置手机号的非空，正则表达式，且唯一
//先设置不能为空
$("#phone").blur(function(){
	var phone=$(this).val();
	if(phone==null || phone == ""){
		$("#phoneInfo").text("电话号码不能为空").css("color","red");
		//聚焦 并跳出
		$(this).focus();
		return;
	}
	if(!(/^1[3456789]\d{9}$/.test(phone))){
		$("#phoneInfo").text("电话号码不符合规范").css("color","red");
		$(this).focus();
	}else{
		//判断唯一性，需要与后台进行交互，判断电话号码是否已经存在，存在则不能继续注册
		$.ajax({
			url:"user",
			type:"post",
			data:{"phone":phone,"func":"checkRegiste"},
			dataType:"json",
			success:function(isRegiste){
			   if(isRegiste){
			$("#phoneInfo").text("该电话号码已被注册!").css("color","red");
			$("#phone").focus();//输入错误，重新输入
				}else{
			$("#phoneInfo").text("该电话号码可以注册").css("color","green");
				}
			}
		})
	}
})

//设置账号的非空校验和唯一性
$("#username").blur(function(){
	//先获取到username的值
	var username=$(this).val();
	//先判断是否为空
	if(username==null || username==""){
		$("#usernameInfo").text("账号不能为空").css("color","red");
				$(this).focus();
	}else{
		//进行唯一值校验
		$.ajax({
			url:"user",
			type:"post",
			data:{"username":username,"func":"checkUsername"},
			dataType:"json",
			success:function(isSuccess){
			   if(isSuccess){
			$("#usernameInfo").text("账号已经存在，请重新注册").css("color","red");
			$("#phone").focus();//输入错误，重新输入
				}else{
			$("#usernameInfo").text("该账号可以注册").css("color","green");
				}
			}
		})
	}
})
function sub(){
	$("form").submit();
}
</script>
</html>