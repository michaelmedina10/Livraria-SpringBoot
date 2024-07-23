package br.com.medina.livraria_spring.core.models;

import java.math.BigDecimal;

import br.com.medina.livraria_spring.core.dtos.FornecedorDTO;
import br.com.medina.livraria_spring.core.dtos.LivroDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "genero", length = 50, nullable = false)
    private String genero;

    @Column(name = "autor", length = 50, nullable = false)
    private String autor;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    public Livro() {
    }

    public Livro(String titulo, String genero, String autor, BigDecimal preco, Integer quantidade,
            Fornecedor fornecedor) {
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.preco = preco;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public static LivroDTO toLivroDTO(Livro livro) {
        Fornecedor fornecedor = livro.getFornecedor();
        FornecedorDTO fornecedorDTO = fornecedor != null
                ? new FornecedorDTO(
                        fornecedor.getId(),
                        fornecedor.getCnpj(),
                        fornecedor.getNome(),
                        fornecedor.getEndereco(),
                        null, // Não incluir livros e telefones para evitar loop infinito
                        null)
                : null;

        return new LivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getGenero(),
                livro.getAutor(),
                livro.getPreco(),
                livro.getQuantidade(),
                fornecedorDTO);
    }

}
