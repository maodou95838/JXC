<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>

<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title></title>
  <script type="text/javascript">
  		function tijiao(){
  			var total = document.getElementById("totalPrice");
  			var t1 = total.value;
  			var t2 = parseInt(t1);//积分 
  			document.getElementById("mark").value=t2;
  		}
  </script>
</head>
<body>
    <div id="container" class="container">

      <div class="hr10"></div>
	  <div class="hr10"></div>
      <h2>会员信息</h2>
<form id="form1" action="${pageContext.request.contextPath}/admin/sellbill/billsearch_disposepage.html" method="post">
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">会员编号：</th>
              <td><input type="text" style="border:0px;" disabled="disabled" name="member.memberCode" value="${member.memberCode }">
              	  <input type="hidden" name="member.memberUuid" value="${member.memberUuid }">
              </td>
              <th class="tr">会员称呼：</th>
              <td><input type="text" style="border:0px;" disabled="disabled" name="member.name" value="${member.name }"></td>
              <th class="tr">会员电话：</th>
              <td><input type="text" style="border:0px;" disabled="disabled" name="member.cellPhone" value="${member.cellPhone }"></td>   
              <th class="tr">余额：</th>
              <td><font color="red"><input type="text" style="border:0px;" disabled="disabled" name="member.leftMoney" value="${member.leftMoney}"></font></td>                
            </tr>
         </tbody>                           
      </table>
      <div class="hr10"></div>
      <div class="hr10">&nbsp;</div>
      <h2>销售单基本信息</h2>

      <table class="m-table-form">
         <tbody>
         	
            <tr>
              <th class="tr">销售单单号：</th>
              <td><input type="text" class="u-ipt  required" name="sellbill.sellBillCode" readonly="readonly"  value="${sellbill.sellBillCode}"></td>
              <th class="tr">销售日期：</th>
              <td><input type="text" class="u-ipt  required" readonly="readonly" class="Wdate" 
				  name="sellbill.sellDate"  value="<s:date name='sellbill.sellDate'  format='yyyy-MM-dd'/>" 
				   /><font color="red">*</font></td>
              <th class="tr">顾客描述：</th>
              <td><input type="text" class="u-ipt" readonly="readonly"  name="sellbill.customerDesc" value="${sellbill.customerDesc}"></td>                                   
            </tr>
            
            <tr>
              <th class="tr">总价：</th>
              <td><input type="text" class="u-ipt" readonly="readonly" id="totalPrice"  name="sellbill.totalPrice" value="${sellbill.totalPrice }">元
              	  <input type="hidden" id="mark"  name="sellbill.mark" value="${sellbill.mark }">
              	  <input type="hidden"  name="sellbill.isExecBill" value="0">
              </td>
              <th class="tr">折扣价：</th>
              <td ><input type="text" class="u-ipt" readonly="readonly" name="sellbill.percentPrice" value="${sellbill.percentPrice }">元</td>
              <th class="tr">实收：</th>
              <td ><input type="text" class="u-ipt " readonly="readonly"  name="sellbill.actualMoney" value="${sellbill.actualMoney }">元</td>
            </tr>
            <tr>
              <th class="tr">销售备注：</th>
              <td colspan="6"><textarea name="sellbill.remark" readonly="readonly" cols="90" rows="4" class="u-ipt">${sellbill.remark}     	
              	</textarea></td>                 
            </tr>
            <tr>
              <th class="tr">异常单标识：</th>
              <td colspan="6"><input type="checkbox" disabled="disabled" checked="checked"  name="" value="1"><font color="red">权限外登记单，必须标识</font></td>                 
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
          <tbody id="tbody">
        	  <tr style="display: none">
              	<td >
                    <input type="hidden" name="sellbill.sellBillId">
                 	<s:select list="#request.category"  id="cateId" name="goods.cateId" listKey="key" listValue="value" headerKey="-1" headerValue="--请选择--"
                  	 cssClass="u-slt required validate-selection" cssStyle="width:80%" onchange="cascade2(this.id)"></s:select>
                  </td>
              	<td>
                  	<select name="goodsIds" id="goodsId" Class="u-slt required validate-selection" cssStyle="width:60%" ></select>
                  </td>  
                <td><input type="text" class="u-ipt required validate-currency-dollar" name="prices" ></td> 
              	<td><input type="text" class="u-ipt required validate-number max-length-5" name="connt" ></td>
              	<td><input type="text" class="u-ipt" name="remark"></td>
              	<td><input type="button" class="u-btn u-btn-c4" onclick="delRow('tbody', this)" value="删除"/></td>
              </tr> 
      <c:forEach items="${alllist}" var="list" varStatus="count">
      		<tr>
              <td><input type="text" style="border:0px;" readonly="readonly"  class="u-ipt"  name="name" value="${list.NAME}"></td>
				<td><input type="text" style="border:0px;" readonly="readonly"  class="u-ipt"  name="goodsName" value="${list.GOODS_NAME}"></td>
              	<td><input type="text" style="border:0px;" readonly="readonly" class="u-ipt"  name="connt" value="${list.SELL_COUNT}"></td>
                <td><input type="text" style="border:0px;" readonly="readonly" class="u-ipt"  name="price" value="${list.PRICE}"></td>  
              	<td><input type="text" style="border:0px;" readonly="readonly" class="u-ipt"  name="remark" value="${list.REMARK}"></td>
    		</tr>
     </c:forEach>
          </tbody>
         
      </table>
   
      <div class="hr10">&nbsp;</div>
             <h2>销售单处理信息</h2>

      <table class="m-table-form">
         <tbody>
         	
            <tr>
              <th class="tr">出单员：</th>
              <td>
              <input type="hidden" name="sellbill.sellBillId"  value="${sellbill.sellBillId }">
              <input type="text" style="border:0px;" readonly="readonly" class="u-ipt" name="sellbill.billPerson" value="${sellbill.billPerson}"></td>                                  
           	   <th class="tr">处理意见：</th>
              <td><input type="text"  class="u-ipt" name="sellbill.recoveryRemark" value="${sellbill.recoveryRemark}"></td>
            </tr>
            
  
              
           
         </tbody>
      <div class="hr10">&nbsp;</div>   
             <tfoot>
               <tr>
                 <td colspan="4" class="tc">
                   <button type="submit" class="u-btn" onclick="tijiao()">处理</button>
                   &emsp;
                   <button type="reset" class="u-btn">取消</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back()">返回</button>
                 </td>
               </tr>
             </tfoot>
          </table> 
          </form>
</body>
</html>