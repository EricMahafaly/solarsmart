package com.solarsmart.frontoffice.models.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solarsmart.frontoffice.models.entities.RelaisState;
import com.solarsmart.frontoffice.models.entities.RelayStateEnum;
import lombok.Data;

@Data
public class RelaisStateInfo {

    private String state;
    private boolean active;

    public RelaisStateInfo(RelaisState relaisState){
        this.state = relaisState.getState().toString();
        this.setActive(relaisState.getActive());
//        if (relaisState.getState() == RelayStateEnum.HIGH) this.
    }

//    public void setActive(RelayStateEnum stateEnum) {
//        this.active = stateEnum == RelayStateEnum.HIGH;
//    }
}
