package br.com.banco.controller;

import br.com.banco.globalExceptionalHandler.ContaInvalidaException;
import br.com.banco.service.ContaCorrenteService;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@Path("/v1/depositar")
public class DepositarController {
    @Inject
    ContaCorrenteService correnteService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response depositar(JsonObject json) {
        try {
            Integer numConta = json.getInt("numConta");
            double valor = json.getJsonNumber("valor").doubleValue();

            correnteService.depositar(numConta, valor);

            String mensagem = String.format("Depósito de R$%.2f realizado na conta de número: %d", valor, numConta);

            return Response.status(Response.Status.CREATED)
                    .entity(mensagem)
                    .build();
        } catch (ContaInvalidaException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}

