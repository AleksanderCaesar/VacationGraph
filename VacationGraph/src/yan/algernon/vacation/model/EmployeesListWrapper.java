
package yan.algernon.vacation.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Алекс
 */
@XmlRootElement(name = "employees")
public class EmployeesListWrapper {
    
    private List<Employees> employees;
    
    
    @XmlElement(name ="employees")   
    public List<Employees> getEmployees(){
        return employees;
    }
    
    public void setEmployees(List<Employees> employees){
        this.employees = employees;
    }
    
    
    
}
