import java.lang.module.ModuleDescriptor.Builder;

class HttpRequest {
    private final String url;
    private final String method;
    private final String headers;
    private final String body;
    
    private HttpRequest( Builder builder ) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.body = builder.body;
    }

    public void send() {
        System.out.println("Sending " + method + " request to " + url);
    }

    public static class Builder {
        private String url;
        private String method = "GET";
        private String headers = "";
        private String body = "";

        public Builder(String url) {
            this.url = url;
        }

        public Builder setMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder addHeader(String header) {
            this.headers += header + ";";
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }

}


public class Ex1 {
    public static void main(String[] args) {
        HttpRequest req = new HttpRequest.Builder("https://api.example.com/data")
                            .setMethod("POST")
                            .addHeader("Auth: Bearer token123")
                            .setBody("{'user_id': '404'}")
                            .build();
        req.send();
    }
}
