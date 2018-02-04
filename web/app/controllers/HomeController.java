package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import play.db.jpa.Transactional;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
@Transactional
public class HomeController extends Controller {

    @Inject
    ObjectMapper objectMapper;

    public Result index() {

        return ok();
    }

    public Result preflight(String all) {

        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Allow-Credentials", "true");
        response().setHeader("Allow", "*");
        response().setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent, Authorization");
        return noContent();
    }

    public Result swagger() {
        return ok(views.html.swagger.render());
    }

    public Result api() {
        return ok(views.html.api.render());
    }

}
