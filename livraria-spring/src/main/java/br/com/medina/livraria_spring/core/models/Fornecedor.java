package br.com.medina.livraria_spring.core.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cnpj", length = 50, nullable = false, unique = true)
    private String cnpj;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "endereco", length = 100, nullable = false)
    private String endereco;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<Livro> livros;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<TelefoneFornecedor> telefones;

    public Fornecedor() {
    }

    public Fornecedor(String cnpj, String nome, String endereco) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<TelefoneFornecedor> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneFornecedor> telefones) {
        this.telefones = telefones;
    }

}
