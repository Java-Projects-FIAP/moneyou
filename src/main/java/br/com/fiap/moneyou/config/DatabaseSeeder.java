package br.com.fiap.moneyou.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.moneyou.Enum.TipoCofrinho;
import br.com.fiap.moneyou.model.Cofrinho;
import br.com.fiap.moneyou.model.Usuario;
import br.com.fiap.moneyou.repository.CofrinhoRepository;
import br.com.fiap.moneyou.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private CofrinhoRepository cofrinhoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init() {
        if (usuarioRepository.count() == 0 && cofrinhoRepository.count() == 0) {

            // Criar usuários
            Usuario user1 = Usuario.builder()
                    .name("Rafael Souza ")
                    .email("rafaelBezerra@email.com")
                    .password("senha123")
                    .balance(5000.0)
                    .build();

            Usuario user2 = Usuario.builder()
                    .name("Guilherme Alves ")
                    .email("guilhermeAlves@email.com")
                    .password("senha456")
                    .balance(8000.0)
                    .build();

            usuarioRepository.saveAll(List.of(user1, user2));

            // Criar cofrinhos vinculados aos usuários
            List<Cofrinho> cofrinhos = List.of(
                    Cofrinho.builder()
                            .idOwner(user1.getId())
                            .date(LocalDate.now().minusDays(1))
                            .balance(1000L)
                            .type(TipoCofrinho.RESGATE_365_DIAS)
                            .name("Viagem ao Japão")
                            .build(),

                    Cofrinho.builder()
                            .idOwner(user2.getId())
                            .date(LocalDate.now().minusDays(5))
                            .balance(3000L)
                            .type(TipoCofrinho.RESGATE_IMEDIATO)
                            .name("Reserva de emergência")
                            .build(),

                    Cofrinho.builder()
                            .idOwner(user1.getId())
                            .date(LocalDate.now().minusDays(10))
                            .balance(500L)
                            .type(TipoCofrinho.RESGATE_365_DIAS)
                            .name("Presente de aniversário")
                            .build());

            cofrinhoRepository.saveAll(cofrinhos);
        }
    }
}
