package nl.abdel.aoc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BingoGame {

    private LinkedList<Integer> luckyNumbers = new LinkedList<>();
    private final List<BingoBoard> boards = new LinkedList<>();

    private int currentLuckyNumberIndex;

    public BingoGame(String numbers) {
        List<String> numberParts = Arrays.asList(numbers.split("\n\n"));
        this.luckyNumbers = Arrays
                .stream(numberParts.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));
        createBoards(numberParts.subList(1, numberParts.size()));
    }

    private void createBoards(List<String> boardStrings) {
        for (String boardString : boardStrings) {
            boards.add(new BingoBoard(boardString));
        }
    }

    public BingoBoard getBoard(int boardNumber) {
        return boards.get(boardNumber);
    }

    public void drawNumber() {
        for (BingoBoard board : boards) {
            board.drawNumber(luckyNumbers.get(currentLuckyNumberIndex));
        }

        currentLuckyNumberIndex++;
    }

    public boolean isFinished() {
        return currentLuckyNumberIndex >= luckyNumbers.size() || boards.stream().anyMatch(BingoBoard::hasBingo);
    }

    public int getNumberOfPlayers() {
        return boards.size();
    }

    public Optional<BingoBoard> getWinningBoard() {
        for (BingoBoard board : boards) {
            if (board.hasBingo()) {
                return Optional.of(board);
            }
        }

        return Optional.empty();
    }

    public int numbersLeft() {
        return luckyNumbers.size() - 1 - currentLuckyNumberIndex;
    }

    public static class BingoBoard {
        private List<List<Integer>> numbers = new LinkedList<>();
        private List<List<Boolean>> markedNumbers = new LinkedList<>();
        private int lastNumberCalled;

        public BingoBoard(String boardString) {
            String[] rows = boardString.split("\n");
            for (String row : rows) {
                String[] numberStrings = row.split(" ");
                numbers.add(
                        Arrays.stream(numberStrings)
                                .filter(numberString -> !numberString.isEmpty())
                                .map(Integer::parseInt)
                                .collect(Collectors.toCollection(LinkedList::new))
                );
                markedNumbers.add(
                        Arrays.stream(numberStrings)
                                .filter(numberString -> !numberString.isEmpty())
                                .map(n -> false)
                                .collect(Collectors.toCollection(LinkedList::new))
                );
            }
        }

        public int[][] getNumbers() {
            int rows = numbers.size();
            int columns = numbers.stream().max(Comparator.comparing(row -> row.size())).get().size();
            int[][] numberGrid = new int[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    numberGrid[row][column] = numbers.get(row).get(column);
                }
            }

            return numberGrid;
        }

        public boolean[][] getMarkedNumbers() {
            int rows = markedNumbers.size();
            int columns = markedNumbers.stream().max(Comparator.comparing(row -> row.size())).get().size();
            boolean[][] markedGrid = new boolean[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    markedGrid[row][column] = markedNumbers.get(row).get(column);
                }
            }

            return markedGrid;
        }

        public boolean hasBingo() {
            for (int row = 0; row < numbers.size(); row++) {
                boolean rowIsMarked = markedNumbers.get(row).stream().allMatch(isMarked -> isMarked == Boolean.TRUE);
                if (rowIsMarked) return true;
                for (int column = 0; column < numbers.get(row).size(); column++) {
                    int finalColumn = column;
                    boolean columnIsMarked = markedNumbers.stream().allMatch(markedRow -> markedRow.get(finalColumn) == Boolean.TRUE);
                    if (columnIsMarked) return true;
                }
            }

            return false;
        }

        public int getScore() {
            if (!hasBingo()) return 0;

            int sumOfUnmarkedNumbers = 0;
            for (int row = 0; row < markedNumbers.size(); row++) {
                for (int column = 0; column < markedNumbers.size(); column++) {
                    if (markedNumbers.get(row).get(column) == Boolean.FALSE) {
                        sumOfUnmarkedNumbers += numbers.get(row).get(column);
                    }
                }
            }

            return sumOfUnmarkedNumbers * lastNumberCalled;
        }

        private void drawNumber(Integer number) {
            lastNumberCalled = number;
            for (List<Integer> row : numbers) {
                if (!row.contains(number)) {
                    continue;
                }

                int indexOfRow = numbers.indexOf(row);
                int indexOfNumber = row.indexOf(number);
                markedNumbers.get(indexOfRow).set(indexOfNumber, Boolean.TRUE);
            }
        }
    }
}
