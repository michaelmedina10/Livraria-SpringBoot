package br.com.medina.livraria_spring.core.dtos;

import java.util.List;

public record ClienteDTO(
        Integer id,
        String nome,
        String sobrenome,
        List<TelefoneClienteDTO> telefones) {

}
