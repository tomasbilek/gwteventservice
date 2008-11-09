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
package de.novanic.eventservice.service.testhelper;

import de.novanic.eventservice.client.event.domain.Domain;
import de.novanic.eventservice.client.event.Event;

import java.util.Map;
import java.util.Collection;

/**
 * @author sstrohschein
 * <br>Date: 21.08.2008
 * <br>Time: 23:50:25
 */
public class ListenResult
{
    private Map<Domain, Collection<Event>> myDomainEvents;
    private Map<String, Collection<Event>> myUserEvents;

    public ListenResult(Map<Domain, Collection<Event>> aDomainEvents, Map<String, Collection<Event>> aUserEvents) {
        myDomainEvents = aDomainEvents;
        myUserEvents = aUserEvents;
    }

    public int getEventCount() {
        int theEventCount = 0;
        for(Domain theDomain: myDomainEvents.keySet()) {
            theEventCount += getEventCount(theDomain);
        }
        for(String theUser: myUserEvents.keySet()) {
            theEventCount += getEventCount(theUser);
        }
        return theEventCount;
    }

    public int getEventCount(Domain aDomain) {
        final Collection<Event> theEvents = myDomainEvents.get(aDomain);
        if(theEvents != null) {
            return theEvents.size();
        }
        return 0;
    }

    public int getEventCount(String aUser) {
        final Collection<Event> theEvents = myUserEvents.get(aUser);
        if(theEvents != null) {
            return theEvents.size();
        }
        return 0;
    }
}