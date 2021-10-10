package com.depromeet.dgdg

import com.amazonaws.services.secretsmanager.AWSSecretsManager
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertiesPropertySource
import java.util.*

class EncryptionPropertyListener : ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    override fun onApplicationEvent(event: ApplicationEnvironmentPreparedEvent) {

        val environment = event.environment
        val client = initClient(environment)
        val secret = getCredential(environment, client)

        val map: HashMap<String, String> = jacksonObjectMapper().readValue(secret)

        environment.propertySources.addFirst(
            PropertiesPropertySource("awsSecretsProps", setDatabaseCredentials(map)),
        )
    }

    private fun initClient(environment: ConfigurableEnvironment): AWSSecretsManager {
        val region = environment.getProperty(AWS_REGION)
        return AWSSecretsManagerClientBuilder.standard().withRegion(region).build()
    }

    private fun getCredential(environment: ConfigurableEnvironment, client: AWSSecretsManager): String {
        val secretName = environment.getProperty(AWS_SECRET_NAME)
        val request = GetSecretValueRequest().withSecretId(secretName)
        val response = client.getSecretValue(request)
        return response.secretString ?: String(Base64.getDecoder().decode(response.secretBinary).array())
    }

    private fun setDatabaseCredentials(map: HashMap<String, String>): Properties {
        val props = Properties()
        props["aws.secret-manager.url"] = map["url"]
        props["aws.secret-manager.username"] = map["username"]
        props["aws.secret-manager.password"] = map["password"]
        return props
    }

    companion object {
        private const val AWS_SECRET_NAME = "aws.encrypt.secret-name"
        private const val AWS_REGION = "aws.encrypt.region"
    }

}
