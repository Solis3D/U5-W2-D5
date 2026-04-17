package solis3d.u5w2d5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import solis3d.u5w2d5.entities.Dipendente;
import solis3d.u5w2d5.exceptions.BadRequestException;
import solis3d.u5w2d5.exceptions.NotFoundException;
import solis3d.u5w2d5.payloads.DipendenteDTO;
import solis3d.u5w2d5.repositories.DipendentiRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DipendentiService {

    private final DipendentiRepository dipendentiRepository;
    private final Cloudinary cloudinaryUploader;

    public DipendentiService(DipendentiRepository dipendentiRepository, Cloudinary cloudinaryUploader) {
        this.dipendentiRepository = dipendentiRepository;
        this.cloudinaryUploader = cloudinaryUploader;
    }

    public Dipendente saveNewDipendente(DipendenteDTO body) {
        if (this.dipendentiRepository.existsByUsername(body.username())) {
            throw new BadRequestException("L'username " + body.username() + " è già in uso!");
        }

        if (this.dipendentiRepository.existsByEmail(body.email())) {
            throw new BadRequestException("L'email " + body.email() + " è già in uso!");
        }

        Dipendente newDipendente = new Dipendente(body.username(), body.nome(), body.cognome(), body.email());

        return dipendentiRepository.save(newDipendente);
    }

    public List<Dipendente> findALl() {
        return this.dipendentiRepository.findAll();
    }

    public Dipendente findById(UUID dipendenteId) {
        return this.dipendentiRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException(dipendenteId));
    }

    public Dipendente findByIdAndUpdate(UUID dipendenteId, DipendenteDTO body) {
        Dipendente trovato = this.findById(dipendenteId);

        if (!trovato.getUsername().equals(body.username())) {
            if (this.dipendentiRepository.existsByUsername(body.username())) {
                throw new BadRequestException("L'username " + body.username() + " è già in uso!");
            }
        }

        if (!trovato.getEmail().equals(body.email())) {
            if (this.dipendentiRepository.existsByEmail(body.email())) {
                throw new BadRequestException("L'email " + body.email() + " è già in uso!");
            }
        }

        trovato.setUsername(body.username());
        trovato.setNome(body.nome());
        trovato.setCognome(body.cognome());
        trovato.setEmail(body.email());

        return this.dipendentiRepository.save(trovato);
    }

    public void findByIdAndDelete(UUID dipendenteId) {
        Dipendente trovato = this.findById(dipendenteId);
        this.dipendentiRepository.delete(trovato);
    }

    public Dipendente uploadImmagineProfilo(MultipartFile file, UUID dipendenteId) {
        Dipendente trovato = this.findById(dipendenteId);

        if (file.isEmpty()) {
            throw new BadRequestException("Il file è obbligatorio, non può essere vuoto!");
        }

        if (file.getContentType() == null || !file.getContentType().equals("image/")) {
            throw new BadRequestException("Il file caricato deve essere un'immagine!");
        }

        try {
            Map result = cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            trovato.setImmagineProfilo(url);
            return dipendentiRepository.save(trovato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
