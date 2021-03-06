<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="include/header.jsp" />
<script type="text/javascript" src="validFormAdd.js">

</script>
<section id="main">
${message}
	<h1>Edit Computer</h1>
	<div id="message"></div>
	<form action="EditComputer" id="formAdd" method="POST">
		<fieldset>
		<div class="clearfix">
				<label for="id">Computer id:</label>
				<div class="input">
					<input type="text" name="id" value="${computer.id}" readonly/>
					<span class="help-inline">Required</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" id="nameInput" value="${computer.name}" />
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate" id="introducedInput" value="${computer.introduced}" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" id="discontinuedInput" value="${computer.discontinued}" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company" id="companyInput">
						<option value="0">--</option>
						<c:forEach var="company" items="${requestScope['companies']}">
							<option value="${company.id}" ${company.id == computer.company.id ? 'selected' : ''}>${company.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<a href="#" onclick="validForm();" class="btn primary">Edit</a>
			or <a href="Dashboard" class="btn">Cancel</a> or <a href="EditComputer?action=delete&id=${computer.id}" class="btn">Delete</a>
		</div>
	</form>
</section>

<jsp:include page="include/footer.jsp" />