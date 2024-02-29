package com.example.twitter.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "follows")
@CompoundIndex(name = "follower_followee_index", def = "{'followerProfile': 1, 'followeeProfile': 1}", unique = true)
public class Follow {
    @Id
    private String id;
    @DBRef
    private Profile followerProfile;
    @DBRef
    private Profile followeeProfile;
    private LocalDateTime followDateTime;
}
