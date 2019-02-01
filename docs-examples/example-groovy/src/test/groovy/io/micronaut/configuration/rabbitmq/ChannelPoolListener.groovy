package io.micronaut.configuration.rabbitmq

import com.rabbitmq.client.Channel
import io.micronaut.configuration.rabbitmq.connect.ChannelPool
import io.micronaut.context.event.BeanCreatedEvent
import io.micronaut.context.event.BeanCreatedEventListener

import javax.inject.Singleton

@Singleton
class ChannelPoolListener implements BeanCreatedEventListener<ChannelPool> {

    @Override
    ChannelPool onCreated(BeanCreatedEvent<ChannelPool> event) {
        ChannelPool pool = event.bean
        try {
            Channel channel = pool.channel
            channel.queueDeclare("product", false, false, false, [:])
            pool.returnChannel(channel)
        } catch (IOException e) {
            //no-op
        }
        pool
    }
}