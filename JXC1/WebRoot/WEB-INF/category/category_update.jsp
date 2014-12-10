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
    <form id="form1" action="${pageContext.request.contextPath}/admin/cate/category_update.html" method="post">   
      <table class="m-table-form">
         <tbody>
         	
         	<tr>
              <th class="tr">类别编号：</th>
              <td><input type="text"  class="u-ipt required  max-length-30" disabled="disabled" name="category.cateId"  value="${category.cateId}">
              <input type="hidden"  class="u-ipt required  max-length-30" name="category.cateId"  value="${category.cateId}"></td>
            </tr>
            <tr>  
              <th class="tr">类别名称：</th>
              <td><input type="text" class="u-ipt required max-length-20" name="category.name" value="${category.name}"><font color="red">*</font></td>
            </tr>  
            <tr>
              <th class="tr">类别描述：</th>
              <td><input type="text" class="u-ipt max-length-40" name="category.cateDesc" value="${category.cateDesc}"></td>                                   
            </tr>
            <tr>  
              <th class="tr">负责人：</th>
              <td><input type="text" class="u-ipt required validate-chinese max-length-20" name="category.managePerson" value="${category.managePerson}"><font color="red">*</font></td>                                   
            </tr>
            <tr>  
              <th class="tr">计量单位：</th>
              <td><input type="text" class="u-ipt required max-length-5" name="category.countUnit" value="${category.countUnit}"><font color="red">*</font></td>                                   
            </tr>    
            <tr>
              <th class="tr">是否可存单：</th>
              <td><s:select  list="#{1:'是',0:'否'}" cssStyle="width:21%"  listKey="key" listValue="value" cssClass="u-ipt"  name="category.canPack"></s:select></td>
            </tr>
            <tr>
              <th class="tr">是否可入库：</th>
              <td><s:select  list="#{1:'是',0:'否'}" cssStyle="width:21%"  listKey="key" listValue="value" cssClass="u-ipt"  name="category.isCountIn"></s:select></td>
            </tr>
            
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn">修改</button>
                   &emsp;
                   <button type="reset" class="u-btn">取消</button>
                 </td>
               </tr>
             </tfoot>                          
      </table>
      </form>
    </div><!-- /#container -->
  	<!--javascript start-->

    </script>
  	<!--javascript end-->
</body>
</html>