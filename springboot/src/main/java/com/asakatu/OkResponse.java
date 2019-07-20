package hello;

public class OkResponse {
	private Object data;

	public OkResponse(Object data) {
		this.data = data;
	}

	public Integer getStatus() {
		return 200;
	}

	public Object getData() {
		return data;
	}
}