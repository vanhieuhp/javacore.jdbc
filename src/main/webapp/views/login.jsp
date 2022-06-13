<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<div class="d-flex justify-content-center h-100">
    <div class="card">
        <div class="alert alert-${alert}" role="alert">
            ${message}
        </div>
        <div class="card-header">
            <h3>Sign In</h3>
        </div>
        <div class="card-body">
            <form action="<c:url value = '/login?action=login'/>" id="formLogin" method="POST">
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" value="" name = "username" class="form-control" placeholder="username">

                </div>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-key"></i></span>
                    </div>
                    <input type="password" value="" name="password" class="form-control" placeholder="password">
                </div>
                <button type="submit" class="btn float-right login_btn" >Login</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>