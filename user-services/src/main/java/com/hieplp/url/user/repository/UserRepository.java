package com.hieplp.url.user.repository;

import com.hieplp.url.common.model.UserModel;
import com.hieplp.url.common.repository.base.BaseRepository;

public interface UserRepository extends BaseRepository {
    UserModel getUserModelByUsername(String username);

    UserModel getUserModelByUserId(String userId);

    boolean doesUsernameExist(String username);
}
