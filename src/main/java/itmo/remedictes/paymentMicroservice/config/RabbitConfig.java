package itmo.remedictes.paymentMicroservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Declarables exchanges(){
        FanoutExchange statusExchange = new FanoutExchange("statusExchange");
        DirectExchange paymentOrderStatus = new DirectExchange("paymentOrderStatus");
        DirectExchange warehouseCommandExchange = new DirectExchange("warehouseCommandExchange");
        Queue paymentStatus = new Queue("paymentStatus", false);

        return new Declarables(
                paymentStatus,
                paymentOrderStatus,
                warehouseCommandExchange,
                statusExchange,
                BindingBuilder.bind(paymentStatus).to(paymentOrderStatus).with("payStatus")
        );
    }

}