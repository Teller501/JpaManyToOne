package dk.kea.jpamanytoone.controller;

import dk.kea.jpamanytoone.model.Kommune;
import dk.kea.jpamanytoone.model.Region;
import dk.kea.jpamanytoone.repository.KommuneRepository;
import dk.kea.jpamanytoone.service.ApiServiceGetKommuner;
import dk.kea.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("/getkommuner")
    public List<Kommune> getKommunerInit(){
        List<Kommune> lstKommuner = apiServiceGetKommuner.getKommuner();
        return lstKommuner;
    }

    @GetMapping("/kommuner")
    public List<Kommune> getKommuner(){
        List<Kommune> lstKommuner = kommuneRepository.findAll();
        return lstKommuner;
    }
}
