<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
 
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
 
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	    	<tr>
	        	<td>ID:</td>
	            <td><input id = "id" class="easyui-textbox" type="text" name="id" data-options="editable:false,required:true" style="width: 280px;"></input></td>
	        </tr>
	    	<tr>
	        	<td>评分标准:</td>
	            <td><input id = "name" class="easyui-textbox" type="text" name="name" data-options="events:{blur:checkLoginName},required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	        	<td>最高得分:</td>
	            <td><input id = "maxgrand" class="easyui-numberbox" type="text" name="maxgrand" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>备注:</td>
	            <td><input class="easyui-textbox" type="text" name="memo" style="width: 280px;"/></td>
	        </tr>
	        
	    </table>
	
	
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	var itemAddEditor ;
	
	//页面初始化完毕后执行此方法
	$(function(){
		var rol = $("input[name='role']").val();
	/*  $("#roles").find("option[value = '"+rol+"']").attr("selected","selected");
		console.log($("#roles").find("option[value = '"+rol+"']")); */
 		$('#roles').change(function (e) {
			var roleValue = $("#roles").find("option:selected").val();
			$("input[name='role']").val(roleValue);
			
		    });
	});
	
	function checkLoginName(){
		
		$.ajax({
            type:'post',
            url:'/standard/nameCheck',
            data:{standardName:this.value},
            dataType:'json',
            success:function(data){
               if (data.status == 500){
               	alert(data.msg);
               	$("#name").textbox().next('span').find('input').focus();
               	$("#name").textbox('setValue','') ;
               } 
            }
           
        });
	}
	
	//提交表单
	function submitForm(){
		//有效性验证
		
		if(!$('#itemAddForm').form('validate')){
			
			$.messager.alert('提示','表单还未填写完成!');
			
			return ;
		}
		$.post("/standard/update",$("#itemAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','评分标准添加成功!');
				$('#itemEditWindow').window('close');
				$("#itemList").datagrid("reload");
				
			}
		}); 
		
		
	}	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>
