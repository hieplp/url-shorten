package com.hieplp.url.auth.repository.impl;

import com.hieplp.url.auth.repository.UserRepository;
import com.hieplp.url.common.exception.data.QueryException;
import com.hieplp.url.common.model.UserModel;
import com.hieplp.url.common.repository.base.BaseRepositoryImpl;
import com.hieplp.url.common.repository.base.CustomDSLContext;
import lombok.extern.slf4j.Slf4j;

import static com.hieplp.url.common.repository.url.Tables.USER;


@Slf4j
public class UserRepositoryImpl extends BaseRepositoryImpl implements UserRepository {
    @Override
    public UserModel getUserModelByUsername(String username) {
        log.info("Get user model by username: {}", username);
        return fetchOneNotNull(USER, USER.USERNAME.eq(username), UserModel.class);
    }

    @Override
    public UserModel getUserModelByUserId(String userId) {
        log.info("Get user model by user id: {}", userId);
        return fetchOneNotNull(USER, USER.USERID.eq(userId), UserModel.class);
    }

    @Override
    public boolean doesUsernameExist(String username) {
        try (CustomDSLContext context = getDslContext()) {
            log.info("Check if username: {} exists", username);
            return context.fetchExists(USER, USER.USERNAME.eq(username));
        } catch (Exception e) {
            log.error("Error when check if username: {} exists", username, e);
            throw new QueryException(e.getMessage());
        }
    }
}
