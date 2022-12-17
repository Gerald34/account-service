package labworx.io.services.account.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import labworx.io.services.account.exceptions.BankAccountException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
@RequiredArgsConstructor
@Slf4j
public class IDValidationService {
    private final String rapidHost = System.getenv("RAPID_HOST");
    private final String headerHost = System.getenv("RAPID_HEADERS_HOST");
    private final String key = System.getenv("RAPID_API_KEY");
    private final WebClient webClient;

    public ValidationResponse validate(ValidationDto validationDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var data = mapper.writeValueAsString(validationDto);
        log.info("Running ID {}, validation request.", validationDto.getIdno());
        try {
            var response =  webClient
                    .post()
                    .uri(rapidHost)
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(httpHeaders -> {
                        httpHeaders.set("X-RapidAPI-Key", key);
                        httpHeaders.set("X-RapidAPI-Host", headerHost);
                    }).bodyValue(data)
                    .retrieve()
                    .toEntity(ValidationResponse.class).block();

            if (response == null) log.error("Response returned null.");
            assert response != null;
            return response.getBody();
        } catch (WebClientException e) {
            log.error("Rapid API exception: " + rapidHost + ": " + e.getMessage());
            throw new BankAccountException(e.getMessage(), e.getCause());
        }
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class ValidationDto {
        public String idno;
    }

    @Getter
    public static class ValidationResponse {
        public Boolean valid;
    }
}
