package br.com.banco.controller;

import br.com.banco.entity.Cliente;
import br.com.banco.service.ContaCorrenteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import br.com.banco.entity.ContaCorrente;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/v1/cadastrarconta")
public class CadastrarContaController {
    @Inject
    ContaCorrenteService correnteService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addConta(String jsonInput) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Cliente cliente = objectMapper.readValue(jsonInput, Cliente.class);

            ContaCorrente contaCorrente = new ContaCorrente();
            contaCorrente.setTitular(cliente.getNome());

            correnteService.addConta(contaCorrente);

            String mensagem = String.format(
                    "\nConta criada com sucesso:" +
                            "\nConta Corrente: %s" +
                            "\nSaldo: %s" +
                            "\nTitular:" +
                            "\nID: %s" +
                            "\n  Nome: %s" +
                            "\n  CPF: %s",
                    contaCorrente.getNumeroConta(),
                    contaCorrente.getSaldo(),
                    contaCorrente.getId(),
                    cliente.getNome(),
                    cliente.getCpf());

            return Response.status(Response.Status.CREATED).entity(mensagem).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ocorreu um erro inesperado ao criar a conta.").build();
        }
    }
}