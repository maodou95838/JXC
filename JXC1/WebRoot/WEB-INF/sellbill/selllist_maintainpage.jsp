<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title></title>
	<script language="javascript" src="${pageContext.request.contextPath}/assets/js/LodopFuncs.js">
	</script>
		<object id="LODOP_OB"
			classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0
				height=0></embed>
		</object>
   <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/cascadeGoods.js'></script>
   <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/rennwalMember.js'></script>   
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script> 
  <script type="text/javascript">
  	function addprice(){
  		var p = 0;
  		var price = document.getElementsByName("prices");//输入商品的价格
  		var count = document.getElementsByName("connt");//输入商品的数量
  		var leftmoney =document.getElementById("leftmoney").value; //余额 
  		var percent =document.getElementById("percent").value; //会员折扣价  
  		var pc = parseFloat(percent);
  		var lm = parseFloat(leftmoney);
  		if(price.length==1){
			   document.getElementById("totalprice").value=0;
		}	
  		for(var t=1;t<price.length;t++){
  			for(var c=t;c<=t;c++){
  			var c1 = count[c].value;
  			var c2 = parseFloat(c1); //商品的数量
  			var p1 = price[t].value;
  			var p2 = parseFloat(p1);//商品的单价 
  			if(isNaN(c2)){
				document.getElementById("totalprice").value=p;
			}else{
  				var actua = p2*c2;
  				var p = actua+p;
	    		document.getElementById("totalprice").value=p; //总价 
			}	
		    }	
  		}
		    if(lm<p){
  				alert("余额不足");
  			}		
  		if(percent!=''){
  			//var pp = p-pc;//折扣价和实收价
  			var pp = p * pc;
  			document.getElementById("percentprice").value=pp.toFixed(2);
  			document.getElementById("actuaMoney").value=pp.toFixed(2);
  			var am = document.getElementById("actuaMoney").value;
  			var am1 = parseFloat(am);
  			if(am1!=pp){
  				var box = document.getElementById("box")
  				box.checked="checked";
  			}
  			return false;
  		}else{
  			document.getElementById("percentprice").value=p.toFixed(2);
  			document.getElementById("actuaMoney").value=p.toFixed(2);
  			return true;
  		}	
  	}	
  	function chbox(){
  		var all = document.getElementById("totalprice").value;
  		var all1 = parseFloat(all);
  		var percent =document.getElementById("percentprice").value; //会员折扣价  
  		var pc = parseFloat(percent);
  		var am = document.getElementById("actuaMoney").value;
  		var am1 = parseFloat(am);
  		//var pp = all1-pc;
  		if(pc > am1){
  			var box = document.getElementById("box")
  				box.checked="checked";
  			document.getElementById("box1").value = "1";
  			var rem = document.getElementById("remark").value;
  			if(rem==""){
  				alert("已确定为异常单，必须填写销售备注！");
  				var sum = document.getElementById("submit");
  				return false;
  			} else {}
  				
  		} else {
  			var box = document.getElementById("box")
  				box.checked="";
  			document.getElementById("box1").value = "0";
  		}
  	}
  	
  	function check(){
  		
  	  var leftmoney =document.getElementById("leftmoney").value; //余额 
  	  if(leftmoney==""){
  	  	var money = document.getElementById("money");
  	  	//alert("现金消费！");
  	  	money.checked="checked";
  	  }else{
  	  	var mark = document.getElementById("mark").value//积分1
  	  	var mark1 =parseInt(mark);
		var aM = document.getElementById("actuaMoney").value//实收钱数
		var aM1 = parseInt(aM);//取整
		var mark2 = mark1+aM1;//总积分
			document.getElementById("mark").value=0;
		
  	  	var cked = document.getElementsByName("pass");
  	  	for ( var i = 0; i < cked.length; i++) {
  	  		if(cked[i].checked==true){
  	  			var card=cked[i].value;
		  	  	if(card==2){
		  	  		
				  	var actuaMoney = document.getElementById("actuaMoney").value
				  	var leftm = document.getElementById("leftmoney").value
				  	var nowmoney = leftm-actuaMoney;
				  	  document.getElementById("leftmoney1").value=nowmoney;
				  	
		  	  	}
  	  		}
  	  	}
  	  	
	  }
  	}
  	//更改会员
  	function mem(){
  		var mem1 = document.getElementById("tt");
  		var mem2 = document.getElementById("tt1");
  		var mem3 = document.getElementById("tt2");
  		var mem4 = document.getElementById("leftmoney");
  		//mem1.disabled="";
  		//mem2.disabled="";
  		mem3.disabled="";
  		//mem4.disabled="";
  		document.getElementById("tt").value="";
  		document.getElementById("tt1").value="";
  		document.getElementById("tt2").value="";
  		mem4.value = "";
  	    document.getElementById("muuid").value=null;
  	    
  	}

  	//删除会员 
  	function mem1(){
  		document.getElementById("tt").value="";
  		document.getElementById("tt1").value="";
  		document.getElementById("tt2").value="";
  		 document.getElementById("muuid").value=null;
  		 document.getElementById("percent").value = 1;
  	}
  	
  	/**
  	 * 未使用
  	 */
  	function print1() {
  		var newWin = window.open('about:blank',"","");  
            //取得id为"order"的<div id="order"></div>之间的内容  
        var titleHTML = document.getElementById("aaa").innerHTML;  
           //将取得的打印内容放入新窗体  
        newWin.document.write(titleHTML);
           //刷新新窗体  
        newWin.document.location.reload();  
      //调用打印功能    
        newWin.print();  
           //打印完毕自动关闭新窗体  
        newWin.close();  
  	}
  		
  </script>
</head>
<body>
    <div id="container" class="container">

      <div class="hr10"></div>
	  <div class="hr10"></div>
      <h2>会员信息</h2>
<form id="form1" action="${pageContext.request.contextPath}/admin/sellbill/billsearch_findAllmaintain.html" method="post">
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">会员编号：</th>
              <td><input type="text" style="border:0px;" id="tt" disabled="disabled"  name="member.memberCode" value="${member.memberCode }">
              	  <input type="hidden" name="member.memberUuid" id="muuid"  value="${member.memberUuid }">
              	  <input type="hidden" name="member.isConsume"  value="Y">
              	  <input type="hidden" id="mark" value="${member.mark}">
              </td>
              <th class="tr">会员称呼：</th>
              <td><input type="text" style="border:0px;" disabled="disabled" id="tt1"  name="member.name" value="${member.name }" ></td>
              <th class="tr">余额：</th>
              <td><font color="red"><input type="text" style="border:0px;" id="leftmoney" disabled="disabled" name="member.leftMoney" value="${member.leftMoney}"></font>
              	<input type="hidden" id="leftmoney1" name="member.leftMoney" value="${member.leftMoney}">
              </td> 
               <th class="tr">会员电话：</th>
              <td>
              <input type="hidden" name="membergrade.gradePercent" id="percent" value="${membergrade.gradePercent}">
              <input type="text" style="border:0px;" disabled="disabled" id="tt2" onblur="dele(this.id)" 
              	name="member.cellPhone" value="${member.cellPhone }"></td>    
              <td><button type="button" class="u-btn" onclick="mem()">更换会员</button></td> 
              <td><button type="button" class="u-btn" onclick="mem1()">删除会员</button></td>             
            </tr>
         </tbody>                           
      </table>
   
      <div class="hr10"></div>
      <div class="hr10">&nbsp;</div>
      <h2>销售单基本信息</h2>

      <table class="m-table-form">
         <tbody>
         	
            <tr>
              <th class="tr">销售单单号：</th>
              <td>
              <input type="hidden" name="sellbill.sellBillId" value="${sellbill.sellBillId}">
              <input type="text" class="u-ipt  required" name="sellbill.sellBillCode" value="${sellbill.sellBillCode}"></td>
              <th class="tr">销售日期：</th>
              <td><input type="text" class="u-ipt  required" class="Wdate" 
				  name="sellbill.sellDate" value="<s:date name='sellbill.sellDate' format='yyyy-MM-dd'/>" 
				  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  /><font color="red">*</font>
				  <%--	用于打印时间，其他地方未调用			  --%>
				  <input type="hidden"  class="Wdate" id="nowTime"
				   value="<s:date name='new java.util.Date()' format='yyyy-MM-dd HH:mm:ss'/>" 
				  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  />
				  </td>
              <th class="tr">顾客描述：</th>
              <td><input type="text" class="u-ipt" name="sellbill.customerDesc" value="${sellbill.customerDesc}"></td>                                   
            </tr>
            
            <tr>
              <th class="tr">总价：</th>
              <td><input type="text" class="u-ipt" name="sellbill.totalPrice" id="totalprice" value="${sellbill.totalPrice }" readonly="readonly">元</td>
              <th class="tr">折扣价：</th>
              <td ><input type="text" class="u-ipt" name="sellbill.percentPrice" id="percentprice" value="${sellbill.percentPrice }" readonly="readonly">元</td>
              <th class="tr">实收：</th>
              <td ><input type="text" class="u-ipt " name="sellbill.actualMoney" id="actuaMoney" onblur="chbox()" value="${sellbill.actualMoney }">元</td>
            </tr>
            <tr>
            	<th class="tr">出单人：</th>
              <td><input type="text" class="u-ipt" name="sellbill.billPerson" value="${sellbill.billPerson}" ></td>
              <th class="tr">状态：</th> 
              <td><s:select  list="#{0:'--请选择--',1:'已结账',2:'挂账'}" cssStyle="width:45%"  listKey="key" listValue="value" cssClass="u-ipt"  name="sellbill.isPay"></s:select></td>
              <th class="tr">付款方式：</th>
              <td colspan="6"><input type="radio" name="pass" id="money" value="1" checked="checked"/>付现   &nbsp;<input type="radio" name="pass" id="card" value="2" />刷卡</td> 
            </tr>
            <tr>
              <th class="tr">销售备注：</th>
              <td colspan="6"><textarea name="sellbill.remark"  id="remark" cols="90" rows="4" class="u-txt" >${sellbill.remark}     	
              	</textarea></td>                 
            </tr>
            <tr>
              <th class="tr">异常单标识：</th>
              <td colspan="6"><input type="checkbox" id="box"  disabled="disabled"><font color="red">权限外登记单，必须标识</font>
               <input type="hidden" name="sellbill.isExecBill" id="box1" value="${sellbill.isExecBill}"></td>                 
            </tr>
         </tbody>
         
      </table>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
          <h2>销售商品详单</h2>

          <table class="m-table">
          <thead>
              <tr>
              	<th class="cola">条码</th>
              	<th class="cola">类别</th>
              	<th class="cola">价格过滤</th>
              	<th class="cola">名称</th>
              	<th class="cola">单价</th>
              	<th class="cola">数量</th>
              	<th class="cola">备注</th>
              	<th class="cola">删除</th></tr>
          </thead>
          <tbody id="tbody">
        	  <tr style="display: none">
        	  	<td><input type="text" id="barId" name="barName" onblur="laserAway(this.id, this.value, 'tbody')"/></td>
              	<td> 
                 	<s:select list="#request.category"  id="cateId" name="goods.cateId" listKey="key" listValue="value.name" headerKey="-1" headerValue="--请选择--"
                  	 cssClass="u-slt required validate-selection" cssStyle="width:100%" onchange="cascade2(this.id, null, '1')"></s:select>
                  </td>
                <td><input type="text" class="u-ipt validate-number max-length-5" style="width:100%" id="filterPrice" onblur="goodsFilter(this.id, this.value)" name="filerPrice"></td>  
              	<td>
                  	<select name="goodsIds" id="goodsId" Class="u-slt required " style="width:100%"  onchange="cascade3(this.id)" onclick="cascade3(this.id)">
                  	<option>--请选择--</option>
                  	</select>
                  	<select name="goodsPrices" id="hiddenPricesId" class="u-slt" style="display:none">
                  		<option>--请选择--</option>
                  	</select>
                  </td>  
                <td><input type="text" class="u-ipt validate-currency-dollar" style="width:100%" onblur="addprice()" name="prices" ></td> 
              	<td><input type="text" class="u-ipt required validate-number max-length-5" style="width:100%" onblur="addprice()"  name="connt" >
              	<input type="hidden"  name="oldCount" value="0"></td>
              	<td><input type="text" class="u-ipt" name="remark" style="width:100%"></td>
              	<td><span style="display:block; width:100%; text-align:center"><input type="button" class="u-btn u-btn-c4" onclick="delRow('tbody', this);addprice()" value="删除"/></span></td>
              </tr> 
      <c:forEach items="${alllist}" var="list" varStatus="count">
              <tr>
              	  <td>
              	  	<input type="text" id="bar" name="barName" onblur="laserAway(this.id, this.value)" value="${list.BAR_CODE}"/>
              	  </td>
                  <td>
                 	<s:select list="#request.category"  id="cateId" name="goods.cateId"	value="#attr.list.CATE_ID" 
                 	listKey="key" listValue="value.name" headerKey="-1" headerValue="--请选择--"
                  	 cssClass="u-slt required validate-selection" cssStyle="width:100%" 
                  	 onchange="cascade2(this.id, null, '1')" ></s:select>
                  </td>
                  <td>
                  	<input type="text" class="u-ipt validate-number max-length-5" id="filterPrice" style="width:100%" onblur="goodsFilter(this.id, this.value)" name="filerPrice">
                  </td>
                  <td>
					<s:select list="#attr.list['gname']"  id="goodsId" name="goodsIds" value="#attr.list.GOODS_ID" 
					listKey="goodsId" listValue="goodsName" headerKey="-1" headerValue="--请选择--"
                  	 cssClass="u-slt required validate-selection" cssStyle="width:100%" onchange="cascade3(this.id)" onclick="cascade3(this.id)"/>
                  	 <s:select list="#attr.list['gname']" name="goodsPrices" id="hiddenPricesId" cssClass="u-slt" cssStyle="display:none"
                  	 listKey="goodsId" listValue="sellPrice" headerKey="0.00" headerValue="--请选择--"/>        		
                  </td>
                  <td>
                  	<input type="text" class="u-ipt" name="prices" style="width:100%" onblur="addprice()"  value="${list.PRICE}">
                  </td> 
                  <td>
              		<input type="text" class="u-ipt" name="connt" style="width:100%" onblur="addprice()" value="${list.SELL_COUNT}" id="connt">
              		<input type="hidden" name="oldCount" value="${list.SELL_COUNT}">
              		<input type="hidden" name="sellgoods.sellGoodsId" value="${list.SELL_GOODS_ID}">
                	<input type="hidden" name="sellgoods.sellBillId" value="${list.SELL_BILL_ID}">
              	</td>
              	<td>
              		<input type="text" class="u-ipt" name="remark" style="width:100%" value="${list.REMARK}">
              	</td>
              	<td>
              		<span style="display:block; width:100%; text-align:center"><input type="button" class="u-btn u-btn-c4" onclick="delRow('tbody', this);addprice()" value="删除"/></span>
              	</td>
              </tr>
     </c:forEach>
          </tbody>
         
      </table>
   
      <table>
      	<td colspan="6" class="cola" align="center">
              		<input type="button" class="u-btn u-btn-c4"  value="增加" onclick="addRow('tbody', '1')"/>
        </td>
        
        </table>
		<div class="hr10">&nbsp;</div>
             <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn" onclick="check()" >提交</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back()">取消</button>
                    &emsp;
                    <button type="button" class="u-btn" onclick="MyPreview()">打印</button>
<%--                    <button type="button" class="u-btn" onclick="myDesign()">设计</button>--%>
                 </td>
               </tr>
             </tfoot>
     </form>         

    </div>
</body>
</html>
<script type="text/javascript">
	resetSubComponentsId('1'); 
	var execBox = document.getElementById("box");
	var valuebox = document.getElementById("box1").value;
	if (valuebox == "1") {
		execBox.checked = "checked";
	}
	
	//-----------------------------------------------------------------------
	var LODOP; //声明为全局变量
	function MyPreview() {	
		AddTitle();
		var iCurLine = 14.5;//起始高度
		var rowHeight = 3.8;//行间距
		
		var eltLength = document.getElementsByName("goods.cateId").length;
		for (var i = 1; i < eltLength; i++) {
			
			//获取商品名称
			var sep = "-" + i;
			var myselect = document.getElementById("goodsId" + sep);
			var currSelectText = myselect.options[myselect.selectedIndex].text;
			var nameLength = currSelectText.length;
			var rows = 1;
			if (nameLength > 7) {
				if (nameLength % 7 == 0) {
					rows = parseInt(nameLength / 7);
				} else {
					rows = parseInt(nameLength / 7 + 1);
				}
			}
			
			LODOP.ADD_PRINT_TEXT(iCurLine  + "mm", "2.1mm", "26.5mm", "5.3mm", currSelectText);
			
			//数量
			LODOP.ADD_PRINT_TEXT(iCurLine  + "mm", "27.5mm", "10.7mm", "5.3mm", document.getElementById("connt" + sep).value);
			
			//价格
			LODOP.ADD_PRINT_TEXT(iCurLine  + "mm", "37.2mm", "15.9mm", "5.3mm", document.getElementById("priceId" + sep).value);
			iCurLine = iCurLine + rowHeight * rows;
		}	
		
		//后一行比前一行高4mm
		LODOP.ADD_PRINT_TEXT(iCurLine + "mm","2.1mm","47.9mm","5.3mm","****************************");
		LODOP.ADD_PRINT_TEXT(iCurLine + 4 +"mm", "2.1mm", "47.9mm", "5.3mm", "合计：     " + document.getElementById("totalprice").value);
		LODOP.ADD_PRINT_TEXT(iCurLine + 8 +"mm", "2.1mm", "47.9mm", "5.3mm", "折扣：     " + document.getElementById("percentprice").value);
		LODOP.ADD_PRINT_TEXT(iCurLine + 12 +"mm", "2.1mm", "47.9mm", "5.3mm", "实收：     " + document.getElementById("actuaMoney").value);
		
		LODOP.ADD_PRINT_TEXT(iCurLine + 16 + "mm","2.1mm","56mm","5.3mm",".............................");
		LODOP.ADD_PRINT_TEXT(iCurLine + 20 + "mm", "2.1mm", "56mm", "5.3mm","销售时间："+ document.getElementById("nowTime").value);
        LODOP.SET_PRINT_STYLEA(0,"FontSize", 7);
        
        LODOP.ADD_PRINT_TEXT(iCurLine + 24 + "mm", "6.1mm", "56mm", "5.3mm","欢迎下次光临！");
        LODOP.SET_PRINT_STYLEA(0,"FontSize", 7);
<%--        alert(LODOP.GET_VALUE("ItemContent"));--%>
		LODOP.PREVIEW();	
	};
	
	function AddTitle(){	
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INIT("销售小票打印");
<%--		LODOP.PRINT_INITA("0mm","0mm","57.9mm","1mm","销售小票打印");--%>
		LODOP.SET_PRINT_PAGESIZE(3, "58mm", "0.01mm", "");
<%--		LODOP.SET_PRINT_MODE("POS_BASEON_PAPER",true);--%>
		LODOP.ADD_PRINT_TEXT("2mm","1.3mm","50.8mm","7.9mm","恒坤酒窖销售小票");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_PRINT_STYLEA(0,"Horient",2);
		LODOP.ADD_PRINT_TEXT("7.3mm","2.4mm","26.5mm","5.3mm","商品名称");
		LODOP.ADD_PRINT_TEXT("7.3mm","27.5mm","8.7mm","5.3mm","数量");
		LODOP.ADD_PRINT_TEXT("7.3mm","35.2mm","15.9mm","5.3mm","单价(元)");
		LODOP.ADD_PRINT_TEXT("10.7mm","2.1mm","47.9mm","5.3mm","****************************");
	};
	
	function myDesign() {
		MyPreview();
		LODOP.PRINT_DESIGN()
	}
</script>