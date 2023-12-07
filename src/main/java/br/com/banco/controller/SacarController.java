package br.com.banco.controller;

import br.com.banco.entity.ContaCorrente;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/sacar")
public class SacarController {

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sacar(@PathParam("id") Long id, ContaCorrente contaCorrente) {
        if (contaCorrente == null) {
            return Response.status(404).entity("Conta não encontrada").build();
        }

        double valorDoSaque = contaCorrente.getValorDoSaque();

        if (contaCorrente.getSaldo() < valorDoSaque) {
            return Response.status(400).entity("Saldo insuficiente").build();
        }

        contaCorrente.setSaldo(contaCorrente.getSaldo() - valorDoSaque);

        return Response.status(200).entity("Saque realizado com sucesso. Saldo atual: " + contaCorrente.getSaldo()).build();
    }
}
