package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.LaborContractDTO;
import com.sts.tradeunion.entities.docs.LaborContractEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.services.LaborContractServiceImpl;
import com.sts.tradeunion.util.validation.LaborContractValidator;
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
@RequestMapping("people/{id}/labor-contracts")
public class LaborContractController {

    private final LaborContractServiceImpl laborContractService;
    private final ModelMapper modelMapper;
    private final LaborContractValidator laborContractValidator;

    public LaborContractController(LaborContractServiceImpl laborContractService, ModelMapper modelMapper, LaborContractValidator laborContractValidator) {
        this.laborContractService = laborContractService;
        this.modelMapper = modelMapper;
        this.laborContractValidator = laborContractValidator;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<List<LaborContractDTO>> getOwnersMembershipCards(@PathVariable int id) {
        List<LaborContractDTO> laborContracts = new ArrayList<>();
        laborContractService.findByOwnerId(id)
                .forEach(laborContractEntity -> laborContracts.add(modelMapper.map(laborContractEntity, LaborContractDTO.class)));
        return new ResponseEntity<>(laborContracts, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<LaborContractDTO> create(@PathVariable(value = "id") int personId,
                                                   @RequestBody @Valid LaborContractDTO laborContract, BindingResult bindingResult) {
        laborContractValidator.validate(laborContract, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, laborContract);
        return new ResponseEntity<>(modelMapper.map(laborContractService
                .save(modelMapper.map(laborContract, LaborContractEntity.class), personId), LaborContractDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<LaborContractDTO> update(@PathVariable(value = "id") int personId,
                                                   @RequestBody @Valid LaborContractDTO laborContract, BindingResult bindingResult) {
        laborContractValidator.validate(laborContract, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, laborContract);
        return new ResponseEntity<>(modelMapper.map(laborContractService
                .update(modelMapper.map(laborContract, LaborContractEntity.class), personId), LaborContractDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") int ownerId, @RequestParam("contractId") int id) {
        laborContractService.delete(ownerId,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
