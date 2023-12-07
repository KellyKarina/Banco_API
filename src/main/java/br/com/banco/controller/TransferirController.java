package br.com.banco.controller;

import br.com.banco.entity.ContaCorrente;
import br.com.banco.globalExceptionalHandler.ContaInvalidaException;
import br.com.banco.globalExceptionalHandler.SaldoInsuficienteException;
import br.com.banco.service.IContaCorrenteService;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/transferir")
public class TransferirController {

    private IContaCorrenteService contaService;

    public TransferirController(IContaCorrenteService contaService) {
        this.contaService = contaService;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transferirContas(JsonObject json) {
        try {
            String contaOrigem = json.getJsonNumber("contaOrigem").toString();
            String contaDestino = json.getJsonNumber("contaDestino").toString();
            double valor = json.getJsonNumber("valor").bigDecimalValue().doubleValue();

            contaService.transferir(contaOrigem, contaDestino, valor);

            String mensagem = String.format("Transferência de R$%.2f realizada da conta %s para a conta %s", valor, contaOrigem, contaDestino);
            return Response.status(Response.Status.OK)
                    .entity(mensagem)
                    .build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro na conversão de número de conta.")
                    .build();
        } catch (ContaInvalidaException | SaldoInsuficienteException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

}

