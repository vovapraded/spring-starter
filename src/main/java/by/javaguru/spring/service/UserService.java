package by.javaguru.spring.service;

import by.javaguru.spring.database.repository.UserRepository;
import by.javaguru.spring.dto.UserCreateEditDto;
import by.javaguru.spring.dto.UserDto;
import by.javaguru.spring.database.entity.User;
import by.javaguru.spring.dto.UserFilter;
import by.javaguru.spring.dto.UserReadDto;
import by.javaguru.spring.mapper.UserCreateEditMapper;
import by.javaguru.spring.mapper.UserMapper;
import by.javaguru.spring.mapper.UserReadMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@ToString
@Service
@Transactional(readOnly = true)

public class UserService {
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserRepository userRepository;
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
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }
    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto  userDto){
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDto,entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
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




}