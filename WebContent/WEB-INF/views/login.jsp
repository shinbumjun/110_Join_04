<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en" >
<head>

  	<script type="text/javascript">
		//컨트롤러에서 보낸 메세지가 있을 경우
		window.onload = function(e){ 
			
			var resultMsg = '${resultMsg}';
			var resultCode = '${resultCode}';
			if(resultMsg.length > 0){
				alert(resultMsg);
				// 메시지를 한 번 보여준 후 resultMsg 값을 비웁니다.
		        resultMsg = '';
			}
		}
	</script>

  <meta charset="UTF-8">
  <title>CodePen - Flat Login Form 3.0</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<!-- ===== CSS 주의할점 : /resources/css/style.css resource안에 css파일 안에 style.css를 넣는다.  ===== -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>
<!-- partial:index.partial.html -->
<!-- Form Mixin-->
<!-- Input Mixin-->
<!-- Button Mixin-->
<!-- Pen Title-->
<div class="pen-title">
  <h1>Flat Login Form</h1><span>Pen <i class='fa fa-paint-brush'></i> + <i class='fa fa-code'></i> by <a href='http://andytran.me'>Andy Tran</a></span>
</div>
<!-- Form Module-->
<div class="module form-module">
  <div class="toggle"><i class="fa fa-times fa-pencil"></i>
    <div class="tooltip">Click Me</div>
  </div>
  <div class="form">
    <h2>Login to your account</h2>
    <form action="/spring/login.do" method="post">
      <input name="member_id" type="text" placeholder="Username"/>
      <input name="passwd" type="password" placeholder="Password"/>
      <button>Login</button>
    </form>
  </div>
  <div class="form">
    <h2>Create an account</h2>
    <form action="/spring/join.do" method="post">
      <input name="member_id" type="text" placeholder="Username"/>
      <input name="passwd" type="password" placeholder="Password"/>
      <input name="email" type="email" placeholder="Email Address"/>
      <input name="Phone" type="tel" placeholder="Phone Number"/>
      <button>Register</button>
    </form>
  </div>
  <div class="cta"><a href="http://andytran.me">Forgot your password?</a></div>
</div>
<!-- partial -->
  <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://codepen.io/andytran/pen/vLmRVp.js'></script>

  <!-- 주의할점 : /resources/js/script.js설정시 resources안에 js파일안에 script.js 넣을 것 -->
  <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>

</body>
</html>