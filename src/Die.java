public class Die
{
    /** Instance Variables **/
    private int sides;
    private int timesRolled;

    /** Constructors **/
    public Die() {
        sides = 6;
        timesRolled = 0;
    }

    public Die(int numSides) {
        sides = numSides;
        timesRolled = 0;

    }

    /** Methods **/
    public int getSides() {
        return sides;
    }

    /**
     * Returns a random int between 1 and
     * the number of sides on the Die
     */
    public int roll() {
        int num = (int)(Math.random()*sides) + 1;
        timesRolled++;
        return num;
    }

    //Returns times dice has been rolled
    public int getTimesRolled(){
        return timesRolled;
    }

    /**
     * Rolls the dice the numRolls times
     * and returns the max value of the rolls
     */
    public int getMaxRoll(int numRolls) {
        int max = Integer.MIN_VALUE;
        for(int i=0; i<numRolls; i++) {
            int num = roll();
            max = Math.max(max, num);
        }
        timesRolled -= 3;
        return max;
    }

    /**
     * TODO: Write a method that rolls the
     * die numRolls times and returns the
     * min value of the rolls
     */

    public int getMinRoll(int numRolls){
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < numRolls; i++){
            int num = roll();
            min = Math.min(min, num);
        }
        timesRolled -= 3;
        return min;
    }

    public String toString() {
        return "This is a " + sides + " sided die.";
    }
}