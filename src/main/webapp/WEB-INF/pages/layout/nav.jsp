<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default" role="navigation" style="margin-top:5px;">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">SoftTour</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/search">Пошук</a></li>
                <li><a href="/hotels">Готелі</a></li>
                <li><a href="/about">Про нас</a></li>
                <li><a href="/feedback">Зворотній зв'язок</a></li>
            </ul>
          
            <ul class="nav navbar-nav navbar-right">
             
               <security:authorize access="hasRole('ROLE_ANONYMOUS')">
	                <li><a href="/login">Увійти</a></li>
	                <li><a href="/registration">Реєстрація</a></li>
                </security:authorize>
             
                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                	  	<c:url value="/j_spring_security_logout" var="logoutUrl" />
                	 
                	   <form action="${logoutUrl}" method="post" id="logoutForm"></form>
						<script>
							function formSubmit() {	document.getElementById("logoutForm").submit();	}
						</script>
                	  	
                	  	<li><a>${pageContext.request.userPrincipal.name}</a></li>
						<li>
                			<a href="/userProfile">Mій профіль</a>
                		</li>
                	   <li>
                	   		<c:if test="${pageContext.request.userPrincipal.name != null}">
								<a href="javascript:formSubmit()">Вийти</a>
							</c:if>
                	   </li>
                </security:authorize>
                
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>