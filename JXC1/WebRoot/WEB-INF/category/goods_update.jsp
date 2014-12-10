 <%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>

<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <script type="text/javascript">
  		<!--
  		function sellprice(){
  			var d = document.getElementById("sell").value;
  			
  			var dd=/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;		
  			if(!dd.test(d)){
  				
				document.getElementById("sp").innerHTML="<font color='red'>请输出正确的钱数！</font>";

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
   <form id="form1" action="${pageContext.request.contextPath}/admin/cate/goods_update.html" method="post" onsubmit="return sellprice();">    
      <table class="m-table-form">
         <tbody>
         	</tr>  
              <th class="tr">商品编号：</th>
              <td><input type="text" class="u-ipt max-length-30" name="goods.goodsId" disabled="disabled" value="${goods.goodsId}"><input type="hidden" class="u-ipt max-length-30" name="goods.goodsId" value="${goods.goodsId}"/></td>                                   
            </tr>
            <th class="tr">类型名称：</th>
                  <td>
                  	<s:select list="#request.category" name="goods.cateId"  headerKey="-1" headerValue="--请选择--" listValue="value.name"
                  	 cssClass="u-slt required validate-selection" cssStyle="width:14.5%" listKey="key" listValue="value.name"></s:select><font color="red">*</font>
                  </td>
            <tr> 
              <th class="tr">条码：</th>
              <td><input type="text" class="u-ipt required validate-number max-length-20" id="3" name="goods.barCode" value="${goods.barCode}"><font color="red">*</font></td>                                   
            </tr>                       
            <tr>  
              <th class="tr">商品名称：</th>
              <td><input type="text" class="u-ipt max-length-20" id="3" name="goods.goodsName" value="${goods.goodsName}"></td>                                   
            </tr>
            <tr>
              <th class="tr">商品描述：</th>
              <td><input type="text" class="u-ipt required max-length-40" id="4" name="goods.goodsDesc" value="${goods.goodsDesc}"></td>
            </tr>
            <tr>  
              <th class="tr">销售单价：</th>
              <td><input type="text" class="u-ipt required validate-currency-dollar" name="goods.sellPrice" value="${goods.sellPrice}" maxlength="10"><font color="red">*</font></td>
            </tr>
            <tr>  
              <th class="tr">库存量：</th>
              <td><input type="text" class="u-ipt required validate-number" id="6" name="goods.haveCount" value="${goods.haveCount}" maxlength="9"><font color="red">*</font></td>                                   
            </tr>
            <tr>  
              <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt max-length-60"  id="7" name="goods.remark" value="${goods.remark}"></td>
            </tr>   
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                 	<input class="u-btn" type="submit" value="提交" />
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
  	<!--javascript start-->
    </script>
  	<!--javascript end-->
</body>
</html>