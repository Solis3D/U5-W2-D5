package solis3d.u5w2d5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import solis3d.u5w2d5.entities.Viaggio;
import solis3d.u5w2d5.exceptions.ValidationException;
import solis3d.u5w2d5.payloads.NewViaggioRespDTO;
import solis3d.u5w2d5.payloads.StatoViaggioDTO;
import solis3d.u5w2d5.payloads.ViaggioDTO;
import solis3d.u5w2d5.services.ViaggiService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {

    private final ViaggiService viaggiService;

    public ViaggiController(ViaggiService viaggiService) {
        this.viaggiService = viaggiService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewViaggioRespDTO saveViaggio(@RequestBody @Validated ViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();

            throw new ValidationException(errors);
        }

        Viaggio newViaggio = this.viaggiService.saveNewViaggio(body);
        return new NewViaggioRespDTO(newViaggio.getId());
    }

    @GetMapping
    public List<Viaggio> findAll() {
        return this.viaggiService.findAll();
    }

    @GetMapping("/{viaggioId}")
    public Viaggio findById(@PathVariable UUID viaggioId) {
        return this.viaggiService.findById(viaggioId);
    }

    @PutMapping("/{viaggioId}/stato")
    public Viaggio findById(@PathVariable UUID viaggioId, @RequestBody @Validated ViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();

            throw new ValidationException(errors);
        }

        return this.viaggiService.findByIdAndUpdate(viaggioId, body);
    }

    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID viaggioId) {
        this.viaggiService.findByIdAndDelete(viaggioId);
    }

    @PatchMapping("/{viaggioId}/stato")
    public Viaggio updateStato(@PathVariable UUID viaggioId, @RequestBody @Validated StatoViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();

            throw new ValidationException(errors);
        }

        return this.viaggiService.updateStato(viaggioId, body);
    }
}
