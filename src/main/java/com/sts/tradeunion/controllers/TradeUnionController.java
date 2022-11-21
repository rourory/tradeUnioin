package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.TradeUnionClassificationDTO;
import com.sts.tradeunion.entities.TradeUnionClassificationEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.exceptions.EntityNotFoundException;
import com.sts.tradeunion.services.TradeUnionServiceImpl;
import com.sts.tradeunion.util.validation.OrganizationValidator;
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
@RequestMapping("/unions")
@CrossOrigin(origins = "http://localhost:3000")
public class TradeUnionController {
    private final TradeUnionServiceImpl orgService;
    private final ModelMapper modelMapper;
    private final OrganizationValidator organizationValidator;

    public TradeUnionController(TradeUnionServiceImpl orgService, ModelMapper modelMapper, OrganizationValidator organizationValidator) {
        this.orgService = orgService;
        this.modelMapper = modelMapper;
        this.organizationValidator = organizationValidator;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<List<TradeUnionClassificationDTO>> getOrganizations() {
        List<TradeUnionClassificationDTO> orgs = new ArrayList<>();
        orgService.getAll().forEach(org -> orgs.add(modelMapper.map(org, TradeUnionClassificationDTO.class)));
        return new ResponseEntity<>(orgs, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public TradeUnionClassificationDTO get(@PathVariable int id) {
        return modelMapper.map(orgService
                .findById(id).orElseThrow(() -> new EntityNotFoundException(id)), TradeUnionClassificationDTO.class);
    }




    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<TradeUnionClassificationDTO> save(@RequestBody @Valid TradeUnionClassificationDTO organizationDTO, BindingResult bindingResult) {
        organizationValidator.validate(organizationDTO, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, organizationDTO);
        return new ResponseEntity<>(modelMapper.map(orgService
                .save(modelMapper.map(organizationDTO, TradeUnionClassificationEntity.class)), TradeUnionClassificationDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<TradeUnionClassificationDTO> update(@Valid @RequestBody TradeUnionClassificationDTO organizationDTO, BindingResult bindingResult) {
        organizationValidator.validate(organizationDTO, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, organizationDTO);
        return new ResponseEntity<>(modelMapper.map(orgService
                .save(modelMapper.map(organizationDTO, TradeUnionClassificationEntity.class)), TradeUnionClassificationDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<Object> delete(@RequestParam("id") int id) {
        orgService.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return new ResponseEntity<>(orgService.deleteById(id), HttpStatus.OK);
    }

}
