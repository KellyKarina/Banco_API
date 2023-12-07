package br.com.banco.service;

import br.com.banco.globalExceptionalHandler.ContaInvalidaException;
import br.com.banco.globalExceptionalHandler.SaldoInsuficienteException;

public interface IContaCorrenteService {
    void transferir(String contaOrigem, String contaDestino, double valor)
            throws ContaInvalidaException, SaldoInsuficienteException;
}
