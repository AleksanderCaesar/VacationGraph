/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yan.algernon.vacation.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import yan.algernon.vacation.MainApp;
import yan.algernon.vacation.model.Employees;

/**
 *
 * @author Алекс
 */
public class GraphController {
    private Stage windowStage;
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
    
    private MainApp mainApp;
    
    
    
    public GraphController (){
        
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        empTable.setItems(mainApp.getEmployeesData());
    }
    
    // Запуск основного метода контроллера, заполнение данными клеток
    public void initialize(){
        
        n1Column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        n2Column.setCellValueFactory(cellData -> cellData.getValue().otchProperty());
        n3Column.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        n4Column.setCellValueFactory(cellData -> cellData.getValue().recProperty());
    }
    
    public void setWindowStage(Stage windowStage){
        this.windowStage = windowStage;
    }
    
    // Метод для кнопки "Закрыть"
    @FXML
    private void closeWindow(ActionEvent event){
      ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
