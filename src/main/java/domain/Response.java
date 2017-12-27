package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shakhawat.hossain
 * @since 12/26/17
 */
public class Response {

    public enum STATUS {
        @JsonProperty("ok")
        OK,

        @JsonProperty("error")
        ERROR
    }

    private STATUS status;

    public Response(STATUS status) {
        this.status = status;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
}
