package com.mixnix.redditintegration.domain.memes;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //todo: remember to validate them before saving to database
    private String url;
    //no need to save utc of meme because I will be saving this in another table

    //later there will be tags here but for now I dont have anything like that
    //@ManyToMany
    //Set<Tag> tags;

    public Meme(String url) {
        this.url = url;
    }
}
