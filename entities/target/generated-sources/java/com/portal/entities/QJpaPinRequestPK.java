package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QJpaPinRequestPK is a Querydsl query type for JpaPinRequestPK
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QJpaPinRequestPK extends BeanPath<JpaPinRequestPK> {

    private static final long serialVersionUID = -729199432L;

    public static final QJpaPinRequestPK jpaPinRequestPK = new QJpaPinRequestPK("jpaPinRequestPK");

    public final StringPath appUserId = createString("appUserId");

    public final StringPath pinId = createString("pinId");

    public QJpaPinRequestPK(String variable) {
        super(JpaPinRequestPK.class, forVariable(variable));
    }

    public QJpaPinRequestPK(Path<? extends JpaPinRequestPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaPinRequestPK(PathMetadata metadata) {
        super(JpaPinRequestPK.class, metadata);
    }

}

