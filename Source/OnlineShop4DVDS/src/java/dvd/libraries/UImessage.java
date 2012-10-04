/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.libraries;

/**
 *
 * @author Administrator
 */
public class UImessage {
    /**
     * Method set message result for form
     * @param typemessage color for message ('red,green,blue,yellow')
     * @param message text message
     * @param linkmessage_redirect link to error form
     * @param textlinkmessage text link error form
     * @return string text
     */
    public static String generalMessage(String typemessage, String message, String linkmessage_redirect, String textlinkmessage) {
        String contentd = "<div class='ContentMessage-p'>";
        contentd += "<div id='message-" + typemessage + "' class='closeMSonly'>";
        contentd += "<table border = '0' width = '100%' cellpadding = '0' cellspacing = '0' >";
        contentd += "<tr>";
        contentd += "<td class='" + typemessage + "-left'>" + message + "&nbsp;&nbsp;<a href='" + linkmessage_redirect + "'>" + textlinkmessage + "</a></td>";
        contentd += "<td class='" + typemessage + "-right'><a runnat='server' class='close-" + typemessage + "'id='close-ly'><div id='messageicon" + typemessage + "'></div>" + "</a></td>";
        contentd += "</tr>";
        contentd += "</table>";
        contentd += "</div>";
        contentd += "</div>";
        return contentd;
    }

    public static String publishAndUn(Boolean status) {
        if (status == true) {
            return "../Iconpage/on.jpg";
        } else {
            return "../Iconpage/off.jpg";
        }
    }
}
