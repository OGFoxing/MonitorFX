package kz.ogfox.monitorfx.readers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * Created by pala4 on 15.02.2017.
 */
public class ShowNotifications {
    public void showNotifications(String title, String text) {
        Notifications notifications = Notifications.create()
                .title(title)
                .text(text)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }
                });
        notifications.darkStyle();
        notifications.showInformation();
        }
    }

