package com.desafioIdwall.external.fbiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FbiResponse implements Serializable {

    private String uid;
    private String title;
    @JsonProperty(value = "age_max")
    private String ageMax;
    @JsonProperty(value = "dates_of_birth_used")
    private List<String> datesOfBirthUsed;
    private String sex;
    @JsonProperty(value = "height_max")
    private String heightMax;
    private String hair;
    private List<String> subjects;
    private String nationality;
    private String description;
    private String caution;
    private List<ImagesResponse> images;
}
