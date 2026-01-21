package pl.mp.barbershopbookingapi.rest.internal.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CreateClientRequest {
    @NotBlank(message = "firstName can not be blank")
    @Size(max = 200, message = "max length is 200")
    @Pattern(regexp = "^\\p{Lu}\\p{Ll}+$", message = "firstName should be valid")
    private String firstName;
    @NotBlank(message = "lastName can not be blank")
    @Size(max = 200, message = "max length is 200")
    @Pattern(regexp = "^\\p{Lu}\\p{Ll}+$", message = "lastName should be valid")
    private String lastName;
    @Email(message = "email should be valid")
    @NotBlank(message = "email can not be blank")
    private String email;
    @Pattern(regexp = "^(\\+?[0-9]{2})?[0-9]{9}$", message = "phone should be valid")
    @Size(min = 9, max = 20, message = "min length is 9 and max length is 20")
    @NotNull(message = "phone number can not be null")
    private String phone;
}
