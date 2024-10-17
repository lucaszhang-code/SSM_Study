package com.atguigu.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Schedule {
    private int id;
    @NotNull
    private String title;
    @NotNull
    private int completed;
}
