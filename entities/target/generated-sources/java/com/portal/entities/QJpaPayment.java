package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJpaPayment is a Querydsl query type for JpaPayment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaPayment extends EntityPathBase<JpaPayment> {

    private static final long serialVersionUID = 1156934179L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaPayment jpaPayment = new QJpaPayment("jpaPayment");

    public final NumberPath<Double> amountPaid = createNumber("amountPaid", Double.class);

    public final NumberPath<Double> amountToPay = createNumber("amountToPay", Double.class);

    public final QJpaAppUser appUserId;

    public final StringPath bankName = createString("bankName");

    public final StringPath bankTeller = createString("bankTeller");

    public final QJpaCategory categoryId;

    public final StringPath certificatePath = createString("certificatePath");

    public final DateTimePath<java.util.Date> dateInitialized = createDateTime("dateInitialized", java.util.Date.class);

    public final DateTimePath<java.util.Date> datePaid = createDateTime("datePaid", java.util.Date.class);

    public final DateTimePath<java.util.Date> expiryDate = createDateTime("expiryDate", java.util.Date.class);

    public final ListPath<JpaManualTransaction, QJpaManualTransaction> jpaManualTransactionList = this.<JpaManualTransaction, QJpaManualTransaction>createList("jpaManualTransactionList", JpaManualTransaction.class, QJpaManualTransaction.class, PathInits.DIRECT2);

    public final ListPath<JpaOnlineTransaction, QJpaOnlineTransaction> jpaOnlineTransactionList = this.<JpaOnlineTransaction, QJpaOnlineTransaction>createList("jpaOnlineTransactionList", JpaOnlineTransaction.class, QJpaOnlineTransaction.class, PathInits.DIRECT2);

    public final BooleanPath paid = createBoolean("paid");

    public final StringPath paymentId = createString("paymentId");

    public final StringPath paymentType = createString("paymentType");

    public QJpaPayment(String variable) {
        this(JpaPayment.class, forVariable(variable), INITS);
    }

    public QJpaPayment(Path<? extends JpaPayment> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaPayment(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaPayment(PathMetadata metadata, PathInits inits) {
        this(JpaPayment.class, metadata, inits);
    }

    public QJpaPayment(Class<? extends JpaPayment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.appUserId = inits.isInitialized("appUserId") ? new QJpaAppUser(forProperty("appUserId"), inits.get("appUserId")) : null;
        this.categoryId = inits.isInitialized("categoryId") ? new QJpaCategory(forProperty("categoryId")) : null;
    }

}

