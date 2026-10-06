package controller;

import entity.Categoria;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("categoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaController {

    @GET
    public List<Categoria> todas() {
        return Categoria.listAll();
    }

    @GET
    @Path("{id}")
    public Categoria buscar(@PathParam("id") Long id) {
        Categoria cat = Categoria.findById(id);
        if (cat == null) {
            throw new NotFoundException("Categoria " + id + " não encontrada");
        }
        return cat;
    }

    @POST
    @Transactional
    public Response save(Categoria cat) {
        cat.id = null; // o banco gera o id
        cat.persist();
        return Response.status(Response.Status.CREATED).entity(cat).build();
    }

    // PUT -> editar um registro
    @PUT
    @Path("{id}")
    @Transactional
    public Categoria update(@PathParam("id") Long id, Categoria dados) {
        Categoria cat = Categoria.findById(id);
        if (cat == null) {
            throw new NotFoundException("Categoria " + id + " não encontrada");
        }
        cat.descricao = dados.descricao;
        return cat; // alterações são gravadas no fim da transação
    }

    // DELETE -> excluir um registro
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean excluiu = Categoria.deleteById(id);
        if (!excluiu) {
            throw new NotFoundException("Categoria " + id + " não encontrada");
        }
        return Response.noContent().build();
    }
}
