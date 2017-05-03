<!--注册处理-->
$(function(){
	$("#regist_button").click(function(){
	
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	$("#warning_4 span").html("");
	//获取请求参数
	var username=$("#regist_username").val().trim();
	var password=$("#regist_password").val().trim();
	var nickname=$("#nickname").val().trim();
	var repassword=$("#final_password").val().trim();
	
	//检查参数的格式
	var ok=true;
	if(username==""){
	 	ok=false;
	 	
		$("#warning_1 span").html("用户名不能为空");
		$("#warning_1").show();
	
	}
	
	if(nickname==""){
		ok=false;
		$("#warning_4 span").html("昵称不能为空");
		$("#warning_4").show();
	}
	
	 
	if(password==""){
			ok=false;
			$("#warning_2 span").html("密码不能为空");
		    $("#warning_2").show();	
	}else{
	
		if(password.length<6){
			ok=false;
			$("#warning_2 span").html("密码长度小于6位");
			$("#warning_2").show();
		}
	
	}
	if(repassword!=password){
		ok=false;
		$("#warning_3 span").html("密码不一致");
		$("#warning_3").show();
	}
	//发送ajax请求
	if(ok){
		$.ajax({
			url:"http://localhost:8888/cloud_note/user/regist.do",
			type:"post",
			data:{"username":username,
				  "password":password,
				  "nickname":nickname},
				  dataType:"json",
				  success:function(result){
				  if(result.status==0){
				  	alert(result.msg);
				  	//跳转到登录页
				  	//触发返回按钮的单击事件
				  	$("#back").click();
				  	
				  }else if(result.status==1){
				  	//用户名重复
				  	$("#warning_1 span").html(result.msg);
					$("#warning_1").show();			  
				  }
				  
				  },
				  error:function(){
				  alert("注册异常");
				  }
			
		
		});
	
	}
	
	
	});


});
