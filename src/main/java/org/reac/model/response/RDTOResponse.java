package org.reac.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.reac.model.RDTO;
import org.reac.model.common.APIResponse;

@Data
@EqualsAndHashCode(callSuper=false)
public class RDTOResponse extends APIResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    RDTO data;
    private int statusCode;

    public RDTOResponse(RDTO data, int statusCode) {
        this.data = data != null ? data : new RDTO();
        this.statusCode = statusCode;
    }
}
