/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitymanagement;

import java.util.Date;

/**
 *
 * @author RANDRIANARISOA
 */
public class StudentData {
    private Integer id;
    private String studentId;
    private String fullName;
    private String gender;
    private Date birthday;
    private String year;
    private String course;
    private String section;
    private Double payement;
    private String statusPayement;
    private String image;
    private Date dateInsert;
    private Date dateUpdate;
    private Date dateDelete;
    private String status;
    
    public StudentData(Integer id,String studentId, String fullName,String gender,Date birthday,String year, String course, String section,Double payement, String statusPayement,String image, Date dateInsert, Date dateUpdate, Date dateDelete, String status) {
        this.id = id;
        this.studentId = studentId;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.year = year;
        this.course = course;
        this.section = section;
        this.payement = payement;
        this.statusPayement = statusPayement;
        this.image = image;
        this.dateInsert = dateInsert;
        this.dateDelete = dateDelete;
        this.status = status;
    }
    public StudentData(Integer id, String studentId, String name, String year, String course,
            String section, Double payement, String statusPayement, Date dateInsert, String status ) {
        this.id = id;
        this.studentId = studentId;
        this.fullName = name;
        this.year = year;
        this.course = course;
        this.section = section;
        this.payement = payement;
        this.statusPayement = statusPayement;
        this.dateInsert = dateInsert;
        this.status = status;
        
    }
    
    public Integer getId(){
        return this.id;
    }
    public String getStudentId() {
        return this.studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getFullName() {
        return this.fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public Date getBirthday() {
        return this.birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public String getYear() {
        return this.year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    
    public String getCourse() {
        return this.course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    
    public String getSection() {
        return this.section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    
    public Double getPayement() {
        return this.payement;
    }
    public void setPayement(Double payement) {
        this.payement = payement;
    }
    
    public String getStatusPayement() {
        return this.statusPayement;
    }
    public void setStatusPayement(String statusPayement) {
        this.statusPayement = statusPayement;
    }
    
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    
    public Date getDateInsert() {
        return this.dateInsert;
    }
    
    public Date getDateDelete() {
        return this.dateDelete;
    }
    public void setDateDelete(Date dateDelete) {
        this.dateDelete = dateDelete;
    }
    
    public Date getDateUpdate(){
        return this.dateUpdate;
    }
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
