public class EmployeeService {
    private Employee[] employees;

    public EmployeeService(Employee[] employees) {
        setEmployees(employees);
    }

    public void setEmployees(Employee[] newEmployees) {
        if (newEmployees.length > 0) {
            employees = newEmployees;
        } else {
            System.out.println("Error! Source employee array is empty!");
        }
    }

    public Employee getEmployee(int id) {
        for (Employee currentEmployee : employees) {
            if (currentEmployee.getId() == id) {
                return currentEmployee;
            }
        }

        return null;
    }

    public Employee getEmployee(String firstName, String lastName) {
        for (Employee currentEmployee : employees) {
            if (currentEmployee.getFirstName().toLowerCase().equals(firstName.toLowerCase()) && currentEmployee.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
                return currentEmployee;
            }
        }

        return null;
    }

    void print() {
        for (Employee employee : employees) {
            System.out.println(employeeToString(employee));
        }
    }

    public double calculateSalary() {
        long result = 0;

        for (Employee employee : employees) {
            result += (long) employee.getSalary();
        }

        return (double) result / 100;
    }

    void removeEmployee(int id) {
       if (getEmployee(id) != null) {
           Employee[] shrinkEmployees = new Employee[employees.length - 1];
           int shrinkArrrayIndex = 0;

           for (int i = 0; i < employees.length; i++) {
               if (employees[i].getId() != id) {
                   shrinkEmployees[shrinkArrrayIndex] = employees[i];
                   shrinkArrrayIndex++;
               }
           }

           employees = shrinkEmployees;
       }
    }

    void addEmployee(Employee employee) {
        if (employee == null)  {
            System.out.println("New employee is null!");

            return;
        }

        Employee employeeById = getEmployee(employee.getId());
        Employee employeeByName = getEmployee(employee.getFirstName(), employee.getLastName());

        if (employeeById == null  && employeeByName == null) {
            Employee[] extendedEmployees = new Employee[employees.length + 1];

            for (int i = 0; i < employees.length; i++) {
                extendedEmployees[i] = employees[i];
            }

            extendedEmployees[employees.length] = employee;
            employees = extendedEmployees;
        } else {
            System.out.println("Found duplicate for employee:");
            System.out.println(employeeToString(employee, false));

            if (employeeById != null) {
                System.out.println("By Id:");
                System.out.println(employeeToString(employeeById, false));
            }

            if (employeeByName != null) {
                System.out.println("By Full Name:");
                System.out.println(employeeToString(employeeByName, false));
            }

        }
    }

    protected void sort(int sortMode) {
        if (employees == null || (employees.length == 0)) {
            return;
        }

        quickSort(0, employees.length - 1, sortMode);
    }

    protected void quickSort(int lowerIndex, int higherIndex, int sortMode) {
        int i = lowerIndex;
        int j = higherIndex;

        Employee pivot = employees[lowerIndex + (higherIndex - lowerIndex)/2];

        while(i <= j) {
            while (sortMode == 1 ? employees[i].getFirstName().compareTo(pivot.getFirstName()) < 0 : employees[i].getLastName().compareTo(pivot.getLastName()) < 0) {
                i++;
            }

            while (sortMode == 1 ? employees[j].getFirstName().compareTo(pivot.getFirstName()) > 0 : employees[j].getLastName().compareTo(pivot.getLastName()) > 0) {
                j--;
            }

            if (i <= j) {
                exchangeValues(i, j);
                i++;
                j--;
            }
        }

        if (lowerIndex < j) {
            quickSort(lowerIndex, j, sortMode);
        }

        if (i < higherIndex) {
            quickSort(i, higherIndex, sortMode);
        }
    }

    protected void exchangeValues(int i, int j) {
        Employee temp = employees[i];
        employees[i] = employees[j];
        employees[j] = temp;
    }

    public Employee[] sortByLastName() {
        sort(2);

        return employees;
    }

    public Employee[] sortByLastFirstName() {
        sort(1);

        return employees;
    }

    protected String employeeToString(Employee employee, boolean showSalary) {
        if (employee == null) {
            return "";
        }

        String result = "=====================================================================================\n";
        result += String.format("Employee id = %d\n", employee.getId());
        result += String.format("  First Name: %s\n", employee.getFirstName());
        result += String.format("  First Name: %s\n", employee.getLastName());
        if (showSalary) {
            result += String.format("  Salary    : %.2f$\n", (double)employee.getSalary() / 100);
        }

        return result;
    }

    protected String employeeToString(Employee employee) {
        return employeeToString(employee, true);
    }
}
