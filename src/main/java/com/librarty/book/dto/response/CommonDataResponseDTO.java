package com.librarty.book.dto.response;

public class CommonDataResponseDTO {
    private boolean success;
    private Object content;

    public CommonDataResponseDTO() {
    }

    public CommonDataResponseDTO(boolean success, Object content) {
        this.success = success;
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommonDataResponseDTO{" +
                "success=" + success +
                ", content=" + content +
                '}';
    }
}
