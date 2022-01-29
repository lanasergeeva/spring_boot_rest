package com.lana.spring.spring_course_springboot.dao;

import com.lana.spring.spring_course_springboot.entity.Employee;
import org.hibernate.Session;
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
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Employee").list();
    }
    @Override
    public void saveEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from Employee"
                + " where id =:employeeId").setParameter("employeeId", id);
        query.executeUpdate();
    }
}

