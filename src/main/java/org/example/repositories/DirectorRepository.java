package org.example.repositories;

import org.example.models.entities.Director;

import java.util.List;

public interface DirectorRepository extends BaseRepository<Director>{

    List<Director> getByKeyword(String keyword);

}
