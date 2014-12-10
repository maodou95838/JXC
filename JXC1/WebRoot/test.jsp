<%@ page language="java" pageEncoding="utf-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
<title>jquery ajax</title>  
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">  
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
<meta http-equiv="description" content="This is my page">  
<script src="${pageContext.request.contextPath}/assets/js/libs/jquery-1.8.3.min.js" type="text/javascript"></script>  
<script language="javascript">  
    $(function(){   
        $('.sumbit').click(function(){   
            if($('#account').val().length==0){   
                $('.hint').text("sssss").css({"background-color":"green"});    
            }else{   
                $.ajax({   
                    url:'http://dskb.snbw.cn/KBGame/questions/1.1.json.action',   
                    //data:{account:$('#account').val()},   
                    dataType:'json', 
                    error:function(){   
                        alert("error occured!!!");   
                    },   
                    success:function(data){   
                        $.each(data.jsonArray,function(index){   
                            $.each(data.jsonArray[index],function(key,value){   
                                alert(key+":"+value)   
                            });   
                        });   
                           
                        $('body').append("<div>"+data.account+"</div>").css("color","red");   
                    }   
                });   
            }   
        });   
    });   
</script>  
</head>  
  
<body>  
<h3 align="center">jquery AjaX</h3>  
<hr>  
<label>test</label>  
<input id="account" name="account" type="text">  
<input class="sumbit" type="button" value="">  
<div class="hint"></div>  
</body>  
</html>  