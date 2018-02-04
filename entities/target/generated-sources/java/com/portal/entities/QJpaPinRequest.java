package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJpaPinRequest is a Querydsl query type for JpaPinRequest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaPinRequest extends EntityPathBase<JpaPinRequest> {

    private static final long serialVersionUID = 1652870653L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaPinRequest jpaPinRequest = new QJpaPinRequest("jpaPinRequest");

    public final QJpaAppUser appUserId;

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateUsed = createDateTime("dateUsed", java.util.Date.class);

    public final StringPath pinId = createString("pinId");

    public final BooleanPath used = createBoolean("used");

    public QJpaPinRequest(String variable) {
        this(JpaPinRequest.class, forVariable(variable), INITS);
    }

    public QJpaPinRequest(Path<? extends JpaPinRequest> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaPinRequest(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaPinRequest(PathMetadata metadata, PathInits inits) {
        this(JpaPinRequest.class, metadata, inits);
    }

    public QJpaPinRequest(Class<? extends JpaPinRequest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.appUserId = inits.isInitialized("appUserId") ? new QJpaAppUser(forProperty("appUserId"), inits.get("appUserId")) : null;
    }

}

