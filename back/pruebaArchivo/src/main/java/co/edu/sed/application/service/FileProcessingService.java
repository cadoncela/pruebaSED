package co.edu.sed.application.service;

import co.edu.sed.application.ports.input.FileProcessingServicePort;
import co.edu.sed.domain.model.response.TimeResponse;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oliver & Ragnar
 */
@Service
@RequiredArgsConstructor
public class FileProcessingService implements FileProcessingServicePort {
    @Override
    public List<TimeResponse> fileProcess(MultipartFile file) {
        List<TimeResponse> responseList = new ArrayList<>();
        try{
            if(file.isEmpty()){
                throw new RuntimeException("File is empty");
            }
            String fileName = file.getOriginalFilename();
            if(fileName.endsWith(".txt")){
                responseList = generateResponseList(file);
            } else {
                throw new RuntimeException("File is not txt");
            }
        } catch (Exception e){
            throw new RuntimeException("Error processing file: " + e.getMessage());
        }
        return responseList;
    }

    private List<TimeResponse> generateResponseList(MultipartFile file) throws IOException {
        List<TimeResponse> responseList = new ArrayList<>();
        InputStream is = file.getInputStream();
        @Cleanup BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine())!= null) {
            System.out.println(" " + line);
            String[] timeArray = line.split(":");
            if (timeArray.length >0){
                int hour = Integer.parseInt(timeArray[0]);
                int minute = Integer.parseInt(timeArray[1]);
                if (hour < 25 && minute < 60) {
                    responseList.add(printTime(hour, minute));
                } else {
                    throw new RuntimeException("incorrect time Format");
                }
            }
        }
        return responseList;
    }

    private TimeResponse printTime(int h, int m)
    {
        TimeResponse response = new TimeResponse();
        String nums[] = { "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve", "thirteen",
                "fourteen", "fifteen", "sixteen", "seventeen",
                "eighteen", "nineteen", "twenty", "twenty one",
                "twenty two", "twenty three", "twenty four",
                "twenty five", "twenty six", "twenty seven",
                "twenty eight", "twenty nine",
        };

        if (m == 0) {
             response = TimeResponse.builder().hora(""+h).minutos(""+m).resultado(nums[h] + " o' clock ").build();
            System.out.println(nums[h] + " o' clock ");


        } else if (m == 1) {
             response = TimeResponse.builder().hora(""+h).minutos(""+m).resultado("one minute past " +
                    nums[h]).build();
            System.out.println("one minute past " +
                    nums[h]);

        } else if (m == 59) {
             response = TimeResponse.builder().hora(""+h).minutos(""+m).resultado("one minute to " +
                    nums[(h % 12) + 1]).build();
            System.out.println("one minute to " +
                    nums[(h % 12) + 1]);

        }else if (m == 15){
             response = TimeResponse.builder().hora(""+h).minutos(""+m).resultado("quarter past " + nums[h]).build();
            System.out.println("quarter past " + nums[h]);

        }else if (m == 30) {
             response = TimeResponse.builder().hora(""+h).minutos(""+m).resultado("half past " + nums[h]).build();
            System.out.println("half past " + nums[h]);

        }else if (m == 45) {
             response = TimeResponse.builder().hora(""+h).minutos(""+m).resultado("quarter to " +
                    nums[(h % 12) + 1]).build();
            System.out.println("quarter to " +
                    nums[(h % 12) + 1]);

        }else if (m <= 30) {
             response = TimeResponse.builder().hora(""+h).minutos(""+m).resultado(nums[m] + " minutes past " +
                    nums[h]).build();
            System.out.println(nums[m] + " minutes past " +
                    nums[h]);

        }else if (m > 30) {
             response = TimeResponse.builder().hora(""+h).minutos(""+m).resultado(nums[60 - m] + " minutes to " +
                    nums[(h % 12) + 1]).build();
            System.out.println(nums[60 - m] + " minutes to " +
                    nums[(h % 12) + 1]);
        }
        return response;
    }
}
