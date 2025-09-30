package org.papelariapachecotorres.clientes;

import java.time.Instant;
import java.util.UUID;

public class ClienteDTO {
    private UUID id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private Instant createdAt;

    public ClienteDTO() {
    }

    public ClienteDTO(UUID id, String nome, String cpf, String endereco, String telefone, String email, Instant createdAt) {
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

    // Getters e Setters para serialização/deserialização do Jackson
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
} 