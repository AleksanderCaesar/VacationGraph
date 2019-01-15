
package yan.algernon.vacation.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import yan.algernon.vacation.MainApp;
import yan.algernon.vacation.model.Employees;

/**
 *
 * @author Алекс
 */
public class EmployeesController {
    
    @FXML
    private TableView<Employees> empTable;
    
    @FXML
    private TableColumn<Employees,String> n1Column;
    
    @FXML
    private TableColumn<Employees,String> n2Column;
    
    @FXML
    private TableColumn<Employees,String> n3Column;
    
    @FXML
    private TableColumn<Employees,String> n4Column;
    
    @FXML
    private TableColumn<Employees,String> n5Column;
    
    @FXML
    private TableColumn<Employees,String> n6Column;
    
    @FXML
    private TableColumn<Employees,String> n7Column;
    
    private MainApp mainApp;
    
    // Конструктор без параметров
    
    public EmployeesController (){
        
    }
    // Запуск основного метода контроллера, заполнение колонок данными
    public void initialize(){
        
        n1Column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        n2Column.setCellValueFactory(cellData -> cellData.getValue().otchProperty());
        n3Column.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        n4Column.setCellValueFactory(cellData -> cellData.getValue().otdelProperty());
        n5Column.setCellValueFactory(cellData -> cellData.getValue().firstVacProperty());
        n6Column.setCellValueFactory(cellData -> cellData.getValue().secVacProperty());
        n7Column.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());
        
       
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        empTable.setItems(mainApp.getEmployeesData());
    }
   
     /**
 * Вызывается, когда пользователь кликает по кнопке "Добавить"
 * Открывает диалоговое окно 
 */
     @FXML
      private void handleNewPerson() {
       Employees tempEmp = new Employees();
       boolean okClicked = mainApp.showNewWindow(tempEmp);
        if (okClicked) {
        mainApp.getEmployeesData().add(tempEmp);
     }
  }
      // Удаляем выбранную строку
    @FXML
    private void handleDelete(){
       
       int selectedIndex = empTable.getSelectionModel().getSelectedIndex();
       if(selectedIndex >= 0){
           empTable.getItems().remove(selectedIndex);
       } else {
           // Ничего не выбрано
           Alert alert = new Alert(AlertType.WARNING);
           alert.initOwner(mainApp.getPrimaryStage());
           alert.setTitle("Ничего не выбрано");
           alert.setHeaderText("Нет объекта для удаления");
           alert.setContentText("Пожалуйста, выберите объект для удаления");

          alert.showAndWait();       
       }
   }
    // Изменяем выбранную строку
     @FXML
     private void handleEdit(){
         
         Employees selectedEmployees = empTable.getSelectionModel().getSelectedItem();
         if (selectedEmployees != null) {
             boolean okClicked = mainApp.showNewWindow(selectedEmployees);
              
         } else {
             // Ничего не выбрано.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
        }
     }
     
     
  }



