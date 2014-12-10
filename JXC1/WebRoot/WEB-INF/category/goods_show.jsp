<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>

<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <script type="text/javascript">	
			function test2(){
				var temp = document.getElementsByName("goods");
				var newtemp;
 				 for(var i=0;i<temp.length;i++){
   				    if(temp[i].checked){
        				newtemp=temp[i].value;
        				break;
        			}
 				 }
				window.location="${pageContext.request.contextPath}/admin/cate/goods_findByOne.html?goods.goodsId="+newtemp;
				
				
			}
			function upd1(){
				var up = document.getElementById("update1");
				up.disabled="";
			}
	</script>
  <title></title>
<body>
    <div id="container" class="container">

    
      <div class="hr10"></div>

          <div class="hr10"></div>
          <h2>商品查询</h2>
		<form id="form0" action="${pageContext.request.contextPath}/admin/cate/goods_findall.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">商品名称：</th>
                  <td><input type="text" class="u-ipt" name="goods.goodsName" value="${goods.goodsName}"></td>
                  <th class="tr">商品编号：</th>
                  <td><input type="text" class="u-ipt" name="goods.goodsId" value="${goods.goodsId}"></td>                    
                </tr>
                <tr>
                	<th class="tr">商品类型：</th>
                  <td>
                  	<s:select list="#request.category" name="goods.cateId"  headerKey="-1" headerValue="--请选择--"
                  	listValue="value.name"

                  	 cssClass="u-slt required validate-selection" cssStyle="width:41%" listKey="key" listValue="value.name"></s:select>
                  </td>
                  <th class="tr">商品条码：</th>
                  <td><input type="text" class="u-ipt" name="goods.barCode" value="${goods.barCode}"></td>                   
                </tr>                     			                           
               <tr>
                 <td colspan="4" class="tc">
                   <button type="button" class="u-btn" onclick="javascript:document.getElementById('form0').submit()">查询</button>&emsp;                   
                   <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/cate/goods_zengjia.html'">增加</button>&emsp;  
                   <button type="button" id="update1" disabled="disabled" class="u-btn" onclick="test2()">修改</button>&emsp;                               
                 </td>
               </tr>
             </tbody>
             <tfoot>
             </tfoot>
          </table>
    </form> 
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
           <h2>角色信息列表</h2> 
          <div align="center">
		   <ec:table items="listgoods" var="GOODS"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/cate/goods_findall.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="选择" width="5%">
					<p align="center">
						<input type="radio" name="goods" value="${GOODS.GOODS_ID }" onclick="upd1()"/>
					</p>	
					</ec:column>
					<ec:column property="GOODS_ID" title="商品ID" width="5%" />
					<ec:column property="NAME" title="类别名称" width="5%" />
					<ec:column property="BAR_CODE" title="条码" width="5%" />
					<ec:column property="GOODS_NAME" title="商品名称" width="5%" />
					<ec:column property="GOODS_DESC" title="商品描述" width="5%" />
					<ec:column property="SELL_PRICE" title="销售单价" width="5%" />
					<ec:column property="HAVE_COUNT" title="库存量" width="5%" />
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
