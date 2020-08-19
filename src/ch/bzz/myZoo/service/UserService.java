package ch.bzz.myZoo.service;

import ch.bzz.myZoo.data.*;
import ch.bzz.myZoo.model.*;

import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * short description
 * <p>
 * myZoo_services
 *
 * @author Mia Gudelj
 * @version 1.0
 * @since 01.04.20
 */

@Path("user")
public class UserService {

    @Path("login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@Size(min = 2, max = 20)
                          @FormParam("username") String username,
                          @Min(value = 6)
                          @FormParam("passwort") String passwort) {
        int status;

        User user = DataHandler.readUser(username, passwort);
        if (user.getUserRole().equals("guest")) {
            status = 404;
        } else {
            status = 200;
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                user.getUserRole(),
                "/",
                "", //zb. ghwalin.ch
                "Login-Cookie",
                600,
                false //wenn man produktiv ist dann unbedingt true!
        );

        Response response = Response
                .status(status)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

    @Path("logout")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout() {

        NewCookie cookie = new NewCookie(
                "userRole",
                "guest",
                "/",
                "", //zb. ghwalin.ch
                "Logout-Cookie",
                1,
                false //wenn man produktiv ist dann unbedingt true!
        );

        Response response = Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }
}
