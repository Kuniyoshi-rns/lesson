package com.example.lesson.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
public class EditForm {

    @NotEmpty(message = "{val.notEmpty.mes}")
    @Length(min = 1,max = 50,message = "{val.length.mes}")
    private String name;

    @NotEmpty(message = "{val.notEmpty.mes}")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "{val.range.mes}")
    private String price;

    public int getPriceInt() {
        return Integer.parseInt(price);
    }
}
