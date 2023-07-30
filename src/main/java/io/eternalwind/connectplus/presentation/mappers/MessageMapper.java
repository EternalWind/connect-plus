package io.eternalwind.connectplus.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.factory.Mappers;

import io.eternalwind.connectplus.persistence.dao.Message;
import io.eternalwind.connectplus.presentation.viewmodels.GetLatestMsgVMs.LatestMessage;
import io.eternalwind.connectplus.presentation.viewmodels.MsgToUserVMs.SentMessage;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = ComponentModel.SPRING)
public interface MessageMapper extends FirestoreDaoMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    SentMessage toSentMessage(Message message);

    LatestMessage toLatestMessage(Message message);
}
