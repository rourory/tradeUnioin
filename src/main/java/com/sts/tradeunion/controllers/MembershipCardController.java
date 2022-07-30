package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.MembershipCardDTO;
import com.sts.tradeunion.entities.docs.MembershipCardEntity;
import com.sts.tradeunion.exceptions.EntityIsNotValidException;
import com.sts.tradeunion.services.MembershipCardServiceImpl;
import com.sts.tradeunion.util.validation.MembershipCardValidator;
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
@RequestMapping("people/{id}/membership-cards")
public class MembershipCardController {

    private final MembershipCardServiceImpl membershipCardService;
    private final ModelMapper modelMapper;
    private final MembershipCardValidator membershipCardValidator;

    public MembershipCardController(MembershipCardServiceImpl membershipCardService, ModelMapper modelMapper, MembershipCardValidator membershipCardValidator) {
        this.membershipCardService = membershipCardService;
        this.modelMapper = modelMapper;
        this.membershipCardValidator = membershipCardValidator;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<List<MembershipCardDTO>> getOwnersMembershipCards(@PathVariable int id) {
        List<MembershipCardDTO> membershipCards = new ArrayList<>();
        membershipCardService.findByOwnerId(id)
                .forEach(membershipCardEntity -> membershipCards.add(modelMapper.map(membershipCardEntity, MembershipCardDTO.class)));
        return new ResponseEntity<>(membershipCards, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<MembershipCardDTO> create(@PathVariable(value = "id") int personId,
                                                    @RequestBody @Valid MembershipCardDTO membershipCard, BindingResult bindingResult) {
        membershipCardValidator.validate(membershipCard, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, membershipCard);
        return new ResponseEntity<>(modelMapper.map(membershipCardService
                .save(modelMapper.map(membershipCard, MembershipCardEntity.class), personId), MembershipCardDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<MembershipCardDTO> update(@PathVariable(value = "id") int personId,
                                                    @RequestBody @Valid MembershipCardDTO membershipCard, BindingResult bindingResult) {
        membershipCardValidator.validate(membershipCard, bindingResult);
        if (bindingResult.hasErrors()) throw new EntityIsNotValidException(bindingResult, membershipCard);
        return new ResponseEntity<>(modelMapper.map(membershipCardService
                .update(modelMapper.map(membershipCard, MembershipCardEntity.class), personId), MembershipCardDTO.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header"
            , dataTypeClass = String.class, example = "Bearer XXX_access_token")
    public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") int ownerId, @RequestParam("cardId") int id) {
        membershipCardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
