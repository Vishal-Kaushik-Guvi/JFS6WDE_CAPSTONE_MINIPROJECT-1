package JFS6WDE.EmployeeManagementApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import JFS6WDE.EmployeeManagementApplication.Entities.Employee;
import JFS6WDE.EmployeeManagementApplication.Service.EmployeeService;

@RestController
@RequestMapping("/api")
public class SwaggerController {

    @Autowired
    private EmployeeService empService;

    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> createEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("departmentName") String departmentName) {
        Employee employeeDetails = new Employee();
        employeeDetails.setFirstName(firstName);
        employeeDetails.setLastName(lastName);
        employeeDetails.setDepartmentName(departmentName);

        Employee updatedEmployee = empService.saveEmployee(employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }
    
    @GetMapping("/find/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return empService.getEmployeeById(id);
    }

    @GetMapping("/allEmployee")
    public List<Employee> getAllEmployee() {
        return empService.getAllEmployee();
    }

    @PutMapping("/updateEmployee")
    public Employee updateEmployee(@RequestParam("id") int id, 
                                 @RequestParam("firstName") String firstName, 
                                 @RequestParam("lastName") String lastName, 
                                 @RequestParam("departmentName") String departmentName) {
        Employee employee = empService.getEmployeeById(id);
        if (employee !=  null) {
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setDepartmentName(departmentName);
            empService.updateEmployee(employee);
        }
        return empService.saveEmployee(employee);

    }
    
    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployeeData(@PathVariable int id) {
        empService.deleteEmployeeById(id);
    }
}
