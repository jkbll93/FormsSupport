package jkbll93.Database.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class ContractDocument extends DBDocument{

    private String employerName;
    private String employerSurname;
    private String company;

    private String employeeName;
    private String employeeSurname;


    public ContractDocument(){

    }

    public ContractDocument(String creationDate, Timestamp serverCreationDate, String fileName, String userFileType, String fileType, byte[] file,
                            String employerName, String employerSurname, String company,
                            String employeeName, String employeeSurname) {
        super(creationDate, serverCreationDate, fileName, userFileType, fileType, file);
        this.employerName = employerName;
        this.employerSurname = employerSurname;
        this.company = company;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerSurname() {
        return employerSurname;
    }

    public void setEmployerSurname(String employerSurname) {
        this.employerSurname = employerSurname;
    }

    @Override
    public String getFileName() {
        return super.getFileName();
    }

    @Override
    public void setFileName(String fileName) {
        super.setFileName(fileName);
    }

    @Override
    public String getFileType() {
        return super.getFileType();
    }

    @Override
    public void setFileType(String fileType) {
        super.setFileType(fileType);
    }

    public String getAllValues(){
        return "Umowa o pracę pomiędzy " + getEmployerName() + " " + getEmployerSurname() + " reprezentującym firmę " + getCompany() +
                " zwanym dalej Pracodawcą, a " + getEmployeeName() + " " + getEmployeeSurname() + " zwanym dalej Pracownikiem.";
    }
}
