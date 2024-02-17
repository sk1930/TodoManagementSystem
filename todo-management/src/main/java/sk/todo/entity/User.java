package sk.todo.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;


    /*Well, fetch type eager means whenever we load user entity along with that it will also load its roles.

Next let's specify Cascade type All. it means if you look at all it has different operations like persist, merge, remove, refresh, detach.
So whenever we perform any action on parent, it also applicable to its child's.

That's why we have given cascade all it means whenever we save user, it will also save its roles because
user is a parent and roles are a child.

Okay, next let's use @JoinTable annotation to create the join table.Next, let's give a third table name as users_roles.


So let's use its name attribute and let's use column name as user_id and this column basically
references from the primary key that is ID.

we need to define one more foreign key in this users_roles table that is role_id.
So for that here, let's use the attribute inverseJoinColumns and let's use again @JoinColumn annotation.
     */

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;




    /*Hibernate: create table roles (id binary(16) not null, name varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table users (id binary(16) not null, email varchar(255) not null, name varchar(255), password varchar(255) not null, username varchar(255) not null, primary key (id)) engine=InnoDB
Hibernate: create table users_roles (user_id binary(16) not null, role_id binary(16) not null, primary key (user_id, role_id)) engine=InnoDB
Hibernate: alter table users drop index UK_6dotkott2kjsp8vw4d0m25fb7
Hibernate: alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
Hibernate: alter table users drop index UK_r43af9ap4edm43mmtq01oddj6
Hibernate: alter table users add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)
Hibernate: alter table users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles (id)
Hibernate: alter table users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users (id)
*/
}