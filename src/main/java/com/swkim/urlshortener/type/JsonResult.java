package com.swkim.urlshortener.type;

public class JsonResult {
	private int code;
	private String message;
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static JsonResult success() {
		JsonResult result = new JsonResult();

		result.code = 0;
		result.message = "success";

		return result;
	}

	public static JsonResult success(Object data) {
		JsonResult result = new JsonResult();

		result.code = 0;
		result.message = "success";
		result.data = data;

		return result;
	}

	public static JsonResult fail() {
		JsonResult result = new JsonResult();

		result.code = -1;
		result.message = "fail";

		return result;
	}

	public static JsonResult fail(int code) {
		JsonResult result = new JsonResult();

		result.code = code;
		result.message = "fail";

		return result;
	}

	public static JsonResult faul(String message) {
		JsonResult result = new JsonResult();

		result.code = -1;
		result.message = message;

		return result;
	}

	public static JsonResult fail(int code, String message) {
		JsonResult result = new JsonResult();

		result.code = code;
		result.message = message;

		return result;
	}

	public static JsonResult fail(int code, String message, String debug) {
		JsonResult result = new JsonResult();

		result.code = code;
		result.message = message;

		return result;
	}
}
