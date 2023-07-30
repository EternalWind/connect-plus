package io.eternalwind.connectplus.persistence.dao;

import com.google.cloud.Timestamp;
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
@Document(collectionName = "connect_plus_forums")
public class Forum {
    @DocumentId
    String id;
    String name;
    String description;
    Timestamp createdOn;
}
