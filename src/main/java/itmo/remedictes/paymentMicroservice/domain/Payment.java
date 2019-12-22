package itmo.remedictes.paymentMicroservice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="payment")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    @NonNull
    private CardAuthorizationInfo cardStatus;

    @NonNull
    private Long orderId;

    @NonNull
    private String username;
}
