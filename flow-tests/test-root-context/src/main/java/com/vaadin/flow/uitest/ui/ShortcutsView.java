/*
 * Copyright 2000-2018 Vaadin Ltd.
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

package com.vaadin.flow.uitest.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.ShortcutRegistration;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.uitest.servlet.ViewTestLayout;

@Route(value = "com.vaadin.flow.uitest.ui.ShortcutsView",
        layout = ViewTestLayout.class)
public class ShortcutsView extends Div {

    private Paragraph invisibleP = new Paragraph("invisible");
    private boolean attached = false;

    private ShortcutRegistration flipFloppingRegistration;

    public ShortcutsView() {
        Input actual = new Input();
        actual.setId("actual");
        actual.setValue("testing...");

        // clickShortcutWorks
        NativeButton button = new NativeButton();
        button.setId("button");
        button.addClickListener(e -> actual.setValue("button"));
        button.addClickShortcut(Key.KEY_B, KeyModifier.ALT);

        // focusShortcutWorks
        Input input = new Input();
        input.setId("input");
        input.addFocusShortcut(Key.KEY_F, KeyModifier.ALT);

        // shortcutsOnlyWorkWhenComponentIsVisible
        UI.getCurrent().addShortcutListener(() -> {
            invisibleP.setVisible(!invisibleP.isVisible());
            actual.setValue("toggled!");
        }, Key.KEY_I, KeyModifier.ALT);

        Shortcuts.addShortcutListener(invisibleP, () -> actual
                .setValue("invisibleP"), Key.KEY_V).withAlt();

        add(actual, button, input, invisibleP);

        // listenOnScopesTheShortcut
        Div subview = new Div();
        subview.setId("subview");

        Input focusTarget = new Input();
        focusTarget.setId("focusTarget");

        subview.add(focusTarget);

        Shortcuts.addShortcutListener(subview,
                () -> actual.setValue("subview"), Key.KEY_S, KeyModifier.ALT)
                .listenOn(subview);

        add(subview);

        // shortcutsOnlyWorkWhenComponentIsAttached
        Paragraph attachable = new Paragraph("attachable");
        attachable.setId("attachable");

        Shortcuts.addShortcutListener(attachable, () -> actual
                .setValue("attachable"), Key.KEY_A).withAlt();

        UI.getCurrent().addShortcutListener(() -> {
            attached = !attached;
            if (attached) {
                add(attachable);
            }
            else {
                remove(attachable);
            }
            actual.setValue("toggled!");
        }, Key.KEY_Y, KeyModifier.ALT);

            // modifyingShortcutShouldChangeShortcutEvent
        flipFloppingRegistration =
                UI.getCurrent().addShortcutListener(event -> {
                    if (event.getKeyModifiers().contains(KeyModifier.ALT)) {
                        actual.setValue("Alt");
                        flipFloppingRegistration.withModifiers(
                                KeyModifier.SHIFT);
                    } else if (event.getKeyModifiers().contains(
                            KeyModifier.SHIFT)) {
                        actual.setValue("Shift");
                        flipFloppingRegistration.withModifiers(KeyModifier.ALT);
                    }
                    else {
                        actual.setValue("Failed");
                    }

                }, Key.KEY_G, KeyModifier.ALT);

        // clickShortcutAllowsKeyDefaults
        Div wrapper1 = new Div();
        Div wrapper2 = new Div();
        final Input clickInput1 = new Input();
        clickInput1.setType("text");
        clickInput1.setId("click-input-1");

        final Input clickInput2 = new Input();
        clickInput2.setType("text");
        clickInput2.setId("click-input-2");

        NativeButton clickButton1 = new NativeButton("CB1",
                event -> actual.setValue("click: " + clickInput1.getValue()));
        ShortcutRegistration r1 =
                clickButton1.addClickShortcut(Key.ENTER).listenOn(wrapper1);

        NativeButton clickButton2 = new NativeButton("CB2",
                event -> actual.setValue("click: " + clickInput2.getValue()));
        clickButton2.addClickShortcut(Key.ENTER).listenOn(wrapper2)
                // this matches the default of other shortcuts but changes
                // the default of the click shortcut
                .setBrowserDefaultAllowed(false);

        wrapper1.add(clickInput1, clickButton1);
        wrapper2.add(clickInput2, clickButton2);
        add(wrapper1, wrapper2);
    }
}
