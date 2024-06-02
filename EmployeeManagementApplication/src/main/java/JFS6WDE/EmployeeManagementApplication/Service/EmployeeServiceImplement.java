package JFS6WDE.EmployeeManagementApplication.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import JFS6WDE.EmployeeManagementApplication.Entities.Employee;
import JFS6WDE.EmployeeManagementApplication.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImplement implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	// method to create employee
	@Override
	public Employee saveEmployee(Employee emp) {
		return empRepo.save(emp);
	}

	// method to get all Employees
	@Override
	public List<Employee> getAllEmployee() {
		return empRepo.findAll();
	}

	// method to get employee by id
	@Override
	public Employee getEmployeeById(long id) {
		return empRepo.findById(id).get();
	}

	// method to update Employee
	@Override
	public Employee updateEmployee(Employee emp) {
		// Fetch the existing employee from the database
		Optional<Employee> existingEmployeeOpt = empRepo.findById(emp.getId());
	
		if (existingEmployeeOpt.isPresent()) {
			Employee existingEmployee = existingEmployeeOpt.get();
			// Update the fields with the new values
			existingEmployee.setFirstName(emp.getFirstName());
			existingEmployee.setLastName(emp.getLastName());
			existingEmployee.setDepartmentName(emp.getDepartmentName());
			
			// Save the updated employee back to the database
			return empRepo.save(existingEmployee);
		} else {
			// Handle the case when the employee is not found
			throw new RuntimeException("Employee not found with id: " + emp.getId());
		}
	}

	// method to delete Employee
	@Override
	public void deleteEmployeeById(long id) {
		empRepo.deleteById(id);
	}

	// method for pagination
	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		// check if the sorting is ascending or descending
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return empRepo.findAll(pageable);
	}

}
