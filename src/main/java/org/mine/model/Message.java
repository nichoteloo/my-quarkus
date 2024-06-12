package org.mine.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private String origin;
    private String role;
}
