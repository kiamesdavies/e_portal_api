package com.portal.commons.models;

import com.portal.admin.models.AppUser;

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

import javax.annotation.Generated;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2018-01-30T13:52:29+0100",

    comments = "version: 1.2.0.Beta2, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"

)

public class GeneralMapperImpl implements GeneralMapper {

    @Override

    public JpaAppConfig generatedAppConfigToJpaAppConfig(AppConfig model) {

        if ( model == null ) {

            return null;
        }

        JpaAppConfig jpaAppConfig = new JpaAppConfig();

        jpaAppConfig.setAppConfigId( model.getAppConfigId() );

        jpaAppConfig.setAppConfigValue( model.getAppConfigValue() );

        jpaAppConfig.setAppConfigName( model.getAppConfigName() );

        jpaAppConfig.setIsAvailableToUser( model.isIsAvailableToUser() );

        jpaAppConfig.setIsCheck( model.isIsCheck() );

        jpaAppConfig.setPossibleValues( model.getPossibleValues() );

        jpaAppConfig.setAppConfigDescription( model.getAppConfigDescription() );

        return jpaAppConfig;
    }

    @Override

    public AppConfig jpaAppConfigToGeneratedConfig(JpaAppConfig entity) {

        if ( entity == null ) {

            return null;
        }

        AppConfig appConfig = new AppConfig();

        appConfig.setAppConfigId( entity.getAppConfigId() );

        appConfig.setIsAvailableToUser( entity.getIsAvailableToUser() );

        appConfig.setAppConfigValue( entity.getAppConfigValue() );

        appConfig.setIsCheck( entity.getIsCheck() );

        appConfig.setPossibleValues( entity.getPossibleValues() );

        appConfig.setAppConfigName( entity.getAppConfigName() );

        appConfig.setAppConfigDescription( entity.getAppConfigDescription() );

        return appConfig;
    }

    @Override

    public JpaMessageTemplate generatedMessageTemplateToMessageTemplate(MessageTemplate model) {

        if ( model == null ) {

            return null;
        }

        JpaMessageTemplate jpaMessageTemplate = new JpaMessageTemplate();

        jpaMessageTemplate.setMessageTemplateId( model.getMessageTemplateId() );

        jpaMessageTemplate.setMessageTemplateName( model.getMessageTemplateName() );

        jpaMessageTemplate.setMessageTemplateDescription( model.getMessageTemplateDescription() );

        jpaMessageTemplate.setMailTemplate( model.getMailTemplate() );

        jpaMessageTemplate.setSmsTemplate( model.getSmsTemplate() );

        return jpaMessageTemplate;
    }

    @Override

    public MessageTemplate jpaMessageTemplateToGeneratedMessageTemplate(JpaMessageTemplate entity) {

        if ( entity == null ) {

            return null;
        }

        MessageTemplate messageTemplate = new MessageTemplate();

        messageTemplate.setMessageTemplateId( entity.getMessageTemplateId() );

        messageTemplate.setMessageTemplateName( entity.getMessageTemplateName() );

        messageTemplate.setMessageTemplateDescription( entity.getMessageTemplateDescription() );

        messageTemplate.setMailTemplate( entity.getMailTemplate() );

        messageTemplate.setSmsTemplate( entity.getSmsTemplate() );

        return messageTemplate;
    }

    @Override

    public JpaAppUser generatedAppUserToJpaAppUser(AppUser model, CycleAvoidingMappingContext context) {

        JpaAppUser target = context.getMappedInstance( model, JpaAppUser.class );

        if ( target != null ) {

            return target;
        }

        if ( model == null ) {

            return null;
        }

        JpaAppUser jpaAppUser = new JpaAppUser();

        context.storeMappedInstance( model, jpaAppUser );

        jpaAppUser.setAppUserId( model.getAppUserId() );

        jpaAppUser.setFirstName( model.getFirstName() );

        jpaAppUser.setLastName( model.getLastName() );

        jpaAppUser.setUserName( model.getUserName() );

        jpaAppUser.setPassword( model.getPassword() );

        jpaAppUser.setMobileNumber( model.getMobileNumber() );

        jpaAppUser.setDateCreated( model.getDateCreated() );

        jpaAppUser.setCreatedBy( model.getCreatedBy() );

        jpaAppUser.setDateModified( model.getDateModified() );

        jpaAppUser.setModifiedBy( model.getModifiedBy() );

        jpaAppUser.setRoleName( model.getRoleName() );

        jpaAppUser.setActive( model.getActive() );

        jpaAppUser.setJpaApplicationData( model.getJpaApplicationData() == null ? null :  generatedApplicationDataToJpaApplicationData(model.getJpaApplicationData(),context) );

        return jpaAppUser;
    }

    @Override

    public AppUser jpaAppUserToGeneratedAppUser(JpaAppUser entity, CycleAvoidingMappingContext context) {

        AppUser target = context.getMappedInstance( entity, AppUser.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        AppUser appUser = new AppUser();

        context.storeMappedInstance( entity, appUser );

        appUser.setAppUserId( entity.getAppUserId() );

        appUser.setFirstName( entity.getFirstName() );

        appUser.setLastName( entity.getLastName() );

        appUser.setUserName( entity.getUserName() );

        appUser.setPassword( entity.getPassword() );

        appUser.setMobileNumber( entity.getMobileNumber() );

        appUser.setDateCreated( entity.getDateCreated() );

        appUser.setCreatedBy( entity.getCreatedBy() );

        appUser.setDateModified( entity.getDateModified() );

        appUser.setModifiedBy( entity.getModifiedBy() );

        appUser.setRoleName( entity.getRoleName() );

        appUser.setActive( entity.getActive() );

        appUser.setJpaApplicationData( entity.getJpaApplicationData() == null ? null : jpaApplicationDataToGeneratedApplicationData(entity.getJpaApplicationData(),context) );

        return appUser;
    }

    @Override

    public JpaPayment generatedPaymentToJpaPayment(GeneratedPayment model, CycleAvoidingMappingContext context) {

        JpaPayment target = context.getMappedInstance( model, JpaPayment.class );

        if ( target != null ) {

            return target;
        }

        if ( model == null ) {

            return null;
        }

        JpaPayment jpaPayment = new JpaPayment();

        context.storeMappedInstance( model, jpaPayment );

        jpaPayment.setPaymentId( model.getPaymentId() );

        jpaPayment.setDateInitialized( model.getDateInitialized() );

        jpaPayment.setDatePaid( model.getDatePaid() );

        jpaPayment.setExpiryDate( model.getExpiryDate() );

        jpaPayment.setPaid( model.getPaid() );

        jpaPayment.setAmountPaid( model.getAmountPaid() );

        jpaPayment.setAppUserId( model.getAppUserId() == null ? null :  generatedAppUserToJpaAppUser(model.getAppUserId(),context) );

        return jpaPayment;
    }

    @Override

    public GeneratedPayment jpaPaymentToGeneratedPayment(JpaPayment entity, CycleAvoidingMappingContext context) {

        GeneratedPayment target = context.getMappedInstance( entity, GeneratedPayment.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        GeneratedPayment generatedPayment = new GeneratedPayment();

        context.storeMappedInstance( entity, generatedPayment );

        generatedPayment.setPaymentId( entity.getPaymentId() );

        generatedPayment.setDateInitialized( entity.getDateInitialized() );

        generatedPayment.setDatePaid( entity.getDatePaid() );

        generatedPayment.setExpiryDate( entity.getExpiryDate() );

        generatedPayment.setPaid( entity.getPaid() );

        generatedPayment.setAmountPaid( entity.getAmountPaid() );

        generatedPayment.setAppUserId( entity.getAppUserId() == null ? null : jpaAppUserToGeneratedAppUser(entity.getAppUserId(),context) );

        return generatedPayment;
    }

    @Override

    public JpaOnlineTransaction generatedOnlineTransactionToJpaOnlineTransaction(GeneratedOnlineTransaction model, CycleAvoidingMappingContext context) {

        JpaOnlineTransaction target = context.getMappedInstance( model, JpaOnlineTransaction.class );

        if ( target != null ) {

            return target;
        }

        if ( model == null ) {

            return null;
        }

        JpaOnlineTransaction jpaOnlineTransaction = new JpaOnlineTransaction();

        context.storeMappedInstance( model, jpaOnlineTransaction );

        jpaOnlineTransaction.setTransactionId( model.getTransactionId() );

        jpaOnlineTransaction.setDateInitialized( model.getDateInitialized() );

        jpaOnlineTransaction.setAmountToPay( model.getAmountToPay() );

        jpaOnlineTransaction.setAmountPaid( model.getAmountPaid() );

        jpaOnlineTransaction.setDatePayed( model.getDatePayed() );

        jpaOnlineTransaction.setSucessful( model.getSucessful() );

        jpaOnlineTransaction.setCreditedAccount( model.getCreditedAccount() );

        jpaOnlineTransaction.setResponseMessage( model.getResponseMessage() );

        jpaOnlineTransaction.setResponseCode( model.getResponseCode() );

        jpaOnlineTransaction.setPaymentId( model.getPaymentId() == null ? null :  generatedPaymentToJpaPayment(model.getPaymentId(),context) );

        return jpaOnlineTransaction;
    }

    @Override

    public GeneratedOnlineTransaction jpaOnlineTransactionToGeneratedOnlineTransaction(JpaOnlineTransaction entity, CycleAvoidingMappingContext context) {

        GeneratedOnlineTransaction target = context.getMappedInstance( entity, GeneratedOnlineTransaction.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        GeneratedOnlineTransaction generatedOnlineTransaction = new GeneratedOnlineTransaction();

        context.storeMappedInstance( entity, generatedOnlineTransaction );

        generatedOnlineTransaction.setTransactionId( entity.getTransactionId() );

        generatedOnlineTransaction.setDateInitialized( entity.getDateInitialized() );

        generatedOnlineTransaction.setAmountToPay( entity.getAmountToPay() );

        generatedOnlineTransaction.setAmountPaid( entity.getAmountPaid() );

        generatedOnlineTransaction.setDatePayed( entity.getDatePayed() );

        generatedOnlineTransaction.setSucessful( entity.getSucessful() );

        generatedOnlineTransaction.setCreditedAccount( entity.getCreditedAccount() );

        generatedOnlineTransaction.setResponseMessage( entity.getResponseMessage() );

        generatedOnlineTransaction.setResponseCode( entity.getResponseCode() );

        generatedOnlineTransaction.setPaymentId( entity.getPaymentId() == null ? null : jpaPaymentToGeneratedPayment(entity.getPaymentId(),context) );

        return generatedOnlineTransaction;
    }

    @Override

    public JpaOnliePaymentTransactionRawReponse generatedOnliePaymentTransactionRawReponseToJpaOnliePaymentTransactionRawReponse(GeneratedOnliePaymentTransactionRawReponse model, CycleAvoidingMappingContext context) {

        JpaOnliePaymentTransactionRawReponse target = context.getMappedInstance( model, JpaOnliePaymentTransactionRawReponse.class );

        if ( target != null ) {

            return target;
        }

        if ( model == null ) {

            return null;
        }

        JpaOnliePaymentTransactionRawReponse jpaOnliePaymentTransactionRawReponse = new JpaOnliePaymentTransactionRawReponse();

        context.storeMappedInstance( model, jpaOnliePaymentTransactionRawReponse );

        jpaOnliePaymentTransactionRawReponse.setRawResponseId( model.getRawResponseId() );

        jpaOnliePaymentTransactionRawReponse.setUrl( model.getUrl() );

        jpaOnliePaymentTransactionRawReponse.setDateCreated( model.getDateCreated() );

        return jpaOnliePaymentTransactionRawReponse;
    }

    @Override

    public GeneratedOnliePaymentTransactionRawReponse jpaOnliePaymentTransactionRawReponseToGeneratedOnliePaymentTransactionRawReponse(JpaOnliePaymentTransactionRawReponse entity, CycleAvoidingMappingContext context) {

        GeneratedOnliePaymentTransactionRawReponse target = context.getMappedInstance( entity, GeneratedOnliePaymentTransactionRawReponse.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        GeneratedOnliePaymentTransactionRawReponse generatedOnliePaymentTransactionRawReponse = new GeneratedOnliePaymentTransactionRawReponse();

        context.storeMappedInstance( entity, generatedOnliePaymentTransactionRawReponse );

        generatedOnliePaymentTransactionRawReponse.setRawResponseId( entity.getRawResponseId() );

        generatedOnliePaymentTransactionRawReponse.setUrl( entity.getUrl() );

        generatedOnliePaymentTransactionRawReponse.setDateCreated( entity.getDateCreated() );

        return generatedOnliePaymentTransactionRawReponse;
    }

    @Override

    public JpaFormVersion generatedFormVersionToJpaFormVersion(GeneratedFormVersion model, CycleAvoidingMappingContext context) {

        JpaFormVersion target = context.getMappedInstance( model, JpaFormVersion.class );

        if ( target != null ) {

            return target;
        }

        if ( model == null ) {

            return null;
        }

        JpaFormVersion jpaFormVersion = new JpaFormVersion();

        context.storeMappedInstance( model, jpaFormVersion );

        jpaFormVersion.setFormVersionId( model.getFormVersionId() );

        jpaFormVersion.setDateCreated( model.getDateCreated() );

        jpaFormVersion.setCreatedBy( model.getCreatedBy() );

        jpaFormVersion.setFormId( model.getFormId() );

        jpaFormVersion.setXformStructure( model.getXformStructure() );

        jpaFormVersion.setJsonStructure( model.getJsonStructure() );

        jpaFormVersion.setPreviewUrl( model.getPreviewUrl() );

        jpaFormVersion.setJpaForm( model.getJpaForm() == null ? null :  generatedFormToJpaForm(model.getJpaForm(),context) );

        return jpaFormVersion;
    }

    @Override

    public GeneratedFormVersion jpaFormVersionToGeneratedFormVersion(JpaFormVersion entity, CycleAvoidingMappingContext context) {

        GeneratedFormVersion target = context.getMappedInstance( entity, GeneratedFormVersion.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        GeneratedFormVersion generatedFormVersion = new GeneratedFormVersion();

        context.storeMappedInstance( entity, generatedFormVersion );

        generatedFormVersion.setFormVersionId( entity.getFormVersionId() );

        generatedFormVersion.setDateCreated( entity.getDateCreated() );

        generatedFormVersion.setCreatedBy( entity.getCreatedBy() );

        generatedFormVersion.setFormId( entity.getFormId() );

        generatedFormVersion.setPreviewUrl( entity.getPreviewUrl() );

        generatedFormVersion.setJsonStructure( entity.getJsonStructure() );

        generatedFormVersion.setXformStructure( entity.getXformStructure() );

        generatedFormVersion.setJpaForm( entity.getJpaForm() == null ? null : jpaFormToGeneratedForm(entity.getJpaForm(),context) );

        return generatedFormVersion;
    }

    @Override

    public JpaManualTransaction generatedManualTransactionToJpaManualTransaction(GeneratedManualTransaction model, CycleAvoidingMappingContext context) {

        JpaManualTransaction target = context.getMappedInstance( model, JpaManualTransaction.class );

        if ( target != null ) {

            return target;
        }

        if ( model == null ) {

            return null;
        }

        JpaManualTransaction jpaManualTransaction = new JpaManualTransaction();

        context.storeMappedInstance( model, jpaManualTransaction );

        jpaManualTransaction.setTransactionId( model.getTransactionId() );

        jpaManualTransaction.setRegistrationNumber( model.getRegistrationNumber() );

        jpaManualTransaction.setDateCreated( model.getDateCreated() );

        jpaManualTransaction.setAmountPaid( model.getAmountPaid() );

        jpaManualTransaction.setDateClaimed( model.getDateClaimed() );

        jpaManualTransaction.setClaimed( model.getClaimed() );

        jpaManualTransaction.setCreditedAccount( model.getCreditedAccount() );

        jpaManualTransaction.setProcessingMessage( model.getProcessingMessage() );

        jpaManualTransaction.setPaymentId( model.getPaymentId() == null ? null :  generatedPaymentToJpaPayment(model.getPaymentId(),context) );

        jpaManualTransaction.setCreatedByAppUserId( model.getCreatedByAppUserId() == null ? null :  generatedAppUserToJpaAppUser(model.getCreatedByAppUserId(),context) );

        return jpaManualTransaction;
    }

    @Override

    public GeneratedManualTransaction jpaManualTransactionToGeneratedManualTransaction(JpaManualTransaction entity, CycleAvoidingMappingContext context) {

        GeneratedManualTransaction target = context.getMappedInstance( entity, GeneratedManualTransaction.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        GeneratedManualTransaction generatedManualTransaction = new GeneratedManualTransaction();

        context.storeMappedInstance( entity, generatedManualTransaction );

        generatedManualTransaction.setTransactionId( entity.getTransactionId() );

        generatedManualTransaction.setRegistrationNumber( entity.getRegistrationNumber() );

        generatedManualTransaction.setDateCreated( entity.getDateCreated() );

        generatedManualTransaction.setAmountPaid( entity.getAmountPaid() );

        generatedManualTransaction.setDateClaimed( entity.getDateClaimed() );

        generatedManualTransaction.setClaimed( entity.getClaimed() );

        generatedManualTransaction.setCreditedAccount( entity.getCreditedAccount() );

        generatedManualTransaction.setProcessingMessage( entity.getProcessingMessage() );

        generatedManualTransaction.setPaymentId( entity.getPaymentId() == null ? null : jpaPaymentToGeneratedPayment(entity.getPaymentId(),context) );

        generatedManualTransaction.setCreatedByAppUserId( entity.getCreatedByAppUserId() == null ? null : jpaAppUserToGeneratedAppUser(entity.getCreatedByAppUserId(),context) );

        return generatedManualTransaction;
    }

    @Override

    public JpaForm generatedFormToJpaForm(GeneratedForm model, CycleAvoidingMappingContext context) {

        JpaForm target = context.getMappedInstance( model, JpaForm.class );

        if ( target != null ) {

            return target;
        }

        if ( model == null ) {

            return null;
        }

        JpaForm jpaForm = new JpaForm();

        context.storeMappedInstance( model, jpaForm );

        jpaForm.setFormId( model.getFormId() );

        jpaForm.setFormName( model.getFormName() );

        jpaForm.setDateCreated( model.getDateCreated() );

        jpaForm.setCreatedBy( model.getCreatedBy() );

        jpaForm.setDateModified( model.getDateModified() );

        jpaForm.setFormDesc( model.getFormDesc() );

        jpaForm.setModifiedBy( model.getModifiedBy() );

        jpaForm.setJpaFormVersion( model.getJpaFormVersion() == null ? null :  generatedFormVersionToJpaFormVersion(model.getJpaFormVersion(),context) );

        return jpaForm;
    }

    @Override

    public GeneratedForm jpaFormToGeneratedForm(JpaForm entity, CycleAvoidingMappingContext context) {

        GeneratedForm target = context.getMappedInstance( entity, GeneratedForm.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        GeneratedForm generatedForm = new GeneratedForm();

        context.storeMappedInstance( entity, generatedForm );

        generatedForm.setFormId( entity.getFormId() );

        generatedForm.setFormName( entity.getFormName() );

        generatedForm.setDateCreated( entity.getDateCreated() );

        generatedForm.setCreatedBy( entity.getCreatedBy() );

        generatedForm.setDateModified( entity.getDateModified() );

        generatedForm.setFormDesc( entity.getFormDesc() );

        generatedForm.setModifiedBy( entity.getModifiedBy() );

        generatedForm.setJpaFormVersion( entity.getJpaFormVersion() == null ? null : jpaFormVersionToGeneratedFormVersion(entity.getJpaFormVersion(),context) );

        return generatedForm;
    }

    @Override

    public JpaApplicationData generatedApplicationDataToJpaApplicationData(ApplicationData model, CycleAvoidingMappingContext context) {

        JpaApplicationData target = context.getMappedInstance( model, JpaApplicationData.class );

        if ( target != null ) {

            return target;
        }

        if ( model == null ) {

            return null;
        }

        JpaApplicationData jpaApplicationData = new JpaApplicationData();

        context.storeMappedInstance( model, jpaApplicationData );

        jpaApplicationData.setAppUserId( model.getAppUserId() );

        jpaApplicationData.setDateCreated( model.getDateCreated() );

        jpaApplicationData.setCreatedBy( model.getCreatedBy() );

        jpaApplicationData.setFormData( model.getFormData() );

        jpaApplicationData.setXmlFormData( model.getXmlFormData() );

        jpaApplicationData.setJpaAppUser( model.getJpaAppUser() == null ? null :  generatedAppUserToJpaAppUser(model.getJpaAppUser(),context) );

        return jpaApplicationData;
    }

    @Override

    public ApplicationData jpaApplicationDataToGeneratedApplicationData(JpaApplicationData entity, CycleAvoidingMappingContext context) {

        ApplicationData target = context.getMappedInstance( entity, ApplicationData.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        ApplicationData applicationData = new ApplicationData();

        context.storeMappedInstance( entity, applicationData );

        applicationData.setAppUserId( entity.getAppUserId() );

        applicationData.setDateCreated( entity.getDateCreated() );

        applicationData.setCreatedBy( entity.getCreatedBy() );

        applicationData.setXmlFormData( entity.getXmlFormData() );

        applicationData.setFormData( entity.getFormData() );

        applicationData.setJpaAppUser( entity.getJpaAppUser() == null ? null : jpaAppUserToGeneratedAppUser(entity.getJpaAppUser(),context) );

        return applicationData;
    }
}

