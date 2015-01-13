/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.dbcp2;

import org.apache.commons.logging.Log;
import org.apache.commons.pool2.SwallowedExceptionListener;

/**
 * Class for logging swallowed exceptions.
 * @version $Id$
 * @since 2.0
 */
public class SwallowedExceptionLogger implements SwallowedExceptionListener{

    private final Log log;
    private final boolean logExpiredConnections;

    /**
     * Create a SwallowedExceptionLogger with the given logger.  By default,
     * expired connection logging is turned on.
     *
     * @param log logger
     */
    public SwallowedExceptionLogger(Log log) {
        this(log, true);    
    }
    
    /**
     * Create a SwallowedExceptionLogger with the given logger and expired
     * connection logging property.
     *
     * @param log logger
     * @param logExpiredConnections false suppresses logging of expired connection events
     */
    public SwallowedExceptionLogger(Log log, boolean logExpiredConnections) {
        this.log = log;
        this.logExpiredConnections = logExpiredConnections;
    }

    @Override
    public void onSwallowException(Exception e) {
        if (logExpiredConnections || !(e instanceof LifetimeExceededException)) {
            log.warn(Utils.getMessage(
                    "swallowedExceptionLogger.onSwallowedException"), e);
        }
    }
}
