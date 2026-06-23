abstract class TeamMember {
    protected String name;
    
    public TeamMember(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

interface BonusEligible {
    void CalculateBonus();    
}

class Employee extends TeamMember implements BonusEligible {
    
    public Employee(String name) {
        super(name);
    }
    
    public void CalculateBonus() {
        System.out.println(
            name + " receives a standard employee bonus."
            );
    }
}

class Contractor extends TeamMember {
    
    public Contractor(String name) {
        super(name);
    }
    
    public void submitInvoice() {
        System.out.println(
            name + " submitted a invoice for payment."
            );
    }
    
}

public class Main{
    public static void main(String[] args) {
        
        Employee employee = new Employee("Akib");
        Contractor contractor = new Contractor("Jax");
        
        employee.CalculateBonus();
        contractor.submitInvoice();
        
    }
}

