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
     <form id="form1" action="${pageContext.request.contextPath }/admin/member/member_charge.html" method="post">     
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">会员编号：</th>
              <td>${member.memberCode}
              	  <input type="hidden" name="member.memberUuid" value="${member.memberUuid}"/>
              </td>
            </tr>
            <tr>  
              <th class="tr">会员姓名：</th>
              <td>${member.name}</td>
            </tr>
            <tr>  
              <th class="tr">会员称呼：</th>
              <td>${member.name2}</td>                                   
            </tr>
            <tr>  
              <th class="tr">会员电话：</th>
              <td>${member.cellPhone}</td>                                   
            </tr>
            <tr>  
              <th class="tr">会员级别：</th>
              <td>${memberGrade[member.gradeId]}</td>                                   
            </tr>
            <tr>  
              <th class="tr">余额：</th>
              <td><font color="red"><b>${member.leftMoney}</font></b>&nbsp;元
              <input type="hidden" name="member.leftMoney" value="${member.leftMoney}"/>
              </td>                                   
            </tr>
             <tr>  
              <th class="tr">积分：</th>
              <td><font color="red"><b>${member.mark}</font></b>&nbsp;
              <input type="hidden" name="member.mark" value="${member.mark}"/>
              </td>                                   
            </tr>
            <tr>  
              <th class="tr">备注：</th>
              <td>${member.remark}</td>                                   
            </tr>
            <tr>  
              <th class="tr">充值金额：</th>
              <td><input type="text" class="u-ipt required validate-currency-dollar" name="charge.money" maxlength="12"><font color="red">*</font></td>                                   
            </tr>
            <tr>  
              <th class="tr">充值备注：</th>
              <td><input type="text" class="u-ipt" name="charge.remark" maxlength="60"></td>                                   
            </tr>
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn">保存</button>
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