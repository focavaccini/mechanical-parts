package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.dtos.ServicePerformedDTO;
import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import br.com.inventory.mechanicalparts.repositories.ServicePerformedRepository;
import br.com.inventory.mechanicalparts.services.ProductService;
import br.com.inventory.mechanicalparts.services.ServicePerformedService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicePerformedServiceImpl implements ServicePerformedService {

    private ServicePerformedRepository servicePerformedRepository;

    private ProductService productService;

    @Override
    public ServicePerformed insert(ServicePerformed servicePerformed) {
        servicePerformed = servicePerformedRepository.save(servicePerformed);

        productService.updateQuantity(servicePerformed.getUsedProducts());

        return servicePerformed;
    }

    @Override
    public void update(Long idServicePerformed, ServicePerformed servicePerformed) {
        ServicePerformed servicePerformedManaged = servicePerformedRepository.findById(idServicePerformed).orElseThrow();

        servicePerformedManaged.setDescription(Util.nvl(servicePerformed.getDescription(), servicePerformedManaged.getDescription()));
        servicePerformedManaged.setServiceDays(Util.nvl(servicePerformed.getServiceDays(), servicePerformedManaged.getServiceDays()));
        servicePerformedManaged.setObservation(Util.nvl(servicePerformed.getObservation(), servicePerformedManaged.getObservation()));
        servicePerformedManaged.setLaborCost(Util.nvl(servicePerformed.getLaborCost(), servicePerformedManaged.getLaborCost()));
        servicePerformedManaged.setTotalValue(Util.nvl(servicePerformed.getTotalValue(), servicePerformedManaged.getTotalValue()));
        servicePerformedManaged.setProblemReported(Util.nvl(servicePerformed.getProblemReported(), servicePerformedManaged.getProblemReported()));
        servicePerformedManaged.setDeliveryDate(Util.nvl(servicePerformed.getDeliveryDate(), servicePerformedManaged.getDeliveryDate()));
        servicePerformedManaged.setValue(Util.nvl(servicePerformed.getValue(), servicePerformedManaged.getValue()));
        servicePerformedManaged.setUsedProducts(Util.nvl(servicePerformed.getUsedProducts(), servicePerformedManaged.getUsedProducts()));

        servicePerformedRepository.save(servicePerformedManaged);
    }

//    @Override
//    public ServicePerformedDTO converterToDTO(ServicePerformed servicePerformed) {
//        ServicePerformedDTO servicePerformedDTO = new ServicePerformedDTO();
//
//        servicePerformedDTO.setDescription(servicePerformed.getDescription());
//        servicePerformedDTO.setServiceDays(servicePerformed.getServiceDays());
//        servicePerformedDTO.setObservation(servicePerformed.getObservation());
//        servicePerformedDTO.setLaborCost(servicePerformed.getLaborCost());
//        servicePerformedDTO.setTotalValue(servicePerformed.getTotalValue());
//        servicePerformedDTO.setProblemReported(servicePerformed.getProblemReported());
//        servicePerformedDTO.setDeliveryDate(servicePerformed.getDeliveryDate());
//        servicePerformedDTO.setValue(servicePerformed.getValue());
//        //servicePerformedDTO.setUsedProducts(servicePerformed.getUsedProducts());
//
//        return servicePerformedDTO;
//    }
//
//    @Override
//    public ServicePerformed converterToEntity(ServicePerformedDTO servicePerformedDTO) {
//        ServicePerformed servicePerformed = new ServicePerformed();
//
//        servicePerformed.setDescription(servicePerformedDTO.getDescription());
//        servicePerformed.setServiceDays(servicePerformedDTO.getServiceDays());
//        servicePerformed.setObservation(servicePerformedDTO.getObservation());
//        servicePerformed.setLaborCost(servicePerformedDTO.getLaborCost());
//        servicePerformed.setTotalValue(servicePerformedDTO.getTotalValue());
//        servicePerformed.setProblemReported(servicePerformedDTO.getProblemReported());
//        servicePerformed.setDeliveryDate(servicePerformedDTO.getDeliveryDate());
//        servicePerformed.setValue(servicePerformedDTO.getValue());
//        //servicePerformed.setUsedProducts(servicePerformedDTO.getUsedProducts());
//
//        return servicePerformed;
//    }

    @Override
    public JpaRepository<ServicePerformed, Long> getRepository() {
        return this.servicePerformedRepository;
    }
}
