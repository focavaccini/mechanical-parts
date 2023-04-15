package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.StateDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/state")
public interface IStateController {

    @PostMapping
    StateDTO insert(@RequestBody StateDTO stateDTO);

    @PutMapping(value = "/{idState}")
    void update(@PathVariable Long idState, @RequestBody StateDTO stateDTO);

    @GetMapping
    List<StateDTO> getAllStates();
}
