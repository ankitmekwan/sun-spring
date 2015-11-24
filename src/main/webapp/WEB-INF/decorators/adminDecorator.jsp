<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}">

    <!-- BEGIN HEAD -->
    <head>
        <title>Sun Computers | <spring:message code="${title}" text="Admin Page" /></title>
        <link rel="shortcut icon" href="favicon.ico" />
		<sitemesh:write property='head' />
	</head>
    <!-- END HEAD -->

    <body>
       <sitemesh:write property='body' />
    </body>

</html>