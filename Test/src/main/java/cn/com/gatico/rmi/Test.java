package cn.com.gatico.rmi;

import javax.print.ServiceUI;
import javax.smartcardio.*;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();

        if (terminals != null && !terminals.isEmpty()) {
            // Use the first terminal
            CardTerminal terminal = terminals.get(0);
            // Connect with the card
            Card card = terminal.connect("*");
            CardChannel channel = card.getBasicChannel();
            int channelNumber = channel.getChannelNumber();
            System.out.println(channelNumber);
            channel.transmit(new CommandAPDU(new byte[]{}));

            // Disconnect the card
        } else {
        }
    }
}
