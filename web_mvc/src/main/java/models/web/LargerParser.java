/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.web;

import javax.inject.Inject;
import play.http.HttpErrorHandler;
import play.mvc.BodyParser;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class LargerParser extends BodyParser.Text {

    @Inject
    public LargerParser(HttpErrorHandler errorHandler) {
        super(10 * 1024 * 1024, errorHandler);
    }
}
