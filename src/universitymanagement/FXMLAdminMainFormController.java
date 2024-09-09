/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitymanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author RANDRIANARISOA
 */
public class FXMLAdminMainFormController implements Initializable {

    @FXML
    private Button addCourse_btn;

    @FXML
    private AnchorPane admin_view_form;
    @FXML
    private Button addStudent_addBtn;

    @FXML
    private Button addStudent_btn;

    @FXML
    private TableColumn<StudentData, String> addStudent_cci_course;

    @FXML
    private TableColumn<StudentData, String> addStudent_cci_dateInsert;

    @FXML
    private TableColumn<StudentData, String> addStudent_cci_name;

    @FXML
    private TableColumn<StudentData, String> addStudent_cci_pay;

    @FXML
    private TableColumn<StudentData, String> addStudent_cci_section;

    @FXML
    private TableColumn<StudentData, String> addStudent_cci_status;

    @FXML
    private TableColumn<StudentData, String> addStudent_cci_statusPayement;

    @FXML
    private TableColumn<StudentData, String> addStudent_cci_studentNumber;

    @FXML
    private TableColumn<StudentData, String> addStudent_cci_year;

    @FXML
    private Button addStudent_deleteBtn;

    @FXML
    private AnchorPane addStudent_form;

    @FXML
    private TableView<StudentData> addStudent_tableView;

    @FXML
    private Button addStudent_updateBtn;

    @FXML
    private Button addSubject_btn;

    @FXML
    private Button addTeacher_btn;

    @FXML
    private Button dashborad_btn;

    @FXML
    private Label great_username;

    @FXML
    private Button payement_btn;

    @FXML
    private Button salary_btn;

    //Database tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private AlertMessage alert = new AlertMessage();

    public ObservableList<StudentData> addStudentgetData() {
        ObservableList<StudentData> listData = FXCollections.observableArrayList();
        String selectData = "SELECT * FROM student";

        connect = DataBase.connectDB();
        StudentData sData;
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {
                //public StudentData(Integer id, String studentId, String name, String year, String course,
                //String section, Double payement, String statusPayement, java.util.Date dateInsert, String status )

                sData = new StudentData(result.getInt("id"), result.getString("student_id"), result.getString("full_name"),
                        result.getString("year"), result.getString("course"), result.getString("section"), result.getDouble("payement"), result.getString("status_payement"),
                        result.getDate("date_insert"), result.getString("status"));
                listData.add(sData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<StudentData> addStudentListData;

    public void addStudentDisplayData() {
        this.addStudentListData = addStudentgetData();

        addStudent_cci_studentNumber.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        addStudent_cci_name.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        addStudent_cci_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudent_cci_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudent_cci_section.setCellValueFactory(new PropertyValueFactory<>("section"));
        addStudent_cci_pay.setCellValueFactory(new PropertyValueFactory<>("payement"));
        addStudent_cci_statusPayement.setCellValueFactory(new PropertyValueFactory<>("statusPayement"));
        addStudent_cci_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        addStudent_cci_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        addStudent_tableView.setItems(this.addStudentListData);
    }

    
    public void change_screen_first() {
        admin_view_form.setVisible(true);
        addStudent_form.setVisible(false);
    }
    
    public void change_screen_second() {
        admin_view_form.setVisible(false);
        addStudent_form.setVisible(true);
    }
    
    public void addStudentAddBtn() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            
            stage.show();
        } catch(Exception e) {e.printStackTrace();}
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addStudentDisplayData();
    }

}
