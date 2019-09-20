package com.zed.sftpdemo.annotation.event.model;

import lombok.Data;

/**
 * @author Zeluo
 * @date 2019/9/20 9:08
 */
@Data
public class OrgChangeModel {
    private String type;
    private String action;
    private String organizationId;
    private String correlationId;

    public OrgChangeModel(String type, String action, String organizationId, String correlationId) {
        this.type = type;
        this.action = action;
        this.organizationId = organizationId;
        this.correlationId = correlationId;
    }
}
