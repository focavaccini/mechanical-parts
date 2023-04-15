package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IServicePerformedController;
import br.com.inventory.mechanicalparts.dtos.ServicePerformedDTO;
import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import br.com.inventory.mechanicalparts.services.ServicePerformedService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ServicePerformedController extends AbstractController<ServicePerformedService> implements IServicePerformedController {

    private ServicePerformedService servicePerformedService;

    public ServicePerformedDTO insert(@RequestBody ServicePerformedDTO servicePerformedDTO){
        ServicePerformed servicePerformed = convert(servicePerformedDTO, ServicePerformed.class);
        servicePerformedService.insert(servicePerformed);
        return convert(servicePerformed, ServicePerformedDTO.class);
    }

    public void update(@PathVariable Long idServicePerformed, @RequestBody ServicePerformedDTO servicePerformedDTO){
        ServicePerformed servicePerformed = convert(servicePerformedDTO, ServicePerformed.class);
        servicePerformedService.update(idServicePerformed, servicePerformed);
    }
}
