package io.eternalwind.connectplus.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingConstants.ComponentModel;

import io.eternalwind.connectplus.persistence.dao.Forum;
import io.eternalwind.connectplus.presentation.viewmodels.ListForumVMs;
import io.eternalwind.connectplus.presentation.viewmodels.CreateForumVMs.CreatingForum;


@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = ComponentModel.SPRING)
public interface ForumMapper extends FirestoreDaoMapper {
    ListForumVMs.Forum toListForumVM(Forum forum);

    Forum fromCreatingForum(CreatingForum creatingForum);
}
