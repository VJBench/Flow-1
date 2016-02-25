/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.ui;

import java.io.Serializable;

/**
 * Provides method for configuring the loading indicator.
 *
 * @author Vaadin Ltd
 * @since 7.1
 */
public interface LoadingIndicatorConfiguration extends Serializable {
    /**
     * Sets the delay before the loading indicator is shown. The default is
     * 300ms.
     *
     * @param firstDelay
     *            The first delay (in ms)
     */
    public void setFirstDelay(int firstDelay);

    /**
     * Returns the delay before the loading indicator is shown.
     *
     * @return The first delay (in ms)
     */
    public int getFirstDelay();

    /**
     * Sets the delay before the loading indicator goes into the "second" state.
     * The delay is calculated from the time when the loading indicator was
     * triggered. The default is 1500ms.
     *
     * @param secondDelay
     *            The delay before going into the "second" state (in ms)
     */
    public void setSecondDelay(int secondDelay);

    /**
     * Returns the delay before the loading indicator goes into the "second"
     * state. The delay is calculated from the time when the loading indicator
     * was triggered.
     *
     * @return The delay before going into the "second" state (in ms)
     */
    public int getSecondDelay();

    /**
     * Sets the delay before the loading indicator goes into the "third" state.
     * The delay is calculated from the time when the loading indicator was
     * triggered. The default is 5000ms.
     *
     * @param thirdDelay
     *            The delay before going into the "third" state (in ms)
     */
    public void setThirdDelay(int thirdDelay);

    /**
     * Returns the delay before the loading indicator goes into the "third"
     * state. The delay is calculated from the time when the loading indicator
     * was triggered.
     *
     * @return The delay before going into the "third" state (in ms)
     */
    public int getThirdDelay();
}