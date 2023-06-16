package br.com.barberShop.repository;

import br.com.barberShop.entity.ServiceEntity;
import br.com.barberShop.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

}
