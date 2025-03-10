package br.com.fiap.moneyou.model;

import br.com.fiap.moneyou.Enum.TipoCofrinho;

public class Cofrinho {
    private Long id;
    private Long idOwner;
    private Long balance;
    private TipoCofrinho tipo;

    public Cofrinho(Long id, Long idOwner, Long balance, TipoCofrinho tipo) {
        this.id = id;
        this.idOwner = idOwner;
        this.balance = balance;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public Long getBalance() {
        return balance;
    }

    public TipoCofrinho getTipo() {
        return tipo;
    }

    public void depositar(Long valor) {
        if (valor > 0) {
            this.balance += valor;
        } else {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo.");
        }
    }

    public void sacar(Long valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        if (valor > this.balance) {
            throw new IllegalArgumentException("Saldo insuficiente para saque.");
        }
        this.balance -= valor;
    }

}