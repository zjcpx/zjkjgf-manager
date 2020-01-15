<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
 
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
 
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	    	<tr>
	    		<td>用户ID</td>
	    		<td><input class = "easyui-textbox" type = "text" name = 'id' data-options="editable:false"/></td>
	    	</tr>
	    	<tr>
	        	<td>用户名:</td>
	            <td><input id = "userName" class="easyui-textbox" type="text" name="username" data-options="events:{blur:checkLoginName},editable:false" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>密码:</td>
	            <td><input class="easyui-textbox" type = 'password' name="password" data-options="editable:false" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>昵称:</td>
	            <td><input class="easyui-textbox" type="text" name="nickname,editable:false" /></td>
	        </tr>
	        <tr>
	            <td>投票权</td>
	            <td><input class="easyui-numberbox" type="text" name="votingpower" data-options="editable:false"/></td>
	        </tr>
	        <tr>
	            <td>角色:</td>
	            <td><select id="roles">
				    	<option value="1">特约专家</option>
				    	<option value="0">普通用户</option>
				    	<option value="2">管理员</option>
					</select>
				</td>

	        </tr>
	    </table>
	<input id = 'r' type="hidden" name="role" value='2'/>
	
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
            url:'/user/usernameCheck',
            data:{userName:this.value},
            dataType:'json',
            success:function(data){
               if (data.status == 500){
               	alert(data.msg);
               	$("#userName").textbox().next('span').find('input').focus();
               	$("#userName").textbox('setValue','') ;
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
		
		$.post("/user/changeInfo",$("#itemAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','用户信息修改成功!');
				$('#itemEditWindow').window('close');
				$("#itemList").datagrid("reload");
				
			}
		}); 
		
	}
	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}
	
	//扩展jquery的格式化方法
    (function($){
        $.fn.parseForm=function(){
            var serializeObj={};
            var array=this.serializeArray();
            var str=this.serialize();
            $(array).each(function(){
                if(serializeObj[this.name]){
                    if($.isArray(serializeObj[this.name])){
                        serializeObj[this.name].push(this.value);
                    }else{
                        serializeObj[this.name]=[serializeObj[this.name],this.value];
                    }
                }else{
                    serializeObj[this.name]=this.value; 
                }
            });
            return serializeObj;
        };
    })(jQuery);

</script>
