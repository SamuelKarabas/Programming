package sk.karabas.myCode.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Entity
public class UserGroup extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String groupName;
    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users;

    public UserGroup() {
    }

    public UserGroup(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public static UserGroup find(SessionFactory sessionFactory, Integer id) {
        return find(UserGroup.class, sessionFactory, id);
    }

    public static List<UserGroup> all(SessionFactory sessionFactory) {
        return all(UserGroup.class, sessionFactory);
    }

    public static UserGroup first(SessionFactory sessionFactory) {
        return first(UserGroup.class, sessionFactory);
    }

    public static Stream<UserGroup> stream(SessionFactory sessionFactory) {
        return stream(UserGroup.class, sessionFactory);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
