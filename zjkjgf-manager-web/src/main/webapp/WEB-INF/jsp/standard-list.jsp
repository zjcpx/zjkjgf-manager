<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="评分标准列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/standard/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:260">ID</th>
            <th data-options="field:'name',width:200">评分标准</th>
			<th data-options="field:'maxgrand',width:200">最高得分</th>
            <th data-options="field:'memo',width:100">备注</th>
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
        	    href:'standard-edit',
        	    title:"新增比赛项目"
        	});
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个评分标准才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个评分标准!');
        		return ;
        	}
        	
        	$('#itemEditWindow').window({    
        	    width:400,    
        	    height:280,    
        	    modal:true,   
        	    href:'standard-edit',
        	    title:"编辑评分标准信息",
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
        		$.messager.alert('提示','未选中评分标准!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能删除一个评分标准!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的评分标准吗？',function(r){
        	    if (r){
        	    	var params = {"id":ids};
                	$.post("/standard/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除评分标准成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>