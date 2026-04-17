package solis3d.u5w2d5.payloads;

import jakarta.validation.constraints.NotNull;
import solis3d.u5w2d5.entities.StatoViaggio;

public record StatoViaggioDTO(
        @NotNull(message = "Lo stato del viaggio è obbligatorio!")
        StatoViaggio stato
) {
}
