package dk.kea.jpamanytoone.controller;

import dk.kea.jpamanytoone.model.Kommune;
import dk.kea.jpamanytoone.repository.KommuneRepository;
import dk.kea.jpamanytoone.service.ApiServiceGetKommuner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("/getkommuner")
    public List<Kommune> getKommunerInit(){
        return apiServiceGetKommuner.getKommuner();
    }

    @GetMapping("/kommuner")
    public List<Kommune> getKommuner(){
        return kommuneRepository.findAll();
    }

    @GetMapping("/kommuner/{id}")
    public ResponseEntity<Kommune> getKommuneById(@PathVariable("id") String id){
        Optional<Kommune> kommuneOptional = kommuneRepository.findById(id);
        if (kommuneOptional.isPresent()){
            Kommune kommune = kommuneOptional.get();
            return ResponseEntity.ok(kommune);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/kommuner")
    public ResponseEntity<Kommune> postRegion(@RequestBody Kommune kommune){
        Kommune kommune1 = kommuneRepository.save(kommune);
        return ResponseEntity.ok(kommune1);
    }

    @PutMapping("/kommuner/{id}")
    public ResponseEntity<Kommune> putKommune(@PathVariable("id") String id, @RequestBody Kommune kommune){
        Optional<Kommune> kommuneOptional = kommuneRepository.findById(id);
        if (kommuneOptional.isPresent()){
            kommune.setKode(id);
            kommuneRepository.save(kommune);
            return ResponseEntity.ok(kommune);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
