public class Test {
    public static void main(String[] args) {
        Employee[] employees = MockEmployeesGenerator.generate(10);

        employees[0] = new Employee(1, "Ivan", "Ivanov", 2000_00);
        employees[1] = new Employee(2, "Petr", "Petrov", 2400_00);
        employees[2] = new Employee(3, "Anton", "Sidorov", 2700_00);
        employees[3] = new Employee(4, "Alexey", "Markov", 2550_00);
        employees[4] = new Employee(5, "Valentin", "Shevchuk", 2050_00);
        employees[5] = new Employee(6, "Nikolay", "Konovalov", 3000_00);
        employees[6] = new Employee(7, "Anatoliy", "Rabinovich", 6800_00);
        employees[7] = new Employee(8, "Petr", "Shevchenko", 2950_00);
        employees[8] = new Employee(9, "Ivan", "Malinovsky", 5040_00);
        employees[9] = new Employee(10, "Andrey", "Shevchenko", 1500_00);

        EmployeeService employeeService = new EmployeeService(employees);

        Employee newEmployee = new Employee(11, "Zinaida", "Levchenko", 300_00);

        employeeService.addEmployee(newEmployee);

        employeeService.removeEmployee(7);

        employeeService.sortByLastName();

        System.out.println("\nEmployees list:");
        employeeService.print();

        System.out.println();
        System.out.println("\nTotal salary: ");
        System.out.print(String.format("%.2f$", employeeService.calculateSalary()));
    }
}
