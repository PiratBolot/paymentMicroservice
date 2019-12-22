package itmo.remedictes.paymentMicroservice.controller;

import itmo.remedictes.paymentMicroservice.domain.Payment;
import itmo.remedictes.paymentMicroservice.dto.UserDetailsDto;
import itmo.remedictes.paymentMicroservice.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final RabbitTemplate rabbitTemplate;
    private PaymentRepository paymentRepository;

    private static final String ORDER_EXCHANGE = "paymentOrderStatus";
    private static final String ORDER_KEY = "payStatus";

    @Autowired
    public PaymentController(RabbitTemplate rabbitTemplate, PaymentRepository paymentRepository){
        this.rabbitTemplate = rabbitTemplate;
        this.paymentRepository = paymentRepository;
    }

    @PutMapping("{orderId}")
    public Payment performPayment(@PathVariable Long orderId,
                                  @RequestBody UserDetailsDto userDetails) {
        log.info("Called perfomedPayment method with parameter order_id = {}, and inputs: CAI = {}, username = {}",
                orderId, userDetails.getStatus(), userDetails.getUsername());
        Payment payment = new Payment(userDetails.getStatus(), orderId, userDetails.getUsername());
        paymentRepository.save(payment);
        String statTmp;
        if (userDetails.getStatus().toString().equals("AUTHORIZED")){
            statTmp="PAYED";
        }
        else {
            statTmp = "FAILED";
        }
        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_KEY,
                "{orderID: " + orderId.toString() + ", status: "+statTmp+"}");
        return payment;
    }

    @GetMapping("{orderId}")
    public Payment getPaymentByOrder(@PathVariable Long orderId){
        return paymentRepository.findByOrderId(orderId);
    }

}
