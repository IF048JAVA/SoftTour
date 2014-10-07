<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

	<div class="col-md-12 backLogin">
		<div class="col-md-7"></div>
		
		<div class="col-md-5 wrapper">
		
			<sf:form action="enter" method="POST" modelAttribute="user"
				class="form-signin"   data-toggle="validator" role="form">
				<fieldset>

						<!-- Form Name -->
						<legend class="form-signin-heading">Login</legend>

						<!-- Email input-->
						<div class="form-group required">
								<sf:input path="email" id="email" class="form-control" 
									pattern="\b(?!.{31})([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4})\b"
									placeholder="Bведіть email"
								 	data-error="Ви ввели некоректний email !" 
								 	required="required" 
								 	autofocus=""/>
								 	
								<div class="help-block with-errors"></div>
								<sf:errors path="email" cssClass="error" />
						</div>

						<!-- Password input-->
						<div class="form-group required">
								<sf:password path="password" id="password" class="form-control" 
									pattern="\b[A-Za-z0-9._%+-]{6,30}\b"
									placeholder="Bведіть пароль" 
									data-error="Ви ввели некоректний пароль !" 
									required="required"/>
									
								<div class="help-block with-errors"></div>	
								<sf:errors path="password" cssClass="error" />
						</div>

						<!-- Button of registration-->
						<div class="form-group">
							<sf:button type="submit" class="btn btn-lg btn-primary btn-block" >
								<span class="glyphicon glyphicon-ok"></span>
								Увійти
							</sf:button>
						</div>

				</fieldset>
			</sf:form>
		</div>
	</div>	