package ryan.phan.starter.entity;

import lombok.Getter;
import lombok.Setter;
import ryan.phan.starter.constant.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = User.TABLE_NAME)
public class User implements Serializable {
    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String name;
    private String password;
    private Role role;
}
