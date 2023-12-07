package br.com.banco.controller;

import br.com.banco.service.ContaCorrenteService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/deletarconta")
public class DeletarController {
    @Inject
    ContaCorrenteService correnteService;

    @DELETE
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteConta(@PathParam("id") Long id) {
        try {
            boolean contaExcluida = correnteService.deleteConta(id);
            if (contaExcluida) {
                return Response.ok("Conta excluída com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Conta não encontrada para o número: " + id).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir a conta.").build();
        }
    }



}