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
        <h6 class="m-0 font-weight-bold text-primary">Lista użytkowników</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Nazwa</th>
                    <th>Email</th>
                    <th>Akcja</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th>Id</th>
                    <th>Nazwa</th>
                    <th>Email</th>
                    <th>Akcja</th>
                </tr>
                </tfoot>
                <tbody>

                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                        <td>${user.email}</td>
                        <td>
                        <a href="${pageContext.request.contextPath}/user/delete"> Usuń </a>
                        <a href="${pageContext.request.contextPath}/user/edit?userId=${user.id}"> Edytuj </a>
                        <a href="${pageContext.request.contextPath}/user/show?userId=${user.id}"> Pokaż </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</div>
<!-- /.container-fluid -->
</div>
</div>
</div>
<!-- End of Main Content -->
<jsp:include page="/footer.jsp"/>
</html>
