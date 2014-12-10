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
  <script type="text/javascript">	
			function test1(){
				var temp = document.getElementsByName("getinbill");
				var newtemp;
 				 for(var i=0;i<temp.length;i++){
   				    if(temp[i].checked){
        				newtemp=temp[i].value;
        				break;
        			}
 				 }
				window.location="${pageContext.request.contextPath}/admin/bill/getinbill_findById.html?getinbill.billId="+newtemp;
				
				
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
          <h2>采购单查询</h2>
		<form id="form1" action="${pageContext.request.contextPath}/admin/bill/getinbill_findalllist.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">采购单编号：</th>
                  <td><input type="text" class="u-ipt validate-number max-length-25" name="getinbill.billId" value="${getinbill.billId}"></td>
                  <th class="tr">采购日期：</th>
				  <td><input type="text" class="u-ipt" class="Wdate" name="getinbill.getInDate" value="<s:date name='getinbill.getInDate' format='yyyy-MM-dd'/>" 
				  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  /></td>
                  <th class="tr">总支出：</th>
                  <td><input type="text" class="u-ipt validate-currency-dollar max-length-10" name="getinbill.totalMoney" value="${getinbill.totalMoney}"></td>
                  <td><button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button></td>
                </tr>
                                
             </tbody>
             
          </table>
          </form>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
				         
          <table class="m-table-form">
             
             <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="button" disabled="disabled" class="u-btn" id="update1" onclick="test1()">维护</button>                   
                 </td>
               </tr>
             </tfoot>
          </table>
          <div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
          
          <h2>采购单列表</h2>

          <div align="center">
		   <ec:table items="billlist" var="GETINBILL"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/bill/getinbill_findalllist.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="选择" width="5%">
						<p align="center"><input type="radio" name="getinbill"  value="${GETINBILL.BILL_ID}" onclick="upd()"/></p>
					</ec:column>
					
					<ec:column property="BILL_CODE" title="采购单编号" width="5%" />
					<ec:column property="GET_IN_DATE" title="采购日期" width="5%" />
					<ec:column property="REG_DATE" title="入库日期" width="5%" />
					<ec:column property="REG_PERSON" title="记录人" width="5%" />
					<ec:column property="TOTAL_MONEY" title="总支出" width="5%" />
				</ec:row>
			</ec:table>
		</div>
      <div class="hr10"></div>
                       
      <div class="hr10"></div>
		
    </div><!-- /#container -->

</body>
</html>