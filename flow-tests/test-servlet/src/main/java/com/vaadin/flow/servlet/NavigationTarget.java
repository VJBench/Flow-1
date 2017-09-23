/*
 * Copyright 2000-2017 Vaadin Ltd.
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
package com.vaadin.flow.servlet;

import com.vaadin.router.Route;
import com.vaadin.ui.html.Div;

/**
 * Navigation target for which a servlet should automatically be registered.
 *
 * @author Vaadin Ltd
 */
@Route("")
public class NavigationTarget extends Div {
    public NavigationTarget() {
        setText("Hello world");
        setId("navigationTarget");
    }
}
