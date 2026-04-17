package solis3d.u5w2d5.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "La destinazione è obbligatoria, non può essere vuota!")
        @Size(min = 2, max = 30, message = "La destinazione deve essere compresa tra i 2 e i 30 caratteri!")
        String destinazione,

        @NotNull(message = "La data del viaggio è obbligatoria!")
        @FutureOrPresent(message = "La data del viaggio non può essere nel passato!")
        LocalDate data
) {
}
