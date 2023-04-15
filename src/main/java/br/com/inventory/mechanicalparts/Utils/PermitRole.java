//package br.com.inventory.mechanicalparts.Util;
//
//@Target({ElementType.TYPE, ElementType.METHOD})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface PermitRole {
//
//    /**
//     * Retorna a lista de atributos de configuração de segurança validação com base no usuario do token,
//     * quando seu usuario é ROLE_ADMIN, não necessita da anotação @PermitRole pois esse é o super usuario.
//     *
//     * <p>
//     * Entidade = Role
//     * Possui metodos estáticos para uso, breve descrição das funções de cada ROLE
//     *
//     * <p>
//     * ROLE_ROOT, ROLE_ADMIN, ROLE_USER, ROLE_RECEPTIONIST, ROLE_PERSONAL, ROLE_ANONYMOUS"
//     *
//     * @return String[] Os atributos do método seguro
//     */
//    String[] value() default "";
//
//    String pathId() default "";
//
//}