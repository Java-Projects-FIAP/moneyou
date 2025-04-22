package br.com.fiap.moneyou.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obrigatório")
    @Size(min = 3, max = 255, message = "O nome ter pelo menos 3 caracteres")
    private String name;

    @NotBlank(message = "campo obrigatório")
    @Size(min = 10, max = 255, message = "O Email ter pelo menos 10 caracteres")
    private String email;

    @NotBlank(message = "campo obrigatório")
    @Size(min = 6, max = 255, message = "A senha deve ter pelo menos 6 caracteres")
    private String password;

    @Positive(message = "deve ser positivo")
    private Double balance = 0.0;
}
