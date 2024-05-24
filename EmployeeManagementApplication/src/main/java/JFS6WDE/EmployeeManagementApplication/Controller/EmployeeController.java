package JFS6WDE.EmployeeManagementApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import JFS6WDE.EmployeeManagementApplication.Entities.Employee;
import JFS6WDE.EmployeeManagementApplication.Service.EmployeeServiceImplement;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServiceImplement employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {

        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create an employee object
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "addemployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.createEmployee(employee);

        // redirect to home page
        return "redirect:index";

    }

    @DeleteMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("id") int id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:index";
    }

    @GetMapping("/page/{pageNO}")
    private String findPaginated(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir, Model model) {
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
