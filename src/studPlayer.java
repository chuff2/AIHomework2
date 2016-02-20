/****************************************************************
 * studPlayer.java
 * Implements MiniMax search with A-B pruning and iterative deepening search (IDS). The static board
 * evaluator (SBE) function is simple: the # of stones in studPlayer's
 * mancala minue the # in opponent's mancala.
 * -----------------------------------------------------------------------------------------------------------------
 * Licensing Information: You are free to use or extend these projects for educational purposes provided that
 * (1) you do not distribute or publish solutions, (2) you retain the notice, and (3) you provide clear attribution to UW-Madison
 *
 * Attribute Information: The Mancala Game was developed at UW-Madison.
 *
 * The initial project was developed by Chuck Dyer(dyer@cs.wisc.edu) and his TAs.
 *
 * Current Version with GUI was developed by Fengan Li(fengan@cs.wisc.edu).
 * Some GUI componets are from Mancala Project in Google code.
 */




//################################################################
// studPlayer class
//################################################################

public class studPlayer extends Player {


    /*Use IDS search to find the best move. The step starts from 1 and increments by step 1.
     *Note that the search can be interrupted by time limit.
     */
    public void move(GameState state){
    	//start IDS off with a depth limit of 1. At each iteration increment it
    	int newDepthLimit = 1;
    	//forever loop. Interrupt will cause us to exit this function and whatever move is set to 
    	//will be the move that is chosen
    	while (true){
    		this.move = maxAction(state, newDepthLimit); //TODO do i need to check if this is better than the last one?
    		newDepthLimit++;
    	}
    }

    //Return best move for max player. Note that this is a wrapper function created for ease to use.
    public int maxAction(GameState state, int maxDepth){
    	//start the depth off at 0. It will be increased with further calls to maxAction and minAction
    	int currentDepth = 0;//TODO might need to be 1?
    	int alpha = Integer.MIN_VALUE;
    	int beta = Integer.MAX_VALUE;
    	int bestMove = maxAction(state, currentDepth, maxDepth, alpha, beta);
    	return bestMove;
    }
    //return best move for min player. Note that this is a wrapper function created for ease to use.
    public int minAction(GameState state, int maxDepth){
    	//not sure that we even ever use this function...
    	//confirmed on piazza... this function is useless
    	return 0;
    }
    
    //return best move for max player
    public int maxAction(GameState state, int currentDepth, int maxDepth, int alpha, int beta){
    	//TODO do terminal test here
    	
    	//our value to determine cutoffs
    	int v = Integer.MIN_VALUE;
    	//if we still have room to explore in our tree
    	if (currentDepth < maxDepth){
    		//loop through all possible moves for this state
    		for (int i = 0; i < 6; i++){
    			//make sure the prospective play is one we can actually make
    			if (!state.illegalMove(i)){
    				//here we get the copy of the given current state
        			GameState childState = new GameState(state);
        			//now we apply to move to the copied state, so that we don't destroy the actual state
        			childState.applyMove(i);
        			v = Math.max(v, minAction(childState, currentDepth, maxDepth, alpha, beta));
        			if (v >= beta)
        				return v;
        			alpha = Math.max(alpha, v);
    			}
    		}
    	}
    	//currentDepth must be == maxDepth here
    	else{
    		//TODO make sure this satisfies the tie condition explained in the spec
        	return sbe(state);
    	}
    	return v;
    }
    //return best move for min player
    public int minAction(GameState state, int currentDepth, int maxDepth, int alpha, int beta){
    	//TODO do terminal test here
    	
    	int v = Integer.MAX_VALUE;
    	//if we still have room to explore in our tree
    	if (currentDepth < maxDepth){
    		//loop through all possible moves for this state
    		for (int i = 0; i < 6; i++){
    			//make sure the prospective play is one we can actually make
    			if (!state.illegalMove(i)){
    				//here we get the copy of the given current state
        			GameState childState = new GameState(state);
        			//now we apply to move to the copied state, so that we don't destroy the actual state
        			childState.applyMove(i);
        			v = Math.max(v, maxAction(childState, currentDepth, maxDepth, alpha, beta));
        			if (v <= alpha)
        				return v;
        			beta = Math.min(beta, v);
    			}
    		}
    	}
    	//currentDepth must be == maxDepth here
    	else{
    		//TODO make sure this satisfies the tie condition explained in the spec
    		//TODO also make sure that this is ok for the min function (i.e. can we copy this from maxAction??)
    		//i totally see where this question is coming from... https://piazza.com/class/ij0elbmn6x64s5?cid=91
        	return sbe(state);
    	}
    	return v;
    }

    //the sbe function for game state. Note that in the game state, the bins for current player are always in the bottom row.
    private int sbe(GameState state){
    	
    	return 0;
    }


}
