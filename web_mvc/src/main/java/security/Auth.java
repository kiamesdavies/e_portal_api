/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.portal.commons.models.AuthorizationRole;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import play.mvc.With;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
@With(AuthorizationAction.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    AuthorizationRole[] roles() default {};

    //the route must have appUserEmail
    boolean requireAppUserId() default false;

}
