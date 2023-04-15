package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractController;
import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractController<E extends IAbstractService> implements IAbstractController {

    private final Class<E> serviceClass;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private ApplicationContext context;

    public AbstractController() {
        this.serviceClass = resolverServiceClass(this.getClass());
    }

    @Override
    public <S, D> D convert(S source, Class<D> destClass) {
        return this.mapper.map(source, destClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, D> List<T> convert(List<?> list, Class<D> destClass) {
        List<D> convertList = new ArrayList<>();
        list.forEach( element -> {
            convertList.add(this.mapper.map(element, destClass));
        });
        return (List<T>) convertList;
    }

    public E getService(){
        return context.getBean(serviceClass);
    }
    @SuppressWarnings("unchecked")
    private Class<E> resolverServiceClass(Class<?> serviceClass) {
        try {
            return (Class<E>) ((ParameterizedType) serviceClass.getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public JpaRepository getRepository() {
        return null;
    }
}
