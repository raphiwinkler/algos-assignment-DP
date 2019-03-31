/**
 * Glass Falling
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
    // Fill in here and change the return
    
    // Base cases
    if(floors == 1 || floors == 0 || sheets == 1) return floors;

    // Iterating through floors to find the optimal drop point
    int ans = floors, temp;
    for(int i = 1; i <= floors; i++){
      temp = 1 + Math.max(glassFallingRecur(i - 1, sheets - 1), glassFallingRecur(floors - i, sheets));
      if(temp < ans) ans = temp;
    }
    
    return ans;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors, int sheets) {
    // Fill in here and change the return

    // Creating storage and initializing to -1
    int memo[][] = new int[floors+1][sheets+1];
    for(int i = 0; i < floors+1; i++){
      for(int j = 0; j < sheets+1; j++){
        memo[i][j] = -1;
      }
    }

    // Calling helper function
    return glassFallingMemoizedHelper(floors, sheets, memo);
  }

  private int glassFallingMemoizedHelper(int floors, int sheets, int[][] memo){

    // If stored, returning answer
    if(memo[floors][sheets] >= 0) return memo[floors][sheets];

    int ans = floors, temp;

    // If not base cases, iterating through floors to find the optimal drop point
    if(floors > 1 && sheets > 1){
      for(int i = 1; i <= floors; i++){
        temp = 1 + Math.max(glassFallingMemoizedHelper(i - 1, sheets - 1, memo), glassFallingMemoizedHelper(floors - i, sheets, memo));
        if(temp < ans) ans = temp;
      }
    }

    // Storing answer
    memo[floors][sheets] = ans;

    return ans;

  }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
    // Fill in here and change the return
    
    // Creating storage
    int dp[][]= new int [sheets + 1][floors + 1];
    
    // Base cases
    for(int i = 1; i <= sheets; i++){
      dp[i][0] = 0;
      dp[i][1] = 1;
    }
    for(int i = 2; i <= floors; i++){
      dp[1][i] = i;
    } 

    // Iterating up through sheets and floors
    for(int i = 2; i <= sheets; i++){
      for(int j = 2; j <= floors; j++){

        // Iterating through lower floors to find the optimal drop point
        dp[i][j] = floors;
        for(int k = 1; k <= j; k++){
          int temp = 1 + Math.max(dp[i - 1][k - 1], dp[i][j - k]);
          if(temp < dp[i][j]) dp[i][j] = temp;
        }
      }
    }

    return dp[sheets][floors];
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Memo = gf.glassFallingMemoized(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
}
