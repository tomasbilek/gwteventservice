/*
 * GWTEventService
 * Copyright (c) 2008, GWTEventService Committers
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package de.novanic.eventservice.config;

import java.util.Map;

/**
 * An EventServiceConfiguration holds the configuration for {@link de.novanic.eventservice.client.event.service.EventService}.
 * The time for a timeout and the min- and max-waiting-time can be configured.
 * <br>
 * <br> - Min waiting time - Listening should hold at least for min waiting time.
 * <br> - Max waiting time - Listening shouldn't hold longer than max waiting time.
 * <br> - Timeout time - Max time for a listen cycle. If the timeout time is exceeded, the client will be deregistered.
 *
 * @author sstrohschein
 * <br>Date: 09.08.2008
 * <br>Time: 23:17:26
 */
public interface EventServiceConfiguration
{
    /**
     * Returns the description of the configuration (for example the location).
     * @return configuration description
     */
    String getConfigDescription();

    /**
     * Returns the min waiting time. Listening should hold at least for min waiting time.
     * @return min waiting time
     */
    Integer getMinWaitingTime();

    /**
     * Returns the max waiting time. Listening shouldn't hold longer than max waiting time.
     * @return max waiting time
     */
    Integer getMaxWaitingTime();

    /**
     * Returns the timeout time. The timeout time is the max time for a listen cycle. If the timeout time is exceeded,
     * the client will be deregistered.
     * @return timeout time
     */
    Integer getTimeoutTime();

    /**
     * Returns the class name of the configured {@link de.novanic.eventservice.service.connection.id.ConnectionIdGenerator}.
     * The {@link de.novanic.eventservice.service.connection.id.ConnectionIdGenerator} generates unique ids to identify the clients.
     * @return class name of the configured {@link de.novanic.eventservice.service.connection.id.ConnectionIdGenerator}
     */
    String getConnectionIdGeneratorClassName();

    /**
     * Returns the class name of the configured {@link de.novanic.eventservice.service.connection.strategy.ConnectionStrategy}.
     * A {@link de.novanic.eventservice.service.connection.strategy.ConnectionStrategy} is used to define the communication
     * between the client and the server side.
     * @return class name of the configured {@link de.novanic.eventservice.service.connection.strategy.ConnectionStrategy}
     */
    String getConnectionStrategyClassName();

    /**
     * Returns the configurations as a {@link java.util.Map} with {@link de.novanic.eventservice.config.ConfigParameter}
     * instances as the key.
     * @return {@link java.util.Map} with the configurations with {@link de.novanic.eventservice.config.ConfigParameter}
     * instances as the key
     */
    Map<ConfigParameter, Object> getConfigMap();
}
