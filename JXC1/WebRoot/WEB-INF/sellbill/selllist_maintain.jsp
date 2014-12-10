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
  <script type="text/javascript">	
			function test1(){
				var temp = document.getElementsByName("sellbill");
				var newtemp;
 				 for(var i=0;i<temp.length;i++){
   				    if(temp[i].checked){
        				newtemp=temp[i].value;
        				break;
        			}
 				 }
				window.location="${pageContext.request.contextPath}/admin/sellbill/billsearch_findBysellbillId.html?sellbill.sellBillId="+newtemp;
				
				
			}
			function upd(){
				var up = document.getElementById("update1");
				up.disabled="";
			}
	</script>
</head>
<body>
    <div id="container" class="container">

    
      <div class="hr10"></div>

          <div class="hr10"></div>
          <h2>销售单查询</h2>
	<form id="form1" action="${pageContext.request.contextPath}/admin/sellbill/billsearch_findAllsellbill.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">会员电话号码：</th>
                  <td><input type="text" class="u-ipt" name="member.cellPhone"></td>
                  <th class="tr">会员名称：</th>
                  <td><input type="text" class="u-ipt" name="member.name"></td>                     
                </tr>
                 <tr>
                  <th class="tr">销售日期：</th>
                  <td><input type="text" class="u-ipt" class="Wdate" name="sellbill.sellDate" 
                  value="<s:date name='sellbill.sellDate' format='yyyy-MM-dd'/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  /></td>
                  <th class="tr">销售单单号：</th>
                  <td><input type="text" class="u-ipt" name="sellbill.sellBillCode"></td>                
                </tr>				                           
             </tbody>
             <tfoot>
               <tr>
                 <td colspan="4" class="tc">
                   <button type="submit" class="u-btn" >查询</button>                   
                   <button  type="button" disabled="disabled" class="u-btn" id="update1" onclick="test1()">维护/结账</button>              
                 </td>
               </tr>
             </tfoot>
          </table>
  </form> 
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>会员信息列表</h2>

          <div align="center">
		   <ec:table items="sellbillList" var="SELLBILL"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/sellbill/billsearch_findAllsellbill.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="选择" width="5%">
						<p align="center"><input type="radio" name="sellbill"  value="${SELLBILL.SELL_BILL_ID}" onclick="upd()"/></p>
						<input type="hidden" name="sellbill" value="${SELLBILL.MEMBER_UUID}"/>
					</ec:column>
					<ec:column property="SELL_BILL_CODE" title="销售单单号" width="5%" />
					<ec:column property="SELL_DATE" title="销售时间" width="5%" />
					<ec:column property="TOTAL_PRICE" title="总金额" width="5%" />
					<ec:column property="PERCENT_PRICE" title="折扣金额" width="5%" />
					<ec:column property="ACTUAL_MONEY" title="实收金额" width="5%" />
					<ec:column property="ISMEMBER" title="是否会员" width="5%" />
					<ec:column property="LEFT_MONEY" title="余额" width="5%" />
					<ec:column property="CELL_PHONE" title="会员电话" width="5%" />
				</ec:row>
			</ec:table>
		</div>

      <div class="hr10"></div>
		
    </div><!-- /#container -->
</body>
</html>