package br.com.barberShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tb_password_reset")
public class PasswordResetEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_password_reset")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @OneToOne(targetEntity = UsersEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_users")
    private UsersEntity id_users;

}
