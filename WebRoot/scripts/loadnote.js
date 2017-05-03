function loadnote(){
	
	//将笔记列表中所有的选中的笔记状态取消
	$("#note_list li a").removeClass("checked");
	//设置当前单击的笔记处于选中状态
	$(this).find("a").addClass("checked");
	//获取提交的数据
	var noteId=$(this).data("noteId");
	//发送ajax请求
	$.ajax({
		url:"http://localhost:8888/cloud_note/note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var note=result.data;
				var noteTitle=note.cn_note_title;//标题
				var noteBody=note.cn_note_body;//内容
				//设置标题
				$("#input_note_title").val(noteTitle);
				um.setContent(noteBody);	
				
			}
		},
		error:function(){
			alert("加载笔记信息异常");
		}
	});

	
}
function sureAddNote(){
	
		//获取请求数据
		var noteTitle=$("#input_note").val().trim();
		//获取当前被选中的笔记本列表中的li
		var $bookli=$("#book_list li a.checked").parent();
		var bookId=$bookli.data("bookId");
		//监测数据格式
		$.ajax({
			url:"http://localhost:8888/cloud_note/note/add.do",
			type:"post",
			data:{"bookId":bookId,"noteTitle":noteTitle,"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//拼一个li
					var sli=	
						'<li class="online">'+
					'<a >'+
					'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
				'</a>'+
				'<div class="note_menu" tabindex="-1">'+
					'<dl>'+
						'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
						'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
						'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
					'</dl>'+
				'</div>'+
			'</li>';
			//把noteId绑定到笔记li上
			var $li=$(sli);
			$li.data("noteId",result.data);
			//将li添加到ul中
			$("#note_list").append($li);
			closeWindow();
			//弹出提示框
			alert(result.msg);
			//将笔记列表中选中的li取消选中
			$("#note_list li a").removeClass("checked");
			//将新建的笔记处于选中状态
			$("#note_list li:last a").addClass("checked");
			//设置标题
			$("#input_note_title").val(noteTitle);
				}
				
			},
			error:function(){
				alert("创建笔记异常");
			}
		});
	
	
}
function updateNote(){
	//判断笔记有没有被选中
	var $a=$("#note_list li a.checked");
	if($a.length==0){
		alert("请选择笔记");
	}else{
		//获取请求参数
		var noteId=$a.parent().data("noteId");
		var noteTitle=$("#input_note_title").val().trim();
		var noteBody=um.getContent();
		//检测数据格式 自写
		//发送请求
		$.ajax({
			url:"http://localhost:8888/cloud_note/note/update.do",
			type:"post",
			data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//更新笔记列表选中的笔记标题
					//拼接一个笔记列表li中的a，然后替换
					var str=
						'<a >'+
						'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
					'</a>';
					//替换
					$a.html(str);
					
					alert(result.msg);
				}
			},
			error:function(){
				alert("保存笔记失败");
			},
			
		});
		
	}
	
	
}
function showNoteMenu(){
	//获取当前选中的笔记li
	var $li=$(this).parents("li");
	//将当前li的菜单显示
	var $menu=$li.find(".note_menu");
	//$menu.show();
	if($menu.is(":hidden")){
		//显示当前菜单前所有的都隐藏
		$("#note_list .note_menu").hide();
		//如果隐藏就显示
		//下拉动画显示当前菜单一秒
		$menu.slideDown(1000);
	}else{
		$menu.slideUp(1000);
	}
}
//回收笔记
function recycleNote(){
	//获取请求参数noteId
	var $li=$("#note_list li a.checked").parent();
	var noteId=$li.data("noteId");
	$.ajax({
		url:path+"/note/recycle.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			//删除笔记列表
			$li.remove();
		//	$li.setContent("");
			$("#input_note_title").val("");
			//关闭确认对话框
			closeWindow();
			//弹出提示信息
			alert(result.msg);
		},
		error:function(){
			
			alert("删除笔记异常");
		}
		
	});
	
}

function shareNote(){
	//获取请求参数noteId
	//var $li=$(this).parents("li");
	var $li=$("#note_list li a.checked").parent();
	var noteId=$li.data("noteId");
	//alert(noteId);
	//发送ajax请求			
	$.ajax({
		url:path+"/note/share.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
			//关闭确认对话框
			closeWindow();
			//弹出提示信息
			alert(result.msg);
			}else if(result.status==1){
				//关闭确认对话框
				closeWindow();
				//弹出提示信息
				alert(result.msg);	
			}
		},
		error:function(){
			
			alert("分享笔记异常");
		}
		
	});

}
function sureMoveNote(){
	
	//获取请求数据
	//var noteTitle=$("#input_note").val().trim();
	//获取当前被选中的笔记本列表中的li
	//var $bookli=$("#book_list li a.checked").parent();
	var bookId=$("#moveSelect option:selected").val();
	if(bookId==""){
		//关闭转移对话框
		closeWindow();
		//弹出提示
		alert("请选择下拉选项中的笔记本");
	}else{	 
		var $a=$("#note_list li a.checked").parent();
		var noteId=$a.data("noteId");
		
	$.ajax({
		url:"http://localhost:8888/cloud_note/note/move.do",
		type:"post",
		data:{"noteId":noteId,"bookId":bookId},
		dataType:"json",
		success:function(result){
				$a.empty();
				
		closeWindow();
		
		//弹出提示框
		alert(result.msg);
			},
		error:function(){
			alert("移动笔记异常");
		}
	});
	}
}
//搜索分享笔记
function searchShareNotes(event){
	var code=event.keyCode;
	if(code==13){//回车键
		//切换中间列表区显示
		$("#pc_part_6").show();//搜索结果列表
		$("#pc_part_5").show();//预览笔记区
		$("#pc_part_2").hide();//全部笔记列表
		$("#pc_part_3").hide();//编辑笔记区
		$("#pc_part_4").hide();//回收站列表
		$("#pc_part_7").hide();//收藏列表
		$("#pc_part_8").hide();//活动列表
		
		//获取请求参数
		var keyword=$("#search_note").val().trim();
		//发送ajax请求
		$.ajax({
			url:path+"/note/search.do",
			type:"post",
			data:{"keyword":keyword},
			dataType:"json",
			success:function(result){
				var notes=result.data;
				$("#search_list").empty();//生成列表之前清空
				for(var i=0;i<notes.length;i++){
					var noteTitle=notes[i].cn_share_title;
					var id=notes[i].cn_share_id;
					//拼一个li
					var sli=
						'<li class="online">'+
						'<a >'+
						'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-star"></i></button>'+
					'</a>'+
				'</li>';
						//把shareId绑定到笔记li上
						var $li=$(sli);
						$li.data("shareId",id);
						$("#search_list").append($li);
					
				}
				//
				//将笔记列表中所有的选中的笔记状态取消
				//获取提交的数据
				
				
				//alert(shareId);
			},
			error:function(){
				alert("搜索失败");
			},
			
			
		});
	}
	
	
}

