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
package de.novanic.eventservice.client.event;

import de.novanic.eventservice.client.event.listener.unlisten.UnlistenEventListenerAdapter;
import de.novanic.eventservice.client.event.listener.unlisten.DefaultUnlistenEvent;

/**
 * @author sstrohschein
 * Date: 20.07.2008
 * Time: 13:53:49
 */
public class RemoteEventServiceTest extends RemoteEventServiceLiveTest
{
    public void testAddListener() {
        assertFalse(myRemoteEventService.isActive());

        //start listen
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.addListener(TEST_DOMAIN, getListener(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(0, getEventCount());
            }
        });

        //add event
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN, new DummyEvent(), getCallback());
            }
        });

        //add another event
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(1, getEventCount());
                myEventService.addEvent(TEST_DOMAIN, new DummyEvent(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(2, getEventCount());
            }
        });

        executeActions();
    }

    public void testAddListener_Concurrent() {
        assertFalse(myRemoteEventService.isActive());

        //add second listener
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.addListener(TEST_DOMAIN_2, getListener(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(0, getEventCount());
            }
        });

        //add event
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN, new DummyEvent(), getCallback());
            }
        });
        //add another event
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN_2, new DummyEvent(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(2, getEventCount());
            }
        });

        //start first listen call
        myRemoteEventService.addListener(TEST_DOMAIN, myGlobalListener);
        executeActions();
    }

    public void testRemoveListener() {
        //start listen
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.addListener(TEST_DOMAIN, getListener(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(0, getEventCount());
            }
        });

        //add event
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN, new DummyEvent(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(1, getEventCount());
            }
        });

        //remove listener / stop listening
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.removeListener(TEST_DOMAIN, getListener(), getCallback());
            }
        });

        //add ignored event
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN, new DummyEvent(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(1, getEventCount());
            }
        });
    }

    public void testRemoveListeners() {
        assertFalse(myRemoteEventService.isActive());

        //start listen for TEST_DOMAIN
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.addListener(TEST_DOMAIN, getListener(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(0, getEventCount());
            }
        });

        //start listen for TEST_DOMAIN_2
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.addListener(TEST_DOMAIN_2, getListener(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(0, getEventCount());
            }
        });

        //start listen for TEST_DOMAIN_3
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.addListener(TEST_DOMAIN_3, getListener(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(0, getEventCount());
            }
        });

        //register unlisten listener
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.addUnlistenListener(new UnlistenEventListenerAdapter(), new DefaultUnlistenEvent(), getCallback());
            }
        });

        //add event to TEST_DOMAIN_2
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN_2, new DummyEvent(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(1, getEventCount());
            }
        });

        //add event to TEST_DOMAIN
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN, new DummyEvent(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(2, getEventCount());
            }
        });

        //add event to TEST_DOMAIN_3
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN_3, new DummyEvent(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(3, getEventCount());
            }
        });

        //remove listener / stop listening for TEST_DOMAIN
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.removeListener(TEST_DOMAIN, getListener(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
            }
        });

        //remove the other listeners / stop listening for TEST_DOMAIN_2 and TEST_DOMAIN_3
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.removeListeners(getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertFalse(myRemoteEventService.isActive());
                assertEquals(3, getEventCount());
            }
        });

        executeActions();
    }

    public void testRegisterEventFilter() {
        assertFalse(myRemoteEventService.isActive());

        //start listen
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.addListener(TEST_DOMAIN, getListener(), getCallback());
            }
        });
        //register EventFilter
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.registerEventFilter(TEST_DOMAIN, getEventFilter(DummyIgnoredEvent.class), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(0, getEventCount());
            }
        });

        //add ignored event
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN, new DummyIgnoredEvent(), getCallback());
            }
        });

        //add event
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN, new DummyEvent(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(1, getEventCount()); //the DummyIgnoredEvent was filtered by the EventFilter
            }
        });

        //deregister EventFilter
        addAction(new TestAction() {
            public void execute() {
                myRemoteEventService.deregisterEventFilter(TEST_DOMAIN, getCallback());
            }
        });

        //add ignored event
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN, new DummyIgnoredEvent(), getCallback()); //the last DummyIgnoredEvent wasn't filtered, because the EventFilter is deregistered
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(2, getEventCount());
            }
        });

        //add event
        addAction(new TestAction(true) {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                myEventService.addEvent(TEST_DOMAIN, new DummyEvent(), getCallback());
            }
        });
        //check result
        addAction(new TestAction() {
            public void execute() {
                assertTrue(myRemoteEventService.isActive());
                assertEquals(3, getEventCount());
            }
        });

        executeActions();
    }
}
