package yan.algernon.vacation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Алекс
 */
public class Employees {
    
    private StringProperty name;
    private StringProperty surname;
    private StringProperty otch;
    private StringProperty otdel;
    private StringProperty firstVacation;
    private StringProperty secondVacation;
    private StringProperty comments;
    private ObjectProperty rec;
    
          
    // конструктор без параметров
    public Employees (){
        this(null,null,null,null,null,null,null);
    }
    // конструктор с параметрами
    public Employees (String name, String otch, String surname , String otdel, String firstVacation, String secondVacation,String comments ){
        
        this.name           = new SimpleStringProperty(name);
        this.otch           = new SimpleStringProperty(otch);
        this.surname        = new SimpleStringProperty(surname);        
        this.otdel          = new SimpleStringProperty(otdel);
        this.firstVacation  = new SimpleStringProperty(firstVacation);
        this.secondVacation = new SimpleStringProperty(secondVacation);
        this.comments       = new SimpleStringProperty(comments);
        this.rec            = new SimpleObjectProperty();
        
               
                
    }
    
    public String getName(){
        return name.get();
    }
    
    public void setName(String name){
        this.name.set(name);
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    
     public String getSurname(){
        return surname.get();
    }
    
    public void setSurname(String surname){
        this.surname.set(surname);
    }
    
    public StringProperty surnameProperty(){
        return surname;
    }
    
     public String getOtch(){
        return otch.get();
    }
    
    public void setOtch(String otch){
        this.otch.set(otch);
    }
    
    public StringProperty otchProperty(){
        return otch;
    }
    
    public String getOtdel(){
        return otdel.get();
    }
    
    public void setOtdel(String otdel){
        this.otdel.set(otdel);
    }
    
    public StringProperty otdelProperty(){
        return otdel;
    }
    
    public String getComments(){
        return comments.get();
    }
    
    public void setComments(String comments){
        this.comments.set(comments);
    }
    
    public StringProperty commentsProperty(){
        return comments;
    }
    
    public String getFirstVac(){
        return firstVacation.get();
    }
    
    public void setFirstVac(String firstVacation){
        this.firstVacation.set(firstVacation);
    }
    
    public StringProperty firstVacProperty(){
        return firstVacation;
    }
    
    public String getSecVac(){
        return secondVacation.get();
    }
    
    public void setSecVac(String secondVacation){
        this.secondVacation.set(secondVacation);
    }
    
    public StringProperty secVacProperty(){
        return secondVacation;
    }
    
    //Получеам данные из строк с отпусками, выеделяем из них цифры и используем их для построения графика отпусков. 
    // Цифры используются в качестве номера индексов в массиве прямоуголников для закрашивания на период отпуска
    public ObjectProperty recProperty() {
        
        // создаем массив прямоугольников и заполняем массив прямоуголниками, раскрашиваем их в желтый цвет
        Rectangle[] r1 = new Rectangle[52];
        for (int i=0;i<52;i++) {
             r1[i] = new Rectangle(12,12);
             r1[i].setFill(Color.YELLOW);
         }
        // просматриваем строку на предмет выделения из нее цифр
         Matcher m = Pattern.compile("\\d+").matcher(firstVacation.get());
        List<Integer> numbers = new ArrayList<Integer>();
        while(m.find()) {
            numbers.add(Integer.parseInt(m.group())); }
        // сохраняем найденные цифры из строки в отдельные перменные
            int x = numbers.get(0);
            int y = numbers.get(1);
            int a = numbers.get(3);
            int b = numbers.get(4); 
            int xy = ((y-1)*30+x)/7;
            int ab = ((b-1)*30+a)/7;
         
        // используем цифры в качестве индексов и перекрашиваем прямоугольники, которые выпадают на отпуск,  в дугой цвет  
        for (int i=xy;i<ab;i++) {
             
             r1[i].setFill(Color.AQUA);
         }
        // То же самое ля второго отпуска
        Matcher m2 = Pattern.compile("\\d+").matcher(secondVacation.get());
        List<Integer> numbers2 = new ArrayList<Integer>();
        while(m2.find()) {
            numbers2.add(Integer.parseInt(m2.group())); }
            int x2 = numbers2.get(0);
            int y2 = numbers2.get(1);
            int a2 = numbers2.get(3);
            int b2 = numbers2.get(4); 
            int xy2 = ((y2-1)*30+x2)/7;
            int ab2 = ((b2-1)*30+a2)/7;
         
          
        for (int i=xy2;i<ab2;i++) {
             
             r1[i].setFill(Color.AQUA);
         } 
        
        // создаем коробку HBox и заполняем  ее массивом прямоугольников
        HBox box = new HBox(2);
         box.getChildren().addAll(r1);
         // Передаем объект типа HBox в переменную ObjectProperty для заполнения клетки
        rec.setValue(box);
       
        return rec;
           }
    
    public Object getRec(){
           
           
    return rec.get();
    }
    
 }