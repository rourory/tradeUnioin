package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.LaborContractDTO;
import com.sts.tradeunion.entities.docs.LaborContractEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.services.LaborContractService;
import com.sts.tradeunion.util.validation.LaborContractValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("people/{id}/labor-contracts")
public class LaborContractController {

    private final LaborContractService laborContractService;
    private final ModelMapper modelMapper;
    private final LaborContractValidator laborContractValidator;

    public LaborContractController(LaborContractService laborContractService, ModelMapper modelMapper, LaborContractValidator laborContractValidator) {
        this.laborContractService = laborContractService;
        this.modelMapper = modelMapper;
        this.laborContractValidator = laborContractValidator;
    }

    @GetMapping
    public ResponseEntity<List<LaborContractDTO>> getOwnersMembershipCards(@PathVariable int id) {
        List<LaborContractDTO> laborContracts = new ArrayList<>();
        laborContractService.findByOwnerId(id)
                .forEach(laborContractEntity -> laborContracts.add(modelMapper.map(laborContractEntity, LaborContractDTO.class)));
        return new ResponseEntity<>(laborContracts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LaborContractDTO> create(@PathVariable(value = "id") int personId,
                                                   @RequestBody @Valid LaborContractDTO laborContract, BindingResult bindingResult) {
        laborContractValidator.validate(laborContract, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, laborContract);
        return new ResponseEntity<>(modelMapper.map(laborContractService
                .save(modelMapper.map(laborContract, LaborContractEntity.class), personId), LaborContractDTO.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LaborContractDTO> update(@PathVariable(value = "id") int personId,
                                                   @RequestBody @Valid LaborContractDTO laborContract, BindingResult bindingResult) {
        laborContractValidator.validate(laborContract, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, laborContract);
        return new ResponseEntity<>(modelMapper.map(laborContractService
                .update(modelMapper.map(laborContract, LaborContractEntity.class), personId), LaborContractDTO.class), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") int ownerId, @RequestParam("contractId") int id) {
        laborContractService.delete(ownerId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
