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
  	function cascadeReason() {
		var status = jQuery("#isLogout").attr("checked");
		var reason = jQuery("#logoutReason");
		if (status == "checked") {
			reason.attr("disabled", false);
		} else {
			reason.attr("disabled", true);
		}
  	}
  </script>
</head>
<body>
    <div id="container" class="container">
    <div class="hr10"></div>   
     <form id="form1" action="${pageContext.request.contextPath }/admin/member/grade_modify.html" method="post">   
      <table class="m-table-form">
         <tbody>
            <tr>  
              <th class="tr">等级名称：</th>
              <td><input type="hidden" name="grade.gradeId" value="${grade.gradeId}"/>
              <input type="text" class="u-ipt" name="grade.gradeName" value="${grade.gradeName}" maxlength="20"><font color="red">*</font></td>
            </tr>  
              <th class="tr">等级描述：</th>
              <td><input type="text" class="u-ipt" name="grade.gradeDesc" value="${grade.gradeDesc}" maxlength="60"></td>                                   
            </tr>
            </tr>  
              <th class="tr">折扣价：</th>
              <td><input type="text" class="u-ipt" name="grade.gradePercent"  class="u-ipt required validate-currency-dollar max-value-1" value="${grade.gradePercent}" maxlength="4"><font color="red">*</font></td>                                   
            </tr>
            </tr>  
              <th class="tr">注销：</th>
              <td><s:checkbox name="grade.isLogout" onclick="cascadeReason()" id="isLogout"/></td>                                   
            </tr>
            </tr>  
              <th class="tr">注销原因：</th>
              <td><input type="text" class="u-ipt" name="grade.logoutReason" value="${grade.logoutReason}" id="logoutReason" maxlength="60"
              	<s:if test='grade.isLogout!="false"'>
              	   disabled="disabled"
              	</s:if>
              ></td>                                   
            </tr>
            
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn">修改</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back();">取消</button>
                 </td>
               </tr>
             </tfoot>                          
      </table>
      </form>
    </div><!-- /#container -->
  	<!--javascript start-->
</body>
</html>