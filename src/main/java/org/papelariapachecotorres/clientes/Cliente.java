package org.papelariapachecotorres.clientes;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    
    @Column(name = "endereco", nullable = false)
    private String endereco;
    
    @Column(name = "telefone", nullable = false)
    private String telefone;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "created_at", nullable = false)
    private String createdAt;

    public Cliente() {}

    public Cliente(Integer id, String nome, String cpf, String endereco, String telefone, String email, String createdAt) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
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
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
} 