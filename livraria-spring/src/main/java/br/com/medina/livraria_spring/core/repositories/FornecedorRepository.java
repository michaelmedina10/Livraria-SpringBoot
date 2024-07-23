package br.com.medina.livraria_spring.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.medina.livraria_spring.core.models.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    public Fornecedor findByNome(String nome);
}
