package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.ViewController;

public class InputAction extends Thread{
    private ViewController viewController;

    public InputAction(ViewController v){
        this.viewController = v;
    }
    @Override
    public void run() {
        try {
            while(true)
                this.viewController.handleAction();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

