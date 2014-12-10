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
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/cascadeGoods.js'></script> 
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
  <script type="text/javascript">
  	function setButtonUse1() {
	 	var myRadios = document.getElementsByName("mySelector");
		var memberId;
		var packId;
		var maintPack = document.getElementById("maintPack");
		var getPack = document.getElementById("getPack");
		var savePack = document.getElementById("savePack");
		
		for (var i=0; i<myRadios.length; i++) {
			if (myRadios[i].checked) {
				savePack.disabled = "";
				savePack.style.color  = "";
				
				packId = myRadios[i].id;
				memberId = myRadios[i].value;
				if (memberId != "") {
					getPack.disabled = "";
					getPack.style.color  = "";
					
					maintPack.disabled = "";
					maintPack.style.color  = "";
				} else {
					getPack.disabled = "disabled";
					getPack.style.color = "gray";
					
					maintPack.disabled = "disabled";
					maintPack.style.color  = "gray";
				}
				
				break;
			} else {
				savePack.disabled = "disabled";
				savePack.style.color  = "gray";
				
				getPack.disabled = "disabled";
				getPack.style.color  = "gray";
				
				maintPack.disabled = "disabled";
				maintPack.style.color  = "gray";
			}
			
		}
		
		return [memberId, packId];
		
 	}
  	
  	function saveBtnEvent() {
  		var v = setButtonUse1();
  		window.location='${pageContext.request.contextPath}/admin/member/pack_saveUI.html' +
  						'?member.memberUuid=' + v[0];
  						
  	}
  	
  	function maintBtnEvent() {
  		var v = setButtonUse1();
  		if (v[0] == "" || v[1] == "") {
  			return;
  		}
  		window.location='${pageContext.request.contextPath}/admin/member/pack_modifyUI.html' +
  						'?member.memberUuid=' + v[0] + '&memberPack.packUuid=' + v[1];
  		
  	}
  	
  	function getPackBtnEvent() {
  		var v = setButtonUse1();
  		if (v[1] == "") {
  			return;
  		}
  		window.location='${pageContext.request.contextPath}/admin/member/pack_getPackUI.html' +
  						'?member.memberUuid=' + v[0] + '&memberPack.packUuid=' + v[1];
  	}
  	
  </script>
</head>
<body>
    <div id="container" class="container">
    
      <div class="hr10"></div>

          <div class="hr10"></div>
          <h2>会员存单查询</h2>
		   <form id="form1" action="${pageContext.request.contextPath}/admin/member/pack_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">会员电话号码：</th>
                  <td><input type="text" class="u-ipt" name="member.cellPhone" value="${member.cellPhone}"></td>
                  <th class="tr">会员称呼：</th>
                  <td><input type="text" class="u-ipt" name="member.name2" value="${member.name2}"></td>                     
                </tr>
                 <tr>
                  <th class="tr">会员级别：</th>
                  <td><s:select list="#request.memberGrade" headerKey="" headerValue="--请选择--" 
                  cssClass="u-slt" cssStyle="width: 42%" name="member.gradeId"/></td>
                  <th class="tr">会员编号：</th>
                  <td><input type="text" class="u-ipt" name="member.memberCode" value="${member.memberCode}"></td>                
                </tr>
                <tr>	
                 <th class="tr">商品类型：</th>
                  <td >
                 	<s:select list="#request.category"  id="cateId" listKey="key" listValue="value.name" headerKey="" headerValue="--请选择--"
                  	 cssClass="u-slt " cssStyle="width:42%" onchange="cascade2(this.id, 'goodsId')"/>
                  </td>
                  <th class="tr" width="11%">商品名称：</th>
                  <td>
                  	<select name="memberPack.goodsId" id="goodsId"
        			 class="u-slt " style="width:42%" >
        			 	<option value="-1">--请选择--</option>
        			 </select>
                  </td>  
                  </tr>  
                <tr>                  
                  <th class="tr">存放日期：</th>
                  <td><input type="text" class="u-ipt" class="Wdate" name="memberPack.leftDate" 
                  value="<s:date name='memberPack.leftDate' format='yyyy-MM-dd'/>" 
                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"></td>  
                   <th class="tr">是否剩余：</th>
                  <td >
                  	<s:select list="#request.surplus"  name="memberPack.leftCount1" 
                  	listKey="key" listValue="value" headerKey="0" headerValue="--请选择--"
                  	 cssClass="u-slt " cssStyle="width:42%" />
                  	</td>                   
                </tr>
             </tbody>
             <tfoot>
               <tr>
                 <td colspan="4" class="tc">
                   <button type="submit" class="u-btn" >查询</button>&emsp;                   
                   <button type="button" class="u-btn" disabled="disabled" style="color:gray" id="savePack"
                   onclick="saveBtnEvent()">存单</button>&emsp;
                   <button type="button" class="u-btn"  disabled="disabled" style="color:gray" id="getPack"
                   onclick="getPackBtnEvent()">取单</button>&emsp;    
                   <button type="button" class="u-btn" disabled="disabled" style="color:gray" id="maintPack" 
                   onclick="maintBtnEvent()">维护</button>              
                 </td>
               </tr>
             </tfoot>
          </table>
          </form>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>会员存单信息列表</h2>
	 	<div align="center">
		   <ec:table items="packList" var="pack1"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/member/pack_find.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="_0" title="选择" width="7%" >
						<p align="center"><input type="radio" id="${pack1.PACK_UUID}" value="${pack1.MEMBER_UUID}" name="mySelector" 
						onclick="setButtonUse1()"></p>
					</ec:column>
					<ec:column property="NAME" title="会员姓名" width="7%" />
					<ec:column property="GRADE_ID" title="级别" width="7%" mappingItem="memberGrade"/>
					<ec:column property="CELL_PHONE" title="电话号码" width="7%" />
					<ec:column property="GOODS_NAME" title="存放商品名称" width="7%" />
					<ec:column property="LEFT_DATE" title="存放时间" width="7%" />
					<ec:column property="LEFT_COUNT" title="存放数量" width="7%" />
					<ec:column property="LEFT_COUNT1" title="剩余数量" width="7%"/>
					<ec:column property="LAST_AWAY_TIME" title="上次取走时间" width="7%"/>
					<ec:column property="LAST_AWAY_COUNT" title="上次取走数量" width="7%"/>
				</ec:row>
			</ec:table>
		</div>
      <div class="hr10"></div>
		
    </div><!-- /#container -->
  	<!--javascript start-->
</body>
</html>