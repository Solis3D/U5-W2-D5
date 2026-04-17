package solis3d.u5w2d5.services;

import org.springframework.stereotype.Service;
import solis3d.u5w2d5.entities.Viaggio;
import solis3d.u5w2d5.exceptions.NotFoundException;
import solis3d.u5w2d5.payloads.StatoViaggioDTO;
import solis3d.u5w2d5.payloads.ViaggioDTO;
import solis3d.u5w2d5.repositories.ViaggiRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ViaggiService {
    private final ViaggiRepository viaggiRepository;

    public ViaggiService(ViaggiRepository viaggiRepository) {
        this.viaggiRepository = viaggiRepository;
    }

    public Viaggio saveNewViaggio(ViaggioDTO body) {
        Viaggio newViaggio = new Viaggio(body.destinazione(), body.data());

        return viaggiRepository.save(newViaggio);
    }

    public List<Viaggio> findAll() {
        return this.viaggiRepository.findAll();
    }

    public Viaggio findById(UUID viaggioId) {
        return this.viaggiRepository.findById(viaggioId).orElseThrow(() -> new NotFoundException(viaggioId));
    }

    public Viaggio findByIdAndUpdate(UUID viaggioId, ViaggioDTO body) {
        Viaggio trovato = this.findById(viaggioId);

        trovato.setDestinazione(body.destinazione());
        trovato.setData(body.data());

        return this.viaggiRepository.save(trovato);
    }

    public void findByIdAndDelete(UUID viaggioId) {
        Viaggio trovato = this.findById(viaggioId);
        this.viaggiRepository.delete(trovato);
    }

    public Viaggio updateStato(UUID viaggioId, StatoViaggioDTO body) {
        Viaggio trovato = this.findById(viaggioId);

        trovato.setStato(body.stato());

        return this.viaggiRepository.save(trovato);
    }
}
