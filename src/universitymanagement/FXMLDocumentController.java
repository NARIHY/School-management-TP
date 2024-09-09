/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package universitymanagement;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import model.Utilisateur;

/**
 *
 * @author RANDRIANARISOA
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private PasswordField admin_confirmPassword;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private PasswordField admin_password;

    @FXML
    private Hyperlink admin_signin;

    @FXML
    private Button admin_signupBtn;

    @FXML
    private TextField admin_username;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private ComboBox<String> login_role;

    @FXML
    private TextField login_username;

    @FXML
    private PasswordField student_confirmPassword;

    @FXML
    private TextField student_email;

    @FXML
    private AnchorPane student_form;

    @FXML
    private PasswordField student_password;

    @FXML
    private Hyperlink student_signin;

    @FXML
    private Button student_signupBtn;

    @FXML
    private TextField student_username;

    @FXML
    private PasswordField teacher_confirmPassword;

    @FXML
    private TextField teacher_email;

    @FXML
    private AnchorPane teacher_form;

    @FXML
    private PasswordField teacher_password;

    @FXML
    private Hyperlink teacher_signin;

    @FXML
    private Button teacher_signupBtn;

    @FXML
    private TextField teacher_username;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    //Alert message
    private AlertMessage alert = new AlertMessage();

    //Student id
    private  int studentID;
    private int teacherId = 0;

    //Auth
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 128;

    //Registration for Admin
    public void registerAdmin() throws SQLException {

        if (admin_username.getText().isEmpty()
                || admin_password.getText().isEmpty()
                || admin_confirmPassword.getText().isEmpty()) {
            alert.errorMessage("Please fill all blanks fields");
        } else {
            connect = DataBase.connectDB();

            String selectData = "SELECT * FROM users WHERE username = '" + admin_username.getText() + "'";
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                if (result.next()) {
                    alert.errorMessage(admin_username.getText() + " is already exists");
                } else if (!admin_password.getText().equals(admin_confirmPassword.getText())) {
                    alert.errorMessage("Password does not match.");
                } else if (admin_password.getLength() < 8) {
                    alert.errorMessage("Invalid password at least 8 caracters needed.");
                } else {
                    String insertData = "INSERT INTO users (username, password, role, date, salt)"
                            + "VALUES (?, ?, ?, ?, ?)";
                    //Generate salt and Hash password 
                    byte[] salt = generateSalt();
                    String password = hashPassword(admin_password.getText(), salt);
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, admin_username.getText());
                    prepare.setString(2, password);
                    prepare.setString(3, "Admin");
                    prepare.setDate(4, sqlDate);
                    prepare.setString(5, Base64.getEncoder().encodeToString(salt));

                    prepare.executeUpdate();

                    //Succes
                    alert.successMessage("Regestred successfull");

                    login_form.setVisible(true);
                    admin_form.setVisible(false);

                    //DROP FIELDS
                    admin_username.setText("");
                    admin_password.setText("");
                    admin_confirmPassword.setText("");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //Registration for student
    public void studentRegistration() {
        if (student_email.getText().isEmpty()
                || student_username.getText().isEmpty()
                || student_password.getText().isEmpty()
                || student_confirmPassword.getText().isEmpty()) {
            alert.errorMessage("Please fill all blanks fields");
        } else {
            connect = DataBase.connectDB();
            String selectData = "SELECT * FROM users  WHERE username = '" + student_username.getText() + "'";
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                if (result.next()) {
                    alert.errorMessage(student_username.getText() + " is already exists");
                } else if (!student_password.getText().equals(student_confirmPassword.getText())) {
                    alert.errorMessage("Password does not match.");
                } else if (student_password.getLength() < 8) {
                    alert.errorMessage("Invalid password at least 8 caracters needed.");
                } else {
                    //
                    studentIDGenerator();
                    String insertStudent = "INSERT INTO student (student_id,date_insert,status)"
                            + "VALUES (?, ?, ?)";

                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                    String getYear = format.format(date);
                    int tempYear = Integer.parseInt(getYear);

                    String student_num = String.valueOf(tempYear) + String.valueOf(studentID);
                    //
                    String insertData = "INSERT INTO users (email,username, password, role, date, student_id, salt)"
                            + "VALUES (?, ?, ?, ?, ?,?,?)";
                    //Generate salt and Hash password 
                    byte[] salt = generateSalt();
                    String password = hashPassword(student_password.getText(), salt);
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, student_email.getText());
                    prepare.setString(2, student_username.getText());
                    prepare.setString(3, password);
                    prepare.setString(4, "Student");
                    prepare.setDate(5, sqlDate);
                    prepare.setString(6, student_num);
                    prepare.setString(7, Base64.getEncoder().encodeToString(salt));

                    prepare.executeUpdate();

                    prepare = connect.prepareStatement(insertStudent);
                    prepare.setString(1, student_num);
                    prepare.setDate(2, sqlDate);
                    prepare.setString(3, "Approval");

                    prepare.executeUpdate();

                    //Succes
                    alert.successMessage("Regestred successfull");

                    login_form.setVisible(true);
                    student_form.setVisible(false);
                    //Close connection
                    connect.close();

                    //DROP FIELDS
                    student_email.setText("");
                    student_username.setText("");
                    student_password.setText("");
                    student_confirmPassword.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int studentIDGenerator() {
        String selectData = "SELECT MAX(id) FROM student";

        connect = DataBase.connectDB();

        int temp_studentID = 0;
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(selectData);

            if (result.next()) {
                temp_studentID = result.getInt("MAX(id)");
            }

            if (temp_studentID == 0) {
                studentID += 1;
            } else {
                studentID = temp_studentID + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentID;
    }

    //Teacher registration
    public void teacherRegistration() {
        if (teacher_email.getText().isEmpty()
                || teacher_username.getText().isEmpty()
                || teacher_password.getText().isEmpty()
                || teacher_confirmPassword.getText().isEmpty()) {
            alert.errorMessage("Please fill all blanks fields");
        } else {
            connect = DataBase.connectDB();

            String selectData = "SELECT * FROM users  WHERE username = '" + teacher_username.getText() + "'";
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                if (result.next()) {
                    alert.errorMessage(teacher_username.getText() + " is already exists");
                } else if (!teacher_password.getText().equals(teacher_confirmPassword.getText())) {
                    alert.errorMessage("Password does not match.");
                } else if (teacher_password.getLength() < 8) {
                    alert.errorMessage("Invalid password at least 8 caracters needed.");
                } else {
                    //
                    studentIDGenerator();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                    String getYear = format.format(date);
                    int tempYear = Integer.parseInt(getYear);
                    String teacher_num = "TD-" + String.valueOf(teacherId) + String.valueOf(tempYear);
                    //
                    String insertData = "INSERT INTO users (email,username, password, role, date, teacher_id, salt)"
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    //Generate salt and Hash password 
                    byte[] salt = generateSalt();
                    String password = hashPassword(teacher_password.getText(), salt);
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, teacher_email.getText());
                    prepare.setString(2, teacher_username.getText());
                    prepare.setString(3, password);
                    prepare.setString(4, "Teacher");
                    prepare.setDate(5, sqlDate);
                    prepare.setString(6, teacher_num);
                    prepare.setString(7, Base64.getEncoder().encodeToString(salt));
                    prepare.executeUpdate();

                    //Insert teacher from teacher
                    String insertTeacher = "INSERT INTO teacher (teacher_id,date_insert,status)"
                            + "VALUES (?, ?, ?)";

                    prepare = connect.prepareStatement(insertTeacher);
                    prepare.setString(1, teacher_num);
                    prepare.setDate(2, sqlDate);
                    prepare.setString(3, "Approval");

                    prepare.executeUpdate();

                    //Succes
                    alert.successMessage("Regestred successfull");

                    login_form.setVisible(true);
                    teacher_form.setVisible(false);

                    connect.close();

                    //DROP FIELDS
                    teacher_email.setText("");
                    teacher_username.setText("");
                    teacher_password.setText("");
                    teacher_confirmPassword.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int TeacherIDGenerator() {
        String sql = "SELECT MAX(id) FROM teacher";

        connect = DataBase.connectDB();

        int temp_teacherID = 0;
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()) {
                temp_teacherID = result.getInt("MAX(id)");
            }

            if (temp_teacherID == 0) {
                teacherId += 1;
            } else {
                teacherId = temp_teacherID + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacherId;
    }

    //List of roles
    public void roleList() {
        List<String> listR = new ArrayList<>();
        for (String data : ListData.role) {
            listR.add(data);
        }
        //Observable list for seeing the list in select
        ObservableList listData = FXCollections.observableArrayList(listR);
        login_role.setItems(listData);

    }

    //Sign in
    public void singInForm() {
        login_form.setVisible(true);
        admin_form.setVisible(false);
        student_form.setVisible(false);
        teacher_form.setVisible(false);
    }

    //Switch interface
    public void switchForm(ActionEvent event) {
        //Verification
        switch (login_role.getSelectionModel().getSelectedItem()) {
            case "Admin":
                login_form.setVisible(false);
                admin_form.setVisible(true);
                student_form.setVisible(false);
                teacher_form.setVisible(false);
                break;
            case "Student":
                login_form.setVisible(false);
                admin_form.setVisible(false);
                student_form.setVisible(true);
                teacher_form.setVisible(false);
                break;
            case "Teacher":
                login_form.setVisible(false);
                admin_form.setVisible(false);
                student_form.setVisible(false);
                teacher_form.setVisible(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleList(); //list of all roles
    }

    public void login() {
        String sql = "SELECT * FROM users WHERE username = '" + login_username.getText() + "'";

        connect = DataBase.connectDB();
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()) {
                //get role 
                String role = result.getString("role");
                //Get the password
                String storedHash = result.getString("password");
                byte[] storedSalt = Base64.getDecoder().decode(result.getString("salt"));

                if (validatePassword(login_password.getText(), storedHash, storedSalt)) {
                    //Just connect
                    
                    Thread.sleep(1000);
                    
                    if (role.equals("Admin")) {
                        //LINK YOUR MAIN FORM ADMIN
                        System.out.println("Admin");
                        Parent root = FXMLLoader.load(getClass().getResource("AdminMainForm.fxml"));
                        //Instance of new stage
                        Stage stage = new Stage();
                        stage.setTitle("UNIVERSITY MANAGEMENT SYSTEM | Admin Portal");
                        stage.setScene(new Scene(root));
                        
                        stage.show();
                        
                        //To hide your Login form
                        login_btn.getScene().getWindow().hide();
                    } else if (role.equals("Teacher")) {
                        //LINK YOUR MAIN FORM TEACHER
                        System.out.println("Teacher");
                    } else if (role.equals("Student")) {
                        //LINK YOUR MAIN FORM STUDENT
                        System.out.println("Student");
                    } else {
                        //LINK A GUEST
                    }
                } else {
                    alert.errorMessage("Invalid password or username");
                }

            } else {
                alert.errorMessage("Undifiend users,this users doesn't exists in the database. please register if you don't have a compte");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hash one password given with salt giving
     *
     * @param password password to hash
     * @param salt salt given
     * @return password hashed with incoded base64
     * @throws NoSuchAlgorithmException if algo doesn't exists
     * @throws InvalidKeySpecException If key are invalid
     */
    private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Valide un mot de passe par rapport à un hash et un sel stockés.
     *
     * @param password password to validate
     * @param storedHash hash for comparaison.
     * @param salt salt stocked in database for hashing.
     * @return True if password are equals and false if another.
     * @throws NoSuchAlgorithmException if algo doesn't exists
     * @throws InvalidKeySpecException If key are invalid
     */
    private static boolean validatePassword(String password, String storedHash, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hashedPassword = hashPassword(password, salt);
        return hashedPassword.equals(storedHash);
    }

    /**
     * generate a new random salt
     *
     * @return array of octets with salt generate
     */
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }
}
