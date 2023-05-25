package com.voloshyn.spring.springboot.springboot_rest.dao;


import com.voloshyn.spring.springboot.springboot_rest.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Override

    public List<Employee> getAllEmployees(){
//       Session session = entityManager.unwrap(Session.class);
//       Query query = session.createQuery("from Employee ",Employee.class);
//       List<Employee> allEmployees = query.getResultList();
        Query query = (Query) entityManager.createQuery("from Employee ");
         List<Employee>allEmployees = query.getResultList();
    return allEmployees;
}

    @Override
    public void saveEmployee(Employee employee) {
//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(employee);
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
    }

    @Override
    public Employee getEmployee(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        Employee employee = session.get(Employee.class, id);
        Employee employee = entityManager.find(Employee.class,id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        Query query = session.createQuery("delete from Employee where id =:employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();
        Query query = entityManager.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId",id);
        query.executeUpdate();
    }
}

