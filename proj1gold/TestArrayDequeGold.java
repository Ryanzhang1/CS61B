import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> testArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> stdArray = new ArrayDequeSolution<>();
        String log = "";
        for (int i = 0; i < 1000; i++) {
            if (stdArray.size() == 0) {
                int number = StdRandom.uniform(1000);
                int headOrBack = StdRandom.uniform(2);
                if (headOrBack == 0) {
                    log = log + "addFirst(" + number + ")\n";
                    testArray.addFirst(number);
                    stdArray.addFirst(number);
                } else {
                    log = log + "addLast(" + number + ")\n";
                    testArray.addLast(number);
                    stdArray.addLast(number);
                }
            } else {
                int CaseNumber = StdRandom.uniform(4);
                Integer StdMoveNumber = 0;
                Integer testMoveNumber = 0;
                int addNumber = StdRandom.uniform(1000);
                switch (CaseNumber) {
                    case 0:
                        log = log + "addFirst(" + addNumber + ")\n";
                        testArray.addFirst(addNumber);
                        stdArray.addFirst(addNumber);
                        break;
                    case 1:
                        log = log + "addLast(" + addNumber + ")\n";
                        testArray.addLast(addNumber);
                        stdArray.addLast(addNumber);
                        break;
                    case 2:
                        log = log + "removeFirst()\n";
                        testMoveNumber = testArray.removeFirst();
                        StdMoveNumber = stdArray.removeFirst();
                        break;
                    case 3:
                        log = log + "removeLast()\n";
                        testMoveNumber = testArray.removeLast();
                        StdMoveNumber = stdArray.removeLast();
                        break;
                }
                //System.out.println(addNumber);
                assertEquals(log,StdMoveNumber, testMoveNumber);
            }
        }
    }
}
