package br.com.banco.service;
import br.com.banco.globalExceptionalHandler.ContaInvalidaException;
import br.com.banco.globalExceptionalHandler.SaldoInsuficienteException;
import jakarta.enterprise.context.ApplicationScoped;
import br.com.banco.entity.Cliente;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public List<Cliente> findAllClientes() {
        return clientes;
    }

    public Cliente addCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente adicionado com sucesso: " + cliente);
        return cliente;
    }
}
