//package br.com.inventory.mechanicalparts.Util;
//
//
//import com.smartbr.clinica.model.dto.system.AbstractDTO;
//import com.smartbr.clinica.model.entity.AbstractEntity;
//import com.smartbr.clinica.model.entity.Profissional;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @author Jakson Wilson Bonfim de Lima
// * @since 08/04/2020
// */
//public interface IAbstractService<E extends AbstractEntity<?>, I, DTO extends AbstractDTO<?>> {
//
//    JpaRepository<E, I> getRepository();
//
//    JpaSpecificationExecutor<E> getSpecRepository();
//
//    DTO findById(I id);
//
//    E findAndValidate(I id);
//
//    List<DTO> findAll(Map<String, Object> filters);
//
//    Page<DTO> findAll(Pageable pageable);
//
//    /**
//     * @param pageable Configuração de paginação da chamada.
//     * @param filters Caso possua alguma configuração referenciada na EnumSpecification*
//     * a consulta fará a condição referenciada na mesma.
//     * @return Lista de DTOs da entidade da consulta
//     * @see com.smartbr.clinica.infrastructure.specs.filtering.EnumSpecification
//     * <p>
//     * <p>
//     * Caso possua apenas configurações de paginação, a consulta será realizada normalmente.
//     * @see com.smartbr.clinica.pattern.constants.OperationsQueryParam
//     */
//    Page<DTO> findAllPageable(Pageable pageable, Map<String, Object> filters);
//
//    //Profissional getProfissional();
//}