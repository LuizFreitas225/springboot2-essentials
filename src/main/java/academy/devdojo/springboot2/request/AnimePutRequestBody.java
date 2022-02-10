package academy.devdojo.springboot2.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePutRequestBody {
    @NotNull(message = "The anime name cannot be null")
    private Long id;
    @NotEmpty(message = "The anime name cannot be empty")
    private  String name;


}
