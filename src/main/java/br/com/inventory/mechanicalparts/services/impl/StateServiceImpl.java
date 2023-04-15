package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.dtos.StateDTO;
import br.com.inventory.mechanicalparts.entities.Address;
import br.com.inventory.mechanicalparts.entities.State;
import br.com.inventory.mechanicalparts.repositories.AddressRepository;
import br.com.inventory.mechanicalparts.repositories.StateRepository;
import br.com.inventory.mechanicalparts.services.AddressService;
import br.com.inventory.mechanicalparts.services.StateService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    @Override
    public List<State> getAll() {
        return stateRepository.findAllByOrderByNameAsc();
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

//    @Override
//    public StateDTO converterToDTO(State state){
//        StateDTO stateDTO = new StateDTO();
//        stateDTO.setId(state.getId());
//        stateDTO.setName(state.getName());
//
//        return stateDTO;
//    }
//
//    @Override
//    public State converterToEntity(StateDTO stateDTO){
//        State state = new State();
//        state.setId(stateDTO.getId());
//        state.setName(stateDTO.getName());
//
//        return state;
//    }

    @Override
    public JpaRepository<State, Long> getRepository() {
        return this.stateRepository;
    }
}
