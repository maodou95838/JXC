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
          <h2>等级查询</h2>
		  <form id="form1" action="${pageContext.request.contextPath}/admin/member/grade_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">等级名称：</th>
                  <td><input type="text" class="u-ipt"  name="grade.gradeName" value="${grade.gradeName}"></td>
                  <th class="tr">等级编号：</th>
                  <td><input type="text" class="u-ipt" name="grade.gradeId" value="${grade.gradeId}"></td>                     
                </tr>                 			                           
             </tbody>
             <tfoot>
               <tr>
                 <td colspan="4" class="tc">
                   <button type="submit" class="u-btn" >查询</button>&emsp;                   
                   <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/member/level_reg.html'">注册</button>&emsp;  
                   <button type="button" class="u-btn" disabled="disabled" id="modifyBtn" style="color:gray" onclick="showModify('${pageContext.request.contextPath}/admin/member/grade_showModifyUI.html?grade.gradeId=')">修改</button>                               
                 </td>
               </tr>
             </tfoot>
          </table>
          </form>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>等级信息列表</h2>
 			<div align="center">
		   <ec:table items="gradeList" var="grade1"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/member/grade_find.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="_0" title="选择" width="10%" >
						<p align="center"><input type="radio" id="${grade1.GRADE_ID}" value="${grade1.GRADE_ID}" name="mySelector" onclick="setButtonUse('mySelector', 'modifyBtn')"/></p>
					</ec:column>
					<ec:column property="GRADE_ID" title="等级编号" width="10%" />
					<ec:column property="GRADE_NAME" title="等级名称" width="10%" />
					<ec:column property="GRADE_DESC" title="等级描述" width="10%" />
					<ec:column property="GRADE_PERCENT" title="优惠金额" width="10%" />
					<ec:column property="ISLOGOUT" title="是否注销" width="10%" mappingItem="logoutMap"/>
					<ec:column property="LOGOUT_REASON" title="注销原因" width="10%" mappingItem="logout"/>
				</ec:row>
			</ec:table>
		</div>
      <div class="hr10"></div>
		
    </div><!-- /#container -->
  	<!--javascript start-->
</body>
</html>