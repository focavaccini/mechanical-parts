package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.State;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.StateRepository;
import br.com.inventory.mechanicalparts.services.StateService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    @Override
    public List<State> getAll() {
        return stateRepository.findAllByOrderByNameAsc();
    }

    @Override
    public State getById(Long idState) {
        Optional<State> state = stateRepository.findById(idState);

        return state.orElseThrow(() -> new ObjectNotFound("Object not found! Id " + idState + ", Type: " + State.class.getName()));
    }

    @Override
    public State insert(State state) {
        return stateRepository.save(state);
    }

    @Override
    public void update(Long idState, State state) {
        State stateManaged = stateRepository.findById(idState).orElseThrow();
        stateManaged.setName(Util.nvl(state.getName(), stateManaged.getName()));
        stateManaged.setSigla(Util.nvl(state.getSigla(), stateManaged.getSigla()));

        stateRepository.save(stateManaged);
    }

    @Override
    public State findByNameIgnoreCase(String name) {
        return stateRepository.findByNameIgnoreCase(name);
    }

    @Override
    public State findByName(String name) {
        if(Util.isEmpty(name)) {
            throw new BadRequestException("Nenhum valor informado");
        }

        State state = stateRepository.findByName(name);

        if(Util.isEmpty(state)) {
            throw new ObjectNotFound("Nenhum resultado foi encontrado");
        }
        return state;
    }

    @Override
    public JpaRepository<State, Long> getRepository() {
        return this.stateRepository;
    }
}
