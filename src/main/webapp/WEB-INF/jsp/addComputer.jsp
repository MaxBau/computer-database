<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>
<jsp:include page="../lib/header.jsp" />
<script type="text/javascript" src="<c:url value="/js/validFormAdd.js" />"></script>

<section id="main">
${message}
	<h1>Add Computer</h1>
	<div id="message"></div>
	<form:form action="addComputer" commandName="addComputer" modelAttribute="computerDto" id="formAdd" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<form:input id="nameInput" path="name" />
					<form:errors path="name" cssstyle="color: red;" />
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<form:input type="date" id="introducedInput" path="introduced" />
					<form:errors path="introduced" cssstyle="color: red;" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
				<form:input type="date" id="discontinuedInput" path="discontinued" />
				<form:errors path="discontinued" cssstyle="color: red;" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="companyId">Company Name:</label>
				<div class="input">
				<form:select id="companyInput" path="companyId" >
					<form:errors path="companyId" cssstyle="color: red;" />
						<form:option value="0">--</form:option>
						<c:forEach var="company" items="${companyList}">
							<form:option value="${company.id}">${company.name}</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Add" class="btn primary" />
			or <a href="dashboard" class="btn">Cancel</a> 
		</div>
	</form:form>
</section>

<jsp:include page="../lib/footer.jsp" />