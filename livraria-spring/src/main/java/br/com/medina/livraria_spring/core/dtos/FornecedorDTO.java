package br.com.medina.livraria_spring.core.dtos;

import java.util.List;

public record FornecedorDTO(
        Integer id,
        String cnpj,
        String nome,
        String endereco,
        List<LivroDTO> livros,
        List<TelefoneFornecedorDTO> telefones) {
}
