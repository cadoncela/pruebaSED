package co.edu.sed.domain.model.response;

import lombok.*;

import java.io.Serializable;

/**
 * @author Oliver & Ragnar
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hora;
    private String minutos;
    private String resultado;
}
