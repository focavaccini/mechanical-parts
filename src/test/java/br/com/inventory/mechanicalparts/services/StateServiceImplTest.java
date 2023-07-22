package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.ApplicationConfigTests;
import br.com.inventory.mechanicalparts.entities.State;
import br.com.inventory.mechanicalparts.repositories.StateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@DisplayName("StateServiceImplTest")
public class StateServiceImplTest extends ApplicationConfigTests {

    @MockBean
    private StateRepository stateRepository;

    @MockBean
    private State state;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private StateService stateService;

    @Test
    @DisplayName("deve inserir um estado")
    public void insert(){
        state = new State();
        Optional<State> state2 = Optional.of(criarState());
        state.setName("Teste");
        state.setSigla("Teste Sigla");

        Mockito.when(stateRepository.save(ArgumentMatchers.eq(state))).thenReturn(state2.get());

        stateService.insert(state);
        Mockito.verify(stateRepository, Mockito.times(1)).save(ArgumentMatchers.any(State.class));
    }

    private State criarState(){
        State state2 = Mockito.mock(State.class);
        return state2;
    }
}
