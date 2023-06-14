package br.com.barberShop.repository;

import br.com.barberShop.entity.PasswordResetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetRepository extends JpaRepository<PasswordResetEntity, Integer> {

    Optional<PasswordResetEntity> findByToken(String token);
}
