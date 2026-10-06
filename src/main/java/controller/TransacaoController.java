package controller;

import entity.Categoria;
import entity.Conta;
import entity.Transacao;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
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

@Path("transacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransacaoController {

    @GET
    public List<Transacao> todas() {
        return Transacao.listAll();
    }

    @GET
    @Path("{id}")
    public Transacao buscar(@PathParam("id") Long id) {
        Transacao t = Transacao.findById(id);
        if (t == null) {
            throw new NotFoundException("Transação " + id + " não encontrada");
        }
        return t;
    }

    @POST
    @Transactional
    public Response save(Transacao t) {
        t.id = null;
        t.conta = carregarConta(t.conta);
        t.categoria = carregarCategoria(t.categoria);
        t.persist();
        return Response.status(Response.Status.CREATED).entity(t).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Transacao update(@PathParam("id") Long id, Transacao dados) {
        Transacao t = Transacao.findById(id);
        if (t == null) {
            throw new NotFoundException("Transação " + id + " não encontrada");
        }
        t.descricao = dados.descricao;
        t.valor = dados.valor;
        t.tipo = dados.tipo;
        t.dataTransacao = dados.dataTransacao;
        t.conta = carregarConta(dados.conta);
        t.categoria = carregarCategoria(dados.categoria);
        return t;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean excluiu = Transacao.deleteById(id);
        if (!excluiu) {
            throw new NotFoundException("Transação " + id + " não encontrada");
        }
        return Response.noContent().build();
    }

    // Busca a conta pelo id enviado no JSON: { "conta": { "id": 1 } }
    private Conta carregarConta(Conta ref) {
        if (ref == null || ref.id == null) {
            throw new BadRequestException("Informe a conta: \"conta\": {\"id\": 1}");
        }
        Conta conta = Conta.findById(ref.id);
        if (conta == null) {
            throw new BadRequestException("Conta " + ref.id + " não existe");
        }
        return conta;
    }

    // Busca a categoria pelo id enviado no JSON: { "categoria": { "id": 1 } }
    private Categoria carregarCategoria(Categoria ref) {
        if (ref == null || ref.id == null) {
            throw new BadRequestException("Informe a categoria: \"categoria\": {\"id\": 1}");
        }
        Categoria cat = Categoria.findById(ref.id);
        if (cat == null) {
            throw new BadRequestException("Categoria " + ref.id + " não existe");
        }
        return cat;
    }
}
