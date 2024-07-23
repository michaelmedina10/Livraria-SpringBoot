package br.com.medina.livraria_spring.adapters;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.medina.livraria_spring.app.BibliotecaService;
import br.com.medina.livraria_spring.core.dtos.ClienteDTO;
import br.com.medina.livraria_spring.core.dtos.FornecedorDTO;
import br.com.medina.livraria_spring.core.dtos.LivroDTO;
import br.com.medina.livraria_spring.core.dtos.TelefoneFornecedorDTO;
import br.com.medina.livraria_spring.core.models.Cliente;
import br.com.medina.livraria_spring.core.models.Fornecedor;
import br.com.medina.livraria_spring.core.models.Livro;
import br.com.medina.livraria_spring.core.models.TelefoneFornecedor;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    @PostMapping("/cliente")
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(bibliotecaService.criarCliente(clienteDTO), HttpStatus.CREATED);
    }

    @PostMapping("/fornecedor")
    public ResponseEntity<FornecedorDTO> criarFornecedor(@RequestBody FornecedorDTO fornecedorDTO) {
        return new ResponseEntity<>(bibliotecaService.criarFornecedor(fornecedorDTO), HttpStatus.CREATED);
    }

    @PostMapping("/livro")
    public ResponseEntity<LivroDTO> criarLivro(@RequestBody LivroDTO livroDTO) {
        return new ResponseEntity<>(bibliotecaService.criarLivro(livroDTO), HttpStatus.CREATED);
    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneFornecedorDTO> criarTelefoneFornecedor(
            @RequestBody TelefoneFornecedorDTO telefoneFornecedorDTO) {
        TelefoneFornecedorDTO createdTelefoneFornecedor = bibliotecaService
                .criarTelefoneFornecedor(telefoneFornecedorDTO);
        return ResponseEntity.ok(createdTelefoneFornecedor);
    }

    @GetMapping("/fornecedor/{nome}/livros")
    public ResponseEntity<List<LivroDTO>> obterLivrosPorFornecedor(@PathVariable String nome) {
        List<LivroDTO> livros = bibliotecaService.obterLivrosPorFornecedor(nome);
        if (livros != null && !livros.isEmpty()) {
            return new ResponseEntity<>(livros, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
