<%@ page language="java"  pageEncoding="UTF-8"%>
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
 
</head>
<body>
    <div id="container" class="container">

    
      <div class="hr10"></div>
      <div class="hr10"></div>
          <h2>按采购单查询</h2>
		<form id="form1" action="${pageContext.request.contextPath}/admin/bill/getinbill_findall.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                	<td><input type="hidden" name="getinbill.billId" value="${getinbill.billId}"></td>
                  <th class="tr">采购日期：</th>
                  <td><input type="text" class="u-ipt" class="Wdate" name="getinbill.getInDate" value="<s:date name='getinbill.getInDate' format='yyyy-MM-dd'/>" onfocus="WdatePicker()"  /></td>
                  <th class="tr">采购人：</th>
                  <td><input type="text" class="u-ipt validate-chinese max-length-8" name="getinbill.buyerPerson" value="${getinbill.buyerPerson}"></td>
                  <th class="tr">总支出：</th>
                  <td><input type="text" class="u-ipt validate-currency-dollar max-length-10"  name="getinbill.totalMoney" value="${getinbill.totalMoney}">元</td>
                  <td><button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button></td>
                </tr>
                                
             </tbody>
             
          </table>
          </form>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
		<div class="hr10"></div>
          <h2>按采购商品查询</h2>
		<form id="form0"  action="${pageContext.request.contextPath}/admin/bill/getinbill_findAllByName.html" method="post">
          <table class="m-table-form">
             <tbody id="tbody">
                <tr>
                  <th class="tr">商品类型：</th>
                  <td >
                 	<s:select list="#request.category"  id="cateId" name="goods.cateId" listKey="key" listValue="value.name" headerKey="-1" headerValue="--请选择--"
                  	 cssClass="u-slt required validate-selection" cssStyle="width:80%" onchange="cascade2(this.id, 'goodsId')"></s:select>
                  </td>
                  <th class="tr" width="11%">商品名称：</th>
                  <td>
                  	<select name="getin.goodsId" id="goodsId"
        			 class="u-slt required validate-selection" style="width:60%" >
        			 	<option value="-1">--请选择--</option>
        			 </select>
                  </td>    
                 	              	                   
                  <td colspan="3" align="right"><button type="button" class="u-btn" onclick="javascript:document.getElementById('form0').submit()">查询</button></td>
                </tr>
                                
             </tbody>
          </table> 		         
         </form>
          <div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
          
          <h2>采购单列表</h2>

          <div align="center">
		   <ec:table items="billlist" var="GETINBILL"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/bill/getinbill_findall.html"
				rowsDisplayed='20' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="_0" title="采购单编号" width="5%" >
						<a href="${pageContext.request.contextPath}/admin/bill/getinbill_minutelist.html?getinbill.billId=${GETINBILL.BILL_ID}">${GETINBILL.BILL_ID}</a>
					</ec:column>
					<ec:column property="GET_IN_DATE" title="采购日期" width="5%" />
					<ec:column property="REG_DATE" title="入库日期" width="5%" />
					<ec:column property="BUYER_PERSON" title="采购人" width="5%" />
					<ec:column property="TOTAL_MONEY" title="总支出" width="5%" />
				</ec:row>
			</ec:table>
		</div>
      <div class="hr10"></div>
		
    </div><!-- /#container -->
</body>
</html>