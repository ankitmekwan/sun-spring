<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}">

    <!-- BEGIN HEAD -->
    <head>
        <title>Sun Computers | <spring:message code="login.login" text="Login" /></title>
        <link rel="shortcut icon" href="favicon.ico" />
	</head>
    <!-- END HEAD -->

    <body>
		<sitemesh:write property='body' />
    </body>
</html>