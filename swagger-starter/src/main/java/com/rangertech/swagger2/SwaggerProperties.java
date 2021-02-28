package com.rangertech.swagger2;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties("cloud.swagger")
public class SwaggerProperties {

    private Boolean enabled;
    private String title = "";
    private String description = "";
    private String version = "";
    private String license = "";
    private String licenseUrl = "";
    private String termsOfServiceUrl = "";
    private SwaggerProperties.Contact contact = new SwaggerProperties.Contact();
    private String basePackage = "";
    private List<String> basePath = Lists.newArrayList();
    private List<String> excludePath = Lists.newArrayList();
    private Map<String, SwaggerProperties.DocketInfo> docket = Maps.newLinkedHashMap();
    private String host = "";
    private List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters;

    public SwaggerProperties() {
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getVersion() {
        return this.version;
    }

    public String getLicense() {
        return this.license;
    }

    public String getLicenseUrl() {
        return this.licenseUrl;
    }

    public String getTermsOfServiceUrl() {
        return this.termsOfServiceUrl;
    }

    public SwaggerProperties.Contact getContact() {
        return this.contact;
    }

    public String getBasePackage() {
        return this.basePackage;
    }

    public List<String> getBasePath() {
        return this.basePath;
    }

    public List<String> getExcludePath() {
        return this.excludePath;
    }

    public Map<String, SwaggerProperties.DocketInfo> getDocket() {
        return this.docket;
    }

    public String getHost() {
        return this.host;
    }

    public List<SwaggerProperties.GlobalOperationParameter> getGlobalOperationParameters() {
        return this.globalOperationParameters;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public void setLicense(final String license) {
        this.license = license;
    }

    public void setLicenseUrl(final String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public void setTermsOfServiceUrl(final String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public void setContact(final SwaggerProperties.Contact contact) {
        this.contact = contact;
    }

    public void setBasePackage(final String basePackage) {
        this.basePackage = basePackage;
    }

    public void setBasePath(final List<String> basePath) {
        this.basePath = basePath;
    }

    public void setExcludePath(final List<String> excludePath) {
        this.excludePath = excludePath;
    }

    public void setDocket(final Map<String, SwaggerProperties.DocketInfo> docket) {
        this.docket = docket;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public void setGlobalOperationParameters(final List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters) {
        this.globalOperationParameters = globalOperationParameters;
    }

    public static class Contact {
        private String name = "";
        private String url = "";
        private String email = "";

        public Contact() {
        }

        public String getName() {
            return this.name;
        }

        public String getUrl() {
            return this.url;
        }

        public String getEmail() {
            return this.email;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public void setUrl(final String url) {
            this.url = url;
        }

        public void setEmail(final String email) {
            this.email = email;
        }
    }

    public static class DocketInfo {
        private String title = "";
        private String description = "";
        private String version = "";
        private String license = "";
        private String licenseUrl = "";
        private String termsOfServiceUrl = "";
        private SwaggerProperties.Contact contact = new SwaggerProperties.Contact();
        private String basePackage = "";
        private List<String> basePath = Lists.newArrayList();
        private List<String> excludePath = Lists.newArrayList();
        private List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters;

        public DocketInfo() {
        }

        public String getTitle() {
            return this.title;
        }

        public String getDescription() {
            return this.description;
        }

        public String getVersion() {
            return this.version;
        }

        public String getLicense() {
            return this.license;
        }

        public String getLicenseUrl() {
            return this.licenseUrl;
        }

        public String getTermsOfServiceUrl() {
            return this.termsOfServiceUrl;
        }

        public SwaggerProperties.Contact getContact() {
            return this.contact;
        }

        public String getBasePackage() {
            return this.basePackage;
        }

        public List<String> getBasePath() {
            return this.basePath;
        }

        public List<String> getExcludePath() {
            return this.excludePath;
        }

        public List<SwaggerProperties.GlobalOperationParameter> getGlobalOperationParameters() {
            return this.globalOperationParameters;
        }

        public void setTitle(final String title) {
            this.title = title;
        }

        public void setDescription(final String description) {
            this.description = description;
        }

        public void setVersion(final String version) {
            this.version = version;
        }

        public void setLicense(final String license) {
            this.license = license;
        }

        public void setLicenseUrl(final String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public void setTermsOfServiceUrl(final String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public void setContact(final SwaggerProperties.Contact contact) {
            this.contact = contact;
        }

        public void setBasePackage(final String basePackage) {
            this.basePackage = basePackage;
        }

        public void setBasePath(final List<String> basePath) {
            this.basePath = basePath;
        }

        public void setExcludePath(final List<String> excludePath) {
            this.excludePath = excludePath;
        }

        public void setGlobalOperationParameters(final List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters) {
            this.globalOperationParameters = globalOperationParameters;
        }
    }

    public static class GlobalOperationParameter {

        private String name;
        private String description;
        private String modelRef;
        private String parameterType;
        private String required;

        public GlobalOperationParameter() {
        }

        public void setName(final String name) {
            this.name = name;
        }

        public void setDescription(final String description) {
            this.description = description;
        }

        public void setModelRef(final String modelRef) {
            this.modelRef = modelRef;
        }

        public void setParameterType(final String parameterType) {
            this.parameterType = parameterType;
        }

        public void setRequired(final String required) {
            this.required = required;
        }

        public String getName() {
            return this.name;
        }

        public String getDescription() {
            return this.description;
        }

        public String getModelRef() {
            return this.modelRef;
        }

        public String getParameterType() {
            return this.parameterType;
        }

        public String getRequired() {
            return this.required;
        }
    }
}
