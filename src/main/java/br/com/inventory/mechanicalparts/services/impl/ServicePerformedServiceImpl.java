package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.DateUtil;
import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.*;
import br.com.inventory.mechanicalparts.entities.enums.EnumStatusServicePerformed;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.ServicePerformedRepository;
import br.com.inventory.mechanicalparts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicePerformedServiceImpl implements ServicePerformedService {

    @Autowired
    private ServicePerformedRepository servicePerformedRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProfessionalService professionalService;

    @Autowired
    private CarService carService;

    @Autowired
    private PaymentService paymentService;

    @Override
    @Transactional
    public ServicePerformed insert(ServicePerformed servicePerformed) {
        onPrepareInsertOrUpdate(servicePerformed);
        servicePerformed.setStatus(EnumStatusServicePerformed.EM_DIA);
        servicePerformed.setRegistrationDate(LocalDateTime.now());
        servicePerformed.setUpdateDate(LocalDateTime.now());

        prepareProductsService(servicePerformed);

        servicePerformed.setServiceDays(servicePerformed.getDeliveryDate().compareTo(LocalDate.now()));
        servicePerformed.setDaysForDelivery(servicePerformed.getServiceDays());
        servicePerformed.setProfessional(professionalService.getById(servicePerformed.getProfessional().getId()));
        servicePerformed.setCar(carService.getById(servicePerformed.getCar().getId()));
        servicePerformed = servicePerformedRepository.save(servicePerformed);
        return servicePerformed;
    }

    @Override
    public void update(Long idServicePerformed, ServicePerformed servicePerformed) {
        ServicePerformed servicePerformedManaged = getById(idServicePerformed);

        servicePerformedManaged.setUpdateDate(LocalDateTime.now());
        servicePerformedManaged.setDescription(Util.nvl(servicePerformed.getDescription(), servicePerformedManaged.getDescription()));
        servicePerformedManaged.setObservation(Util.nvl(servicePerformed.getObservation(), servicePerformedManaged.getObservation()));
        servicePerformedManaged.setLaborCost(Util.nvl(servicePerformed.getLaborCost(), servicePerformedManaged.getLaborCost()));
        servicePerformedManaged.setStatus(Util.nvl(servicePerformed.getStatus(), servicePerformedManaged.getStatus()));
        servicePerformedManaged.setProblemReported(Util.nvl(servicePerformed.getProblemReported(), servicePerformedManaged.getProblemReported()));
        servicePerformedManaged.setDeliveryDate(Util.nvl(servicePerformed.getDeliveryDate(), servicePerformedManaged.getDeliveryDate()));
        servicePerformedManaged.setServiceDays(Util.nvl(servicePerformed.getDeliveryDate().compareTo(LocalDate.now()), servicePerformedManaged.getDeliveryDate().compareTo(LocalDate.now())));

        prepareProductsService(servicePerformed);

        servicePerformedManaged.setTotalValue(Util.nvl(servicePerformed.getTotalValue(), servicePerformedManaged.getTotalValue()));
        servicePerformedManaged.setUsedProducts(Util.nvl(servicePerformed.getUsedProducts(), servicePerformedManaged.getUsedProducts()));

        servicePerformedRepository.save(servicePerformedManaged);
    }

    private void prepareProductsService(ServicePerformed servicePerformed) {
        BigDecimal value = BigDecimal.ZERO;
        List<Product> listProductServices = new ArrayList<>();

        for (Product products : servicePerformed.getUsedProducts()) {
            Product product = productService.getById(products.getId());
            product = productService.updateQuantity(product, products.getQuantityUsed());
            value = value.add(calculoTotalDoServico(product, products.getQuantityUsed()));
            servicePerformed.setTotalValue(servicePerformed.getLaborCost().add(value));
            listProductServices.add(product);
        }

        servicePerformed.setUsedProducts(listProductServices);
    }

    @Override
    public ServicePerformed getById(Long idServicePerformed) {
        Optional<ServicePerformed> servicePerformed = servicePerformedRepository.findById(idServicePerformed);
        ServicePerformed servicePerformedEntity = servicePerformed.orElseThrow(() -> new ObjectNotFound("Object not found! Id " + idServicePerformed + ", Type: " + ServicePerformed.class.getName()));
        validateStatusServicePerformed(servicePerformedEntity);
        return servicePerformedEntity;
    }

    @Override
    public List<ServicePerformed> getAll() {
        User user = getUsuarioLogado();
        Professional professional = professionalService.findByUserId(user.getId());
        List<ServicePerformed> services = servicePerformedRepository.findAllByProfessionalId(professional.getId());
        for (ServicePerformed service : services) {
            validateStatusServicePerformed(service);
        }
        return services;
    }

    private void validateStatusServicePerformed(ServicePerformed service) {
        if (!service.getStatus().equals(EnumStatusServicePerformed.FINALIZADO) && !service.getStatus().equals(EnumStatusServicePerformed.ENTREGUE)) {
            int daysToDelivery = DateUtil.calculateDifference(ChronoUnit.DAYS, service.getDeliveryDate().atStartOfDay(), LocalDateTime.now());
            if (service.getDeliveryDate().isBefore(LocalDate.now())) {
                service.setStatus(EnumStatusServicePerformed.ATRASADO);
                service.setDaysForDelivery(daysToDelivery * -1);
            } else {
                service.setDaysForDelivery(daysToDelivery * -1);
            }
        } else {
            service.setDaysForDelivery(0);
        }
    }

    private BigDecimal calculoTotalDoServico(Product product, Integer quantityUsed) {
        return BigDecimal.valueOf(quantityUsed).multiply(product.getValue());
    }

    private void onPrepareInsertOrUpdate(ServicePerformed servicePerformed) {
        checkIfDaysArePositive(servicePerformed);
    }

    private void checkIfDaysArePositive(ServicePerformed servicePerformed) {

        if (servicePerformed != null && servicePerformed.getDeliveryDate() != null && servicePerformed.getDeliveryDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Data de entrega inválida, deve ser informada uma data a partir de: " + LocalDate.now());
        }
    }

    @Override
    public void insertPayment(Long idServicePerformed, Payment payment) {
        ServicePerformed servicePerformed = getById(idServicePerformed);
        servicePerformed.setStatus(EnumStatusServicePerformed.ENTREGUE);
        Payment paymentSaved = paymentService.insert(servicePerformed, payment);
        servicePerformed.setPayment(paymentSaved);
        update(servicePerformed.getId(), servicePerformed);
    }

    private User getUsuarioLogado() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public JpaRepository<ServicePerformed, Long> getRepository() {
        return this.servicePerformedRepository;
    }
}
