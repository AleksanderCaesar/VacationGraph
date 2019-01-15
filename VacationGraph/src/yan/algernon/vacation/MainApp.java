package yan.algernon.vacation;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import yan.algernon.vacation.fxml.EmployeesController;
import yan.algernon.vacation.fxml.MainLayoutController;
import yan.algernon.vacation.fxml.NewWindow;
import yan.algernon.vacation.model.Employees;
import yan.algernon.vacation.model.EmployeesListWrapper;

/**
 *
 * @author Алекс
 */
public class MainApp extends Application{
   
    private Stage primaryStage;
    private BorderPane mainRoot;
    private  ObservableList<Employees> employeesData = FXCollections.observableArrayList();
    
    // Конструктор главного класса
    
    public MainApp(){
        
        employeesData.add(new Employees("Иван","Иванович","Иванов","директор","11.01.2018-24.02.2018","17.04.2018-30.05.2018","Как-то так"));
        
    }
    
    // метод для получения данных из ObservableList
    
    public ObservableList<Employees> getEmployeesData(){
        return employeesData;
    }
    
    // запуск метода start
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Программа учета отпусков");
        primaryStage.setResizable(false);
        
        initRootLayout();
        initMainView();
        
    }
    
    // Возвращает главную сцену
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    // Подключение fxml файла для корневого расположения
    public void initRootLayout(){
        
        try{
            // Загружаем корневой макет из fxml файла.
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(getClass().getResource("fxml/MainLayout.fxml"));
             
             mainRoot = (BorderPane) loader.load();
             
             // Отображаем сцену, содержащую корневой макет.
             Scene scene = new Scene(mainRoot);
             primaryStage.setScene(scene);
             
               // Даём контроллеру доступ к главному приложению.
              MainLayoutController controller = loader.getController();
              controller.setMainApp(this); 
             
             primaryStage.show();
             
        }catch (IOException e) {
         e.printStackTrace();
        }
    }
    
    // Подключает fxml файл для главного экрана
    public void initMainView (){
        
          try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/MainView.fxml"));
            AnchorPane MainView1 = (AnchorPane) loader.load();
            // Помещаем сведения об адресатах в центр корневого макета.
            mainRoot.setCenter(MainView1);
           // Даём контроллеру доступ к главному приложению.
        EmployeesController controller = loader.getController();
        controller.setMainApp(this);        
        }  catch (IOException e) {
            e.printStackTrace();
        }
          // Пытается загрузить последний открытый файл с адресатами.
    File file = getPersonFilePath();
    if (file != null) {
        loadEmployeesFromFile(file);
    }
    }
    
    /**
 * Открывает диалоговое окно для изменения данных
 * @return true, если пользователь кликнул OK, в противном случае false.
 */
    public boolean showNewWindow(Employees employees){
        
         try {
        // Загружаем fxml-файл и создаём новую сцену
        // для всплывающего диалогового окна.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/EditEmployees.fxml"));
        AnchorPane window = (AnchorPane)loader.load();
        
         // Создаём диалоговое окно Stage.
        Stage windowStage = new Stage();
        windowStage.setTitle("Окно заполнения данных");
        windowStage.initModality(Modality.WINDOW_MODAL);
        windowStage.initOwner(primaryStage);
        Scene scene = new Scene(window);
        windowStage.setScene(scene);
        
        // Передаём адресата в контроллер.
        NewWindow controller = loader.getController();
        controller.setWindowStage(windowStage);
        controller.setEmployees(employees);
        
        // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
        windowStage.showAndWait();
        
        return controller.isOkClicked();   
        
    } catch (IOException e) {
        e.printStackTrace();
       }        
        return false;
    }
    // Получеам путь к расположению сохраненного файла
    public File getPersonFilePath() {
       
         Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
       String filePath = prefs.get("filePath", null);
       if (filePath != null) {
        return new File(filePath);
     } else {
        return null;
      }
    }
    
    public void setEmployeesFilePath(File file) {
         
     
       Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
        prefs.put("filePath", file.getPath());

        // Обновление заглавия сцены.
        primaryStage.setTitle("Учет отпусков - " + file.getName());
      } else {
        prefs.remove("filePath");

        // Обновление заглавия сцены.
        primaryStage.setTitle("Учет отпусков");
      }
    }
    
    public void loadEmployeesFromFile(File file){
         
     
         try{
             JAXBContext context = JAXBContext.newInstance(EmployeesListWrapper.class);
             Unmarshaller um = context.createUnmarshaller();
             
              // Чтение XML из файла и демаршализация.
              EmployeesListWrapper wrapper = (EmployeesListWrapper)um.unmarshal(file);
              
              employeesData.clear();
              employeesData.addAll(wrapper.getEmployees());
              
              // Сохраняем путь к файлу в реестре.
              setEmployeesFilePath(file);
              
         } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());

        alert.showAndWait();
     }
  }
     /**
 * Сохраняет текущую информацию об адресатах в указанном файле.
 * 
 * 
 */
     public void saveEmployeesToFile(File file){
         
         try{
             JAXBContext context = JAXBContext.newInstance(EmployeesListWrapper.class);
             Marshaller m = context.createMarshaller();
             m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
             
             // Обёртываем наши данные об адресатах.
             EmployeesListWrapper wrapper = new EmployeesListWrapper();
             wrapper.setEmployees(employeesData);
             
             // Маршаллируем и сохраняем XML в файл.
             m.marshal(wrapper, file);
             
             // Сохраняем путь в реестре
             setEmployeesFilePath(file);         
     } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + file.getPath());

        alert.showAndWait();
      }        
     }
     
     
}
