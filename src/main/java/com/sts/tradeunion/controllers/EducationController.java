package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.EducationClassificationDTO;
import com.sts.tradeunion.entities.EducationClassificationEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.exceptions.EntityNotFoundException;
import com.sts.tradeunion.services.EducationServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/education_states")
public class EducationController {
 //some comment
    private final EducationServiceImpl educationService;

    private final ModelMapper modelMapper;

    public EducationController(EducationServiceImpl educationService, ModelMapper modelMapper) {
        this.educationService = educationService;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<List<EducationClassificationDTO>> getBasicInfo() {
        List<EducationClassificationDTO> states = new ArrayList<>();
        educationService.getAll().forEach(state -> states.add(modelMapper.map(state, EducationClassificationDTO.class)));
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public EducationClassificationDTO get(@PathVariable int id) {
        return modelMapper.map(educationService
                .findById(id).orElseThrow(() -> new EntityNotFoundException(id)), EducationClassificationDTO.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<EducationClassificationDTO> save(@RequestBody @Valid EducationClassificationDTO stateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, stateDTO);
        return new ResponseEntity<>(modelMapper.map(educationService
                .save(modelMapper.map(stateDTO, EducationClassificationEntity.class)), EducationClassificationDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<EducationClassificationDTO> update(@Valid @RequestBody EducationClassificationDTO stateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, stateDTO);
        return new ResponseEntity<>(modelMapper.map(educationService
                .save(modelMapper.map(stateDTO, EducationClassificationEntity.class)), EducationClassificationDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<Object> delete(@RequestParam("id") int id) {
        educationService.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return new ResponseEntity<>(educationService.deleteById(id), HttpStatus.OK);
    }
}
