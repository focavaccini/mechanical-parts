//package br.com.inventory.mechanicalparts.Util;
//
//import com.smartbr.clinica.context.IContext;
//import com.smartbr.clinica.converter.Converter;
//import com.smartbr.clinica.exception.SmartRuntimeException;
//import com.smartbr.clinica.exception.enums.EnumSmartException;
//import com.smartbr.clinica.infrastructure.specs.filtering.FindAllSpecification;
//import com.smartbr.clinica.model.dto.UsuarioDTO;
//import com.smartbr.clinica.model.dto.system.AbstractDTO;
//import com.smartbr.clinica.model.entity.AbstractEntity;
//import com.smartbr.clinica.model.entity.Profissional;
//import com.smartbr.clinica.pattern.constants.OperationsQueryParam;
//import com.smartbr.clinica.service.IAbstractService;
//import com.smartbr.clinica.service.ProfissionalService;
//import com.smartbr.clinica.utils.CurrentSession;
//import com.smartbr.clinica.utils.ListUtil;
//import com.smartbr.clinica.utils.Utils;
//import com.smartbr.clinica.utils.optional.OptionalUtil;
//import lombok.var;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.mapping.PropertyReferenceException;
//import org.springframework.data.repository.Repository;
//import org.springframework.util.ObjectUtils;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
///**
// * @author Jakson Wilson Bonfim de Lima
// * @since 08/04/2020
// */
//@SuppressWarnings("unchecked")
//public abstract class AbstractServiceImpl<E extends AbstractEntity<?>, I extends Serializable, DTO extends AbstractDTO<?>>
//        implements IAbstractService<E, I, DTO> {
//
//    private final Class<E> entityClass;
//    private final Class<DTO> dtoClass;
//
//    @Autowired
//    private CurrentSession currentSession;
//
//    @SuppressWarnings("unchecked")
//    public AbstractServiceImpl() {
//        Type[] actualTypeArguments = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
//        entityClass = (Class<E>) actualTypeArguments[0];
//        dtoClass = (Class<DTO>) actualTypeArguments[2];
//    }
//
//    private IContext getContext() {
//        return IContext.context();
//    }
//
//    @Override
//    public JpaRepository<E, I> getRepository() {
//        return getContext().getRepositoryFromClass(entityClass);
//    }
//
//    @Override
//    public JpaSpecificationExecutor<E> getSpecRepository() {
//        return getContext().getSpecRepositoryFromClass(entityClass);
//    }
//
//    public <R extends Repository> R getRepository(Class<R> classRespository) {
//        return getContext().getBean(classRespository);
//    }
//
//    public <R extends IAbstractService> R getService(Class<R> classService) {
//        return getContext().getBean(classService);
//    }
//
//
//    @Override
//    public DTO findById(I id) {
//        final E entidade = findAndValidate(id);
//
//        return converterEntityToDTO(entidade);
//    }
//
//    @Override
//    public E findAndValidate(I id) {
//        if (id == null) {
//            throw new SmartRuntimeException(EnumSmartException.NULL_POINTER_EXCEPTION);
//        }
//
//        return getRepository()
//                .findById(id)
//                .orElseThrow(() -> new SmartRuntimeException(EnumSmartException.ENTITY_NOT_FOUND, entityClass.getSimpleName(), id));
//    }
//
//    @Override
//    public List<DTO> findAll(Map<String, Object> filters) {
//        try {
//            final JpaRepository<E, I> repository = getRepository();
//            validarRepository(repository);
//
//            List<E> entidades = findAllEntidadesByFilters(filters, repository);
//
//            return ListUtil.stream(entidades)
//                    .map(this::converterEntityToDTO)
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            throw exception(e);
//        }
//    }
//
//    @Override
//    public Page<DTO> findAllPageable(Pageable pageable, Map<String, Object> filters) {
//        try {
//            final JpaRepository<E, I> repository = getRepository();
//            validarRepository(repository);
//
//            Page<E> entidades = null;
//            if (Utils.isNotEmpty(filters)) {
//                entidades = findAllEntidadesPageableByFilters(pageable, filters, repository);
//            }
//
//            if (Utils.isEmpty(entidades)) {
//                entidades = getRepository().findAll(pageable);
//            }
//
//            return entidades.map(this::converterEntityToDTO);
//        } catch (Exception e) {
//            throw exception(e);
//        }
//    }
//
//    @Override
//    public Page<DTO> findAll(Pageable pageable) {
//        Sort sort = pageable.getSort();
//
//        if (Utils.isEmpty(sort)) {
//            sort = sortBy();
//            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
//        }
//
//        final Page<E> entidades = getRepository().findAll(pageable);
//
//        return entidades.map(this::converterEntityToDTO);
//    }
//
//    private List<E> findAllEntidadesByFilters(Map<String, Object> filters, JpaRepository<E, I> repository) {
//        if (possuiApenasFiltrosPageable(filters)) {
//            return findAllSorted(filters);
//        }
//
//        if (Utils.isNotEmpty(filters)) {
//            return findAllByFilters(filters, repository);
//        }
//        return getRepository().findAll();
//    }
//
//    private Page<E> findAllEntidadesPageableByFilters(Pageable pageable, Map<String, Object> filters, JpaRepository<E, I> repository) {
//        if (possuiApenasFiltrosPageable(filters)) {
//            return repository.findAll(pageable);
//        }
//
//        Page<E> entidades = findAllPageableByFilters(pageable, filters, repository);
//        if (Utils.isNotEmpty(entidades)) {
//            return entidades;
//        }
//        return repository.findAll(pageable);
//    }
//
//    private E converterDTOToEntity(DTO dto) {
//        return Converter.converterDTOParaEntity(dto, entityClass);
//    }
//
//    protected DTO converterEntityToDTO(E entidade) {
//        return Converter.converterEntityParaDTO(entidade, dtoClass);
//    }
//
//    private Sort sortBy() {
//        return Sort.unsorted();
//    }
//
//    private List<E> findAllSorted(Map<String, Object> filters) {
//        final Sort sort = Utils.verificarFiltrosPageable(filters);
//        if (Utils.isEmpty(sort)) {
//            //Quando o sort estava null, retornava return null, não fazia um busca geral sem sort e gerando uma lista vazia.
//            return getRepository().findAll();
//        }
//        return getRepository().findAll(sort);
//    }
//
//    //Migrado para Utils a ser usado em mais de um local
//// private Sort verificarFiltrosPageable(Map<String, Object> filters) {
//// String sortOrder = ObjectUtils.nullSafeToString(filters.getOrDefault("sort", null));
//// if (Utils.isEmpty(filters.get("sort"))) {
//// return null;
//// }
////
//// Sort sort = resolverSortField(sortOrder);
//// sort = resolverAscDesc(sortOrder, sort);
//// return OptionalUtil.ofNullable(sort)
//// .orElse(null);
//// }
////
//// private Sort resolverAscDesc(String sortOrder, Sort sort) {
//// return sortOrder.contains("asc")
//// ? sort.ascending()
//// : sort.descending();
//// }
////
//// private Sort resolverSortField(String sortOrder) {
//// return Sort.by(sortOrder
//// .replace(":asc", "")
//// .replace(":desc", ""));
//// }
//
//    private Page<E> findAllPageableByFilters(Pageable pageable, Map filters, JpaRepository<E, I> repository) {
//        return FindAllSpecification
//                .useRepository((JpaSpecificationExecutor<E>) repository)
//                .filterBy(filters)
//                .findAllPageable(pageable);
//    }
//
//    private List<E> findAllByFilters(Map<String, Object> filters, JpaRepository<E, I> repository) {
//        return FindAllSpecification
//                .useRepository((JpaSpecificationExecutor<E>) repository)
//                .filterBy(filters)
//                .findAll();
//    }
//
//    private void validarRepository(JpaRepository<E, I> repository) {
//        if (!JpaSpecificationExecutor.class.isAssignableFrom(repository.getClass())) {
//            throw new SmartRuntimeException(EnumSmartException.REPOSITORY_IMPLEMENTS_NOT_FOUND,
//                    entityClass.getSimpleName(),
//                    JpaSpecificationExecutor.class.getSimpleName());
//        }
//    }
//
//    private boolean possuiApenasFiltrosPageable(Map<String, Object> filters) {
//        final List<String> filterOpertaions = new ArrayList<>();
//
//        ListUtil.stream(OperationsQueryParam.OPERATIONS)
//                .filter(filters::containsKey)
//                .forEach(filterOpertaions::add);
//
//        return ListUtil.isNotNullOrNotEmpty(filterOpertaions)
//                && Utils.equals(filters.size(), filterOpertaions.size());
//    }
//
//    private SmartRuntimeException exception(Exception e) throws SmartRuntimeException {
//        if (e instanceof SmartRuntimeException) {
//            throw new SmartRuntimeException(EnumSmartException.ATRIBUTO_NAO_ENCOTRADO,
//                    getAttributeNotFound(e.getLocalizedMessage()),
//                    entityClass.getSimpleName());
//        }
//        if (e instanceof PropertyReferenceException) {
//            throw new SmartRuntimeException(EnumSmartException.SORT_INCORRETO);
//        }
//        throw new SmartRuntimeException(e);
//    }
//
//    private String getAttributeNotFound(String exceptionMessage) {
//        var message = Pattern.compile("\\[(\\w+)\\]").matcher(exceptionMessage);
//        return message.find()
//                ? message.group().replace("[", "'").replace("]", "'")
//                : null;
//    }
//
//    @Override
//    public Profissional getProfissional() {
//        final UsuarioDTO usuario = getUsuario();
//        if (usuario != null && usuario.getProfessionalId() != null) {
//            return getContext().getBean(ProfissionalService.class).findAndValidate(getUsuario().getProfessionalId());
//        }
//        return null;
//    }
//
//    protected UsuarioDTO getUsuario() {
//        if (currentSession.isAuthenticated()) {
//            return currentSession.getCustomUser().getUsuario();
//        }
//        return null;
//    }
//
//}
