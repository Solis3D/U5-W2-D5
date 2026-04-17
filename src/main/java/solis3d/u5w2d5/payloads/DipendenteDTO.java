package solis3d.u5w2d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotBlank(message = "Username è obbligatorio, non può essere vuoto!")
        @Size(min = 2, max = 30, message = "L'username deve essere compreso tra 2 e 30 caratteri!")
        String username,

        @NotBlank(message = "Il nome è obbligatorio, non può essere vuoto!")
        @Size(min = 2, max = 30, message = "Il nome deve essere compreso tra 2 e 30 caratteri!")
        String nome,

        @NotBlank(message = "Il cognome è obbligatorio, non può essere vuoto!")
        @Size(min = 2, max = 30, message = "Il cognome deve essere compreso tra 2 e 30 caratteri!")
        String cognome,

        @NotBlank(message = "L'email è obbligatoria, non può essere vuota!")
        @Email(message = "L'email inserita non è in un formato valido!")
        String email
) {

}


