<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<jsp:include page="../lib/header.jsp" />
<script type="text/javascript" src="<c:url value="/js/validFormAdd.js" />"></script>

<section id="main">
Language : <a href="?lang=en">English</a>|<a href="?lang=fr">Fran�ais</a><br>
${message}
	<h1><spring:message code="page.title.add" text="default text" /></h1>
	<div id="message"></div>
	<form:form action="addComputer" modelAttribute="computerDto" id="formAdd" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name"><spring:message code="label.name" text="default text" /></label>
				<div class="input">
					<form:input id="nameInput" path="name" />
					<form:errors path="name" cssStyle="color: red;" />
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced"><spring:message code="label.introduced" text="default text" /></label>
				<div class="input">
					<form:input type="date" id="introducedInput" path="introduced"  />
					<form:errors path="introduced" cssStyle="color: red;" />
					<span class="help-inline"><spring:message code="date.format.views" text="default text" /></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><spring:message code="label.discontinued" text="default text" /></label>
				<div class="input">
				<form:input type="date" id="discontinuedInput" path="discontinued"  />
				<form:errors path="discontinued" cssStyle="color: red;" />
					<span class="help-inline"><spring:message code="date.format.views" text="default text" /></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="companyId"><spring:message code="label.company" text="default text" /></label>
				<div class="input">
				<form:select id="companyInput" path="companyId" items="${companyList}" itemLabel="name" itemValue="id" />

					<form:errors path="companyId" cssStyle="color: red;" />
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="<spring:message code="button.add.title" text="default text" />" class="btn primary" />
			or <a href="dashboard" class="btn"><spring:message code="button.cancel.title" text="default text" /></a> 
		</div>
	</form:form>
</section>

<jsp:include page="../lib/footer.jsp" />