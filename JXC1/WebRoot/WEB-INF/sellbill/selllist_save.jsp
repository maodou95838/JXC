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
				var temp = document.getElementsByName("member");
				var newtemp = null;
 				 for(var i=0;i<temp.length;i++){
   				    if(temp[i].checked){
        				newtemp=temp[i].value;
        				break;
        			}
 				 }
 				
 				if (newtemp == null) {
 					newtemp = '';	
				}
				window.location="${pageContext.request.contextPath}/admin/sellbill/billsearch_findByMemberId.html?member.memberUuid="+newtemp;
			}
  </script>
</head>
<body>
    <div id="container" class="container">

    
      <div class="hr10"></div>

          <div class="hr10"></div>
          <h2>会员信息查询</h2>
	<form id="form1" action="${pageContext.request.contextPath}/admin/sellbill/billsearch_showmember.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">会员电话号码：</th>
                  <td><input type="text" class="u-ipt" name="member.cellPhone" value="${member.cellPhone}"></td>
                  <th class="tr">会员名称：</th>
                  <td><input type="text" class="u-ipt" name="member.name2" value="${member.name2}"></td>   
                  <td><button type="submit" class="u-btn">查询</button></td>
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
                   <button type="button" class="u-btn" onclick="test1()">登记</button>                   
                 </td>
               </tr>
             </tfoot>
          </table>
          <div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
          
          <h2>会员信息列表</h2>
			<div align="center">
		   <ec:table items="sellbillList" var="memberlist"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/sellbill/billsearch_showmember.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="选择" width="5%">
						<p align="center"><input type="radio" name="member"  value="${memberlist.MEMBER_UUID}"/></p>
					</ec:column>
					<ec:column property="NAME2" title="会员称呼" width="5%" />
					<ec:column property="CELL_PHONE" title="电话号码" width="5%" />
					<ec:column property="LEFT_MONEY" title="余额" width="5%" />
					<ec:column property="REG_DATE" title="登记日期" width="5%" />
					<ec:column property="IS_LOGOUT" title="是否注销" width="5%" />
					<ec:column property="LOGOUT_REASON" title="注销原因" width="5%" />
					<ec:column property="MARK" title="积分" width="5%" />
<%--					<ec:column property="CHARGE_TIME" title="上次充值时间" width="5%" /> --%>
<%--					<ec:column property="MONEY" title="上次充值钱数" width="5%" />--%>
<%--					<ec:column property="REG_TIME" title="上次消费时间" width="5%" />--%>
<%--					<ec:column property="ACTUAL_MONEY" title="上次消费钱数" width="5%" />--%>
				</ec:row>
			</ec:table>
		</div>
      <div class="hr10"></div>
        
		
    </div><!-- /#container -->
</body>
</html>