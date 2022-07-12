package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.LaborContractDTO;
import com.sts.tradeunion.entities.docs.LaborContractEntity;
import com.sts.tradeunion.services.LaborContractService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("people/{id}/labor-contracts")
public class LaborContractController {

    private final LaborContractService laborContractService;
    private final ModelMapper modelMapper;

    public LaborContractController(LaborContractService laborContractService, ModelMapper modelMapper) {
        this.laborContractService = laborContractService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<LaborContractDTO>> getOwnersMembershipCards(@PathVariable int id){
        List<LaborContractDTO> laborContracts = new ArrayList<>();
        laborContractService.findByOwnerId(id)
                .forEach(laborContractEntity -> laborContracts.add(modelMapper.map(laborContractEntity, LaborContractDTO.class)));
        return new ResponseEntity<>(laborContracts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LaborContractDTO> create (@PathVariable(value = "id") int personId, @RequestBody LaborContractDTO laborContract){
        return new ResponseEntity<>(modelMapper.map(laborContractService
                .save(modelMapper.map(laborContract, LaborContractEntity.class), personId), LaborContractDTO.class),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LaborContractDTO> update (@PathVariable(value = "id") int personId, @RequestBody LaborContractDTO membershipCard) {
        return new ResponseEntity<>(modelMapper.map(laborContractService
                .update(modelMapper.map(membershipCard, LaborContractEntity.class), personId), LaborContractDTO.class), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete (@PathVariable(value = "id") int ownerId, @RequestParam("contractId") int id ){
        laborContractService.delete(ownerId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
