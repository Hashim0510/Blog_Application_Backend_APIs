package com.example.blogapprestapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Comment")
public class comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    private String name;
    private String email;
    private String commentBody;

    /*
    relationship mappings are used to map one entity with another entity like however we want.
    different relationship annotations are there like oneToMany, ManyToOne, OneToOne, ManyToMany, ManyToAny, etc.,
    This annotation has some important methods to improve the performance of way of fetching relationship entities.
    fetch is on eof them, FETCH ---->>> method used to how and when to fetch the related entities which mapped
    It has several types ----> LAZY fetching, EAGER fetching, BATCH fetching, SUB-SELECT fetching
    **LAZY --->> used to fetch the related entity lazily, which means It does not fetch or load the post entity data from db or hibernate does not create queries to fetch post related entities
    Therefore database loading will be optimised, performance improved by fetching comment related entities, It will fetch post entities when post object instantiates
    **EAGER --->> used to fetch the related entity along with the own entity, It will load or fetch the related entity queries and own entity queries to fetch the data from db
    It will have performance decrement due to the increase of database load time
    BASED ON THE USER CASE AND NEED ---> DECIDE WHICHEVER FETCH WE WANT
    Note --> study about other fetching

    */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)  //this annotation is used to tell JPA to use post entity pirmary key as foreign key in comment entity
    private post post;

}

