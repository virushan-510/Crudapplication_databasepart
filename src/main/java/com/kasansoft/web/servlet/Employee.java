package com.kasansoft.web.servlet;

import com.kasansoft.web.entity.Department;
import com.kasansoft.web.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "Employee",urlPatterns = "/employee")
public class Employee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("test");

        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();

            Department department=new Department();
            department.setName("Finance");

            entityManager.persist(department);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String position=req.getParameter("position");
        String hireDate=req.getParameter("hire_date");
        String departmentId=req.getParameter("department");
        String salary=req.getParameter("salary");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = dateFormat.parse(hireDate);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();

            try{
                transaction.begin();

                Department department=entityManager.find(Department.class,Integer.parseInt(departmentId));
                com.kasansoft.web.entity.Employee employee=new com.kasansoft.web.entity.Employee();
                employee.setName(name);
                employee.setPosition(position);
                employee.setHireDate(sqlDate);
                employee.setSalary(Double.parseDouble(salary));
                employee.setDepartment(department);

                entityManager.persist(employee);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        resp.getWriter().write(name);
    }
}
