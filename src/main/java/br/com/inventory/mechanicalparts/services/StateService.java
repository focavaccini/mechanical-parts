package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.dtos.StateDTO;
import br.com.inventory.mechanicalparts.entities.State;

import java.util.List;

public interface StateService extends IAbstractService<State, Long> {

    List<State> getAll();

    State insert(State state);

    void update(Long idState, State state);

//    StateDTO converterToDTO(State state);
//
//    State converterToEntity(StateDTO stateDTO);
}
