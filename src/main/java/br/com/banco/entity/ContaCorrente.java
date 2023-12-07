package br.com.banco.entity;

import br.com.banco.globalExceptionalHandler.ContaInvalidaException;
import br.com.banco.globalExceptionalHandler.SaldoInsuficienteException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroConta;

    private Double saldo;

    private String titular;

    private static final double TAXA_DE_TRANSFERENCIA = 0.001;

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque inválido.");
        }
        if (saldo >= valor) {
            saldo -= valor;
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar o saque.");
        }
    }
    public void depositar(double valor) {
        this.saldo += valor;
        System.out.printf("Valor de %.2f depositado na conta de número %s \n", valor, this.numeroConta);
    }

    public ContaCorrente() {
        this.saldo = 0.0;
    }

    public double getValorDoSaque() {
        return 0;
    }

    public void transferir(ContaCorrente destino, double valor) throws SaldoInsuficienteException {
        if (this.saldo < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar a transferência.");
        }
        this.saldo -= valor + (valor * TAXA_DE_TRANSFERENCIA);
        destino.saldo += valor;
    }
}
