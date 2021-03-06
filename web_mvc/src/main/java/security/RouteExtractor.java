package security;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by  Akinniranye James Ayodele on 8/12/16 12:56 PM.
 * Project Andromeda
 * Version 0.1
 */
public class RouteExtractor {

    //something like "/foo/$id<[^/]+>/edit/$james<[^/]+>"
    private String routePattern;
    private String path;

    //something like /$id<[^/]+>
    private static final String INDEX_PATTERN = "\\$(.+?)<\\[\\^/\\]\\+>";

    public RouteExtractor(String routePattern, String path) {

        this.routePattern = routePattern;
        this.path = path;
    }

    private Map<Integer, String> extractPositions() {
        Pattern pattern = Pattern.compile(INDEX_PATTERN);
        Matcher matcher = pattern.matcher(this.routePattern);
        Map<Integer, String> results = new HashMap<>();
        int index = 0;
        while (matcher.find()) {
            results.put(index++, matcher.group(1));
        }
        return results;
    }

    private String replaceRoutePatternWithGroup() {
        Pattern pattern = Pattern.compile(INDEX_PATTERN);
        Matcher matcher = pattern.matcher(this.routePattern);
        return matcher.replaceAll("([^/]+)");
    }

    public Map<String, String> extract() {
        Pattern pattern = Pattern.compile(this.replaceRoutePatternWithGroup());
        Matcher matcher = pattern.matcher(this.path);
        final Map<String, String> results = new HashMap<>();
        if (matcher.find()) {
            this.extractPositions().entrySet().stream().forEach(s -> results.put(s.getValue(), matcher.group(s.getKey() + 1)));
        }
        return results;
    }

}