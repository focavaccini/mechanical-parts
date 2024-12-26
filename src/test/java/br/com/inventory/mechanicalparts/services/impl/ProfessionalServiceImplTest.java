package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.entities.Professional;
import br.com.inventory.mechanicalparts.entities.Role;
import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.entities.enums.EnumRole;
import br.com.inventory.mechanicalparts.repositories.ProfessionalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ProfessionalServiceImplTest {

    @Mock
    private ProfessionalRepository professionalRepository;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private ProfessionalServiceImpl professionalService;

    @Test
    void create() {

        Long userId = 1L;
        Long roleId = 1L;
        Role role = new Role(roleId, "Admin", EnumRole.ADMIN, null);
        User user = new User(userId, "login", "password", true, role);

        Professional professional = new Professional(null, "Professional 1", "45999111111", true, "1111", "1234", null, user, LocalDateTime.now(), null);

        Mockito.when(professionalRepository.save(Mockito.any(Professional.class))).thenReturn(new Professional(1L, "Professional 1", "45999111111", true, "1111", "1234", null, null, LocalDateTime.now(), null));

        var result = professionalService.insert(professional);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();  // Verifica se o ID foi gerado
        Assertions.assertThat(result.getName()).isEqualTo("Professional 1");
    }

    @Test
    void getAll() {
        List<Professional> professionalList = new ArrayList<>();

        Professional professional1 = new Professional(1L, "Professional 1", "45999111111", true, "1111", null, null, null, null, null);
        Professional professional2 = new Professional(2L, "Professional 1", "45999111111", true, "1111", null, null, null, null, null);

        professionalList.add(professional1);
        professionalList.add(professional2);

        Mockito.when(professionalRepository.findAll()).thenReturn(professionalList);
        var result = professionalService.getAll();
        Assertions.assertThat(result).isNotEmpty();
    }

    @Test
    void getById() {

        Long id = 1L;
        Professional professional = new Professional(id, "Professional 1", "45999111111", true, "1111", null, null, null, null, null);

        Mockito.when(professionalRepository.findById(id)).thenReturn(Optional.of(professional));

        var result = professionalService.getById(id);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(id);
        Assertions.assertThat(result.getName()).isEqualTo("Professional 1");
    }

    @Test
    void findByName() {

        Long id = 1L;
        String name = "Professional 1";
        Professional professional = new Professional(id, name, "45999111111", true, "1111", null, null, null, null, null);

        Mockito.when(professionalRepository.findByName(name)).thenReturn(professional);

        var result = professionalService.findByName(name);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(id);
        Assertions.assertThat(result.getName()).isEqualTo("Professional 1");
    }

    @Test
    void findAllByName() {

        String name = "Professional 1";
        List<Professional> professionalList = new ArrayList<>();
        professionalList.add(new Professional(1L, name, "45999111111", true, "1111", null, null, null, null, null));
        professionalList.add(new Professional(2L, name, "45999111111", true, "1111", null, null, null, null, null));

        Mockito.when(professionalRepository.findAllByName(name)).thenReturn(professionalList);

        var result = professionalService.findAllByName(name);

        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result.get(0).getName()).isEqualTo(name);
    }

    @Test
    void findByUserId() {

        Long id = 1L;
        Long userId = 1L;

        User user = new User(userId, "login", "password", true, null);
        Professional professional = new Professional(id, "Professional 1", "45999111111", true, "1111", "1234", null, user, null, null);

        Mockito.when(professionalRepository.findByUserId(userId)).thenReturn(professional);

        var result = professionalService.findByUserId(userId);

        // Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(id);
        Assertions.assertThat(result.getName()).isEqualTo("Professional 1");
        Assertions.assertThat(result.getUser().getId()).isEqualTo(userId);
        Assertions.assertThat(result.getUser().getLogin()).isEqualTo("login");
    }
}