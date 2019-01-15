
package yan.algernon.vacation.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import yan.algernon.vacation.model.Employees;

/**
 *
 * @author Алекс
 */
public class NewWindow {
    
    @FXML
    private TextField nameField;    
    @FXML
    private TextField surnameField;    
    @FXML
    private TextField otchField;
    @FXML
    private TextField otdelField;
    @FXML
    private TextField firstVacField;
    @FXML
    private TextField secondVacField;
    @FXML
    private TextField commentField;
    
    private Stage windowStage;
    private Employees employees;
    private boolean okClicked = false;
    
    
    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }
    
    
    public void setWindowStage(Stage windowStage){
        this.windowStage = windowStage;
    }
    
    public void setEmployees(Employees employees){
        
        this.employees = employees;
        
        nameField.setText(employees.getName());
        surnameField.setText(employees.getSurname());
        otchField.setText(employees.getOtch());
        otdelField.setText(employees.getOtdel());
        firstVacField.setText(employees.getFirstVac());
        secondVacField.setText(employees.getSecVac());
        commentField.setText(employees.getComments());
    }
    
    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk() {
       
        employees.setName(nameField.getText());
        employees.setSurname(surnameField.getText());
        employees.setOtch(otchField.getText());
        employees.setOtdel(otdelField.getText());
        employees.setFirstVac(firstVacField.getText());
        employees.setSecVac(secondVacField.getText());
        employees.setComments(commentField.getText());
        
        okClicked = true;
        windowStage.close();
    }
    
    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        windowStage.close();
    }
    
    
}
