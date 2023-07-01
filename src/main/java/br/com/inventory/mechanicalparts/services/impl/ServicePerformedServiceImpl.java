package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import br.com.inventory.mechanicalparts.entities.enums.EnumStatusServicePerformed;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.ServicePerformedRepository;
import br.com.inventory.mechanicalparts.services.CarService;
import br.com.inventory.mechanicalparts.services.ProductService;
import br.com.inventory.mechanicalparts.services.ProfessionalService;
import br.com.inventory.mechanicalparts.services.ServicePerformedService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicePerformedServiceImpl implements ServicePerformedService {

    private ServicePerformedRepository servicePerformedRepository;

    private ProductService productService;

    private ProfessionalService professionalService;

    private CarService carService;

    @Override
    @Transactional
    public ServicePerformed insert(ServicePerformed servicePerformed) {
        BigDecimal value = BigDecimal.ZERO;

        servicePerformed.setStatus(EnumStatusServicePerformed.EM_DIA);
        List<Product> listServices = new ArrayList<>();
        for(Product products : servicePerformed.getUsedProducts()){
            Product product = productService.getById(products.getId());
            product = productService.updateQuantity(product, products.getQuantityUsed());
            value = value.add(calculoTotalDoServico(product, products.getQuantityUsed()));
            servicePerformed.setTotalValue((servicePerformed.getLaborCost().add(value)));
            listServices.add(product);
        }

        servicePerformed.setServiceDays(servicePerformed.getDeliveryDate().compareTo(LocalDate.now()));
        servicePerformed.setUsedProducts(listServices);
        servicePerformed.setProfessional(professionalService.getById(servicePerformed.getProfessional().getId()));
        servicePerformed.setCar(carService.getById(servicePerformed.getCar().getId()));
        servicePerformed = servicePerformedRepository.save(servicePerformed);
        return servicePerformed;
    }

    @Override
    public void update(Long idServicePerformed, ServicePerformed servicePerformed) {
        ServicePerformed servicePerformedManaged = getById(idServicePerformed);

        servicePerformedManaged.setDescription(Util.nvl(servicePerformed.getDescription(), servicePerformedManaged.getDescription()));
        servicePerformedManaged.setServiceDays(Util.nvl(servicePerformed.getServiceDays(), servicePerformedManaged.getServiceDays()));
        servicePerformedManaged.setObservation(Util.nvl(servicePerformed.getObservation(), servicePerformedManaged.getObservation()));
        servicePerformedManaged.setLaborCost(Util.nvl(servicePerformed.getLaborCost(), servicePerformedManaged.getLaborCost()));
        servicePerformedManaged.setTotalValue(Util.nvl(servicePerformed.getTotalValue(), servicePerformedManaged.getTotalValue()));
        servicePerformedManaged.setProblemReported(Util.nvl(servicePerformed.getProblemReported(), servicePerformedManaged.getProblemReported()));
        servicePerformedManaged.setDeliveryDate(Util.nvl(servicePerformed.getDeliveryDate(), servicePerformedManaged.getDeliveryDate()));
        //servicePerformedManaged.setValue(Util.nvl(servicePerformed.getValue(), servicePerformedManaged.getValue()));
        servicePerformedManaged.setUsedProducts(Util.nvl(servicePerformed.getUsedProducts(), servicePerformedManaged.getUsedProducts()));

        servicePerformedRepository.save(servicePerformedManaged);
    }

    @Override
    public ServicePerformed getById(Long idServicePerformed) {
        Optional<ServicePerformed> servicePerformed = servicePerformedRepository.findById(idServicePerformed);

        Integer days = servicePerformed.get().getDeliveryDate().compareTo(LocalDate.now());
        if(days < 0){
            servicePerformed.get().setStatus(EnumStatusServicePerformed.ATRASADO);
        }
        servicePerformed.get().setDaysForDelivery(days);
        return servicePerformed.orElseThrow(()-> new ObjectNotFound("Object not found! Id " + idServicePerformed + ", Type: " + ServicePerformed.class.getName()));
    }

    @Override
    public List<ServicePerformed> getAll() {
        List<ServicePerformed> services = servicePerformedRepository.findAll();
        for(ServicePerformed service : services){
            Integer days = service.getDeliveryDate().compareTo(LocalDate.now());
            if(days < 0){
                service.setStatus(EnumStatusServicePerformed.ATRASADO);
            }
        }
        return services;
    }

    private BigDecimal calculoTotalDoServico(Product product, Integer quantityUsed){
        //List<Product> usedProducts = servicePerformed.getUsedProducts();
        //BigDecimal value = BigDecimal.ZERO;
        //for(Product product : usedProducts){
       return BigDecimal.valueOf(quantityUsed).multiply(product.getValue());
       // }
        //return value.add(servicePerformed.getLaborCost());
    }


    @Override
    public JpaRepository<ServicePerformed, Long> getRepository() {
        return this.servicePerformedRepository;
    }
}
