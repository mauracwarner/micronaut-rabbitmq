package io.micronaut.configuration.rabbitmq.docs.consumer.acknowledge.type

// tag::imports[]
import io.micronaut.configuration.rabbitmq.annotation.Binding
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient
import io.micronaut.context.annotation.Requires
// end::imports[]

@Requires(property = "spec.name", value = "AcknowledgeSpec")
// tag::clazz[]
@RabbitClient // <1>
interface ProductClient {

    @Binding("product") // <2>
    void send(byte[] data) // <3>
}
// end::clazz[]