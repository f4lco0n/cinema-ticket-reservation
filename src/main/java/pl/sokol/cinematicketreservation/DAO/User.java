package pl.sokol.cinematicketreservation.DAO;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String userPassword;
    public User(){}

    public User(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @ManyToMany(mappedBy = "userSet",fetch = FetchType.EAGER)
    private Set<UserRoles> usersRolesSet;

    public Set<UserRoles> getUsersRolesSet() {
        return usersRolesSet;
    }

    public void setUsersRolesSet(Set<UserRoles> usersRolesSet) {
        this.usersRolesSet = usersRolesSet;
    }
}
