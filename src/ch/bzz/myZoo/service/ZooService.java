package ch.bzz.myZoo.service;


import ch.bzz.myZoo.data.DataHandler;
import ch.bzz.myZoo.model.Gehege;
import ch.bzz.myZoo.model.Tier;
import ch.bzz.myZoo.model.Zoo;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

/**
 * short description
 * <p>
 * Zoo
 *
 * @author Mia Gudelj
 * @version 1.0
 * @since 03.03.20
 */

@Path("zoo")
public class ZooService extends Application {

    /**
     * lists all animals
     * listet alle tiere auf
     *
     * @return response
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTiere(@CookieParam("userRole") String userRole) {

        //variabeln
        int status;
        Map<String, Tier> tierMap = null;

        if (userRole == null || userRole.equals("guest")) {
            status = 403;
        } else {
            tierMap = new Gehege().getTierMap();
            status = 200;
        }
        //logik
        Response response = Response
                .status(status)
                .entity(tierMap)
                .build();
        return response;
    }

    /**
     * reads an animal
     * liest ein Tier
     *
     * @param tierUUID
     * @return response
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readTier(
            @CookieParam("userRole") String userRole,

            @Pattern(regexp = "[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}")
            @QueryParam("tierUUID") String tierUUID) {

        //variabeln
        Map<String, Tier> tierMap = null;
        Tier tier = null;
        int status;

        //logik
        if (userRole == null || userRole.equals("guest")) {
            status = 403;
        } else {
            tierMap = new Gehege().getTierMap();
            if (tierMap.containsKey(tierUUID)) {
                tier = tierMap.get(tierUUID);

                status = 200;

            } else if (!tierMap.containsKey(tierUUID)) {
                status = 404;

            } else {
                status = 400;
            }
        }

        Response response = Response
                .status(status)
                .entity(tier)
                .build();
        return response;
    }

    /**
     * creates an animal
     * erstellt ein Tier
     *
     * @param userRole
     * @param tier
     * @return response
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createTier(
            @CookieParam("userRole") String userRole,
            @Valid @BeanParam Tier tier
    ) {

        //variabeln
        int status;

        //logik
        if (userRole == null || userRole.equals("guest") || userRole.equals("user")) {
            status = 403;
        } else {
            if (DataHandler.getZooMap().containsKey(tier.getZoo().getZooUUID())) {

                tier.setTierUUID(UUID.randomUUID().toString());

                Zoo zoo = DataHandler.getZooMap().get(tier.getZoo().getZooUUID());
                tier.setZoo(zoo);

                Gehege gehege = new Gehege();
                gehege.getTierMap().put(tier.getTierUUID(), tier);
                DataHandler.writeTiere(gehege.getTierMap());

                status = 200;

            } else {
                status = 404;
            }
        }

        Response response = Response
                .status(status)
                .entity("Aufruf erfolgreich")
                .build();
        return response;
    }

    /**
     * updates an animal
     * updated ein Tier
     *
     * @param userRole
     * @param tier
     * //@param fell
     * @return response
     */
    @Path("update")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateTier(
            @CookieParam("userRole") String userRole,
            @Valid @BeanParam Tier tier
    ) {
        //variabeln
        Gehege gehege = null;
        int status;

        //logik
        if (userRole == null || userRole.equals("guest") || userRole.equals("user")) {
            status = 403;
        } else {
            if (DataHandler.getTierMap().containsKey(tier.getTierUUID())) {
                gehege = new Gehege();

                if (DataHandler.getZooMap().containsKey(tier.getZoo().getZooUUID())) {
                    Zoo zoo = DataHandler.getZooMap().get(tier.getZoo().getZooUUID());
                    tier.setZoo(zoo);

                    gehege.getTierMap().put(tier.getTierUUID(), tier);

                    DataHandler.writeTiere(gehege.getTierMap());
                }

                status = 200;

            } else if (!(DataHandler.getTierMap().containsKey(tier.getTierUUID()))) {
                status = 400;

            } else {
                status = 404;
            }
        }

        Response response = Response
                .status(status)
                .entity("Aufruf erfolgreich")
                .build();
        return response;
    }

    /**
     * deletes an animal
     * loescht ein Tier
     *
     * @param tierUUID
     * @return response
     */
    @Path("delete")
    @DELETE
    @Produces("")
    public Response deleteTier(
            @CookieParam("userRole") String userRole,

            @Pattern(regexp = "[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}")
            @QueryParam("tierUUID") String tierUUID) {

        //variabeln
        Tier tier;
        int status;

        //logik
        if (userRole == null || userRole.equals("guest") || userRole.equals("user")) {
            status = 403;
        } else {
            tier = DataHandler.getTierMap().get(tierUUID);

            if (DataHandler.getTierMap().containsKey(tierUUID)) {
                Gehege gehege = new Gehege();
                gehege.getTierMap().remove(tier.getTierUUID());
                DataHandler.writeTiere(gehege.getTierMap());

                status = 200;

            } else {
                status = 404;
            }
        }

        Response response = Response
                .status(status)
                .entity("Entfernen erfolgreich")
                .build();
        return response;
    }
}
