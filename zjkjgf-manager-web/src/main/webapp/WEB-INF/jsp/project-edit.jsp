<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
 
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
 
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	<input id = "id" class="easyui-textbox" type="hidden" name="id"></input>
	    <table cellpadding="5">
	    	
	        <tr>
	        	<td>部门名称:</td>
	            <td><input id = " depname" class="easyui-textbox" type="text" name="depname" data-options="events:{blur:checkLoginName},required:true" style="width: 280px;"></input></td>
	        </tr>
	    	<tr>
	        	<td>项目名称:</td>
	            <td><input id = "projectname" class="easyui-textbox" type="text" name="projectname" data-options="events:{blur:checkLoginName},required:true" style="width: 280px;"></input></td>
	        </tr>
	        
	        <tr>
	            <td>备注:</td>
	            <td><input class="easyui-textbox" type="text" name="memo" /></td>
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
            url:'/user/projectNameCheck',
            data:{projectName:this.value},
            dataType:'json',
            success:function(data){
               if (data.status == 500){
               	alert(data.msg);
               	$("#projectname").textbox().next('span').find('input').focus();
               	$("#projectname").textbox('setValue','') ;
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
		
		$.post("/project/update",$("#itemAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','比赛项目编辑成功!');
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
