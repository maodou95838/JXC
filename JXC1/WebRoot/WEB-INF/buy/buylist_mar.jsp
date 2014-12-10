<%@ page language="java" pageEncoding="UTF-8"%>
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
          <h2>采购单基本信息</h2>
		<form id="form1" action="${pageContext.request.contextPath}/admin/bill/getin_save.html" method="post">	 
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">采购单编号：</th>
                  <td>
                  <input type="hidden" name="getinbill.billId">
                  <input type="text" class="u-ipt required validate-number max-length-25" name="getinbill.billCode" ><font color="red">*</font></td>
                  <th class="tr">采购日期：</th>
                  <td><input type="text" class="u-ipt required" class="Wdate" name="getinbill.getInDate" 
                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  /><font color="red">*</font></td>

                  <th class="tr">采购人：</th>
                  <td><input type="text" class="u-ipt required validate-chinese max-length-8" name="getinbill.buyerPerson" ><font color="red">*</font></td>                  
                </tr>
                
                <tr>
                  <th class="tr">总支出：</th>
                  <td><input type="text" class="u-ipt validate-currency-dollar max-length-10" id="totalprice" name="getinbill.totalMoney" value="" >元<font color="red">*</font></td>
                  <th class="tr">备注：</th>
                  <td colspan="3"><input type="text" class="u-ipt u-ipt-1" name="getinbill.remark"  style="width:85%"/></td>
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
	              	<th class="cola">商品名称</th>
	              	<th class="cola">数量</th>
	              	<th class="cola">进价</th>
	              	<th class="cola">备注</th>
	              	<th class="cola">删除</th>
	             </tr>
	          </thead>
        	<tbody id="tbody">
              <tr style="display: none; width: 100%">
              	<td>
                    <input type="hidden" name="getinbill.billId">
                 	<s:select list="#request.category"  id="cateId" cssStyle="width:100%;" name="goods.cateId" listKey="key" listValue="value.name" 
                 	headerKey="-1" headerValue="--请选择--"
                  	 cssClass="u-slt required validate-selection"  onchange="cascade2(this.id)" />
                </td>
              	<td>
                  	<select style="width:100%;" name="goodsIds" id="goodsId" class="u-slt required validate-selection" >
                  		<option>--请选择--</option>
                  	</select>
                 </td>  
              	<td><input type="text"  style="width:100%;" class="u-ipt required validate-number max-length-5" onblur="addprice()" name="connt" ></td>
                <td><input type="text"  style="width:100%;" class="u-ipt required validate-currency-dollar" onblur="addprice()" name="prices" ></td>  
              	<td><input type="text"  style="width:100%;" class="u-ipt" name="remark"></td>
           		<td><span style="display:block; text-align:center"><input type="button" class="u-btn u-btn-c4" onclick="delRow('tbody', this),addprice()" value="删除"/></span></td>
              </tr>
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
                   <button type="reset" class="u-btn">取消</button>
                 </td>
               </tr>
             </tfoot>
	 </form>
	
    </div><!-- /#container -->
</body>
</html>