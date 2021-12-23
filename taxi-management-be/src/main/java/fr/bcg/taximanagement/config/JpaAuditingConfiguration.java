package fr.bcg.taximanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import fr.bcg.taximanagement.implementation.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
class JpaAuditingConfiguration {

	@Bean
    public AuditorAware<String>  auditorAware(){
        return new AuditorAwareImpl();
    }

}