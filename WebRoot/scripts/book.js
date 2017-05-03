function loadBooks(){
	$.ajax({
					url:"http://localhost:8888/cloud_note/notebook/loadbooks.do",
					type:"post",
					data:{"userId":userId},
					dataType:"json",
					success:function(result){
						if(result.status==0){
							//笔记本json对象数组
							var books=result.data;
							//清除原有笔记本列表
							$("#book_list").empty();
							//循环生成笔记本列表li
							for(var i=0;i<books.length;i++){
								var bookName=books[i].cn_notebook_name;
								var bookId=books[i].cn_notebook_id;
								var sli=
									'<li class="online">'+
								'<a>'+
								'<i class="fa fa-book" title="online" rel="tooltip-bottom">'+
								'</i>'+bookName+'</a></li>';
								var $li=$(sli);//将sli字符串转成jquery对象
								//将bookId绑定到$li上,也可以在li上加id属性
								$li.data("bookId",bookId);
								//将$li添加到ul上
								$("#book_list").append($li);
							}
							
						}
						
					},
					error:function(){
						alert("笔记本加载异常");	
					}
				});
				
}		

function loadnotes(){
	//切换中间列表区显示
	$("#pc_part_2").show();//搜索结果列表
	$("#pc_part_3").show();//预览笔记区
	$("#pc_part_4").hide();//全部笔记列表
	$("#pc_part_5").hide();//编辑笔记区
	$("#pc_part_6").hide();//回收站列表
	$("#pc_part_7").hide();//收藏列表
	$("#pc_part_8").hide();//活动列
	$("#input_note_title").val("");
	um.setContent("");
//获取请求参数,当前选中笔记本的id
//$(this)当前被点中的li
var bookId=$(this).data("bookId");
//将当前点中的笔记本设置为选中状态
//增加样式前，将以前选中的li的选中状态取消
$("#book_list li a").removeClass("checked");
$(this).find("a").addClass("checked");
//发送ajax请求
$.ajax({
	url:"http://localhost:8888/cloud_note/note/loadnotes.do",
	type:"post",
	data:{"bookId":bookId},
	dataType:"json",
	success:function(result){
		if(result.status==0){
			//获取笔记对象json数组
			var notes=result.data;
			//清除原有的笔记
			$("#note_list").empty();
			//循环生成li
			for(var i=0;i<notes.length;i++){
				var noteTitle=notes[i].cn_note_title;
				var noteId=notes[i].cn_note_id;
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
		$li.data("noteId",noteId);
		//将li添加到ul中
		$("#note_list").append($li);
			}
		}
		
	},
	error:function(){
		alert("加载笔记列表出现异常");
	}
	
	
});
	
}


function sureAddBook(){
	//获取请求参数
	var bookName=$("#input_notebook").val().trim();
	//发送ajax请求
	$.ajax({
		url:"http://localhost:8888/cloud_note/notebook/add.do",
		type:"post",
		data:{"bookName":bookName,"userId":userId},
		dataType:"json",
		success:function(result){
			
			if(result.status==0){
				var bookId=result.data;
				//拼一个li
				var sli=
					'<li class="online">'+
					'<a>'+
					'<i class="fa fa-book" title="online" rel="tooltip-bottom">'+
					'</i>'+bookName+'</a></li>';
					var $li=$(sli);//将sli字符串转成jquery对象
					//将bookId绑定到$li上,也可以在li上加id属性
					$li.data("bookId",bookId);
					//将$li添加到ul上
					$("#book_list").append($li);
					//关闭对话框
					closeWindow();
					//弹出成功的提示
					alert(result.msg);
					//将新建的笔记本设置为选中
					$("#book_list li a").removeClass("checked");
					$("#book_list li:last a").addClass("checked");
			}
		},
		error:function(){
			alert("创建笔记本异常");
		}
		
	});
}