/*
 * GWTEventService
 * Copyright (c) 2010, GWTEventService Committers
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
package de.novanic.eventservice.client;

/**
 * The {@link de.novanic.eventservice.client.ClientHandler} can be used to transfer the connection-/client-id to the server-side
 * and to identify the clients. The connection-/client-id is necessary to add user-specific events or domain-user-specific
 * EventFilters dynamically from the server-side.
 *
 * @author sstrohschein
 *         <br>Date: 01.08.2010
 *         <br>Time: 13:06:50
 */
public interface ClientHandler
{
    /**
     * Returns the specific connection-/client-id of the client.
     * @return connection-/client-id of the client
     */
    String getConnectionId();
}