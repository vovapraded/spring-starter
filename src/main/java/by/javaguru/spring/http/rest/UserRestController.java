package by.javaguru.spring.http.rest;

import by.javaguru.spring.dto.PageResponse;
import by.javaguru.spring.dto.UserCreateEditDto;
import by.javaguru.spring.dto.UserFilter;
import by.javaguru.spring.dto.UserReadDto;
import by.javaguru.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public PageResponse<UserReadDto> findAll(UserFilter filter, Pageable pageable){
//        model.addAttribute("users",userService.findAll());
        Page<UserReadDto> page = userService.findAll(filter,pageable);
        return PageResponse.of(page);
    }
    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id){
//        model.addAttribute("user",userService.findById(id));
        return userService.findById(id)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String create( @Validated @RequestBody UserCreateEditDto user){

        UserReadDto dto = userService.create(user);
        return "redirect:/users/" + dto.getId();

    }
    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Long id, @Validated @RequestBody UserCreateEditDto user){
        userService.update(id,user);
        return userService.update(id,user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
//    @DeleteMapping("{id}/delete")
//    public void delete(Long id) {
//        if (!userService.delete(id)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }
@DeleteMapping("{id}/delete")
public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    return userService.delete(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
}
    @GetMapping(value = "/{id}/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> findAvatar(@PathVariable("id") Long id){
        return userService.findAvatar(id)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
    }

}
