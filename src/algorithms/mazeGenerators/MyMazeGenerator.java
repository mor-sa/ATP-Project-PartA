package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.List;

public class MyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {
        Maze newMaze = new Maze(rows, columns);
        newMaze.setStartAndGoal();
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                newMaze.setValue(new Position(i,j),1);
            }
        }

        //creating lists
        List<Position> walls = new ArrayList<>();
        List<Position> visited = new ArrayList<>();

        // Starting with the start position of the maze and building a tree from there
        /*what was was
        Position randPos = newMaze.getStartPosition();
        newMaze.setValue(randPos, 0);
        List<Position> randNeighbors = newMaze.getNeighbors(randPos);
        for (int i=0; i<randNeighbors.size(); i++){
            if(newMaze.getValue(randNeighbors.get(i)) == 1){
                walls.add(randNeighbors.get(i));
                visited.add(randNeighbors.get(i));
            }
        }*/

        //ronen changed
        newMaze.setValue(newMaze.getStartPosition(), 0);
        List<Position> startPositionNeighbors = newMaze.getNeighbors(newMaze.getStartPosition());
        for (Position startPositionNeighbor : startPositionNeighbors) {
            if (startPositionNeighbor != null && newMaze.getValue(startPositionNeighbor) == 1) {
                walls.add(startPositionNeighbor);
            }
        }
        visited.add(newMaze.getStartPosition());

        while(!walls.isEmpty()){
            int wallIndex = (int)(Math.random()*(walls.size()));
            Position wall = walls.get(wallIndex);
            List<Position> wallNeighbors = newMaze.getNeighbors(wall);
            int breakWall = 0;
            for (int i=0; i<wallNeighbors.size(); i+=2){
                if (wallNeighbors.get(i) != null && wallNeighbors.get(i+1) != null){
                    if (visited.contains(wallNeighbors.get(i))){
                        if (!visited.contains(wallNeighbors.get(i+1))){
                            breakWall = 1;
                        }
                    }
                    else{
                        if (visited.contains(wallNeighbors.get(i+1))){
                            breakWall = 1;
                        }
                    }
                }
            }
            if (breakWall == 1){
                newMaze.setValue(wall,0);
                visited.add(wall);
            }
            walls.remove(wall);

//            if(randPos.getX() ) {
//                if (curWallRow < randPos.getX()) {
//                    if (curWallRow > 0) {
//                        Position curPos = new Position(curWallRow - 1, curWallCol);
//                        if (newMaze.getValue(curPos) == 1) {
//                            newMaze.setValue(walls.get(rand), 0);
//                            newMaze.setValue(curPos, 0);
//                            randNeighbors = newMaze.getNeighbors(curPos);
//                            for (int i = 0; i < randNeighbors.size(); i++) {
//                                if (newMaze.getValue(randNeighbors.get(i)) == 1) {
//                                    walls.add(randNeighbors.get(i));
//                                }
//                            }
//                            randNeighbors.remove(rand);
//                        }
//                    }
//                }
//            }
            /* check if only one of the 2 cells the wall divides is visited
             if(...){
                wall is passage


              }

              change randPos;
             */

        }

        return newMaze;
    }
}
