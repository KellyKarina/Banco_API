package br.com.banco.globalExceptionalHandler;

public class ContaInvalidaException extends Exception {
    public ContaInvalidaException(String mensagem) {
        super(mensagem);
    }
}