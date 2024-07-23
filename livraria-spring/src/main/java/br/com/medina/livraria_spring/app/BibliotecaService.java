package br.com.medina.livraria_spring.app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.medina.livraria_spring.core.dtos.ClienteDTO;
import br.com.medina.livraria_spring.core.dtos.FornecedorDTO;
import br.com.medina.livraria_spring.core.dtos.LivroDTO;
import br.com.medina.livraria_spring.core.dtos.TelefoneClienteDTO;
import br.com.medina.livraria_spring.core.dtos.TelefoneFornecedorDTO;
import br.com.medina.livraria_spring.core.models.Cliente;
import br.com.medina.livraria_spring.core.models.Fornecedor;
import br.com.medina.livraria_spring.core.models.Livro;
import br.com.medina.livraria_spring.core.models.TelefoneCliente;
import br.com.medina.livraria_spring.core.models.TelefoneFornecedor;
import br.com.medina.livraria_spring.core.repositories.ClienteRepository;
import br.com.medina.livraria_spring.core.repositories.FornecedorRepository;
import br.com.medina.livraria_spring.core.repositories.LivroRepository;
import br.com.medina.livraria_spring.core.repositories.TelefoneClienteRepository;
import br.com.medina.livraria_spring.core.repositories.TelefoneFornecedorRepository;

@Service
public class BibliotecaService {

        @Autowired
        private FornecedorRepository fornecedorRepository;

        @Autowired
        private LivroRepository livroRepository;

        @Autowired
        private TelefoneFornecedorRepository telefoneFornecedorRepository;

        @Autowired
        private TelefoneClienteRepository telefoneClienteRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        public FornecedorDTO criarFornecedor(FornecedorDTO fornecedorDTO) {
                Fornecedor fornecedorExistente = fornecedorRepository.findByNome(fornecedorDTO.nome());
                if (fornecedorExistente != null) {
                        return Fornecedor.toFornecedorDTO(fornecedorExistente);
                }

                Fornecedor fornecedor = new Fornecedor(
                                fornecedorDTO.cnpj(),
                                fornecedorDTO.nome(),
                                fornecedorDTO.endereco());

                List<Livro> livros = fornecedorDTO.livros().stream()
                                .map(livroDTO -> new Livro(
                                                livroDTO.titulo(),
                                                livroDTO.genero(),
                                                livroDTO.autor(),
                                                livroDTO.preco(),
                                                livroDTO.quantidade(),
                                                fornecedor))
                                .collect(Collectors.toList());

                List<TelefoneFornecedor> telefones = fornecedorDTO.telefones().stream()
                                .map(telefoneDTO -> new TelefoneFornecedor(
                                                telefoneDTO.numero(),
                                                fornecedor))
                                .collect(Collectors.toList());

                fornecedor.setLivros(livros);
                fornecedor.setTelefones(telefones);

                Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
                return Fornecedor.toFornecedorDTO(fornecedorSalvo);
        }

        public LivroDTO criarLivro(LivroDTO livroDTO) {
                // Cria ou obtém o fornecedor
                Fornecedor fornecedor = new Fornecedor(
                                livroDTO.fornecedor().cnpj(),
                                livroDTO.fornecedor().nome(),
                                livroDTO.fornecedor().endereco());

                Fornecedor fornecedorExistente = fornecedorRepository.findByNome(fornecedor.getNome());
                if (fornecedorExistente != null) {
                        fornecedor = fornecedorExistente;
                } else {
                        fornecedor = fornecedorRepository.save(fornecedor);
                }

                Livro livro = new Livro(
                                livroDTO.titulo(),
                                livroDTO.genero(),
                                livroDTO.autor(),
                                livroDTO.preco(),
                                livroDTO.quantidade(),
                                fornecedor);

                Livro livroSalvo = livroRepository.save(livro);

                return new LivroDTO(
                                livroSalvo.getId(),
                                livroSalvo.getTitulo(),
                                livroSalvo.getGenero(),
                                livroSalvo.getAutor(),
                                livroSalvo.getPreco(),
                                livroSalvo.getQuantidade(),
                                new FornecedorDTO(
                                                fornecedor.getId(),
                                                fornecedor.getCnpj(),
                                                fornecedor.getNome(),
                                                fornecedor.getEndereco(),
                                                null, // Livros não são retornados aqui para evitar loops
                                                null // Telefones não são retornados aqui para evitar loops
                                ));
        }

        public TelefoneFornecedorDTO criarTelefoneFornecedor(TelefoneFornecedorDTO telefoneFornecedorDTO) {
                Fornecedor fornecedor = fornecedorRepository.findByNome(telefoneFornecedorDTO.fornecedorNome());

                if (fornecedor == null) {
                        throw new RuntimeException("Fornecedor não encontrado");
                }

                TelefoneFornecedor telefoneFornecedor = new TelefoneFornecedor(
                                telefoneFornecedorDTO.numero(),
                                fornecedor);

                TelefoneFornecedor telefoneSalvo = telefoneFornecedorRepository.save(telefoneFornecedor);

                return new TelefoneFornecedorDTO(
                                telefoneSalvo.getNumero(),
                                telefoneSalvo.getFornecedor().getNome());
        }

        public List<LivroDTO> obterLivrosPorFornecedor(String fornecedorNome) {
                Fornecedor fornecedor = fornecedorRepository.findByNome(fornecedorNome);
                if (fornecedor != null) {
                        return fornecedor.getLivros().stream()
                                        .map(Livro::toLivroDTO)
                                        .collect(Collectors.toList());
                }
                return List.of();
        }

        public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
                // Cria o cliente com os dados fornecidos
                Cliente cliente = new Cliente(clienteDTO.nome(), clienteDTO.sobrenome());

                // Adiciona os telefones ao cliente, se houver
                if (clienteDTO.telefones() != null) {
                        List<TelefoneCliente> telefones = clienteDTO.telefones().stream()
                                        .map(dto -> new TelefoneCliente(dto.numero(), cliente))
                                        .collect(Collectors.toList());
                        cliente.setTelefones(telefones);
                }

                // Salva o cliente no repositório
                Cliente clienteSalvo = clienteRepository.save(cliente);

                // Converte o cliente salvo para DTO
                return Cliente.toClienteDTO(clienteSalvo);
        }

        public TelefoneClienteDTO criarTelefoneCliente(TelefoneClienteDTO telefoneClienteDTO, Integer clienteId) {
                Cliente cliente = clienteRepository.findById(clienteId)
                                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

                TelefoneCliente telefoneCliente = new TelefoneCliente(
                                telefoneClienteDTO.numero(),
                                cliente);

                telefoneClienteRepository.save(telefoneCliente);

                return telefoneClienteDTO;
        }

}
