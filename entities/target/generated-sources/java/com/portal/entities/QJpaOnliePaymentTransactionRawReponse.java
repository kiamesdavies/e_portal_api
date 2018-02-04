package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QJpaOnliePaymentTransactionRawReponse is a Querydsl query type for JpaOnliePaymentTransactionRawReponse
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaOnliePaymentTransactionRawReponse extends EntityPathBase<JpaOnliePaymentTransactionRawReponse> {

    private static final long serialVersionUID = -131448028L;

    public static final QJpaOnliePaymentTransactionRawReponse jpaOnliePaymentTransactionRawReponse = new QJpaOnliePaymentTransactionRawReponse("jpaOnliePaymentTransactionRawReponse");

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final StringPath rawResponse = createString("rawResponse");

    public final NumberPath<Integer> rawResponseId = createNumber("rawResponseId", Integer.class);

    public final DateTimePath<java.util.Date> requestReceivedTime = createDateTime("requestReceivedTime", java.util.Date.class);

    public final StringPath url = createString("url");

    public QJpaOnliePaymentTransactionRawReponse(String variable) {
        super(JpaOnliePaymentTransactionRawReponse.class, forVariable(variable));
    }

    public QJpaOnliePaymentTransactionRawReponse(Path<? extends JpaOnliePaymentTransactionRawReponse> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaOnliePaymentTransactionRawReponse(PathMetadata metadata) {
        super(JpaOnliePaymentTransactionRawReponse.class, metadata);
    }

}

