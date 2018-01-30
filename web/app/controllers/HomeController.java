package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.markserrano.jsonquery.jpa.util.DateTimeUtil;
import com.github.markserrano.jsonquery.jpa.util.QueryUtil;
import com.traccar.positions.DevicePositionsResource;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.text.ParseException;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
@Transactional
public class HomeController extends Controller {

    @Inject
    DevicePositionsResource devicePositionsResource;

    @Inject
    ObjectMapper objectMapper;

    /**
     * An action that renders an HTML page with a welcome message. The
     * configuration in the <code>routes</code> file means that this method will
     * be called when the application receives a <code>GET</code> request with a
     * path of <code>/</code>.
     */
    public Result index() {
        Config conf = ConfigFactory.load();
        return ok(index.render(conf.getString("application_server_url")));
    }

    public Result positions(String start, String end, String search, String filters, Integer page, String rows, String sidx, String sord) throws JsonProcessingException, ParseException {
        filters = StringUtils.isBlank(filters) ? QueryUtil.INIT_FILTER : filters;
        return ok(objectMapper.writeValueAsString(devicePositionsResource.getDevicePositions(DateTimeUtil.SIMPLE_DATE_WITH_TIME_FORMAT.parse(start), DateTimeUtil.SIMPLE_DATE_WITH_TIME_FORMAT.parse(end), Boolean.parseBoolean(search), filters, page, StringUtils.isBlank(rows) ? null : Integer.parseInt(rows), sidx, sord)));

    }
}
