<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../lib/header.jsp" />
<script type="text/javascript" src="<c:url value="/js/validFormAdd.js" />"></script>
<%@ page isELIgnored="false" %>

<section id="main">
Language : <a href="?lang=en">English</a>|<a href="?lang=fr">Français</a><br>
${message}
	<h1><spring:message code="page.title.edit" text="default text" /></h1>
	<div id="message"></div>
	<form:form action="editComputer" modelAttribute="computerDto" id="formAdd" method="POST">
		<fieldset>
		<div class="clearfix">
				<label for="id"><spring:message code="label.id" text="default text" /></label>
				<div class="input">
					<form:input id="nameInput" path="id" readonly="true"/>
					<form:errors path="id" cssstyle="color: red;"></form:errors>
					<span class="help-inline">Required</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="name"><spring:message code="label.name" text="default text" /></label>
				<div class="input">
					<form:input id="nameInput" path="name"  />
					<form:errors path="name" cssstyle="color: red;"></form:errors>
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced"><spring:message code="label.introduced" text="default text" /></label>
				<div class="input">
					<form:input type="date" id="introducedInput"  path="introduced" pattern="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])"/>
					<form:errors path="introduced" cssstyle="color: red;"></form:errors>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><spring:message code="label.discontinued" text="default text" /></label>
				<div class="input">
				<form:input type="date" id="discontinuedInput"  path="discontinued" pattern="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])"/>
				<form:errors path="discontinued" cssstyle="color: red;"></form:errors>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="companyId"><spring:message code="label.company" text="default text" /></label>
				<div class="input">
				<form:select id="companyInput" path="companyId" items="${companyList}" itemLabel="name" itemValue="id" />
					<form:errors path="companyId" cssstyle="color: red;" ></form:errors>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<a href="#" onclick="validForm();" class="btn primary"><spring:message code="button.edit.title" text="default text" /></a>
			or <a href="dashboard" class="btn"><spring:message code="button.cancel.title" text="default text" /></a>
		</div>
	</form:form>
</section>

<jsp:include page="../lib/footer.jsp" />