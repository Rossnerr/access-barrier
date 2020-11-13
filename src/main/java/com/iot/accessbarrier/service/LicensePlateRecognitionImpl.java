package com.iot.accessbarrier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.accessbarrier.dto.CarInfoDto;
import com.iot.accessbarrier.dto.alpr.RecognizeLicensePlateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LicensePlateRecognitionImpl implements LicensePlateRecognition {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public CarInfoDto recognizePlateNumber(MultipartFile image) throws IOException {
        return convert(performRequest(image));
    }

    private CarInfoDto convert(RecognizeLicensePlateDTO from) {
        return CarInfoDto.builder()
                .plateNumber(from.getResults().get(0).getPlate())
                .brand(from.getResults().get(0).getVehicle().getMake().get(0).getName())
                .numberOfLeftRequests(from.getCredits_monthly_total() - from.getCredits_monthly_used())
                .build();
    }

    private RecognizeLicensePlateDTO performRequest(MultipartFile image) throws IOException {
        var url = "https://api.openalpr.com/v3/recognize?secret_key=sk_6d56c242fb9d39db923c7ed9&recognize_vehicle=1&country=md&return_image=0&topn=1&is_cropped=0";
        var response = restTemplate
                .postForEntity(url, getRequest(image), RecognizeLicensePlateDTO.class);
        return response.getBody();
    }

    private RecognizeLicensePlateDTO mockRequest() throws IOException {
        var response = objectMapper.readValue(new File("E:\\App\\Java\\accessbarrier\\access-barrier\\src\\main\\java\\com\\iot\\accessbarrier\\service\\test.json"),
                RecognizeLicensePlateDTO.class);
        return response;
    }

    private HttpEntity<MultiValueMap<String, Object>> getRequest(MultipartFile file) throws IOException {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", getFileEntity(file));

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        return requestEntity;
    }

    // This nested HttpEntiy is important to create the correct
    // Content-Disposition entry with metadata "name" and "filename"
    private HttpEntity<byte[]> getFileEntity(MultipartFile file) throws IOException {
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("image")
                .filename("filename")
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        return new HttpEntity<>(file.getBytes(), fileMap);
    }


}
