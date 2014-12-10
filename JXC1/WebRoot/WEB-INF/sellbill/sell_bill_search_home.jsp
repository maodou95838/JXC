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
</head>
<body>
    <div id="container" class="container">

    
      <div class="hr10"></div>

          <div class="hr10"></div>
          <h2>销售单查询</h2>
 		<form id="form1" action="${pageContext.request.contextPath}/admin/sellbill/billsearch_search.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">会员电话号码：</th>
                  <td><input type="text" class="u-ipt" name="member.cellPhone" value="${member.cellPhone}"></td>
                  <th class="tr">会员名称：</th>
                  <td><input type="text" class="u-ipt" name="member.name" value="${member.name}"></td>                     
                </tr>
                 <tr> 
                  <th class="tr">销售起止日期：</th>
                  <td><input type="text" class="u-ipt" class="Wdate" name="sellDate1" value="${sellDate1}" 
                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})">--
                  <input type="text" class="u-ipt" class="Wdate" name="sellDate2" value="${sellDate2}"
                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"></td>
                  <th class="tr">销售单单号：</th>
                  <td><input type="text" class="u-ipt" name="sellbill.sellBillCode"></td>                
                </tr>
                <tr>
                  <th class="tr">销售单状态：</th>
                  <td><SELECT class="u-ipt" style="width: 21%"  name="sellbill.isPay"><option value="0">--请选择--</option><option value="1">已结账</option><option value="2">挂账</option></SELECT></td>	
                </tr>				                           
             </tbody>
             <tfoot>
               <tr>
                 <td colspan="4" class="tc">
                   <button type="submit" class="u-btn" >查询</button>               
                 </td>
               </tr>
             </tfoot>
          </table>
         </form>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
          <h2>销售单信息列表</h2>

          <div align="center">
		   <ec:table items="sellbillList" var="sellbill1"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/sellbill/billsearch_search.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="_0" title="销售单单号" width="10%" >
						<a href="${pageContext.request.contextPath}/admin/sellbill/billsearch_billDetail.html?sellbill.sellBillId=${sellbill1.SELL_BILL_ID}&sellbill.memberUuid=${sellbill.memberUuid}" >${sellbill1.SELL_BILL_CODE}</a>
					</ec:column>
					<ec:column property="SELL_DATE" title="销售时间" width="10%" />
					<ec:column property="TOTAL_PRICE" title="总金额" width="10%" mappingItem="memberGrade"/>
					<ec:column property="PERCENT_PRICE" title="折扣金额" width="10%" />
					<ec:column property="ACTUAL_MONEY" title="实收金额" width="10%" />
					<ec:column property="ISMEMBER" title="是否会员" width="10%" />
					<ec:column property="LEFT_MONEY" title="余额" width="10%" />
					<ec:column property="CELL_PHONE" title="会员电话" width="10%" />
				</ec:row>
			</ec:table>
		</div>
      <div class="hr10"></div>
		
    </div><!-- /#container -->
</body>
</html>