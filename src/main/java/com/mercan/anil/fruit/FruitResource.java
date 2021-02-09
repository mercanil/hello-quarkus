package com.mercan.anil.fruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fruit")
public class FruitResource {

    protected final Logger log = LoggerFactory.getLogger(FruitResource.class);

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getFruits(@QueryParam("season") String season){
        List<Fruit> fruitList = Fruit.listAll();
        if (season != null){
            fruitList = Fruit.getBySeason(season);
        }
        return Response.ok(fruitList).build();
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveFruit(Fruit fruit){
        fruit.id = null;
        Fruit.persist(fruit);
        return Response.ok().build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteFruit(@PathParam("id") Long id){
      return Response.ok(Fruit.deleteById(id)).build();
    }

    @GET()
    @Path("/{name}")
    public  String thisIsAHelloExample(@PathParam(("name")) String name){
        log.info("called with :" + name);
        return "Hello:" + name;
    }
}