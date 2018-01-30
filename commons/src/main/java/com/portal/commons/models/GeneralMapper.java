package com.portal.commons.models;

import com.portal.admin.models.ApplicationData;
import com.portal.admin.models.GeneratedForm;
import com.portal.admin.models.GeneratedFormVersion;
import com.portal.admin.models.GeneratedManualTransaction;
import com.portal.admin.models.GeneratedOnliePaymentTransactionRawReponse;
import com.portal.admin.models.GeneratedOnlineTransaction;
import com.portal.admin.models.GeneratedPayment;
import com.portal.commons.util.CycleAvoidingMappingContext;
import com.portal.entities.JpaAppConfig;
import com.portal.entities.JpaAppUser;
import com.portal.entities.JpaApplicationData;
import com.portal.entities.JpaForm;
import com.portal.entities.JpaFormVersion;
import com.portal.entities.JpaManualTransaction;
import com.portal.entities.JpaMessageTemplate;
import com.portal.entities.JpaOnliePaymentTransactionRawReponse;
import com.portal.entities.JpaOnlineTransaction;
import com.portal.entities.JpaPayment;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeneralMapper {

    GeneralMapper INSTANCE = Mappers.getMapper(GeneralMapper.class);

    JpaAppConfig generatedAppConfigToJpaAppConfig(AppConfig model);

    AppConfig jpaAppConfigToGeneratedConfig(JpaAppConfig entity);

    JpaMessageTemplate generatedMessageTemplateToMessageTemplate(MessageTemplate model);

    MessageTemplate jpaMessageTemplateToGeneratedMessageTemplate(JpaMessageTemplate entity);

    @Mapping(
            target = "jpaApplicationData",
            expression = "java(  model.getJpaApplicationData() == null ? null :  generatedApplicationDataToJpaApplicationData(model.getJpaApplicationData(),context) )"
    )
    JpaAppUser generatedAppUserToJpaAppUser(AppUser model,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "jpaApplicationData",
            expression = "java(  entity.getJpaApplicationData() == null ? null : jpaApplicationDataToGeneratedApplicationData(entity.getJpaApplicationData(),context) )"
    )
    AppUser jpaAppUserToGeneratedAppUser(JpaAppUser entity,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "appUserId",
            expression = "java(  model.getAppUserId() == null ? null :  generatedAppUserToJpaAppUser(model.getAppUserId(),context) )"
    )
    JpaPayment generatedPaymentToJpaPayment(GeneratedPayment model,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "appUserId",
            expression = "java(  entity.getAppUserId() == null ? null : jpaAppUserToGeneratedAppUser(entity.getAppUserId(),context) )"
    )
    GeneratedPayment jpaPaymentToGeneratedPayment(JpaPayment entity,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "paymentId",
            expression = "java(  model.getPaymentId() == null ? null :  generatedPaymentToJpaPayment(model.getPaymentId(),context) )"
    )
    JpaOnlineTransaction generatedOnlineTransactionToJpaOnlineTransaction(GeneratedOnlineTransaction model,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "paymentId",
            expression = "java(  entity.getPaymentId() == null ? null : jpaPaymentToGeneratedPayment(entity.getPaymentId(),context) )"
    )
    GeneratedOnlineTransaction jpaOnlineTransactionToGeneratedOnlineTransaction(JpaOnlineTransaction entity,
            @Context CycleAvoidingMappingContext context);

    JpaOnliePaymentTransactionRawReponse generatedOnliePaymentTransactionRawReponseToJpaOnliePaymentTransactionRawReponse(GeneratedOnliePaymentTransactionRawReponse model,
            @Context CycleAvoidingMappingContext context);

    GeneratedOnliePaymentTransactionRawReponse jpaOnliePaymentTransactionRawReponseToGeneratedOnliePaymentTransactionRawReponse(JpaOnliePaymentTransactionRawReponse entity,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "jpaForm",
            expression = "java(  model.getJpaForm() == null ? null :  generatedFormToJpaForm(model.getJpaForm(),context) )"
    )
    JpaFormVersion generatedFormVersionToJpaFormVersion(GeneratedFormVersion model,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "jpaForm",
            expression = "java(  entity.getJpaForm() == null ? null : jpaFormToGeneratedForm(entity.getJpaForm(),context) )"
    )
    GeneratedFormVersion jpaFormVersionToGeneratedFormVersion(JpaFormVersion entity,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "paymentId",
            expression = "java(  model.getPaymentId() == null ? null :  generatedPaymentToJpaPayment(model.getPaymentId(),context) )"
    )
    @Mapping(
            target = "createdByAppUserId",
            expression = "java(  model.getCreatedByAppUserId() == null ? null :  generatedAppUserToJpaAppUser(model.getCreatedByAppUserId(),context) )"
    )
    JpaManualTransaction generatedManualTransactionToJpaManualTransaction(GeneratedManualTransaction model,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "paymentId",
            expression = "java(  entity.getPaymentId() == null ? null : jpaPaymentToGeneratedPayment(entity.getPaymentId(),context) )"
    )
    @Mapping(
            target = "createdByAppUserId",
            expression = "java(  entity.getCreatedByAppUserId() == null ? null : jpaAppUserToGeneratedAppUser(entity.getCreatedByAppUserId(),context) )"
    )
    GeneratedManualTransaction jpaManualTransactionToGeneratedManualTransaction(JpaManualTransaction entity,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "jpaFormVersion",
            expression = "java(  model.getJpaFormVersion() == null ? null :  generatedFormVersionToJpaFormVersion(model.getJpaFormVersion(),context) )"
    )
    JpaForm generatedFormToJpaForm(GeneratedForm model, @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "jpaFormVersion",
            expression = "java(  entity.getJpaFormVersion() == null ? null : jpaFormVersionToGeneratedFormVersion(entity.getJpaFormVersion(),context) )"
    )
    GeneratedForm jpaFormToGeneratedForm(JpaForm entity,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "jpaAppUser",
            expression = "java(  model.getJpaAppUser() == null ? null :  generatedAppUserToJpaAppUser(model.getJpaAppUser(),context) )"
    )
    JpaApplicationData generatedApplicationDataToJpaApplicationData(ApplicationData model,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "jpaAppUser",
            expression = "java(  entity.getJpaAppUser() == null ? null : jpaAppUserToGeneratedAppUser(entity.getJpaAppUser(),context) )"
    )
    ApplicationData jpaApplicationDataToGeneratedApplicationData(JpaApplicationData entity,
            @Context CycleAvoidingMappingContext context);
}
