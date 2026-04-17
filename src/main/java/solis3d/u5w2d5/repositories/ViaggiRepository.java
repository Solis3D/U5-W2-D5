package solis3d.u5w2d5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solis3d.u5w2d5.entities.Viaggio;

import java.util.UUID;

public interface ViaggiRepository extends JpaRepository<Viaggio, UUID> {}
