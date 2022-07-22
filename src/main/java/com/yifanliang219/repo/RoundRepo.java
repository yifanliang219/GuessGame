package com.yifanliang219.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yifanliang219.entity.Round;
import com.yifanliang219.entity.RoundId;

@Repository
public interface RoundRepo extends CrudRepository<Round, RoundId> {

}
