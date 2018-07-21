package com.szczudlinski.dawid.offer.offer.rest;

import com.szczudlinski.dawid.offer.offer.domain.Insurance;
import com.szczudlinski.dawid.offer.offer.domain.Offer;
import com.szczudlinski.dawid.offer.offer.domain.OfferRepository;
import com.szczudlinski.dawid.offer.offer.domain.Person;
import com.szczudlinski.dawid.offer.offer.dto.MakeOfferCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfferRest {

    private OfferRepository offerRepository;

    @Autowired
    public OfferRest(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<Offer> getOffers(Pageable pageRequest) {
        return offerRepository.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String makeOffer(@RequestBody MakeOfferCommand makeOfferCommand){
        Offer offer = new Offer();
        offer.setDuration(makeOfferCommand.getDuration());
        offer.setFrequency(makeOfferCommand.getFrequency());
        offer.setOfferNumber("");
        offer.setStartDate(makeOfferCommand.getStartDate());

        Person person = new Person();
        person.setFirstName(makeOfferCommand.getFirstName());
        person.setLastName(makeOfferCommand.getLastName());
        person.setPhoneNumber(makeOfferCommand.getPhoneNumber());
        person.setEmail(makeOfferCommand.getEmail());
        person.setBirthDate(makeOfferCommand.getBirthDate());

        List personList = new ArrayList<Person> ();
        personList.add(person);

        offer.setPersonList(personList);

        List insuranceList = makeOfferCommand.getInsuranceList().stream()
                .map(insuranceDTO -> {
                    Insurance insurance = new Insurance();
                    insurance.setBaseProduct(insuranceDTO.getBaseProduct());
                    insurance.setCode(insuranceDTO.getCode());
                    insurance.setSum(insuranceDTO.getSum());
                    return insurance;
                }).collect(Collectors.toList());

        offer.setInsuranceList(insuranceList);

        offerRepository.save(offer);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String addOffer(@RequestBody Offer offer){
        offerRepository.save(offer);
        return "OK";
    }

    @RequestMapping(value = "/{offerNumber}", method = RequestMethod.PUT)
    @ResponseBody
    public String saveOffer(@RequestBody Offer offer, @PathVariable String offerNumber){
        offerRepository.save(offer);
        return "OK";
    }

    @RequestMapping(value = "/{offerNumber}", method = RequestMethod.GET)
    @ResponseBody
    public Offer getOffer(@PathVariable String offerNumber) {
        Optional<Offer> offer = offerRepository.getByOfferNumber(offerNumber);
        return offer.get();
    }

}