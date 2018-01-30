/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.user_management;

import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.entities.JpaAppUser;
import com.portal.entities.QJpaAppUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.inject.Inject;
import javax.inject.Singleton;
import play.db.jpa.JPAApi;

/**
 *
 * @author poseidon
 */
@Singleton
class JpaAppUserRepository {

    @Inject
    JPAApi provider;

    JpaAppUser getJpaAppUser(String username) throws ResourceNotFound {
        QJpaAppUser appUser = QJpaAppUser.jpaAppUser;
        JpaAppUser find = new JPAQueryFactory(provider.em()).selectFrom(appUser).where(appUser.userName.equalsIgnoreCase(username).or(appUser.appUserId.equalsIgnoreCase(username))).fetchOne();
        if (find == null) {
            throw new ResourceNotFound(String.format("No user with %s id", username));
        }
        return find;
    }

}
