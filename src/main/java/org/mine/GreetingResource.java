package org.mine;

import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.core.multipart.FormData;
import org.mine.model.Message;
import org.mine.model.Parameters;

import javax.print.attribute.standard.Media;
import java.util.Map;
import java.util.Objects;

@Path("/hello")
public class GreetingResource {
    @Inject
    RoutingContext routingContext;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello(@BeanParam Parameters p) {
        return "Hello from Quarkus " + p.name + " from " + "bandung";
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postHello(Parameters parameters) {
        System.out.println("Print sourceIP: " +  routingContext.request().remoteAddress().host());
        if (!Objects.isNull(parameters)) {
            System.out.println("Greeting Resource: " + parameters.namespace);
        }
        return Response.status(Response.Status.OK).build();
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response postHello(@BeanParam Parameters p, Message message) {
//        if (!Objects.isNull(message)) {
//            return Response.status(Response.Status.OK)
//                    .entity(Map.of(
//                            "response", Map.of(
//                                    "name", p.name,
//                                    "message", message
//                            )
//                    )).type(MediaType.APPLICATION_JSON).build();
//        }
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }
}
