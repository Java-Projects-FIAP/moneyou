package br.com.fiap.moneyou.model;

import br.com.fiap.moneyou.Enum.TipoCofrinho;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cofrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idOwner;


    @PastOrPresent(message = "deve ser no passado")
    private LocalDate date;

    @Positive(message = "deve ser positivo")
    private Long balance;
    
    @NotNull(message = "campo obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoCofrinho type;

    
    @NotBlank(message = "campo obrigatório")
    @Size(min = 5, max = 255, message = "O Nome ter pelo menos 5 caracteres")
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