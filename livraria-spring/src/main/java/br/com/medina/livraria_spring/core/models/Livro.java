package br.com.medina.livraria_spring.core.models;

import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "genero", length = 50, nullable = false)
    private String genero;

    @Column(name = "autor", length = 50, nullable = false)
    private String autor;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private Double preco;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    public Livro() {
    }

    public Livro(String titulo, String genero, String autor, Double preco, Integer quantidade,
            Fornecedor fornecedor) {
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.preco = preco;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
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

}
