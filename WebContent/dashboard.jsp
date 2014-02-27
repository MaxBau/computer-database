<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<jsp:include page="include/header.jsp" />


<section id="main">
	<h1 id="homeTitle">${computerCount} Computers found</h1>
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
					<th> </th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="computer" items="${requestScope['computers']}">
				<tr>
					<td><a href="EditComputerServlet?id=${computer.id}&action=edit" onclick="">${computer.name}</a></td>
					<td>${computer.introduced}</td>
					<td>${computer.discontinued}</td>
					<td>${computer.company.name }</td>
					<td><a href="EditComputerServlet?action=delete&id=${computer.id}" class="btn danger">Delete</a>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		Items per page :<select name="itemsPerPage">
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="50">50</option>
							<option value="100">100</option>
						</select>
		<a href="DashboardServlet?action=previousPage" class=btn><</a><a href="DashboardServlet?action=nextPage" class=btn>></a>
</section>

<jsp:include page="include/footer.jsp" />
