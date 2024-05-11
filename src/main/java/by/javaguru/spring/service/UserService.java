package by.javaguru.spring.service;

import by.javaguru.spring.database.entity.QUser;
import by.javaguru.spring.database.repository.UserRepository;
import by.javaguru.spring.dto.*;
import by.javaguru.spring.database.entity.User;
import by.javaguru.spring.mapper.UserCreateEditMapper;
import by.javaguru.spring.mapper.UserMapper;
import by.javaguru.spring.mapper.UserReadMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.javaguru.spring.database.entity.QUser.*;

@RequiredArgsConstructor
@ToString
@Service
@Transactional(readOnly = true)

public class UserService implements UserDetailsService {
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable){
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();
        return  userRepository.findAll(predicate,pageable)
                .map(userReadMapper::map);

    }

    public List<UserReadDto> findAll(UserFilter filter){
        return userRepository.findAllByFilter(filter).stream()
                .map(userReadMapper::map)
                .toList();
    }

    public List<UserReadDto> findAll(){
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }
    public Optional<UserReadDto> findById(Long id){
        return userRepository.findById(id).map(userReadMapper::map);
    }
    @Transactional
    public UserReadDto create(UserCreateEditDto userDto){
        return Optional.of(userDto)
                .map(dto -> {
                    uploadImage(dto.getImage());
                    return  userCreateEditMapper.map(dto);
                })                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }
    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto  userDto){
        return userRepository.findById(id)
                .map(entity -> {
                    uploadImage(userDto.getImage());
                    return  userCreateEditMapper.map(userDto,entity);
                })
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }
@SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty()){
            imageService.upload(image.getOriginalFilename(),image.getInputStream());
        }
    }
    public  Optional<byte[]> findAvatar(Long id){
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    @Transactional
    public boolean delete(Long id){
        return userRepository.findById(id)
                .map(entity ->{
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                }).orElse(false);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
        .orElseThrow(()-> new UsernameNotFoundException("Failed ro retrieve user: "+username));

    }
}
