package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QJpaAppConfig is a Querydsl query type for JpaAppConfig
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaAppConfig extends EntityPathBase<JpaAppConfig> {

    private static final long serialVersionUID = 540783008L;

    public static final QJpaAppConfig jpaAppConfig = new QJpaAppConfig("jpaAppConfig");

    public final StringPath appConfigDescription = createString("appConfigDescription");

    public final StringPath appConfigId = createString("appConfigId");

    public final StringPath appConfigName = createString("appConfigName");

    public final StringPath appConfigValue = createString("appConfigValue");

    public final BooleanPath isAvailableToUser = createBoolean("isAvailableToUser");

    public final BooleanPath isCheck = createBoolean("isCheck");

    public final StringPath possibleValues = createString("possibleValues");

    public QJpaAppConfig(String variable) {
        super(JpaAppConfig.class, forVariable(variable));
    }

    public QJpaAppConfig(Path<? extends JpaAppConfig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaAppConfig(PathMetadata metadata) {
        super(JpaAppConfig.class, metadata);
    }

}

