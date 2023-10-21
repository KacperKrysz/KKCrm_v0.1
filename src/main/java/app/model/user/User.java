package app.model.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", length = 100)
    private String login;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "email", length = 100)
    private String email;

    @Column
    private String fullName;

//    @OneToMany(mappedBy = "softwarePatron")
//    private List<Client> clients = new ArrayList<>();
//
//    @OneToMany(mappedBy = "softwarePatron")
//    private List<Activity> activities = new ArrayList<>();

}
