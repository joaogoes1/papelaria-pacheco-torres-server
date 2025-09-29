package org.papelariapachecotorres.auth;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UsuarioRepository repository) {
        this.repository = repository;
        this.encoder = new BCryptPasswordEncoder(16);
    }

    public String verifyUser(String nome, String senha) {
        Optional<Usuario> usuario = repository.findByNome(nome);
        if (usuario.isPresent() && encoder.matches(senha, usuario.get().getSenha())) {
            return JwtUtil.generateToken(usuario.get().getNome());
        }
        return null;
    }

    public void saveUser(String username, String password) {
        final Usuario user = new Usuario(username, encoder.encode(password));
        repository.save(user);
    }
}
