package yoon.test.oAuthTest2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoon.test.oAuthTest2.enums.Role;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="account")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String name;

    private String picture;

    private Role role;

    @Builder
    public Accounts(String email, String name, String picture, Role role){
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.role = Role.USER;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
