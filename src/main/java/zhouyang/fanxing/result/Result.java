package zhouyang.fanxing.result;

import java.io.Serializable;


/**
 * 建造者模式
 * @param <T>
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    protected boolean success;
    protected String code;
    protected String message;
    protected String tag;
    protected Environment env;
    protected String version;
    protected T data;

    public T get() {
        if (this.isSuccess()) {
            return this.data;
        }
        return null;
    }

    public static Result.Builder success() {
        return new Result.Builder(Boolean.TRUE);
    }

    public static Result.Builder failure() {
        return new Result.Builder(Boolean.FALSE);
    }

    public Result(boolean success, String code, String message, String tag, Environment env, String version, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.tag = tag;
        this.env = env;
        this.version = version;
        this.data = data;
    }

    public Result() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Environment getEnv() {
        return this.env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getData() {
        return this.data;
    }

    public static class Builder {
        protected boolean success = true;
        protected String code = "200";
        protected String message;
        protected String tag;
        protected Environment env;
        protected String version;
        protected Object data;

        protected Builder(boolean success) {
            this.success = success;
        }

        public Result.Builder code(String code) {
            this.code = code;
            return this;
        }

        public Result.Builder message(String message) {
            this.message = message;
            return this;
        }

        public Result.Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Result.Builder env(Environment env) {
            this.env = env;
            return this;
        }

        public Result.Builder version(String version) {
            this.version = version;
            return this;
        }

        public Result.Builder data(Object data) {
            this.data = data;
            return this;
        }

        public <T> Result<T> build() {
            return new Result(this.success, this.code, this.message, this.tag, this.env, this.version, this.data);
        }
    }

}