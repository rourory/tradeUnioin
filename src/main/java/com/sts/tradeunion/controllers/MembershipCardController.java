package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.MembershipCardDTO;
import com.sts.tradeunion.repositories.MembershipCardRepository;
import com.sts.tradeunion.services.MembershipCardService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("people/{id}/membership-cards")
public class MembershipCardController {

    private final MembershipCardService membershipCardService;
    private final ModelMapper modelMapper;

    public MembershipCardController(MembershipCardService membershipCardService, ModelMapper modelMapper) {
        this.membershipCardService = membershipCardService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<MembershipCardDTO>> getOwnersMembershipCards(@PathVariable int id){
        List<MembershipCardDTO> membershipCards = new ArrayList<>();
        membershipCardService.findByOwnerId(id).stream()
                .forEach(membershipCardEntity -> membershipCards.add(modelMapper.map(membershipCardEntity,MembershipCardDTO.class)));
        return new ResponseEntity<>(membershipCards, HttpStatus.OK);
    }

}
