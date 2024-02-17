package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.PaymentDTO;
import br.com.inventory.mechanicalparts.dtos.ServicePerformedDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/service-performed")
public interface IServicePerformedController {

    @PostMapping
    ServicePerformedDTO insert(@RequestBody ServicePerformedDTO servicePerformedDTO);

    @PutMapping(value = "/{idServicePerformed}")
    void update(@PathVariable Long idServicePerformed, @RequestBody ServicePerformedDTO servicePerformedDTO);

    @GetMapping
    List<ServicePerformedDTO> getAll();

    @GetMapping(value = "/{idServicePerformed}")
    ServicePerformedDTO getById(@PathVariable("idServicePerformed") Long idServicePerformed);

    @PostMapping(value = "/pay-service/{idServicePerformed}")
    void insertPayment(@PathVariable Long idServicePerformed, @RequestBody PaymentDTO paymentDTO);
}
