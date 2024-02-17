package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.Payment;
import br.com.inventory.mechanicalparts.entities.ServicePerformed;

import java.util.List;

public interface PaymentService extends IAbstractService<Payment, Long> {

    Payment insert(ServicePerformed servicePerformed, Payment payment);

    void update(Long idPayment, Payment payment);

    List<Payment> getAll();

    Payment getById(Long idPayment);
}
