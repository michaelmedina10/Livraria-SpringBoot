package br.com.medina.livraria_spring.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.medina.livraria_spring.core.models.TelefoneCliente;

public interface TelefoneClienteRepository extends JpaRepository<TelefoneCliente, Integer> {

}
