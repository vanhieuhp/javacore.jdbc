<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="NewAPI" value="/api-admin-news"/>
<c:url var="NewURL" value="/admin-new"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="https://kit.fontawesome.com/yourcode.js" crossorigin="anonymous"></script>
</head>

<body>
    <form action="<c:url value='/admin-new'/>" id="formSubmit" method="GET">
        <div class="alert alert-${alert}" role="alert">
        ${message}
    </div>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th><input type="checkbox" id="checkbox"/></th>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Short Description</th>
                    <th>
                        <c:url var="editURL" value="/admin-new">
                            <c:param name="type" value="edit"/>
                        </c:url>
                        <a data-toggle="tooltip" title="Add news" href="${editURL}" class="btn btn-primary btn-user" >
                            <i class="fas fa-plus-circle"></i>
                        </a>

                    </th>
                    <th>
                        <button id="btnDelete" type="button"
                        class="btn btn-google btn-user" data-toggle="tooltip" title="Delete News" >
                            <i class='fas fa-trash-alt' ></i>
                        </button>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${model.getListResult()}">
                    <tr>
                        <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                        <td>${item.id}</td>
                        <td>${item.title}</td>
                        <td>${item.shortDescription}</td>
                        <td>
                            <c:url var="editURL" value="/admin-new">
                                <c:param name="type" value="edit"/>
                                <c:param name="id"  value="${item.id}"/>
                            </c:url>
                            <a data-toggle="tooltip" title="Edit news" href = "${editURL}"
                               class="btn btn-primary btn-user">
                                <i class="fas fa-edit"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination" id="pagination"></ul>
        </nav>
        <input type="hidden" value="list" id="type" name="type"/>
        <input type="hidden" value="" id="currentPage" name="currentPage" />
        <input type="hidden" value="" id="maxPageItem" name="maxPageItem" />
        <input type="hidden" value="" id="sortName" name="sortName" />
        <input type="hidden" value="" id="sortBy" name="sortBy" />
    </form>

    <script type="text/javascript">
        var currentPage = ${model.getCurrentPage()};
        var totalPages = ${model.getTotalPage()};
        var limit = 3;
        $(function (){
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPages,
                visiblePages: 3,
                startPage: currentPage,
                onPageClick: function (event, page) {
                    if (page != currentPage) {
                        document.getElementById("maxPageItem").setAttribute('value', limit),
                            document.getElementById("currentPage").setAttribute('value', page),
                            document.getElementById("sortName").setAttribute('value', 'id'),
                            document.getElementById("sortBy").setAttribute('value', ''),
                            $("#formSubmit").submit();
                    }
                }
            });
        });

        $(document).ready(function() {
            $("#btnDelete").click(function(){
                alert("Click Button");
            var data={};
            var ids = $('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            data['ids'] = ids;
            deteteNews(data);
        })});

        function deteteNews(data) {
            $.ajax({
                url: '${NewAPI}',
                type: 'DELETE',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
                success: function (result) {
                    window.location.href = "${NewURL}?type=list&currentPage=${model.getCurrentPage()}&maxPageItem=3&message=deleting_success&alert=success"
                },
                error: function (error) {
                    window.location.href = "${NewURL}?type=list&currentPage=${model.getCurrentPage()}&maxPageItem=3&message=error-system&alert=danger"
                }
            });
        }
    </script>
</body>

</html>