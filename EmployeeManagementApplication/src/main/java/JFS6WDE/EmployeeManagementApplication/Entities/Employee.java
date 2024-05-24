package JFS6WDE.EmployeeManagementApplication.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 
 @Column(name = "First_Name")
 private String firstName;
 
 @Column(name = "Last_Name")
 private String lastName;
 
 @Column(name = "Department")
 private String departmentName;
}
