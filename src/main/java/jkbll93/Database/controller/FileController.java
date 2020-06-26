package jkbll93.Database.controller;

import jkbll93.Database.model.ContractDocument;
import jkbll93.Database.model.DBDocument;
import jkbll93.Database.model.FormDocument;
import jkbll93.Database.payload.UploadFileResponse;
import jkbll93.Database.service.DBFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DBFileStorageService documentStoreService;

    @PostMapping("/sendData")
    public UploadFileResponse uploadFile(@RequestHeader("documentType") String userFileType, @RequestHeader("values") String values, @RequestHeader("creationDate") String date, @RequestParam("file") MultipartFile file){

        DBDocument dbDocument = documentStoreService.storeDocument(date, userFileType, values, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbDocument.getFileName())
                .toUriString();

        return new UploadFileResponse(dbDocument.getFileName(), fileDownloadUri);
    }

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){

        DBDocument dbDocument = documentStoreService.getDocument(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbDocument.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbDocument.getFileName() + "\"")
                .body(new ByteArrayResource(dbDocument.getFile()));
    }

    @GetMapping("/downloadNames")
    public ResponseEntity<String> downloadFiles(HttpServletRequest request){

        List<DBDocument> dbDocuments = documentStoreService.getDocuments();

        StringBuilder stringBuilder = new StringBuilder();

        String content = "";
        String fileName = "";

        stringBuilder.append("<article>");

        if(dbDocuments.isEmpty()){
            stringBuilder.append("<h2>Nie znaleziono żadnych plików :(</h2>");
        } else {
            int files = dbDocuments.size();
            stringBuilder.append("<h2>Znalazłem: ");
            stringBuilder.append(files);
            stringBuilder.append(foundFiles(files));
            stringBuilder.append(":</h2><br>");
            stringBuilder.append("<table>");

            for (DBDocument dbDocument : dbDocuments) {
                stringBuilder.append("<tr>");

                fileName = dbDocument.getFileName();

                stringBuilder.append("<td><a href=\"/downloadFile/");
                stringBuilder.append(fileName);
                stringBuilder.append("\">");
                stringBuilder.append(fileName);
                stringBuilder.append("</a></td><td>");

                switch (dbDocument.getUserFileType()) {
                    case "form":
                        content = ((FormDocument) dbDocument).getAllValues();
                        break;
                    case "contract":
                        content = ((ContractDocument) dbDocument).getAllValues();
                        break;
                }
                stringBuilder.append(content);
                stringBuilder.append("</td>");
                stringBuilder.append("</tr>");
            }
            stringBuilder.append("</table>");
        }

        stringBuilder.append("</article>");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment")
                .body(stringBuilder.toString());
    }

    private String foundFiles(int files){
        if (files == 0){
            return " plików";
        } else if (files == 1){
            return " plik";
        } else if ((files == 2) || (files == 3) || (files == 4) ){
            return " pliki";
        } else {
            return " plików";
        }
    }
}
