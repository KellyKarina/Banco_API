package br.com.banco.controller;


import br.com.banco.service.ContaCorrenteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import br.com.banco.entity.ContaCorrente;

import java.util.ArrayList;
import java.util.List;


@Path("/v1/conta")
public class ContaCorrenteController {
    @Inject
    ContaCorrenteService correnteService;
    @GET
    public List<ContaCorrente> retriveConta(){

        List<ContaCorrente> contas = new ArrayList<>();

        try {
            contas = correnteService.findAllContas();
        }catch (Exception e){
            e.printStackTrace();
        }
        return contas;
    }

}