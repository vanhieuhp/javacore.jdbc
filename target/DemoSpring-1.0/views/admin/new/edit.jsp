<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="NewAPI" value="/api-admin-news"/>
<c:url var="NewURL" value="/admin-new"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit News</title>
    <script type="text/javascript"  src="<c:url value='https://cdn.ckeditor.com/4.7.0/standard/ckeditor.js'/>"></script>
</head>
<body>
<form id="formSubmit">
    <div class="row">
        <div class="alert alert-${alert}" role="alert">
            ${message}
        </div>
    </div>
    <div class="row">
        <div class="col-lg-4">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Thumbnail</h6>
                </div>
                <div class="card-body">
                    <input type="Text" id="thumbnail" value="Put Image" name="thumbnail" class="form-control"
                           placeholder="thumbnail">
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Title</h6>
                </div>
                <div class="card-body">
                    <input type="text" id="title" value="${model.title}" name="title" class="form-control"
                           placeholder="title">
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Kind of category</h6>
                </div>
                <div class="card-body">
                    <select class="form-control" id="categoryCode" name="categoryCode" aria-label="Default select example">
                        <c:if test="${empty model.categoryCode}">
                            <option value="">Categories</option>
                            <c:forEach var="item" items="${categories}">
                                <option value="${item.code}">${item.name}</option>
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty model.categoryCode}">
                            <c:forEach var="item" items="${categories}">
                                <option value="${item.code}"
                                        <c:if test="${item.code==model.categoryCode}">selected="selected"</c:if>>
                                        ${item.name}
                                </option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="card shadow mb-4">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Short Description</h6>
                </div>
                <div class="card-body">
                    <div class="form-group purple-border">
                        <textarea class="form-control" id="shortDescription" name="shortDescription"
                                  rows="3">${model.shortDescription}</textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class=col-lg-12>
            <div class="card shadow mb-4">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Content</h6>
                </div>
                <div class="card-body">
                    <div class="form-group purple-border">
                        <textarea class="form-control" id="content" name="content" rows="10">${model.content}</textarea>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" name="id" id="id" value="${model.id}">
    </div>
    <div class="row">
        <c:if test="${not empty model.id}">
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <input class="btn btn-primary btn-user" type="button" value="Update News" id="btnAddOrUpdateNews"/>
            </div>
        </c:if>
        <c:if test="${empty model.id}">
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <input class="btn btn-primary btn-user" type="button" value="Add news" id="btnAddOrUpdateNews"/>
            </div>
        </c:if>
    </div>
</form>
<script type="text/javascript">
    var editor = CKEDITOR.replace('content');

    $("#btnAddOrUpdateNews").click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $("#formSubmit").serializeArray();

        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        })
        data['content'] = editor.getData();
        var id = $("#id").val();
        if (id == "") {
            addNews(data);
        } else {
            updateNews(data);
        }
    });


    function addNews(data) {
        $.ajax({
            url: '${NewAPI}',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${NewURL}?type=edit&id="+ result.id + "&message=adding_success&alert=success"
            },
            error: function (error) {
                window.location.href = "${NewURL}?type=edit&message=error_system&alert=danger"
            }
        });
    }

    function updateNews(data) {
        $.ajax({
            url: '${NewAPI}',
            type: 'PUT',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${NewURL}?type=edit&id="+ result.id + "&message=updating_success&alert=success"
            },
            error: function (error) {
                window.location.href = "${NewURL}?type=edit&id="+ result.id + "&message=error_system&alert=danger"
            }
        });
    }
</script>
</body>
</html>