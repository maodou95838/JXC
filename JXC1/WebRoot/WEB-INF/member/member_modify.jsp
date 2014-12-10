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
  	 <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/rennwalMember.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
  <script type="text/javascript">
  	function cascadeReason() {
		var status = jQuery("#isLogout").attr("checked");
		var reason = jQuery("#logoutReason");
		if (status == "checked") {
			reason.attr("disabled", false);
		} else {
			reason.attr("disabled", true);
		}
  	}
  	
  
  	 function singleCellPhone(phoneNumber) {
  	 	 var uuid = document.getElementById("uuid");
  		 rennwalMember.findByphone(phoneNumber, uuid.value, function(member) {
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
</head>
<body>
    <div id="container" class="container">
    <div class="hr10"></div>
     <form id="form1" action="${pageContext.request.contextPath }/admin/member/member_modify.html" method="post">      
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">会员编号：</th>
              <td><input type="text" class="u-ipt" name="member.memberCode" maxlength="6" value="${member.memberCode}">
              	<input type="hidden" name="member.memberUuid" value="${member.memberUuid}" id="uuid"/>
              </td>
            </tr>
            <tr>  
              <th class="tr">会员姓名：</th>
              <td><input type="text" class="u-ipt" name="member.name" maxlength="20" value="${member.name}"></td>
            </tr>  
            <tr>
              <th class="tr">会员称呼：</th>
              <td><input type="text" class="u-ipt required" name="member.name2" maxlength="20" value="${member.name2}"><font color="red">*</font></td>                                   
            </tr>
             <tr>  
              <th class="tr">会员电话：1.</th>
              <td><input type="text" class="u-ipt required validate-number" name="member.cellPhone" maxlength="15" value="${member.cellPhone}" onblur="singleCellPhone(this.value)">
              <font color="red">*</font>
              <span id="notSingle" style="display : none">电话号码已经存在！</span>
              </td>                                   
            </tr>
            <tr> 
              <th>2.</th>
              <td><input type="text" class="u-ipt validate-number" name="member.cellPhone1" maxlength="15" value="${member.cellPhone1}"></td>
            </tr>
            <tr>  
              <th class="tr">性别：</th>
              <td><s:select list="#request.sex" headerKey="" 
              headerValue="--请选择--" cssClass="u-slt validate-selection" cssStyle="width: 20%" name="member.sex"/><font color="red">*</font></td>                                   
            </tr>
            <tr>  
              <th class="tr">出生日期：</th>
              <td><input type="text" class="u-ipt required" class="Wdate" name="member.birthday" value="<s:date name='member.birthday' format='yyyy-MM-dd'/>" 
                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  /></td>                                   
            </tr>
            <tr>  
              <th class="tr">会员级别：</th>
              <td><s:select list="#request.memberGrade" headerKey="" 
              headerValue="--请选择--" cssClass="u-slt validate-selection" cssStyle="width: 20%" name="member.gradeId"/><font color="red">*</font></td>                                   
            </tr>
            <tr>  
              <th class="tr">充值余额：</th>
              <td><input type="hidden" name="member.leftMoney" value="${member.leftMoney}"/>${member.leftMoney}元</td>                                   
            </tr>
              	<s:if test="member.lastChargeUuid != null">
             <tr>  
              <th class="tr">上次充值余额：</th>
              <td>
              		<input type="text" class="u-ipt required validate-currency-dollar" name="charge.money" maxlength="12" value="${member.remark1}"><font color="red">*</font>元
              		<input type="hidden" name="charge.remark" value="${member.remark1}"/>
              		<input type="hidden" name="charge.chargeUuid" value="${member.lastChargeUuid}"/>
              		<input type="hidden" name="member.mark" value="${member.mark}"/>
              </td>                                                            
            </tr>
              	</s:if>
            <tr>  
              <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt" name="member.remark" maxlength="60" value="${member.remark}"></td>                                   
            </tr>
            </tr>  
              <th class="tr">注销：</th>
              <td><s:checkbox name="member.isLogout" onclick="cascadeReason()" id="isLogout"/></td>                                   
            </tr>
            </tr>  
              <th class="tr">注销原因：</th>
              <td><input type="text" name="member.logoutReason" class="u-ipt" id="logoutReason" maxlength="60"
              	<s:if test='member.isLogout!="false"'>
              	   disabled="disabled"
              	</s:if>></td>                                   
            </tr>
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn" id="submitBtn">保存</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back();">取消</button>
                 </td>
               </tr>
             </tfoot>                          
      </table>
     </form> 
    </div><!-- /#container -->
</body>
</html>