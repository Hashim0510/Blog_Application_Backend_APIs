package com.example.blogapprestapi.payload;


//payload package is used to create the classes which we used to send to a client
//actually we can send a json which is converted from entiyty class to a client, but that is not sscured to send entire onjects,
//we can send whatever requires from a object thats why we are having dto classes separately

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class postDTO {

    private long id;
    //these anootations are used to vlidate the input queries from client using starter validation dependency
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 5, message = "Post description should have at least 5 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<commentDTO> comments;

}
