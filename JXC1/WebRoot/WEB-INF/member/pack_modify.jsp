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
  	function changeInputs() {
  		var selector = document.getElementById("selectOperType");
  		var value = selector.options[selector.selectedIndex].value;
  		var putArea = document.getElementsByName("cun1");
  		var getArea = document.getElementsByName("get1");
  		//var getGoodsId = document.getElementById("getGoodsId").value;
  		
  		//var goodsCmnt = document.getElementById("cateId");
  		
  		if (value == "1") {
  			for (var i=0; i<putArea.length; i++) {
  				putArea[i].style.display = "";
  			}
  			
  			for (var i=0; i<getArea.length; i++) {
  				getArea[i].style.display= "none";
  			}
  			
	  	} else if (value == "2") {
	  		for (var i=0; i<putArea.length; i++) {
  				putArea[i].style.display = "none";
  			}
  			
  			for (var i=0; i<getArea.length; i++) {
  				getArea[i].style.display= "";
  			}
	  	}
  	}
  </script>
</head>
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
    <form action="${pageContext.request.contextPath }/admin/member/pack_modify.html" method="post" id="form1">
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">存/取：</th>
              <td> <s:select list="#request.putGetMap" id="selectOperType" cssClass="u-slt" 
              onchange="changeInputs()" value="1" cssStyle="width: 23%" name="memberPack.purOrGet"/>
              	<input type="hidden" name="memberPack.packUuid" value="${memberPack.packUuid}"/>
              </td>
            </tr>
         	<tr name="cun1">
              <th class="tr" >存放日期：</th>
              <td><input type="text" class="u-ipt" class="Wdate required" name="memberPack.leftDate" 
                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" value="<s:date name='memberPack.leftDate' format='yyyy-MM-dd'/>">
                  <input type="hidden" name="memberPack.memberUuid" value="${member.memberUuid }"/> </td>
            </tr>
            <tr>  
              <th class="tr">商品名称：</th>
              <td>
              	<s:select list="#request.packGoodsList"  id="cateId" listKey="goodsId" listValue="goodsName" 
              		headerKey="" headerValue="--请选择--" value="memberPack.goodsId" disabled="true"
                  	cssClass="u-slt validate-selection" cssStyle="width:23%" name="memberPack.goodsId"	/> 
                 <input type="hidden" id="getGoodsId" value="${memberPack.goodsId}" 	  />      	
              </td>
            </tr> 
            <tr name="cun1"> 
              <th class="tr">存放数量：</th>
              <td><input type="text" class="u-ipt required validate-number min-value-0" name="memberPack.leftCount"
              value="${memberPack.leftCount }" maxlength="3"></td>                                   
            </tr>
            <tr name="cun1">  
              <th class="tr">存单备注：</th>
              <td><input type="text" class="u-ipt" name="memberPack.remark" maxlength="40" value="${memberPack.remark}"></td>                                   
            </tr>
             <tr name="get1" style="display:none"> 
              <th class="tr">剩余数量：</th>
              <td><input type="text" class="u-ipt"  value="${memberPack.leftCount1}" disabled="disabled"/> </td>                                   
            </tr>
            <tr name="get1" style="display:none"> 
              <th class="tr">取走时间：</th>
              <td><input type="text" class="u-ipt" class="Wdate required" name="memberPack.lastAwayTime" 
                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" 
                  value="<s:date name='memberPack.lastAwayTime' format='yyyy-MM-dd'/>"></td>                                   
            </tr>
             <tr name="get1" style="display:none">  
              <th class="tr">本次取走数量：</th>
              <td><input type="text"  class="u-ipt validate-number min-value-1 lessequal-than-value1" name="memberPack.lastAwayCount"
               value="${memberPack.lastAwayCount}" maxlength="3" ></td>                                   
            </tr>
            <tr name="get1" style="display:none">  
              <th class="tr">取单备注：</th>
              <td><input type="text" class="u-ipt" name="memberPack.lastRemark" value="${memberPack.lastRemark}" maxlength="40"></td>                                   
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
    </div><!-- /#container -->
</body>
<script type="text/javascript">
	var time = '${memberPack.lastAwayTime}';
	if (time == "") {
		document.getElementById("selectOperType").disabled="disabled";
	}
</script>

</html>