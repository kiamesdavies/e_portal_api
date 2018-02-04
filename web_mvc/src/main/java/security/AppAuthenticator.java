package security;

import com.google.inject.Provider;
import com.portal.commons.exceptions.InvalidCredentialsException;
import com.portal.commons.models.AppUser;
import com.portal.user_management.Authentication;
import play.Logger;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import org.javatuples.Pair;
import play.mvc.Http.Cookie;
/**
 * Created by James Akinniranye on 6/25/2015:8:32 PM. Project : andromeda-server
 */
@Transactional
public class AppAuthenticator extends play.mvc.Action.Simple {

    @Inject
    Provider<Authentication> iAuthentication;

    @Inject
    JPAApi jPAApi;

    @Inject
    JPAApi provider;

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        if (putUserInContext(ctx, jPAApi, iAuthentication) == null) {
            return CompletableFuture.completedFuture(unauthorized("You have not been authenticated"));
        }
        return delegate.call(ctx);
    }
    private static final Function<Cookie, String> tokenFromCookie = (cook) -> {
        return cook != null ? cook.value() : null;
    };

    public static String putUserInContext(Http.Context ctx, JPAApi jPAApi, Provider<Authentication> iAuthentication) {
        try {

            String token = ctx.request().hasHeader("Authorization") ? ctx.request().getHeader("Authorization") : tokenFromCookie.apply(ctx.request().cookie("token"));
            if (ctx.args.containsKey("appUser") && ctx.args.get("appUser") != null) {
                return token;
            }
            Supplier< String> function = () -> {

                try {
                    Pair<AppUser, String> triplet = iAuthentication.get().tokenIsValid(token);
                    AppUser appUser = triplet.getValue0();
                    if (appUser != null) {
                        ctx.args.put("appUser", appUser);
                        ctx.args.put("roleId", triplet.getValue1());

                        return token;
                    }

                } catch (InvalidCredentialsException ex) {
                } catch (IllegalStateException ex) {
                    java.util.logging.Logger.getLogger(AppAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            };

            return function.get();
        } catch (Exception ex) {
            Logger.error("Exception with Authentication", ex);
        }
        return null;
    }

    public static AppUser getAppUser() {
        Object get = Http.Context.current().args.get("appUser");
        if (get != null) {
            return (AppUser) get;
        }
        return null;
    }

    public static String getRoleId() {
        return (String) Http.Context.current().args.get("roleId");
    }

//    public static Optional<String> getOrganizationId() {
//        return (Optional<String>) Http.Context.current().args.get("organizationId");
//    }
}
