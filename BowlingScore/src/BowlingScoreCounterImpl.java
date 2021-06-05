public class BowlingScoreCounterImpl implements BowlingScoreCounter{
    /**
     * Pre-condition: Needs to be a valid string of frames
     * @param frames - The result for each frame for a single player, from a finished game.
     *               -  Given as a String where a space (' ') indicates the end of a frame,
     *               'X' is a strike,
     *               '/'is a spare,
     *               '-' is a miss,
     *               the numbers 1-9 indicates the number of pins knocked down for each roll.
     * @return The total score for all frames and rolls.
     */
    @Override
    public int countScore(String frames){
        int totalScore = 0;
        int frameScore = 0;
        int frameCount = 0;
        for(int i=0;i<frames.length();i++){
            switch(frames.charAt(i)) {
                //End of frame, adds frameScore to totalScore and resets for next frame.
                case ' ':
                    totalScore += frameScore;
                    frameScore = 0;
                    frameCount++;
                    break;
                //Strike, sets the frameScore to 10 + bonus for the scores in the following two rolls.
                case 'X':
                    //Handles bonus throws that are part of the last frame.
                    if(frameCount == 9){
                        frameScore += 10;
                    }
                    else{frameScore = 10 + strikeBonus(frames,i);}
                    break;
                //Spare, sets the frameScore to 10 + bonus for the score in the next roll.
                case '/':
                    //Handles bonus throw that are part of the last frame.
                    if(frameCount == 9){
                        frameScore = 10;
                    }
                    else {
                        frameScore = 10 + spareBonus(frames, i);
                    }
                //Miss, no points.
                case '-':
                    break;
                //Adds the number of pins knocked down on current roll to frameScore.
                default:
                        frameScore += convertCharToInt(frames.charAt(i));
            }
        }
        //adds the last frame to totalScore, and returns result.
        return totalScore+frameScore;
    }

    /**
     * Pre-condition: c needs to be a character of a number 1-9, else 0 is returned
     * @param c - a character that will be returned as an int.
     * @return an int of the character c
     */
    private int convertCharToInt(char c){
        switch (c){
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
        }
        return 0;
    }

    //Returns the bonus for the next two rolls after achieving a strike.
    private int strikeBonus(String game, int index){
        return bonus(game,index,2);
    }
    //Returns the bonus for the next roll after achieving a spare.
    private int spareBonus(String game, int index){
        return bonus(game,index,1);
    }

    /**
     * @param game - The result for each frame for a single player, from a finished game.
     * @param index - - The index for the current roll in countScore.
     * @param bonusRolls - The number of bonus rolls, 1 for spare, 2 for strike.
     * @return The bonus achieved from the following rolls, after achieving a spare or a strike.
     */
    private int bonus(String game, int index, int bonusRolls){
        int bonusScore = 0;
        int rolls = bonusRolls;
        for(int i=index+1;i<game.length();i++){
            if(rolls == 0){
                return bonusScore;
            }
            switch (game.charAt(i)){
                case ' ':
                    break;
                case 'X':
                    bonusScore += 10;
                    rolls--;
                    break;
                case '/':
                    bonusScore = 10;
                    rolls--;
                    break;
                case '-':
                    rolls--;
                    break;
                default:
                    bonusScore += convertCharToInt(game.charAt(i));
                    rolls--;
                    break;
            }
        }
        return bonusScore;
    }
}
