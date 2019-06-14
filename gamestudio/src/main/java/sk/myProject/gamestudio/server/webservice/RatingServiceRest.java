package sk.myProject.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import sk.myProject.gamestudio.entity.Rating;
import sk.myProject.gamestudio.service.RatingException;
import sk.myProject.gamestudio.service.RatingService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/rating")
public class RatingServiceRest {

    @Autowired
    private RatingService ratingService;

    //http://localhost:8080/rest/rating
    @POST

    @Consumes("application/json")
    public Response addRating(Rating rating) throws RatingException {

        ratingService.setRating(rating);

        return Response.ok().build();
    }

    //http://localhost:8080/rest/rating/clovece
    @GET
    @Path("/{game}")
    @Produces("application/json")
    public int getAverageRating(@PathParam("game") String game) throws RatingException {
        return ratingService.getAverageRating(game);
    }

}
