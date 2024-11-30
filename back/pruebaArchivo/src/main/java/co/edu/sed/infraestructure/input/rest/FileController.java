package co.edu.sed.infraestructure.input.rest;

import co.edu.sed.application.ports.input.FileProcessingServicePort;
import co.edu.sed.domain.model.response.TimeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Oliver & Ragnar
 */
@RestController
@RequestMapping("/archivo")
@RequiredArgsConstructor
public class FileController {
    private final FileProcessingServicePort service;

    @GetMapping("/sumar/{param}/{param2}")
    public ResponseEntity<Integer> sumarNumerosGet(
                                        @PathVariable int param,
                                        @PathVariable int param2) {

        int sum = param+param2;
        System.out.println("PruebaArchivo.sumarNumerosGet. Resultado suma : " + sum);
        return new ResponseEntity<Integer>(sum, HttpStatus.PARTIAL_CONTENT);
    }

    @PostMapping("/process")
    public ResponseEntity<List<TimeResponse>> processFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).body(service.fileProcess(file));
    }
}
