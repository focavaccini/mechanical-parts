package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.entities.Payment;
import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.PaymentRepository;
import br.com.inventory.mechanicalparts.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment insert(ServicePerformed servicePerformed, Payment payment) {

        payment.setPaymentDate(LocalDateTime.now());
        onPrepareInsertOrUpdate(payment.getTotalValuePaied(), servicePerformed.getTotalValue());
        return paymentRepository.save(payment);

    }

    @Override
    public void update(Long idPayment, Payment payment) {

    }

    @Override
    public List<Payment> getAll() {
        return getRepository().findAll();
    }

    @Override
    public Payment getById(Long idPayment) {
        Optional<Payment> payment = getRepository().findById(idPayment);

        return payment.orElseThrow(()-> new ObjectNotFound("Object not found! Id " + idPayment + ", Type: " + Payment.class.getName()));
    }

    private void onPrepareInsertOrUpdate(BigDecimal paiedValue, BigDecimal serviceValue) {
        checkIfValuesAreEquals(paiedValue, serviceValue);
    }

    private void checkIfValuesAreEquals(BigDecimal paiedValue, BigDecimal serviceValue) {

        if(paiedValue.compareTo(serviceValue) < 0) {
            throw new BadRequestException("O valor do pagamento informado é inferior ao valor do serviço: " + serviceValue);
        }

        if(paiedValue.compareTo(serviceValue) > 0) {
            throw new BadRequestException("O valor do pagamento informado é superior ao valor do serviço: " + serviceValue);
        }
    }

    @Override
    public JpaRepository<Payment, Long> getRepository() {
        return null;
    }
}
