package itmo.remedictes.paymentMicroservice.dto;

import itmo.remedictes.paymentMicroservice.domain.CardAuthorizationInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {
    @NonNull
    private String username;

    @NonNull
    private CardAuthorizationInfo status;
}
