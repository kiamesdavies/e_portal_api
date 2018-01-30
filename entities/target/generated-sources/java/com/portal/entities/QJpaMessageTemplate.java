package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QJpaMessageTemplate is a Querydsl query type for JpaMessageTemplate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaMessageTemplate extends EntityPathBase<JpaMessageTemplate> {

    private static final long serialVersionUID = 801178814L;

    public static final QJpaMessageTemplate jpaMessageTemplate = new QJpaMessageTemplate("jpaMessageTemplate");

    public final StringPath mailTemplate = createString("mailTemplate");

    public final StringPath messageTemplateDescription = createString("messageTemplateDescription");

    public final StringPath messageTemplateId = createString("messageTemplateId");

    public final StringPath messageTemplateName = createString("messageTemplateName");

    public final StringPath smsTemplate = createString("smsTemplate");

    public QJpaMessageTemplate(String variable) {
        super(JpaMessageTemplate.class, forVariable(variable));
    }

    public QJpaMessageTemplate(Path<? extends JpaMessageTemplate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaMessageTemplate(PathMetadata metadata) {
        super(JpaMessageTemplate.class, metadata);
    }

}

