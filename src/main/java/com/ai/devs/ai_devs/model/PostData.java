package com.ai.devs.ai_devs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostData {

    String task;
    String apikey;
    String[] answer;
}

