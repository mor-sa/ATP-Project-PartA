package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {
        Maze newMaze = new Maze(rows, columns);
        //newMaze.setStartAndGoal();
        for (int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                newMaze.setValue(new Position(i,j),0);
            }
        }
        Position startPos = newMaze.randPosOnVertex();
        newMaze.setStartPosition(startPos.getX(), startPos.getY());
        do{
            Position goalPos = newMaze.randPosOnVertex();
            newMaze.setGoalPosition(goalPos.getX(), goalPos.getY());
        }while(newMaze.sameVertex(newMaze.getStartPosition(),newMaze.getGoalPosition()));
        return newMaze;
    }
}
