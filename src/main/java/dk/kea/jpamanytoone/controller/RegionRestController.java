package dk.kea.jpamanytoone.controller;

import dk.kea.jpamanytoone.model.Kommune;
import dk.kea.jpamanytoone.model.Region;
import dk.kea.jpamanytoone.repository.RegionRepository;
import dk.kea.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class RegionRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/getregioner")
    public List<Region> getRegionerInit(){
        List<Region> lstRegioner = apiServiceGetRegioner.getRegioner();
        return lstRegioner;
    }

    @GetMapping("/regioner")
    public List<Region> getRegioner(){
        List<Region> lstRegioner = regionRepository.findAll();
        return lstRegioner;
    }

    @GetMapping("/kommunenavne/{id}")
    public ResponseEntity<List<String>> getKommuneNavne(@PathVariable("id") String id){
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isPresent()){
            Region region = regionOptional.get();
            Set<Kommune> lstKommuner = region.getKommuner();
            List<String> kommuneNavne = new ArrayList<>();
            for (Kommune kommune : lstKommuner) {
                kommuneNavne.add(kommune.getNavn());
            }
            return ResponseEntity.ok(kommuneNavne);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/regioner/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable("id") String id){
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isPresent()){
            regionRepository.deleteById(id);
            return ResponseEntity.ok("Region slettet");
        } else{
            return ResponseEntity.notFound().build();
        }
    }

}
