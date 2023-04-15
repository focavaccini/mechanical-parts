//package br.com.inventory.mechanicalparts.Util;
//
////import com.smartbr.clinica.context.IContext;
////import com.smartbr.clinica.converter.Converter;
////import com.smartbr.clinica.model.classes.PageableBean;
////import com.smartbr.clinica.model.dto.UsuarioDTO;
////import com.smartbr.clinica.model.dto.system.AbstractDTO;
////import com.smartbr.clinica.model.dto.system.DefaultDTO;
////import com.smartbr.clinica.model.entity.AbstractEntity;
////import com.smartbr.clinica.model.entity.generic.UsuarioPortal;
////import com.smartbr.clinica.service.IAbstractService;
////import com.smartbr.clinica.service.UsuarioPortalService;
////import com.smartbr.clinica.utils.CurrentSession;
////import com.smartbr.clinica.utils.LoggerUtil;
////import com.smartbr.clinica.utils.RequestUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.ParameterizedType;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Edimar
// */
//public abstract class AbstractController<E extends IAbstractService> {
//
//    @Autowired
//    private HttpServletRequest request;
//
//    @Autowired
//    private CurrentSession currentSession;
//
//    private Class<E> serviceClass;
//
//    public AbstractController() {
//        this.serviceClass = resolverServiceClass(this.getClass());
//    }
//
//    protected ResponseEntity okResponse() {
//        return ResponseEntity.ok().build();
//    }
//
//    @SuppressWarnings("unchecked")
//    protected <T extends Object> ResponseEntity<T> okResponse(T body) {
//        return ResponseEntity.ok(body);
//    }
//
//    @SuppressWarnings("unchecked")
//    protected <E extends AbstractEntity, DTO extends AbstractDTO> ResponseEntity<DTO> okResponsePage(Page<E> bodyPageCollection, Class<DTO> classDTO) {
//        if (!bodyPageCollection.isEmpty()) {
//            return ResponseEntity.ok((DTO) bodyPageCollection.map(item -> converterEntityParaDTO(item, classDTO)));
//        }
//        return createNoContentResponse();
//    }
//
////    protected <D extends AbstractDTO> ResponseEntity<DefaultDTO<D>> defaultResponse(List<? extends AbstractEntity> list, Class<D> clazzDto) {
////        final List<D> dtoList = Converter.converterEntityParaDTO(list, clazzDto);
////        return okResponse(new DefaultDTO<>(dtoList));
////    }
//
//    protected ResponseEntity createNoContentResponse() {
//        return ResponseEntity.noContent().build();
//    }
//
//    protected ResponseEntity createBadRequestResponse() {
//        return ResponseEntity.badRequest().build();
//    }
//
////    protected UsuarioDTO getUsuario() {
////        if (currentSession.isAuthenticated()) {
////            return currentSession.getCustomUser().getUsuario();
////        }
////        return null;
////    }
//
//    protected boolean isPersonal() {
//        return currentSession.isPersonal();
//    }
//
//    protected boolean isReceptionist() {
//        return currentSession.isReceptionist();
//    }
//
//    protected boolean isAdmin() {
//        return currentSession.isAdmin();
//    }
//
////    protected UsuarioPortal getUsuarioPortal() {
////        final UsuarioDTO usuario = getUsuario();
////        if (usuario != null) {
////            return getContext().getBean(UsuarioPortalService.class).findAndValidate(usuario.getId());
////        }
////        return null;
////    }
//
//    protected String getOrigin() {
//        return request.getHeader(HttpHeaders.ORIGIN);
//    }
//
//    protected String getHeader(String name) {
//        return request.getHeader(name);
//    }
//
//    protected E getService() {
//        return getContext().getBean(serviceClass);
//    }
//
//    /**
//     * @param sort query param
//     * @apiNote sort = id:desc
//     */
//    protected Sort getOrders(String sort) {
//        return RequestUtil.resolveSort(sort);
//    }
//
//    protected Integer getParamValueAsInteger(final String pathVariable) {
//        return RequestUtil.getParamValueAsInteger(request, pathVariable);
//    }
//
//    protected <D extends AbstractDTO<?>, E extends AbstractEntity> E converterDTOParaEntity(D dto, Class<E> clazzEntity) {
//        return Converter.converterDTOParaEntity(dto, clazzEntity);
//    }
//
//    protected <D extends AbstractDTO<?>, E extends AbstractEntity> List<E> converterDTOParaEntity(List<D> dtos, Class<E> clazzEntity) {
//        return Converter.converterDTOParaEntity(dtos, clazzEntity);
//    }
//
//    protected <D extends AbstractDTO<?>, E extends AbstractEntity> D converterEntityParaDTO(E entity, Class<D> clazzDto) {
//        return Converter.converterEntityParaDTO(entity, clazzDto);
//    }
//
//    protected <D extends AbstractDTO<?>, E extends AbstractEntity> List<D> converterEntityParaDTO(List<E> entitys, Class<D> clazzDto) {
//        return Converter.converterEntityParaDTO(entitys, clazzDto);
//    }
//
//    @SuppressWarnings("unchecked")
//    private Class<E> resolverServiceClass(Class<?> serviceClass) {
//        try {
//            return (Class<E>) ((ParameterizedType) serviceClass.getGenericSuperclass()).getActualTypeArguments()[0];
//        } catch (Exception e) {
//            LoggerUtil.getLogger(this.getClass()).error(e.getMessage());
//        }
//        return null;
//    }
//
//    private IContext getContext() {
//        return IContext.context();
//    }
//
//
////    protected <DTO extends AbstractDTO> ResponseEntity<Page<DTO>> createEmptyPagebleResponse() {
////        final PageableBean<DTO> pageableBean = new PageableBean();
////        pageableBean.setPage(0);
////        pageableBean.setPageSize(1);
////        pageableBean.setContent(new ArrayList<DTO>());
////        pageableBean.setTotalElements(0);
////        return ResponseEntity.ok().body(pageableBean.getPaged());
////    }
//
//}
