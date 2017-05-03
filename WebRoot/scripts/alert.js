function alertAddBookWindow(){
	//0.弹出背景色
	$(".opacity_bg").show();
	//1.弹出对话框，并加载到id=can的div里面
	$("#can").load("http://localhost:8888/cloud_note/alert/alert_notebook.html");

}
function closeWindow(){
	
	//隐藏背景色
	$(".opacity_bg").hide();
	//清空对话框
	$("#can").empty();
}

function alertAddNoteWindow(){
	//检测是否选中笔记本
//	$(":checked")选择器所选取的所有被被选中的复选框或单选按钮
//所有book_list li a 下的选中的class

var $a=$("#book_list li a.checked");//获取选中的a
if($a.length==0){//未选中提示
	alert("请先选择笔记本");
}else{
	
	//0.弹出背景色
	$(".opacity_bg").show();
	//1.弹出对话框，并加载到id=can的div里面
	$("#can").load("http://localhost:8888/cloud_note/alert/alert_note.html");

}
	
	
}

function alertRecycleWindow(){
	//0.弹出背景色
	$(".opacity_bg").show();
	//1.弹出对话框，并加载到id=can的div里面
	$("#can").load("http://localhost:8888/cloud_note/alert/alert_delete_note.html");

}
function alertShareWindow(){
	//0.弹出背景色
	$(".opacity_bg").show();
	//1.弹出对话框，并加载到id=can的div里面
	$("#can").load("http://localhost:8888/cloud_note/alert/alert_share_note.html");

}
function alertMoveNoteWindow(){
	
	//0.弹出背景色
	$(".opacity_bg").show();
	//1.弹出对话框，并加载到id=can的div里面
	var url=path+"/alert/alert_move.html";
	//加回调函数这样就可以取到数据了
	$("#can").load(url,function(){
		//获取笔记信息填充到下拉选项
		var bookLis=$("#book_list li");//获取所有笔记本信息
		for(var i=0;i<bookLis.length;i++){
			var $li=$(bookLis[i]);
			var bookId=$li.data("bookId");
			var bookName=$li.text().trim();
			//拼一个option
			var opt="<option value='"+bookId+"'>"+bookName+"</option>";
			//放入select
			$("#moveSelect").append(opt);
		}
		
	});
		
		
	
	
}