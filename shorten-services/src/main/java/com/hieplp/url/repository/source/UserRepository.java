package com.hieplp.url.repository.source;

import com.hieplp.url.common.model.UserModel;
import com.hieplp.url.repository.base.BaseRepository;

public interface UserRepository extends BaseRepository {
    UserModel getUserModelByUsername(String username);

    UserModel getUserModelByUserId(String userId);

    boolean doesUsernameExist(String username);
}
