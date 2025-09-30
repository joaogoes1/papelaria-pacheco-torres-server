package org.papelariapachecotorres.config;

import org.papelariapachecotorres.auth.AuthService;
import org.papelariapachecotorres.auth.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inicializa dados essenciais na aplicação durante o startup.
 * Garante que o usuário admin sempre existe no sistema.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final UsuarioRepository usuarioRepository;
    private final AuthService authService;

    public DataInitializer(UsuarioRepository usuarioRepository, AuthService authService) {
        this.usuarioRepository = usuarioRepository;
        this.authService = authService;
    }

    @Override
    public void run(String... args) {
        initializeAdminUser();
    }

    /**
     * Cria o usuário admin se ele não existir no banco de dados.
     * Credenciais: username=pachecho_torres_adm@papelariapachecotorres.com.br, password=pacheco123
     */
    private void initializeAdminUser() {
        final String adminUsername = "pachecho_torres_adm@papelariapachecotorres.com.br";
        final String adminPassword = "pacheco123";

        if (usuarioRepository.findByNome(adminUsername).isEmpty()) {
            logger.info("Usuário admin não encontrado. Criando usuário admin padrão...");
            authService.saveUser(adminUsername, adminPassword);
            logger.info("✅ Usuário admin criado com sucesso! (username: {}, password: {})", adminUsername, adminPassword);
        } else {
            logger.info("✅ Usuário admin já existe no banco de dados.");
        }
    }
}