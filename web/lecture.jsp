<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"></jsp:include>

<form action="/sms/Lecture" method="post">
    <table cellspacing="0" cellpadding="0">
        <tr>
            <td>ლექცია: </td>
            <td><input type="text" name="lectureName"></td>
        </tr>
        <tr>
            <td>სტატუსი: </td>
            <td><input type="text" name="lectureStatus"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="submit">
            </td>
        </tr>
    </table>
</form>

<jsp:include page="footer.jsp"></jsp:include>