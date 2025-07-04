package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.CLIController;

/**
 * This class listens the actions by the user in CLI
 */
public class InputAction extends Thread{
    private CLIController viewController;

    public InputAction(CLIController v){
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

