package sk.myProject.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import sk.myProject.gamestudio.entity.Comment;
import sk.myProject.gamestudio.service.CommentException;
import sk.myProject.gamestudio.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/comment")
public class CommentServiceRest {

    @Autowired
    private CommentService commentService;

    //http://localhost:8080/rest/comment
    @POST
    public Response addComment(Comment comment) throws CommentException {
        commentService.addComment(comment);

        return Response.ok().build();
    }

    //http://localhost:8080/rest/comment/clovece
    @GET
    @Path("/{game}")
    @Produces("application/json")
    public List<Comment> getComments(@PathParam("game") String game) throws CommentException {
        return commentService.getComments(game);
    }
}
