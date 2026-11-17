package balintpuddle.io.gui.dialog;

import javax.swing.*;
import java.awt.*;

public class AboutDialog implements iDialogInfo{
    private JPanel panel;
    private String text;

    @Override
    public <T> void create(JPanel panel, T modifiable) {
        this.panel = panel;
        this.text = (String) modifiable;

        panel.setLayout(new BorderLayout());
        JPanel container = new JPanel(new FlowLayout());
        JPanel marginTop = new JPanel(), marginBottom = new JPanel();
        marginTop.setPreferredSize(new Dimension(100,80));
        marginBottom.setPreferredSize(new Dimension(100,80));
        panel.add(marginTop, BorderLayout.NORTH);
        panel.add(container, BorderLayout.CENTER);
        panel.add(marginBottom, BorderLayout.SOUTH);

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont((new Font("Verdana", Font.PLAIN, 18)));
        container.add(label);
    }

    @Override
    public <T> void action(T modifiable) {

    }
}
