package JFS6WDE.EmployeeManagementApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import JFS6WDE.EmployeeManagementApplication.Entities.Employee;

@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
