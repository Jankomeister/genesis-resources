package cz.engeto.genesis_resources.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailDto {
    private Long id;
    private String name;
    private String surname;
    private String personID;
    private String uuid;
}
