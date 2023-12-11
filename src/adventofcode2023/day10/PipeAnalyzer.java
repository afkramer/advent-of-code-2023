package adventofcode2023.day10;

public class PipeAnalyzer {

    public String createPipeSequence() {
        StringBuilder sb = new StringBuilder();

        Pipe currPipe = PipeMatrix.getStartPipe();
        boolean isEndOfPipe = false;

        while (!isEndOfPipe) {

            Pipe pipeToNorth = currPipe.findPipeToNorth();
            Pipe pipeToEast = currPipe.findPipeToEast();
            Pipe pipeToSouth = currPipe.findPipeToSouth();
            Pipe pipeToWest = currPipe.findPipeToWest();
            currPipe.visit();

            if (currPipe.getType().canLeaveToNorth() && pipeToNorth != null && !pipeToNorth.wasVisited()
                    && pipeToNorth.getType().resultEntryFromSouth() != null) {
                sb.append(pipeToNorth.getCode());
                currPipe = pipeToNorth;
            } else if (currPipe.getType().canLeaveToEast() && pipeToEast != null && !pipeToEast.wasVisited()
                    && pipeToEast.getType().resultEntryFromWest() != null) {
                sb.append(pipeToEast.getCode());
                currPipe = pipeToEast;
            } else if (currPipe.getType().canLeaveToSouth() && pipeToSouth != null && !pipeToSouth.wasVisited()
                    && pipeToSouth.getType().resultEntryFromNorth() != null) {
                sb.append(pipeToSouth.getCode());
                currPipe = pipeToSouth;
            } else if (currPipe.getType().canLeaveToWest() && pipeToWest != null && !pipeToWest.wasVisited()
                    && pipeToWest.getType().resultEntryFromEast() != null) {
                sb.append(pipeToWest.getCode());
                currPipe = pipeToWest;
            } else if ((pipeToNorth != null && pipeToNorth.getType() == PipeType.START_END)
                    || (pipeToEast != null && pipeToEast.getType() == PipeType.START_END)
                    || (pipeToSouth != null && pipeToSouth.getType() == PipeType.START_END)
                    || (pipeToWest != null && pipeToWest.getType() == PipeType.START_END)) {
                isEndOfPipe = true;
            }
        }

        return sb.toString();
    }

    public int countInteriorTiles() {
        int sum = 0;
        for (Pipe pipe : PipeMatrix.getPipeMaze()) {
            if (!pipe.wasVisited() && !pipe.wasCounted()) {
                sum += PipeMatrix.findNeighboringUnvisitedEnclosedPipes(pipe).size();
            }
        }
        return sum;
    }

}
