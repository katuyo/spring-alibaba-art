package org.featx.katuyo.springali.model;

public class ErrorResponse extends BaseResponse<Error> {

    public ErrorResponse(Error error) {
        setSuccess(false);
        setModel(error);
        setCode("");
    }

    @Override
    public ErrorResponse setSuccess(Boolean success) {
        super.setSuccess(false);
        return this;
    }
}
