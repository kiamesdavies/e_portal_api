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

import javax.annotation.Generated;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2018-02-05T00:19:54+0100",

    comments = "version: 1.2.0.Beta2, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"

)

public class GeneralMapperImpl implements GeneralMapper {

    @Override

    public Category jpaCategoryToCategory(JpaCategory category, CycleAvoidingMappingContext context) {

        Category target = context.getMappedInstance( category, Category.class );

        if ( target != null ) {

            return target;
        }

        if ( category == null ) {

            return null;
        }

        Category category1 = new Category();

        context.storeMappedInstance( category, category1 );

        category1.setCategoryId( category.getCategoryId() );

        category1.setCategoryName( category.getCategoryName() );

        category1.setDateCreated( category.getDateCreated() );

        category1.setCreatedBy( category.getCreatedBy() );

        category1.setDateModified( category.getDateModified() );

        category1.setCategoryDesc( category.getCategoryDesc() );

        category1.setModifiedBy( category.getModifiedBy() );

        category1.setAmount( category.getAmount() );

        return category1;
    }

    @Override

    public JpaCategory categorytoJpaCategory(Category c, CycleAvoidingMappingContext context) {

        JpaCategory target = context.getMappedInstance( c, JpaCategory.class );

        if ( target != null ) {

            return target;
        }

        if ( c == null ) {

            return null;
        }

        JpaCategory jpaCategory = new JpaCategory();

        context.storeMappedInstance( c, jpaCategory );

        jpaCategory.setCategoryId( c.getCategoryId() );

        jpaCategory.setCategoryName( c.getCategoryName() );

        jpaCategory.setDateCreated( c.getDateCreated() );

        jpaCategory.setCreatedBy( c.getCreatedBy() );

        jpaCategory.setDateModified( c.getDateModified() );

        jpaCategory.setCategoryDesc( c.getCategoryDesc() );

        jpaCategory.setModifiedBy( c.getModifiedBy() );

        jpaCategory.setAmount( c.getAmount() );

        return jpaCategory;
    }

    @Override

    public ApplicationSummary jpaApplicationSummaryToApplicationSummary(JpaApplicationSummary applicationSummary) {

        if ( applicationSummary == null ) {

            return null;
        }

        ApplicationSummary applicationSummary1 = new ApplicationSummary();

        applicationSummary1.setQualificationsWithDate( applicationSummary.getQualificationsWithDate() );

        applicationSummary1.setAppUserId( applicationSummary.getAppUserId() );

        applicationSummary1.setDateCreated( applicationSummary.getDateCreated() );

        applicationSummary1.setUserName( applicationSummary.getUserName() );

        applicationSummary1.setFilledForm( applicationSummary.getFilledForm() );

        applicationSummary1.setCategory( applicationSummary.getCategory() );

        applicationSummary1.setSurName( applicationSummary.getSurName() );

        applicationSummary1.setFirstName( applicationSummary.getFirstName() );

        applicationSummary1.setMiddleName( applicationSummary.getMiddleName() );

        applicationSummary1.setDateOfBirth( applicationSummary.getDateOfBirth() );

        applicationSummary1.setSex( applicationSummary.getSex() );

        applicationSummary1.setMobileNumber( applicationSummary.getMobileNumber() );

        applicationSummary1.seteMail( applicationSummary.geteMail() );

        applicationSummary1.setNationality( applicationSummary.getNationality() );

        applicationSummary1.setStateOfOrigin( applicationSummary.getStateOfOrigin() );

        applicationSummary1.setLgaOfOrigin( applicationSummary.getLgaOfOrigin() );

        applicationSummary1.setCurrentEmployer( applicationSummary.getCurrentEmployer() );

        applicationSummary1.setCurrentEmployerOfficeAddress( applicationSummary.getCurrentEmployerOfficeAddress() );

        applicationSummary1.setAreaOfSpecialization( applicationSummary.getAreaOfSpecialization() );

        applicationSummary1.setFormNo( applicationSummary.getFormNo() );

        applicationSummary1.setInstitutionsAttended( applicationSummary.getInstitutionsAttended() );

        applicationSummary1.setWorkExperience( applicationSummary.getWorkExperience() );

        applicationSummary1.setDatePaid( applicationSummary.getDatePaid() );

        applicationSummary1.setBankName( applicationSummary.getBankName() );

        applicationSummary1.setBankTeller( applicationSummary.getBankTeller() );

        applicationSummary1.setAmountPaid( applicationSummary.getAmountPaid() );

        applicationSummary1.setPaid( applicationSummary.getPaid() );

        applicationSummary1.setExpiryDate( applicationSummary.getExpiryDate() );

        applicationSummary1.setDateInitialized( applicationSummary.getDateInitialized() );

        applicationSummary1.setPaymentId( applicationSummary.getPaymentId() );

        return applicationSummary1;
    }

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

        jpaAppUser.setRegistrationDate( model.getRegistrationDate() );

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

        appUser.setRegistrationDate( entity.getRegistrationDate() );

        return appUser;
    }

    @Override

    public JpaPayment generatedPaymentToJpaPayment(Payment model, CycleAvoidingMappingContext context) {

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

        jpaPayment.setPaymentType( model.getPaymentType() );

        jpaPayment.setBankName( model.getBankName() );

        jpaPayment.setBankTeller( model.getBankTeller() );

        jpaPayment.setCertificatePath( model.getCertificatePath() );

        jpaPayment.setAmountToPay( model.getAmountToPay() );

        jpaPayment.setAppUserId( model.getAppUserId() == null ? null :  generatedAppUserToJpaAppUser(model.getAppUserId(),context) );

        return jpaPayment;
    }

    @Override

    public Payment jpaPaymentToGeneratedPayment(JpaPayment entity, CycleAvoidingMappingContext context) {

        Payment target = context.getMappedInstance( entity, Payment.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        Payment payment = new Payment();

        context.storeMappedInstance( entity, payment );

        payment.setPaymentId( entity.getPaymentId() );

        payment.setDateInitialized( entity.getDateInitialized() );

        payment.setDatePaid( entity.getDatePaid() );

        payment.setExpiryDate( entity.getExpiryDate() );

        payment.setPaid( entity.getPaid() );

        payment.setAmountPaid( entity.getAmountPaid() );

        payment.setCertificatePath( entity.getCertificatePath() );

        payment.setBankName( entity.getBankName() );

        payment.setBankTeller( entity.getBankTeller() );

        payment.setPaymentType( entity.getPaymentType() );

        payment.setAmountToPay( entity.getAmountToPay() );

        payment.setAppUserId( entity.getAppUserId() == null ? null : jpaAppUserToGeneratedAppUser(entity.getAppUserId(),context) );

        return payment;
    }

    @Override

    public JpaOnlineTransaction generatedOnlineTransactionToJpaOnlineTransaction(OnlineTransaction model, CycleAvoidingMappingContext context) {

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

        jpaOnlineTransaction.setRrr( model.getRrr() );

        jpaOnlineTransaction.setPaymentId( model.getPaymentId() == null ? null :  generatedPaymentToJpaPayment(model.getPaymentId(),context) );

        return jpaOnlineTransaction;
    }

    @Override

    public OnlineTransaction jpaOnlineTransactionToGeneratedOnlineTransaction(JpaOnlineTransaction entity, CycleAvoidingMappingContext context) {

        OnlineTransaction target = context.getMappedInstance( entity, OnlineTransaction.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        OnlineTransaction onlineTransaction = new OnlineTransaction();

        context.storeMappedInstance( entity, onlineTransaction );

        onlineTransaction.setTransactionId( entity.getTransactionId() );

        onlineTransaction.setDateInitialized( entity.getDateInitialized() );

        onlineTransaction.setAmountToPay( entity.getAmountToPay() );

        onlineTransaction.setAmountPaid( entity.getAmountPaid() );

        onlineTransaction.setDatePayed( entity.getDatePayed() );

        onlineTransaction.setSucessful( entity.getSucessful() );

        onlineTransaction.setCreditedAccount( entity.getCreditedAccount() );

        onlineTransaction.setResponseMessage( entity.getResponseMessage() );

        onlineTransaction.setResponseCode( entity.getResponseCode() );

        onlineTransaction.setRrr( entity.getRrr() );

        onlineTransaction.setPaymentId( entity.getPaymentId() == null ? null : jpaPaymentToGeneratedPayment(entity.getPaymentId(),context) );

        return onlineTransaction;
    }

    @Override

    public JpaOnliePaymentTransactionRawReponse generatedOnliePaymentTransactionRawReponseToJpaOnliePaymentTransactionRawReponse(OnliePaymentTransactionRawReponse model, CycleAvoidingMappingContext context) {

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

    public OnliePaymentTransactionRawReponse jpaOnliePaymentTransactionRawReponseToGeneratedOnliePaymentTransactionRawReponse(JpaOnliePaymentTransactionRawReponse entity, CycleAvoidingMappingContext context) {

        OnliePaymentTransactionRawReponse target = context.getMappedInstance( entity, OnliePaymentTransactionRawReponse.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        OnliePaymentTransactionRawReponse onliePaymentTransactionRawReponse = new OnliePaymentTransactionRawReponse();

        context.storeMappedInstance( entity, onliePaymentTransactionRawReponse );

        onliePaymentTransactionRawReponse.setRawResponseId( entity.getRawResponseId() );

        onliePaymentTransactionRawReponse.setUrl( entity.getUrl() );

        onliePaymentTransactionRawReponse.setDateCreated( entity.getDateCreated() );

        return onliePaymentTransactionRawReponse;
    }

    @Override

    public JpaFormVersion generatedFormVersionToJpaFormVersion(FormVersion model, CycleAvoidingMappingContext context) {

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

        jpaFormVersion.setXformStructure( model.getXformStructure() );

        jpaFormVersion.setJsonStructure( model.getJsonStructure() );

        jpaFormVersion.setPreviewUrl( model.getPreviewUrl() );

        jpaFormVersion.setFormId( model.getFormId() == null ? null :  generatedFormToJpaForm(model.getFormId(),context) );

        return jpaFormVersion;
    }

    @Override

    public FormVersion jpaFormVersionToGeneratedFormVersion(JpaFormVersion entity, CycleAvoidingMappingContext context) {

        FormVersion target = context.getMappedInstance( entity, FormVersion.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        FormVersion formVersion = new FormVersion();

        context.storeMappedInstance( entity, formVersion );

        formVersion.setFormVersionId( entity.getFormVersionId() );

        formVersion.setDateCreated( entity.getDateCreated() );

        formVersion.setCreatedBy( entity.getCreatedBy() );

        formVersion.setPreviewUrl( entity.getPreviewUrl() );

        formVersion.setJsonStructure( entity.getJsonStructure() );

        formVersion.setXformStructure( entity.getXformStructure() );

        formVersion.setFormId( entity.getFormId() == null ? null : jpaFormToGeneratedForm(entity.getFormId(),context) );

        return formVersion;
    }

    @Override

    public JpaManualTransaction generatedManualTransactionToJpaManualTransaction(ManualTransaction model, CycleAvoidingMappingContext context) {

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

        jpaManualTransaction.setBankName( model.getBankName() );

        jpaManualTransaction.setBankTeller( model.getBankTeller() );

        jpaManualTransaction.setDatePaid( model.getDatePaid() );

        jpaManualTransaction.setPaymentId( model.getPaymentId() == null ? null :  generatedPaymentToJpaPayment(model.getPaymentId(),context) );

        jpaManualTransaction.setCreatedByAppUserId( model.getCreatedByAppUserId() == null ? null :  generatedAppUserToJpaAppUser(model.getCreatedByAppUserId(),context) );

        return jpaManualTransaction;
    }

    @Override

    public ManualTransaction jpaManualTransactionToGeneratedManualTransaction(JpaManualTransaction entity, CycleAvoidingMappingContext context) {

        ManualTransaction target = context.getMappedInstance( entity, ManualTransaction.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        ManualTransaction manualTransaction = new ManualTransaction();

        context.storeMappedInstance( entity, manualTransaction );

        manualTransaction.setTransactionId( entity.getTransactionId() );

        manualTransaction.setRegistrationNumber( entity.getRegistrationNumber() );

        manualTransaction.setDateCreated( entity.getDateCreated() );

        manualTransaction.setAmountPaid( entity.getAmountPaid() );

        manualTransaction.setDateClaimed( entity.getDateClaimed() );

        manualTransaction.setClaimed( entity.getClaimed() );

        manualTransaction.setCreditedAccount( entity.getCreditedAccount() );

        manualTransaction.setProcessingMessage( entity.getProcessingMessage() );

        manualTransaction.setBankName( entity.getBankName() );

        manualTransaction.setBankTeller( entity.getBankTeller() );

        manualTransaction.setDatePaid( entity.getDatePaid() );

        manualTransaction.setPaymentId( entity.getPaymentId() == null ? null : jpaPaymentToGeneratedPayment(entity.getPaymentId(),context) );

        manualTransaction.setCreatedByAppUserId( entity.getCreatedByAppUserId() == null ? null : jpaAppUserToGeneratedAppUser(entity.getCreatedByAppUserId(),context) );

        return manualTransaction;
    }

    @Override

    public JpaForm generatedFormToJpaForm(Form model, CycleAvoidingMappingContext context) {

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

        return jpaForm;
    }

    @Override

    public Form jpaFormToGeneratedForm(JpaForm entity, CycleAvoidingMappingContext context) {

        Form target = context.getMappedInstance( entity, Form.class );

        if ( target != null ) {

            return target;
        }

        if ( entity == null ) {

            return null;
        }

        Form form = new Form();

        context.storeMappedInstance( entity, form );

        form.setFormId( entity.getFormId() );

        form.setFormName( entity.getFormName() );

        form.setDateCreated( entity.getDateCreated() );

        form.setCreatedBy( entity.getCreatedBy() );

        form.setDateModified( entity.getDateModified() );

        form.setFormDesc( entity.getFormDesc() );

        form.setModifiedBy( entity.getModifiedBy() );

        return form;
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

        jpaApplicationData.setFormVersionId( generatedFormVersionToJpaFormVersion( model.getFormVersionId(), context ) );

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

        applicationData.setFormVersionId( jpaFormVersionToGeneratedFormVersion( entity.getFormVersionId(), context ) );

        applicationData.setJpaAppUser( entity.getJpaAppUser() == null ? null : jpaAppUserToGeneratedAppUser(entity.getJpaAppUser(),context) );

        return applicationData;
    }
}

