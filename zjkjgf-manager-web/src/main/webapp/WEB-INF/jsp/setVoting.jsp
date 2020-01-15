<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<p>普通用户有<input class="easyui-numberbox" type="text" name="" id="nomalVoting">票<span style="margin-left:20px"></span ><input id = 'btn1' class="easyui-linkbutton" type="button" value="设置"></p>
<p>特约专家有<input class="easyui-numberbox" type="text" name="" id="specialistVoting">票<span style="margin-left:20px"></span><input id = 'btn2' class="easyui-linkbutton" type="button" value="设置"></p>
<script>
	$('#btn1').click(function(){
		
		$.ajax({
            type:'get',
            url:'/user/changeVotingPower',
            data:{role:0,votingPower:$('#nomalVoting').textbox('getValue')},
            dataType:'json',
            success:function(data){
               if (data.status == 200){
               	alert('普通用户投票权更新成功');
               
               } 
            }
           
        });
	})  
	$('#btn2').click(function(){
		
		$.ajax({
            type:'get',
            url:'/user/changeVotingPower',
            data:{role:1,votingPower:$('#specialistVoting').textbox('getValue')},
            dataType:'json',
            success:function(data){
               if (data.status == 200){
               	alert("专家投票权更新成功") ;
               } 
            }
           
        });
	})  
</script>