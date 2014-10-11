<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

	<div class="col-md-12 backLogin">
		<div class="col-md-7"></div>
		
		<div class="col-md-5 wrapper">
		
			<form name="loginForm" action="<c:url value='/j_spring_security_check' />" method="POST"
				class="form-signin"   data-toggle="validator" role="form">
				<fieldset>

						<!-- Form Name -->
						<legend class="form-signin-heading">Login</legend>

						<!-- Email input -->
						<div class="form-group required">
								<input type="text" id="email" class="form-control" 
									name="email"
									pattern="\b(?!.{31})([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4})\b"
									placeholder="Bведіть email"
								 	data-error="Ви ввели некоректний email !" 
								 	required="required" />
								 	
								<div class="help-block with-errors"></div>
						</div>

						<!-- Password input-->
						<div class="form-group required">
								<input type="password" id="password" class="form-control" 
									name="password"
									pattern="\b[A-Za-z0-9._%+-]{6,30}\b"
									placeholder="Bведіть пароль" 
									data-error="Ви ввели некоректний пароль !" 
									required="required"/>
									
								<div class="help-block with-errors"></div>	
						</div>

						<!-- Button of registration-->
						<div class="form-group">
							<button type="submit" class="btn btn-lg btn-primary btn-block" >
								<span class="glyphicon glyphicon-ok"></span>
								Увійти
							</button>
						</div>

				</fieldset>
			</form>
		</div>
	</div>	