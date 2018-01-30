package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJpaForm is a Querydsl query type for JpaForm
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaForm extends EntityPathBase<JpaForm> {

    private static final long serialVersionUID = 1205735879L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaForm jpaForm = new QJpaForm("jpaForm");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateModified = createDateTime("dateModified", java.util.Date.class);

    public final StringPath formDesc = createString("formDesc");

    public final StringPath formId = createString("formId");

    public final StringPath formName = createString("formName");

    public final QJpaFormVersion jpaFormVersion;

    public final StringPath modifiedBy = createString("modifiedBy");

    public QJpaForm(String variable) {
        this(JpaForm.class, forVariable(variable), INITS);
    }

    public QJpaForm(Path<? extends JpaForm> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaForm(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaForm(PathMetadata metadata, PathInits inits) {
        this(JpaForm.class, metadata, inits);
    }

    public QJpaForm(Class<? extends JpaForm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jpaFormVersion = inits.isInitialized("jpaFormVersion") ? new QJpaFormVersion(forProperty("jpaFormVersion"), inits.get("jpaFormVersion")) : null;
    }

}

