package com.sts.tradeunion.services;

import com.sts.tradeunion.entities.UserEntity;
import com.sts.tradeunion.repositories.UserRepository;
import com.sts.tradeunion.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * Service класс для работы с сущностью {@link UserEntity}. Это особый service класс, необходимый для работы
 * с SpringSecurity, поэтому он реализет метод {@code loadUserByUsername} интерфейса {@link UserDetailsService}
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Метод служит для получения объекта пользователя через его имя.
     * @param username имя пользователя
     * @return объект класса {@link UserEntity}, обернутый в класс {@link UserDetailsImpl} для удобного
     * доступа к инофрмации о юзере
     * @throws UsernameNotFoundException выбрасывается в случае отсутствия инофрмации в базе данных о пользователе с таким именем
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("Пользователь с таким именем не найден");
        return new UserDetailsImpl(user.get());
    }

    public Optional<UserEntity> findById(int id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity save(UserEntity user) {
        user.setCreated(new Date());
        user.setUpdated(LocalDateTime.now());
        user.setRole("USER");
        return userRepository.save(user);
    }

    public UserEntity update(UserEntity user) {
        user.setUpdated(LocalDateTime.now());
        return userRepository.save(user);
    }

    public boolean delete(int id) {
        return userRepository.deleteById(id);
    }


}
