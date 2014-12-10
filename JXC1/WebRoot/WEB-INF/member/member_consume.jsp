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
<body>
    <div id="container" class="container">

      <div class="hr10"></div>
	  <div class="hr10"></div>
      <h2>会员信息</h2>

      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">会员编号：</th>
              <td>${member.memberCode}</td>
              <th class="tr">会员称呼：</th>
              <td>${member.name}</td>
              <th class="tr">会员电话：</th>
              <td>${member.cellPhone}</td>   
              <th class="tr">余额：</th>
              <td><font color="red">${member.leftMoney} 元</font></td>                
            </tr>
            <tr>
              <th class="tr">会员等级：</th>
              <td>${member.memberCode}</td>
              <th class="tr">备注信息：</th>
              <td>${member.remark}</td>
              <th class="tr">是否注销：</th>
              <td>${member.isLogout}</td>   
              <th class="tr">积分：</th>
              <td>${member.mark}</td>                
            </tr>
         </tbody>                           
      </table>
      
      <div class="hr10"></div>
      <div class="hr10">&nbsp;</div>
      <h2>消费历史记录</h2>

       <div align="center"> 
		   <ec:table  items="consumeList" var="bill1" 
				retrieveRowsCallback="limit" 
				action="${pageContext.request.contextPath}/admin/member/chag_csum_detail.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="true" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="SELL_BILL_ID" title="销售单编号" width="10%" />
					<ec:column property="SELL_DATE" title="销售时间" width="10%" />
					<ec:column property="TOTAL_PRICE" title="总金额" width="10%" />
					<ec:column property="PERCENT_PRICE" title="折扣金额" width="10%" />
					<ec:column property="ACTUAL_MONEY" title="实收金额" width="5%" />
					<ec:column property="MARK" title="本次积分" width="5%" />
					<ec:column property="_0" title="详细" width="5%" >
						<a href="${pageContext.request.contextPath}/admin/sellbill/billsearch_billDetail.html?sellbill.sellBillId=${bill1.SELL_BILL_ID}" >
							<img src="${pageContext.request.contextPath}/assets/images/view.gif" />
						</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
		
      <div class="hr10"></div> 
      <div class="hr10" align="center">&nbsp;
            <button type="reset" class="u-btn" onclick="javascript:history.back()">返回</button></div>
    </div><!-- /#container -->
</body>
</html>