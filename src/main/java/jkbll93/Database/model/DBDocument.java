package jkbll93.Database.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "documents")
public class DBDocument {

    @Id
    private String fileName;
    private String userFileType;
    private String fileType;

    private String creationDate;

    private Timestamp serverCreationDate;

    private byte[] file;

    public DBDocument(){

    }

    public DBDocument(String creationDate, Timestamp serverCreationDate, String fileName, String userFileType, String fileType, byte[] file) {
        this.creationDate = creationDate;
        this.serverCreationDate = serverCreationDate;
        this.fileName = fileName;
        this.userFileType = userFileType;
        this.fileType = fileType;
        this.file = file;
    }

    public Timestamp getServerCreationDate() {
        return serverCreationDate;
    }

    public void setServerCreationDate(Timestamp serverCreationDate) {
        this.serverCreationDate = serverCreationDate;
    }

    public String getUserFileType() {
        return userFileType;
    }

    public void setUserFileType(String userFileType) {
        this.userFileType = userFileType;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
