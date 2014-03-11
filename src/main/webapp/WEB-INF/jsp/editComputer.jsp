<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="../lib/header.jsp" />
<script type="text/javascript" src="<c:url value="/js/validFormAdd.js" />"></script>
<%@ page isELIgnored="false" %>

<section id="main">
Language : <a href="?lang=en">English</a>|<a href="?lang=fr">Français</a>
${message}
	<h1><spring:message code="page.title.edit" text="default text" /></h1>
	<div id="message"></div>
	<form action="editComputer" id="formAdd" method="POST">
		<fieldset>
		<div class="clearfix">
				<label for="id"><spring:message code="label.id" text="default text" /></label>
				<div class="input">
					<input type="text" name="id" value="${computer.id}" readonly/>
					<span class="help-inline">Required</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="name"><spring:message code="label.name" text="default text" /></label>
				<div class="input">
					<input type="text" name="name" id="nameInput" value="${computer.name}" />
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced"><spring:message code="label.introduced" text="default text" /></label>
				<div class="input">
					<input type="date" name="introducedDate" id="introducedInput" value="${computer.introduced}" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><spring:message code="label.discontinued" text="default text" /></label>
				<div class="input">
					<input type="date" name="discontinuedDate" id="discontinuedInput" value="${computer.discontinued}" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company"><spring:message code="label.company" text="default text" /></label>
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
			<a href="#" onclick="validForm();" class="btn primary"><spring:message code="button.edit.title" text="default text" /></a>
			or <a href="dashboard" class="btn"><spring:message code="button.cancel.title" text="default text" /></a>
		</div>
	</form>
</section>

<jsp:include page="../lib/footer.jsp" />