package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.List;

public class MyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {
        Maze newMaze = new Maze(rows, columns);
        //newMaze.setStartAndGoal();
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                newMaze.setValue(new Position(i,j),1);
            }
        }
        //creating lists
        List<Position> walls = new ArrayList<>();
        List<Position> visited = new ArrayList<>();

        // Starting with the start position of the maze and building a tree from there
        Position startPos = newMaze.randPosOnVertex();
        newMaze.setStartPosition(startPos.getX(), startPos.getY());
        newMaze.setValue(newMaze.getStartPosition(), 0);
        visited.add(newMaze.getStartPosition());
        List<Position> startPositionNeighbors = newMaze.getNeighbors(newMaze.getStartPosition());
        for (Position startPositionNeighbor : startPositionNeighbors) {
            if (startPositionNeighbor != null && newMaze.getValue(startPositionNeighbor) == 1) {
                walls.add(startPositionNeighbor);
            }
        }
        //Going through all walls
        while(!walls.isEmpty()){
            int wallIndex = (int)(Math.random()*(walls.size()));
            Position wall = walls.get(wallIndex);
            List<Position> wallNeighbors = newMaze.getNeighbors(wall);
            int counter = 0;
            Position selectedNeighbor = new Position(-1,-1);
            for (Position wallNeighbor : wallNeighbors) {
                if (visited.contains(wallNeighbor)) {
                    counter++;
                    selectedNeighbor = wallNeighbor;
                }
            }
            //if only one visited cell then break wall
            if (counter == 1){
                newMaze.setValue(wall, 0);
                visited.add(wall);
                Position unvisited = newMaze.unvisitedPosition(wall, selectedNeighbor);
                if (unvisited.getX() != -1){
                    newMaze.setValue(unvisited, 0);
                    visited.add(unvisited);
                    List<Position> toAddtoWalls = newMaze.getNeighbors(unvisited);
                    for (Position toAddtoWall : toAddtoWalls) {
                        if (newMaze.getValue(toAddtoWall) == 1 && !walls.contains(toAddtoWall)) {
                            walls.add(toAddtoWall);
                        }
                    }
                }

            }
            walls.remove(wall);
        }
        do{
            Position goalPos = newMaze.randPosOnVertex();
            newMaze.setGoalPosition(goalPos.getX(), goalPos.getY());
        }while(newMaze.sameVertex(newMaze.getStartPosition(),newMaze.getGoalPosition()) || newMaze.getValue(newMaze.getGoalPosition()) == 1);
        return newMaze;
    }
}
