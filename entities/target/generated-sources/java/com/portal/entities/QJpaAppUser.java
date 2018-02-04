package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJpaAppUser is a Querydsl query type for JpaAppUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaAppUser extends EntityPathBase<JpaAppUser> {

    private static final long serialVersionUID = 1149704617L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaAppUser jpaAppUser = new QJpaAppUser("jpaAppUser");

    public final BooleanPath active = createBoolean("active");

    public final StringPath appUserId = createString("appUserId");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateModified = createDateTime("dateModified", java.util.Date.class);

    public final StringPath firstName = createString("firstName");

    public final QJpaApplicationData jpaApplicationData;

    public final ListPath<JpaManualTransaction, QJpaManualTransaction> jpaManualTransactionList = this.<JpaManualTransaction, QJpaManualTransaction>createList("jpaManualTransactionList", JpaManualTransaction.class, QJpaManualTransaction.class, PathInits.DIRECT2);

    public final ListPath<JpaPayment, QJpaPayment> jpaPaymentList = this.<JpaPayment, QJpaPayment>createList("jpaPaymentList", JpaPayment.class, QJpaPayment.class, PathInits.DIRECT2);

    public final ListPath<JpaPinRequest, QJpaPinRequest> jpaPinRequestList = this.<JpaPinRequest, QJpaPinRequest>createList("jpaPinRequestList", JpaPinRequest.class, QJpaPinRequest.class, PathInits.DIRECT2);

    public final StringPath lastName = createString("lastName");

    public final StringPath mobileNumber = createString("mobileNumber");

    public final StringPath modifiedBy = createString("modifiedBy");

    public final StringPath password = createString("password");

    public final DateTimePath<java.util.Date> registrationDate = createDateTime("registrationDate", java.util.Date.class);

    public final StringPath roleName = createString("roleName");

    public final StringPath userName = createString("userName");

    public QJpaAppUser(String variable) {
        this(JpaAppUser.class, forVariable(variable), INITS);
    }

    public QJpaAppUser(Path<? extends JpaAppUser> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaAppUser(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaAppUser(PathMetadata metadata, PathInits inits) {
        this(JpaAppUser.class, metadata, inits);
    }

    public QJpaAppUser(Class<? extends JpaAppUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jpaApplicationData = inits.isInitialized("jpaApplicationData") ? new QJpaApplicationData(forProperty("jpaApplicationData"), inits.get("jpaApplicationData")) : null;
    }

}

