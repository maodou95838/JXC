<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>

<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title></title>
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/cascadeGoods.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
  <script type="text/javascript">
  	resetSubSelectId("goods.cateId", "goodsIds"); 
  
  	function addprice(){
  		var p = 0;
  		var price = document.getElementsByName("prices");//输入商品的价格
  		var count = document.getElementsByName("connt");//输入商品的数量
  		var leftmoney =document.getElementById("leftmoney").value; //余额 
  		var uuid = document.getElementById("Uuid").value;
  		
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
			    //if(lm<p && uuid!=""){
				//	alert("余额不足");
				//}	
  			}

  		}
			
  		if(percent != ''){
  			//var pp = p-pc;//折扣价和实收价
  			var pp = p * pc;//折扣价和实收价,折扣通常都是小数
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
  			}else {}
  				
  		} else {
  			var box = document.getElementById("box")
  				box.checked="";
  			document.getElementById("box1").value = "0";
  		}
	}
  </script>
</head>
<body>
    <div id="container" class="container">

      <div class="hr10"></div>
	  <div class="hr10"></div>
<form id="form1" action="${pageContext.request.contextPath}/admin/sellbill/billsearch_addsellbill.html" method="post">
	  <s:if test="member.memberUuid != ''">
      <h2>会员信息</h2>
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">会员编号：</th>
              <td><input type="text" style="border:0px;" disabled="disabled" name="member.memberCode" value="${member.memberCode }">
              </td>
              <th class="tr">会员称呼：</th>
              <td><input type="text" style="border:0px;" disabled="disabled" name="member.name" value="${member.name }"></td>
              <th class="tr">会员电话：</th>
              <td>
              <input type="text" style="border:0px;" disabled="disabled" name="member.cellPhone" value="${member.cellPhone }"></td>   
              <th class="tr">余额：</th>
              <td><input type="text"  style="border:0px;" disabled="disabled" id="leftmoney" name="member.leftMoney" value="${member.leftMoney}"><font color="red">元</font></td>                
            </tr>
         </tbody>                           
      </table>
      
      <div class="hr10"></div>
      <div class="hr10">&nbsp;</div>
      </s:if>
      			<input type="hidden" id="leftmoney" name="member.leftMoney" value="${member.leftMoney}">
              <input type="hidden" name="membergrade.gradePercent" id="percent" value="${membergrade.gradePercent}">
              	  <input type="hidden" name="member.memberUuid" value="${member.memberUuid}">
      <h2>销售单基本信息</h2>
  
      <table class="m-table-form">
         <tbody>
         	
            <tr>
              <th class="tr">销售单单号：</th>	
            	<td>  
              	  <input type="text" class="u-ipt required" name="sellbill.sellBillCode">
              	  <input type="hidden" name="sellbill.memberUuid" id="Uuid" value="${member.memberUuid}">
              </td>
              <th class="tr">销售日期：</th>
              <td><input type="text" class="u-ipt required" class="Wdate" name="sellbill.sellDate" 
              value="<s:date name="new java.util.Date()" format="yyyy-MM-dd"/>"
              onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  /><font color="red">*</font></td>
              <th class="tr">顾客描述：</th>
              <td><input type="text" class="u-ipt max-length-40" name="sellbill.customerDesc"></td>                                   
            </tr>
            
            <tr>
              <th class="tr">总价：</th>
              <td><input type="text" id="totalprice" class="u-ipt " name="sellbill.totalPrice" readonly="readonly" value="">元</td>
              <th class="tr">折扣价：</th>
              <td ><input type="text" class="u-ipt" id="percentprice" class="u-ipt required validate-currency-dollar" name="sellbill.percentPrice" readonly="readonly" value="">元</td>
              <th class="tr">实收：</th>
              <td ><input type="text" class="u-ipt required validate-currency-dollar" id="actuaMoney" class="u-ipt required validate-currency-dollar" name="sellbill.actualMoney" onblur="chbox()" value="" >元</td>
            </tr>
            <tr>
             <th class="tr">出单员：</th>
             <td>
             <input type="hidden"  name="sellbill.isPay" value="0">
             <input type="text" class="u-ipt required max-length-20" name="sellbill.billPerson"></td>
            </tr>
            <tr>
              <th class="tr">销售备注：</th>
              <td colspan="6"><textarea name="sellbill.remark" id="remark"  cols="90" rows="4" class="u-txt required max-length-70"></textarea></td>                 
            </tr>
            <tr>
              <th class="tr">异常单标识：</th>
              <td colspan="6"><input type="checkbox"  id="box" value="1" disabled="disabled"><font color="red">权限外登记单，必须标识</font>
              <input type="hidden" name="sellbill.isExecBill" id="box1">
              </td>                 
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
              	<td >
<%--                    <input type="hidden" name="sellbill.sellBillId">--%>
                 	<s:select list="#request.category" cssStyle="width:100%"  id="cateId" name="goods.cateId" listKey="key" listValue="value.name" headerKey="-1" headerValue="--请选择--"
                  	 cssClass="u-slt required validate-selection"  onchange="cascade2(this.id, null, '1')"></s:select>
                </td>
                <td><input type="text" class="u-ipt validate-number max-length-5" style="width:100%" id="filterPrice" onblur="goodsFilter(this.id, this.value)" name="filerPrice"></td>
              	<td>
                  	<select name="goodsIds" id="goodsId" class="u-slt required" style="width:100%" onchange="cascade3(this.id)" onclick="cascade3(this.id)">
                  		<option>--请选择--</option>
                  	</select>
                  	<select name="goodsPrices" id="hiddenPricesId" class="u-slt" style="display:none">
                  		<option>--请选择--</option>
                  	</select>
                  </td>  
                <td><input type="text" class="u-ipt required validate-currency-dollar" style="width:100%" id="sellPrice" onblur="addprice()" name="prices" ></td> 
				<td><input type="text" class="u-ipt required validate-currency-dollar" style="width:100%" id="pricesId" onblur="addprice()" name="connt" ></td>
              	<td><input type="text" class="u-ipt" style="width:100%" name="remark"></td>
              	<td> <span style="display:block; width:100%; text-align:center"><input type="button"  class="u-btn u-btn-c4" onclick="delRow('tbody', this);addprice();" value="删除"/></span></td>
              </tr> 
          </tbody>
      </table>
      <table>
      	<td colspan="6" class="cola" align="center">
              		<input type="button" class="u-btn u-btn-c4"  value="增加" onclick="addRow('tbody', '1')"/>
        </td>
        </table>
		<div class="hr10">&nbsp;</div>
          <table class="m-table-form">
             
             <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn">保存</button>
                   &emsp;
                   <button type="reset" class="u-btn">取消</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back()">返回</button>
                 </td>
               </tr>
             </tfoot>
          </table>


 </form>  
    </div><!-- /#container -->
</body>
</html>