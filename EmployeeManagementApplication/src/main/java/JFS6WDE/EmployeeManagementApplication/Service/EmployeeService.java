package JFS6WDE.EmployeeManagementApplication.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import JFS6WDE.EmployeeManagementApplication.Entities.Employee;

public interface EmployeeService {
	    List<Employee> getAllEmployee();
	  
	    void createEmployee(Employee employee);

	    Employee getEmployeeById(int id);

	    void deleteEmployeeById(int id);
	    
	    Employee updateEmployee(Employee emp);

	    Page<Employee> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection);

}
