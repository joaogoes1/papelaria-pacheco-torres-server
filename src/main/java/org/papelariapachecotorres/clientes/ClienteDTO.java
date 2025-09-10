package org.papelariapachecotorres.clientes;

import java.time.Instant;

public class ClienteDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private Instant createdAt;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer id, String nome, String cpf, String endereco, String telefone, String email, Instant createdAt) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.createdAt = createdAt;
    }

    Cliente toDomain() {
        return new Cliente(
                id,
                nome,
                cpf,
                endereco,
                telefone,
                email,
                createdAt
        );
    }

    static ClienteDTO fromDomain(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEndereco(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCreatedAt()
        );
    }
} 