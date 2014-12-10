<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <script type="text/javascript">
  		<!--
  		function mon99(){
  			var d = document.getElementById("sell").value;
  			
  			var dd=/^[0-9]+(.[0-9]{2})?$/;		
  			if(!dd.test(d)){
  				
				document.getElementById("sp").innerHTML="请输入正确的钱数！";
				return false;
  			}else{
  			
  			return true;
  			}
  		}
  		//-->
  </script>
  <title></title>
</head>
<body>
    <div id="container" class="container">
    <div class="hr10"></div> 
    <form id="form1" action="${pageContext.request.contextPath }/admin/cate/goods_save.html" method="post" onsubmit="return mon99();">    
      <table class="m-table-form">
         <tbody>
            <tr>  
            	<th class="tr">商品类型：</th>        
              <td>
                  	<s:select name="goods.cateId" list="#request.category" 

                  	  headerKey="-1" headerValue="请选择" listKey="key" listValue="value.name"

                  	 cssClass="u-slt required validate-selection" cssStyle="width:14.5%">
                  	
                  	</s:select><font color="red">*</font>
              </td>
            </tr> 
            <tr> 
              <th class="tr">条码：</th>
              <td><input type="text" class="u-ipt validate-number max-length-20"  name="goods.barCode" maxlength="20"></td>                                   
            </tr>                       
            <tr>  
              <th class="tr">商品名称：</th>
              <td><input type="text" class="u-ipt required max-length-40"  name="goods.goodsName" maxlength="40"><font color="red">*</font></td>                                   
            </tr>
            <tr>
              <th class="tr">商品描述：</th>
              <td><input type="text" class="u-ipt max-length-40"  name="goods.goodsDesc" maxlength="40"></td>
            </tr>
            <tr>  
              <th class="tr">销售单价：</th>
              <td><input type="text" class="u-ipt required validate-currency-dollar" name="goods.sellPrice" maxlength="10"><font color="red">*</font></td>
            </tr>
            <tr>  
              <th class="tr">库存量：</th>
              <td><input type="text" class="u-ipt required validate-number" name="goods.haveCount" maxlength="9"><font color="red">*</font></td>                                   
            </tr>
            <tr>  
              <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt max-length-60" name="goods.remark" maxlength="60"></td>
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
    </body>
</body>
</html>