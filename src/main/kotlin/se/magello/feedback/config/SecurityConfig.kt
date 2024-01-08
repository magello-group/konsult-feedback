package se.magello.feedback.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

  @Value("\${spring.security.oauth2.client.provider.azure.issuer-uri:dev}")
  private val issuer: String = String()

  @Bean
  @ConditionalOnProperty("using.jwt", havingValue = "true")
  fun jwtDecoder() : JwtDecoder {
    val jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer) as NimbusJwtDecoder
    val withIssuer: OAuth2TokenValidator<Jwt> = JwtValidators.createDefaultWithIssuer(issuer)
    val withAudience: OAuth2TokenValidator<Jwt> = DelegatingOAuth2TokenValidator(withIssuer)
    jwtDecoder.setJwtValidator(withAudience)
    return jwtDecoder
  }

  @Bean
  @ConditionalOnProperty("using.jwt", havingValue = "true")
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    http.invoke {
      authorizeRequests {
        authorize("/hello", permitAll)
        authorize("/external/*/edit", permitAll)
        authorize("/add/**",  authenticated)
        authorize("/edit/**", authenticated)
        authorize("/view/**", authenticated)
        authorize(anyRequest, denyAll)
      }
      oauth2ResourceServer {
        jwt {}
      }
    }
    return http.build()
  }


  @Bean
  @ConditionalOnProperty("using.jwt", havingValue = "false")
  fun devFilterChain(http: HttpSecurity): SecurityFilterChain {
    http.invoke {
      authorizeRequests {
        authorize("/hello", permitAll)
        authorize("/external/*/edit", permitAll)
        authorize("/add/**",  authenticated)
        authorize("/edit/**", authenticated)
        authorize("/view/**", authenticated)
        authorize(anyRequest, denyAll)
      }
      httpBasic {  }
    }
    return http.build()
  }
}