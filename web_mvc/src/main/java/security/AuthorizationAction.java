/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.inject.Provider;
import com.portal.commons.models.AuthorizationRole;
import com.portal.user_management.Authentication;
import java.util.Arrays;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

/**
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
@Transactional
public class AuthorizationAction extends Action<Auth> {

    @Inject
    Provider<Authentication> iAuthentication;

    @Inject
    JPAApi jPAApi;

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {

        CompletableFuture<Result> completableFuture = jPAApi.withTransaction(() -> {

            if (AppAuthenticator.getAppUser() == null) {
                if (AppAuthenticator.putUserInContext(ctx, jPAApi, iAuthentication) == null) {
                    return CompletableFuture.completedFuture(unauthorized("You have not been authenticated"));
                }

            }

            String roleName = AppAuthenticator.getRoleId();

            if (roleName.equalsIgnoreCase(AuthorizationRole.ADMIN.name())) {
                return null;
            }

            String appUserId = AppAuthenticator.getAppUser().getAppUserId();
            String projectId = null;

            if (configuration.requireAppUserId()) {
                if (!appUserId.equalsIgnoreCase(getParameterValue(ctx, "appUserId"))) {
                    return CompletableFuture.completedFuture(unauthorized("You only have access to your own records"));
                }

            }

            if (configuration.roles() != null && configuration.roles().length > 0 && !Arrays.asList(configuration.roles()).contains(roleName)) {
                return CompletableFuture.completedFuture(unauthorized("You dont belong to role " + roleName));

            }

            return null;
        });

        if (completableFuture == null) {
            return delegate.call(ctx);
        }
        return completableFuture;

    }

    public static String getParameterValue(Http.Context ctx, String parameterName) {

        RouteExtractor routeExtractor = new RouteExtractor((String) ctx.args.get("ROUTE_PATTERN"), ctx.request().path());
        Map<String, String> extract = routeExtractor.extract();
        if (extract.containsKey(parameterName)) {
            return extract.get(parameterName);
        }
        return null;
    }

}
