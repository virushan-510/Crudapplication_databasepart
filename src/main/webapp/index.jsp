<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="com.kasansoft.web.util.JPAUtil" %>
<%@ page import="javax.persistence.EntityTransaction" %>
<%@ page import="com.kasansoft.web.entity.Department" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Nuwan
  Date: 8/18/2023
  Time: 11:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    try{
        transaction.begin();

        List<Department> departments = entityManager.createQuery("SELECT d FROM Department d", Department.class)
                .getResultList();
        pageContext.setAttribute("departments",departments);

        transaction.commit();
    }catch (Exception e){
        e.printStackTrace();
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>hi</h1>
<form action="employee" method="POST">
    <input name="name" type="text" id="name" placeholder="name">
    <input name="position" type="text" id="position" placeholder="position">
    <input name="hire_date" type="date" id="hire_date" placeholder="hire_date">
    <input name="department" type="text" id="department" placeholder="department">

    <select class="form-select" id="customer">
        <option value="0">Select Customer</option>
        <c:forEach var="department" items="${departments}">
            <option value="${department.id}">${department.name}</option>
        </c:forEach>
    </select>
    <input name="salary" type="number" id="salary" placeholder="salary">
    <input type="submit" value="Submit">
</form>
</body>
</html>
