package io.micronaut.configuration.rabbitmq.docs.consumer.custom.annotation;

// tag::imports[]
import io.micronaut.configuration.rabbitmq.annotation.Queue;
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener;
import io.micronaut.context.annotation.Requires;

import java.util.*;
// end::imports[]

@Requires(property = "spec.name", value = "DeliveryTagSpec")
// tag::clazz[]
@RabbitListener
public class ProductListener {

    Set<Long> messages = Collections.synchronizedSet(new HashSet<>());

    @Queue("product")
    public void receive(byte[] data, @DeliveryTag Long tag) { // <1>
        messages.add(tag);
    }
}
// end::clazz[]
