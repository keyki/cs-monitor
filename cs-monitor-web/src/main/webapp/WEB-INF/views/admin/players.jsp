<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

players

<c:forEach items="${players}" var="player">
<c:out value="${player.name}"/><br />
</c:forEach>

