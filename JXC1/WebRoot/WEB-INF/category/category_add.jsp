<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
    <form id="form1" action="${pageContext.request.contextPath }/admin/cate/category_save.html" method="post">    
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">类别编号：</th>
              <td><input type="text"  class="u-ipt validate-number max-length-30" disabled="disabled" name="category.cateId"  value="${category.cateId}">
              <input type="hidden"  class="u-ipt validate-number max-length-30" name="category.cateId"  value="${category.cateId}"></td>
            </tr>
         	<tr>
              <th class="tr">类别名称：</th>
              <td><input type="text" class="u-ipt required max-length-10" id="1" name="category.name" maxlength="10"><font color="red">*</font></td>
            </tr>
            <tr>  
              <th class="tr">类别描述：</th>
              <td><input type="text" class="u-ipt max-length-40" id="2" name="category.cateDesc"></td>
            </tr> 
            <tr> 
              <th class="tr">负责人：</th>
              <td><input type="text" class="u-ipt required validate-chinese max-length-20" id="3" name="category.managePerson"><font color="red">*</font></td>                                   
            </tr>                       
            <tr>  
              <th class="tr">计量单位：</th>
              <td><input type="text" class="u-ipt required max-length-5" name="category.countUnit"><font color="red">*</font></td>                                   
            </tr>
            <tr>
              <th class="tr">是否可存单：</th>
              <td><s:select  list="#{1:'是',0:'否'}" cssStyle="width:21%"  listKey="key" listValue="value" cssClass="u-ipt required"  name="category.canPack"></s:select></td>
            </tr>
            <tr>
              <th class="tr">是否可入库：</th>
              <td><s:select  list="#{1:'是',0:'否'}" cssStyle="width:21%"  listKey="key" listValue="value" cssClass="u-ipt required"  name="category.isCountIn"></s:select></td>
            </tr>
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                 	<input class="u-btn" type="submit" value="提交"/>
<%--                   <button type="button" class="u-btn" onclick="javascript:$('#form1').submit()">保存</button>--%>
                   &emsp;
<%--                   <button type="reset" class="u-btn">取消</button>--%>
					<input class="u-btn" type="reset" value="重置"/>
                 </td>
               </tr>
             </tfoot>                          
      </table>
      </form>
    </div><!-- /#container -->
    <script type="text/javascript">
    	
    </script>
    </body>"
</body>
</html>