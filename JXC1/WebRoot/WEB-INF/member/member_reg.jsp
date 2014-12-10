<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/rennwalMember.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
  <script type='text/javascript'>
  	 function singleCellPhone(phoneNumber) {
  		 rennwalMember.findByphone(phoneNumber, function(member) {
  			 if (member != null) {
  				 document.getElementById("notSingle").style.display = "";
				document.getElementById("submitBtn").disabled = "disabled";
  			 } else {
  				 document.getElementById("notSingle").style.display = "none";
				document.getElementById("submitBtn").disabled = "";
  			 }
  			 
  		 });
  	 }
  </script>
  <title></title>
</head>
<body>
    <div id="container" class="container">
    <div class="hr10"></div>  
     <form id="form1" action="${pageContext.request.contextPath }/admin/member/member_reg.html" method="post">    
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">会员编号：</th>
              <td><input type="text" class="u-ipt" name="member.memberCode" maxlength="6"></td>
            </tr>
            <tr>  
              <th class="tr">会员姓名：</th>
              <td><input type="text" class="u-ipt" name="member.name" maxlength="20"></td>
            </tr>  
            <tr>
              <th class="tr">会员称呼：</th>
              <td><input type="text" class="u-ipt required" name="member.name2" maxlength="20"><font color="red">*</font></td>                                   
            </tr>
            <tr>  
              <th class="tr">会员电话：1.</th>
              <td><input type="text" class="u-ipt required validate-number" name="member.cellPhone" maxlength="15" onblur="singleCellPhone(this.value)">
              <font color="red">*</font>
              <span id="notSingle" style="display : none">电话号码已经存在！</span>
              </td>                                   
            </tr>
            <tr> 
              <th>2.</th>
              <td><input type="text" class="u-ipt validate-number" name="member.cellPhone1" maxlength="15"></td>
            </tr>
            <tr>  
              <th class="tr">性别：</th>
              <td><s:select list="#request.sex" headerKey="" 
              headerValue="--请选择--" cssClass="u-slt validate-selection" cssStyle="width: 20%" name="member.sex"/><font color="red">*</font></td>                                   
            </tr>
            <tr>  
              <th class="tr">出生日期：</th>
              <td><input type="text" class="u-ipt required" class="Wdate" name="member.birthday" 
                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  /></td>                                   
            </tr>
            <tr>  
              <th class="tr">会员级别：</th>
              <td><s:select list="#request.memberGrade" headerKey="" 
              headerValue="--请选择--" cssClass="u-slt validate-selection" cssStyle="width: 20%" name="member.gradeId"/><font color="red">*</font></td>                                   
            </tr>
<%--            <tr>  --%>
<%--              <th class="tr">充值金额：</th>--%>
<%--              <td><input type="text" class="u-ipt validate-currency-dollar" name="member.leftMoney" maxlength="12">元</td>                                   --%>
<%--            </tr>--%>
            <tr>  
              <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt" name="member.remark" maxlength="60"></td>                                   
            </tr>
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn" id="submitBtn">保存</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back()">取消</button>
                 </td>
               </tr>
             </tfoot>                          
      </table>
      </form>
    </div><!-- /#container -->
</body>
</html>