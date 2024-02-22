package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IStateController;
import br.com.inventory.mechanicalparts.dtos.StateDTO;
import br.com.inventory.mechanicalparts.entities.State;
import br.com.inventory.mechanicalparts.services.StateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StateController extends AbstractController<StateService> implements IStateController {

    private StateService stateService;

    @Override
    public StateDTO insert(@RequestBody StateDTO stateDTO){
        State state = convert(stateDTO, State.class);
        stateService.insert(state);
        return convert(state, StateDTO.class);
    }

    @Override
    public void update(@PathVariable Long idState, @RequestBody StateDTO stateDTO){
        State state = convert(stateDTO, State.class);
        stateService.update(idState, state);
    }

    @Override
    public List<StateDTO> getAllStates() {
        return convert(stateService.getAll(), StateDTO.class);
    }

    @Override
    public StateDTO getById(Long idState) {
        return convert(stateService.getById(idState), StateDTO.class);
    }

    @Override
    public StateDTO findByName(String name) {
        return convert(stateService.findByName(name), StateDTO.class);
    }
}
