package br.com.fiap.moneyou.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.moneyou.model.Cofrinho;

public interface CofrinhoRepository extends JpaRepository<Cofrinho, Long>,  
JpaSpecificationExecutor<Cofrinho>  {

    Page<Cofrinho> findByNameContainingIgnoringCase(String name, Pageable pageable); 
}
