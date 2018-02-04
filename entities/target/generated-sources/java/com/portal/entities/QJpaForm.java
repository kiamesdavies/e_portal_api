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

    public static final QJpaForm jpaForm = new QJpaForm("jpaForm");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateModified = createDateTime("dateModified", java.util.Date.class);

    public final StringPath formDesc = createString("formDesc");

    public final StringPath formId = createString("formId");

    public final StringPath formName = createString("formName");

    public final ListPath<JpaFormVersion, QJpaFormVersion> jpaFormVersionList = this.<JpaFormVersion, QJpaFormVersion>createList("jpaFormVersionList", JpaFormVersion.class, QJpaFormVersion.class, PathInits.DIRECT2);

    public final StringPath modifiedBy = createString("modifiedBy");

    public QJpaForm(String variable) {
        super(JpaForm.class, forVariable(variable));
    }

    public QJpaForm(Path<? extends JpaForm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaForm(PathMetadata metadata) {
        super(JpaForm.class, metadata);
    }

}

