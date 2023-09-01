package dk.kea.jpamanytoone.repository;

import dk.kea.jpamanytoone.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KommuneRepository extends JpaRepository<Kommune, String> {

}
