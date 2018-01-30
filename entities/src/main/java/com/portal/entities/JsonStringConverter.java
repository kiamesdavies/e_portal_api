/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.sql.SQLException;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.postgresql.util.PGobject;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
@Converter
public class JsonStringConverter implements AttributeConverter<String, PGobject> {

    @Override
    public PGobject convertToDatabaseColumn(String attribute) {
        try {
            PGobject po = new PGobject();
            po.setType("json");
            po.setValue(attribute);
            return po;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String convertToEntityAttribute(PGobject dbData) {
        return dbData == null ? null: dbData.getValue();
    }

}
