<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
  		
  		function addprice(){
  			var p = 0;
	  		var price = document.getElementsByName("prices");//输入商品的价格
	  		var count = document.getElementsByName("connt");//输入商品的数量
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
	  	}	
  </script>
</head>
<body>
    <div id="container" class="container">

    
      <div class="hr10"></div>

          <div class="hr10"></div>
          <h2>&nbsp;采购单基本信息</h2>
	<form id="form1" action="${pageContext.request.contextPath}/admin/bill/getin_maintain.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">采购单编号：</th>
                  <td>
                  <input type="text" class="u-ipt required validate-number max-length-25" 
                  name="getinbill.billCode" value="${getinbill.billCode}"><font color="red">*</font></td>	
                  <th class="tr">采购日期：</th>
				  <td><input type="text" class="u-ipt  required" class="Wdate" 
				  name="getinbill.getInDate" value="<s:date name='getinbill.getInDate' format='yyyy-MM-dd'/>" 
				  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  /><font color="red">*</font>
				  <input type="hidden" name="getinbill.billId" value="${getinbill.billId}">
				  </td>
                  <th class="tr">采购人：</th>
                  <td><input type="text" class="u-ipt required validate-chinese max-length-8"  name="getinbill.buyerPerson" value="${getinbill.buyerPerson}"><font color="red">*</font></td>
                  
                </tr>
           
                
                <tr>
                  <th class="tr">总支出：</th>
                  <td><input type="text" class="u-ipt required validate-currency-dollar max-length-10" id="totalprice"  name="getinbill.totalMoney" value="${getinbill.totalMoney}"><font color="red">*</font></td>
                  <th class="tr">备注：</th>
                  <td colspan="3"><input type="text" class="u-ipt u-ipt-1" style="width:85%"  name="getinbill.remark" value="${getinbill.remark}"/></td>
                </tr>
             </tbody>
          </table>
     	<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
          <h2>采购商品详单</h2>

          <table class="m-table">
          <thead >
              <tr>
              	<th class="cola">类别</th>
              	<th class="cola">名称</th>
              	<th class="cola">数量</th>
              	<th class="cola">单价</th>
              	<th class="cola">备注</th>
              	<th class="cola">删除</th>
             </tr>
          </thead>
          </table>
          <table class="m-table">
        <tbody id="tbody">
        	  <tr style="display: none">
              	<td >
                 	<s:select list="#request.category"  id="cateId" name="goods.cateId" listKey="key" listValue="value.name" headerKey="-1" headerValue="--请选择--"
                  	 cssClass="u-slt required validate-selection" cssStyle="width:100%" onchange="cascade2(this.id)"></s:select>
                  </td>
              	<td>
                  	<select name="goodsIds" id="goodsId" Class="u-slt required validate-selection" style="width:100%" >
                  		<option>--请选择--</option>
                  	</select>
                  </td>  
              	<td><input type="text" class="u-ipt required validate-number max-length-5" style="width:100%" onblur="addprice()" name="connt" >
              	<input type="hidden"  name="oldCount" value="0"></td>
                <td><input type="text" class="u-ipt required validate-currency-dollar" style="width:100%" onblur="addprice()" name="prices" ></td> 
              	<td><input type="text" class="u-ipt" style="width:100%" name="remark"></td>
              	<td><span style="display:block; width:100%; text-align:center"><input type="button" class="u-btn u-btn-c4" onclick="delRow('tbody', this),addprice()" value="删除"/></span></td>
              </tr>
      <c:forEach items="${alllist}" var="list" varStatus="count">
              <tr>
                  <td>
                 	<s:select list="#request.category"  id="cateId" name="goods.cateId"	value="#attr.list.CATE_ID" 
                 	listKey="key" listValue="value.name" headerKey="-1" headerValue="--请选择--"
                  	 cssClass="u-slt required validate-selection" cssStyle="width:100%" 
                  	 onchange="cascade2(this.id)" ></s:select>
                  </td>
                  <td>
					<s:select list="#attr.list['gname']"  id="goodsId" name="goodsIds" value="#attr.list.GOODS_ID" listKey="goodsId" listValue="goodsName" headerKey="-1" headerValue="--请选择--"
                  	 cssClass="u-slt required validate-selection" cssStyle="width:100%"></s:select>
                  </td>
              <td>  <input type="text" class="u-ipt" style="width:100%" name="connt" onblur="addprice()" value="${list.CONNT}">
              		<input type="hidden"  name="oldCount" value="${list.CONNT}">
                	<input type="hidden" id="getingoodsid" name="getin.getInGoodsId" value="${list.GET_IN_GOODS_ID}">
                	<input type="hidden" name="getin.billId" value="${list.BILL_ID}">
                </td>  
              	<td><input type="text" class="u-ipt" style="width:100%" name="prices" onblur="addprice()"  value="${list.PRICE}"></td>
              	<td><input type="text" class="u-ipt" style="width:100%" name="remark" value="${list.REMARK}"></td>
              	<td><span style="display:block; width:100%; text-align:center"><input type="button" class="u-btn u-btn-c4" onclick="delRow('tbody', this),addprice()" value="删除"/></span></td>
              </tr>
     </c:forEach>
          </tbody>
         
      </table>
   
      <table>
      	<td colspan="6" class="cola" align="center">
              		<input type="button" class="u-btn u-btn-c4"  value="增加" onclick="addRow('tbody', 'buy')"/>
        </td>
        </table>
		<div class="hr10">&nbsp;</div>
             <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn">提交</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back()">取消</button>
                 </td>
               </tr>
             </tfoot>
              

	 </form>
    </div><!-- /#container -->
 	<script type="text/javascript">
  		resetSubComponentsId('buy'); 
  </script>
</body>
</html>
