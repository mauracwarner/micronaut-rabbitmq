package io.micronaut.configuration.rabbitmq.docs.consumer.executor

// tag::imports[]
import io.micronaut.configuration.rabbitmq.annotation.Binding
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient
import io.micronaut.context.annotation.Requires
// end::imports[]

@Requires(property = "spec.name", value = "CustomExecutorSpec")
// tag::clazz[]
@RabbitClient // <1>
interface ProductClient {

    @Binding(value = "product") // <2>
    fun send(data: ByteArray)  // <3>
}
// end::clazz[]