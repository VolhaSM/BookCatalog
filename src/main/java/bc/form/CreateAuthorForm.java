package bc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateAuthorForm {
    @NotNull
    @NotEmpty
    private String name;
}
