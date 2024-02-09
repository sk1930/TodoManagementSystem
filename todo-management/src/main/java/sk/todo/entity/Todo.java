package sk.todo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    */


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "title",nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
    // if we don't specify @Column annotation for this description field then JPA will
    //create a column name as a same as this field name.
    private boolean completed;

    /*Hibernate:
    create table todos (id binary(16) not null, completed bit not null, description varchar(255) not null, title varchar(255) not null, primary key (id)) engine=InnoDB
*/
}