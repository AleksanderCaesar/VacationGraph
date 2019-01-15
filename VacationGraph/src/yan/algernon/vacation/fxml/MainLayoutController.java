
package yan.algernon.vacation.fxml;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import yan.algernon.vacation.MainApp;
import yan.algernon.vacation.model.Employees;

/**
 *
 * @author Алекс
 */
public class MainLayoutController {
    
    // Ссылка на главное приложение
    private MainApp mainApp;

    /**
     * Вызывается главным приложением, чтобы оставить ссылку на самого себя.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Создаёт пустую адресную книгу.
     */
    @FXML
    private void handleNew() {
        mainApp.getEmployeesData().clear();
        mainApp.setEmployeesFilePath(null);
    }
    
    /**
     * Открывает FileChooser, чтобы пользователь имел возможность
     * выбрать адресную книгу для загрузки.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог загрузки файла
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadEmployeesFromFile(file);
        }
    }
    
    /**
     * Сохраняет файл в файл адресатов, который в настоящее время открыт.
     * Если файл не открыт, то отображается диалог "save as".
     */
    @FXML
    private void handleSave() {
        File EmployeesFile = mainApp.getPersonFilePath();
        if (EmployeesFile != null) {
            mainApp.saveEmployeesToFile(EmployeesFile);
        } else {
            handleSaveAs();
        }
    }
    
    /**
     * Открывает FileChooser, чтобы пользователь имел возможность
     * выбрать файл, куда будут сохранены данные
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог сохранения файла
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveEmployeesToFile(file);
        }
    }
     /**
     * Открывает диалоговое окно about.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Учет отпусков");
        alert.setHeaderText("О программе");
        alert.setContentText("Автор: Александр Юциков");

        alert.showAndWait();
    }

    /**
     * Закрывает приложение.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    @FXML
    private void graphics(){
        try {
            // Загружаем график отпусков.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Graphics.fxml"));
            AnchorPane windowGraph = (AnchorPane) loader.load();
            // Создаём диалоговое окно Stage.
        Stage windowStage = new Stage();
        windowStage.setTitle("График отпусков");
        windowStage.initModality(Modality.WINDOW_MODAL);
        windowStage.initOwner(mainApp.getPrimaryStage());
        Scene scene = new Scene(windowGraph);
        windowStage.setScene(scene);
        
       GraphController controller = loader.getController();
       controller.setMainApp(mainApp);
       controller.initialize();
       
       // controller.setEmployees(employees);
        windowStage.showAndWait();
       
        // Передаём адресата в контроллер.
        
        }  catch (IOException e) {
            e.printStackTrace();
        }
     
    
    }
    
}
