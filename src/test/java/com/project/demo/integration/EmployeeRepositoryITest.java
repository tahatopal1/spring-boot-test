package com.project.demo.integration;

import com.project.demo.model.Employee;
import com.project.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryITest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@mail.com")
                .build();
    }

    // JUnit test for save employee operation
    // @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("John")
//                .lastName("Doe")
//                .email("johndoe@mail.com")
//                .build();

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);


    }

    // JUnit test for get all employees operation
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("John")
//                .lastName("Doe")
//                .email("johndoe@mail.com")
//                .build();

        Employee employee1 = Employee.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("janedoe@mail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when - action or the behaviour that we are going to test
        List<Employee> employees = employeeRepository.findAll();

        // then - verify the output
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);

    }

    // JUnit test for
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("John")
//                .lastName("Doe")
//                .email("johndoe@mail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();

    }

    // JUnit test for employee by email operation
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@mail.com")
                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();

    }

    // JUnit test for update employee operation
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("John")
//                .lastName("Doe")
//                .email("johndoe@mail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("johnsmith@mail.com");
        savedEmployee.setLastName("Smith");
        Employee updatedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo(savedEmployee.getEmail());
        assertThat(updatedEmployee.getFirstName()).isEqualTo(savedEmployee.getFirstName());

    }

    // JUnit test for delete employee operation
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("John")
//                .lastName("Doe")
//                .email("johndoe@mail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        employeeRepository.delete(employee);
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();

    }

    // JUnit test for custom query using JPQL with index
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){

        // given - precondition or setup
        String firstName = "John";
        String lastName = "Doe";

//        Employee employee = Employee.builder()
//                .firstName(firstName)
//                .lastName(lastName)
//                .email("johndoe@mail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    // JUnit test for custom query using JPQL with named params
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject(){

        // given - precondition or setup
        String firstName = "John";
        String lastName = "Doe";

//        Employee employee = Employee.builder()
//                .firstName(firstName)
//                .lastName(lastName)
//                .email("johndoe@mail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    // JUnit test for custom query using native SQL with index params
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){

        // given - precondition or setup
        String firstName = "John";
        String lastName = "Doe";

//        Employee employee = Employee.builder()
//                .firstName(firstName)
//                .lastName(lastName)
//                .email("johndoe@mail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQL(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    // JUnit test for custom query using native SQL with named params
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){

        // given - precondition or setup
        String firstName = "John";
        String lastName = "Doe";

//        Employee employee = Employee.builder()
//                .firstName(firstName)
//                .lastName(lastName)
//                .email("johndoe@mail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

}
