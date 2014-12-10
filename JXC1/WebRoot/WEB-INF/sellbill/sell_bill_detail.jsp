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
      <h2>会员信息</h2>

      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">会员编号：</th>
              <td>${memberAndBill.member_code}</td>
              <th class="tr">会员称呼：</th>
              <td>${memberAndBill.name}</td>
              <th class="tr">会员电话：</th>
              <td>${memberAndBill.cell_phone}</td>   
              <th class="tr">余额：</th>
              <td><font color="red">${memberAndBill.left_money} 元</font></td>                
            </tr>
         </tbody>                           
      </table>
      
      <div class="hr10"></div>
      <div class="hr10">&nbsp;</div>
      <h2>销售单基本信息</h2>

      <table class="m-table-form">
         <tbody>
         	
            <tr>
              <th class="tr">销售单编号：</th>
              <td>${memberAndBill.sell_bill_id}</td>
              <th class="tr">销售日期：</th>
              <td>${memberAndBill.sell_date}</td>
              <th class="tr">顾客描述：</th>
              <td>${memberAndBill.customer_desc}</td>                                   
            </tr>
            
            <tr>
              <th class="tr">总价：</th>
              <td>${memberAndBill.total_price}元</td>
              <th class="tr">折扣价：</th>
              <td >${memberAndBill.percent_price}元</td>
              <th class="tr">实收：</th>
              <td >${memberAndBill.actual_money}元</td>
            </tr>
            <tr>
              <th class="tr">销售备注：</th>
              <td colspan="6">
              	${memberAndBill.remark}        	
              	</td>                 
            </tr>
            <tr>
              <th class="tr">异常单标识：</th>
              <td colspan="6"><input type="checkbox" name="" value="${memberAndBill.is_exec_bill}" disabled="disabled"><font color="red">权限外登记单，必须标识</font></td>                 
            </tr>
         </tbody>
         
      </table>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
          <h2>销售商品详单</h2>

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
          <tbody>
          	 <s:iterator value="#request.goodsList" status="st">
              <tr <s:if test="#st.even">
              	  class="even"
              	  </s:if>
              >
              	<td>${cate_id}</td>
              	<td>${goods_name}</td>
              	<td>${sell_count}</td>
              	<td>${price}</td>
              	<td>${remark}</td>              	
              	</tr>
              </s:iterator>	
              <tr class="even">
              </tr>                           
          </tbody>
      </table>
		
		<div class="hr10"></div>
      <div class="hr10">&nbsp;</div>
      <h2>销售单处理信息</h2>

      <table class="m-table-form">
         <tbody>
         	
            <tr>
              <th class="tr">出单员：</th>
              <td>${memberAndBill.bill_person}</td>
              <th class="tr">处理意见：</th>
              <td>${memberAndBill.recovery_remark}</td>                                   
            </tr>
         </tbody>
      <div class="hr10">&nbsp;</div>
             
             <tfoot>
               <tr>
                 <td colspan="4" class="tc">                   
                   <button type="button" class="u-btn" onclick="javascript:history.back()">返回</button>
                 </td>
               </tr>
             </tfoot>
          </table>   

    </div><!-- /#container -->
  	<!--javascript end-->
</body>
</html>