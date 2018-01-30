package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJpaOnlineTransaction is a Querydsl query type for JpaOnlineTransaction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaOnlineTransaction extends EntityPathBase<JpaOnlineTransaction> {

    private static final long serialVersionUID = -119137688L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaOnlineTransaction jpaOnlineTransaction = new QJpaOnlineTransaction("jpaOnlineTransaction");

    public final NumberPath<Double> amountPaid = createNumber("amountPaid", Double.class);

    public final NumberPath<Double> amountToPay = createNumber("amountToPay", Double.class);

    public final BooleanPath creditedAccount = createBoolean("creditedAccount");

    public final DateTimePath<java.util.Date> dateInitialized = createDateTime("dateInitialized", java.util.Date.class);

    public final DateTimePath<java.util.Date> datePayed = createDateTime("datePayed", java.util.Date.class);

    public final QJpaPayment paymentId;

    public final StringPath responseCode = createString("responseCode");

    public final StringPath responseMessage = createString("responseMessage");

    public final BooleanPath sucessful = createBoolean("sucessful");

    public final StringPath transactionId = createString("transactionId");

    public QJpaOnlineTransaction(String variable) {
        this(JpaOnlineTransaction.class, forVariable(variable), INITS);
    }

    public QJpaOnlineTransaction(Path<? extends JpaOnlineTransaction> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaOnlineTransaction(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaOnlineTransaction(PathMetadata metadata, PathInits inits) {
        this(JpaOnlineTransaction.class, metadata, inits);
    }

    public QJpaOnlineTransaction(Class<? extends JpaOnlineTransaction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.paymentId = inits.isInitialized("paymentId") ? new QJpaPayment(forProperty("paymentId"), inits.get("paymentId")) : null;
    }

}

