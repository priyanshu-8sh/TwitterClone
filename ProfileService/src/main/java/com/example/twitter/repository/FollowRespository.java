package com.example.twitter.repository;

import com.example.twitter.entity.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRespository extends MongoRepository<Follow,String> {

    boolean existsByFollowerProfile_IdAndFolloweeProfile_Id(String followerId, String followeeId);
    List<Follow> findAllByFolloweeProfile_Id(String profileId);

    List<Follow> findAllByFollowerProfile_Id(String profileId);

    Optional<Follow> deleteByFollowerProfile_IdAndFolloweeProfile_Id(String followerId, String followeeId);
}
