package org.mine;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mine.model.FileUploadForm;
import org.mine.model.Message;
import org.mine.model.Parameters;

import java.util.Map;

@Path("/upload")
public class FileResource {
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@BeanParam Parameters p, FileUploadForm form) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Message message = objectMapper.readValue(form.getFileAsFile(), Message.class);
            return Response.status(Response.Status.OK)
                    .entity(Map.of(
                            "filename", form.getFileName(),
                            "message", message
                    )).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
