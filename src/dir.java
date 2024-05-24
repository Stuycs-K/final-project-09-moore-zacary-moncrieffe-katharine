import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


class Dir {

public static void main(String [ ] args) {
String[] w = {"zacm00re","B","poiwutopiuqrt"}; 
dir("http://google.com", w);
}

public static void dir(String url, String[] wordlist) {
    int TIMEOUT_DURATION = 2;

    for (int word = 0; word < wordlist.length; ++word) {
        String requestUrl = url + "/" + wordlist[word];
        // make request
        try {
            HttpResponse<String> response = get(requestUrl);
            int statusCode = response.statusCode();
            if (statusCode < 400) {
                System.out.println("[" + statusCode + "]" + "Valid url: " + requestUrl);
            } else {
                System.out.println("[" + statusCode + "]" + "Invalid url: " + requestUrl);

            }
        } catch (Exception err) {
            System.out.println("request failed: " + requestUrl);

        }
        }

}


public static HttpResponse<String> get(String uri) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(uri))
          .build();

    HttpResponse<String> response =
          client.send(request, HttpResponse.BodyHandlers.ofString());

    return response;
}


}



