package coin.backend.data.dto;

import lombok.Data;

@Data
public class SignUpReq {
    private String user_name;
    private String user_password;
}
