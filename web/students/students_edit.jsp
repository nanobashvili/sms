<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../header.jsp"></jsp:include>

<div style="margin: 20px;">
    <form action="/sms/Students" method="post">
        <input type="hidden" name="edit" value="${isEdit}">
        <input type="hidden" name="id" value="${student.id}">

        <table cellspacing="10" cellpadding="10" border="1" style="border-collapse: collapse">
            <tr>
                <td>სახელი: </td>
                <td><input type="text" name="firstname" value="${student.firstName}"></td>
            </tr>
            <tr>
                <td>გვარი: </td>
                <td><input type="text" name="lastname" value="${student.lastName}"></td>
            </tr>
            <tr>
                <td>დაბ. თარიღი: </td>
                <td><input type="text" name="birthdate" value="${student.birthDate}"></td>
            </tr>
            <tr>
                <td>სქესი: </td>
                <td>
                    <select name="gender">
                        <c:forEach items="${genders}" var="gender">
                            <option value="${gender.value}">${gender.value}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>ლექცია: </td>
                <td><input type="text" name="lecture" value="${student.lecture}"></td>
            </tr>
            <tr>
                <td>სტატუსი: </td>
                <td>
                    <select name="status">
                        <c:forEach items="${statusList}" var="status">
                            <option value="${status.value}">${status.value}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>პ. ნომერი: </td>
                <td><input type="text" name="pn" value="${student.pn}"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="submit">
                </td>
            </tr>
        </table>
    </form>
</div>


<jsp:include page="../footer.jsp"></jsp:include>