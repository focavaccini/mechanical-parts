//package br.com.inventory.mechanicalparts.Util;
//
//
//import com.smartbr.clinica.exception.ISmartException;
//import com.smartbr.clinica.exception.SmartRuntimeException;
//import com.smartbr.clinica.exception.enums.EnumSmartException;
//import com.smartbr.clinica.utils.ListUtil;
//import com.smartbr.clinica.utils.NumericUtil;
//import com.smartbr.clinica.utils.StringUtil;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.function.Predicate;
//
//public class ValidateMandatoryFields {
//
//    private final List<String> nullFields = new ArrayList<>();
//
//    public void add(Object valor, String nome) {
//        boolean isNullOrEmpty;
//        if (valor instanceof Collection<?>) {
//            isNullOrEmpty = ListUtil.isNullOrEmpty((Collection<?>) valor);
//        } else if (valor instanceof CharSequence) {
//            isNullOrEmpty = StringUtil.isNullOrEmpty(valor);
//        } else if (valor != null && valor.getClass().isArray()) {
//            isNullOrEmpty = ListUtil.isNullOrEmpty((Object[]) valor);
//        } else {
//            isNullOrEmpty = valor == null;
//        }
//
//        if (isNullOrEmpty) {
//            nullFields.add(nome);
//        }
//    }
//
//    public <T> void add(String nome, T valor, Predicate<? super T> predicateAdd) {
//        if (predicateAdd.test(valor)) {
//            nullFields.add(nome);
//        }
//    }
//
//    public void addValor(Number valor, String nome) {
//        if (valor == null || NumericUtil.isLessOrEquals(valor, 0)) {
//            nullFields.add(nome);
//        }
//    }
//
//    public void validate() {
//        validate(EnumSmartException.CAMPOS_OBRIGATORIOS);
//    }
//
//    public void validate(ISmartException messageTemplate) {
//        if (ListUtil.isNotNullOrNotEmpty(this.nullFields)) {
//            throw new SmartRuntimeException(messageTemplate, this.nullFields);
//        }
//    }
//
//}