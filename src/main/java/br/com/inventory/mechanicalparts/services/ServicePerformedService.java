package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.ServicePerformed;

public interface ServicePerformedService extends IAbstractService<ServicePerformed, Long> {

    ServicePerformed insert(ServicePerformed servicePerformed);

    void update(Long idServicePerformed, ServicePerformed servicePerformed);
}
