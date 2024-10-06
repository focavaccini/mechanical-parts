package br.com.inventory.mechanicalparts.configurations;

import br.com.inventory.mechanicalparts.entities.Role;
import br.com.inventory.mechanicalparts.entities.enums.EnumRole;
import br.com.inventory.mechanicalparts.repositories.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;


    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        EnumRole[] roleNames = new EnumRole[]{EnumRole.USER, EnumRole.ADMIN, EnumRole.SUPER_ADMIN};
        Map<EnumRole, String> roleDescriptionMap = Map.of(
                EnumRole.USER, "Default user role",
                EnumRole.ADMIN, "Administrator role",
                EnumRole.SUPER_ADMIN, "Super Administrator role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName.name());

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName.name());

                roleRepository.save(roleToCreate);
            });
        });
    }

}
