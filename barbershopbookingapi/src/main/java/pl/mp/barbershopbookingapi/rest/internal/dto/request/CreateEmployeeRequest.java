package pl.mp.barbershopbookingapi.rest.internal.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateEmployeeRequest {
    @NotBlank(message = "firstName can not be blank")
    @Size(max = 200, message = "max length is 200")
    @Pattern(regexp = "^\\p{Lu}\\p{Ll}+$", message = "firstName should be valid")
    private String firstName;
    @NotBlank(message = "lastName can not be blank")
    @Size(max = 200, message = "max length is 200")
    @Pattern(regexp = "^\\p{Lu}\\p{Ll}+$", message = "lastName should be valid")
    private String lastName;
    @NotBlank(message = "email can not be blank")
    @Email(message = "email should be valid")
    private String email;
    @Pattern(regexp = "^(\\+?[0-9]{2})?[0-9]{9}$", message = "phone should be valid")
    @Size(min = 9, max = 20, message = "min length is 9 and max length is 20")
    @NotBlank(message = "phone number can not be blank")
    private String phone;
}
