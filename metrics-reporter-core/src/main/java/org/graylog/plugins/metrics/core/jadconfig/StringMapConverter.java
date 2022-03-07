/**
 * This file is part of Graylog Metrics Reporter Core Classes.
 *
 * Graylog Metrics Reporter Core Classes is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Reporter Core Classes is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Reporter Core Classes.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.core.jadconfig;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.Map;

public class StringMapConverter implements Converter<Map<String, String>> {
    private static final String SEPARATOR = ",";
    private static final String KEY_VALUE_SEPARATOR = ":";

    @Override
    public Map<String, String> convertFrom(String value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert value \"null\" to a map of strings.");
        }

        try {
            return Splitter.on(SEPARATOR)
                    .omitEmptyStrings()
                    .trimResults()
                    .withKeyValueSeparator(KEY_VALUE_SEPARATOR)
                    .split(value);
        } catch (Exception e) {
            throw new ParameterException(e.getMessage(), e);
        }
    }

    @Override
    public String convertTo(Map<String, String> value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to string.");
        }

        return Joiner.on(SEPARATOR)
                .withKeyValueSeparator(KEY_VALUE_SEPARATOR)
                .useForNull("")
                .join(value);
    }
}
