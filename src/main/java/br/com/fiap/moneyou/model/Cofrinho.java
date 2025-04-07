package br.com.fiap.moneyou.model;

import br.com.fiap.moneyou.Enum.TipoCofrinho;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cofrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idOwner;
    private Long balance;
    //private double value;
    private TipoCofrinho type;
    private String name;

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