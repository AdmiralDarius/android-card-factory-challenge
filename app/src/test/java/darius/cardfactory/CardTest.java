package darius.cardfactory;

import junit.framework.TestCase;


public class CardTest extends TestCase {
    protected Card cards[]=new Card[5];

    protected void setUp() {
        for(int i=0;i<5;i++) {
            cards[i] = new Card(0, "", "", "");
            cards[i].setRoll(i);
        }
    }
    // roll is chosen randomly here we initialize and check them
    public void testChoose_card(){
        assert(Card.choose_card(cards)==cards[4]);
        for(int i=0;i<5;i++)
            cards[i].setRoll(-i);
        assert(Card.choose_card(cards)==cards[0]);
    }
}