/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.web;

import com.portal.commons.exceptions.InvalidCredentialsException;
import com.portal.commons.models.AppUser;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.commons.util.Utility;
import com.portal.commons.util.XFormUtility;
import com.portal.user_management.Authentication;
import org.javatuples.Pair;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
@Singleton
public class Helper {

    public static final String API_SEVER_URL = EnvironMentVariables.API_SERVER_URL;
    public static final String APP_SEVER_URL = EnvironMentVariables.APP_SERVER_URL;

    public static final String ENKETO_SEVER_URL = EnvironMentVariables.ENKETO_SERVER_URL;
    public static final String COOKIE_SEVER_URL = EnvironMentVariables.COOKIE_SERVER_URL;

    @Inject
    Authentication iAuthentication;

    @Inject
    XFormUtility xFormUtility;

    public static Optional<Pair<Double, Double>> extractCordinates(String content) {
        Pair<Double, Double> doubles = null;
        Pattern pattern = Pattern.compile("((-?[0-9]{1,2}\\.[0-9]+)\\s+(-?[0-9]{1,2}\\.[0-9]+))");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find() && matcher.groupCount() >= 3) {
            String latitude = matcher.group(2);
            String longitude = matcher.group(3);
            doubles = Pair.with(Double.parseDouble(latitude), Double.parseDouble(longitude));
        }
        return Optional.ofNullable(doubles);
    }

    public Optional<AppUser> getAppUserFromToken(String token) {
        AppUser appUser = null;
        try {
            if (Objects.nonNull(token)) {
                appUser = iAuthentication.tokenIsValid(token).getValue0();
            }
        } catch (InvalidCredentialsException e) {

        }
        return Optional.ofNullable(appUser);
    }

    public static Integer parseInt(String s) {
        return Utility.parseInt(s);
    }

    public List<Pair<String, String>> convertXlsForm(File file, String formVersionId) throws IOException {
        return xFormUtility.combineXLSJsonConversion(file, formVersionId);

    }
}
