package org.mine.model;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.File;

public class FileUploadForm {
    @FormParam("applicationFile")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private FileUpload fileData;

    public String getFileName() {
        return fileData.fileName();
    }

    public File getFileAsFile() {
        return fileData.uploadedFile().toFile();
    }
}
