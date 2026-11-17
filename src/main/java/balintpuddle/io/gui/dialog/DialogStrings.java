package balintpuddle.io.gui.dialog;

import balintpuddle.global.ImmutableFlags;

public class DialogStrings {
    public static String ABOUT_ROYALTREE = "";
    public static String ABOUT_CREDITS = "";

    public static void init() {
        ABOUT_ROYALTREE = "<html><b>ROYALTREE</b> is a project aimed at providing a useful and easy set of tools <br>for creating family trees primarily for worldbuilding or other related purposes. <br><br>" +
                "It was originally created as a first project for learning about concepts related to<br> desktop application development, and for earning experience in this area.<br> The project is planned to be updated and patched with the current primary goal being<br>adding a large set of new features already planned by the developer,<br> while listening to community feedback, and fixing any issues." +
                "<br><br>Current version: " + ImmutableFlags.VERSION + " </html>";
        ABOUT_CREDITS = "<html><b>Contributors:</b><br>Developer: BalintPuddle<br> Art: BalintPuddle</html>";
    }
}
