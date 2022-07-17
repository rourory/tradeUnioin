package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.PaymentDTO;
import com.sts.tradeunion.entities.docs.PaymentEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.services.PaymentService;
import com.sts.tradeunion.util.validation.PaymentValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people/{id}/payments")
public class PaymentController {

    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final PaymentValidator paymentValidator;

    public PaymentController(ModelMapper modelMapper, PaymentService paymentService, PaymentValidator paymentValidator) {
        this.modelMapper = modelMapper;
        this.paymentService = paymentService;
        this.paymentValidator = paymentValidator;
    }


    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getOwnersMembershipCards(@PathVariable int id) {
        List<PaymentDTO> payments = new ArrayList<>();
        paymentService.findByOwnerId(id).stream()
                .forEach(payment -> payments.add(modelMapper.map(payment, PaymentDTO.class)));
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@PathVariable(value = "id") int personId,
                                             @RequestBody @Valid PaymentDTO payment, BindingResult bindingResult) {
        paymentValidator.validate(payment, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, payment);
        return new ResponseEntity<>(modelMapper.map(paymentService
                .save(modelMapper.map(payment, PaymentEntity.class), personId), PaymentDTO.class), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PaymentDTO> update(@PathVariable(value = "id") int personId,
                                             @RequestBody @Valid PaymentDTO payment, BindingResult bindingResult) {
        paymentValidator.validate(payment, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, payment);
        return new ResponseEntity<>(modelMapper.map(paymentService
                .update(modelMapper.map(payment, PaymentEntity.class), personId), PaymentDTO.class), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") int ownerId, @RequestParam("paymentId") int id) {
        paymentService.delete(ownerId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
