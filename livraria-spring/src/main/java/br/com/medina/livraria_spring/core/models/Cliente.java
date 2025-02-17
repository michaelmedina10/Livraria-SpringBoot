package br.com.medina.livraria_spring.core.models;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.medina.livraria_spring.core.dtos.ClienteDTO;
import br.com.medina.livraria_spring.core.dtos.TelefoneClienteDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "sobrenome", length = 50, nullable = false)
    private String sobrenome;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<TelefoneCliente> telefones;

    public Cliente() {
    }

    public Cliente(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public List<TelefoneCliente> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneCliente> telefones) {
        this.telefones = telefones;
    }

    public static ClienteDTO toClienteDTO(Cliente cliente) {
        List<TelefoneClienteDTO> telefoneDTOs = cliente.getTelefones() == null ? Collections.emptyList()
                : cliente.getTelefones().stream()
                        .map(telefone -> new TelefoneClienteDTO(telefone.getNumero()))
                        .collect(Collectors.toList());

        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getSobrenome(),
                telefoneDTOs);
    }

}
