package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJpaApplicationData is a Querydsl query type for JpaApplicationData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaApplicationData extends EntityPathBase<JpaApplicationData> {

    private static final long serialVersionUID = 75697207L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaApplicationData jpaApplicationData = new QJpaApplicationData("jpaApplicationData");

    public final StringPath appUserId = createString("appUserId");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final StringPath formData = createString("formData");

    public final QJpaFormVersion formVersionId;

    public final QJpaAppUser jpaAppUser;

    public final StringPath xmlFormData = createString("xmlFormData");

    public QJpaApplicationData(String variable) {
        this(JpaApplicationData.class, forVariable(variable), INITS);
    }

    public QJpaApplicationData(Path<? extends JpaApplicationData> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaApplicationData(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaApplicationData(PathMetadata metadata, PathInits inits) {
        this(JpaApplicationData.class, metadata, inits);
    }

    public QJpaApplicationData(Class<? extends JpaApplicationData> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.formVersionId = inits.isInitialized("formVersionId") ? new QJpaFormVersion(forProperty("formVersionId"), inits.get("formVersionId")) : null;
        this.jpaAppUser = inits.isInitialized("jpaAppUser") ? new QJpaAppUser(forProperty("jpaAppUser"), inits.get("jpaAppUser")) : null;
    }

}

