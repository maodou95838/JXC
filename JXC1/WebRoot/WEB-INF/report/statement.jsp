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

  	function click2(){
  		var dd = document.getElementById("years").value;
  		    
  			if(dd!=""){
			  			var ss = parseInt(dd);
			  			var time = parseInt(new Date().getFullYear());
			  			if(1900>ss||ss>time){
				  			alert("请输入正确的年份！");
				  			return false;	
			  			}	  		
	  		}

  	}
  </script>
</head>
<body>
    <div id="container" class="container">

    
      <div class="hr10"></div>

          <div class="hr10"></div>
          <h2>按月份查询</h2>
 		<form id="form1" action="${pageContext.request.contextPath}/admin/basic/report_findAll.html" method="post">
          <table class="m-table-form">
             <tbody id="tbody">
                 <tr>
                  <th class="tr">按年份：</th> 
                  	<td><input type="text" class="u-ipt validate-number max-length-4"  name="sellbill.years" id="years" ></td>
                  <th class="tr">按月份：</th>
                  <td><s:select  list="#{0:'请选择',1:'一月份',2:'二月份',3:'三月份',4:'四月份',5:'五月份',6:'六月份',7:'七月份',8:'八月份',9:'九月份',10:'十月份',11:'十一月份',12:'十二月份'}" name="sellbill.num"  listKey="key" listValue="value" cssClass="u-ipt"></s:select><br></td>      
                  <th class="tr">按季度：</th>
                  <td><s:select  list="#{0:'请选择',1:'第一季度',2:'第二季度',3:'第三季度',4:'第四季度'}" name="sellbill.jidu"  listKey="key" listValue="value" cssClass="u-ipt"></s:select><br></td>  
                  <th class="tr">按半年：</th>
                  <td><s:select  list="#{0:'请选择',6:'上半年段',7:'下半年段'}" name="sellbill.quarter"  listKey="key" listValue="value" cssClass="u-ipt"></s:select><br></td> 
                  <td colspan="4" class="tc">
                   <button type="submit" class="u-btn" onclick="click2()"  >查询</button>               
                 </td>
                </tr>		                           
             </tbody>
          </table>
         </form>
         

          <h2>报表</h2>

          <div align="center">
		   <ec:table items="list" var="list"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/basic/report_findAll.html"
				rowsDisplayed='20' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="GOODS_NAME" title="商品名称" width="10%" />
					<ec:column property="SELL_COUNT" title="销售数量" width="10%" />
					<ec:column property="PRICE" title="单价" width="10%" />
					<ec:column property="TOTAL_PRICE" title="销售总金额" width="10%" />
					<ec:column property="HAVE_COUNT" title="库存量" width="10%" />
				</ec:row>
			</ec:table>
		</div>
      <div class="hr10"></div>
		
    </div><!-- /#container -->
</body>
</html>