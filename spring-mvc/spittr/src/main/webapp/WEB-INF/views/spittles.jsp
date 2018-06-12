<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
    <head>
        <title>Spittr</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">
    </head>

    <body>
        <div class="spittleForm">
            <h1>Spit it out...</h1>
            <form method="POST" name="spittleForm">
                <input type="hidden" name="latitude">
                <input type="hidden" name="longitude">
                <textarea name="message" cols="80" rows="5"></textarea><br/>
                <input type="submit" value="Add" />
            </form>
        </div>
        <div class="listTitle">
            <h1>Recent Spittles</h1>
            <ul class="spittleList">
                <li id="spittle_<c:out value="spittle.id"/>">
                    <!-- main content of message -->
                    <div class="spittleMessage">
                        <c:out value="${spittle.message}"/>
                    </div>
                    <!-- timestamp and location info -->
                    <div>
                        <span class="spittleTime"><c:out value="${spittle.time}"/></span>
                        <span class="spittleLocation">(<c:out value="${spittle.latitude}"/>, <c:out value="${spittle.longitude}"/>)</span>
                    </div>
                </li>
            </ul>
        </div>

    </body>
</html>