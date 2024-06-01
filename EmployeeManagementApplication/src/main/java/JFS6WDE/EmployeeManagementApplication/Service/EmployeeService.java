package JFS6WDE.EmployeeManagementApplication.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import JFS6WDE.EmployeeManagementApplication.Entities.Employee;

public interface EmployeeService {
	    List<Employee> getAllEmployee();
	  
	    Employee saveEmployee(Employee employee);

	    Employee getEmployeeById(long id);

	    void deleteEmployeeById(long id);
	    
	    Employee updateEmployee(Employee emp);

	    Page<Employee> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection);

}
