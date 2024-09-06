package com.example.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteResponse {
    private String message;
    private HttpStatus status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime dateTime;
}
