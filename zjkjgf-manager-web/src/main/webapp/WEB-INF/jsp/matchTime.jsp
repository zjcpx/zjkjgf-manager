<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="../css/jquery.datetimepicker.css"/>
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="../js/angular.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/jquery.datetimepicker.js" type="text/javascript" charset="utf-8"></script>
		<h2>活动开始时间：</h2>
		<input type="text" id="startTime" /><br><br>
		<h2>活动结束时间：</h2>
		<input type="text" id="endTime" /><br><br>
		<input type="button" id = 'reset' value="重置">
		<input type="button" id = 'submit' value="设定">
		<script type="text/javascript">			
			$('#startTime').datetimepicker({
				lang:'ch',
				format:'Y-m-d H:m:00',
			}); 
			$('#endTime').datetimepicker({
				lang:'ch',
 				format:'Y-m-d H:m:00',
			});
			$('#reset').click(function(){
				$('#startTime').val('');
				$('#endTime').val('');
			});
			$('#submit').click(function(){
				var startTime = $('#startTime').val();
				var endTime = $('#endTime').val();
				if (startTime == '' || endTime == ''){
					alert('您还没有完整设定比赛时间，请检查');
					
					return;
				}else{
					var d1 = new Date(startTime.replace(/\-/g, "\/"));  
 					var d2 = new Date(endTime.replace(/\-/g, "\/"));
					if(d1 > d2){
						alert("开始时间不能晚于结束时间");
						return;
					};
					
				}
				$.ajax({
                type:'get',
                url:'/time/set',
                data:{startTime:startTime,endTime:endTime},
                dataType:'json',
                // TaotaoResult.build(status, msg)
                success:function(data){
                   if (data.status == 200){
                   	alert(data.msg);
                   } 
                }
               
            });
			})
		</script>
	