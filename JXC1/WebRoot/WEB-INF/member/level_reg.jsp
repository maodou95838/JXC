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
     <form id="form1" action="${pageContext.request.contextPath }/admin/member/grade_add.html" method="post"> 
      <table class="m-table-form">
         <tbody>
            <tr>  
              <th class="tr">等级名称：</th>
              <td><input type="text" class="u-ipt required" name="grade.gradeName" maxlength="20"><font color="red">*</font></td>
            </tr>  
              <th class="tr">等级描述：</th>
              <td><input type="text" class="u-ipt" name="grade.gradeDesc" maxlength="60"></td>                                   
            </tr>
            </tr>  
              <th class="tr">折扣价：</th>
              <td><input type="text" class="u-ipt required validate-currency-dollar max-value-1" name="grade.gradePercent" maxlength="4"><font color="red">*</font></td>                                   
            </tr>
            
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn">保存</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back()">返回</button>
                 </td>
               </tr>
             </tfoot>                          
      </table>
      </form>
    </div><!-- /#container -->
</body>
</html>