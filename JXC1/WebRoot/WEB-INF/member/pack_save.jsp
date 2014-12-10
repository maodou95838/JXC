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
              <td><font color="red">${member.leftMoney } 元</font></td>                
            </tr>
            <tr>
              <th class="tr">会员等级：</th>
              <td>${memberGrade[member.gradeId]}</td>
              <th class="tr">备注信息：</th>
              <td>${member.remark}</td>
              <th class="tr">是否注销：</th>
              <td>${logout[member.isLogout]}</td>   
              <th class="tr">积分：</th>
              <td>${member.mark}</td>                
            </tr>
         </tbody>                           
      </table>
    <div class="hr10">&nbsp;</div>   
    <h2>存单信息</h2> 
    <form action="${pageContext.request.contextPath }/admin/member/pack_save.html" method="post" id="form1">
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">存放日期：</th>
              <td><input type="text" class="u-ipt" class="Wdate required" name="memberPack.leftDate" 
                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})">
                  <input type="hidden" name="memberPack.memberUuid" value="${member.memberUuid }"/> 
                  
                  </td>
            </tr>
            <tr>  
              <th class="tr">商品名称：</th>
              <td>
              	<s:select list="#request.packGoodsList"  id="cateId" listKey="goodsId" listValue="goodsName" 
              		headerKey="" headerValue="--请选择--"
                  	cssClass="u-slt validate-selection" cssStyle="width:21%" name="memberPack.goodsId"	/>
              </td>
            </tr> 
            <tr> 
              <th class="tr">存放数量：</th>
              <td><input type="text" class="u-ipt required validate-number" name="memberPack.leftCount" maxlength="3"></td>                                   
            </tr>
            <tr>  
              <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt" name="memberPack.remark" maxlength="40"></td>                                   
            </tr>
            
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn">保存</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back()">取消</button>
                 </td>
               </tr>
             </tfoot>                          
      </table>
      </form>
    </div><!-- /#container -->
  	<!--javascript start-->
</body>
</html>