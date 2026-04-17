package solis3d.u5w2d5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solis3d.u5w2d5.entities.Prenotazione;

import java.time.LocalDate;
import java.util.UUID;

public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {
    boolean existsByDipendenteIdAndViaggioData(UUID dipendenteId, LocalDate data);
}
