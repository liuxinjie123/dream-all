package com.dream.vo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude
public class Result<T> implements Serializable {
    private static final ObjectMapper MAPPER = new ObjectMapper();	// 定义jackson对象
    private String code;
    private String msg;
    private Page meta;
    private T data;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result setMeta(Page meta) {
        this.meta = meta;
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public static Result success(){
        return new Result().setCode(Constants.CODE_SUCCESS).setMsg(Constants.MSG_SUCCESS);
    }

    public static Result error(String msg){
        return new  Result().setCode(Constants.CODE_FAILURE).setMsg(msg);
    }

    public static Result error(String code, String msg){
        return new  Result().setCode(code).setMsg(msg);
    }

    public static Result build(String code, String msg) {
        return new Result(code, msg);
    }

    public static Result build(String code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    /**
     * 将json结果集转化为 Result 对象
     *
     * @param jsonData json数据
     * @param clazz ITDragonResult中的object类型
     * @return
     */
    public static Result formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, Result.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(String.valueOf(jsonNode.get("status")), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static Result format(String json) {
        try {
            return MAPPER.readValue(json, Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static Result formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(String.valueOf(jsonNode.get("status")), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
