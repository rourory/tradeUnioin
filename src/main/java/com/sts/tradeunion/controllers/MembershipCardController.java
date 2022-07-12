package com.sts.tradeunion.controllers;

import com.sts.tradeunion.dto.MembershipCardDTO;
import com.sts.tradeunion.entities.docs.MembershipCardEntity;
import com.sts.tradeunion.services.MembershipCardService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<MembershipCardDTO>> getOwnersMembershipCards(@PathVariable int id){
        List<MembershipCardDTO> membershipCards = new ArrayList<>();
        membershipCardService.findByOwnerId(id)
                .forEach(membershipCardEntity -> membershipCards.add(modelMapper.map(membershipCardEntity,MembershipCardDTO.class)));
        return new ResponseEntity<>(membershipCards, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MembershipCardDTO> create (@PathVariable(value = "id") int personId, @RequestBody MembershipCardDTO membershipCard){
        return new ResponseEntity<>(modelMapper.map(membershipCardService.save(modelMapper.map(membershipCard,MembershipCardEntity.class), personId),MembershipCardDTO.class),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MembershipCardDTO> update (@PathVariable(value = "id") int personId, @RequestBody MembershipCardDTO membershipCard) {
        return new ResponseEntity<>(modelMapper.map(membershipCardService.update(modelMapper.map(membershipCard, MembershipCardEntity.class), personId),MembershipCardDTO.class), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete (@PathVariable(value = "id") int ownerId, @RequestParam("cardId") int id ){
        membershipCardService.delete(ownerId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
