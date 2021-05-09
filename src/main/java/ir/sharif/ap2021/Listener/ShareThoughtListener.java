package ir.sharif.ap2021.Listener;


import ir.sharif.ap2021.Controller.ShareThoughtController;
import ir.sharif.ap2021.Event.ShareThoughtEvent;

import java.io.IOException;


public class ShareThoughtListener {

    private final ShareThoughtController shareThoughtController = new ShareThoughtController();

    public ShareThoughtListener() throws IOException {
    }

    public void eventOccurred(ShareThoughtEvent formEvent) throws IOException {
        shareThoughtController.share(formEvent);
    }
}
