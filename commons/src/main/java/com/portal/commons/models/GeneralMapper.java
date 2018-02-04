package com.portal.commons.models;

import com.portal.commons.util.CycleAvoidingMappingContext;
import com.portal.entities.JpaAppConfig;
import com.portal.entities.JpaAppUser;
import com.portal.entities.JpaApplicationData;
import com.portal.entities.JpaApplicationSummary;
import com.portal.entities.JpaCategory;
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

    Category jpaCategoryToCategory(JpaCategory category, @Context CycleAvoidingMappingContext context);

    JpaCategory categorytoJpaCategory(Category c, @Context CycleAvoidingMappingContext context);

    ApplicationSummary jpaApplicationSummaryToApplicationSummary(JpaApplicationSummary applicationSummary);

    JpaAppConfig generatedAppConfigToJpaAppConfig(AppConfig model);

    AppConfig jpaAppConfigToGeneratedConfig(JpaAppConfig entity);

    JpaMessageTemplate generatedMessageTemplateToMessageTemplate(MessageTemplate model);

    MessageTemplate jpaMessageTemplateToGeneratedMessageTemplate(JpaMessageTemplate entity);

    
    JpaAppUser generatedAppUserToJpaAppUser(AppUser model,
            @Context CycleAvoidingMappingContext context);

   
    AppUser jpaAppUserToGeneratedAppUser(JpaAppUser entity,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "appUserId",
            expression = "java(  model.getAppUserId() == null ? null :  generatedAppUserToJpaAppUser(model.getAppUserId(),context) )"
    )
    JpaPayment generatedPaymentToJpaPayment(Payment model,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "appUserId",
            expression = "java(  entity.getAppUserId() == null ? null : jpaAppUserToGeneratedAppUser(entity.getAppUserId(),context) )"
    )
    Payment jpaPaymentToGeneratedPayment(JpaPayment entity,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "paymentId",
            expression = "java(  model.getPaymentId() == null ? null :  generatedPaymentToJpaPayment(model.getPaymentId(),context) )"
    )
    JpaOnlineTransaction generatedOnlineTransactionToJpaOnlineTransaction(OnlineTransaction model,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "paymentId",
            expression = "java(  entity.getPaymentId() == null ? null : jpaPaymentToGeneratedPayment(entity.getPaymentId(),context) )"
    )
    OnlineTransaction jpaOnlineTransactionToGeneratedOnlineTransaction(JpaOnlineTransaction entity,
            @Context CycleAvoidingMappingContext context);

    JpaOnliePaymentTransactionRawReponse generatedOnliePaymentTransactionRawReponseToJpaOnliePaymentTransactionRawReponse(OnliePaymentTransactionRawReponse model,
            @Context CycleAvoidingMappingContext context);

    OnliePaymentTransactionRawReponse jpaOnliePaymentTransactionRawReponseToGeneratedOnliePaymentTransactionRawReponse(JpaOnliePaymentTransactionRawReponse entity,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "formId",
            expression = "java(  model.getFormId() == null ? null :  generatedFormToJpaForm(model.getFormId(),context) )"
    )
    JpaFormVersion generatedFormVersionToJpaFormVersion(FormVersion model,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "formId",
            expression = "java(  entity.getFormId() == null ? null : jpaFormToGeneratedForm(entity.getFormId(),context) )"
    )
    FormVersion jpaFormVersionToGeneratedFormVersion(JpaFormVersion entity,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "paymentId",
            expression = "java(  model.getPaymentId() == null ? null :  generatedPaymentToJpaPayment(model.getPaymentId(),context) )"
    )
    @Mapping(
            target = "createdByAppUserId",
            expression = "java(  model.getCreatedByAppUserId() == null ? null :  generatedAppUserToJpaAppUser(model.getCreatedByAppUserId(),context) )"
    )
    JpaManualTransaction generatedManualTransactionToJpaManualTransaction(ManualTransaction model,
            @Context CycleAvoidingMappingContext context);

    @Mapping(
            target = "paymentId",
            expression = "java(  entity.getPaymentId() == null ? null : jpaPaymentToGeneratedPayment(entity.getPaymentId(),context) )"
    )
    @Mapping(
            target = "createdByAppUserId",
            expression = "java(  entity.getCreatedByAppUserId() == null ? null : jpaAppUserToGeneratedAppUser(entity.getCreatedByAppUserId(),context) )"
    )
    ManualTransaction jpaManualTransactionToGeneratedManualTransaction(JpaManualTransaction entity,
            @Context CycleAvoidingMappingContext context);

    JpaForm generatedFormToJpaForm(Form model, @Context CycleAvoidingMappingContext context);

    Form jpaFormToGeneratedForm(JpaForm entity,
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
