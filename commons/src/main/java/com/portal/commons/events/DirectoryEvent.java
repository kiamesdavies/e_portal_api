/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.events;

import java.io.File;

/**
 *
 * @author poseidon
 */
public class DirectoryEvent implements GenericEvents {

    public final String key;
    public final File file;

    public static class Builder {

        private String key;
        private File file;

        private Builder() {
        }

        public Builder key(final String value) {
            this.key = value;
            return this;
        }

        public Builder file(final File value) {
            this.file = value;
            return this;
        }

        public DirectoryEvent build() {
            return new DirectoryEvent(key, file);
        }
    }

    public static DirectoryEvent.Builder builder() {
        return new DirectoryEvent.Builder();
    }

    private DirectoryEvent(final String key, final File file) {
        this.key = key;
        this.file = file;
    }

}
