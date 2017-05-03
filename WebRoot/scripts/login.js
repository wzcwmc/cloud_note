

  $(function(){
  	$("#login").click(function(){
  		//获取请求提交的数据
  		var ok=true;
  		var name=$("#count").val().trim();
  		var password=$("#password").val().trim();
  		//检测一下数据格式
  		//清空文本框后的span内容
  		$("#count_span").html("");
  		$("#password_span").html();
  		if(name==""){
  		ok=false;
  		  $("#count_span").html("用户名为空");
  		}
  		if(password==""){
  		ok=false;
  			$("#password_span").html("密码为空");
  		}
  		//发送Ajax请求
  		if(ok){
  			$.ajax({
  				url:"http://localhost:8888/cloud_note/user/login.do",
  				type:"post",
  				data:{"username":name,"password":password},
  				dataType:"json",
  				success:function(result){
  				//成功处理
  				if(result.status==0){
  					//获取用户的id 写入cookie
  					var userId=result.data;
  					addCookie("userId",userId,2);
  					
  					//成功跳转到编辑页面
  				window.location.href="edit.html";
  				
  				}else if(result.status==1){
  				//用户名错误
  				$("count_span").html(result.msg);
  				}else if(result.status==2){
  				$("count_span").html(result.msg);
  				}
  				},
  				error:function(){
  				//异常处理
  				alert("登录异常");
  				}
  			
  			});
  		}
  	
  	});
  
  });




