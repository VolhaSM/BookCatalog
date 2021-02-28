package bc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UpdateBookForm {

    private long id;
    @NotNull
    @NotEmpty
    private String bookName;
    private String shortDescription;
    @NotNull
    @NotEmpty
    private Set<Long> authors;
}
