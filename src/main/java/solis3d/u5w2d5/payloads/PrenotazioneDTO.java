package solis3d.u5w2d5.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "L'id del dipendente è obbligatorio!")
        UUID dipendenteId,

        @NotNull(message = "L'id del viaggio è obbligatorio!")
        UUID viaggioId,

        @Size(max = 300, message = "Le note non possono superare i 300 caratteri!")
        String note
) {
}
