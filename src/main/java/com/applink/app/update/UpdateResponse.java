package com.applink.app.update;


import com.applink.app.url.UrlDto;
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
        return UpdateResponse.builder().updateError(UpdateError.success).urlDto(urlDto).build();
    }

    public static UpdateResponse error(UpdateError updateError) {
        return UpdateResponse.builder().urlDto(null).updateError(updateError).build();
    }

    public enum UpdateError {
        success,
        belongsToAnotherUser,
        linkExpired
    }
}
