package com.solarsmart.frontoffice.models.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class DeleteManyRequest<T> {
    private List<T> data;
}
