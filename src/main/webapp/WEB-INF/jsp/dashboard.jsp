<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%@ taglib uri="/WEB-INF/lib/paginatorTaglib.tld" prefix="pag" %> --%>
<%@ page isELIgnored="false" %>


<jsp:include page="../lib/header.jsp" />


<section id="main">
	<h1 id="homeTitle">${computerCount} Computers found</h1>
<%-- 	${ComputerBean} --%>
	<div id="actions">
		<form action="search" method="GET">
			<input type="search" id="searchbox" name="search"
				value="${sessionScope['search']}" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="addComputerForm">Add Computer</a>
	</div>

		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th><a href="Dashboard?order=name">Computer Name</a></th>
					<th><a href="Dashboard?order=introduced">Introduced Date</a></th>
					<!-- Table header for Discontinued Date -->
					<th><a href="Dashboard?order=discontinued">Discontinued Date</a></th>
					<!-- Table header for Company -->
					<th><a href="Dashboard?order=company.name">Company</a></th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="computer" items="${computerList}">
				<tr>
					<td><a href="editComputerForm?id=${computer.id}" onclick="">${computer.name}</a></td>
					<td>${computer.introduced}</td>
					<td>${computer.discontinued}</td>
					<td>${computer.company.name }</td>
					<td><a href="deleteComputer?id=${computer.id}" class="btn danger">Delete</a>
				</tr>
			</c:forEach>
			</tbody>
		</table>
<%-- 		<pag:Paginator itemsPerPage="10" collectionSize="${computerCount}"></pag:Paginator> --%>
</section>

<jsp:include page="../lib/footer.jsp" />
