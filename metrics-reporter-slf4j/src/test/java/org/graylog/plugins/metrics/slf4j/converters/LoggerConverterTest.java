/**
 * This file is part of Graylog Metrics SLF4J Reporter Plugin.
 *
 * Graylog Metrics SLF4J Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics SLF4J Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics SLF4J Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.slf4j.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class LoggerConverterTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void convertFromValidString() throws Exception {
        assertEquals(LoggerFactory.getLogger("test"), new LoggerConverter().convertFrom("test"));
    }

    @Test
    public void convertFromNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"null\" to logger.");
        new LoggerConverter().convertFrom(null);
    }

    @Test
    public void convertTo() throws Exception {
        assertEquals("test", new LoggerConverter().convertTo(LoggerFactory.getLogger("test")));
    }

    @Test
    public void convertToWithNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert \"null\" to string.");
        new LoggerConverter().convertTo(null);
    }
}