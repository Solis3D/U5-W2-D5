package solis3d.u5w2d5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import solis3d.u5w2d5.entities.Dipendente;
import solis3d.u5w2d5.exceptions.ValidationException;
import solis3d.u5w2d5.payloads.DipendenteDTO;
import solis3d.u5w2d5.payloads.NewDipendenteRespDTO;
import solis3d.u5w2d5.services.DipendentiService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {

    private final DipendentiService dipendentiService;

    public DipendentiController(DipendentiService dipendentiService) {
        this.dipendentiService = dipendentiService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendenteRespDTO saveDipendente(@RequestBody @Validated DipendenteDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();

            throw new ValidationException(errors);
        }

        Dipendente newDipendente = this.dipendentiService.saveNewDipendente(body);
        return new NewDipendenteRespDTO(newDipendente.getId());

    }

    @GetMapping
    public List<Dipendente> getDipendenti() {
        return this.dipendentiService.findALl();
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente getDipendenteById(@PathVariable UUID dipendenteId) {
        return this.dipendentiService.findById(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(@PathVariable UUID dipendenteId, @RequestBody @Validated DipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();

            throw new ValidationException(errors);
        }

        return this.dipendentiService.findByIdAndUpdate(dipendenteId, body);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID dipendenteId) {
        this.dipendentiService.findByIdAndDelete(dipendenteId);
    }

    @PatchMapping("/{dipendenteId}/immagine-profilo")
    public Dipendente uploadImmagineProfilo(@RequestParam("immagineProfilo") MultipartFile file, @PathVariable UUID dipendenteId) {
        return this.dipendentiService.uploadImmagineProfilo(file, dipendenteId);
    }
}
