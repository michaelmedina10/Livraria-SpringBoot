package br.com.medina.livraria_spring.core.dtos;

import java.math.BigDecimal;

public record LivroDTO(
                Integer id,
                String titulo,
                String genero,
                String autor,
                BigDecimal preco,
                Integer quantidade,
                FornecedorDTO fornecedor) {
}
