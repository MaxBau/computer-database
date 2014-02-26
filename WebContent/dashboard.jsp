<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<jsp:include page="include/header.jsp" />


<section id="main">
	<h1 id="homeTitle">${fn:length(computers)} Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="AddComputerServlet">Add Computer</a>
	</div>

		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date</th>
					<!-- Table header for Company -->
					<th>Company</th>
				</tr>
			</thead>
			<tbody>
<c:forEach var="computer" items="${requestScope['computers']}">
	<tr>
		<td><a href="EditComputerServlet?id=${computer.id}" onclick="">${computer.name}</a></td>
		<td>${computer.introduced}</td>
		<td>${computer.discontinued}</td>
		<td>${computer.company.name }</td>
	</tr>
</c:forEach>
			</tbody>
		</table>
</section>

<jsp:include page="include/footer.jsp" />
