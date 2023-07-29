package io.eternalwind.connectplus.persistence.dao;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collectionName = "connect_plus_forum_memberships")
public class ForumMembership {
    @DocumentId
    String id;
    String forumId;
    String memberId;
}
