package co.edu.sed.application.ports.input;

import co.edu.sed.domain.model.response.TimeResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Oliver & Ragnar
 */
public interface FileProcessingServicePort {

    List<TimeResponse> fileProcess(MultipartFile file);

}
