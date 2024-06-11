package com.applink.app.update;


import com.applink.app.database.dto.UrlDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResponse {
    private UpdateError updateError;
    private UrlDto urlDto;


    public static UpdateResponse ok(UrlDto urlDto) {
        return UpdateResponse.builder().updateError(UpdateError.SUCCESS).urlDto(urlDto).build();
    }

    public static UpdateResponse error(UpdateError updateError) {
        return UpdateResponse.builder().urlDto(null).updateError(updateError).build();
    }

    public enum UpdateError {
        SUCCESS,
        BELONGS_TO_ANOTHER_USER,
        LINK_EXPIRED
    }
}