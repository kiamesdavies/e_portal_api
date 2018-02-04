package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJpaFormVersion is a Querydsl query type for JpaFormVersion
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaFormVersion extends EntityPathBase<JpaFormVersion> {

    private static final long serialVersionUID = -292264687L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaFormVersion jpaFormVersion = new QJpaFormVersion("jpaFormVersion");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final QJpaForm formId;

    public final StringPath formVersionId = createString("formVersionId");

    public final ListPath<JpaApplicationData, QJpaApplicationData> jpaApplicationDataList = this.<JpaApplicationData, QJpaApplicationData>createList("jpaApplicationDataList", JpaApplicationData.class, QJpaApplicationData.class, PathInits.DIRECT2);

    public final StringPath jsonStructure = createString("jsonStructure");

    public final StringPath previewUrl = createString("previewUrl");

    public final StringPath xformStructure = createString("xformStructure");

    public QJpaFormVersion(String variable) {
        this(JpaFormVersion.class, forVariable(variable), INITS);
    }

    public QJpaFormVersion(Path<? extends JpaFormVersion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaFormVersion(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaFormVersion(PathMetadata metadata, PathInits inits) {
        this(JpaFormVersion.class, metadata, inits);
    }

    public QJpaFormVersion(Class<? extends JpaFormVersion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.formId = inits.isInitialized("formId") ? new QJpaForm(forProperty("formId")) : null;
    }

}

