package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.MaritalStateDTO;
import com.sts.tradeunion.entities.MaritalStateEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.exceptions.EntityNotFoundException;
import com.sts.tradeunion.services.MaritalStateServiceImpl;
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
@RequestMapping("/marital_states")
public class MaritalStateController {

    private final MaritalStateServiceImpl maritalStateService;

    private final ModelMapper modelMapper;


    public MaritalStateController(MaritalStateServiceImpl maritalStateService, ModelMapper modelMapper) {
        this.maritalStateService = maritalStateService;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<List<MaritalStateDTO>> getBasicInfo() {
        List<MaritalStateDTO> states = new ArrayList<>();
        maritalStateService.getAll().forEach(state -> states.add(modelMapper.map(state, MaritalStateDTO.class)));
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public MaritalStateDTO get(@PathVariable int id) {
        return modelMapper.map(maritalStateService
                .findById(id).orElseThrow(() -> new EntityNotFoundException(id)), MaritalStateDTO.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<MaritalStateDTO> save(@RequestBody @Valid MaritalStateDTO stateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, stateDTO);
        return new ResponseEntity<>(modelMapper.map(maritalStateService
                .save(modelMapper.map(stateDTO, MaritalStateEntity.class)), MaritalStateDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<MaritalStateDTO> update(@Valid @RequestBody MaritalStateDTO stateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, stateDTO);
        return new ResponseEntity<>(modelMapper.map(maritalStateService
                .save(modelMapper.map(stateDTO, MaritalStateEntity.class)), MaritalStateDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<Object> delete(@RequestParam("id") int id) {
        maritalStateService.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return new ResponseEntity<>(maritalStateService.deleteById(id), HttpStatus.OK);
    }
}
