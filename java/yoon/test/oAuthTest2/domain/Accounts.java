package yoon.test.oAuthTest2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoon.test.oAuthTest2.enums.Providers;
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

    @Enumerated(EnumType.STRING)
    private Providers provider;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Accounts(String email, String name, Providers provider, Role role){
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.role = role;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
