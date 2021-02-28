package bc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateAuthorForm {

    private Long id;
    @NotNull
    @NotEmpty
    private String name;
}
