package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.ServicePerformedDTO;
import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/service-performed")
public interface IServicePerformedController {

    @PostMapping
    ServicePerformedDTO insert(@RequestBody ServicePerformedDTO servicePerformedDTO);

    @PutMapping(value = "/{id}")
    void update(@PathVariable Long idServicePerformed, @RequestBody ServicePerformedDTO servicePerformedDTO);
}
