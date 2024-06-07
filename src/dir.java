import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


class Dir {
// public static void main(String [ ] args) {
//     try {
//         String[] w = readWordlistIntoArray("wordlist.txt");
//         dir("http://google.com", w);
//     } catch (Exception err) {
//         System.out.println(err);
//     }

// }

public static String[] readWordlistIntoArray(String filename) throws IOException {
    filename = System.getProperty("user.dir") + "/" + filename;
    Path path = Paths.get(filename);
    String words = Files.readString(path);
    
    return words.split("\n", 0); // limit 0 meaning any size array and discard trailing empty strings
}

public static void dir(String url, String[] wordlist, String[] filePathWordlist, Options options) {
    for (int word = 0; word < wordlist.length; ++word) {
        if (options.delay) {
                try { Thread.sleep(options.delayTime); } 
                catch (InterruptedException e) { System.err.println("error during delay"); }
        }
        String requestUrl = url + "/" + wordlist[word];

        // make request
        try {
            HttpResponse<String> response = get(requestUrl);
            int statusCode = response.statusCode();

            if (statusCode < 400) {
                System.out.println("[" + statusCode + "] " + "Valid url: " + requestUrl);
                if (options.outputRedirects && (statusCode == 301 || statusCode == 302))
                    System.out.println(requestUrl + " redirects to " + response.uri());
                if (options.extensions) enumerateFiles(requestUrl, filePathWordlist, options);
            } else if (options.showProgress) {
                System.out.println("[" + statusCode + "] " + "Invalid url: " + requestUrl);
            }
        } catch (Exception err) {
            System.out.println("request failed: " + requestUrl);
        }
    }
}


private static HttpResponse<String> get(String uri) throws Exception {
    HttpClient client = HttpClient.newBuilder()
        .followRedirects(Redirect.NORMAL)
        .build();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .build();

    HttpResponse<String> response =
          client.send(request, HttpResponse.BodyHandlers.ofString());

    return response;
}

private static void enumerateFiles(String url, String[] filePathWordlist, Options options) {
    String[] extensions = options.extensionsList.split(",");

    for (int extension = 0; extension < extensions.length; ++extension) { // dont use literal names for indexes
        for (int filePath = 0; filePath < filePathWordlist.length; ++ filePath) {
            if (options.delay) {
                try { Thread.sleep(options.delayTime); } 
                catch (InterruptedException e) { System.err.println("error during delay"); }
            }
            String requestUrl = url + "/" + filePathWordlist[filePath].trim() + "." + extensions[extension].trim();
            try {
                HttpResponse<String> response = get(requestUrl);
                int statusCode = response.statusCode();
                if (statusCode < 400) {
                    System.out.println("[" + statusCode + "] " + "Valid file: " + requestUrl);
                } else if (options.showProgress) {
                    System.out.println("[" + statusCode + "] " + "Invalid file: " + requestUrl);
                }
        } catch (Exception err) {
            System.out.println("request failed: " + requestUrl);
        }
        }
    }  
}
}



