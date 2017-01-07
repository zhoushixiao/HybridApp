;
(function($, window, document, undefined) {
	/*文档准备就绪*/
	$(document).ready(function() {

             //js调用原生方法获取本地数据
				window.hybrid.getLocalData();

	});

     /*API*/
	$.webapp.Api.setStructureValue=function(data) {
	      autoAddChart(data)
    	};


//模拟真实数据JSON解析
function jiexiJSON(obj)
{
	var titles=new Array();
	var datas=new Array();
	var names=new Array();
	var i=0;

   //字符串转json
	var data=$.parseJSON(obj);

       //json解析
	 $.each(data, function(index,val) { 
	 	
	 	var xx=new Array();
		names.push(index);
	 	
	     $.each(val, function(index2,val2) {
	     	var a=index2.indexOf("#");
	     	var title=index2.substring(a+1);
	     	if(i==0)
	     	{
	     	titles.push(title);	
	     	}
	     	i++;
	     	
	     	$.each(val2, function(index3,val3) {
	     	   xx.push(val3);
	     	});
	     	
	     });
	     datas.push(xx);
    });	

    return setDataFormat(names,titles,datas);
}


//加工数据
function setDataFormat(names,titles,datas)
{
	
	var mySeries=new Array();
	
	for(i=0;i<datas.length;i++)
	{
		
		var s={
        	//color: ['#FF7043'],
            name:names[i],
            type:'bar',
            stack:'A',
            data:datas[i]
		}
		mySeries[i]=s;
	}
	
	
	var dataYun3 = {
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:names
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data :titles
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : mySeries
};
return dataYun3;
}


//动态添加图表
function autoAddChart(data)
{
	var randomID=getRandomID();
	var chart="<div id="+randomID+" style=\"height:400px;margin-top: 40px;\"></div>";
	
	$("body").append(chart);
	var dom = document.getElementById(randomID);
	var dataYun=jiexiJSON(data);
    var myChart = echarts.init(dom);
	if (dataYun && typeof dataYun === "object") {
	    myChart.setOption(dataYun, true);
	}
}


/**
 * 产生一个随机的id
 */
function getRandomID()
{
	var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
	  var res = "";
     for(var i = 0; i < 30 ; i ++) {
         var id = Math.ceil(Math.random()*35);
         res += chars[id];
     }
     return res;
}

















})(jQuery, window, document);




	
