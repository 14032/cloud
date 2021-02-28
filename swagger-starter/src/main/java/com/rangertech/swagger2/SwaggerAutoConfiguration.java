package com.rangertech.swagger2;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;
import java.util.stream.Collectors;

@Import({Swagger2Configuration.class})
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerAutoConfiguration implements BeanFactoryAware {

    private static final String AUTH_KEY = "token";
    private BeanFactory beanFactory;

    public SwaggerAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = {"cloud.swagger.enabled"}, matchIfMissing = true)
    public List<Docket> createRestApi(SwaggerProperties swaggerProperties) {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) this.beanFactory;
        List<Docket> docketList = Lists.newLinkedList();
        if (swaggerProperties.getDocket().size() == 0) {
            Docket docket = this.createDocket(swaggerProperties);
            configurableBeanFactory.registerSingleton("defaultDocket", docket);
            docketList.add(docket);
            return docketList;
        } else {

            for (String groupName : swaggerProperties.getDocket().keySet()) {
                SwaggerProperties.DocketInfo docketInfo = (SwaggerProperties.DocketInfo) swaggerProperties.getDocket().get(groupName);
                ApiInfo apiInfo = (new ApiInfoBuilder()).title(docketInfo.getTitle().isEmpty() ? swaggerProperties.getTitle() : docketInfo.getTitle()).description(docketInfo.getDescription().isEmpty() ? swaggerProperties.getDescription() : docketInfo.getDescription()).version(docketInfo.getVersion().isEmpty() ? swaggerProperties.getVersion() : docketInfo.getVersion()).license(docketInfo.getLicense().isEmpty() ? swaggerProperties.getLicense() : docketInfo.getLicense()).licenseUrl(docketInfo.getLicenseUrl().isEmpty() ? swaggerProperties.getLicenseUrl() : docketInfo.getLicenseUrl()).contact(new Contact(docketInfo.getContact().getName().isEmpty() ? swaggerProperties.getContact().getName() : docketInfo.getContact().getName(), docketInfo.getContact().getUrl().isEmpty() ? swaggerProperties.getContact().getUrl() : docketInfo.getContact().getUrl(), docketInfo.getContact().getEmail().isEmpty() ? swaggerProperties.getContact().getEmail() : docketInfo.getContact().getEmail())).termsOfServiceUrl(docketInfo.getTermsOfServiceUrl().isEmpty() ? swaggerProperties.getTermsOfServiceUrl() : docketInfo.getTermsOfServiceUrl()).build();
                if (docketInfo.getBasePath().isEmpty()) {
                    docketInfo.getBasePath().add("/**");
                }

                List<Predicate<String>> basePath = Lists.newArrayListWithCapacity(docketInfo.getBasePath().size());

                for (String path : docketInfo.getBasePath()) {
                    basePath.add(PathSelectors.ant(path));
                }

                List<Predicate<String>> excludePath = Lists.newArrayListWithCapacity(docketInfo.getExcludePath().size());

                for (String path : docketInfo.getExcludePath()) {
                    excludePath.add(PathSelectors.ant(path));
                }

                Docket docket = (new Docket(DocumentationType.SWAGGER_2)).host(swaggerProperties.getHost()).apiInfo(apiInfo).globalOperationParameters(this.assemblyGlobalOperationParameters(swaggerProperties.getGlobalOperationParameters(), docketInfo.getGlobalOperationParameters())).groupName(groupName).select().apis(RequestHandlerSelectors.basePackage(docketInfo.getBasePackage())).paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath))).build().securitySchemes(this.securitySchemes()).securityContexts(this.securityContexts());
                configurableBeanFactory.registerSingleton(groupName, docket);
                docketList.add(docket);
            }

            return docketList;
        }
    }

    private Docket createDocket(final SwaggerProperties swaggerProperties) {
        ApiInfo apiInfo = (new ApiInfoBuilder()).title(swaggerProperties.getTitle()).description(swaggerProperties.getDescription()).version(swaggerProperties.getVersion()).license(swaggerProperties.getLicense()).licenseUrl(swaggerProperties.getLicenseUrl()).contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail())).termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl()).build();
        if (swaggerProperties.getBasePath().isEmpty()) {
            swaggerProperties.getBasePath().add("/**");
        }

        List<Predicate<String>> basePath = Lists.newArrayList();

        for (String path : swaggerProperties.getBasePath()) {
            basePath.add(PathSelectors.ant(path));
        }

        List<Predicate<String>> excludePath = Lists.newArrayList();

        for (String path : swaggerProperties.getExcludePath()) {
            excludePath.add(PathSelectors.ant(path));
        }
        return (new Docket(DocumentationType.SWAGGER_2)).host(swaggerProperties.getHost()).apiInfo(apiInfo).globalOperationParameters(this.buildGlobalOperationParametersFromSwaggerProperties(swaggerProperties.getGlobalOperationParameters())).select().apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage())).paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath))).build().securitySchemes(this.securitySchemes()).securityContexts(this.securityContexts());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> contexts = Lists.newArrayListWithCapacity(1);
        SecurityContext securityContext = SecurityContext.builder().securityReferences(this.defaultAuth()).build();
        contexts.add(securityContext);
        return contexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        List<SecurityReference> references = Lists.newArrayListWithCapacity(1);
        references.add(new SecurityReference("token", authorizationScopes));
        return references;
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = Lists.newArrayListWithCapacity(1);
        ApiKey apiKey = new ApiKey("token", "token", "header");
        apiKeys.add(apiKey);
        return apiKeys;
    }

    private List<Parameter> buildGlobalOperationParametersFromSwaggerProperties(List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters) {
        List<Parameter> parameters = Lists.newArrayList();
        if (Objects.isNull(globalOperationParameters)) {
            return parameters;
        } else {
            for (SwaggerProperties.GlobalOperationParameter globalOperationParameter : globalOperationParameters) {
                parameters.add((new ParameterBuilder()).name(globalOperationParameter.getName()).description(globalOperationParameter.getDescription()).modelRef(new ModelRef(globalOperationParameter.getModelRef())).parameterType(globalOperationParameter.getParameterType()).required(Boolean.parseBoolean(globalOperationParameter.getRequired())).build());
            }
            return parameters;
        }
    }

    private List<Parameter> assemblyGlobalOperationParameters(List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters, List<SwaggerProperties.GlobalOperationParameter> docketOperationParameters) {
        if (!Objects.isNull(docketOperationParameters) && !docketOperationParameters.isEmpty()) {
            Set<String> docketNames = docketOperationParameters.stream().map(SwaggerProperties.GlobalOperationParameter::getName).collect(Collectors.toSet());
            List<SwaggerProperties.GlobalOperationParameter> resultOperationParameters = Lists.newArrayList();
            if (Objects.nonNull(globalOperationParameters)) {

                for (SwaggerProperties.GlobalOperationParameter parameter : globalOperationParameters) {
                    if (!docketNames.contains(parameter.getName())) {
                        resultOperationParameters.add(parameter);
                    }
                }
            }

            resultOperationParameters.addAll(docketOperationParameters);
            return this.buildGlobalOperationParametersFromSwaggerProperties(resultOperationParameters);
        } else {
            return this.buildGlobalOperationParametersFromSwaggerProperties(globalOperationParameters);
        }
    }
}
