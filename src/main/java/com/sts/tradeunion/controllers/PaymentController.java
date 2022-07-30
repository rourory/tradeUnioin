package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.PaymentDTO;
import com.sts.tradeunion.entities.docs.PaymentEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.services.PaymentServiceImpl;
import com.sts.tradeunion.util.validation.PaymentValidator;
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
@RequestMapping("/people/{id}/payments")
public class PaymentController {

    private final ModelMapper modelMapper;
    private final PaymentServiceImpl paymentService;
    private final PaymentValidator paymentValidator;

    public PaymentController(ModelMapper modelMapper, PaymentServiceImpl paymentService, PaymentValidator paymentValidator) {
        this.modelMapper = modelMapper;
        this.paymentService = paymentService;
        this.paymentValidator = paymentValidator;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<List<PaymentDTO>> getOwnersMembershipCards(@PathVariable int id) {
        List<PaymentDTO> payments = new ArrayList<>();
        paymentService.findByOwnerId(id)
                .forEach(payment -> payments.add(modelMapper.map(payment, PaymentDTO.class)));
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<PaymentDTO> create(@PathVariable(value = "id") int personId,
                                             @RequestBody @Valid PaymentDTO payment, BindingResult bindingResult) {
        paymentValidator.validate(payment, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, payment);
        return new ResponseEntity<>(modelMapper.map(paymentService
                .save(modelMapper.map(payment, PaymentEntity.class), personId), PaymentDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<PaymentDTO> update(@PathVariable(value = "id") int personId,
                                             @RequestBody @Valid PaymentDTO payment, BindingResult bindingResult) {
        paymentValidator.validate(payment, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, payment);
        return new ResponseEntity<>(modelMapper.map(paymentService
                .update(modelMapper.map(payment, PaymentEntity.class), personId), PaymentDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") int ownerId, @RequestParam("paymentId") int id) {
        paymentService.delete(ownerId,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
