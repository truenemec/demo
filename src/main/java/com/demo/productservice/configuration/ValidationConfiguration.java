package com.demo.productservice.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import java.util.Locale;

@Configuration
public class ValidationConfiguration {

    class MyMessageInterpolator implements MessageInterpolator {
        private final MessageInterpolator defaultInterpolator;

        public MyMessageInterpolator(MessageInterpolator interpolator) {
            this.defaultInterpolator = interpolator;
        }

        @Override
        public String interpolate(String messageTemplate, Context context) {
            messageTemplate = messageTemplate.toUpperCase();
            return defaultInterpolator.interpolate(messageTemplate, context);
        }

        @Override
        public String interpolate(String messageTemplate, Context context, Locale locale) {
            messageTemplate = messageTemplate.toUpperCase();
            return defaultInterpolator.interpolate(messageTemplate, context, locale);
        }
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        Validation.byDefaultProvider().configure().messageInterpolator(
                new MyMessageInterpolator(
                        Validation.byDefaultProvider().configure().getDefaultMessageInterpolator())
        );
        return bean;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
