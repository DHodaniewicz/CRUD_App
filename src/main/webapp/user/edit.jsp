<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html lang="en">
<jsp:include page="/header.jsp"/>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
        <a href="${pageContext.request.contextPath}/user/list" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i> Lista użytkowników</a>
    </div>

    <h4>Edytuj użytkownika: </h4>


    <!-- Content Row -->

    <div class="row">

        <form action="/user/edit" method="POST">
            <div class="form-group">
                <label for="userName">Nazwa użytkownika</label>
                <input type="text" class="form-control" id="userName" name="userName" value="${user.userName}" required>
            </div>
            <div class="form-group">
                <label for="userEmail">Email address</label>
                <input type="email" name="userEmail" class="form-control" id="userEmail" value="${user.email}" aria-describedby="emailHelp" required >
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="userPassword">Password</label>
                <input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="Hasło" minlength="8" reqired>
            </div>
            <input type="hidden" name="id" value="${user.id}"/>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>



    </div>
    <!-- /.container-fluid -->
</div>
</div>
</div></div>
</div>
<!-- End of Main Content -->


<jsp:include page="/footer.jsp"/>
</html>
