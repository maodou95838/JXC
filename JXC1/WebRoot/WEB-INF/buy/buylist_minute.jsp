<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title></title>
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/cascadeGoods.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>  
</head>
<body>
    <div id="container" class="container">

    
      <div class="hr10"></div>

          <div class="hr10"></div>
          <h2>采购单基本信息</h2>

         <table class="m-table-form">
             <tbody>
                <tr>
	              <!-- <td><input type="hidden" name="getinbill.billId" value="${getinbill.billId}"></td> -->
                  <th class="tr">采购日期：</th>
				  <td><input type="text" class="u-ipt" class="Wdate" disabled="disabled" name="getinbill.getInDate" value="<s:date name='getinbill.getInDate' format='yyyy-MM-dd'/>" onfocus="WdatePicker()"  /></td>
                  <th class="tr">采购人：</th>
                  <td><input type="text" class="u-ipt"  name="getinbill.buyerPerson" disabled="disabled" value="${getinbill.buyerPerson}"></td>
                  <th class="tr">总支出：</th>
                  <td><input type="text" class="u-ipt validate-currency-dollar" disabled="disabled"  name="getinbill.totalMoney" value="${getinbill.totalMoney}"></td>
                </tr>
           
                
                <tr>
                	<th class="tr">采购单编号：</th>
                	<td colspan="3"><input type="text" class="u-ipt u-ipt-1" disabled="disabled" style="width:85%"  name="getinbill.billCode" value="${getinbill.billCode}"/></td>
                  <th class="tr">备注：</th>
                  <td colspan="3"><input type="text" class="u-ipt u-ipt-1" disabled="disabled" style="width:85%"  name="getinbill.remark" value="${getinbill.remark}"/></td>
                </tr>
             </tbody>
             
          </table>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
          <h2>采购商品详单</h2>
          <table class="m-table">
          <thead>
              <tr>
              	<th class="cola">类别</th>
              	<th class="cola">名称</th>
              	<th class="cola">数量</th>
              	<th class="cola">单价</th>
              	<th class="cola">备注</th>
              </tr>
          </thead>
          </table>
          <c:forEach items="${alllist}" var="list">
          <table class="m-table">
        <tbody id="tbody">
              <tr>
				<td><input type="text" style="border:0px;"  class="u-ipt" disabled="disabled" name="name" value="${list.NAME}"></td>
				<td><input type="text" style="border:0px;"  class="u-ipt" disabled="disabled" name="goodsName" value="${list.GOODS_NAME}"></td>
                <td><input type="text" style="border:0px;"  class="u-ipt" disabled="disabled" name="price" value="${list.PRICE}"></td>  
              	<td><input type="text" style="border:0px;"  class="u-ipt" disabled="disabled" name="connt" value="${list.CONNT}"></td>
              	<td><input type="text" style="border:0px;"  class="u-ipt" disabled="disabled" name="remark" value="${list.REMARK}"></td>
              </tr>
          </tbody>
         
      </table>
      </c:forEach>
	 <table class="m-table-form">
             
             <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="button" class="u-btn" onclick="javascript:history.back()">关闭</button>
                  
                 </td>
               </tr>
             </tfoot>
          </table>

    </div><!-- /#container -->
</body>
</html>