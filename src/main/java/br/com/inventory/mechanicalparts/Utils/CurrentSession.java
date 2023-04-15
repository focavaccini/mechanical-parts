//package br.com.inventory.mechanicalparts.Util;
//
//
//import com.smartbr.clinica.model.classes.CustomUserDetails;
//import com.smartbr.clinica.model.entity.generic.Role;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.Serializable;
//
///**
// * @author Edimar
// */
//@Component("currentSession")
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class CurrentSession implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Autowired
//    private HttpServletRequest request;
//
//    public HttpServletRequest getRequest() {
//        return request;
//    }
//
//    public Authentication getAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//
//    public CustomUserDetails getCustomUser() {
//        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }
//
//    public boolean isAuthenticated() {
//        return !isRole(Role.ANONYMOUS);
//    }
//
//    public boolean isAdmin() {
//        return isRole(Role.ADMIN);
//    }
//
//    public boolean isPersonal() {
//        return isRole(Role.PERSONAL);
//    }
//
//    public boolean isReceptionist() {
//        return isRole(Role.RECEPTIONIST);
//    }
//
//    private boolean isRole(String role) {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return ListUtil.stream(authentication.getAuthorities())
//                .anyMatch(a -> StringUtils.equalsIgnoreCase(a.toString(), role));
//    }
//
//}