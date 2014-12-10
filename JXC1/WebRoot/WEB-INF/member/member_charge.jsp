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
      <h2>充值记录</h2>
		<div align="center"> 
		   <ec:table   items="chargeList" var="charge1" 
				retrieveRowsCallback="limit" 
				action="${pageContext.request.contextPath}/admin/member/chag_csum_detail.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="true" style="align:center"
				autoIncludeParameters="true" >
				<ec:row>
					<ec:column property="CHARGE_TIME" title="充值日期" width="10%" />
					<ec:column property="MONEY" title="充值钱数" width="10%" />
					<ec:column property="REMARK" title="充值备注" width="10%" />
				</ec:row>
			</ec:table>
		</div>	
      <div class="hr10"></div> 
      <div class="hr10" align="center">&nbsp;
            <button type="reset" class="u-btn" onclick="javascript:history.back()">返回</button></div>
    </div><!-- /#container -->
</body>
</html>