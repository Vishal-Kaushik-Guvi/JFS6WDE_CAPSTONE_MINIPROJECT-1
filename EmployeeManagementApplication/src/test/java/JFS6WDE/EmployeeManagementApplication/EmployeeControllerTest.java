package JFS6WDE.EmployeeManagementApplication;

import JFS6WDE.EmployeeManagementApplication.Controller.EmployeeController;
import JFS6WDE.EmployeeManagementApplication.Entities.Employee;
import JFS6WDE.EmployeeManagementApplication.Service.EmployeeServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class EmployeeControllerTest {

    @Mock
    private EmployeeServiceImplement employeeService;

    @Mock
    private Model model;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testAddEmployee() throws Exception {
        mockMvc.perform(get("/addEmployee"))
                .andExpect(status().isOk())
                .andExpect(view().name("addemployee"))
                .andExpect(model().attributeExists("employee"));
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);

        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/saveEmployee")
                        .flashAttr("employee", employee))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(employeeService, times(1)).saveEmployee(any(Employee.class));
    }

    @Test
    public void testViewEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);

        when(employeeService.getEmployeeById(anyLong())).thenReturn(employee);

        mockMvc.perform(get("/viewEmployee").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewemployee"))
                .andExpect(model().attributeExists("employee"))
                .andExpect(model().attribute("employee", employee));

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    public void testShowUpdateEmployeeForm() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);

        when(employeeService.getEmployeeById(anyLong())).thenReturn(employee);

        mockMvc.perform(get("/updateEmployee").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("updateemployee"))
                .andExpect(model().attributeExists("employee"))
                .andExpect(model().attribute("employee", employee));

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);

        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/updateEmployee")
                        .flashAttr("employee", employee))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(employeeService, times(1)).updateEmployee(any(Employee.class));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(post("/deleteEmployee").param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(employeeService, times(1)).deleteEmployeeById(1L);
    }

    @Test
    public void testFindPaginated() throws Exception {
        int pageNo = 1;
        String sortField = "firstName";
        String sortDir = "asc";
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        Page<Employee> page = new PageImpl<>(Arrays.asList(new Employee(), new Employee()), pageable, 2);

        when(employeeService.findPaginated(pageNo, 5, sortField, sortDir)).thenReturn(page);

        mockMvc.perform(get("/page/{pageNo}", pageNo)
                        .param("sortField", sortField)
                        .param("sortDir", sortDir))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("totalPages"))
                .andExpect(model().attributeExists("totalItems"))
                .andExpect(model().attributeExists("sortField"))
                .andExpect(model().attributeExists("sortDir"))
                .andExpect(model().attributeExists("reverseSortDir"))
                .andExpect(model().attributeExists("listEmployees"));

        verify(employeeService, times(1)).findPaginated(pageNo, 5, sortField, sortDir);
    }
}
