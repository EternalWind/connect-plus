package io.eternalwind.connectplus.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.factory.Mappers;

import io.eternalwind.connectplus.persistence.dao.User;
import io.eternalwind.connectplus.presentation.viewmodels.GetUserVMs.UserInfo;
import io.eternalwind.connectplus.presentation.viewmodels.LoginVMs.LoggedInUser;
import io.eternalwind.connectplus.presentation.viewmodels.SignUpVMs.NewUser;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = ComponentModel.SPRING)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User fromNewUser(NewUser newUser);

    LoggedInUser toLoggedInUser(User user);

    UserInfo toUserInfo(User user);
}
