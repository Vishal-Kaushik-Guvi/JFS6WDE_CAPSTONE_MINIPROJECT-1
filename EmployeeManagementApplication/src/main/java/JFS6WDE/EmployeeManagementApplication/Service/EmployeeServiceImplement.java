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
	public void createEmployee(Employee emp) {
		empRepo.save(emp);
	}

	// method to get all Employees
	@Override
	public List<Employee> getAllEmployee() {
		return empRepo.findAll();
	}

	// method to get employee by id
	@Override
	public Employee getEmployeeById(int id) {
		return empRepo.findById(id).get();
	}

	// method to update Employee
	@Override
	public Employee updateEmployee(Employee emp) {
		// Fetch the existing employee from the database
		Optional<Employee> optionalEmployee = empRepo.findById(emp.getId());

		if (optionalEmployee.isPresent()) {
			Employee empUpdate = optionalEmployee.get();

			// Update the fields with the new values
			empUpdate.setFirstName(emp.getFirstName());
			empUpdate.setLastName(emp.getLastName());
			empUpdate.setDepartmentName(emp.getDepartmentName());

			// Save the updated employee back to the database
			return empRepo.save(empUpdate);
		} else {
			// Handle the case when the employee is not found
			throw new RuntimeException("Employee not found with id: " + emp.getId());
		}
	}

	// method to delete Employee
	@Override
	public void deleteEmployeeById(int id) {
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
