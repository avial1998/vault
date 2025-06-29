package com.avinash.vault.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        description = "Response Data Transfer Object",
        title = "ResponseDto")
public class ResponseDto {
    @Schema(
            description = "Status of the response",
            example = "success")
    private String status;
    @Schema(
            description = "Message providing additional information about the response",
            example = "Credential created successfully")
    private String message;
}
