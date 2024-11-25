package org.mine.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Provider
public class IpLoggingFilter implements ContainerRequestFilter {
    @Inject
    RoutingContext routingContext;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if ("GET".equalsIgnoreCase(requestContext.getMethod())) {
            System.out.println("Hello GET debug");
        } else if (
                "POST".equalsIgnoreCase(requestContext.getMethod())
                    && MediaType.MULTIPART_FORM_DATA_TYPE.isCompatible(requestContext.getMediaType())) {
            System.out.println("Hello POST debug");

//            ObjectMapper objectMapper = new ObjectMapper();
//            System.out.println("Media Type " + objectMapper.writeValueAsString(requestContext.getMediaType()));

//            RoutingContext routingContext = (RoutingContext) requestContext.getProperty(RoutingContext.class.getName());
//            if (routingContext != null) {
//                String sourceIp = routingContext.request().remoteAddress().host();
//                System.out.println("Incoming request from IP: " + sourceIp);
//            }
//            System.out.println("Debug namespace form routingContext: " + routingContext.request().getFormAttribute("namespace"));
//            System.out.println("Incoming request from IP: " + routingContext.request().remoteAddress().host());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            requestContext.getEntityStream().transferTo(baos);
            String body = baos.toString(StandardCharsets.UTF_8);
            System.out.println("Debug body: " + body);

            requestContext.setEntityStream(new ByteArrayInputStream(baos.toByteArray()));

            Map<String, String> formData = parseFormData(body);
            String namespace = formData.get("namespace");

            if (namespace != null) {
                System.out.println("Namespace: " + namespace);
            } else {
                System.out.println("Namespace not found");
            }
        }
    }

    private Map<String, String> parseFormData(String body) {
        Map<String, String> formData = new HashMap<>();
        for (String pair : body.split("&")) {
            String[] keyValue = pair.split("=", 2);
            if (keyValue.length == 2) {
                formData.put(keyValue[0], keyValue[1]);
            }
        }
        return formData;
    }
}
