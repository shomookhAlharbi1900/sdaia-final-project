package org.example.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.example.services.ApplicationService;
import org.example.services.RequestService;
import org.example.services.SessionService;

@Path("Login")
public class Login {

    @GET
    public String getIt() {
        return "SUCCESS";
    }
}




