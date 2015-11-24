<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<!-- BEGIN LOGIN FORM -->
	<form action="<c:url value='/j_spring_security_check' />" method="post">
		<h3>
			<spring:message code="login.signin" text="Sign In" />
		</h3>
		<c:if test="${param.login_error ne null}">
			<div>
				<span> <spring:message code="login.login_error"
						text="Username / password not valid." />
				</span>
			</div>
		</c:if>
		<c:if test="${param.logout ne null}">
			<div>
				<span> <spring:message code="login.logout_successfully"
						text="Logout successfully." />
				</span>
			</div>
		</c:if>
		<div>
			<label><spring:message code="login.username" text="Username" /></label>
			<input type="text" autocomplete="off" name="username"
				placeholder="<spring:message code="login.username" text="Username" />" />
		</div>
		<div>
			<label><spring:message code="login.password" text="Password" /></label>
			<input type="password" autocomplete="off" name="password"
				placeholder="<spring:message code="login.password" text="Password" />" />
		</div>
		<div>
			<button type="submit">
				<spring:message code="login.login" text="Login" />
			</button>
			<a href="javascript:;" id="forget-password"><spring:message
					code="login.forgot_password" text="Forgot Password ?" /></a>
		</div>
	</form>
	<!-- END LOGIN FORM -->
</body>
</html>