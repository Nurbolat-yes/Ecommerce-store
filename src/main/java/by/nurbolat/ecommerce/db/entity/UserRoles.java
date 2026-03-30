package by.nurbolat.ecommerce.db.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRoles {
    USER,ADMIN;

    public SimpleGrantedAuthority toAuthority(){
        return new SimpleGrantedAuthority("ROLE_"+this.name());
    }
}
