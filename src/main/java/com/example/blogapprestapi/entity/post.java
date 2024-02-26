package com.example.blogapprestapi.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


//these are the lombok related annotations to reduce the boilerplate of adding setters, getters, constructors on a class
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//these are the annotations used to convert the normal class into an JPA entity class to create ORM to the database automatically by hibernate
@Entity
@Table(name = "Post", uniqueConstraints = {@UniqueConstraint(columnNames = {"description", "title"})})
public class post {

    //these are the jpa related annotations to allocate primary key for the col and how, whom to generate the primary keys
    //study detail about this("GENERATED VALUE TYPES IN DATA JPA")
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "Id")
    private long id;
    @Column(name = "Description", nullable = false)
    private String description;
    @Column(name = "Title", nullable = false)
    private String title;
    @Column(name = "Content", nullable = false)
    private String content;

    /*
    In post entity we need to mention the attribute where we want store this primary key as foreign key in comment entity
    so that's why we are mappedBy attribute to give the attribute name used in comment entity
    cascade --->> used to when comment entity is deleted or saved or updated, refresh, ---> do or update the same thing in post entity too
    we have several type of values can be given to change the cascade configuration ---> READ ABOUT IT

    **PERSIST --->> when adding new instance of data (parent enitity(post in our case)) into the db, It will save its child entities into the db as well
    HERE persist is changing the state of object instance from transient to persistent(which means when changed to persistent object data will be stored in db as well)
    NOTE : STUDY ABOUT OBJECT STATES IN HIBERNATE

    persist will do following:
            *add new instance into db and change object state from transient to persistent
            *update the already existing data on db

    **MERGE --->>
            *add new instance into db and change object state from transient to persistent
            *update the already existing data on db
            *change object state from detached state to persistent state adn saving into the db

    **REMOVE --->>
            *used to remove child entities from db when parent entity is removed from db

    **REFRESH --->>
            *used to refresh child entities on db when parent entity is refreshed on db

    **DETACH --->>
            *used to move object instance to detached state of child entities when parent entity also moved to detached state

    **ALL --->> IT WILL CONTAINS ALL ABOVE TYPES, IF WE WANT TO USE ALL THE ABOVE, IT WILL DYNAMICALLY WHICH TO USE

    orphanRemoval --->> used to don't consider the SET type comments attribute for all object or data created using post entity if comment entity completely deleted from code
     */
    //when we use cascade in parent, it will update /delete/save in its child entities
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<comment> comments = new HashSet<>();

}
