package com.mixnix.redditintegration.memes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Meme {
    @Id
    private Long id;
    //todo: remember to validate them before saving
    private String url;
    //no need to save utc of meme because I will be saving this in another table

    //later there will be tags here but for now I dont have anything like that
    //@ManyToMany
    //Set<Tag> tags;
}
