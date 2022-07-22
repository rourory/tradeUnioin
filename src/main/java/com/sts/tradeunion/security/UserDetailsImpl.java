package com.sts.tradeunion.security;

import com.sts.tradeunion.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Класс-обёртка над сущностью {@link UserEntity}, отражающей пользователя как совокупность username и password
 * <p>
 * Обеспечивает удобный доступ к такой информации о юзере, как Права доступа ({@code getAuthorities}), пароль ({@code getPassword}), юзернейм ({@code getUsername}) и др.
 * <p>
 * Необходим для реализации Security.
 */
public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {

    private final UserEntity user;

    public UserDetailsImpl(UserEntity user) {
        this.user = user;
    }

    //Получения прав пользователя
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //Получение пароля пользователя
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //Получение имени пользователя
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //Метод необходим для получения данных аутенфицированного пользователя
    public UserEntity getUser() {
        return user;
    }
}
