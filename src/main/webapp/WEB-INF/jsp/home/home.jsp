<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>Simplify your links</h1>Supports only http, https
		<br />
		<input class="input_text" placeholder="Your original URL here">
		<button class= "btn_short">Shorten URL</button>
	</div>
	<br/>
	<a href="" class="btn_moveurl">
	<span class="short_link" style="font-family: Arial, Helvetica, sans-serif; font-size: 10pt;"></span>
	</a>
	<br/>
	<script src="/static/jquery.min.js"></script>
	<script type="text/javascript">
		$(".btn_short").click(function(){
			var url = $('.input_text').val().trim();
			if(url == "") {
				alert("URL을 적어주세요");
				return false;
			}
			
			var r = /^(market|http|https):\/\/[^ "]+$/;
			if(!r.test(url)) {
				alert("http://, https:// 를 입력해주세요");
				return false;
			}
			
			var encodedURL = encodeURIComponent(url);
			
			$.ajax({      
		        type:"POST",  
		        url:"/api/shorten",
		        data: { 'url' : encodedURL },
		        success:function(resp){   
		        	console.log(resp);
					if (resp.code == 0) {
						console.log(resp.data);
						$(".btn_moveurl").prop("href", resp.data.originalUrl);
						$(".short_link").text("http://localhost:10880/" + resp.data.shortUrl);
					} else {
						alert(resp.message); 
					}
		        },   
		        error:function(e){  
		            alert(e.responseText);
		        }  
		    });
			
		});
	</script>
</body>
</html>
