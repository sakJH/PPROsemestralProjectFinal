package uhk.sakac.recenzentiuhk.model;


import jakarta.persistence.*;

@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(name = "role_name_unique", columnNames = "name"))
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String roleName;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + roleName + '\'' +
                '}';
    }
}
