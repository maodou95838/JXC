<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<script type="text/javascript">	
			function test1(){
				var temp = document.getElementsByName("cate");
				var newtemp;
 				 for(var i=0;i<temp.length;i++){
   				    if(temp[i].checked){
        				newtemp=temp[i].value;
        				break;
        			}
 				 }
				window.location="${pageContext.request.contextPath}/admin/cate/category_findById.html?category.cateId="+newtemp;
				
				
			}
			function upd(){
				var up = document.getElementById("update1");
				up.disabled="";
			}
	</script>
  <title></title>
</head>
<body>
    <div id="container" class="container">

    
      <div class="hr10"></div>

          <div class="hr10"></div>
          <h2>&nbsp;商品类别查询</h2>
		 <form id="form0" action="${pageContext.request.contextPath}/admin/cate/category_findall.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">类别名称：</th>
                  <td><input type="text" class="u-ipt" name="category.name" value="${category.name}"></td>
                  <th class="tr">类别编号：</th>
                  <td><input type="text" class="u-ipt" name="category.cateId" value="${category.cateId}"></td>   
                  <td >
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form0').submit()">查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/cate/category_goinput.html'">新增</button>&emsp;
                     <button type="button" disabled="disabled" id="update1" class="u-btn" onclick="test1()">修改</button>&emsp;           
             	 </td> 
               </tr>                          
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>角色信息列表</h2> 
          <div align="center">
		   <ec:table items="catelists" var="cate"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/cate/category_findall.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="选择" width="10%">
						<p align="center">
						<input type="radio" name="cate"  value="${cate.CATE_ID}" onclick="upd()"/>
						</p>
					</ec:column>
					<ec:column property="CATE_ID" title="类别编号" width="10%" />
					<ec:column property="NAME" title="类别名称" width="10%" />
					<ec:column property="CATE_DESC" title="描述" width="10%" />
					<ec:column property="MANAGE_PERSON" title="负责人" width="10%" />
					<ec:column property="COUNT_UNIT" title="计量单位" width="10%" />
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>