package JFS6WDE.EmployeeManagementApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import JFS6WDE.EmployeeManagementApplication.Entities.Employee;
import JFS6WDE.EmployeeManagementApplication.Service.EmployeeServiceImplement;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServiceImplement employeeService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "addEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/viewEmployee")
    public String viewEmployee(@RequestParam long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "viewEmployee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@RequestParam("id") int id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("departmentName") String departmentName) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setDepartmentName(departmentName);
            employeeService.updateEmployee(employee);
        }
        return "redirect:/";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("id") long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }
}
