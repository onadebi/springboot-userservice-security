package dev.onaxsys.onaxsecurity.utils;

public final class GenResponse<T> {
    public String message;
    public T data;
    public boolean success;
    public StatusEnum statCode;

    public GenResponse() {
        this.success = false;
        this.statCode = StatusEnum.OK;
    }


    public static <T> GenResponse<T> success(T data, String message, StatusEnum statCode) {
        GenResponse<T> response = new GenResponse<>();
        response.data = data;
        response.success = true;
        response.message = message;
        response.statCode = statCode;
        return response;
    }

    public static <T> GenResponse<T> success(T data, String message) {
        GenResponse<T> response = new GenResponse<>();
        response.data = data;
        response.success = true;
        response.message = message;
        response.statCode = StatusEnum.OK;
        return success(data, message, response.statCode);
    }

    public static <T> GenResponse<T> failed(T data, String errorMessage, StatusEnum statCode) {
        GenResponse<T> response = new GenResponse<>();
        response.data = data;
        response.success = false;
        response.message = errorMessage;
        response.statCode = statCode;
        return response;
    }

    public static <T> GenResponse<T> failed(T data, String errorMessage) {
        GenResponse<T> response = new GenResponse<>();
        response.data = data;
        response.success = false;
        response.message = errorMessage;
        response.statCode = StatusEnum.NO_CHANGE;
        return failed(data, errorMessage, response.statCode);
    }
}