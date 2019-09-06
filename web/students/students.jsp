<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../header.jsp"></jsp:include>

<div style="margin: 20px;">
    <table cellpadding="10" cellspacing="10" border="1" style="border-collapse: collapse">
        <tr>
            <td>ID</td>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Birth Date</td>
            <td>Gender</td>
            <td>Private Number</td>
            <td>Status</td>
            <td>Lecture Name</td>
            <td>Lecture State</td>
            <td>#</td>
        </tr>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.birthDate}</td>
                <td>${student.gender}</td>
                <td>${student.pn}</td>
                <td>${student.status}</td>
                <td>${student.lecture.lectureName}</td>
                <td>${student.lecture.state}</td>
                <td>
                    <a href="/sms/Students?action=add">[ + ]</a>
                    <a href="/sms/Students?action=edit&id=${student.id}">[ * ]</a>
                    <a href="/sms/Students?action=delete&id=${student.id}">[ x ]</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<jsp:include page="../footer.jsp"></jsp:include>