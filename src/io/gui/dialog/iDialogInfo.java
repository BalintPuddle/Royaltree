package io.gui.dialog;

import javax.swing.*;

public interface iDialogInfo {
    <T> void create(JPanel panel, T modifiable);
    <T> void action(T modifiable);
}
