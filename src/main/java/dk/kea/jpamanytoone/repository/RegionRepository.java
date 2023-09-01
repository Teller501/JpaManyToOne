package dk.kea.jpamanytoone.repository;

import dk.kea.jpamanytoone.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, String> {

}
