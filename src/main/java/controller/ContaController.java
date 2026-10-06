package controller;

import entity.Conta;
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

@Path("conta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContaController {

    @GET
    public List<Conta> todas() {
        return Conta.listAll();
    }

    @GET
    @Path("{id}")
    public Conta buscar(@PathParam("id") Long id) {
        Conta conta = Conta.findById(id);
        if (conta == null) {
            throw new NotFoundException("Conta " + id + " não encontrada");
        }
        return conta;
    }

    @POST
    @Transactional
    public Response save(Conta conta) {
        conta.id = null;
        conta.persist();
        return Response.status(Response.Status.CREATED).entity(conta).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Conta update(@PathParam("id") Long id, Conta dados) {
        Conta conta = Conta.findById(id);
        if (conta == null) {
            throw new NotFoundException("Conta " + id + " não encontrada");
        }
        conta.descricao = dados.descricao;
        return conta;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean excluiu = Conta.deleteById(id);
        if (!excluiu) {
            throw new NotFoundException("Conta " + id + " não encontrada");
        }
        return Response.noContent().build();
    }
}
