public class BowlingScoreCounterFactory {
    public static BowlingScoreCounter createBowlingScoreCounter(){
        return new BowlingScoreCounterImpl();
    }
}
