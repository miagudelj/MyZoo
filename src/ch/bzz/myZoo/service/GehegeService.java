package ch.bzz.myZoo.service;

import ch.bzz.myZoo.data.*;
import ch.bzz.myZoo.model.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

/**
 * short description
 * <p>
 * MyZoo
 *
 * @author Mia Gudelj
 * @version 1.0
 * @since 15.04.20
 */
@Path("gehege")
public class GehegeService {

    /**
     * produces a map of all zoos
     *
     * @param userRole the role of the current use
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listZoos(
            @CookieParam("userRole") String userRole
    ) {
        Map<String, Zoo> zooMap = null;
        int status;
        if (userRole == null || userRole.equals("guest")) {
            status = 403;
        } else {
            status = 200;
            zooMap = DataHandler.getZooMap();
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        Response response = Response
                .status(status)
                .entity(zooMap)
                .cookie(cookie)
                .build();
        return response;

    }

    /**
     * reads a zoo identified by its uuid
     *
     * @param zooUUID the zooUUID to be searched
     * @param userRole      the role of the current user
     * @return Response with zoo-object
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readZoo(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("zooUUID") String zooUUID,
            @CookieParam("userRole") String userRole) {
        Zoo zoo = null;
        int status;
        if (userRole == null || userRole.equals("guest")) {
            status = 403;
        } else {
            zoo = DataHandler.getZooMap().get(zooUUID);

            if (zoo != null) {
                status = 200;
            } else {
                status = 404;
            }
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        Response response = Response
                .status(status)
                .entity(zoo)
                .cookie(cookie)
                .build();
        return response;

    }

    /**
     * creates a new zoo
     *
     * @param zoo a valid zoo
     * @param userRole  the role of the current user
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createZoo(
            @Valid @BeanParam Zoo zoo,
            @CookieParam("userRole") String userRole
    ) {
        int status;
        if (userRole != null && userRole.equals("admin")) {

            status = 200;

            zoo.setZooUUID(UUID.randomUUID().toString());

            Map<String, Zoo> zooMap = DataHandler.getZooMap();

            zooMap.put(zoo.getZooUUID(), zoo);
            DataHandler.writeZoo(zooMap);

        } else {
            status = 403;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        Response response = Response
                .status(status)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

    /**
     * updates an existing zoo
     *
     * @param zoo a valid zoo
     * @param userRole  the role of the current user
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateZoo(
            @Valid @BeanParam Zoo zoo,
            @CookieParam("userRole") String userRole
    ) {
        int status;
        if (userRole != null && userRole.equals("admin")) {

            Map<String, Zoo> zooMap = DataHandler.getZooMap();
            if (zooMap.containsKey(zoo.getZooUUID())) {
                zooMap.put(zoo.getZooUUID(), zoo);
                DataHandler.writeZoo(zooMap);
                status = 200;
            } else {
                status = 404;
            }
        } else {
            status = 403;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        Response response = Response
                .status(status)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }
}
