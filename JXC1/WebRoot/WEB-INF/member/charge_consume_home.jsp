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
          <h2>会员查询</h2>
		  <form id="form1" action="${pageContext.request.contextPath}/admin/member/member_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">会员电话号码：</th>
                  <td><input type="text" class="u-ipt" name="member.cellPhone" value="${member.cellPhone}"></td>
                  <th class="tr">会员称呼：</th>
                  <td><input type="text" class="u-ipt" name="member.name2" value="${member.name2}"></td>                     
                </tr>
                 <tr>
                  <th class="tr">会员级别：</th>
                  <td><s:select list="#request.memberGrade" headerKey="" headerValue="--请选择--" cssClass="u-slt" cssStyle="width: 45%" name="member.gradeId"/> </td>
                  <th class="tr">会员编号：</th>
                  <td><input type="text" class="u-ipt"></td>                
                </tr>
                <tr>
                	<th class="tr">查询类别</th>
                	<td colspan="3"><s:select list="#request.findTypeMap"  cssClass="u-slt" cssStyle="width: 17%" name="findType"/> </td>	
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
			      
                   
          <h2>会员信息列表</h2>

          <div align="center">
		   <ec:table items="memberList" var="member1"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/member/member_find.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="_0" title="会员姓名" width="10%" >
						<a href="${pageContext.request.contextPath}/admin/member/chag_csum_detail.html?member.memberUuid=${member1.MEMBER_UUID}&findType=${findType }">${member1.NAME}</a>
					</ec:column>
					<ec:column property="MEMBER_CODE" title="会员编号" width="10%" />
					<ec:column property="GRADE_ID" title="级别" width="10%" mappingItem="memberGrade"/>
					<ec:column property="CELL_PHONE" title="电话号码" width="10%" />
					<ec:column property="REG_DATE" title="登记时间" width="10%" />
					<ec:column property="LEFT_MONEY" title="余额" width="10%" />
					<ec:column property="MARK" title="积分" width="10%" />
					<ec:column property="IS_LOGOUT" title="是否注销" width="10%" mappingItem="logoutMap"/>
				</ec:row>
			</ec:table>
		</div>
      <div class="hr10"></div>
		
    </div><!-- /#container -->
</body>
</html>