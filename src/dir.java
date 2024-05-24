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
        System.out.println(requestUrl);
        // make request
        try {
            get(requestUrl);
        } catch (Exception err) {
            System.out.println(err);
        }
        }

}


public static void get(String uri) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(uri))
          .build();

    HttpResponse<String> response =
          client.send(request, HttpResponse.BodyHandlers.ofString());

    System.out.println(response.body());
}


}



