package com.portal.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJpaManualTransaction is a Querydsl query type for JpaManualTransaction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaManualTransaction extends EntityPathBase<JpaManualTransaction> {

    private static final long serialVersionUID = 1182438165L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaManualTransaction jpaManualTransaction = new QJpaManualTransaction("jpaManualTransaction");

    public final NumberPath<Double> amountPaid = createNumber("amountPaid", Double.class);

    public final StringPath bankName = createString("bankName");

    public final StringPath bankTeller = createString("bankTeller");

    public final BooleanPath claimed = createBoolean("claimed");

    public final QJpaAppUser createdByAppUserId;

    public final BooleanPath creditedAccount = createBoolean("creditedAccount");

    public final DateTimePath<java.util.Date> dateClaimed = createDateTime("dateClaimed", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final DateTimePath<java.util.Date> datePaid = createDateTime("datePaid", java.util.Date.class);

    public final QJpaPayment paymentId;

    public final StringPath processingMessage = createString("processingMessage");

    public final StringPath receipt = createString("receipt");

    public final StringPath registrationNumber = createString("registrationNumber");

    public final StringPath transactionId = createString("transactionId");

    public QJpaManualTransaction(String variable) {
        this(JpaManualTransaction.class, forVariable(variable), INITS);
    }

    public QJpaManualTransaction(Path<? extends JpaManualTransaction> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaManualTransaction(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJpaManualTransaction(PathMetadata metadata, PathInits inits) {
        this(JpaManualTransaction.class, metadata, inits);
    }

    public QJpaManualTransaction(Class<? extends JpaManualTransaction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdByAppUserId = inits.isInitialized("createdByAppUserId") ? new QJpaAppUser(forProperty("createdByAppUserId"), inits.get("createdByAppUserId")) : null;
        this.paymentId = inits.isInitialized("paymentId") ? new QJpaPayment(forProperty("paymentId"), inits.get("paymentId")) : null;
    }

}

