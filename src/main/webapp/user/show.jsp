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
        <a href="${pageContext.request.contextPath}/user/add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkownika</a>
    </div>

    <!-- Content Row -->


    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Szczegóły uzytkownika</h6>
        </div>
        <table class="table">
            <tbody>
            <tr>
                <td>Id użytkownika:</td>
                <td>${user.id}</td>
            </tr>
            <tr>
                <td>Nazwa użytkownika</td>
                <td>${user.userName}</td>
            </tr>
            <tr>
                <td>Adres e-mail</td>
                <td>${user.email}</td>
            </tr>
            </tbody>
        </table>

    </div>




</div>
<!-- /.container-fluid -->
</div>
</div>
</div>
<!-- End of Main Content -->
<jsp:include page="/footer.jsp"/>
</html>
