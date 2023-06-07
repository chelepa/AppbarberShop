package br.com.barberShop.repository;

import br.com.barberShop.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {

    Optional<GroupEntity> findById(Long id);
}
