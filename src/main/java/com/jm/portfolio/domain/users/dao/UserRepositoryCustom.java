package com.jm.portfolio.domain.users.dao;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.request.UserModificationRequest;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.global.common.paging.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {

    Long maxUserIdx();

    int countUser();

    Users findByEmail(Email email);

    boolean existsByEmail(Email email);

    boolean existsByNickname(String nickname);

    Page<UserResponse> getUserList(Pageable pageable, SearchCondition searchCondition);

    void modifyNickname(UserModificationRequest modifyInfo);

    void modifyName(UserModificationRequest modifyInfo);

    void modifyBirth(UserModificationRequest modifyInfo);

    void modifyPassword(Users modifyInfo);

    void deleteUserInfo(Users deleteInfo);
}
