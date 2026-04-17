package solis3d.u5w2d5.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsWIthListDTO(String message, LocalDateTime timestamp, List<String> errors) {
}
