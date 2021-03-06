package io.micronaut.configuration.rabbitmq.docs.consumer.executor

import io.kotlintest.eventually
import io.kotlintest.seconds
import io.kotlintest.shouldBe
import io.micronaut.configuration.rabbitmq.AbstractRabbitMQTest
import org.opentest4j.AssertionFailedError

class CustomExecutorSpec : AbstractRabbitMQTest({

    val specName = javaClass.simpleName

    given("A basic producer and consumer") {
        val config = AbstractRabbitMQTest.getDefaultConfig(specName)
        config["micronaut.executors.product-listener.type"] = "FIXED"

        val ctx = startContext(config)

        `when`("the message is published") {
            val productListener = ctx.getBean(ProductListener::class.java)

// tag::producer[]
            val productClient = ctx.getBean(ProductClient::class.java)
            productClient.send("quickstart".toByteArray())
// end::producer[]

            then("the message is consumed") {
                eventually(10.seconds, AssertionFailedError::class.java) {
                    productListener.messageLengths.size shouldBe 1
                    productListener.messageLengths[0] shouldBe "quickstart"
                }
            }
        }

        Thread.sleep(200)
        ctx.stop()
    }
})
