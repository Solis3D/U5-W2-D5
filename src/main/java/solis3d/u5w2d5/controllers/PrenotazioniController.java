package solis3d.u5w2d5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import solis3d.u5w2d5.entities.Prenotazione;
import solis3d.u5w2d5.exceptions.ValidationException;
import solis3d.u5w2d5.payloads.NewPrenotazioneRespDTO;
import solis3d.u5w2d5.payloads.PrenotazioneDTO;
import solis3d.u5w2d5.services.PrenotazioniService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    private final PrenotazioniService prenotazioniService;

    public PrenotazioniController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewPrenotazioneRespDTO savePrenotazione(@RequestBody @Validated PrenotazioneDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();

            throw new ValidationException(errors);
        }

        Prenotazione newPrenotazione = this.prenotazioniService.saveNewPrenotazione(body);
        return new NewPrenotazioneRespDTO(newPrenotazione.getId());
    }

    @GetMapping
    public List<Prenotazione> findAll() {
        return this.prenotazioniService.findAll();
    }

    @GetMapping("/{prenotazioneId}")
    public Prenotazione findById(@PathVariable UUID prenotazioneId) {
        return this.prenotazioniService.findById(prenotazioneId);
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID prenotazioneId) {
        this.prenotazioniService.findByIdAndDelete(prenotazioneId);
    }
}
