package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.Payment;
import br.com.inventory.mechanicalparts.entities.ServicePerformed;

import java.util.List;

public interface ServicePerformedService extends IAbstractService<ServicePerformed, Long> {

    ServicePerformed insert(ServicePerformed servicePerformed);

    void update(Long idServicePerformed, ServicePerformed servicePerformed);

    ServicePerformed getById(Long idServicePerformed);

    List<ServicePerformed> getAll();

    void insertPayment(Long idServicePerformed, Payment payment);

}
