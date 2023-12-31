package dk.kea.jpamanytoone.controller;

import dk.kea.jpamanytoone.model.Kommune;
import dk.kea.jpamanytoone.model.Region;
import dk.kea.jpamanytoone.repository.RegionRepository;
import dk.kea.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
public class RegionRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/getregioner")
    public List<Region> getRegionerInit(){
        return apiServiceGetRegioner.getRegioner();
    }

    @GetMapping("/regioner")
    public List<Region> getRegioner(){
        return regionRepository.findAll();
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

    @PostMapping("/regioner")
    public ResponseEntity<Region> postRegion(@RequestBody Region region){
        Region region1 = regionRepository.save(region);
        return ResponseEntity.ok(region1);
    }

    @PutMapping("/regioner/{id}")
    public ResponseEntity<Region> putRegion(@PathVariable("id") String id, @RequestBody Region region){
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isPresent()){
            region.setKode(id);
            regionRepository.save(region);
            return ResponseEntity.ok(region);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

}
