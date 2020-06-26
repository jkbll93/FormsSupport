package jkbll93.Database.service;

import jkbll93.Database.exception.FileStorageException;
import jkbll93.Database.exception.MyFileNotFoundException;
import jkbll93.Database.model.ContractDocument;
import jkbll93.Database.model.DBDocument;
import jkbll93.Database.model.FormDocument;
import jkbll93.Database.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository fileRepository;

    public DBDocument storeDocument(String date, String formType, String values, MultipartFile file){

        switch (formType){
            case "form":
                return formDocumentFound(date, values, file);
            case "contract":
                return contractDocumentFound(date, values, file);
            default:
                return null;
        }
    }

    private DBDocument contractDocumentFound(String date, String values, MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String[] value = values.split("_");

        String employerName = value[0];
        String employerSurname = value[1];
        String company = value[2];
        String employeeName = value[3];
        String employeeSurname = value[4];

        try {
            ContractDocument formDocument = new ContractDocument(date, createServerCreationDate(), fileName,"contract", file.getContentType(), file.getBytes(),
                    employerName, employerSurname, company, employeeName, employeeSurname);
            return fileRepository.save(formDocument);
        } catch (Exception ex){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    private FormDocument formDocumentFound(String date, String values, MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String[] value = values.split("_");

        String yourName = value[0];
        String yourSurname = value[1];
        String name = value[2];
        String surname = value[3];
        String position = value[4];

        try {
            FormDocument formDocument = new FormDocument(date, createServerCreationDate(), fileName,"form", file.getContentType(), file.getBytes(),
                    yourName, yourSurname, name, surname, position);
            return fileRepository.save(formDocument);
        } catch (Exception ex){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    private Timestamp createServerCreationDate(){
        return new Timestamp(System.currentTimeMillis());
    }

    public DBDocument getDocument(String id){
        return fileRepository.findById(id)
                .orElseThrow(()-> new MyFileNotFoundException("Nie znaleziono pliku"));
    }

    public List<DBDocument> getDocuments(){
        return fileRepository.findAll();
    }
}
