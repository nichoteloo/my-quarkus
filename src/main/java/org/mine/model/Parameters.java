package org.mine.model;

//import org.jboss.resteasy.annotations.Body;
import org.jboss.resteasy.reactive.RestCookie;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestHeader;
import org.jboss.resteasy.reactive.RestQuery;

public class Parameters {
    public @RestQuery("name") String name;
    public @RestHeader("x-access-token") String token;
    public @RestCookie("tenantId") String cTenantId;
    public @RestForm("namespace") String namespace;
}