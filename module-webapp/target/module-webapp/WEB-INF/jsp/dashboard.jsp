<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/lib/paginatorTaglib.tld" prefix="pag" %>
<%@ page isELIgnored="false" %>


<jsp:include page="../lib/header.jsp" />

<section id="main">
Language : <a href="?lang=en">English</a>|<a href="?lang=fr">Français</a>
	<h1 id="homeTitle">${computerCount} <spring:message code="page.title.dashboard" text="default text" /></h1>
	
	
<%-- 	${ComputerBean} --%>
	<div id="actions">
		<form action="dashboard" method="POST">
			<input type="text" name="search" id="searchbox" value="${search}" />
			<input type="submit" id="searchsubmit"
				value="<spring:message code="button.filter.title" text="default text" />"
				class="btn primary" />
		</form>
		<a class="btn success" id="add" href="addComputerForm"><spring:message code="button.addComputer.title" text="default text" /></a>
	</div>

		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th><a href="dashboard?order=computer.name"><spring:message code="table.header.name" text="default text" /></a></th>
					<th><a href="dashboard?order=computer.introduced"><spring:message code="table.header.introduced" text="default text" /></a></th>
					<!-- Table header for Discontinued Date -->
					<th><a href="dashboard?order=computer.discontinued"><spring:message code="table.header.discontinued" text="default text" /></a></th>
					<!-- Table header for Company -->
					<th><a href="dashboard?order=company.name"><spring:message code="table.header.company" text="default text" /></a></th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="computer" items="${computerList}">
				<tr>
					<td><a href="editComputerForm?id=${computer.id}" onclick="">${computer.name}</a></td>
					<td>${computer.introduced}</td>
					<td>${computer.discontinued}</td>
					<td>${computer.companyName }</td>
					<td><a href="deleteComputer?id=${computer.id}" class="btn danger"><spring:message code="button.delete.title" text="default text" /></a>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<pag:Paginator itemsPerPage="10" collectionSize="${computerCount}" search="${search}"></pag:Paginator>
</section>

<jsp:include page="../lib/footer.jsp" />
