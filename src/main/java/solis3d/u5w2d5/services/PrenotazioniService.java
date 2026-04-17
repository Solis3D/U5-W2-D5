package solis3d.u5w2d5.services;

import org.springframework.stereotype.Service;
import solis3d.u5w2d5.entities.Dipendente;
import solis3d.u5w2d5.entities.Prenotazione;
import solis3d.u5w2d5.entities.Viaggio;
import solis3d.u5w2d5.exceptions.BadRequestException;
import solis3d.u5w2d5.exceptions.NotFoundException;
import solis3d.u5w2d5.payloads.PrenotazioneDTO;
import solis3d.u5w2d5.repositories.PrenotazioniRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioniService {

    private final PrenotazioniRepository prenotazioniRepository;
    private final DipendentiService dipendentiService;
    private final ViaggiService viaggiService;

    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository, DipendentiService dipendentiService, ViaggiService viaggiService) {
        this.prenotazioniRepository = prenotazioniRepository;
        this.dipendentiService = dipendentiService;
        this.viaggiService = viaggiService;
    }

    public Prenotazione saveNewPrenotazione(PrenotazioneDTO body) {
        Dipendente dipendente = dipendentiService.findById(body.dipendenteId());
        Viaggio viaggio = viaggiService.findById(body.viaggioId());

        if (this.prenotazioniRepository.existsByDipendenteIdAndViaggioData(dipendente.getId(), viaggio.getData())) {
            throw new BadRequestException("Il dipendente ha già un viaggio pianificato in data " + viaggio.getData());
        }

        Prenotazione newPrenotazione = new Prenotazione(dipendente, viaggio, LocalDate.now(), body.note());

        return this.prenotazioniRepository.save(newPrenotazione);
    }

    List<Prenotazione> findAll() {
        return this.prenotazioniRepository.findAll();
    }

    public Prenotazione findById(UUID prenotazioneId) {
        return this.prenotazioniRepository.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId));
    }

    public void findByIdAndDelete(UUID prenotazioneId) {
        Prenotazione trovata = this.findById(prenotazioneId);
        this.prenotazioniRepository.delete(trovata);
    }
}
