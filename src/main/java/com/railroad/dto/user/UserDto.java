package com.railroad.dto.user;

import lombok.Data;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Data transfer object that represents UserEntity
 *
 * @author Stanislav Popovich
 */

@Data
public class UserDto {

    @Size(min = 3, max = 25, message = "{username.size.error}")
    private String userName;
    @Size(min = 4, message = "{password.size.error}")
    private String password;
    @Size(min = 4, message = "{confirmPassword.size.error}")
    private String confirmPassword;

    private Set<String> roles;

}
