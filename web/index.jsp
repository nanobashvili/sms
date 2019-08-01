<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="header.jsp"></jsp:include>

<form action="/sms/Students" method="post">
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td>სახელი: </td>
      <td><input type="text" name="firstname"></td>
    </tr>
    <tr>
      <td>გვარი: </td>
      <td><input type="text" name="lastname"></td>
    </tr>
    <tr>
      <td>დაბ. თარიღი: </td>
      <td><input type="text" name="birthdate"></td>
    </tr>
    <tr>
      <td>სქესი: </td>
      <td><input type="text" name="gender"></td>
    </tr>
    <tr>
      <td>ლექცია: </td>
      <td><input type="text" name="lecture"></td>
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
      <td><input type="text" name="pn"></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="submit">
      </td>
    </tr>
  </table>
</form>


<jsp:include page="footer.jsp"></jsp:include>