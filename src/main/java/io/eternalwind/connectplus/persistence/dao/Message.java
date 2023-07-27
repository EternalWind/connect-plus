package io.eternalwind.connectplus.persistence.dao;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import io.eternalwind.connectplus.domain.models.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collectionName = "connect_plus_messages")
public class Message {
    @DocumentId
    String id;
    String senderId;
    String receiverId;
    String msg;
    Timestamp timestamp;
    MessageType messageType;
}
