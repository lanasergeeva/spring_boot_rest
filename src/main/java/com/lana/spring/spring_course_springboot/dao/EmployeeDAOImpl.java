package com.lana.spring.spring_course_springboot.dao;

import com.lana.spring.spring_course_springboot.entity.Employee;
//import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
        //Session session = entityManager.unwrap(Session.class);
        Query query = entityManager.createQuery("from Employee");
        List<Employee> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void saveEmployee(Employee employee) {
        //Session session = entityManager.unwrap(Session.class);
        //session.saveOrUpdate(employee);
        Employee merge = entityManager.merge(employee);
        employee.setId(merge.getId());
    }

    @Override
    public Employee getEmployee(int id) {
        //Session session = entityManager.unwrap(Session.class);
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        /*  Session session = entityManager.unwrap(Session.class);*/
        Query query = entityManager.createQuery("delete from Employee"
                + " where id =:employeeId").setParameter("employeeId", id);
        query.executeUpdate();
    }
}

