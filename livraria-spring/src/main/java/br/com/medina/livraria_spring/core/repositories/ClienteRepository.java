package br.com.medina.livraria_spring.core.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import br.com.medina.livraria_spring.core.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    public Cliente findByNome(String nome);
}
