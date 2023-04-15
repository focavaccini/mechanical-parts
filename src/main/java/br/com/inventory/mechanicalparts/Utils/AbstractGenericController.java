//package br.com.inventory.mechanicalparts.Util;
//
//import com.smartbr.clinica.controller.interfaces.IAbstractController;
//import com.smartbr.clinica.model.dto.system.AbstractDTO;
//import com.smartbr.clinica.model.dto.system.DefaultDTO;
//import com.smartbr.clinica.service.IAbstractService;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Map;
//
///**
// * @author Edimar
// */
//public abstract class AbstractGenericController<S extends IAbstractService<?, Integer, DTO>, DTO extends AbstractDTO<?>>
//        extends AbstractController<S>
//        implements IAbstractController<DTO> {
//
//    @Override
//    public ResponseEntity<Page<DTO>> findAll(Pageable pageable, Map<String, Object> filters) {
//        return okResponse(getService().findAllPageable(pageable, filters));
//    }
//
//    @Override
//    public DefaultDTO<DTO> findAll(Map<String, Object> filters) {
//        return new DefaultDTO<>(getService().findAll(filters));
//    }
//
//    @Override
//    public ResponseEntity<DTO> findById(Integer id) {
//        return okResponse(getService().findById(id));
//    }
//
//}