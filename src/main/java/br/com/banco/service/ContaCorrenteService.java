package br.com.banco.service;

import br.com.banco.globalExceptionalHandler.ContaInvalidaException;
import br.com.banco.globalExceptionalHandler.SaldoInsuficienteException;
import br.com.banco.entity.ContaCorrente;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ContaCorrenteService implements IContaCorrenteService {

    private List<ContaCorrente> contas = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong(1);
    private static final AtomicInteger contadorContas = new AtomicInteger(1);

    public List<ContaCorrente> findAllContas() {
        return contas;
    }

    private String gerarNumContaUnico() {
        return String.format("%05d", contadorContas.getAndIncrement());
    }

    public void addConta(ContaCorrente contaCorrente) {
        long idGerado = nextId.getAndIncrement();
        contaCorrente.setId(idGerado);

        Random rand = new Random();
        int numGerado = rand.nextInt(1000000);
        contaCorrente.setNumeroConta(numGerado);

        contas.add(contaCorrente);
    }

    public void depositar(Integer numConta, double valor) throws ContaInvalidaException {
        ContaCorrente conta = getContaPorNumero(numConta);
        if (conta == null) {
            throw new ContaInvalidaException("Conta inválida. Verifique o número da conta.");
        }
        conta.depositar(valor);
    }

    public ContaCorrente getContaPorNumero(Integer numConta) {
        return contas.stream()
                .filter(conta -> conta.getNumeroConta().equals(numConta))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteConta(Long id) {
        return contas.removeIf(conta -> conta.getId().equals(id));
    }

    public void updateConta(Long id, ContaCorrente contaAtt) {
        Optional<ContaCorrente> contaOp = contas.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        contaOp.ifPresent(contaCorrente -> contaCorrente.setSaldo(contaAtt.getSaldo()));
    }

    public void transferir(String contaOrigem, String contaDestino, double valor)
            throws ContaInvalidaException, SaldoInsuficienteException {
        ContaCorrente origem = getContaPorNumero(Integer.parseInt(contaOrigem));
        ContaCorrente destino = getContaPorNumero(Integer.parseInt(contaDestino));

        if (origem == null || destino == null) {
            throw new ContaInvalidaException("Conta de origem ou destino inválida. Verifique os números das contas.");
        }

        origem.transferir(destino, valor);
    }
}