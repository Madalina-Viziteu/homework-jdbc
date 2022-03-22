package project;


import project.entity.Department;
import project.repository.DepartmentRepository;
import project.repository.EmployeeRepository;
import project.repository.ProjectRepository;

public class CommandOperation {

    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private DepartmentRepository departmentRepository = new DepartmentRepository();
    private ProjectRepository projectRepository = new ProjectRepository();

    public void employeeOperation() {
        System.out.println("All employees: ");
        employeeRepository.findAllEmployees();
        System.out.println("All employees starting with letter J: ");
        employeeRepository.displayAllEmployeesStartingWithLetter("J");
        System.out.println("All employees with a department: ");
        employeeRepository.displayAllEmployeesWithDepartment();
        System.out.println("All employees without department: ");
        employeeRepository.displayAllEmployeesWithoutDepartment();
    }

    public void departmentOperation() {
        departmentRepository.insertDepartment(new Department(2, "Dev"));
        departmentRepository.findAll();
        departmentRepository.findById(2);
        departmentRepository.updateDepartment(new Department(2, "Development"));
        departmentRepository.deleteById(1);
    }

    public void projectOperation() {
        System.out.println("All projects: ");
        projectRepository.displayAllProjects();
    }

}