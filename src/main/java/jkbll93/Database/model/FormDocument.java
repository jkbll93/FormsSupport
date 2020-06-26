package jkbll93.Database.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class FormDocument extends DBDocument {

    private String yourName;
    private String yourSurname;
    private String name;
    private String surname;
    private String position;

    public FormDocument(){

    }

    public FormDocument(String creationDate, Timestamp serverCreationDate, String fileName, String userFileType, String fileType, byte[] file,
                        String yourName, String yourSurname, String name, String surname, String position) {
        super(creationDate, serverCreationDate, fileName, userFileType, fileType, file);
        this.yourName = yourName;
        this.yourSurname = yourSurname;
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getYourSurname() {
        return yourSurname;
    }

    public void setYourSurname(String yourSurname) {
        this.yourSurname = yourSurname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        return "Podanie " + getYourName() + " " + getYourSurname() + " do " + getName() + " " + getSurname() + " piastującego stanowisko " + getPosition();
    }
}
