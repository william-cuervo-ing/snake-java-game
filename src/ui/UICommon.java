package ui;

import java.awt.*;

public interface UICommon {

    default void showComponent(Container container, Component component) {
        for (int i = 0; i < container.getComponents().length; i++) {
            container.getComponents()[i].setVisible(false);
            container.remove(i);
            i = 0;
        }
        container.add(component);
        component.setVisible(true);
    }

}
