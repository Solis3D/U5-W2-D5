package solis3d.u5w2d5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solis3d.u5w2d5.entities.Dipendente;

import java.util.UUID;

public interface DipendentiRepository extends JpaRepository<Dipendente, UUID> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
