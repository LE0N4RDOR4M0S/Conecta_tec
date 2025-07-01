package br.com.leonardoramos.conecta_tec.config;

import br.com.leonardoramos.conecta_tec.entity.Role;
import br.com.leonardoramos.conecta_tec.entity.Usuario;
import br.com.leonardoramos.conecta_tec.repository.RoleRepository;
import br.com.leonardoramos.conecta_tec.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    public DataInitializer(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.findByNome("ROLE_ADMIN").orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));
        roleRepository.findByNome("ROLE_USER").orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        if (usuarioRepository.findByEmail(adminEmail).isEmpty()) {
            Usuario adminUser = new Usuario();
            adminUser.setNome("Administrador");
            adminUser.setEmail(adminEmail);
            adminUser.setSenha(passwordEncoder.encode(adminPassword));
            adminUser.setRoles(Set.of(adminRole));

            usuarioRepository.save(adminUser);
            System.out.println(">>> Usuário administrador padrão criado com sucesso!");
        }
    }
}
