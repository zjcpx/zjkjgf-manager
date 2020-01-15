<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="人员列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/user/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:150">ID</th>
            <th data-options="field:'username',width:200">用户名</th>
            <th data-options="field:'nickname',width:100">昵称</th>
            <th data-options="field:'role',width:100">角色</th>
            <th data-options="field:'votingpower',width:70,align:'right'">投票权</th>
            <th data-options="field:'votingrecoder',width:200">投票纪录</th>
            <th data-options="field:'createtime',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
            <th data-options="field:'modifytime',width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#itemList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$('#itemEditWindow').window({    
        	    width:400,    
        	    height:280,    
        	    modal:true,   
        	    href:'user-add',
        	    title:"新增用户" 
        	});  

          /*  	$('#itemEditWindow').window("open"); */
            
        }
    }, {
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
			var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个用户才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次只能选择一个用户!');
        		return ;
        	}
        	$('#itemEditWindow').window({    
        	    width:400,    
        	    height:280,    
        	    modal:true,   
        	    href:'user-edit',
        	    title:"编辑用户信息",
        	    onLoad :function(){
        			//回显数据
        			var data = $("#itemList").datagrid("getSelections")[0];
        			$("#itemAddForm").form("load",data);
        	    }
        	});
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的用户吗？',function(r){
        	    if (r){
        	    	var params = {"id":ids};
                	$.post("/user/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除用户成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>