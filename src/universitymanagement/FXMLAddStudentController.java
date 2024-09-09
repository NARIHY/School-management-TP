/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitymanagement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author RANDRIANARISOA
 */
public class FXMLAddStudentController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button student_addBtn;

    @FXML
    private ComboBox<String> student_course;

    @FXML
    private ImageView student_imageView;

    @FXML
    private FontAwesomeIcon student_importBtn;

    @FXML
    private TextField student_name;

    @FXML
    private TextField student_number;

    @FXML
    private TextField student_pay;

    @FXML
    private ComboBox<String> student_section;

    @FXML
    private ComboBox<String> student_status;

    @FXML
    private Button student_updateBtn;

    @FXML
    private ComboBox<String> student_year;

    @FXML
    private ComboBox<String> student_gender;

    @FXML
    private ComboBox<String> student_statusStudent;

    @FXML
    private DatePicker student_birthDate;

    @FXML
    private Label student_priceValue;

    //DB
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    //Alert
    private AlertMessage alert = new AlertMessage();

    //Image
    private Image image;

    public void addBtn() {
        //Error here
        if (student_number.getText().isEmpty() || student_name.getText().isEmpty()
                || student_year.getSelectionModel().getSelectedItem().isEmpty()
                || student_course.getSelectionModel().getSelectedItem().isEmpty()
                || student_section.getSelectionModel().getSelectedItem().isEmpty()
                || student_gender.getSelectionModel().getSelectedItem().isEmpty()
                || student_pay.getText().isEmpty()
                || student_status.getSelectionModel().getSelectedItem().isEmpty()
                || student_statusStudent.getSelectionModel().getSelectedItem().isEmpty()
                || student_gender.getSelectionModel().getSelectedItem().isEmpty()
                || ListData.path == null || "".equals(ListData.path)
                || student_birthDate.getValue() == null) {
            System.out.println(ListData.path);
            alert.errorMessage("Please fill al the blank.");
        } else {
            connect = DataBase.connectDB();

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String checkStudentName = "SELECT * FROM student WHERE student_id ='"
                    + student_number.getText() + "'";
            try {
                prepare = connect.prepareStatement(checkStudentName);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage("Student number " + student_number.getText() + " is already taken");
                } else {
                    String insertData = "INSERT INTO student "
                            + "(student_id, full_name, gender, birthday_date, year, course, section, payement, total_payement, status_payement, image, date_insert, status)"
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, student_number.getText());
                    prepare.setString(2, student_name.getText());
                    prepare.setString(3, student_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, String.valueOf(student_birthDate));
                    prepare.setString(5, student_year.getSelectionModel().getSelectedItem());
                    prepare.setString(6, student_course.getSelectionModel().getSelectedItem());
                    prepare.setString(7, student_section.getSelectionModel().getSelectedItem());
                    prepare.setDouble(8, price);
                    prepare.setDouble(9, Double.parseDouble(student_pay.getText()));
                    prepare.setString(10, student_status.getSelectionModel().getSelectedItem());

                    //Path image
                    String path = ListData.path;
                    //error here
                    path = path.replace("\\", "\\\\");
                    prepare.setString(11, path);

                    prepare.setDate(12, sqlDate);
                    prepare.setString(13, student_statusStudent.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    //Paths F:\Projet\JavaFx\UniversityManagement\src\student_Directory
                    Path transfert = Paths.get(path);
                    Path copy = Paths.get("F:\\Projet\\JavaFx\\UniversityManagement\\src\\student_Directory" + student_number.getText() + ".jpg");

                    Files.copy(transfert, copy, StandardCopyOption.REPLACE_EXISTING);

                    alert.successMessage("Added succefully new student");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    //Correct later
    //private int getAge = 0;
    //public void countAge() {
    //    if(student_birthDate.getValue() != null) {
    //        LocalDate birthDate = student_birthDate.getValue();
    //        int age = Period.between(birthDate, LocalDate.now());
    //        getAge = age;
    //        System.out.println(getAge);
    //    }
    //}
    private double price = 0;

    public void priceList() {
        System.out.println(student_course.getSelectionModel().getSelectedItem());
        if (student_course.getSelectionModel().getSelectedItem() != null) {
            if (student_course.getSelectionModel().getSelectedItem().equals("Management")) {
                price = 900000;
            } else if (student_course.getSelectionModel().getSelectedItem().equals("Software Engenering")) {
                price = 950000;
            } else if (student_course.getSelectionModel().getSelectedItem().equals("Data analyst")) {
                price = 9250000;
            } else if (student_course.getSelectionModel().getSelectedItem().equals("Mathematics")) {
                price = 1000000;
            } else if (student_course.getSelectionModel().getSelectedItem().equals("Physique nuclear")) {
                price = 2500000;
            } else if (student_course.getSelectionModel().getSelectedItem().equals("Sience")) {
                price = 4250000;
            } else if (student_course.getSelectionModel().getSelectedItem().equals("French & Malagasy")) {
                price = 42500000 + 35600;
            } else if (student_course.getSelectionModel().getSelectedItem().equals("Marketing")) {
                price = 350000 + 12000;
            } else if (student_course.getSelectionModel().getSelectedItem().equals("Web designer")) {
                price = 545000;
            } else if (student_course.getSelectionModel().getSelectedItem().equals("Theathre")) {
                price = 1250000;
            } else if (student_course.getSelectionModel().getSelectedItem().equals("Web develloper")) {
                price = 350000 + 245500 + 89500 + 62300 - 1200;
            } else {
                price = 1500000;
            }
            student_priceValue.setText("MGA " + String.valueOf(price));
        } else {
            student_priceValue.setText("MGA " + String.valueOf(price));
        }

    }

    public void importBtn() {
        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*.jpg", "*.jpeg", "*.png"));

        File file = open.showOpenDialog(student_importBtn.getScene().getWindow());

        if (file != null) {
            ListData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 128, 125, false, true);

            student_imageView.setImage(image);
        }
    }

    //Student number generator
    public void displayStudentNumber() {
        FXMLDocumentController control = new FXMLDocumentController();

        int getStudentNumber = control.studentIDGenerator();

        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String getYear = format.format(date);
        int tempYear = Integer.parseInt(getYear);

        String student_num = String.valueOf(tempYear) + String.valueOf(getStudentNumber);

        student_number.setText(student_num);
    }

    //Student section
    public void studentSection() {
        List<String> listY = new ArrayList<>();

        for (String data : ListData.section) {
            listY.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listY);
        student_section.setItems(listData);
    }

    //Status of one student 
    public void studentStatusSeven() {
        List<String> listY = new ArrayList<>();

        for (String data : ListData.studentStatus) {
            listY.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listY);
        student_statusStudent.setItems(listData);
    }

    //Student course
    public void studentCourse() {
        List<String> listY = new ArrayList<>();

        for (String data : ListData.course) {
            listY.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listY);
        student_course.setItems(listData);

        priceList();
    }

    //Student payement status
    public void studentPayementStatus() {
        List<String> listY = new ArrayList<>();

        for (String data : ListData.payementStatus) {
            listY.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listY);
        student_status.setItems(listData);
    }

    //Student gender 
    public void studentGender() {
        List<String> listY = new ArrayList<>();

        for (String data : ListData.gender) {
            listY.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listY);
        student_gender.setItems(listData);
    }

    //Year list
    public void yearList() {
        List<String> listY = new ArrayList<>();

        for (String data : ListData.year) {
            listY.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listY);
        student_year.setItems(listData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yearList();
        studentCourse();
        studentPayementStatus();
        studentSection();
        studentGender();
        studentStatusSeven();
        displayStudentNumber();

        //
    }

}
