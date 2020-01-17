<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
	<link rel="stylesheet" href="../css/style.css">
<title>心海报关创新大赛评分系统</title>

</head>
<body>

	<!-- 登录窗口 -->
	<div class="container">
		<div class="row">
			<div class="col-xs-4"></div>
			<div id="login" class="col-xs-4">
				<h3 class = "page-header" style="text-align: center;">请登录</h3>
				<p>用户名<input class="form-control" type="text" name="username" id="loginName" placeholder = "请输入用户名"></p>
				<p>密码：<input class="form-control" type="password" name="password" id="password" placeholder = "请输入密码"></p>
				<input class="btn-primary btn-lg" id = "logBtn" type="button" value="登录">
			</div>
			<div class="col-xs-4"></div>      
		</div>
	</div>	
	<!-- 人气评分窗口 -->
	<div id="hot" class = 'container' >
		<div class="row">
			<h3 class="col-xs-12 page-header" style="text-align: center;">请选择您喜欢的比赛项目</h3>	
			<div id="hot_match" class="col-xs-12">
			</div>	
		</div>				
	</div>

	<!-- 专家评分窗口 -->
	<div id="specalistMatch" class="container">
		<div class="row">
			<h3 class="col-xs-12 page-header" style="text-align: center;">请选择每个项目打分</h3>	
			<h5>请点您要评分的项目：</h5>		
			<div id ="special_match">				
			</div>				
		</div>							
	</div>
	<!-- 结果窗口 -->
	<div id="result" class="container">
		<div id="content">
			
		</div>
	</div>
	<!-- 人气投票浮动区 -->
	<div id="hotVotbtn" class="col-xs-12">
		<div id="hotVotingPower">
			<h4 class="col-xs-12 page-header" style="padding-left: 10px">您现在拥有的票数：</h4>
			<div class = 'col-xs-12' id = "votingNombers"></div></h4>
		</div>
		<p style="text-align: center;">
			<input class="btn-primary btn-lg" id = "hotVotBtn" type="button" value="投票">
			<input class="btn-primary btn-lg toResult" type="button" value="看结果">
			<input class="btn-primary btn-lg" id = "retVotBtn" type="button" value="关闭投票区">
		</p>
	</div>
	<!-- 专家投票浮动区 -->
	<!-- <div class="col-xs-12" id="specalistVotbtn">
		<p style="text-align: center;">
			<input class="btn-primary btn-lg" id = "specialVotBtn" type="button" value="投票">
			<input class="btn-primary btn-lg toResult" type="button" value="看结果">
			<input class="btn-primary btn-lg" id = "closeVoting" type="button" value="关闭投票区">
	
		</p>
	</div>			 -->

 <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>    
<script type="text/javascript">
	//用户对象
	var userObj ={};
	//比赛项目对象
	var projectList = {};
	//项目列表HTML
	var projectdiv = '';
	//人气评分区域的HTML
	var projectValueDiv = '';
	
	//已经投的票
	var vottedRecoder ='';
	//根据数据库数据生成的投票纪录的Map对象
	var vottedRecordMap = {};

	//专家投票纪录Map
	var specialRecordMap = {};
	var standardList = {};

	//专家基金
	var specialistfund = 0;
	//登录
	$(function(){
		/*根据后台设定的比赛时间，确定比赛是否开始*/
		if(!isEffective()){
			alert("大赛还未开始，请稍候");
		}else{
			$("#login").show('slow');

			//显示的div居中
			makeMiddle($('#login')[0]);
		}
		

		//显示的div居中
		/*makeMiddle($('#login')[0]);*/

		//获取比赛项目列表
		getProjectList();

		//获取专家评分标准
		getStandard();

		//登录处理，成功：返回用户对象；失败：返回false
		$("#logBtn").bind("click",function(){ 
			var loginName = $("#loginName").val();
			var password = $("#password").val();
			$.ajax({
            type:'post',
	            url:'/user/login',
	            data:{username:loginName,password:password},
	            dataType:'json',
	            async: false,
	            success:function(data){
					if(data.status == 200){
						//获取用户对象
						if(data.data){
							userObj = data.data[0];	
							//隐藏登录窗口
							$('#login').hide();	
						}else{
							alert('请确认用户名和密码');
							return;
						}
						
					}
				}
			});
			//获取已投记录
			vottedRecoder = userObj.votingrecoder;
			//初始化纪录的Map对象
			changeVottedToMap(vottedRecoder);
			//根据用户角色，显示不同比赛评分页面
			if (userObj.role == 0){//普通用户；
				$('#hot').show('slow',showHotMatch);
			}else if(userObj.role == 1){//专家用户，打开专家投票DIV
				specialistfund = userObj.votingpower;
				/*$("#specalistvotingNombers")[0].innerHTML = specialistfund;*/
				$('#specalistMatch').show('slow',showspecalistMatch);
			}
		})

		//人气投票
		$('#hotVotBtn').bind('click',function(event) {
			var currentVottingRecord = '';
			for (var i = 0; i < projectList.length;i++){
				var projectName = projectList[i].projectname
				var vottedTickets = $("input[name ="+projectName+"]")[0].value;
				currentVottingRecord+= projectName+': ['+vottedTickets+']/';
			}
			currentVottingRecord = currentVottingRecord.substring(0,currentVottingRecord.lastIndexOf('/'));
			console.log(currentVottingRecord);
			//调用接口将用户当前的投票情况更新到数据库
			$.ajax({
            type:'post',
	            url:'/user/changeVottingInfo',
	            //Long id,String votingRecoder, int vottingPower
	            data:{id:userObj.id,votingRecoder:currentVottingRecord,vottingPower:userObj.votingpower},
	            dataType:'json',
	            async: false,
	            success:function(data){
					if(data.status == 200){
						//获取用户对象	
					}
				}
			});

			//2、将投票结果写入成绩表 
			$.ajax({
            type:'post',
	            url:'/scores/createHote',
	            //Long id,String votingRecoder, int vottingPower
	            data:{vottingperson:userObj.username,projectMap:currentVottingRecord},
	            dataType:'json',
	            async: false,
	            success:function(data){
					if(data.status == 200){
					$('#hotVotbtn').hide();
							alert("投票成功");
						
						
					}
				}
			});
		});
		//专家投票
/*		$('#specialVotBtn').bind('click', function(event) {
			return
			//封装专家投票数据
			var specialVottedRecorder = '';
			var targetObj = $('.special_match_project');
			var len = targetObj.length;
			for(var i = 0; i< len; i++){
				var name = targetObj[i].innerHTML;
				name = name.replace('<p>','');
				name = name.replace('</p>','');
				specialVottedRecorder += name+': [';
				var targetObj2 = targetObj[i].parentNode.nextElementSibling.children[0].children;
				var len2 = targetObj2.length;
				for (var j = 0; j < len2;j++){
					specialVottedRecorder += targetObj2[j].children[2].value +',';					
				}
				specialVottedRecorder = specialVottedRecorder.substring(0,specialVottedRecorder.lastIndexOf(','));
				specialVottedRecorder += ']/';
			}
			specialVottedRecorder = specialVottedRecorder.substring(0,specialVottedRecorder.lastIndexOf('/'));
			//console.dir(specialVottedRecorder);

			//1将投票纪录写入用户数据库
			$.ajax({
            type:'post',
	            url:'/user/changeVottingInfo',
	            //Long id,String votingRecoder, int vottingPower
	            data:{id:userObj.id,votingRecoder:specialVottedRecorder,vottingPower:userObj.votingpower},
	            dataType:'json',
	            async: false,
	            success:function(data){
					if(data.status == 200){
						//获取用户对象

						
					}
				}
			});

			//将成绩写入成绩表
			$.ajax({
            type:'post',
	            url:'/scores/createSpecial',
	            //Long id,String votingRecoder, int vottingPower
	            data:{vottingperson:userObj.username,specialRecorder:specialVottedRecorder},
	            dataType:'json',
	            async: false,
	            success:function(data){
					if(data.status == 200){
					$('#hotVotbtn').hide();
							alert("投票成功");
						
						
					}
				}
			});

		});*/
		/*//隐藏投票区
		$("#retVotBtn").bind('click', function(){
			$('#hotVotbtn').hide('slow');
		})
		//隐藏投票区
		$("#closeVoting").bind('click', function(){
			$('#specalistVotbtn').hide('slow');
		})*/

		/*//打开结果区
		$(".toResult").bind('click', function(event) {
			$('#hotVotbtn').hide('slow');
			$('#specalistVotbtn').hide('slow');
			$('#hotVotbtn').hide();
			$('#specalistMatch').hide();
			$('#hot').hide();
			$('#result').show('slow',showResult);
		});*/

		
		/*$('#retVoting').bind('click', function(event) {
			
			
		});*/
	})
		//返回专家投票区
		function showSpeialVotting(e,element){
			$('#result').hide();
			$('#specalistMatch').show();
		}
	//设置登录Div以及登录按钮居中
	function makeMiddle(element){

		var windowWidth = document.body.clientWidth;

		var divWidth = $('#login')[0].clientWidth;
		element.style.left = ((windowWidth - 240) / 2) + 'px';
		//登录按钮剧中，距底部15px
		var btnHeight = $('#logBtn')[0].offsetHeight;
		$('#logBtn')[0].style.width = 220+'px';
		$('#logBtn')[0].style.left = 10+'px';
		$('#logBtn')[0].style.top = (300 - btnHeight - 10)+'px';
	}
	//显示结果
	function showResult(){
		$('#hotVotbtn').hide('slow');
		/*$('#specalistVotbtn').hide('slow');*/
		$('#hotVotbtn').hide();
	
		
		$('#specalistMatch').hide();
		$('#hot').hide();
		$('#result').show('slow');
		//封装项目列表
		var projectNameList = '';
		for(var i = 0; i < projectList.length; i++){
			projectNameList += projectList[i].projectname+',';
		}
		projectNameList = projectNameList.substring(0,projectNameList.lastIndexOf(','));
		var specialresultList = [];
		$.ajax({
            type:'post',
	            url:'/scores/getScores',
	            data:{projectNameList:projectNameList,type:"special"},
	            dataType:'json',
	            async: false,
	            success:function(data){
					if(data.status == 200){
						specialresultList = data.data
					}
				}
			});
		var resultHTML = '';
		for (var i = 0,len = projectList.length; i < len; i++){
			var proName = projectList[i].projectname;
			proName = proName.substring(0,4);
			console.log(proName);
			resultHTML += '<div class ="tttt" style = "left:'+(i+1)*210+'px"><div class = "proName"><p>'+proName+'</p></div><div id = "result'+(i+1)+'" class="col-xs-12 resultStyle" style = "height:'+specialresultList[i]+'px; background-color : #FF'+i+'"><p style="line-height:'+specialresultList[i]+'px">'+specialresultList[i]+'</span>分</p></div></div>';

		}
		/*resultHTML += '<div class="col-xs-12" id="goback"><p style="text-align: center;"><button type="button" class="btn btn-primary" id="retVoting" onclick = "showSpeialVotting(event,this)">返回投票区</button></p></div>'*/
		$('#content').append(resultHTML);
	}
	//初始化人气比赛的DIV
	function showHotMatch(){
		//根据后台项目数量，生成人气评分的HTML
		var hotmatchHTML = '<ul>';
		for(var i = 0,len = projectList.length; i < len; i++){
			hotmatchHTML += '<li id="hot_match'+(i+1)+'" class="hot_match">';
			hotmatchHTML += '<div class="col-xs-12">';
			hotmatchHTML += '<div id="hot_match_dep'+(i+1)+'" class="col-xs-3 hot_match_dep"><p>'+projectList[i].depname+'</p></div>';
			hotmatchHTML += '<div onclick="clickProject(event,this)" id="hot_match_project'+(i+1)+'" class="col-xs-9 hot_match_project"><p>'+projectList[i].projectname+'</p></div>';
			hotmatchHTML += '</div>'
			hotmatchHTML += '<div class="col-xs-12"><div id="hot_match_value'+(i+1)+'" class="col-xs-12 hot_match_value"></div></div>'
			hotmatchHTML += '<input type="hidden" name="'+projectList[i].projectname+'" id="" value =0 />';
			
			hotmatchHTML += '</li>';			
		}
		hotmatchHTML +='</ul>';
		$('#hot_match').append(hotmatchHTML);

		//将用户已经投票记录显示在投票区
		initVotted($(".hot_match"));
		//显示用户已经投票情况
		showVottedRecords($(".hot_match"));	
	}
	//弹出提交确认窗口
	function submitFunciton(e,element){
		var cut_total = 0;
		var valueString = '';
		var vlueList = element.parentNode.parentNode.parentNode.children;
		for(var i = 0,len =standardList.length; i < len; i++){
			cut_total += parseInt(vlueList[i].children[2].value);
			valueString +='第'+ (i+1)+'条: '+vlueList[i].children[2].value+'分； ';
		};
		console.log(cut_total);
		var a = confirm("您确定您的评分是"+valueString+"?");
		if (a){
			var cur_name = element.parentNode.parentNode.parentNode.parentNode.previousElementSibling.children[1].innerHTML;
			cur_name = cur_name.replace("<p>","");
			cur_name = cur_name.replace("</p>","");
			console.log(cur_name);
			
			
			//将此项目的总分写入数据库
			$.ajax({
	            type:'post',
		            url:'/scores/createSpecial',
		            //String vottingperson, String projectName,String specialRecorder
		            data:{vottingperson:userObj.username,projectName:cur_name,specialRecorder:cut_total},
		            dataType:'json',
		            async: false,
		            success:function(data){
						if(data.status == 200){
							alert("提交成功");
						}
					}
				});

			
		}else{

		}
    };
    
	//在人气评分区生成已经评分的情况
	function showVottedRecords(obj){
		 for (var i = 0; i < obj.length; i++){
		 	var nob = obj[i].children[2].value
		 	obj[i].children[1].children[0].innerHTML = showVottedDiv(nob);
		 }
	}
	//根据数据库中的记录，将数据库中每个项目的人气得分情况写入隐藏的input中
	function initVotted(obj){
		for (var i = 0,len = obj.length; i < len; i++){
			var name = obj[i].lastChild.name;
			var valueString = vottedRecordMap[name];
			
			valueString = valueString.replace('[','');
			valueString = valueString.replace(']','');
			obj[i].lastChild.value = parseInt(valueString.substring(valueString.indexOf(':')+1));
		}
	}
	//将已投票纪录转换为Map
	function changeVottedToMap(votingrecoder){
		if(votingrecoder == null){
			return;
		}
		var vottedRecordArray = (votingrecoder.split('/'));
		
		for (var i = 0, len = vottedRecordArray.length; i<len; i++){
			var tempArray = vottedRecordArray[i].split(': ');
			vottedRecordMap[tempArray[0]] = tempArray[1];
		}
	}
	//生成专家评分的DIV
	function showspecalistMatch(){
		var specialmatchHTML = '<ul id = "hot_match_ui">';
		for(var i = 0,len = projectList.length; i < len; i++){

			specialmatchHTML += '<li id="special_match'+(i+1)+'" class="special_match">';
			specialmatchHTML += '<div class="col-xs-12 margin-b">';
			specialmatchHTML += '<div id = "special_match_dep'+(i+1)+'"class="col-xs-4 special_match_dep"><p>'+projectList[i].depname+'</p></div>';
			specialmatchHTML += '<div onclick="clickSpecialProject(event,this)" id="special_match_project'+(i+1)+'" class="col-xs-8 special_match_project"><p>'+projectList[i].projectname+'</p></div></div>';
			specialmatchHTML += '<div class="col-xs-12">';
			specialmatchHTML += '<div id="special_match_value'+(i+1)+'" class="col-xs-12 special_match_value">'+getStandardHtml(standardList);
			specialmatchHTML += '<div class="btn-group" id= "submits"><p><button type="button" class="btn btn-primary" id="submit" onclick = "submitFunciton(event,this)">提交</button><button type="button" class="btn btn-primary" id="showTotal" onclick = "showResult(event,this)">看总分</button></p></div></div></div>';
			
			specialmatchHTML += '</li>';
			
			specialmatchHTML += '<input type="hidden" name="'+projectList[i].projectname+'" id="">'
		}
		specialmatchHTML +='</ul>';
		$('#special_match').append(specialmatchHTML);
		//initSpeciallistValue();
		//getSpecialTotal();
		/*showVotingPower(userObj.votingpower);*/
		disableInput();
	}


	//选中专家评分项目,当前项目可以评分，其他项目不可评分
	function clickSpecialProject(e,element){
		var vottedResult;
		//消除未选中项目的样式
		$('.special_match_project').each(function(){
		   $(this)[0].style.backgroundColor = "#0CC";
		   disableInput();
		   $('.special_match_value').hide('slow');
		});
		
		
		//将选中项目的评分区域开放评分
		element.style.backgroundColor ="#bb06f7";
		element.parentNode.nextElementSibling.children[0].style.display = 'block';
		//将数据库中已经投出的记录展示在投票区中
		var p_Name = element.innerHTML;
		p_Name = p_Name.replace('<p>','');
		p_Name = p_Name.replace('</p>','');
		$.ajax({
            type:'post',
	            url:'/vottedRecoder/getScores',
	            data:{vottingperson:userObj.username,projectName:p_Name},
	            dataType:'json',
	            async: false,
	            success:function(data){
					if(data.status == 200){
						vottedResult = data.data;
					}
				}
			});
		var inputList = element.parentNode.nextElementSibling.children[0].children;
		if (vottedResult != 'error'){
			var resultList = vottedResult.split(',');
			
		//console.dir(standardList.length);
			for (var i = 0; i < standardList.length; i++){
				inputList[i].children[2].disabled = false;
				inputList[i].children[2].value = resultList[i];
			}
		}else{
			for (var i = 0; i < standardList.length; i++){
				inputList[i].children[2].disabled = false;
				inputList[i].children[2].value = 0;
			}

		}
		
		

		



	}
	//根据数据库数据生成专家评分区HTML，input只能输入数字
	function getStandardHtml(obj){
		var tempHTML = '';
		for (var i = 0,n=obj.length; i < n; i++){			
			tempHTML += '<p><strong>'+obj[i].name+':</strong><br /><input onchange="changeValue(this)" onFocus="onFouceFunction(this) " onBlur = "onBlurFunction(this)" type = "number" name = "'+(i+1)+'" value = 0 /><span></span>最高分值为：<strong>'+obj[i].maxgrand+'</strong>分</p>';
		}	
		return tempHTML;
	}
	//将专家投票纪录初始化为Map
	function initspecialRecordMap(){
		return;
		if(vottedRecoder == null){
			return;
		}
		var specialRecArray = vottedRecoder.split('/');
		
		for(var i = 0 ,len = specialRecArray.length; i < len; i ++){
			var tempArray = specialRecArray[i].split(': ');
			var iemtValueArrayString = tempArray[1];	
			iemtValueArrayString = iemtValueArrayString.replace("[",'');
			iemtValueArrayString = iemtValueArrayString.replace(']','');
			var itemValueArray = iemtValueArrayString.split(',');
			specialRecordMap[tempArray[0]] = itemValueArray;
		}
	}

	//根据数据库的投票纪录初始化专家投票区
	function initSpeciallistValue(){
		return;
		initspecialRecordMap();
		var specialnumbs = $('.special_match_project');
		var name = '';
		if($.isEmptyObject(specialRecordMap)){
			return;
		};
		for (var i = 0, len = specialnumbs.length; i < len; i++){
			//获取参赛项目名称；
			name = specialnumbs[i].children[0].innerText;
			//获取与参赛项目对应的评分区的各个输入框，并初始化历史数据
			/*for(var p in specialRecordMap){
				if (p == name){
					var values = specialnumbs[i].parentNode.nextElementSibling.children[0].children;
					for (var j = 0,len1 = values.length; j < len1; j++){
				
				
						values[j].children[2].value = specialRecordMap[p][j];
					}
				};
				/*console.log(p);
				console.log(specialRecordMap[p]);
				
			}*/
			
		}
	}
	//将专家评分区的所有输入框禁用
	function disableInput(){
		$('.special_match_value input').attr("disabled","disabled");

	}
	//计算专家已经做出的评分总数
	function getSpecialTotal(){
		var values = $('.special_match_value');
		console.log(values);
		for (var i = 0; i < values.length; i++){
			var total = 0;
			var len = values[i].children.length;
			for(var j = 0; j < len; j++){
				total += parseInt(values[i].children[j].children[2].value);	
			};
			values[i].parentNode.parentNode.nextSibling.value = total;
		}
	}

	/*
	专家评分区变化事件,
		1、保证输入的数值不大于规定的上限
		2、用户输入分数后立即将当前的评分情况写入用户表的
		3、将当前此项目的总分写入成绩表
	*/
	function changeValue(e){
		//保证输入的数值不大于规定的上限
		var input_Value = parseInt(e.value);
		var maxValue = parseInt(e.parentNode.children[4].innerHTML);
		if (input_Value > maxValue){
			alert('您所给出的评分不能超出'+maxValue+'分');
			e.value = 0;
			return;
		}
		//将当前的评分写入数据库
		var scoresList = [];
		var projectName = e.parentNode.parentNode.parentNode.previousElementSibling.children[1].children[0].innerHTML;
		var Ps = null;
			//将当前项目各个评分标准的得分封装成List：scoresList
		Ps = e.parentNode.parentNode.children;
		for (var i = 0, len = standardList.length; i < len; i++){
			scoresList.push(Ps[i].children[2].value);
		}
		/*$('#submits').style.display = 'block';*/

		var scoresListString = scoresList.toString();
			//把当前的投票情况写入数据库 参数表：private String username private String votingproject private String scoreslist;
		$.ajax({
            type:'post',
            url:'/vottingRecorder/modify',
            data:{username:userObj.username,votingproject:projectName,scoreslist:scoresListString},
            dataType:'json',
            async: false,	
            success:function(data){
				if(data.status == 200){
				
				}
			}	
		});


		//计算专家评分项目的总得分
		var total = 0;
		var inputLists = e.parentNode.parentNode.parentNode.parentNode.children[1].children[0].children;
		var projectName = e.parentNode.parentNode.parentNode.parentNode.children[0].children[1].innerText;
		for (var i = 0,len = standardList.length; i < len; i++ ){
			total += parseInt(e.parentNode.parentNode.children[i].children[2].value);		
		}
		//给隐藏的总数框赋值
		$('input[name="'+projectName+'"]')[0].value = total;

		
	}
	//专家评分框获得焦点时，如果内容为0，则置空，
	function onFouceFunction(e){
		e.style.backgroundColor ="#bb06f7";
		e.style.color = '#fff'
		if(e.value == 0){
			e.value = '';
		}else{
			
		}
		
	}
	//专家评分框失去焦点时，如果值为空，默认为0；
	function onBlurFunction(e){
		e.style.backgroundColor ="#fff";
		e.style.color = '#222';
		if (e.value == ''){
			e.value = 0;
		}
	}
	//给项目添加人气票,需要进一步优化2020/1/5
	function clickProject(e,param){
		$('#hotVotbtn').show('slow');
		var event = e || window.event;
		$('#hotVotbtn')[0].style.top = event.clientY +60 + 'px';
		//显示用户现有的选票数
		showVotingPower(userObj.votingpower);
		//消除未选中项目的样式
		$('.hot_match_project').each(function(){
		   $(this)[0].style.backgroundColor = "#0CC";
		});
		//给选中的项目更改样式
		param.style.backgroundColor='#f06';
		//修改选票数量，重新生成选票
		
		if(userObj.votingpower > 0){
			userObj.votingpower--;
			showVotingPower(userObj.votingpower);
		}else{
			alert('您的选票已用完！');
			return;
		}
		//在被选中的项目的投票区中添加一个选票
		param.parentNode.nextElementSibling.children[0].innerHTML += addUsedTicket(param);		
	}
	//显示尚未投出的人气选票数量
	function showVotingPower(n){
		//当前浏览器可视区域大小
		var windowWidth = document.body.clientWidth;
		//每个分区中能显示的数量，-1为了保证所有的都完整显示
		var every = parseInt(windowWidth/35) -1;
		//根据每个分区能显示的数量，获取有几个分区
		var times =parseInt(n / every);
		//不满一个分区的部分
		var left = n - times  * every ;
		var votingPowerHTML = '';
		var currentTop = -15;
		var currentleft = 0;
		
		for(var i = 0;i < times; i++){
			currentTop = (i -  1) * 35 + 20;
			for(var j = 0; j < every; j++){			
				currentleft = j *35 + 50;
				votingPowerHTML += '<div id = "votingPower'+(i+1)+'" class="votingPower" style="left:'+currentleft+'px;top:'+currentTop+'px"></div>';
			}			
		};
		currentTop = (times -  1) * 35 + 20;
		for(var k = 0; k < left ; k++){
			currentleft = k *35 + 20;
				votingPowerHTML += '<div id = "votingPower'+(i+1)+'" class="votingPower" style="left:'+currentleft+'px;top:'+currentTop+'px"></div>';
		}		
		$("#votingNombers")[0].innerHTML = votingPowerHTML;	
		//设置剩余投票区的高度
		$("#votingNombers")[0].style.height = (times + 1)* 35 + 'px';
	}
	//生成已投选票的HTML
	function showVottedDiv(n){	
		var showVotted = '';
		for (var i = 0; i < n; i++){
			showVotted += '<div onclick="removeVoter(this)" class = "usedVoting" style = "left:'+(i*40 +10)+'px"></div>';
		}
		return showVotted;
	}
	//给项目添加人气选票
	function addUsedTicket(element){
		var votingNumb = parseInt(element.parentNode.parentNode.lastElementChild.value)+1;
		element.parentNode.parentNode.lastElementChild.value= votingNumb;
		return showVottedDiv(votingNumb);
	}
	//点击选票，移除选票
	function removeVoter(e){	
		var targetElement = e.parentNode;
		targetElement.removeChild(e);
		var nob = targetElement.parentNode.parentNode.children[2].value - 1;
		targetElement.parentNode.parentNode.children[2].value = nob;
		targetElement.innerHTML = showVottedDiv(nob);
		userObj.votingpower++;
		showVotingPower(userObj.votingpower);
	}
	//获取项目列表
	function getProjectList(){
		$.ajax({
            type:'get',
            url:'/project/list2',
            data:{},
            dataType:'json',
            async: false,	//同步方式获取全部项目列表
            success:function(data){
				if(data.status == 200){
					projectList = data.data;
				}
			}	
		});	
	} 
	//根据数据库数据生成项目列表的HTML
	function getprojectListHTML(obj,element){
		var tempHTML = '';
		for (var i = 0,n=obj.length; i < n; i++){
			tempHTML +='<div id = "'+element+(i+1)+'" class="'+element+'"><p>'+obj[i].projectname+'</p></div>';
		}
		return tempHTML;
	}
	//获取专家评分标准
	function getStandard(){
		$.ajax({
            type:'get',
            url:'/standard/list2',
            data:{},
            dataType:'json',
            async: false,	//同步方式获取全部项目列表
            success:function(data){
				if(data.status == 200){
					standardList = data.data;
				}
			}	
		});	
	}
	//判断比赛时间
	function isEffective(){
		var flag = false;
		$.ajax({
            type:'post',
            url:'/time/isEffective',
            data:{date:new Date()},
            dataType:'json',
            async: false,	//同步方式确定后台比赛时间数据
            success:function(data){
				if(data.status == 200){
					flag = data.data;
				}
			}	
		});
		return flag;
	}
</script>
</body>
</html>