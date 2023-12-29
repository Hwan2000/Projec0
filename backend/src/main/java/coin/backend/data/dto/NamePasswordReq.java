package coin.backend.data.dto;

import lombok.Data;

@Data
public class NamePasswordReq {
    private String userName;
    private String userPassword;
}
