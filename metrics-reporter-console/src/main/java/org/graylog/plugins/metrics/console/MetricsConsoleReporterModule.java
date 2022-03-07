/**
 * This file is part of Graylog Metrics Console Reporter Plugin.
 *
 * Graylog Metrics Console Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Console Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Console Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.console;

import com.codahale.metrics.ConsoleReporter;
import org.graylog.plugins.metrics.console.providers.ConsoleReporterProvider;
import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;

import java.util.Collections;
import java.util.Set;

public class MetricsConsoleReporterModule extends PluginModule {
    @Override
    public Set<? extends PluginConfigBean> getConfigBeans() {
        return Collections.singleton(new MetricsConsoleReporterConfiguration());
    }

    @Override
    protected void configure() {
        bind(ConsoleReporter.class).toProvider(ConsoleReporterProvider.class);
        
        addConfigBeans();
        addInitializer(MetricsConsoleReporterService.class);
    }
}
