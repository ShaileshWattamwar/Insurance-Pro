package pet_Insurance.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "owners")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerDeteail {
    @Id
    private String ownerId;
    private String OwnerName;
    private String email;
    private String phoneNo;
    private String OwnerAddress;
}
