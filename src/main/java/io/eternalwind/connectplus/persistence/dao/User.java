package io.eternalwind.connectplus.persistence.dao;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import io.eternalwind.connectplus.domain.models.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collectionName = "connect_plus_users")
public class User {
    @DocumentId
    String id;
    String username;
    String userPk;
    String description;
    String avatarUrl;
    Sex selfBioSex;
    Sex selfPsySex;
}
