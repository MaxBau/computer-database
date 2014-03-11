<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../lib/header.jsp" />
<script type="text/javascript" src="<c:url value="/js/validFormAdd.js" />"></script>
<%@ page isELIgnored="false" %>

</script>
<section id="main">
${message}
	<h1>Edit Computer</h1>
	<div id="message"></div>
	<form action="editComputer" id="formAdd" method="POST">
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
						<c:forEach var="company" items="${companyList}">
							<option value="${company.id}" ${company.id == computer.company.id ? 'selected' : ''}>${company.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<a href="#" onclick="validForm();" class="btn primary">Edit</a>
			or <a href="dashboard" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="../lib/footer.jsp" />