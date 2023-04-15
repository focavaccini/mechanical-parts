package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.StateDTO;
import br.com.inventory.mechanicalparts.entities.State;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/state")
public interface IStateController {

    @PostMapping
    StateDTO insert(@RequestBody StateDTO stateDTO);

    @PutMapping(value = "/{id}")
    void update(@PathVariable Long idState, @RequestBody StateDTO stateDTO);

    @GetMapping
    List<StateDTO> getAllStates();
}
