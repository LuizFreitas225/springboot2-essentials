package academy.devdojo.springboot2.exception;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;



@Getter
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails{
    private final String fields;
    private final String fieldMessage;


}
