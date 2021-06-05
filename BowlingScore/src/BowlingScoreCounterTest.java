import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingScoreCounterTest {
    BowlingScoreCounter scoreCounter = BowlingScoreCounterFactory.createBowlingScoreCounter();
    @Test
    void testAllStrike(){
        String test = "X X X X X X X X X X X X";
        assertEquals(300, scoreCounter.countScore(test));
    }
    @Test
    void testAllSpare5(){
        String test = "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5";
        assertEquals(150, scoreCounter.countScore(test));
    }
    @Test
    void testAllSpareMiss(){
        String test = "-/ -/ -/ -/ -/ -/ -/ -/ -/ -/ -";
        assertEquals(100, scoreCounter.countScore(test));
    }
    @Test
        void testAllSpare9(){
            String test = "9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9";
            assertEquals(190, scoreCounter.countScore(test));
    }
    @Test
        void testAllMiss(){
            String test = "-- -- -- -- -- -- -- -- -- --";
            assertEquals(0, scoreCounter.countScore(test));
    }
    @Test
        void test5Miss(){
            String test = "5- 5- 5- 5- 5- 5- 5- 5- 5- 5-";
            assertEquals(50, scoreCounter.countScore(test));
    }
    @Test
        void testVariousFrames1(){
            String test = "X 5- 8/ 42 -- -8 3- X X 11";
            assertEquals(86, scoreCounter.countScore(test));
    }
    @Test
        void testVariousFrames2(){
            String test = "X X 81 9/ X -8 8/ X 9- 6/ X";
            assertEquals(170, scoreCounter.countScore(test));
    }

}