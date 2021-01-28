package com.mixnix.redditintegration.domain.memes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends CrudRepository<Meme, Long> {
}