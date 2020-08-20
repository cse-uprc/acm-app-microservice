package com.acm.translations;

public class EmailTranslation {
        public final static String NEW_USER_TEMPLATE_ONE = "<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
                        + "<tr>" + "<td bgcolor=\"#72b3fd\""
                        + "style=\"padding: 15px 30px 15px 30px;background-color: #72b3fd;\">"
                        + "<h2 style=\"text-align: center; margin: 0; padding: 0;\">New User Request</h2>" + "</td>"
                        + "</tr>" + "<tr>" + "<td style=\"padding: 15px 15px 15px 15px;background-color: #f3f3f3;\">"
                        + "<p style=\" margin-bottom: 5px; font-size: 20px;\">Dear Admin,</p>"
                        + "<p style=\"margin-top: 0; margin-left: 20px; font-size: 16px;line-height: 20px;\">"
                        + "A new user request has been submited for";
        public final static String NEW_USER_TEMPLATE_TWO = "You can view this request by clicking the following link below. "
                        + "Once the status of the request is reviewed, then the user will recieve an email "
                        + "letting them know the status of their request has changed." + "</p>" + "</td>" + "</tr>"
                        + "<tr>" + "<td style=\"padding: 0 30px 30px 30px;background-color: #f3f3f3;color: black;\">"
                        + "Click " + "<a href=\"https://acm-ui-dev.herokuapp.com/\" style=\"color: blue;\"> here </a>"
                        + " to view new user!" + "</td>" + "</tr>" + "</table>";
}