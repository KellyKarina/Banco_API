package br.com.banco.controller;

import br.com.banco.entity.ContaCorrente;
import br.com.banco.service.ClienteService;
import br.com.banco.service.ContaCorrenteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/v1/cliente")
public class ClienteController {
    @Inject
    ContaCorrenteService correnteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> retrieveClientes() {
        List<ContaCorrente> contas = correnteService.findAllContas();
        List<String> informacoesContas = new ArrayList<>();

        for (ContaCorrente conta : contas) {
            informacoesContas.add(String.format("Conta: %s, Saldo: R$%s", conta.getId(), conta.getSaldo()));
        }
        return informacoesContas;
    }
}
