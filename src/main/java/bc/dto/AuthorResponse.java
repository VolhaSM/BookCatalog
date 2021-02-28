package bc.dto;

import bc.model.AuthorModel;
import lombok.Data;

@Data
public class AuthorResponse {
    private Long id;
    private String authorName;

    public AuthorResponse(AuthorModel model) {

        this.id = model.getId();
        this.authorName = model.getName();
    }
}
